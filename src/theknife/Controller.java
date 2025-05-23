package theknife;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import simple.file.CSV;
import simple.file.CSVFileNotFoundException;
import simple.file.CSVRow;
import simple.file.FileUtils;
import simple.file.JSON;
import simple.file.JSONFileNotFoundException;
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
import theknife.obj.InputPattern;
import theknife.obj.lists.ListCustomer;
import theknife.obj.lists.ListRestaurant;
import theknife.obj.lists.ListRestaurateur;
import theknife.obj.restaurant.Restaurant;
import theknife.obj.user.Customer;
import theknife.obj.user.Restaurateur;
import theknife.obj.user.User;

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
public final class Controller 
{
    
    //<editor-fold defaultstate="collapsed" desc="Consts">
    /**
     * JSON file restaurants
     */
    private static final File JSON_RESTAURANTS      = AppPaths.getDataFile("data", "restaurants.json");
    
    /**
     * JSON file customers
     */
    private static final File JSON_CUSTOMERS        = AppPaths.getDataFile("data", "customers.json");
    
    /**
     * JSON file restaurateurs
     */
    private static final File JSON_RESTAURATEURS    = AppPaths.getDataFile("data", "restaurateurs.json");
    
    /**
     * CSV file dataset
     */
    private static final File CSV_RESTAURANTS       = AppPaths.getDataFile("data", "restaurants.csv");
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    /**
     * The {@link User} who log in (either Restaurateur or Customer)
     */
    private User             loggedUser;
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
    
    private Map<String,  List<Restaurant>> byCity      = new HashMap<>();
    private Map<Double,  List<Restaurant>> byRating    = new HashMap<>();
    private Map<Integer, List<Restaurant>> byPrice     = new HashMap<>();
    private Map<String,  List<Restaurant>> byCuisine   = new HashMap<>();
    private Map<String,  List<Restaurant>> byServices  = new HashMap<>();
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
      if (JSON_RESTAURANTS.exists())
        this.setRestaurants    (JSON.read(JSON_RESTAURANTS, ListRestaurant.class));
      else if (CSV_RESTAURANTS.exists()) 
      {
        this.setRestaurants(new ListRestaurant());
        List<CSVRow> csvContent = CSV.read(CSV_RESTAURANTS);
        Restaurant restaurant;
        int id = 0;
        for (CSVRow row : csvContent) 
        {
          id++;
          restaurant = new Restaurant
          (
            id,
            -1,
            row.get("Name", String.class),
            StringUtils.normalize(row.get("Name", String.class)),
            row.get("Price", String.class).length(),
            row.get("Price", String.class),
            row.get("PhoneNumber", String.class),
            row.get("Url", String.class),
            row.get("WebsiteUrl", String.class),
            row.get("Award", String.class).contains("Stars") ? row.get("Award", String.class).replace("Stars", "Michelin") : (row.get("Award", String.class).contains("Star") ? row.get("Award", String.class).replace("Star", "Michelin") : (row.get("Award", String.class).contains(" Restaurants") ? row.get("Award", String.class).replace(" Restaurants", "") : row.get("Award", String.class))), 
            row.get("GreenStar", Boolean.class), 
            row.get("Cuisine", String.class),
            row.get("FacilitiesAndServices", String.class), 
            row.get("Description", String.class), 
            0,
            row.get("Location", String.class).split(",")[row.get("Location", String.class).split(",").length - 1].trim(), 
            row.get("Location", String.class).split(",")[0], 
            row.get("Address", String.class),
            row.get("Latitude", Double.class), 
            row.get("Longitude", Double.class)
          );
          this.getRestaurants().getList().add(restaurant);
        }
        FileUtils.create(JSON_RESTAURANTS);
        JSON.writeToFile(JSON_RESTAURANTS, this.getRestaurants());
      } 
      else
        LoggerUtils.logSevereAndThrow("!!!CRITICAL ERROR!!!", new CSVFileNotFoundException("Unable to get the restaurant file!"));
    
      if (JSON_CUSTOMERS.exists())
        this.setCustomers    (JSON.read(JSON_CUSTOMERS,     ListCustomer.class));
      else
        LoggerUtils.logSevereAndThrow("!!!CRITICAL ERROR!!!", new JSONFileNotFoundException("Unable to get the customers file!"));
           
      if(JSON_RESTAURATEURS.exists())
        this.setRestaurateurs(JSON.read(JSON_RESTAURATEURS, ListRestaurateur.class));
      else
        LoggerUtils.logSevereAndThrow("!!!CRITICAL ERROR!!!", new JSONFileNotFoundException("Unable to get the restaurateurs file!"));
      
      for(Customer c : customers.getList())
        c.setRole("customer");
      
      for(Restaurateur r : restaurateurs.getList())
        r.setRole("restaurateur");
      
      for(Restaurant r : restaurants.getList())
      {
        List<Restaurant> lista = byCity.get(r.getCity());
        if(lista == null) 
        {
          lista = new ArrayList<>();
          byCity.put(r.getCity(), lista);
        }
        lista.add(r);
      }
     
      for(Restaurant r : restaurants.getList())
      {
        List<Restaurant> lista = byRating.get(r.getRating());
        if(lista == null) 
        {
          lista = new ArrayList<>();
          byRating.put(r.getRating(), lista);
        }
        lista.add(r);
      }
      
      for(Restaurant r : restaurants.getList())
      {
        List<Restaurant> lista = byPrice.get(r.getPrice());
        if(lista == null) 
        {
          lista = new ArrayList<>();
          byPrice.put(r.getPrice(), lista);
        }
        lista.add(r);
      }
      
