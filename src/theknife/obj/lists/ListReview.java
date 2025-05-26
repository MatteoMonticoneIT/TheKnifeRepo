package theknife.obj.lists;

import java.util.LinkedList;
import java.util.List;
import theknife.obj.review.Review;

/**
 * A final class that represents a collection of {@link Review} objects.
 * <p>
 * Provides methods to access and modify the list of reviews.<br>
 * The list can be initialized either as empty or with an existing list.
 * </p>
 * <p>
 * This class extends {@link AbstractListWrapper}, inheriting its methods to manage the list of {@link Review} objects.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public final class ListReview extends AbstractListWrapper<Review> 
{
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor
     * <p>
     * Initializes the list as a new empty {@link LinkedList}.
     * </p>
     */
    public ListReview   ()                  {super(new LinkedList<Review>());}
    
    /**
     * Constructor that initializes the list with an existing list of {@link Review} objects.
     *
     * @param list the list of reviews to be used
     */
    public ListReview   (List<Review> list) {super(list);}
    //</editor-fold>
}
