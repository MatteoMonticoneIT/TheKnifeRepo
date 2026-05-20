package theknife.server;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import theknife.obj.lists.ListRestaurant;
import theknife.obj.restaurant.Restaurant;

/**
 *
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public class ClientHandler implements Runnable {
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
    }

    private ListRestaurant getRestaurants() {
        String sql = queryServer.getAllRestaurant();
        ListRestaurant temp = new ListRestaurant();
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            try (rs = pstmt.executeQuery()) {
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

    //TODO funzione getFavorites: ritorna i preferiti dato l'id utente
    //TODO creare getReview, getCuisine, getServices
}