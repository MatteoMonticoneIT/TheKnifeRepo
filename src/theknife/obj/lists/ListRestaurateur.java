package theknife.obj.lists;

import java.util.LinkedList;
import java.util.List;
import theknife.obj.user.Restaurateur;

/**
 * A final class that represents a collection of {@link Restaurateur} objects.
 * <p>
 * Provides methods to access and modify the list of restaurateurs.<br>
 * The list can be initialized either as empty or with an existing list.
 * </p>
 * <p>
 * This class extends {@link AbstractListWrapper}, inheriting its methods to manage the list of {@link Restaurateur} objects.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public final class ListRestaurateur extends AbstractListWrapper<Restaurateur> 
{
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor.
     * <p>
     * Initializes the list as a new empty {@link LinkedList}.
     * </p>
     */
    public ListRestaurateur ()                          {super(new LinkedList<Restaurateur>());}
    
    /**
     * Constructor that initializes the list with an existing list of {@link Restaurateur} objects.
     *
     * @param list the list of restaurateurs to be used
     */
    public ListRestaurateur (List<Restaurateur> list)   {super(list); }
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Checks if a restaurateur exists in the list by comparing the username and password.
     * <p>
     * This method iterates through the list of restaurateur and checks if there is a restaurateur with the specified username and password.
     * </p>
     *
     * @param username the username of the restaurateur to check
     * @param password the password of the restaurateur to check
     * @return {@code true} if a restaurateur with the given username and password is found,
     *         {@code false} otherwise
     */
    public Restaurateur checkUser(String username, String password)
    {
      for(Restaurateur user : super.getList())
        if(user.getUsername().equals(username) && user.getPassword().equals(password))
          return user;
        
      return null;
    }
 
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Checks if a restaurateur exists in the list by comparing the email and password.
     * <p>
     * This method iterates through the list of restaurateur and checks if there is a restaurateur with the specified email and password.
     * </p>
     *
     * @param email the username of the restaurateur to check
     * @param password the password of the restaurateur to check
     * @return {@code true} if a restaurateur with the given email and password is found,
     *         {@code false} otherwise
     */
    public Restaurateur checkUserByEmail(String email, String password)
    {
      for(Restaurateur user : super.getList())
        if(user.getEmail().equals(email) && user.getPassword().equals(password))
          return user;
        
      return null;
    }
    
    /**
     * Checks if a restaurateur exists in the list by comparing the username.
     * <p>
     * This method iterates through the list of restaurateur and checks if there is a restaurateur with the specified username.
     * </p>
     *
     * @param username the username of the restaurateur to check
     * @return {@code true} if a restaurateur with the given username is found,
     *         {@code false} otherwise
     */
    public boolean existUser(String username)
    {
      for(Restaurateur user : super.getList())
        if(user.getUsername().equals(username))
          return true;
        
      return false;
    }
    //</editor-fold>
}
