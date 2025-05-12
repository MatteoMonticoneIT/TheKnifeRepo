package theknife;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import theknife.gui.AdvancedSearch;
import theknife.gui.Home;
import theknife.gui.Login;
import theknife.gui.LoginRestaurateur;
import theknife.gui.Page;
import theknife.gui.PanelMain;
import theknife.gui.Register;
import theknife.gui.RegisterRestaurateur;
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

public class Controller 
{
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
    
    public              Controller          (PanelMain pnl_main)
    {
        //generazione delle liste
        initGUI(pnl_main);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the Graphic User Interface (GUI) of TheKnife application.<br>
     * This method creates the main panel, home page, and login page, and adds them to the main panel.<br>
     * It also sets up the content pane and displays the home page initially.
     */
    private void        initGUI             (PanelMain pnl_main) 
    {
      this.pnl_main = pnl_main;
      initFields    ();
      initTheKnife  ();
    }
    
    /**
     * Initializes all the fields in the knife.
     */
    private void        initFields          () 
    {
      home                 = new Home                (this);
      login                = new Login               (this);
      loginRestaurateur    = new LoginRestaurateur   (this);
      register             = new Register            (this);
      registerRestaurateur = new RegisterRestaurateur(this);
      advancedSearch       = new AdvancedSearch      (this);
    }
    
    /**
     * Initializes the layout and appearance of the main page.
     */
    private void        initTheKnife        () 
    {        
      pnl_main.getPanel().add(home,                 Page.HOME);
      pnl_main.getPanel().add(login,                Page.LOGIN);
      pnl_main.getPanel().add(loginRestaurateur,    Page.LOGIN_RESTAURATEUR);
      pnl_main.getPanel().add(register,             Page.REGISTER);
      pnl_main.getPanel().add(registerRestaurateur, Page.REGISTER_RESTAURATEUR);
      pnl_main.getPanel().add(advancedSearch,       Page.ADVANCED_SEARCH);
    }
    //</editor-fold>
    
    
    public  void        LoginClient         (String user, String password)
    {
      //controllo user e password
        
    }
    
    public  void        LoginRestaurateur   ()
    {
        
    }
    
    public  void        RegisterClient      ()
    {
        
    }
    
    public  void        RegisterRestaurateur()
    {
        
    }
    
    public  void        addRestaurant       ()
    {
      //funzione di aggiunta ristorante
    }
    
    public  void        searchRestaurant    ()
    {
        
    }
    
    public  void        advancedSearch      ()
    {
        
    }
    
    public  PanelMain   getPanelMain        ()
    {
      return this.pnl_main;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Components">
    private PanelMain            pnl_main;
    private Home                 home;
    private Login                login;
    private LoginRestaurateur    loginRestaurateur;
    private Register             register;
    private RegisterRestaurateur registerRestaurateur;
    private AdvancedSearch       advancedSearch;
    //</editor-fold>
}
