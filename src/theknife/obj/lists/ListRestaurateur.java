/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class ListRestaurateur {
    private List<Restaurateur> list;

    public ListRestaurateur() {
        this.setList(new LinkedList<>());
    }

    public List<Restaurateur> getList() {
        return list;
    }

    public void setList(List<Restaurateur> list) {
        this.list = list;
    }
    
}
