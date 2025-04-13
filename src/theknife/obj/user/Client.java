package theknife.obj.user;

import java.time.LocalDate;
import theknife.obj.lists.ListFavorite;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public final class Client extends User {
    private ListFavorite listFavorite;

    public Client() {
    }

    public Client(ListFavorite listFavorite, String name, String surname, String username, String password, LocalDate birthday, String domicile) {
        super(name, surname, username, password, birthday, domicile);
        this.setRole("client");
        this.setListFavorite(listFavorite);
    }

    public final ListFavorite getListFavorite() {
        return listFavorite;
    }

    public final void setListFavorite(ListFavorite listFavorite) {
        this.listFavorite = listFavorite;
    }
    
}
