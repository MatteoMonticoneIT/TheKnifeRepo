package theknife.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import simple.socket.*;

/**
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public class serverTK 
{
  public static void main(String[] args) 
  {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=====================================\n" +
                       "   Avvio TheKnife Server (serverTK)  \n" +
                       "=====================================\n");

    System.out.print("Inserisci l'host del DB (localhost:5432): ");
    String dbHost       = scanner.nextLine();

    System.out.print("Inserisci l'username del database the_knife_db (postgres): ");
    String dbUser       = scanner.nextLine();

    System.out.print("Inserisci la password del database the_knife_db (1234): ");
    String dbPassword   = scanner.nextLine(); 

    String jdbcUrl      = "jdbc:postgresql://" + dbHost + "/the_knife_db"; 

    try 
    {
      System.out.println("\n[!] Tentativo di connessione al database in corso...");
            
      Connection dbConnection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            
      System.out.println("[OK] Connessione al database the_knife_db stabilita con successo!");
    } 
    catch (SQLException e) 
    {
      System.err.println("[ERRORE] Impossibile connettersi al database.");
      System.err.println("Motivo: " + e.getMessage());
      System.exit(1);
    } 
    finally 
    {
      scanner.close();
    }
  }
  
  public static void startServerServices(Connection dbConnection) 
  {
    int port = 7070;
    QueryServer queryServer = new QueryServer();
    ServerConfig config = new ServerConfig.Builder()
      .port(7070)
      .maxThreads(16)
      .backlog(16)
      .build();
    
    SimpleServer server = new SimpleServer(config);
      
    /*server.setHandler(socket -> {
      try {
        while(socket.isConnected()) {
          try {
            String msg = SocketUtils.receive(socket);
            if (msg == null) 
              break;
            server.broadcast(msg);

          } catch (SocketTimeoutException e) {
            if (!server.isRunning()) break;
          }
        }
      } catch (IOException e) {
        System.out.println("Client disconnected: " + socket.getInetAddress());
      } finally {
        try { 
          socket.close(); 
        } catch (IOException ignore) {}
     }
    });*/
    try {
        server.start();
        System.out.println("\n[SERVER] ServerTK in ascolto sulla porta " + port + "...");
        
        ClientHandler handler = new ClientHandler(dbConnection, queryServer);
        new Thread(handler).start();
    } catch (IOException e) {
      System.err.println("[ERRORE SERVER] Eccezione nell'avvio del server: " + e.getMessage());
    }
    
    /*try (ServerSocket serverSocket = new ServerSocket(port)) 
    {
      System.out.println("\n[SERVER] ServerTK in ascolto sulla porta " + port + "...");

      while (true) 
      {
        Socket clientSocket = serverSocket.accept();
        System.out.println("[SERVER] Nuovo client connesso da: " + clientSocket.getInetAddress());

        ClientHandler handler = new ClientHandler(clientSocket, dbConnection);
        new Thread(handler).start();
      }

    } 
    catch(IOException e) 
    {
      System.err.println("[ERRORE SERVER] Eccezione nell'avvio del server: " + e.getMessage());
    }*/
  }
  
  //<editor-fold defaultstate="collapsed" desc="Exclusive Programmer Methods">   
    /**
     * @param dbConnection
     * @throws Exception 
     * Method to calculate all restaurant rating average.
     */
  private static void calculateRatingAverage(Connection dbConnection) throws Exception
  {
    ArrayList<Double>     averageReview   = new ArrayList<>();
    ArrayList<Integer>    restaurantsID   = new ArrayList<>();  
      
    String sql = "SELECT restaurantID, rating FROM Review ORDER BY restaurantID";
       
    try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
    {  
      try (ResultSet rs = pstmt.executeQuery()) 
      {
        if(rs.next())
        {
          int     temp  = rs.getInt("restaurantID");
          int     count = 1;
          double  somma = rs.getDouble("rating");
            
          while (rs.next()) 
          {
            if(temp != rs.getInt("restaurantID"))
            {               
              averageReview.add(Math.round((somma/count)*100.0)/100.0);
              restaurantsID.add(temp);
              somma = 0;
              count = 0;
              temp = rs.getInt("restaurantID");
            }
            somma += rs.getDouble("rating");
            count += 1;
          }
        }
      }
    }
    catch (SQLException e) 
    {
      System.err.println("[ERRORE] Impossibile connettersi al database.");
      System.err.println("Motivo: " + e.getMessage());
      System.exit(1);
    } 
      
    sql = "UPDATE restaurant SET rating = ? WHERE id = ?";

    try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
    {
      dbConnection.setAutoCommit(false);
      for (int i = 0; i < restaurantsID.size(); i++) 
      {
        pstmt     .setDouble      (1, averageReview.get(i)); 
        pstmt     .setInt         (2, restaurantsID.get(i));          
        pstmt     .addBatch       ();
      }
      pstmt       .executeBatch   ();
      dbConnection.commit         ();
    } 
    catch (SQLException e) 
    {
      try 
      {
        if (dbConnection != null) 
          dbConnection.rollback();
      } 
      catch (SQLException ex) 
      {
        ex.printStackTrace();
      }
      e.printStackTrace();
    } 
    finally 
    {
      try 
      {
        if (dbConnection != null) 
          dbConnection.setAutoCommit(true);
      } 
      catch (SQLException ex) 
      {
        ex.printStackTrace();
      }
    }  
  }
  //</editor-fold>
}