package theknife.obj.lists;

import java.util.LinkedList;
import java.util.List;
import theknife.obj.restaurant.Restaurant;

/**
 * A final class that represents a collection of {@link Restaurant} as IDs, specifically used for storing a list of owned restaurants.
 * <p>
 * Provides methods to access and modify the list of owned restaurants.<br>
 * The list can be initialized either as empty or with an existing list.
 * </p>
 * <p>
 * This class extends {@link AbstractListWrapper}, inheriting its methods to manage the list of {@link Restaurant} IDs.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public final class ListOwned extends AbstractListWrapper<Integer> 
{ 
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor.
     * <p>
     * Initializes the list as a new empty {@link LinkedList}.
     * </p>
     */
    public ListOwned() 
    {
      super(new LinkedList<Integer>());
    }
    
    /**
     * Constructor that initializes the list with an existing list of {@link Restaurant} IDs.
     *
     * @param list the list of restaurants to be used as owned
     */
    public ListOwned(List<Integer> list) 
    {
      super(list);
    }
    //</editor-fold>
}
