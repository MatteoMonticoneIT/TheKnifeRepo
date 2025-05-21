package theknife.obj.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;
import theknife.obj.lists.ListRestaurant;

/**
 * Represents a restaurateur user in the system. A restaurateur is a type of {@link User} who manages a list of restaurants.
 * <p>
 * This class extends the {@link User} class and adds a list of restaurants, allowing the restaurateur to manage their owned restaurants.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
@JsonPropertyOrder({
    "ID", 
    "firstName", 
    "lastName", 
    "birthDate", 
    "address", 
    "username", 
    "email", 
    "password", 
    "restaurants"
})
public final class Restaurateur extends User {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The list of restaurants managed by the restaurateur.
     */
    @JsonProperty("restaurants")
    private ListRestaurant listRestaurant = null;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor.
     * <p>
     * Initializes a new {@code Restaurateur} object without any attributes set.
     * </p>
     */
    public Restaurateur() {}
    
    /**
     * Constructor that initializes a {@code Restaurateur} object with the specified attributes.
     * <p>
     * This constructor initializes the {@code Restaurateur} object's firstName, lastName, username, password, birthDate, address and the list of restaurants they manage.
     * </p>
     *
     * @param id the restaurateur's id
     * @param firstName the restaurateur's firstName
     * @param lastName the restaurateur's lastName
     * @param birthDate the restaurateur's birthDate
     * @param address the restaurateur's address
     * @param username the restaurateur's username
     * @param email the restaurateur's email
     * @param password the restaurateur's password
     * @param listRestaurant the list of restaurants managed by the restaurateur
     */
    public Restaurateur(int id, String firstName, String lastName, String birthDate, String address, String username, String email, String password, ListRestaurant listRestaurant) {
        this(firstName, lastName, birthDate, address, username, email, password);
        this.setListRestaurant(listRestaurant);
        this.setId(id);
    }
    
    public Restaurateur(String firstName, String lastName, String birthDate, String address, String username, String email, String password) 
    {
        super(firstName, lastName, birthDate, address, username, email, password);
        super.setRole("restaurateur");
    }
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Returns the list of restaurants managed by the restaurateur.
     *
     * @return the list of managed restaurants
     */
    public final ListRestaurant getListRestaurant() {
        return listRestaurant;
    }
    
    /**
     * Sets the list of restaurants managed by the restaurateur.
     *
     * @param listRestaurant the new list of restaurants to be managed
     */
    public final void setListRestaurant(ListRestaurant listRestaurant) {
        this.listRestaurant = listRestaurant;
    }
    //</editor-fold>
}
