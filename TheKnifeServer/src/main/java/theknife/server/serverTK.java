package theknife.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

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
    String dbHost = scanner.nextLine();

    System.out.print("Inserisci l'username del database the_knife_db (postgres): ");
    String dbUser = scanner.nextLine();

    System.out.print("Inserisci la password del database the_knife_db (1234): ");
    String dbPassword = scanner.nextLine(); 

    String jdbcUrl = "jdbc:postgresql://" + dbHost + "/the_knife_db"; 

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
}