package theknife.obj.lists;

import java.util.LinkedList;
import java.util.List;
import theknife.obj.review.Response;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public final class ListResponse {
    private List<Response> list;

    public ListResponse() {
        this.setList(new LinkedList<>());
    }

    public final List<Response> getList() {
        return list;
    }

    public final void setList(List<Response> list) {
        this.list = list;
    }
    
}
