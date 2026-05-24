package theknife.server;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import simple.socket.*;

/**
 * Main server application class.
 * Initializes the database connection.
 * Starts the socket server.
 *
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       760743 (CO)
 */

public class serverTK 
{
  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * Server listening port.
   */
  private final static int port        = 7070;
  
  /**
   * Maximum concurrent thread count.
   */
  private final static int maxThreads  = 16;
  
  /**
   * Maximum queued incoming connections.
   */ 
  private final static int backLog     = 16;
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Application entry point.
   * Prompts for database credentials.
   * Establishes the database connection.
   * @param args Command line arguments.
   */
  public static void main(String[] args) 
  {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=====================================\n" +
                       "   Avvio TheKnife Server (serverTK)  \n" +
                       "=====================================\n");

    System.out.print("Inserisci l'host del DB: ");
    String dbHost       = scanner.nextLine();

    System.out.print("Inserisci l'username del database the_knife_db: ");
    String dbUser       = scanner.nextLine();

    System.out.print("Inserisci la password del database the_knife_db: ");
    String dbPassword   = scanner.nextLine(); 

    String jdbcUrl      = "jdbc:postgresql://" + dbHost + "/the_knife_db"; 

    try 
    {
      System.out.println("\n[!] Tentativo di connessione al database in corso...");
            
      Connection dbConnection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            
      System.out.println("[OK] Connessione al database the_knife_db stabilita con successo!");
      
      startServerServices(dbConnection);
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
  
  /**
   * Configures and starts the server.
   * Binds the connection handler.
   * * @param dbConnection The active database connection.
   */
  public static void startServerServices(Connection dbConnection) 
  {
    try 
    {
      ServerConfig config = new ServerConfig.Builder()
        .port       (port)
        .maxThreads (maxThreads)
        .backlog    (backLog)
        .build      ();
                
      ConnectionHandler handler = (Socket client) -> 
      {
        ClientHandler ch = new ClientHandler (client, dbConnection);
        ch.run();
      };

      SimpleServer server = new SimpleServer(config, handler);
      server.start();            
    } 
    catch (Exception e) 
    {
      e.printStackTrace();
    }
  }
  //</editor-fold>
}
