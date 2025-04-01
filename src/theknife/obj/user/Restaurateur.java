package theknife.obj.user;

import java.time.LocalDate;
import theknife.obj.lists.ListRestaurant;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public class Restaurateur extends User {
    private ListRestaurant listRestaurant;

    public Restaurateur() {
    }

    public Restaurateur(ListRestaurant listRestaurant, String name, String surname, String username, String password, LocalDate birthday, String domicile) {
        super(name, surname, username, password, birthday, domicile);
        this.setRole("restaurateur");
        setListRestaurant(listRestaurant);
    }

    public ListRestaurant getListRestaurant() {
        return listRestaurant;
    }

    public void setListRestaurant(ListRestaurant listRestaurant) {
        this.listRestaurant = listRestaurant;
    }
    
}
