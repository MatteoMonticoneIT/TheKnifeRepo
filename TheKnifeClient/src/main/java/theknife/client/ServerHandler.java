package theknife.client;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import simple.socket.SocketUtils;
import theknife.obj.lists.ListCuisines;
import theknife.obj.lists.ListFavorite;
import theknife.obj.lists.ListOwned;
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
 * @author Mattia Tamburo       761743 (CO)
 */

public class ServerHandler
{
    private Socket  socket;
    private String  serverAddress   = "127.0.0.1";
    private int     serverPort      = 7070;

    public ServerHandler() throws IOException 
    {
      this.socket = new Socket(serverAddress, serverPort);
    }

    public boolean loginCustomer (Customer customer) throws IOException, ClassNotFoundException
    {
      sendCmd("LOGIN_CUSTOMER");
      sendData(customer);
      return SocketUtils.receive(socket, boolean.class);
    }
    
    public boolean loginRestaurateur (Restaurateur restaurateur) throws IOException, ClassNotFoundException
    {
      sendCmd("LOGIN_RESTAURATEUR");
      sendData(restaurateur);
      return SocketUtils.receive(socket, boolean.class);
    }
    
    public boolean registerCustomer (Customer customer) throws IOException, ClassNotFoundException
    {
      sendCmd("REGISTER_CUSTOMER");
      sendData(customer);
      return SocketUtils.receive(socket, boolean.class);
    }
    
    public boolean registerRestaurateur (Restaurateur restaurateur) throws IOException, ClassNotFoundException
    {
      sendCmd("REGISTER_RESTAURATEUR");
      sendData(restaurateur);
      return SocketUtils.receive(socket, boolean.class);
    }
    
    public ListRestaurant getRestaurants() throws IOException, ClassNotFoundException
    {
      sendCmd("GET_RESTAURANTS");
      ListRestaurant l = SocketUtils.receive(socket, ListRestaurant.class);
      for(int i=0; i<5; i++)
        System.out.println(l.getList().get(i).getName());
      return l;
    }
    
    public ListCuisines getCuisines() throws IOException, ClassNotFoundException
    {
      sendCmd("GET_CUISINES");
      return SocketUtils.receive(socket, ListCuisines.class);
    }
    
    public ListServices getServices() throws IOException, ClassNotFoundException
    {
      sendCmd("GET_CUISINES");
      return SocketUtils.receive(socket, ListServices.class);
    }
    
    public ListReview getReviews() throws IOException, ClassNotFoundException
    {
      sendCmd("GET_REVIEWS");
      return SocketUtils.receive(socket, ListReview.class);
    }
    
    public ListFavorite getFavorite() throws IOException, ClassNotFoundException
    {
      sendCmd("GET_REVIEWS");
      return SocketUtils.receive(socket, ListFavorite.class);
    }
    
    public ListOwned getOwned() throws IOException, ClassNotFoundException
    {
      sendCmd("GET_REVIEWS");
      return SocketUtils.receive(socket, ListOwned.class);
    }
    
    public void addRestaurant(Restaurant restaurant) throws IOException, ClassNotFoundException
    {
      sendCmd("ADD_RESTAURANT");
      sendData(restaurant);
    }
    
    public void addReview(Review review) throws IOException, ClassNotFoundException
    {
      sendCmd("ADD_REVIEW");
      sendData(review);
    }
    
    public void editReview(Review review) throws IOException, ClassNotFoundException
    {
      sendCmd("EDIT_REVIEW");
      sendData(review);
    }
    
    public void removeReview(Review review) throws IOException, ClassNotFoundException
    {
      sendCmd("REMOVE_REVIEW");
      sendData(review);
    }
    
    public Socket getSocket() 
    {
        return socket;
    }
    
    public void closeSocket() 
    {
        try {
            socket.close();
        } catch (IOException ex) {
            System.err.println("Errore nella chiusura del socket: " + ex.getMessage());
        }
        System.out.println("Client closed.");
    }
    
    public void sendCmd(String cmd) 
    {
        try {
            SocketUtils.send(socket, cmd);
        } catch (IOException ex) {
            System.err.println("Errore nell'invio del comando: " + ex.getMessage());
        }
    }
    
    public <T extends Serializable> void sendData(T thing) 
    {
        try {
            SocketUtils.send(socket, thing);
        } catch (IOException ex) {
            System.err.println("Errore nell'invio dell'oggetto: " + ex.getMessage());
        }
    }    
}
