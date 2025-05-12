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

public final class ListRestaurateur extends AbstractListWrapper<Restaurateur> {
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor.
     * <p>
     * Initializes the list as a new empty {@link LinkedList}.
     * </p>
     */
    public ListRestaurateur() {
        super(new LinkedList<Restaurateur>());
    }
    
    /**
     * Constructor that initializes the list with an existing list of {@link Restaurateur} objects.
     *
     * @param list the list of restaurateurs to be used
     */
    public ListRestaurateur(List<Restaurateur> list) {
        super(list);
    }
    //</editor-fold>
}
