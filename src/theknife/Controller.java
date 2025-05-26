package theknife;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import simple.crypto.AES;
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
//import theknife.obj.lists.ListResponse;
import theknife.obj.lists.ListRestaurant;
import theknife.obj.lists.ListRestaurateur;
//import theknife.obj.lists.ListReview;
import theknife.obj.restaurant.Restaurant;
import theknife.obj.review.Review;
import theknife.obj.user.Customer;
import theknife.obj.user.Restaurateur;
import theknife.obj.user.User;

/**
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
@JsonPropertyOrder(
{
  "restaurants",
  "customers",
  "restaurateurs",
  "reviews",
  "responses"
})
public final class Controller 
{   
    //<editor-fold defaultstate="collapsed" desc="Consts">
    /**
     * JSON file restaurants.
     */
    private static final File JSON_RESTAURANTS      = AppPaths.getOptionalFile("data", "restaurants.json");
    
    /**
     * JSON file customers.
     */
    private static final File JSON_CUSTOMERS        = AppPaths.getRequiredFile("data", "customers.json");
    
    /**
     * JSON file restaurateurs.
     */
    private static final File JSON_RESTAURATEURS    = AppPaths.getRequiredFile("data", "restaurateurs.json");
    
    /**
     * CSV file dataset.
     */
    private static final File CSV_RESTAURANTS       = AppPaths.getRequiredFile("data", "restaurants.csv");
    
    /**
     * CSV file program dataset.
     */
    private static final File PROGRAM_DATASET       = AppPaths.getOptionalFile("data", "program_dataset.csv");
    
    /**
     * KeyStore file for encryption.
     */
    private static final File KEYSTORE_FILE         = AppPaths.getRequiredFile("data", "keystore.jks");
    
    /**
     * Cuisines list.
     */
    private final String[] cuisines = //CSV.read(PROGRAM_DATASET, "CUISINES").toArray(new String[0]);
    {
        "Afghan",
        "African",
        "Alpine",
        "Alsatian",
        "American",
        "American Contemporary",
        "Andalusian",
        "Apulian",
        "Argentinian",
        "Armenian",
        "Asian",
        "Asian Contemporary",
        "Asian Influences",
        "Asian and Western",
        "Asturian",
        "Australian Contemporary",
        "Austrian",
        "Bakery",
        "Balinese",
        "Balkan",
        "Barbecue",
        "Basque",
        "Bavarian",
        "Beef",
        "Beijing Cuisine",
        "Belgian",
        "Brazilian",
        "Breton",
        "British Contemporary",
        "Bulgogi",
        "Burgundian",
        "Burmese",
        "Cajun",
        "Calabrian",
        "Californian",
        "Cambodian",
        "Campanian",
        "Cantonese",
        "Cantonese Roast Meats",
        "Caribbean",
        "Castilian",
        "Catalan",
        "Central Asian",
        "Chao Zhou",
        "Cheese",
        "Chicken Specialities",
        "Chinese",
        "Chinese Contemporary",
        "Chiu Chow",
        "Chueotang",
        "Classic Cuisine",
        "Classic French",
        "Colombian",
        "Congee",
        "Contemporary",
        "Corsican",
        "Country cooking",
        "Crab Specialities",
        "Creative",
        "Creative British",
        "Creative French",
        "Creole",
        "Croatian",
        "Cuban",
        "Cuisine from Abruzzo",
        "Cuisine from Basilicata",
        "Cuisine from Franche-Comté",
        "Cuisine from Lazio",
        "Cuisine from Romagna",
        "Cuisine from South West France",
        "Cuisine from Valtellina",
        "Cuisine from the Aosta Valley",
        "Cuisine from the Marches",
        "Curry",
        "Czech",
        "Danish",
        "Deli",
        "Dim Sum",
        "Doganitang",
        "Dongbei",
        "Dubu",
        "Duck Specialities",
        "Dumplings",
        "Dwaeji-gukbap",
        "Eastern European",
        "Egyptian",
        "Emilian",
        "Emirati Cuisine",
        "English",
        "Ethiopian",
        "European",
        "European Contemporary",
        "Farm to table",
        "Filipino",
        "Finnish",
        "Fish and Chips",
        "Flemish",
        "Fondue and Raclette",
        "French",
        "French Contemporary",
        "Friulian",
        "Fugu / Pufferfish",
        "Fujian",
        "Fusion",
        "Galician",
        "Gastropub",
        "Gejang",
        "German",
        "Gomtang",
        "Greek",
        "Grills",
        "Hainanese",
        "Hakkanese",
        "Hang Zhou",
        "Home Cooking",
        "Hotpot",
        "Huaiyang",
        "Hubei",
        "Hui Cuisine",
        "Hunanese",
        "Hunanese and Sichuan",
        "Hungarian",
        "Indian",
        "Indian Vegetarian",
        "Indonesian",
        "Innovative",
        "International",
        "Irish",
        "Isan",
        "Israeli",
        "Italian",
        "Italian Contemporary",
        "Italian and Japanese",
        "Italian-American",
        "Izakaya",
        "Jamaican",
        "Japan",
        "Japanese",
        "Japanese Contemporary",
        "Japanese Steakhouse",
        "Jiangzhe",
        "Jokbal",
        "Kalguksu",
        "Korean",
        "Korean Contemporary",
        "Kushiage",
        "Kyoto",
        "Lamb Specialities",
        "Lao",
        "Latin American",
        "Lebanese",
        "Ligurian",
        "Lombardian",
        "Lyonnaise",
        "Macanese",
        "Malaysian",
        "Mandu",
        "Mantuan",
        "Meats and Grills",
        "Meats and Seafood",
        "Mediterranean Cuisine",
        "Memil-guksu",
        "Mexican",
        "Middle Eastern",
        "Milanese",
        "Modern British",
        "Modern Cuisine",
        "Modern French",
        "Moroccan",
        "Naengmyeon",
        "Nakagyo-ku",
        "Nepali",
        "Ningbo",
        "Noodles",
        "Noodles and Congee",
        "North African",
        "North American",
        "Northern Thai",
        "Norwegian",
        "Obanzai",
        "Oden",
        "Okonomiyaki",
        "Onigiri",
        "Organic",
        "Oyster Specialities",
        "Pakistani",
        "Peranakan",
        "Persian",
        "Peruvian",
        "Piedmontese",
        "Pizza",
        "Polish",
        "Pork",
        "Portuguese",
        "Provençal",
        "Puerto Rican",
        "Ramen",
        "Regional Cuisine",
        "Regional European",
        "Rice Dishes",
        "Roman",
        "Russian",
        "Sardinian",
        "Savoyard",
        "Scandinavian",
        "Scottish",
        "Seafood",
        "Seasonal Cuisine",
        "Seolleongtang",
        "Shaanxi",
        "Shabu-shabu",
        "Shandong",
        "Shanghainese",
        "Sharing",
        "Shellfish Specialities",
        "Shojin",
        "Shun Tak",
        "Sichuan",
        "Sicilian",
        "Singaporean",
        "Singaporean and Malaysian",
        "Small eats",
        "Smørrebrød",
        "Soba",
        "South African",
        "South American",
        "South East Asian",
        "South Indian",
        "South Tyrolean",
        "Southern",
        "Southern Thai",
        "Spanish",
        "Spanish Contemporary",
        "Sri Lankan",
        "Steakhouse",
        "Street Food",
        "Sujebi",
        "Sukiyaki",
        "Sushi",
        "Swabian",
        "Swedish",
        "Swiss",
        "Taiwanese",
        "Taiwanese contemporary",
        "Taizhou",
        "Tempura",
        "Teochew",
        "Teppanyaki",
        "Tex-Mex",
        "Thai",
        "Thai and Vietnamese",
        "Thai contemporary",
        "Thai-Chinese",
        "Tibetan",
        "Tonkatsu",
        "Traditional British",
        "Traditional Cuisine",
        "Turkish",
        "Tuscan",
        "Udon",
        "Umbrian",
        "Unagi / Freshwater Eel",
        "Vegan",
        "Vegetarian",
        "Venetian",
        "Venezuelan",
        "Vietnamese",
        "Vietnamese Contemporary",
        "World Cuisine",
        "Xibei",
        "Xinjiang",
        "Yakitori",
        "Yoshoku",
        "Yukhoe",
        "Yunnanese",
        "Zhejiang"
    };
    
    /**
     * Service list.
     */
    private final String[] services = //CSV.read(PROGRAM_DATASET, "SERVICES").toArray(new String[0]);
    {
        "Air conditioning",
        "Booking essential",
        "Booking essential - dinner",
        "Brunch",
        "Bring your own bottle",
        "Car park",
        "Cash only",
        "Cash only - lunch",
        "Counter dining",
        "Credit cards not accepted",
        "Foreign credit cards not accepted",
        "Garden or park",
        "Great view",
        "Interesting wine list",
        "Notable sake list",
        "Restaurant offering vegetarian menus",
        "Shoes must be removed",
        "Terrace",
        "Valet parking",
        "Wheelchair access"
    };
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The {@link AES} which encrypt/decrypt data.
     */
    private AES              aes;
    
    /**
     * The {@link User} who log in (either Restaurateur or Customer).
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
/*    
    **
     * The list of {@link Review}.
     *
    @JsonProperty("reviews")
    private ListReview reviews;
    
    **
     * The list of {@link Restaurateur}.
     *
    @JsonProperty("responses")
    private ListResponse responses;
*/    
    /**
     * A {@link HashMap} mapping city names to lists of {@link Restaurant} objects.
     */  
    private final Map<String,  List<Restaurant>> byCity             = new HashMap<>();
    
    /**
     * A {@link HashMap} mapping ratings to lists of {@link Restaurant} objects.
     */
    private final Map<Double,  List<Restaurant>> byRating           = new HashMap<>();
    
    /**
     * A {@link HashMap} mapping price ranges to lists of {@link Restaurant} objects.
     */
    private final Map<Integer, List<Restaurant>> byPrice            = new HashMap<>();
    
    /**
     * A {@link HashMap} mapping cuisines to lists of {@link Restaurant} objects.
     */
    private final Map<String,  List<Restaurant>> byCuisineSingle    = new HashMap<>();
    
    /**
     * A {@link HashMap} mapping services to lists of {@link Restaurant} objects.
     */
    private final Map<String,  List<Restaurant>> byServiceSingle    = new HashMap<>();
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    /**
     * The main panel for changing pages
     */
    @JsonIgnore
    /**
     * The {@link PanelMain} container using {@link CardLayout} to display different pages.
     */
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
      aes = new AES(KEYSTORE_FILE);

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
            row.get                      ("Name",                   String  .class),
            StringUtils.normalize(row.get("Name",                   String  .class)),
            row.get                      ("Price",                  String  .class).length(),
            row.get                      ("Price",                  String  .class),
            row.get                      ("PhoneNumber",            String  .class),
            row.get                      ("Url",                    String  .class),
            row.get                      ("WebsiteUrl",             String  .class),
                  
            row.get                      ("Award",                  String  .class).contains("Stars")           ? row.get("Award", String.class).replace("Stars",           "Michelin") : 
           (row.get                      ("Award",                  String  .class).contains("Star")            ? row.get("Award", String.class).replace("Star",            "Michelin") :   
           (row.get                      ("Award",                  String  .class).contains(" Restaurants")    ? row.get("Award", String.class).replace(" Restaurants",    "")         : row.get("Award", String.class))), 
                  
            row.get                      ("GreenStar",              Boolean .class), 
            row.get                      ("Cuisine",                String  .class),
            row.get                      ("FacilitiesAndServices",  String  .class), 
            row.get                      ("Description",            String  .class), 
            0,
            row.get                      ("Location",               String  .class).split(",")[row.get("Location", String.class).split(",").length - 1].trim(), 
            row.get                      ("Location",               String  .class).split(",")[0], 
            row.get                      ("Address",                String  .class),
            row.get                      ("Latitude",               Double  .class), 
            row.get                      ("Longitude",              Double  .class)
          );
          this.getRestaurants().getList().add(restaurant);
        }
        FileUtils.create(JSON_RESTAURANTS);
        JSON.writeToFile(JSON_RESTAURANTS, this.getRestaurants());
      } 
      else
        LoggerUtils.logSevereAndThrow("!!!CRITICAL ERROR!!!", new CSVFileNotFoundException  ("Unable to get the restaurant file!"));
    
      if (JSON_CUSTOMERS    .exists())
        this.setCustomers    (JSON.read(JSON_CUSTOMERS,     ListCustomer.class));
      else
        LoggerUtils.logSevereAndThrow("!!!CRITICAL ERROR!!!", new JSONFileNotFoundException ("Unable to get the customers file!"));
           
      if(JSON_RESTAURATEURS .exists())
        this.setRestaurateurs(JSON.read(JSON_RESTAURATEURS, ListRestaurateur.class));
      else
        LoggerUtils.logSevereAndThrow("!!!CRITICAL ERROR!!!", new JSONFileNotFoundException ("Unable to get the restaurateurs file!"));
      
      for(Customer c        : this.getCustomers()       .getList())
        c.setRole("customer");
      
      for(Restaurateur r    : this.getRestaurateurs()   .getList())
        r.setRole("restaurateur");
      
      createHashMaps            ();     
      //encryptAllPassword      ();
      //calculateRatingAverage  ();    
    }
    
    /**
     * Initializes all the fields in The Knife.
     */
    private void        initFields          () 
    {
      home                 = new Home                (this);
      login                = new Login               (this, Page.LOGIN);
      loginRestaurateur    = new LoginRestaurateur   (this, Page.LOGIN_RESTAURATEUR);
      register             = new Register            (this, Page.REGISTER);
      registerRestaurateur = new RegisterRestaurateur(this, Page.REGISTER_RESTAURATEUR);
      advancedSearch       = new AdvancedSearch      (this);
    }
    
    /**
     * Initializes the layout and adds various pages to the main container using {@link CardLayout}.
     * Each page is associated with a specific {@link Page} constant.
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
    public final PanelMain          getPanelMain        ()                                  {return pnl_main;}

    /**
     * Returns the {@code ListRestaurant} object.
     * 
     * @return the {@code ListRestaurant} object.
     */
    public final ListRestaurant     getRestaurants      ()                                  {return restaurants;}

    /**
     * Returns the {@code ListCustomer} object.
     * 
     * @return the {@code ListCustomer} object.
     */
    public final ListCustomer       getCustomers        ()                                  {return customers;}
      
    /**
     * Returns the {@code ListRestaurateur} object.
     * 
     * @return the {@code ListRestaurateur} object.
     */
    public final ListRestaurateur   getRestaurateurs    ()                                  {return restaurateurs;}
