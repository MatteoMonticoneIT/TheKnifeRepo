package theknife.obj.lists;

import java.util.LinkedList;
import java.util.List;
import theknife.obj.user.Client;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public class ListClient {
    private List<Client> list;

    public ListClient() {
        this.setList(new LinkedList<>());
    }

    public List<Client> getList() {
        return list;
    }

    public void setList(List<Client> list) {
        this.list = list;
    }
    
}
