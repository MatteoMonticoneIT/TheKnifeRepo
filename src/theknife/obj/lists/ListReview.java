package theknife.obj.lists;

import java.util.LinkedList;
import java.util.List;
import theknife.obj.review.Review;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public class ListReview {
    private List<Review> list;

    public ListReview() {
        this.setList(new LinkedList<>());
    }

    public List<Review> getList() {
        return list;
    }

    public void setList(List<Review> list) {
        this.list = list;
    }
    
}
