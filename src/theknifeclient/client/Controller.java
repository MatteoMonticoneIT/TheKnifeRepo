package theknife.client;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import theknife.gui.AdvancedSearch;
import theknife.gui.Home;
import theknife.gui.Login;
import theknife.gui.LoginRestaurateur;
import theknife.gui.Page;
import theknife.gui.PanelMain;
import theknife.gui.Register;
import theknife.gui.RegisterRestaurateur;
import theknife.gui.CustomerReviews;
import theknife.obj.InputPattern;
import theknife.obj.lists.ListCuisines;
import theknife.obj.lists.ListCustomer;
import theknife.obj.lists.ListOwned;
import theknife.obj.lists.ListRestaurant;
import theknife.obj.lists.ListRestaurateur;
import theknife.obj.lists.ListReview;
import theknife.obj.lists.ListServices;
import theknife.obj.restaurant.Restaurant;
import theknife.obj.review.Response;
import theknife.obj.review.Review;
import theknife.obj.user.Customer;
import theknife.obj.user.Restaurateur;
import theknife.obj.user.User;

/**
 * This class manages all datasets and data handling in {@code TheKnife} project.
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       760743 (CO)
 */

public final class Controller 
{   
    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The {@link ServerHandler} which handles the communication with Server
     */
    private ServerHandler    serverHandler;
    
    /**
     * The {@link User} who log in (either Restaurateur or Customer).
     */
    private User             loggedUser;
    
    /**
     * The list of {@link Restaurant}.
     */
    private ListRestaurant   restaurants;
    
    /**
     * Cuisines list.
     */
    private ListCuisines cuisines;
    
    /**
     * Service list.
     */
    private ListServices services;
    
    /**
     * The list of {@link Customer}.
     */
    private ListCustomer     customers;
    
    /**
     * The list of {@link Restaurateur}.
     */
    private ListRestaurateur restaurateurs;

    /**
     * connection at {@Link ServerHandler}
     */
    //private ServerHandler serverHandler = new ServerHandler();

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
    
