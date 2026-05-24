package theknife.obj.lists;

import java.util.ArrayList;
import java.util.List;
import theknife.obj.restaurant.Restaurant;

/**
 * A final class that represents a collection of {@link Restaurant} objects.
 * <p>
 * Provides methods to access and modify the list of restaurants.<br>
 * The list can be initialized either as empty or with an existing list.
 * </p>
 * <p>
 * This class extends {@link AbstractListWrapper}, inheriting its methods to manage the list of {@link Restaurant} objects.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       760743 (CO)
 */

public final class ListRestaurant extends AbstractListWrapper<Restaurant> 
{
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor.
     * <p>
     * Initializes the list as a new empty {@link ArrayList}.
     * </p>
     */
    public ListRestaurant   ()                      {super(new ArrayList<Restaurant>());}
    
    /**
     * Constructor that initializes the list with an existing list of {@link Restaurant} objects.
     *
     * @param list the list of restaurants to be used
     */
    public ListRestaurant   (List<Restaurant> list) {this.setList(list);}
    //</editor-fold>
}
