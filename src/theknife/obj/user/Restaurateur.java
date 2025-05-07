package theknife.obj.user;

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
public final class Restaurateur extends User {

    /**
     * The list of restaurants managed by the restaurateur.
     */
    private ListRestaurant listRestaurant;

    /**
     * Default constructor.
     * <p>
     * Initializes a new {@code Restaurateur} object without any attributes set.
     * </p>
     */
    public Restaurateur() {
    }

    /**
     * Constructor that initializes a {@code Restaurateur} object with the specified attributes.
     * <p>
     * This constructor initializes the {@code Restaurateur} object's name, surname, username, password, birthday, domicile and the list of restaurants they manage.
     * </p>
     *
     * @param listRestaurant the list of restaurants managed by the restaurateur
     * @param name the restaurateur's name
     * @param surname the restaurateur's surname
     * @param username the restaurateur's username
     * @param password the restaurateur's password
     * @param birthday the restaurateur's birthday
     * @param domicile the restaurateur's domicile
     */
    public Restaurateur(ListRestaurant listRestaurant, String name, String surname, String username, String password, LocalDate birthday, String domicile) {
        super(name, surname, username, password, birthday, domicile);
        super.setRole("restaurateur"); // Sets the role of the user to "restaurateur"
        this.setListRestaurant(listRestaurant);
    }

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
    
}