    /**
     * Review selected from the {@link CustomerReviews} page.
     */
    private Review selectedReview;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
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
     * Default constructor.
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
     * @param pnl_main the panel main
     */
    public              Controller          (PanelMain pnl_main) throws IOException, ClassNotFoundException
    {
        serverHandler = new ServerHandler();
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
    
    /**
     * Initializes all lists.
     */
    private void        initLists           () throws IOException, ClassNotFoundException 
    {
      setRestaurants(serverHandler.getRestaurants());
      cuisines = serverHandler.getCuisines();
      services = serverHandler.getServices();
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
    
    /**
     * Sets the {@code Review} selected from the {@link CustomerReviews} page.
     * @param review the review selected
     */
    public final void               setSelectedReview   (Review review)                     {this.selectedReview = review; }
    
    /**
     * Returns the {@link Review} selected from the CustomerReviews page.
     * @return the {@link Review} selected
     */
    public final Review             getSelectedReview   ()                                  {return this.selectedReview;}
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Generate all {@link HashMap} needed.
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
     * Method to log in a client.
     * @param user username or email given
     * @param password password given
     * @return true if loggedUser exist
     *         false if it doesn't
     */
    public  final boolean       LoginClient             (String user, String password) throws IOException, ClassNotFoundException, Exception {
      if(     InputPattern   .match   (InputPattern.USERNAME, user)     || 
              InputPattern   .match   (InputPattern.EMAIL, user)        && 
              InputPattern   .match   (InputPattern.PASSWORD, password))         
        loggedUser = serverHandler.loginCustomer       (new Customer(user, password));
      
      if(loggedUser!=null)
      {
        home.UILoggedUser("customer");
        return true;
      }
      return false;       
    }
    
    /**
     * Method to log in a restaurateurs.
     * @param user username or email given
     * @param password password given
     * @return true if loggedUser exist
     *         false if it doesn't
     */
    public  final boolean       LoginRestaurateur       (String user, String password) throws IOException, ClassNotFoundException, Exception {
      if(     InputPattern   .match   (InputPattern.USERNAME, user)     || 
              InputPattern   .match   (InputPattern.EMAIL, user)        && 
              InputPattern   .match   (InputPattern.PASSWORD, password))  
        loggedUser = serverHandler.loginRestaurateur       (new Restaurateur(user, password));
      
      if(loggedUser!=null)
      {
        home.UILoggedUser("restaurateur");
        return true;
      }
      return false;
    }
    
    /**
     * Method to register a client.
     * @param customer all information given in the register page
     * @return true if there's any other user (either Customer or Restaurateur) with the same username exist
     *         false if it there isn't
     */
    public  final boolean       RegisterClient          (Customer customer) throws IOException, ClassNotFoundException, Exception
    {
      return serverHandler.registerCustomer(customer);
    }
    
    /**
     * Method to register a client
     * @param restaurateur all information given in the register page
     * @return true if there's any other user (either Customer or Restaurateur) with the same username exist
     *         false if it there isn't
     */
    public  final boolean       RegisterRestaurateur    (Restaurateur restaurateur) throws IOException, ClassNotFoundException, Exception
    {
      return serverHandler.registerRestaurateur(restaurateur);
    }
    
    /**
     * Method to log out user from application.
     */
    public  final void          logout                  ()
    {
      loggedUser = null;   
      home.removeSideBar();
    }
    
    /**
     * Method to add a new Restaurant as a restaurateur.
     * @param restaurant new restaurant given by AddRestaurant
     *
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public  final void          addRestaurant           (Restaurant restaurant) throws IOException, ClassNotFoundException
    {
      restaurant    .setOwnerId (loggedUser .getId());
      restaurants   .add        (restaurant);
      serverHandler.addRestaurant(restaurant);
      Restaurateur r = (Restaurateur) loggedUser;
      
      if(r.getListOwned() == null)
        r.setListOwned(new ListOwned());
      r.getListOwned().add(restaurant.getId());
    }
    
    /**
     * Method to search a restaurant by name.
     * @param restaurant partial name of the restaurant
     */
    public  final void          searchRestaurant        (String restaurant)
    {
      home.list_restaurants_searchRestaurants   (restaurant);
    }
    
    /**
     * Method to view user list (either favourite list of Customer or owned list of Restaurateur).
     */
    public  final void          viewUserList            ()
    {
      List<Integer>     restaurantsList;
      Customer          customer;
      Restaurateur      restaurateur;
      
      if(loggedUser instanceof Customer)
      {
        customer            = (Customer)    loggedUser;
        if (customer.getListFavorite().size() == 0) {
            JOptionPane.showMessageDialog(null, "You must have a favourite restaurant first! (You can remove it later!)", "No favorite restaurants yet!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        restaurantsList     = customer     .getListFavorite ()      .getList();
      }
      else
      {
        restaurateur        = (Restaurateur)loggedUser;
        if (restaurateur.getListOwned().size() == 0) {
            JOptionPane.showMessageDialog(null, "You must have an owned restaurant first! (You can remove it later!)", "No owned restaurants yet!", JOptionPane.WARNING_MESSAGE);
            return;
        }
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
     * @see Home#visualizeAdvancedSearchResult
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
     * Converts selected cuisine from booleans to string list.
     * @param booleanCuisines selected cuisines
     * @return string list of selected cuisines
     */   
    public        List<String>  getListCuisines         (boolean[] booleanCuisines)
    {
      List<String> selectedCuisines = new ArrayList<>();
      
      for(int i=0; i<booleanCuisines.length; i++)
        if(booleanCuisines[i])
          selectedCuisines.add(cuisines.getList().get(i));
      
      return selectedCuisines;
    }
    
    /**
     * Converts selected services from booleans to string list.
     * @param booleanServices selected services
     * @return string list of selected services
     */   
    public        List<String>  getListServices         (boolean[] booleanServices)
    {
      List<String> selectedServices = new ArrayList<>();
      
      for(int i=0; i<booleanServices.length; i++)
        if(booleanServices[i])
          selectedServices.add(services.getList().get(i));
      
      return selectedServices;
    }
    
    /**
     * Get list of reviews of selected Restaurant from database
     * @param id the restaurant's id
     * @return the list of review {@link ListReview}
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public        ListReview    getRestaurantReviews(int id) throws IOException, ClassNotFoundException
    {
      return serverHandler.getReviews(id);
    }
    
    /**
     * Get list of reviews of customer from database
     * @return the list of review {@link ListReview}
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public        ListReview    getCustomerReviews() throws IOException, ClassNotFoundException
    {
      return serverHandler.getCustomerReviews(loggedUser.getId());
    }
    
    /**
     * Edit the selected review on database
     * @param review the review modified
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public        void          editReview(Review review) throws IOException, ClassNotFoundException
    {
      serverHandler.editReview(review);
    }
    
    /**
     * Add a new review on database
     * @param review the review to add
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public        void          addReview(Review review) throws IOException, ClassNotFoundException
    {
      serverHandler.addReview(review);
    }
    
    /**
     * Removes the selected review from database
     * @param review the review to remove
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public        void          removeReview(Review review) throws IOException, ClassNotFoundException
    {
      serverHandler.removeReview(review);
    }
    
    /**
     * Adds a new response to the server.
     *
     * @param response the response to be added
     * @throws IOException 
     * @throws ClassNotFoundException
     */
    public        void          addResponse (Response response) throws IOException, ClassNotFoundException
    {
      serverHandler.addResponse(response);
    }
    
    /**
     * Adds a restaurant to a customer's favourites.
     *
     * @param restaurantID the ID of the restaurant
     * @param customerID the ID of the customer
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public        void          addFavourite(int restaurantID, int customerID) throws IOException, ClassNotFoundException
    {
      ArrayList<Integer> temp = new ArrayList<>();
      temp.add(restaurantID);
      temp.add(customerID);
      serverHandler.addFavourite(temp);
    }
    
    /**
     * Removes a restaurant from a customer's favourites.
     *
     * @param restaurantID the ID of the restaurant
     * @param customerID the ID of the customer
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public        void          removeFavourite(int restaurantID, int customerID) throws IOException, ClassNotFoundException
    {
      ArrayList<Integer> temp = new ArrayList<>();
      temp.add(restaurantID);
      temp.add(customerID);
      serverHandler.removeFavourite(temp);
    }
    /**
     * Close socket
     */
    public        void          closeSocket()
    {
      serverHandler.closeSocket();
    }
    //</editor-fold>
}