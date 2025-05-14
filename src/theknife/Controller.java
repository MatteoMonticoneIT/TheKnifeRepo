package theknife;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.File;
import java.util.List;
import simple.file.CSV;
import simple.file.CSVFileNotFoundException;
import simple.file.CSVRow;
import simple.file.FileUtils;
import simple.file.JSON;
import simple.logging.LoggerUtils;
import simple.util.StringUtils;
import theknife.gui.AdvancedSearch;
import theknife.gui.Home;
import theknife.gui.Login;
import theknife.gui.LoginRestaurateur;
import theknife.gui.Page;
import theknife.gui.PanelMain;
import theknife.gui.Register;
import theknife.gui.RegisterRestaurateur;
import theknife.obj.AppPaths;
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
@JsonPropertyOrder({
    "restaurants",
    "customers",
    "restaurateurs"
})
public final class Controller {
    
    //<editor-fold defaultstate="collapsed" desc="Consts">
    /**
     * JSON file dataset
     */
    private static final File JSON_DATASET = AppPaths.getDataFile("data", "dataset.json");
    
    /**
     * CSV file dataset
     */
    private static final File CSV_DATASET = AppPaths.getDataFile("data", "dataset.csv");
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The list of {@link Restaurant}.
     */
    @JsonProperty("restaurants")
    private ListRestaurant   restaurants;
    
    /**
     * The list of {@link Customer}.
     */
    @JsonProperty("customers")
    private ListCustomer     customers;
    
    /**
     * The list of {@link Restaurateur}.
     */
    @JsonProperty("restaurateurs")
    private ListRestaurateur restaurateurs;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    /**
     * The main panel for changing pages
     */
    @JsonIgnore
    private PanelMain            pnl_main;
    
    /**
     * The {@link Home} page
     */
    private Home                 home;
    
    /**
     * The {@link Login} page
     */
    private Login                login;
    
    /**
     * The {@link LoginRestaurateur} page
     */
    private LoginRestaurateur    loginRestaurateur;
    
    /**
     * The {@link Register} page
     */
    private Register             register;
    
    /**
     * The {@link RegisterRestaurateur} page
     */
    private RegisterRestaurateur registerRestaurateur;
    
    /**
     * The {@link AdvancedSearch} page
     */
    private AdvancedSearch       advancedSearch;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructior.
     * <p>
     * Initializes the {@code Controller} without any attributes set.
     * </p>
     */
    public              Controller          (){}
    
    /**
     * Creates a new {@code Controller}.
     * <p>
     * This constructor initializes the {@code Controller} object's {@link PanelMain}.
     * </p>
     * @param pnl_main
     */
    public              Controller          (PanelMain pnl_main)
    {
        initLists();
        initGUI  (pnl_main);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the Graphic User Interface (GUI) of TheKnife application.<br>
     * This method creates the main panel, home page, and login page, and adds them to the main panel.<br>
     * It also sets up the content pane and displays the home page initially.
     */
    private void        initGUI             (PanelMain pnl_main) 
    {
        this.pnl_main = pnl_main;
        initFields  ();
        initTheKnife();
    }
    
    private void        initLists           () 
    {
        if (JSON_DATASET.exists()) {
            this.setRestaurants  (JSON.readNodeAsObject(JSON_DATASET, "restaurants",   ListRestaurant.class));
            this.setCustomers    (JSON.readNodeAsObject(JSON_DATASET, "customers",     ListCustomer.class));
            this.setRestaurateurs(JSON.readNodeAsObject(JSON_DATASET, "restaurateurs", ListRestaurateur.class));
        } else if (CSV_DATASET.exists()) {
            FileUtils.create(JSON_DATASET);
            this.setRestaurants(new ListRestaurant());
            List<CSVRow> csvContent = CSV.read(CSV_DATASET);
            Restaurant restaurant;
            int id = 0;
            for (CSVRow row : csvContent) {
                id++;
                restaurant = new Restaurant(
                    id,
                    -1,
                    row.get("Name", String.class),
                    StringUtils.normalize(row.get("Name", String.class)),
                    row.get("Price", String.class).length(),
                    row.get("Price", String.class),
                    row.get("PhoneNumber", String.class),
                    row.get("Url", String.class),
                    row.get("WebsiteUrl", String.class),
                    row.get("Award", String.class), 
                    row.get("GreenStar", Boolean.class), 
                    row.get("FacilitiesAndServices", String.class), 
                    row.get("Description", String.class), 
                    -1,
                    row.get("Location", String.class).split(",")[row.get("Location", String.class).split(",").length - 1], 
                    row.get("Location", String.class).split(",")[0], 
                    row.get("Address", String.class), 
                    row.get("Latitude", Double.class), 
                    row.get("Longitude", Double.class)
                );
                this.getRestaurants().getList().add(restaurant);
            }
            JSON.writeToFile(JSON_DATASET, this);
        } else {
            LoggerUtils.logSevereAndThrow("!!!CRITICAL ERROR!!!", new CSVFileNotFoundException("Unable to get the dataset file!"));
        }
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
    //<editor-fold defaultstate="collapsed" desc="Getter and Setters">
    /**
     * Returns the {@link PanelMain}.
     * 
     * @return the main panel
     */
    @JsonIgnore
    public final PanelMain getPanelMain(){
        return this.pnl_main;
    }

    /**
     * Returns the {@code ListRestaurant} object.
     * 
     * @return the {@code ListRestaurant} object.
     */
    public final ListRestaurant getRestaurants() {
        return restaurants;
    }

    /**
     * Sets the {@code ListRestaurant} object for the controller.
     * 
     * @param restaurants the {@code ListRestaurant} object to set.
     */
    public final void setRestaurants(ListRestaurant restaurants) {
        this.restaurants = restaurants;
    }

    /**
     * Returns the {@code ListCustomer} object.
     * 
     * @return the {@code ListCustomer} object.
     */
    public final ListCustomer getCustomers() {
        return customers;
    }

    /**
     * Sets the {@code ListCustomer} object for the controller.
     * 
     * @param customers the {@code ListCustomer} object to set.
     */
    public final void setCustomers(ListCustomer customers) {
        this.customers = customers;
    }

    /**
     * Returns the {@code ListRestaurateur} object.
     * 
     * @return the {@code ListRestaurateur} object.
     */
    public final ListRestaurateur getRestaurateurs() {
        return restaurateurs;
    }

    /**
     * Sets the {@code ListRestaurateur} object for the controller.
     * 
     * @param restaurateurs the {@code ListRestaurateur} object to set.
     */
    public final void setRestaurateurs(ListRestaurateur restaurateurs) {
        this.restaurateurs = restaurateurs;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public final void        LoginClient         (String user, String password)
    {
        //controllo user e password
    }
    
    public final void        LoginRestaurateur   ()
    {
        
    }
    
    public final void        RegisterClient      ()
    {
        
    }
    
    public final void        RegisterRestaurateur()
    {
        
    }
    
    public final void        addRestaurant       ()
    {
        //funzione di aggiunta ristorante
    }
    
    public final void        searchRestaurant    ()
    {
        
    }
    
    public final void        advancedSearch      ()
    {
        
    }
    //</editor-fold>
}