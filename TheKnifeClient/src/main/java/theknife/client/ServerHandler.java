package theknife.client;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import simple.crypto.AES;
import simple.socket.SocketUtils;
import theknife.obj.AppPaths;
import theknife.obj.lists.ListCuisines;
import theknife.obj.lists.ListRestaurant;
import theknife.obj.lists.ListReview;
import theknife.obj.lists.ListServices;
import theknife.obj.restaurant.Restaurant;
import theknife.obj.review.Response;
import theknife.obj.review.Review;
import theknife.obj.user.Customer;
import theknife.obj.user.Restaurateur;

/**
 * This class handles the communication with the server
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       760743 (CO)
 */

public class ServerHandler
{
    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The {@link Socket} which communicates with Server 
     */
    private final Socket  socket;
    
    /**
     * The server's address
     */
    private final String  serverAddress   = "127.0.0.1";
    
    /**
     * The server's port
     */
    private final int     serverPort      = 7070;
    
    /**
     * The {@link AES} which encrypt/decrypt data.
     */
    
    private static       AES  aes;
  
    /**
     * KeyStore file for encryption.
     */
    private static final File KEYSTORE_FILE     = AppPaths.getRequiredFile("data", "keystore.jks");
    //</editor-fold>
    
    /**
     * Creates a new {@code ServerHandler}.
     * <p>
     * This constructor initializes the {@code ServerHandler} object's {@link Socket}.
     * </p>
     * @throws IOException 
     */
    public ServerHandler() throws IOException 
    {
      this.socket   = new Socket(serverAddress, serverPort);
      aes           = new AES   (KEYSTORE_FILE);
    }

