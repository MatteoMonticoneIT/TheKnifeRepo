package theknife.obj.lists;

import java.util.List;

/**
 * Abstract generic class that wraps a list of elements of type {@code T}.
 * <p>
 * This class provides basic methods to get and set the list of elements.<br>
 * The list can be initialized either as an empty list or with an existing list.
 * </p>
 * <p>
 * Subclasses should specify the type of elements they store by passing the appropriate type parameter {@code T}.
 * </p>
 * 
 * @param <T> the type of elements in the list
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public abstract class AbstractListWrapper<T> {
    
    /**
     * The list containing elements of type {@code T}.
     */
    private List<T> list;
    
    /**
     * Default constructor
     */
    public AbstractListWrapper() {
        
    }
    
    /**
     * Constructs a new instance of {@link AbstractListWrapper} with the specified list.
     * 
     * @param list the list of elements to be used
     */
    protected AbstractListWrapper(List<T> list) {
        this.setList(list);
    }

    /**
     * Returns the list of elements.
     * 
     * @return the list containing elements of type {@code T}
     */
    public final List<T> getList() {
        return list;
    }

    /**
     * Sets the list of elements.
     * 
     * @param list the new list of elements to assign
     */
    public final void setList(List<T> list) {
        this.list = list;
    }
    
}
