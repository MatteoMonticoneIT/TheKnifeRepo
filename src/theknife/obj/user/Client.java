package theknife.obj.user;

import java.util.Date;
import theknife.obj.lists.ListFavorite;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public class Client extends User {
    private ListFavorite listFavorite;

    public Client(ListFavorite listFavorite, String name, String surname, String username, String password, Date birthday, String domicile, String role) {
        super(name, surname, username, password, birthday, domicile, role);
        setListFavorite(listFavorite);
    }

    public ListFavorite getListFavorite() {
        return listFavorite;
    }

    public void setListFavorite(ListFavorite listFavorite) {
        this.listFavorite = listFavorite;
    }
    
}
