package theknife.obj.user;

import java.time.LocalDate;
import theknife.obj.lists.ListFavorite;

/**
 * Represents a client user in the system. A client is a type of {@link User} that has a list of favorite restaurants.
 * <p>
 * This class extends the {@link User} class and adds a list of favorite restaurants, allowing the client to store and manage their preferences.
 * </p>
 * 
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public final class Client extends User {
    
    /**
     * The list of favorite restaurants for the client.
     */
    private ListFavorite listFavorite;

    /**
     * Default constructor.
     * <p>
     * Initializes a new client with no list of favorites, using default values for other user attributes.
     * </p>
     */
    public Client() {
    }

    /**
     * Constructor that initializes a client with the specified attributes.
     * <p>
     * This constructor initializes the client's name, surname, username, password, birthday, domicile and favorite restaurants list.
     * </p>
     *
     * @param listFavorite the list of favorite restaurants for the client
     * @param name the client's name
     * @param surname the client's surname
     * @param username the client's username
     * @param password the client's password
     * @param birthday the client's birthday
     * @param domicile the client's domicile
     */
    public Client(ListFavorite listFavorite, String name, String surname, String username, String password, LocalDate birthday, String domicile) {
        super(name, surname, username, password, birthday, domicile);
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
