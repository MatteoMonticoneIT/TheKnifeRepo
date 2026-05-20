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
import theknife.obj.lists.ListFavorite;
import theknife.obj.lists.ListOwned;

import theknife.obj.lists.ListFavorite;
import theknife.obj.lists.ListRestaurant;
import theknife.obj.lists.ListReview;
import theknife.obj.restaurant.Restaurant;
import theknife.obj.review.Review;
import theknife.obj.user.Customer;
import theknife.obj.user.Restaurateur;

/**
 *
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public class ClientHandler implements Runnable {
    public enum COMMANDS {
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

    public ClientHandler(Socket socket, Connection dbConnection) {
        this.clientSocket = socket;
        this.dbConnection = dbConnection;
    }

    public ClientHandler(Connection dbConnection, QueryServer queryServer) {
        this.dbConnection = dbConnection;
        this.queryServer = queryServer;
    }

    @Override
    public void run() {
        //TODO mettersi in ascolto sulla porta 7070
        try {
            String cmd = SocketUtils.receive(clientSocket);        
            COMMANDS cmdc = COMMANDS.valueOf(cmd);
            while (true) {            
                switch (cmdc) {
                    case LOGIN_CUSTOMER:
                        Customer loginCustomer = SocketUtils.receive(clientSocket, Customer.class);
                        break;
                    case LOGIN_RESTAURATEUR:
                        Restaurateur loginRestaurateur = SocketUtils.receive(clientSocket, Restaurateur.class);
                        break;
                    case REGISTER_CUSTOMER:
                        Customer registerCustomer = SocketUtils.receive(clientSocket, Customer.class);
                        break;
                    case REGISTER_RESTAURATEUR:
                        Restaurateur registerRestaurateur = SocketUtils.receive(clientSocket, Restaurateur.class);
                        break;
                    case GET_RESTAURANTS:
                        ListRestaurant listRestaurant = SocketUtils.receive(clientSocket, ListRestaurant.class);
                        break;
                    case GET_CUISINES:
                        break;
                    case GET_SERVICES:
                        break;
                    case GET_REVIEWS:
                        ListReview listReview = SocketUtils.receive(clientSocket, ListReview.class);
                        break;
                    case GET_LIST_FAVOURITE:
                        ListFavorite listFavorite = SocketUtils.receive(clientSocket, ListFavorite.class);
                        break;
                    case GET_OWNED_RESTAURANTS:
                        ListOwned listOwnedRestaurants = SocketUtils.receive(clientSocket, ListOwned.class);
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
        }
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

    private ArrayList<String> getCuisine(){
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
        return list;
    }

    private ArrayList<String> getService(){
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
        return list;
    }
}
