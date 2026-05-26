package theknife.server;

import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import simple.socket.SocketUtils;
import theknife.cypher.CypherHandler;
import theknife.obj.lists.ListCuisines;
import theknife.obj.lists.ListResponse;

import theknife.obj.lists.ListRestaurant;
import theknife.obj.lists.ListReview;
import theknife.obj.lists.ListServices;
import theknife.obj.restaurant.Restaurant;
import theknife.obj.review.Response;
import theknife.obj.review.Review;
import theknife.obj.user.Customer;
import theknife.obj.user.Restaurateur;

/**
 * Handles client socket connections.
 * Processes database requests.
 * Runs in a separate thread.
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       760743 (CO)
 */

public class ClientHandler implements Runnable 
{
    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * Defines supported client commands.
     */
    public enum COMMANDS 
    {
        LOGIN_CUSTOMER,
        LOGIN_RESTAURATEUR,
        REGISTER_CUSTOMER,
        REGISTER_RESTAURATEUR,
        GET_RESTAURANTS,
        GET_CUISINES,
        GET_SERVICES,
        GET_REVIEWS,
        GET_CUSTOMER_REVIEWS,
        ADD_FAVOURITE,
        REMOVE_FAVOURITE,
        ADD_RESTAURANT,
        ADD_REVIEW,
        ADD_RESPONSE,
        EDIT_REVIEW,
        REMOVE_REVIEW     
    }
    
    /**
     * Holds the client socket.
     */
    private Socket          clientSocket;
    
    /**
     * Holds the database connection.
     */
    private Connection      dbConnection;  
    
