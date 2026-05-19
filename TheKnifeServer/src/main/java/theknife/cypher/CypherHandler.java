package theknife.cypher;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import simple.crypto.AES;
import theknife.obj.AppPaths;

/**
 * Manages database encryption and decryption
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public class CypherHandler 
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
  
  public CypherHandler()
  {
    aes = new AES(KEYSTORE_FILE);
  }
  
  /**    
   * @param dbConnection
   * @throws Exception 
   * Method to encrypt all Customers passwords
   */
  private void      encryptAllCustomer      (Connection dbConnection) throws Exception
  {
    ArrayList<Integer>    IDs         = new ArrayList<>();
    ArrayList<String>     passwords   = new ArrayList<>();
     
    String sql = "SELECT id, password FROM customer";
       
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
      System.exit       (1);
    } 
      
    sql = "UPDATE customer SET password = ? WHERE id = ?";

    try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
    {
      dbConnection.setAutoCommit(false);
      for (int i=0; i < IDs.size(); i++) 
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
   * @param dbConnection
   * @throws Exception 
   * Method to encrypt all restaurateurs passwords
   */
  private void      encryptAllRestaurateur  (Connection dbConnection) throws Exception
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
      System.exit       (1);
    } 
      
    sql = "UPDATE restaurateur SET password = ? WHERE id = ?";

    try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
    {
      dbConnection.setAutoCommit(false);
      for (int i=0; i<IDs.size(); i++) 
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
   * @param password
   * @throws Exception 
   * Method to encrypt a given password
   */
  private String    encryptUser             (String password) throws Exception
  {
    return aes.encrypt(password);
  }
  
  /**    
   * @param dbConnection
   * @param username
   * @param password
   * @throws Exception 
   * Method to decrypt specified customer password
   */
  private boolean   decryptCustomer         (Connection dbConnection, String username, String password) throws Exception
  {  
    String sql = "SELECT username, password FROM customer WHERE username = ?";
       
    try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
    {  
      pstmt.setString(1, username);
      
      try (ResultSet rs = pstmt.executeQuery()) 
      {
        if(rs.next() && password.equals(aes.decrypt(rs.getString ("password")))) 
          return true;
      }
    }
    catch (SQLException e) 
    {
      System.err.println("[ERRORE] Impossibile connettersi al database.");
      System.err.println("Motivo: " + e.getMessage());
      System.exit       (1);
    } 
    return false;
  }
  
  /**
   * @param dbConnection
   * @param username
   * @param password
   * @throws Exception 
   * Method to decrypt specified restaurateur password
   */
  private boolean   decryptRestaurateur     (Connection dbConnection, String username, String password) throws Exception
  {
    String sql = "SELECT username, password FROM restaurateur WHERE username = ?";
       
    try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
    {  
      pstmt.setString(1, username);
      
      try (ResultSet rs = pstmt.executeQuery()) 
      {
        if(rs.next() && password.equals(aes.decrypt(rs.getString ("password")))) 
          return true;
      }
    }
    catch (SQLException e) 
    {
      System.err.println("[ERRORE] Impossibile connettersi al database.");
      System.err.println("Motivo: " + e.getMessage());
      System.exit       (1);
    } 
    return false;
  }
}
