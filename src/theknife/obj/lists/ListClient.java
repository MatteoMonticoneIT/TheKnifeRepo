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

public final class ListClient {
    private List<Client> list;

    public ListClient() {
        this.setList(new LinkedList<>());
    }

    public final List<Client> getList() {
        return list;
    }

    public final void setList(List<Client> list) {
        this.list = list;
    }
    
    public boolean checkUser(String username, String password){
        //controllo se utente é gia registrato
        for(Client user : list){
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
                return true;
        }
        return false;
    }
}