    /**
     * Send server customer's credentials in order to check them in the database
     * @param customer the credentials of the customer
     * @return all {@link Customer} information
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public Customer         loginCustomer           (Customer customer)         throws IOException, ClassNotFoundException, Exception
    {
      customer.setPassword(aes.encrypt(customer.getPassword()));
      sendData("LOGIN_CUSTOMER");
      sendData(customer);
      return SocketUtils.receive(socket, Customer.class);
    }
    
    /**
     * Send server restaurateur's credentials in order to check them in the database
     * @param restaurateur the credentials of the restaurateur
     * @return all {@link Restaurateur} information
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public Restaurateur     loginRestaurateur       (Restaurateur restaurateur) throws IOException, ClassNotFoundException, Exception
    {
      restaurateur.setPassword(aes.encrypt(restaurateur.getPassword()));
      sendData("LOGIN_RESTAURATEUR");
      ServerHandler.this.sendData(restaurateur);
      return SocketUtils.receive(socket, Restaurateur.class);
    }
    
    /**
     * Send server customer's information in order to insert them in the database
     * @param customer the information of the customer
     * @return boolean, true to confirm registration, false if something went wrong
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public boolean          registerCustomer        (Customer customer)         throws IOException, ClassNotFoundException, Exception
    {
      customer.setPassword(aes.encrypt(customer.getPassword()));
      sendData("REGISTER_CUSTOMER");
      sendData(customer);
      return SocketUtils.receive(socket, Boolean.class);
    }
    
    /**
     * Send server restaurateur's information in order to insert them in the database
     * @param restaurateur the information of the restaurateur
     * @return boolean, true to confirm registration, false if something went wrong
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public boolean          registerRestaurateur    (Restaurateur restaurateur) throws IOException, ClassNotFoundException, Exception
    {
      restaurateur.setPassword(aes.encrypt(restaurateur.getPassword()));
      sendData("REGISTER_RESTAURATEUR");
      sendData(restaurateur);
      return SocketUtils.receive(socket, Boolean.class);
    }
    
    /**
     * Gets all {@link Restaurant} in the database
     * @return the {@link ListRestaurant} retrieved from database
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public ListRestaurant   getRestaurants          ()                          throws IOException, ClassNotFoundException
    {
      sendData("GET_RESTAURANTS");
      return SocketUtils.receive(socket, ListRestaurant.class);
    }
    
    /**
     * Gets all cuisine in the database
     * @return the {@link ListCuisines} retrieved from database
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public ListCuisines     getCuisines             ()                          throws IOException, ClassNotFoundException
    {
      sendData("GET_CUISINES");
      return SocketUtils.receive(socket, ListCuisines.class);
    }
    
    /**
     * Gets all services in the database
     * @return the {@link ListServices} retrieved from database
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public ListServices     getServices             ()                          throws IOException, ClassNotFoundException
    {
      sendData("GET_SERVICES");
      return SocketUtils.receive(socket, ListServices.class);
    }
    
    /**
     * Gets all {@link Review} of a selected restaurant
     * @param id the restaurant's id
     * @return the {@link ListReview} retrieved from database
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public ListReview       getReviews              (int id)                    throws IOException, ClassNotFoundException
    {
      sendData("GET_REVIEWS");
      sendData(id);
      return SocketUtils.receive(socket, ListReview.class);
    }
    
    /**
     * Gets all {@link Review} of a selected {@link Customer}
     * @param id the customer's id
     * @return the {@link ListReview} retrieved from database
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public ListReview       getCustomerReviews      (int id)                    throws IOException, ClassNotFoundException
    {
      sendData("GET_CUSTOMER_REVIEWS");
      sendData(id);
      return SocketUtils.receive(socket, ListReview.class);
    }
    
    /**
     * Add a new {@link Restaurant} inserted by a restaurateur in the database
     * @param restaurant the restaurant to add
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void             addRestaurant           (Restaurant restaurant)     throws IOException, ClassNotFoundException
    {
      sendData("ADD_RESTAURANT");
      sendData(restaurant);
    }
    
    /**
     * Add a new favourite restaurant to customer's list
     * @param favourite customer's id and restaurant's id
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void             addFavourite            (ArrayList<Integer> favourite) throws IOException, ClassNotFoundException
    {
      sendData("ADD_FAVOURITE");
      sendData(favourite);
    }
    
    /**
     * Remove a favourite restaurant to customer's list
     * @param favourite customer's id and restaurant's id
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void             removeFavourite         (ArrayList<Integer> favourite) throws IOException, ClassNotFoundException
    {
      sendData("REMOVE_FAVOURITE");
      sendData(favourite);
    }
    /**
     * Add a new {@link Review} inserted by a customer in the database
     * @param review the review to add
     * @return updated restaurant rating
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public double             addReview               (Review review)             throws IOException, ClassNotFoundException
    {
      sendData("ADD_REVIEW");
      sendData(review);
      return SocketUtils.receive(socket, Double.class);
    }
    
   /**
     * Add a new {@link Response} inserted by a restaurateur in the database
     * @param response the response created by a restaurateur
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void             addResponse             (Response response)             throws IOException, ClassNotFoundException
    {
      sendData("ADD_RESPONSE");
      sendData(response);
    }
    
    /**
     * Edit a {@link Review} inserted by a customer in the database
     * @param review the review to edit
     * @return updated restaurant rating
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public double             editReview              (Review review)             throws IOException, ClassNotFoundException
    {
      sendData("EDIT_REVIEW");
      sendData(review);
      return SocketUtils.receive(socket, Double.class);
    }
    
    /**
     * Removes a {@link Review} inserted by a customer in the database
     * @param review the review to remove
     * @return updated restaurant rating
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public double             removeReview            (Review review)                    throws IOException, ClassNotFoundException
    {
      sendData("REMOVE_REVIEW");
      sendData(review);
      return SocketUtils.receive(socket, Double.class);
    }
    
    /**
     * Returns the {@link Socket}
     *
     * @return the socket
     */
    public Socket getSocket() 
    {
        return socket;
    }
    
    /**
     * Function to close {@link Socket}
     */
    public void closeSocket() 
    {
        try {
            socket.close();
        } catch (IOException ex) {
            System.err.println("Errore nella chiusura del socket: " + ex.getMessage());
        }
        System.out.println("Client closed.");
    }
    
    /**
     * Sends a serializable object to the server via the active socket
     * @param <T>   the type of the object, which must implement {@link Serializable}.
     * @param thing the payload to send to the server.
     */
    public <T extends Serializable> void sendData(T thing) 
    {
        try {
            SocketUtils.send(socket, thing);
        } catch (IOException ex) {
            System.err.println("Errore nell'invio dell'oggetto: " + ex.getMessage());
        }
    }    
}
