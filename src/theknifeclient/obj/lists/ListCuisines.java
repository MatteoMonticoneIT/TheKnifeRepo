package theknife.obj.lists;

import java.util.LinkedList;
import java.util.List;

/**
 * A final class that represents a collection of cuisines.
 * <p>
 * Provides methods to access and modify the list of cuisines.<br>
 * The list can be initialized either as empty or with an existing list.
 * </p>
 * <p>
 * This class extends {@link AbstractListWrapper}, inheriting its methods to manage the list of cuisines.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       760743 (CO)
 */

public class ListCuisines extends AbstractListWrapper<String> 
{
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor.
     * <p>
     * Initializes the list as a new empty {@link LinkedList}.
     * </p>
     */
    public ListCuisines ()                      {super(new LinkedList<String>());}
    
    /**
     * Constructor that initializes the list with an existing list of cuisines.
     *
     * @param list the list of cuisines stored
     */
    public ListCuisines (List<String> list)    {super(list);}
    //</editor-fold>
}
