package theknife.obj.lists;

import java.util.LinkedList;
import java.util.List;
import theknife.obj.review.Response;

/**
 * A final class that represents a collection of {@link Response} objects.
 * <p>
 * Provides methods to access and modify the list of responses.<br>
 * The list can be initialized either as empty or with an existing list.
 * </p>
 * <p>
 * This class extends {@link AbstractListWrapper}, inheriting its methods to manage the list of {@link Response} objects.
 * </p>
 * 
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public final class ListResponse extends AbstractListWrapper<Response> {
    
    /**
     * Default constructor.
     * <p>
     * Initializes the list as a new empty {@link LinkedList}.
     * </p>
     */
    public ListResponse() {
        super(new LinkedList<Response>());
    }

    /**
     * Constructor that initializes the list with an existing list of {@link Response} objects.
     *
     * @param list the list of responses to be used
     */
    public ListResponse(List<Response> list) {
        super(list);
    }
    
}
