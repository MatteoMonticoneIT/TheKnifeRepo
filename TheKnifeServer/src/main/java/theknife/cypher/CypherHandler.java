package theknife.cypher;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import simple.crypto.AES;
import theknife.obj.AppPaths;
import theknife.obj.lists.ListFavorite;
import theknife.obj.lists.ListOwned;
import theknife.obj.user.Customer;
import theknife.obj.user.Restaurateur;
import theknife.obj.user.User;

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
  public void      encryptAllCustomer      (Connection dbConnection) throws Exception
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
  public void      encryptAllRestaurateur  (Connection dbConnection) throws Exception
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
  public String    encryptUser             (String password) throws Exception
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
  public Customer   decryptCustomer         (Connection dbConnection, String username, String password) throws Exception
  {  
    String sql = "SELECT c.*, CASE WHEN COUNT(lf.restaurantid) = 0 THEN '' ELSE STRING_AGG(DISTINCT lf.restaurantid::text, ', ') END AS listfavourite FROM customer c LEFT JOIN listfavourite lf ON lf.customerid = c.id WHERE username = ? OR email = ? GROUP BY c.id;";
      System.out.println(username);
    try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
    {  
      pstmt.setString(1, username);
      pstmt.setString(2, username);
      
      try (ResultSet rs = pstmt.executeQuery()) 
      {
        if(rs.next() && password.equals(aes.decrypt(rs.getString ("password")))) {
          ArrayList<Integer> temp = new ArrayList<>();
          String[] list = rs.getString("listfavourite").split("\\,");
          if(!list[0].isBlank())
            for(String s: list)
              temp.add(Integer.parseInt(s));
          ListFavorite ls = new ListFavorite(temp);

          return new Customer(
                  rs.getInt("id"),
                  ls,
                  rs.getString("firstName"),
                  rs.getString("lastName"),
                  rs.getString("birthDate"),
                  rs.getString("address"),
                  rs.getString("username"),
                  rs.getString("email"),
                  rs.getString("password")
          );
        };
      }
    }
    catch (SQLException e) 
    {
      System.err.println("[ERRORE] Impossibile connettersi al database.");
      System.err.println("Motivo: " + e.getMessage());
      System.exit       (1);
    } 
    return null;
  }
  
  /**
   * @param dbConnection
   * @param username
   * @param password
   * @throws Exception 
   * Method to decrypt specified restaurateur password
   */
  public Restaurateur decryptRestaurateur     (Connection dbConnection, String username, String password) throws Exception
  {
    String sql = "SELECT r.*, CASE WHEN COUNT(res.id) = 0 THEN '' ELSE STRING_AGG(DISTINCT res.id::text, ', ') END AS listowned FROM restaurateur r LEFT JOIN restaurant res ON res.ownerid = r.id WHERE username = ? OR email = ? GROUP BY r.id;";
       
    try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
    {  
      pstmt.setString(1, username);
      pstmt.setString(2, username);

      try (ResultSet rs = pstmt.executeQuery()) 
      {
        if(rs.next() && password.equals(aes.decrypt(rs.getString ("password")))) {
          ArrayList<Integer> temp = new ArrayList<>();
          for (String s : (rs.getString("listowned").split("\\,")))
            temp.add(Integer.parseInt(s));
          ListOwned lo = new ListOwned(temp);
          return new Restaurateur(
                  rs.getInt("id"),
                  rs.getString("firstName"),
                  rs.getString("lastName"),
                  rs.getString("birthDate"),
                  rs.getString("address"),
                  rs.getString("username"),
                  rs.getString("email"),
                  rs.getString("password"),
                  lo
                  );
        }
      }
    }
    catch (SQLException e) 
    {
      System.err.println("[ERRORE] Impossibile connettersi al database.");
      System.err.println("Motivo: " + e.getMessage());
      System.exit       (1);
    } 
    return null;
  }
}
