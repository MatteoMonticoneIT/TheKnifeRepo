package theknife.obj.lists;

import java.util.LinkedList;
import java.util.List;
import theknife.obj.restaurant.Restaurant;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public final class ListFavorite {
    private List<Restaurant> list;

    public ListFavorite() {
        this.setList(new LinkedList<>());
    }

    public final List<Restaurant> getList() {
        return list;
    }

    public final void setList(List<Restaurant> list) {
        this.list = list;
    }
    
}
