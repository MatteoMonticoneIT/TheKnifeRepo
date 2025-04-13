package theknife.obj.lists;

import java.util.LinkedList;
import java.util.List;
import theknife.obj.user.Restaurateur;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public final class ListRestaurateur {
    private List<Restaurateur> list;

    public ListRestaurateur() {
        this.setList(new LinkedList<>());
    }

    public final List<Restaurateur> getList() {
        return list;
    }

    public final void setList(List<Restaurateur> list) {
        this.list = list;
    }
    
}
