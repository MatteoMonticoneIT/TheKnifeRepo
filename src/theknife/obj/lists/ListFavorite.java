package theknife.obj.lists;

import java.util.LinkedList;
import java.util.List;
import theknife.obj.restaurant.Restaurant;

/**
 * A final class that represents a collection of {@link Restaurant} objects, specifically used for storing a list of favorite restaurants.
 * <p>
 * Provides methods to access and modify the list of favorite restaurants.<br>
 * The list can be initialized either as empty or with an existing list.
 * </p>
 * <p>
 * This class extends {@link AbstractListWrapper}, inheriting its methods to manage the list of {@link Restaurant} objects.
 * </p>
 * 
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public final class ListFavorite extends AbstractListWrapper<Restaurant> {
    
    /**
     * Default constructor.
     * <p>
     * Initializes the list as a new empty {@link LinkedList}.
     * </p>
     */
    public ListFavorite() {
        super(new LinkedList<Restaurant>());
    }

    /**
     * Constructor that initializes the list with an existing list of {@link Restaurant} objects.
     *
     * @param list the list of restaurants to be used as favorites
     */
    public ListFavorite(List<Restaurant> list) {
        super(list);
    }
    
}
