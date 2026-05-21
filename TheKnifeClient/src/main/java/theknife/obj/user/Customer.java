package theknife.obj.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import theknife.obj.lists.ListFavorite;

/**
 * Represents a customer user in the system. A customer is a type of {@link User} that has a list of favorite restaurants.
 * <p>
 * This class extends the {@link User} class and adds a list of favorite restaurants, allowing the customer to store and manage their preferences.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       760743 (CO)
 */
@JsonPropertyOrder(
{
    "ID", 
    "firstName", 
    "firstNameNormalized",
    "lastName", 
    "lastNameNormalized",
    "birthDate", 
    "address", 
    "username", 
    "email", 
    "password", 
    "favorites"
})
public final class Customer extends User
{  
    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The list of favorite restaurants for the customer.
     */
    @JsonProperty("favorites")
    private ListFavorite listFavorite = null;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor.
     * <p>
     * Initializes a new {@code Client} object without any attributes set.
     * </p>
     */
    public Customer() {}
    
    /**
     * Constructor that initializes a {@code Customer} object with the specified attributes.
     * <p>
     * This constructor initializes the {@code Customer} object's username and password.
     * </p>
     *
     * @param username the customer's username
     * @param password the customer's password
     */
    public Customer(String username, String password) 
    {
      super(username, password);
    }
    
    /**
     * Constructor that initializes a {@code Client} object with the specified attributes.
     * <p>
     * This constructor initializes the {@code Client} object's firstName, lastName, username, password, birthDate, address and favorite restaurants list.
     * </p>
     *
     * @param id the customer's id
     * @param firstName the customer's firstName
     * @param lastName the customer's lastName
     * @param birthDate the customer's birthDate
     * @param address the customer's address
     * @param username the customer's username
     * @param email the customer's email
     * @param password the customer's password
     * @param listFavorite the list of favorite restaurants for the customer
     */
    
    public Customer(int id, ListFavorite listFavorite, String firstName, String lastName, String birthDate, String address, String username, String email, String password) 
    {
      this                    (firstName, lastName, birthDate, address, username, email, password);
      this.setListFavorite    (listFavorite);
      this.setId              (id);
    }
    
    /**
     * Constructor that initializes a {@code Client} object with the specified attributes.
     * <p>
     * This constructor initializes the {@code Client} object's firstName, lastName, username, password, birthDate, address and favorite restaurants list.
     * </p>
     *
     * @param firstName the customer's firstName
     * @param lastName the customer's lastName
     * @param birthDate the customer's birthDate
     * @param address the customer's address
     * @param username the customer's username
     * @param email the customer's email
     * @param password the customer's password
     */
    public Customer(String firstName, String lastName, String birthDate, String address, String username, String email, String password) 
    {
      super           (firstName, lastName, birthDate, address, username, email, password);
      this.setRole    ("customer");
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Returns the list of favorite restaurants for the customer.
     *
     * @return the list of favorite restaurants
     */
    public final ListFavorite   getListFavorite ()                          {return listFavorite;}
    
    /**
     * Sets the list of favorite restaurants for the customer.
     *
     * @param listFavorite the new list of favorite restaurants
     */
    public final void           setListFavorite (ListFavorite listFavorite) {this.listFavorite = listFavorite;}
    //</editor-fold>
    
}
