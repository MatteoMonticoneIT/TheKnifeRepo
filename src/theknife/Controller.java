package theknife;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import theknife.obj.lists.ListCustomer;
import theknife.obj.lists.ListRestaurant;
import theknife.obj.lists.ListRestaurateur;
import theknife.obj.restaurant.Restaurant;
import theknife.obj.user.Customer;
import theknife.obj.user.Restaurateur;

/**
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public class Controller {
    ListCustomer        customers;
    ListRestaurant      restaurants;
    ListRestaurateur    restaurateur;
    
    /*
        Funzioni da implementare:
            - generazione delle liste dal Json (nel costruttore)
                - richiamo della funzione setList
            - login cliente/ristoratore
            - registrazione
            - 
    */
    
    public Controller(){
        //generazione delle liste
        
    }
    
    public void LoginClient(){
        
    }
    
    public void LoginRestaurateur(){
        
    }
    
    public void RegisterClient(){
        
    }
    
    public void RegisterRestaurateur(){
        
    }
    
    public void addRestaurant(){
        //funzione di aggiunta ristorante
    }
    
    public void searchRestaurant(){
        
    }
    
    public void advancedSearch(){
        
    }
}