/*    
    **
     * Returns the {@code ListReview} object.
     * 
     * @return the {@code ListReview} object.
     *
    public       ListReview         getReviews          ()                                  {return reviews;}
    
    **
     * Returns the {@code ListResponse} object.
     * 
     * @return the {@code ListResponse} object.
     *
    public       ListResponse       getResponses        ()                                  {return responses;}
*/    
    /**
     * Returns the {@code User} object.
     * 
     * @return the {@code User} object.
     */
    public final User               getLoggedUser       ()                                  {return loggedUser;}
    
    
    /**
     * Sets the {@code ListRestaurant} object for the controller.
     * 
     * @param restaurants the {@code ListRestaurant} object to set.
     */
    public final void               setRestaurants      (ListRestaurant restaurants)        {this.restaurants   = restaurants;}

    /**
     * Sets the {@code ListCustomer} object for the controller.
     * 
     * @param customers the {@code ListCustomer} object to set.
     */
    public final void               setCustomers        (ListCustomer customers)            {this.customers     = customers;}

    /**
     * Sets the {@code ListRestaurateur} object for the controller.
     * 
     * @param restaurateurs the {@code ListRestaurateur} object to set.
     */
    public final void               setRestaurateurs    (ListRestaurateur restaurateurs)    {this.restaurateurs = restaurateurs;}
