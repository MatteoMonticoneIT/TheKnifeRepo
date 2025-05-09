package theknife.obj.user;

import java.time.LocalDate;
import theknife.obj.lists.ListFavorite;

/**
 * Represents a client user in the system. A client is a type of {@link User} that has a list of favorite restaurants.
 * <p>
 * This class extends the {@link User} class and adds a list of favorite restaurants, allowing the client to store and manage their preferences.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public final class Customer extends User {
    
    /**
     * The list of favorite restaurants for the client.
     */
    private ListFavorite listFavorite;

    /**
     * Default constructor.
     * <p>
     * Initializes a new {@code Client} object without any attributes set.
     * </p>
     */
    public Customer() {
    }

    /**
     * Constructor that initializes a {@code Client} object with the specified attributes.
     * <p>
     * This constructor initializes the {@code Client} object's firstName, lastName, username, password, birthDate, address and favorite restaurants list.
     * </p>
     *
     * @param listFavorite the list of favorite restaurants for the client
     * @param firstName the client's firstName
     * @param lastName the client's lastName
     * @param username the client's username
     * @param password the client's password
     * @param birthDate the client's birthDate
     * @param address the client's address
     */
    public Customer(ListFavorite listFavorite, String firstName, String lastName, String username, String password, LocalDate birthDate, String address) {
        super(firstName, lastName, username, password, birthDate, address);
        this.setRole("client");
        this.setListFavorite(listFavorite);
    }

    /**
     * Returns the list of favorite restaurants for the client.
     *
     * @return the list of favorite restaurants
     */
    public final ListFavorite getListFavorite() {
        return listFavorite;
    }

    /**
     * Sets the list of favorite restaurants for the client.
     *
     * @param listFavorite the new list of favorite restaurants
     */
    public final void setListFavorite(ListFavorite listFavorite) {
        this.listFavorite = listFavorite;
    }
    
}
