package theknife.server;

import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import simple.socket.SocketUtils;
import theknife.cypher.CypherHandler;
import theknife.obj.lists.ListCuisines;
import theknife.obj.lists.ListFavorite;
import theknife.obj.lists.ListOwned;

import theknife.obj.lists.ListFavorite;
import theknife.obj.lists.ListRestaurant;
import theknife.obj.lists.ListReview;
import theknife.obj.lists.ListServices;
import theknife.obj.restaurant.Restaurant;
import theknife.obj.review.Review;
import theknife.obj.user.Customer;
import theknife.obj.user.Restaurateur;

/**
 *
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       760743 (CO)
 */

public class ClientHandler implements Runnable 
{
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
        GET_LIST_FAVOURITE,
        GET_OWNED_RESTAURANTS,
        ADD_RESTAURANT,
        ADD_REVIEW,
        EDIT_REVIEW,
        REMOVE_REVIEW
    };
    private Socket clientSocket;
    private Connection dbConnection;
    private QueryServer queryServer;
    private ResultSet rs;
    
    private CypherHandler cypherHandler = new CypherHandler();

    public ClientHandler(Socket socket, Connection dbConnection) {
        this.clientSocket = socket;
        this.dbConnection = dbConnection;
    }

    public ClientHandler(Connection dbConnection, QueryServer queryServer) {
        this.dbConnection = dbConnection;
        this.queryServer = queryServer;
    }

    @Override
    public void run() 
    {
        //TODO mettersi in ascolto sulla porta 7070
        try 
        {
            while (clientSocket.isClosed()) 
            {            
            String cmd = SocketUtils.receive(clientSocket);        
            COMMANDS cmdc = COMMANDS.valueOf(cmd);
            
                switch (cmdc) {
                    case LOGIN_CUSTOMER:
                        Customer loginCustomer = SocketUtils.receive(clientSocket, Customer.class);
                        SocketUtils.send(clientSocket, checkLoginCustomer(loginCustomer));
                        break;
                    case LOGIN_RESTAURATEUR:
                        Restaurateur loginRestaurateur = SocketUtils.receive(clientSocket, Restaurateur.class);
                        SocketUtils.send(clientSocket, checkLoginRestaurateur(loginRestaurateur));
                        break;
                    case REGISTER_CUSTOMER:
                        Customer registerCustomer = SocketUtils.receive(clientSocket, Customer.class);
                        break;
                    case REGISTER_RESTAURATEUR:
                        Restaurateur registerRestaurateur = SocketUtils.receive(clientSocket, Restaurateur.class);
                        break;
                    case GET_RESTAURANTS:
                        ListRestaurant listRestaurant = getRestaurants();
                        SocketUtils.send(clientSocket, listRestaurant);
                        break;
                    case GET_CUISINES:
                        ListCuisines listCuisines = getCuisines();
                        SocketUtils.send(clientSocket, listCuisines);
                        break;
                    case GET_SERVICES:
                        ListServices listServices = getServices();
                        SocketUtils.send(clientSocket, listServices);
                        break;
                    case GET_REVIEWS:
                        ListReview listReview = SocketUtils.receive(clientSocket, ListReview.class);
                        break;
                    case GET_LIST_FAVOURITE:
                        int customerID = SocketUtils.receive(clientSocket, Integer.class);
                        ListFavorite listFavorite = getFavorite(customerID);
                        SocketUtils.send(clientSocket, listFavorite);
                        break;
                    case GET_OWNED_RESTAURANTS:
                        int restaurateurID = SocketUtils.receive(clientSocket, Integer.class);
                        ListOwned listOwned = getOwned(restaurateurID);
                        SocketUtils.send(clientSocket, listOwned);
                        break;
                    case ADD_RESTAURANT:
                        Restaurant restaurant = SocketUtils.receive(clientSocket, Restaurant.class);
                        break;
                    case ADD_REVIEW:
                        Review addReview = SocketUtils.receive(clientSocket, Review.class);
                        break;
                    case EDIT_REVIEW:
                        Review editReview = SocketUtils.receive(clientSocket, Review.class);
                        break;
                    case REMOVE_REVIEW:
                        Review removeReview = SocketUtils.receive(clientSocket, Review.class);
                        break;
                    default: System.err.println("Unexpected command: " + cmdc);
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

    private boolean checkLoginCustomer(Customer customer) throws Exception
    {
      return cypherHandler.decryptCustomer(dbConnection, customer.getUsername(), customer.getPassword());
    }
    
    private boolean checkLoginRestaurateur(Restaurateur restaurateur) throws Exception
    {
      return cypherHandler.decryptCustomer(dbConnection, restaurateur.getUsername(), restaurateur.getPassword());
    }
            
    private ListRestaurant getRestaurants() {
        String sql = queryServer.getAllRestaurant();
        ListRestaurant temp = new ListRestaurant();
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    //TODO salvare i ristoranti nella lista locale (in teoria fatto)
                    temp.add(new Restaurant(rs.getInt("id"),
                                            rs.getInt("ownerID"),
                                            rs.getString("name"),
                                            rs.getString("normName"),
                                            rs.getInt("price"),
                                            rs.getString("currency"),
                                            rs.getString("phoneNo"),
                                            rs.getString("url"),
                                            rs.getString("webUrl"),
                                            rs.getString("award"),
                                            rs.getBoolean("greenStar"),
                                            rs.getString("cuisine"),
                                            rs.getString("service"),
                                            rs.getString("description"),
                                            rs.getDouble("rating"),
                                            rs.getString("country"),
                                            rs.getString("city"),
                                            rs.getString("address"),
                                            rs.getDouble("latitude"),
                                            rs.getDouble("longitude")
                            ));
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERRORE] Impossibile connettersi al database.");
            System.err.println("Motivo: " + e.getMessage());
            System.exit(1);
        }
        return temp;
    }

    private ListOwned getOwned(int id){
        String sql = queryServer.getOwned(id);
        ArrayList<Integer> list = new ArrayList();
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getInt("ID"));
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERRORE] Impossibile connettersi al database.");
            System.err.println("Motivo: " + e.getMessage());
            System.exit(1);
        }
        ListOwned temp = new ListOwned(list);
        return temp;
    }
    
    private ListFavorite getFavorite(int id){
        String sql = queryServer.getFavorites(id);
        ArrayList<Integer> list = new ArrayList();
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getInt("ID"));
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERRORE] Impossibile connettersi al database.");
            System.err.println("Motivo: " + e.getMessage());
            System.exit(1);
        }
        ListFavorite temp = new ListFavorite(list);
        return temp;
    }

    private ListCuisines getCuisines(){
        String sql = queryServer.getAllCuisine();
        ArrayList<String> list = new ArrayList();
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("description"));
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERRORE] Impossibile connettersi al database.");
            System.err.println("Motivo: " + e.getMessage());
            System.exit(1);
        }
        ListCuisines temp = new ListCuisines(list);
        return temp;
    }

    private ListServices getServices(){
        String sql = queryServer.getAllServices();
        ArrayList<String> list = new ArrayList();
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("description"));
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERRORE] Impossibile connettersi al database.");
            System.err.println("Motivo: " + e.getMessage());
            System.exit(1);
        }
        ListServices temp = new ListServices(list);
        return temp;
    }
}
