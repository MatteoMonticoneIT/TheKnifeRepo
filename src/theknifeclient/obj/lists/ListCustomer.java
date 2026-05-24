package theknife.obj.lists;

import java.util.LinkedList;
import java.util.List;
import simple.crypto.AES;
import simple.logging.LoggerUtils;
import theknife.obj.user.Customer;

/**
 * A final class that represents a collection of {@link Customer} objects.
 * <p>
 * Provides methods to access and modify the list of clients.<br> 
 * The list can be initialized either as empty or with an existing list.
 * </p>
 * <p>
 * This class extends {@link AbstractListWrapper}, inheriting its methods to manage the list of {@link Customer} objects.<br>
 * Additionally, it provides a method to check whether a client exists in the list by matching the username and password.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       760743 (CO)
 */

public final class ListCustomer extends AbstractListWrapper<Customer> 
{
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor.
     * <p>
     * Initializes the list as a new empty {@link LinkedList}.
     * </p>
     */
    public ListCustomer ()                      {super(new LinkedList<Customer>());}
    
    /**
     * Constructor that initializes the list with an existing list of {@link Customer} objects.
     *
     * @param list the list of clients to be used
     */
    public ListCustomer (List<Customer> list)   {super(list);}
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Checks if a client exists in the list by comparing the username and password.
     * <p>
     * This method iterates through the list of clients and checks if there is a client with the specified username and password.
     * </p>
     *
     * @param username the username of the client to check
     * @param password the password of the client to check
     * @param aes the decypher of the password
     * @return {@code true} if a client with the given username and password is found,
     *         {@code false} otherwise
     */
    public Customer checkUser(String username, String password, AES aes)
    {
      for(Customer user : super.getList())
          try 
          {
            String s = aes.decrypt(user.getPassword());
            if(user.getUsername().equals(username) && s.equals(password))
              return user;
          }
          catch (Exception e) 
          {
            LoggerUtils.logSevereAndThrow("!!!CRITICAL ERROR!!!", new Exception("Unable to decrypt password!", e));
          }
      
      return null;
    }
    
    /**
     * Checks if a client exists in the list by comparing the email and password.
     * <p>
     * This method iterates through the list of clients and checks if there is a client with the specified email and password.
     * </p>
     *
     * @param email the email of the client to check
     * @param password the password of the client to check
     * @param aes the decypher of the password
     * @return {@code true} if a client with the given email and password is found,
     *         {@code false} otherwise
     */
    public Customer checkUserByEmail(String email, String password, AES aes)
    {
      for(Customer user : super.getList())
        try
        {
          String s = aes.decrypt(user.getPassword());
          if(user.getEmail().equals(email) && s.equals(password))
            return user;
        }
        catch (Exception e) 
        {
          LoggerUtils.logSevereAndThrow("!!!CRITICAL ERROR!!!", new Exception("Unable to decrypt password!", e));
        }
      
      return null;
    }
    
    /**
     * Checks if a client exists in the list by comparing the username.
     * <p>
     * This method iterates through the list of clients and checks if there is a client with the specified username.
     * </p>
     *
     * @param username the username of the client to check
     * @return {@code true} if a client with the given username is found,
     *         {@code false} otherwise
     */
    public boolean existUser(String username)
    {
      for(Customer user : super.getList())
        if(user.getUsername().equals(username))
          return true;
      
      return false;
    }
    //</editor-fold>
}
