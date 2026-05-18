package theknife.server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import simple.crypto.AES;
import simple.logging.LoggerUtils;
import theknife.obj.AppPaths;

/**
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public class serverTK 
{
  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The {@link AES} which encrypt/decrypt data.
   */
    
  private static       AES  aes;
  
  /**
   * KeyStore file for encryption.
   */
  private static final File KEYSTORE_FILE         = AppPaths.getRequiredFile("data", "keystore.jks");
  
  //</editor-fold>
  
  public static void main(String[] args) 
  {
    aes = new AES(KEYSTORE_FILE);
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
    int port = 8080;

    try (ServerSocket serverSocket = new ServerSocket(port)) 
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
    }
  }
  
  //<editor-fold defaultstate="collapsed" desc="Exclusive Programmer Methods">
    /**    
     * @param dbConnection
     * @throws Exception 
     * Method to encrypt all Customers passwords
     */
    private static void encryptCustomer(Connection dbConnection) throws Exception
    {
      ArrayList<Integer>    IDs         = new ArrayList<>();
      ArrayList<String>     passwords   = new ArrayList<>();
      
      String sql = "SELECT id, password FROM customer";
       
      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
      {  
        // 4. Esecuzione della query e lettura dei risultati
        try (ResultSet rs = pstmt.executeQuery()) 
        {
          // Cicla attraverso tutte le righe restituite
          while (rs.next()) 
          {
            IDs         .add        (rs.getInt      ("id"));
            passwords   .add        (rs.getString   ("password"));           
            passwords   .set        (passwords.size()-1, aes.encrypt(passwords.get(passwords.size()-1)));            
          }
        }
      }
      catch (SQLException e) 
      {
        System.err.println("[ERRORE] Impossibile connettersi al database.");
        System.err.println("Motivo: " + e.getMessage());
        System.exit(1);
      } 
      
      sql = "UPDATE customer SET password = ? WHERE id = ?";

      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
      {
        dbConnection.setAutoCommit(false);
        for (int i = 0; i < IDs.size(); i++) 
        {
          pstmt.setString   (1, passwords.get(i)); 
          pstmt.setInt      (2, IDs.get(i));          
          pstmt.addBatch    ();
        }
        int[] risultati = pstmt.executeBatch();
        dbConnection.commit();
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
    
    /**
     * 
     * @param dbConnection
     * @throws Exception 
     * Method to encrypt all restaurateurs passwords
     */
    private static void encryptRestaurateur(Connection dbConnection) throws Exception
    {
      ArrayList<Integer>    IDs         = new ArrayList<>();
      ArrayList<String>     passwords   = new ArrayList<>();
      
      String sql = "SELECT id, password FROM restaurateur";
       
      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
      {  
        try (ResultSet rs = pstmt.executeQuery()) 
        {
          while (rs.next()) 
          {
            IDs         .add        (rs.getInt      ("id"));
            passwords   .add        (rs.getString   ("password"));           
            passwords   .set        (passwords.size()-1, aes.encrypt(passwords.get(passwords.size()-1)));            
          }
        }
      }
      catch (SQLException e) 
      {
        System.err.println("[ERRORE] Impossibile connettersi al database.");
        System.err.println("Motivo: " + e.getMessage());
        System.exit(1);
      } 
      
      sql = "UPDATE restaurateur SET password = ? WHERE id = ?";

      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
      {
        dbConnection.setAutoCommit(false);
        for (int i = 0; i < IDs.size(); i++) 
        {
          pstmt     .setString      (1, passwords.get(i)); 
          pstmt     .setInt         (2, IDs.get(i));          
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
    
    /**
     * Method to calculate all restaurant rating average.
     */
    private static void calculateRatingAverage(Connection dbConnection) throws Exception
    {
      ArrayList<Double>     averageReview   = new ArrayList<>();
      ArrayList<Integer>    restaurantsID   = new ArrayList<>();  
      
      String sql = "SELECT restaurantID, rating FROM Review ORDER BY restaurantID";
       
      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
      {  
        // 4. Esecuzione della query e lettura dei risultati
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