/*
    **
     * Sets the {@code ListReview} object for the controller.
     * 
     * @param reviews the {@code ListReview} object to set.
     *
    public       void               setReviews          (ListReview reviews)                {this.reviews       = reviews; }

    **
     * Sets the {@code ListResponse} object for the controller.
     * 
     * @param responses the {@code ListResponse} object to set.
     *
    public       void               setResponses        (ListResponse responses)            {this.responses     = responses; }
*/    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Generate all {@link HashMap} needed
     */
    private       void          createHashMaps          ()
    {
      for(Restaurant r : this.getRestaurants().getList())
      {
        List<Restaurant> lista = byCity.get(r.getCity());
        if(lista == null) 
        {
          lista = new ArrayList<>();
          byCity.put(r.getCity(), lista);
        }
        lista.add(r);
      }
     
      for(Restaurant r : this.getRestaurants().getList())
      {
        List<Restaurant> lista = byRating.get(r.getRating());
        if(lista == null) 
        {
          lista = new ArrayList<>();
          byRating.put(r.getRating(), lista);
        }
        lista.add(r);
      }
      
      for(Restaurant r : this.getRestaurants().getList())
      {
        List<Restaurant> lista = byPrice.get(r.getPrice());
        if(lista == null) 
        {
          lista = new ArrayList<>();
          byPrice.put(r.getPrice(), lista);
        }
        lista.add(r);
      }
      
      for (Restaurant r : restaurants.getList()) 
      {
        if (r.getCuisine() == null || r.getCuisine().isEmpty()) continue;

        String[] cuisineArr = r.getCuisine().split(",\\s*");
        for (String cuisine : cuisineArr) {
            byCuisineSingle.computeIfAbsent(cuisine, k -> new ArrayList<>()).add(r);
        }
      }
      
      for (Restaurant r : restaurants.getList()) 
      {
        if (r.getServicesAvailable() == null || r.getServicesAvailable().isEmpty()) 
          continue;

        String[] servicesArr = r.getServicesAvailable().split(",\\s*");
        for (String service : servicesArr)
            byServiceSingle.computeIfAbsent(service, k -> new ArrayList<>()).add(r);
       }        
    }
    
    /**
     * Save Restaurants, Customers and Restaurateurs lists to their jsons
     */
    public  final void          saveData                () 
    {
      JSON.writeToFile(JSON_RESTAURANTS,   this.getRestaurants());
      JSON.writeToFile(JSON_CUSTOMERS,     this.getCustomers());
      JSON.writeToFile(JSON_RESTAURATEURS, this.getRestaurateurs());
    }
    
    /**
     * Method to log in a client 
     * @param user username or email given
     * @param password password given
     * @return true if loggedUser exist
     *         false if it doesn't
     */
    public  final boolean       LoginClient             (String user, String password)
    {
      if(     InputPattern   .match   (InputPattern.USERNAME, user) && 
              InputPattern   .match   (InputPattern.PASSWORD, password))         
        loggedUser = this.getCustomers().checkUser       (user, password, aes);
      else if(InputPattern   .match   (InputPattern.EMAIL, user)    && 
              InputPattern   .match   (InputPattern.PASSWORD, password)) 
        loggedUser = this.getCustomers().checkUserByEmail(user, password, aes);
      
      if(loggedUser!=null)
      {
        home.UILoggedUser("customer");
        return true;
      }
      return false;       
    }
    
    /**
     * Method to log in a restaurateurs
     * @param user username or email given
     * @param password password given
     * @return true if loggedUser exist
     *         false if it doesn't
     */
    public  final boolean       LoginRestaurateur       (String user, String password)
    {
      if(     InputPattern   .match       (InputPattern.USERNAME, user)     &&
              InputPattern   .match       (InputPattern.PASSWORD, password))
        loggedUser = this.getRestaurateurs().checkUser       (user, password, aes); 
      else if(InputPattern   .match       (InputPattern.EMAIL, user)        && 
              InputPattern   .match       (InputPattern.PASSWORD, password)) 
        loggedUser = this.getRestaurateurs().checkUserByEmail(user, password, aes); 
      
      if(loggedUser!=null)
      {
        home.UILoggedUser("restaurateur");
        return true;
      }
      return false;
    }
    
    /**
     * Method to register a client
     * @param customer all information given in the register page
     * @return true if there's any other user (either Customer or Restaurateur) with the same username exist
     *         false if it there isnt't
     */
    public  final boolean       RegisterClient          (Customer customer)
    {
      if(!this.getCustomers().existUser(customer    .getUsername()) && !this.getRestaurateurs().existUser(customer      .getUsername()))
      {
        try 
        {
          customer              .setPassword        (aes.encrypt(customer.getPassword()));
        }
        catch(Exception e) 
        {
          LoggerUtils           .logSevereAndThrow  ("!!!CRITICAL ERROR!!!", new Exception("Unable to encrypt password!", e));
        }
        customer                .setId              (this.getCustomers().size() + 1);
        this.getCustomers()     .add                (customer);
        return true;
      }
      else
        return false;
    }
    
    /**
     * Method to register a client
     * @param restaurateur all information given in the register page
     * @return true if there's any other user (either Customer or Restaurateur) with the same username exist
     *         false if it there isnt't
     */
    public  final boolean       RegisterRestaurateur    (Restaurateur restaurateur)
    {
      if(!this.getCustomers().existUser(restaurateur.getUsername()) && !this.getRestaurateurs().existUser(restaurateur  .getUsername()))
      {
        try 
        {
          restaurateur          .setPassword        (aes.encrypt(restaurateur.getPassword()));
        }
        catch(Exception e) 
        {
          LoggerUtils           .logSevereAndThrow  ("!!!CRITICAL ERROR!!!", new Exception("Unable to encrypt password!", e));
        }
        restaurateur            .setId              (this.getRestaurateurs().size() + 1);
        this.getRestaurateurs() .add                (restaurateur);
        return true;
      }
      else
        return false;
    }
    
    /**
     * Method to log out user from application
     */
    public  final void          logout                  ()
    {
      loggedUser = null;   
      home.removeSideBar();
    }
    
    /**
     * Method to add a new Restaurant as a restaurateur
     * @param restaurant new restaurant given by {@link AddRestaurant}
     */
    public  final void          addRestaurant           (Restaurant restaurant)
    {
      restaurant    .setId      (restaurants.getList().size()+1);
      restaurant    .setOwnerId (loggedUser .getId());
      restaurants   .add        (restaurant);
    }
    
    /**
     * Method to search a restaurant by name
     * @param restaurant partial name of the restaurant
     */
    public  final void          searchRestaurant        (String restaurant)
    {
      home.list_restaurants_searchRestaurants   (restaurant);
    }
    
    /**
     * Method to view user list (either favourite list of Customer or owned list of Restaurateur)
     */
    public  final void          viewUserList            ()
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
    
    /**
     * Searches for restaurants based on city, rating, price, cuisines, and services.
     * Filters are applied sequentially and results are visualized.
     *
     * @param city The city of the restaurant (null for no filter).
     * @param rating The minimum rating (null for no filter).
     * @param price The target price (null for no filter).
     * @param booleanCuisines Array of boolean values for included cuisines (null for no filter).
     * @param booleanServices Array of boolean values for included services (null for no filter).
     * 
     * @see Restaurant
     * @see home.visualizeAdvancedSearchResult
     */
    public  final void          advancedSearch          (String city, Double rating, Integer price, boolean[] booleanCuisines, boolean[] booleanServices)
    { 
      List<Restaurant> result           = new ArrayList<>(restaurants.getList());
      List<String>     selectedCuisines;
      List<String>     selectedServices;
                  
      if (city != null) 
        result.retainAll(byCity.getOrDefault(city, Collections.emptyList()));
      
      if (rating != null)      
        result.removeIf (r -> r.getRating() < rating);
      
      if (price != null)
        result.removeIf (r -> r.getPrice() > price);
      
      if (booleanCuisines != null)
      {
        selectedCuisines = getListCuisines(booleanCuisines);
        Set<Restaurant> filteredByCuisine = new HashSet<>(byCuisineSingle.getOrDefault(selectedCuisines.get(0), Collections.emptyList()));
        for (int i = 1; i < selectedCuisines.size(); i++)
        {
          List<Restaurant> currentList = byCuisineSingle.getOrDefault(selectedCuisines.get(i), Collections.emptyList());
          filteredByCuisine.retainAll(currentList);
        }
        result.retainAll(filteredByCuisine);
      }
      if (booleanServices != null) 
      {      
        selectedServices = getListServices(booleanServices);
        Set<Restaurant> filteredByServices = new HashSet<>(byServiceSingle.getOrDefault(selectedServices.get(0), Collections.emptyList()));
        for (int i = 1; i < selectedServices.size(); i++) 
        {
          List<Restaurant> currentList = byServiceSingle.getOrDefault(selectedServices.get(i), Collections.emptyList());
          filteredByServices.retainAll(currentList);
        }
        result.retainAll(filteredByServices);
      }
      
      home.visualizeAdvancedSearchResult(result);
    }
    
    /**
     * Method to calculate a restaurant rating average after inserting a new review
     * @param restaurant the restaurant which needs to recalculate rating average
     */
    public        void          restaurantRatingAverage (Restaurant restaurant)
    {
      double ratingAverage = 0;
      for(Review review: restaurant.getListReview().getList())
        ratingAverage += review.getRating();
      restaurant.setRating(Math.round((ratingAverage/restaurant.getListReview().getList().size())*100.0)/100.0);  
    }
    
    /**
     * Converts selected cuisine from booleans to string list
     * @param booleanCuisines selected cuisines
     * @return string list of selected cuisines
     */   
    public        List<String>  getListCuisines         (boolean[] booleanCuisines)
    {
      List<String> selectedCuisines = new ArrayList<>();
      
      for(int i=0; i<booleanCuisines.length; i++)
        if(booleanCuisines[i])
          selectedCuisines.add(cuisines[i]);
      
      return selectedCuisines;
    }
    
    /**
     * Converts selected services from booleans to string list
     * @param booleanServices selected services
     * @return string list of selected services
     */   
    public        List<String>  getListServices         (boolean[] booleanServices)
    {
      List<String> selectedServices = new ArrayList<>();
      
      for(int i=0; i<booleanServices.length; i++)
        if(booleanServices[i])
          selectedServices.add(services[i]);
      
      return selectedServices;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Exclusive Programmer Methods">
    /**
     * Method to calculate all password of both Customers and Restaurateurs
     */
    private void encryptAllPassword     ()
    {
      for(Customer customer: this.getCustomers().getList())
        try 
        {
          customer      .setPassword(aes.encrypt(customer.getPassword()));
        }
        catch(Exception e) 
        {
          LoggerUtils   .logSevereAndThrow("!!!CRITICAL ERROR!!!", new Exception("Unable to encrypt password!", e));
        }
      
      for(Restaurateur restaurateur: this.getRestaurateurs().getList())
        try 
        {
          restaurateur  .setPassword(aes.encrypt(restaurateur.getPassword()));
        }
        catch(Exception e) 
        {
          LoggerUtils   .logSevereAndThrow("!!!CRITICAL ERROR!!!", new Exception("Unable to encrypt password!", e));
        }
    }
 
    /**
     * Method to calculate all restaurant rating average 
     */
    private void calculateRatingAverage ()
    {
      double ratingAverage;
      for(Restaurant restaurant: this.getRestaurants().getList())
      {
        ratingAverage = 0;
        for(Review review: restaurant.getListReview().getList())
          ratingAverage += review.getRating();
        restaurant.setRating(Math.round((ratingAverage/restaurant.getListReview().getList().size())*100.0)/100.0);  
      }
    }
    //</editor-fold>
}
