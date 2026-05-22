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
import theknife.obj.user.User;

/**
 *
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       760743 (CO)
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
    }

    ;
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
    public void run() {
        //TODO mettersi in ascolto sulla porta 7070
        try {
            while (clientSocket.isClosed()) {
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
                        SocketUtils.send(clientSocket, registerCustomer(registerCustomer));
                        break;
                    case REGISTER_RESTAURATEUR:
                        Restaurateur registerRestaurateur = SocketUtils.receive(clientSocket, Restaurateur.class);
                        SocketUtils.send(clientSocket, registerRestaurateur(registerRestaurateur));
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

    private Customer checkLoginCustomer(Customer customer) throws Exception {
        return cypherHandler.decryptCustomer(dbConnection, customer.getUsername(), customer.getPassword());
    }

    private Restaurateur checkLoginRestaurateur(Restaurateur restaurateur) throws Exception {
        return cypherHandler.decryptRestaurateur(dbConnection, restaurateur.getUsername(), restaurateur.getPassword());
    }

    private ListRestaurant getRestaurants() {
        String sql = queryServer.getAllRestaurant();
        ListRestaurant temp = new ListRestaurant();
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
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

    private ListOwned getOwned(int id) {
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

    private ListFavorite getFavorite(int id) {
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

    private ListCuisines getCuisines() {
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

    private ListServices getServices() {
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

    private ListReview getReview(int id) {
        String sql = "SELECT r.*, c.username FROM review r JOIN customer c ON c.ID = r.IDCustomer WHERE restaurantID = ?";
        ListReview list = new ListReview();
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Review(
                            rs.getInt("id"),
                            rs.getInt("restaurantID"),
                            rs.getInt("IDCustomer"),
                            rs.getString("username"),
                            rs.getString("content"),
                            rs.getDouble("rating")
                    ));
                }
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        return list;
    }

    private boolean registerCustomer(Customer customer) throws Exception {
        String sql = "SELECT * FROM customer WHERE username = ?";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next())
                    return false;
            }

        } catch (SQLException e) {
            System.err.println("[ERRORE] Impossibile connettersi al database.");
            System.err.println("Motivo: " + e.getMessage());
            System.exit(1);
        }

        sql = "INSERT INTO customer VALUES (id, firstName, firstNameNormalized, lastName, lastNameNormalized, birthDate, address, username, email, password)";
        //TODO gestire parametri e eseguire la query
        String passwordEncrypted = cypherHandler.encryptUser(customer.getPassword());
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                pstmt.setInt(1, customer.getId());
                pstmt.setString(2, customer.getFirstName());
                pstmt.setString(2, customer.getFirstNameNormalized());
                pstmt.setString(2, customer.getLastName());
                pstmt.setString(2, customer.getLastNameNormalized());
                pstmt.setString(2, customer.getBirthDate());
                pstmt.setString(2, customer.getAddress());
                pstmt.setString(2, customer.getUsername());
                pstmt.setString(2, customer.getEmail());
                pstmt.setString(2, passwordEncrypted);
                return true;
            } catch (SQLException e) {
                System.err.println(e);
            }

        } catch (SQLException e) {
            System.err.println("[ERRORE] Impossibile connettersi al database.");
            System.err.println("Motivo: " + e.getMessage());
            System.exit(1);
        }
        return false;
    }

    private boolean registerRestaurateur(Restaurateur restaurateur) throws Exception {
        String sql = "SELECT * FROM restaurateur WHERE username = ?";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next())
                    return false;
            }

        } catch (SQLException e) {
            System.err.println("[ERRORE] Impossibile connettersi al database.");
            System.err.println("Motivo: " + e.getMessage());
            System.exit(1);
        }

        sql = "INSERT INTO restaurateur VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        //TODO gestire parametri e eseguire la query
        String passwordEncrypted = cypherHandler.encryptUser(restaurateur.getPassword());
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                pstmt.setInt(1, restaurateur.getId());
                pstmt.setString(2, restaurateur.getFirstName());
                pstmt.setString(3, restaurateur.getFirstNameNormalized());
                pstmt.setString(4, restaurateur.getLastName());
                pstmt.setString(5, restaurateur.getLastNameNormalized());
                pstmt.setString(6, restaurateur.getBirthDate());
                pstmt.setString(7, restaurateur.getAddress());
                pstmt.setString(8, restaurateur.getUsername());
                pstmt.setString(9, restaurateur.getEmail());
                pstmt.setString(10, passwordEncrypted);
                return true;
            } catch (SQLException e) {
                System.err.println(e);
            }

        } catch (SQLException e) {
            System.err.println("[ERRORE] Impossibile connettersi al database.");
            System.err.println("Motivo: " + e.getMessage());
            System.exit(1);
        }
        return false;
    }

    //TODO fare addRestaurant, addReview, editReview, removeRevoiew

    private boolean addRestaurant(Restaurant restaurant) {
        String sql = "INSERT INTO restaurant VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                pstmt.setInt(1, restaurant.getId());
                pstmt.setInt(2, restaurant.getOwnerId());
                pstmt.setString(3, restaurant.getName());
                pstmt.setString(4, restaurant.getNormalizedName());
                pstmt.setInt(5, restaurant.getPrice());
                pstmt.setString(6, restaurant.getCurrency());
                pstmt.setString(7, restaurant.getPhoneNumber());
                pstmt.setString(8, restaurant.getCountry());
                pstmt.setString(9, restaurant.getCity());
                pstmt.setString(10, restaurant.getAddress());
                pstmt.setDouble(11, restaurant.getLatitude());
                pstmt.setDouble(12, restaurant.getLongitude());
                pstmt.setString(13, restaurant.getUrl());
                pstmt.setString(14, restaurant.getWebsiteUrl());
                pstmt.setString(15, restaurant.getAward());
                pstmt.setBoolean(16, restaurant.isGreenStar());
                pstmt.setString(17, restaurant.getDescription());
                pstmt.setDouble(18, restaurant.getRating());
                return true;
            } catch (SQLException e) {
                System.err.println(e);
            }
        } catch (SQLException e) {
            System.err.println("[ERRORE] Impossibile connettersi al database.");
            System.err.println("Motivo: " + e.getMessage());
            System.exit(1);
        }
        return false;
    }

    private boolean addReview(Review review) {
        int customerID;

        String sql = "INSERT INTO review VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setInt(1, review.getID());
            pstmt.setInt(1, review.getRestaurantID());
            pstmt.setInt(1, review.getcustomerID());
            pstmt.setString(1, review.getContent());
            pstmt.setDouble(1, review.getRating());
            try (ResultSet rs = pstmt.executeQuery()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("[ERRORE] Impossibile connettersi al database.");
            System.err.println("Motivo: " + e.getMessage());
            System.exit(1);
        }
        return false;
    }

    private boolean editReview(Review review) {

        //controllo se esiste una review con quell'id
        String sql = "SELECT * FROM review WHERE id = ?";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setInt(1, review.getID());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    sql = "UPDATE review SET content = ?, rating = ? WHERE id = ?";
                    try (PreparedStatement pstmt2 = dbConnection.prepareStatement(sql)) {
                        pstmt.setString(1, review.getContent());
                        pstmt.setDouble(2, review.getRating());
                        pstmt.setInt(3, review.getID());
                        try (ResultSet rs2 = pstmt2.executeQuery()) {
                            return true;
                        } catch (SQLException e) {
                            System.err.println("[ERRORE] Impossibile connettersi al database.");
                            System.err.println("Motivo: " + e.getMessage());
                            System.exit(1);
                        }
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
        return false;
    }

    private boolean removeReview(int id) {

        String sql = "SELECT * FROM review WHERE id = ?";
        try(PreparedStatement pstmt = dbConnection.prepareStatement(sql)){
            pstmt.setInt(1, id);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    sql = "DELETE FROM review WHERE id = ?";
                    try(PreparedStatement pstmt2 = dbConnection.prepareStatement(sql)){
                        pstmt2.setInt(1, id);
                        try(ResultSet rs2 = pstmt2.executeQuery()){
                            return true;
                        } catch (SQLException e) {
                            System.err.println("[ERRORE] Impossibile connettersi al database.");
                            System.err.println("Motivo: " + e.getMessage());
                            System.exit(1);
                        }
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
        return false;
    }
}