      for(Restaurant r : restaurants.getList())
      {
        List<Restaurant> lista = byCuisine.get(r.getCuisine());
        if(lista == null) 
        {
          lista = new ArrayList<>();
          byCuisine.put(r.getCuisine(), lista);
        }
        lista.add(r);
      }
      
      for(Restaurant r : restaurants.getList())
      {
        List<Restaurant> lista = byServices.get(r.getServicesAvailable());
        if(lista == null) 
        {
          lista = new ArrayList<>();
          byServices.put(r.getServicesAvailable(), lista);
        }
        lista.add(r);
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
        this.getPanelMain().getPanel().add(home,                 Page.HOME);
        this.getPanelMain().getPanel().add(login,                Page.LOGIN);
        this.getPanelMain().getPanel().add(loginRestaurateur,    Page.LOGIN_RESTAURATEUR);
        this.getPanelMain().getPanel().add(register,             Page.REGISTER);
        this.getPanelMain().getPanel().add(registerRestaurateur, Page.REGISTER_RESTAURATEUR);
        this.getPanelMain().getPanel().add(advancedSearch,       Page.ADVANCED_SEARCH);
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
    public final void        saveData() 
    {
      JSON.writeToFile(JSON_RESTAURANTS,    restaurants);
      JSON.writeToFile(JSON_CUSTOMERS,      customers);
      JSON.writeToFile(JSON_RESTAURATEURS,  restaurateurs);
    }
    
    public final void        logout()
    {
      loggedUser = null;   
    }
    
    public final boolean     LoginClient         (String user, String password)
    {
      if(     InputPattern   .match    (InputPattern.USERNAME, user) && 
              InputPattern   .match    (InputPattern.PASSWORD, password))         
        loggedUser = customers         .checkUser        (user, password);
      else if(InputPattern   .match    (InputPattern.EMAIL, user) && 
              InputPattern   .match    (InputPattern.PASSWORD, password)) 
        loggedUser = customers         .checkUserByEmail (user, password);
      
      if(loggedUser!=null)
      {
        home.UILoggedUser("customer");
        return true;
      }
      return false;       
    }
    
    public final boolean     LoginRestaurateur   (String user, String password)
    {
      if(     InputPattern   .match      (InputPattern.USERNAME, user)     &&
              InputPattern   .match      (InputPattern.PASSWORD, password))
        loggedUser = restaurateurs       .checkUser          (user, password); 
      else if(InputPattern   .match      (InputPattern.EMAIL, user) && 
              InputPattern   .match      (InputPattern.PASSWORD, password)) 
       loggedUser = restaurateurs        .checkUserByEmail   (user, password); 
      
      if(loggedUser!=null)
      {
        home.UILoggedUser("restaurateur");
        return true;
      }
      return false;
    }
    
    public final boolean     RegisterClient      (Customer customer)
    {
      if(!customers     .existUser  (customer.    getUsername()) && !restaurateurs .existUser   (customer    .getUsername()))
      {
        customer        .setId      (customers.size());
        customers       .add        (customer);
        return true;
      }
      else
        return false;
    }
    
    public final boolean     RegisterRestaurateur(Restaurateur restaurateur)
    {
      if(!customers     .existUser  (restaurateur .getUsername()) && !restaurateurs .existUser  (restaurateur.getUsername()))
      {
        restaurateur    .setId      (restaurateurs.size());
        restaurateurs   .add        (restaurateur);
        return true;
      }
      else
        return false;
    }
    
    public final boolean     addRestaurant       (Restaurant restaurant)
    {
      return true;  
    }
    
    public final void        searchRestaurant    (String restaurant)
    {
      home.list_restaurants_searchRestaurants (restaurant);
    }
    
    public final void        viewUserList        ()
    {
      List<Integer>     restaurantsList;
      Customer          customer;
      Restaurateur      restaurateur;
      
      if(loggedUser instanceof Customer)
      {
        customer            = (Customer)    loggedUser;
        restaurantsList     = customer     .getListFavorite ()      .getList();
      }
      else
      {
        restaurateur        = (Restaurateur)loggedUser;
        restaurantsList     = restaurateur .getListOwned    ()      .getList();
      }
      home.list_restaurants_viewUserList      (restaurantsList, loggedUser.getRole());
    }
    
    public final void        advancedSearch      (Double rating, String city, boolean[] cuisines, boolean[] services)
    {
     /* List<Restaurant> ratingResult   = null;
      List<Restaurant> locationResult = null;
      List<Restaurant> cusisineResult = null;
      List<Restaurant> servicesResult = null;
      
      if(rating!=0)
        ratingResult   = byRating.get(rating);
      
      if(!city.isEmpty())
        locationResult = byCity.get(city);
      
      if(cuisines != null)
      {
        ArrayList<String> selected = new ArrayList();
        for(int i=0; i<cuisines.length; i++)
          if(cuisines[i])
            selected.add();
      }
      
      if(cuisines != null)
      {
        ArrayList<String> selected = new ArrayList();
        for(int i=0; i<cuisines.length; i++)
          if(cuisines[i])
            selected.add();
        
        cusisineResult = byCuisine.get(selected.get(0));
        
        for(int i=1; i<selected.size(); i++)
          if (r.byCuisine().equals(cittaInput)) 
            risultato.add(r);
    

      }
        cusisineResult = byCuisine.get(cuisine);*/
    }
    //</editor-fold>
}