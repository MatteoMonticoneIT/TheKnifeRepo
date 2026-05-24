package theknife.obj.lists;

import java.io.Serializable;
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
 * @author Mattia Tamburo       760743 (CO)
 */
public abstract class AbstractListWrapper<T> implements Serializable
{  
    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The list containing elements of type {@code T}.
     */
    private List<T> list;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor
     */
    public      AbstractListWrapper ()              {}
    
    /**
     * Constructs a new instance of {@link AbstractListWrapper} with the specified list.
     *
     * @param list the list of elements to be used
     */
    protected   AbstractListWrapper (List<T> list)  {this.setList(list);}
    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Add a new element in the list
     * @param t generic type
     */
    public       void       add     (T t)           {list.add(t);}
    
    /**
     * Remove a given element in the list
     * @param t generic type
     */
    public       void       remove  (T t)           {list.remove(t);}
    
    /**
     * Get size of the list
     * @return Returns size of the list
     */
    public       int        size    ()              {return list.size();}
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Returns the list of elements.
     *
     * @return the list containing elements of type {@code T}
     */
    public final List<T>    getList ()              {return list;}
    
    /**
     * Sets the list of elements.
     *
     * @param list the new list of elements to assign
     */
    public final void       setList (List<T> list)  {this.list = list;}
    //</editor-fold>    
}