    /**
     * Handles encryption operations.
     */
    private CypherHandler   cypherHandler;
    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new client handler.
     * @param socket The client socket.
     * @param dbConnection The database connection.
     * @throws Exception If cipher initialization fails.
     */
    public ClientHandler(Socket socket, Connection dbConnection) throws Exception 
    {
        this.clientSocket   = socket;
        this.dbConnection   = dbConnection;
        this.cypherHandler  = new CypherHandler();
    }
    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Methods">   
    /**
     * Listens for client commands.
     * Routes commands to specific methods.
     */
    @Override
    public void run() 
    {
        try 
        {
          while (!clientSocket.isClosed()) 
          {            
            String cmd      = SocketUtils.receive(clientSocket, String.class);    
            if (cmd == null)
              break;
            COMMANDS cmdc   = COMMANDS.valueOf(cmd);
            
                switch (cmdc) 
                {
                    case LOGIN_CUSTOMER:
                        Customer loginCustomer              = SocketUtils.receive   (clientSocket, Customer.class);
                        loginCustomer.setPassword                                   (cypherHandler.decryptUser(loginCustomer.getPassword()));
                        SocketUtils.send(clientSocket, checkLoginCustomer           (loginCustomer));
                        break;
                    case LOGIN_RESTAURATEUR:
                        Restaurateur loginRestaurateur      = SocketUtils.receive   (clientSocket, Restaurateur.class);
                        loginRestaurateur.setPassword                               (cypherHandler.decryptUser(loginRestaurateur.getPassword()));
                        SocketUtils.send(clientSocket, checkLoginRestaurateur       (loginRestaurateur));
                        break;
                    case REGISTER_CUSTOMER:
                        Customer registerCustomer           = SocketUtils.receive   (clientSocket, Customer.class);
                        registerCustomer.setPassword                                (cypherHandler.decryptUser(registerCustomer.getPassword()));
                        SocketUtils.send(clientSocket, registerCustomer             (registerCustomer));
                        break;
                    case REGISTER_RESTAURATEUR:
                        Restaurateur registerRestaurateur   = SocketUtils.receive   (clientSocket, Restaurateur.class);
                        registerRestaurateur.setPassword                            (cypherHandler.decryptUser(registerRestaurateur.getPassword()));
                        SocketUtils.send(clientSocket, registerRestaurateur         (registerRestaurateur));
                        break;
                    case GET_RESTAURANTS:
                        ListRestaurant listRestaurant       = getRestaurants();
                        SocketUtils.send(clientSocket, listRestaurant);
                        break;
                    case GET_CUISINES:
                        ListCuisines listCuisines           = getCuisines();
                        SocketUtils.send(clientSocket, listCuisines);
                        break;
                    case GET_SERVICES:
                        ListServices listServices           = getServices();
                        SocketUtils.send(clientSocket, listServices);
                        break;
                    case GET_REVIEWS:
                        int restaurantID                    = SocketUtils.receive   (clientSocket, Integer.class);
                        ListReview listReview = getReview(restaurantID);
                        SocketUtils.send(clientSocket, listReview);
                        break;
                    case GET_CUSTOMER_REVIEWS:
                        int customerID                      = SocketUtils.receive   (clientSocket, Integer.class);
                        ListReview listCustomerReview = getCustomerReview(customerID);
                        SocketUtils.send(clientSocket, listCustomerReview);
                        break;
                    case ADD_FAVOURITE:
                        ArrayList<Integer> addFavorite      = SocketUtils.receive   (clientSocket, ArrayList.class);
                        addFavourite(addFavorite);
                        break;
                    case REMOVE_FAVOURITE:
                        ArrayList<Integer> remfavorite      = SocketUtils.receive   (clientSocket, ArrayList.class);
                        removeFavourite(remfavorite);
                        break;
                    case ADD_RESTAURANT:
                        Restaurant restaurant               = SocketUtils.receive   (clientSocket, Restaurant.class);
                        addRestaurant(restaurant);
                        break;
                    case ADD_REVIEW:
                        Review addReview                    = SocketUtils.receive   (clientSocket, Review.class);
                        SocketUtils.send(clientSocket, addReview(addReview));
                        break;
                    case ADD_RESPONSE:
                        Response response                   = SocketUtils.receive   (clientSocket, Response.class);
                        addResponse(response);
                        break;
                    case EDIT_REVIEW:
                        Review editReview                   = SocketUtils.receive   (clientSocket, Review.class);
                        SocketUtils.send(clientSocket, editReview(editReview));
                        break;
                    case REMOVE_REVIEW:
                        Review removeReview                 = SocketUtils.receive   (clientSocket, Review.class);
                        SocketUtils.send(clientSocket, removeReview(removeReview));
                        break;
                    default:
                        System.err.println("Unexpected command: " + cmdc);
                }
            }
        } catch (IOException e) {
            System.err.println("Exception IOException: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Exception ClassNotFoundException: " + ex.getMessage());
        } catch (Exception ex) {
            System.getLogger(ClientHandler.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    /**
     * Verifies customer login credentials.
     * @param customer The customer object.
     * @return Decrypted customer if valid, null otherwise.
     * @throws Exception If decryption fails.
     */
    private Customer        checkLoginCustomer      (Customer customer)         throws Exception
    {
      try
      {
        return cypherHandler.decryptCustomer(dbConnection, customer.getUsername(), customer.getPassword());
      }
      catch(Throwable t)
      {
        t.printStackTrace();
      }
      return null;
    }

    /**
     * Verifies restaurateur login credentials.
     * @param restaurateur The restaurateur object.
     * @return Decrypted restaurateur if valid, null otherwise.
     * @throws Exception If decryption fails.
     */
    private Restaurateur    checkLoginRestaurateur  (Restaurateur restaurateur) throws Exception 
    {
      try
      {
        return cypherHandler.decryptRestaurateur(dbConnection, restaurateur.getUsername(), restaurateur.getPassword());
      }
      catch(Throwable t)
      {
        t.printStackTrace();
      }
      return null;
    }

    /**
     * Fetches all restaurants from the database.
     * @return A list of restaurants.
     */
    private ListRestaurant  getRestaurants          () 
    {
        String sql = "SELECT r.*, CASE WHEN COUNT(c.id) = 0 THEN '' ELSE STRING_AGG(DISTINCT c.description, ', ') END AS cuisine, CASE WHEN COUNT(s.id) = 0 THEN '' ELSE STRING_AGG(DISTINCT s.description, ', ') END AS service FROM restaurant r LEFT JOIN listcuisine lc ON lc.restaurantId = r.id LEFT JOIN cuisine c ON c.id = lc.cuisineId LEFT JOIN listservice ls ON ls.restaurantId = r.id LEFT JOIN service s ON ls.serviceId = s.id GROUP BY r.id";
        ListRestaurant temp = new ListRestaurant();
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
        {
          try (ResultSet rs = pstmt.executeQuery()) 
          {
            while (rs.next()) 
              temp.add(new Restaurant(  rs.getInt       ("id"),
                                        rs.getInt       ("ownerID"),
                                        rs.getString    ("name"),
                                        rs.getString    ("normName"),
                                        rs.getInt       ("price"),
                                        rs.getString    ("currency"),
                                        rs.getString    ("phoneNo"),
                                        rs.getString    ("url"),
                                        rs.getString    ("webUrl"),
                                        rs.getString    ("award"),
                                        rs.getBoolean   ("greenStar"),
                                        rs.getString    ("cuisine"),
                                        rs.getString    ("service"),
                                        rs.getString    ("description"),
                                        rs.getDouble    ("rating"),
                                        rs.getString    ("country"),
                                        rs.getString    ("city"),
                                        rs.getString    ("address"),
                                        rs.getDouble    ("latitude"),
                                        rs.getDouble    ("longitude")
                                     ));
          }
        } 
        catch (SQLException e) 
        {
          System.err.println("[ERRORE] Impossibile connettersi al database.");
          System.err.println("Motivo: " + e.getMessage());
          System.exit(1);
        }
        return temp;
    }

    /**
     * Fetches all available cuisines.
     * @return A list of cuisines.
     */
    private ListCuisines    getCuisines             ()
    {
        String sql = "SELECT description FROM cuisine";
        ArrayList<String> list = new ArrayList();
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
        {
          try (ResultSet rs = pstmt.executeQuery()) 
          {
            while (rs.next()) 
              list.add(rs.getString("description"));
          }
        } 
        catch (SQLException e) 
        {
          System.err.println("[ERRORE] Impossibile connettersi al database.");
          System.err.println("Motivo: " + e.getMessage());
          System.exit(1);
        }
        ListCuisines temp = new ListCuisines(list);
        return temp;
    }

    /**
     * Fetches all available services.
     * @return A list of services.
     */
    private ListServices    getServices             ()
    {
        String sql = "SELECT description FROM service";
        ArrayList<String> list = new ArrayList();
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
        {
          try (ResultSet rs = pstmt.executeQuery()) 
          {
            while (rs.next())
              list.add(rs.getString("description"));
          }
        } 
        catch (SQLException e) 
        {
          System.err.println("[ERRORE] Impossibile connettersi al database.");
          System.err.println("Motivo: " + e.getMessage());
          System.exit(1);
        }
        ListServices temp = new ListServices(list);
        return temp;
    }

    /**
     * Fetches all reviews for a specific restaurant.
     * @param id The restaurant ID.
     * @return A list of reviews.
     */
    private ListReview      getReview               (int id) 
    {
      String sql = "SELECT r.*, c.username, resp.ID AS respID, resp.restaurateurID, resp.content AS respContent, rest.username AS respUsername FROM review r JOIN customer c ON c.ID = r.IDCustomer LEFT JOIN response resp ON r.ID = resp.reviewID LEFT JOIN restaurateur rest ON resp.restaurateurID = rest.ID WHERE r.restaurantID = ? ORDER BY r.ID;";
      ListReview list = new ListReview();
      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
      {
        pstmt.setInt(1, id);
        try (ResultSet rs = pstmt.executeQuery())
        {
          while (rs.next()) 
          {
            list.add(new Review(    rs.getInt   ("id"),
                                    rs.getInt   ("restaurantID"),
                                    rs.getInt   ("IDCustomer"),
                                    rs.getString("username"),
                                    rs.getString("content"),
                                    rs.getDouble("rating")
                               ));

            Integer respID = (Integer) rs.getObject("respID");
            if(respID != null)
            {
             ListResponse response = new ListResponse();
              response.add(new Response(respID,
                                        rs.getInt     ("restaurateurID"),
                                        rs.getString  ("respUsername"),
                                        rs.getString  ("respContent")));
             
              list.getList().get(list.size()-1).setResponses(response);
            }
          }
        }
      }
      catch (SQLException e) 
      {
        System.err.println(e);
      }
      return list;
    }
    
    /**
     * Fetches all reviews made by a specific customer.
     * @param id The customer ID.
     * @return A list of reviews.
     */
    private ListReview      getCustomerReview       (int id) 
    {
      String sql = "SELECT r.*, c.username FROM review r JOIN customer c ON c.ID = r.IDCustomer WHERE IDCustomer = ?";
      ListReview list = new ListReview();
      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
      {
        pstmt.setInt(1, id);
        try (ResultSet rs = pstmt.executeQuery())
        {
          while (rs.next()) 
          {
            list.add(new Review(    rs.getInt   ("id"),
                                    rs.getInt   ("restaurantID"),
                                    rs.getInt   ("IDCustomer"),
                                    rs.getString("username"),
                                    rs.getString("content"),
                                    rs.getDouble("rating")
                               ));
            

            }
          }
        } catch (SQLException e) 
      {
        System.err.println(e);
      }
      return list;
      
    }

    /**
     * Adds a restaurant to a customer's favorites.
     * @param listFavorite A list containing customer ID and restaurant ID.
     */
    private void            addFavourite            (ArrayList<Integer> listFavorite)
    {
      String sql = "INSERT INTO listFavourite VALUES (?, ?)";
      
      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
      {
        pstmt.setInt    (1, listFavorite.get(0));
        pstmt.setInt    (2, listFavorite.get(1));
        pstmt.executeUpdate();
      } 
      catch (SQLException e) 
      {
        System.err.println("[ERRORE] Impossibile connettersi al database.");
        System.err.println("Motivo: " + e.getMessage());
        System.exit(1);
      }    
    }   
    
    /**
     * Removes a restaurant from a customer's favorites.
     * @param listFavorite A list containing customer ID and restaurant ID.
     */
    private void            removeFavourite         (ArrayList<Integer> listFavorite)
    {
      String sql = "DELETE FROM listFavourite WHERE customerid = ? AND restaurantID = ?";
      
      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
      {
        pstmt.setInt    (1, listFavorite.get(0));
        pstmt.setInt    (2, listFavorite.get(1));
        pstmt.executeUpdate();     
      } 
      catch (SQLException e) 
      {
        System.err.println("[ERRORE] Impossibile connettersi al database.");
        System.err.println("Motivo: " + e.getMessage());
        System.exit(1);
      }    
    }
    
    /**
     * Registers a new customer.
     * @param customer The new customer object.
     * @return True if successful, false otherwise.
     * @throws Exception If encryption fails.
     */
    private Boolean         registerCustomer        (Customer customer)         throws Exception 
    {
      String sql = "SELECT * FROM customer WHERE username = ? OR email = ?";
      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
      {
        pstmt.setString(1, customer.getUsername ());
        pstmt.setString(2, customer.getEmail    ());
        
        try (ResultSet rs = pstmt.executeQuery()) 
        {
          if (rs.next())
            return false;
        }
      }
      catch (SQLException e) 
      {
        System.err.println("[ERRORE] Impossibile connettersi al database.");
        System.err.println("Motivo: " + e.getMessage());
        System.exit(1);      
      }
      
      sql = "INSERT INTO Customer (firstName, firstNameNormalized, lastName, lastNameNormalized, birthDate, address, username, email, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
      String            passwordEncrypted   = cypherHandler.encryptUser(customer.getPassword());
      DateTimeFormatter formatter           = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate         localDate           = LocalDate.parse(customer.getBirthDate(), formatter);
      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
      {
        pstmt.setString     (1, customer.getFirstName());
        pstmt.setString     (2, customer.getFirstNameNormalized());
        pstmt.setString     (3, customer.getLastName());
        pstmt.setString     (4, customer.getLastNameNormalized());
        pstmt.setDate       (5, java.sql.Date.valueOf(localDate));
        pstmt.setString     (6, customer.getAddress());
        pstmt.setString     (7, customer.getUsername());
        pstmt.setString     (8, customer.getEmail());
        pstmt.setString     (9, passwordEncrypted);
        pstmt.executeUpdate();
        return true;
      } 
      catch (SQLException e) 
      {
        System.err.println("[ERRORE] Impossibile connettersi al database.");
        System.err.println("Motivo: " + e.getMessage());
        System.exit(1);
      }
      return false;
    }

    /**
     * Registers a new restaurateur.
     * @param restaurateur The new restaurateur object.
     * @return True if successful, false otherwise.
     * @throws Exception If encryption fails.
     */
    private Boolean         registerRestaurateur    (Restaurateur restaurateur) throws Exception 
    {
      String sql = "SELECT * FROM restaurateur WHERE username = ? OR email = ?";
      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
      {
        pstmt.setString(1, restaurateur.getUsername ());
        pstmt.setString(2, restaurateur.getEmail    ());
        
        try (ResultSet rs = pstmt.executeQuery()) 
        {
          if (rs.next())
            return false;
        } 
        catch (SQLException e) 
        {
          System.err.println("[ERRORE] Impossibile connettersi al database.");
          System.err.println("Motivo: " + e.getMessage());
          System.exit(1);
        }
      }
      
      sql = "INSERT INTO Restaurateur (firstName, firstNameNormalized, lastName, lastNameNormalized, birthDate, address, username, email, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
      String            passwordEncrypted   = cypherHandler.encryptUser(restaurateur.getPassword());
      DateTimeFormatter formatter           = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate         localDate           = LocalDate.parse(restaurateur.getBirthDate(), formatter);
      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql))
      {
        pstmt.setString (1, restaurateur.getFirstName());
        pstmt.setString (2, restaurateur.getFirstNameNormalized());
        pstmt.setString (3, restaurateur.getLastName());
        pstmt.setString (4, restaurateur.getLastNameNormalized());
        pstmt.setDate   (5, java.sql.Date.valueOf(localDate));
        pstmt.setString (6, restaurateur.getAddress());
        pstmt.setString (7, restaurateur.getUsername());
        pstmt.setString (8, restaurateur.getEmail());
        pstmt.setString (9, passwordEncrypted);
        pstmt.executeUpdate(); 
        return true;
      } 
      catch (SQLException e) 
      {
        System.err.println("[ERRORE] Impossibile connettersi al database.");
        System.err.println("Motivo: " + e.getMessage());
        System.exit(1);
      }
      return false;
    }

    /**
     * Adds a new restaurant to the database.
     * @param restaurant The new restaurant object.
     * @return True if successful, false otherwise.
     */
    private boolean         addRestaurant           (Restaurant restaurant) 
    {
      String sql = "INSERT INTO Restaurant (ownerID, name, normName, price, currency, phoneNo, country, city, address, latitude, longitude, url, webUrl, award, greenStar, description, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql))
      {
        pstmt.setInt        (1,     restaurant.getOwnerId());
        pstmt.setString     (2,     restaurant.getName());
        pstmt.setString     (3,     restaurant.getNormalizedName());
        pstmt.setInt        (4,     restaurant.getPrice());
        pstmt.setString     (5,     restaurant.getCurrency());
        pstmt.setString     (6,     restaurant.getPhoneNumber());
        pstmt.setString     (7,     restaurant.getCountry());
        pstmt.setString     (8,     restaurant.getCity());
        pstmt.setString     (9,    restaurant.getAddress());
        pstmt.setDouble     (10,    restaurant.getLatitude());
        pstmt.setDouble     (11,    restaurant.getLongitude());
        pstmt.setString     (12,    restaurant.getUrl());
        pstmt.setString     (13,    restaurant.getWebsiteUrl());
        pstmt.setString     (14,    restaurant.getAward());
        pstmt.setBoolean    (15,    restaurant.isGreenStar());
        pstmt.setString     (16,    restaurant.getDescription());
        pstmt.setDouble     (17,    restaurant.getRating());
        pstmt.executeUpdate();
      }  
      catch (SQLException e) 
      {
        System.err.println("[ERRORE] Impossibile connettersi al database.");
        System.err.println("Motivo: " + e.getMessage());
        System.exit(1);
      }
      return false;
    }

    /**
     * Adds a new review and updates the restaurant's average rating.
     * @param review The new review object.
     * @return True if successful, false otherwise.
     */
    private Double         addReview               (Review review) 
    {
      String sql = "INSERT INTO Review (restaurantID, IDCustomer, rating, content) VALUES (?, ?, ?, ?)";
      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
      {
        pstmt.setInt    (1, review.getRestaurantID());
        pstmt.setInt    (2, review.getCustomerID());
        pstmt.setDouble (3, review.getRating());
        pstmt.setString (4, review.getContent());

        pstmt.executeUpdate();
        return restaurantRatingAverage(review.getRestaurantID());    
      } 
      catch (SQLException e) 
      {
        System.err.println("[ERRORE] Impossibile connettersi al database.");
        System.err.println("Motivo: " + e.getMessage());
        System.exit(1);
      }
      return 0d;
    }

    /**
     * Adds a new review and updates the restaurant's average rating.
     * @param review The new review object.
     * @return True if successful, false otherwise.
     */
    private void            addResponse             (Response response)
    {
      String sql = "INSERT INTO response (reviewID, restaurateurID, content) VALUES (?, ?, ?)";
      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
      {
        pstmt.setInt    (1, response.getReviewID());
        pstmt.setInt    (2, response.getRestaurateurID());
        pstmt.setString (3, response.getContent());
        pstmt.executeUpdate(); 
      } 
      catch (SQLException e) 
      {
        System.err.println("[ERRORE] Impossibile connettersi al database.");
        System.err.println("Motivo: " + e.getMessage());
        System.exit(1);
      }
    }
    /**
     * Edits an existing review.
     * Updates the restaurant's average rating.
     * @param review The updated review object.
     * @return True if successful, false otherwise.
     */
    private Double         editReview              (Review review) 
    {
        String sql = "SELECT * FROM review WHERE id = ?";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setInt(1, review.getID());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    sql = "UPDATE review SET content = ?, rating = ? WHERE id = ?";
                    try (PreparedStatement pstmt2 = dbConnection.prepareStatement(sql)) {
                        pstmt2.setString(1, review.getContent());
                        pstmt2.setDouble(2, review.getRating());
                        pstmt2.setInt(3, review.getID());
                        pstmt2.executeUpdate();
                        return restaurantRatingAverage(review.getRestaurantID());
                    } catch (SQLException e) {
                        System.err.println("[ERRORE] Impossibile connettersi al database.");
                        System.err.println("Motivo: " + e.getMessage());
                        System.exit(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERRORE] Impossibile connettersi al database.");
            System.err.println("Motivo: " + e.getMessage());
            System.exit(1);
        }
        return 0d;
    }

    /**
     * Removes a review from the database.
     * @param review The review object to remove.
     * @return True if successful, false otherwise.
     */
    private Double         removeReview            (Review review) 
    {
        String sql = "SELECT * FROM review WHERE id = ?";
        try(PreparedStatement pstmt = dbConnection.prepareStatement(sql)){
            pstmt.setInt(1, review.getID());
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    sql = "DELETE FROM review WHERE id = ?";
                    try(PreparedStatement pstmt2 = dbConnection.prepareStatement(sql)){
                        pstmt2.setInt(1, review.getID());
                        pstmt2.executeUpdate();
                        return restaurantRatingAverage(review.getRestaurantID());         
                    }catch(SQLException e){
                        System.err.println("[ERRORE] Impossibile connettersi al database.");
                        System.err.println("Motivo: " + e.getMessage());
                        System.exit(1);
                    }

                }
            }catch(SQLException e){
                System.err.println("[ERRORE] Impossibile connettersi al database.");
                System.err.println("Motivo: " + e.getMessage());
                System.exit(1);
            }
        }catch(SQLException e){
            System.err.println("[ERRORE] Impossibile connettersi al database.");
            System.err.println("Motivo: " + e.getMessage());
            System.exit(1);
        }
        return 0d;
    }
    
    /**
     * Method to calculate a restaurant rating average after inserting or editing a new review.
     * @param id the restaurant's id which needs to recalculate rating average
     */
    public        Double      restaurantRatingAverage (int id)
    {
      Double rating = 0d;
      String sql = "UPDATE Restaurant SET rating = (SELECT ROUND(AVG(rating), 2) FROM Review WHERE restaurantID = ?) WHERE ID = ?;";
      try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) 
      {
        pstmt.setInt(1, id);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
      } 
      catch (SQLException e) 
      {
        System.err.println("[ERRORE] Impossibile connettersi al database.");
        System.err.println("Motivo: " + e.getMessage());
        System.exit(1);
      }
      
      sql = "SELECT rating FROM restaurant WHERE id = ?";
      try (PreparedStatement pstmt2 = dbConnection.prepareStatement(sql)) 
      {
        pstmt2.setInt(1, id);
        try (ResultSet rs = pstmt2.executeQuery()) 
        {
          rating = (rs.next()) ? rs.getDouble("rating"):0d;
        }
      } 
      catch (SQLException e) 
      {
        System.err.println("[ERRORE] Impossibile connettersi al database.");
        System.err.println("Motivo: " + e.getMessage());
        System.exit(1);
      }
      return rating;
    }
    //</editor-fold> 
}
