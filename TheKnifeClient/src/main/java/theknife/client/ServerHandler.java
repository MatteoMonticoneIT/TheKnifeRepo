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
import theknife.obj.user.User;

/**
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       760743 (CO)
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

    public Customer loginCustomer (Customer customer) throws IOException, ClassNotFoundException
    {
      ServerHandler.this.sendData("LOGIN_CUSTOMER");
      ServerHandler.this.sendData(customer);
      return SocketUtils.receive(socket, Customer.class);
    }

    public Customer loginCustomerEmail (Customer customer) throws IOException, ClassNotFoundException
    {
        sendData("LOGIN_CUSTOMER_EMAIL");
        ServerHandler.this.sendData(customer);
        return SocketUtils.receive(socket, Customer.class);
    }
    public Restaurateur loginRestaurateur (Restaurateur restaurateur) throws IOException, ClassNotFoundException
    {
      sendData("LOGIN_RESTAURATEUR");
      ServerHandler.this.sendData(restaurateur);
      return SocketUtils.receive(socket, Restaurateur.class);
    }

    public Restaurateur loginRestaurateurEmail (Restaurateur restaurateur) throws IOException, ClassNotFoundException
    {
        sendData("LOGIN_RESTAURATEUR_EMAIL");
        ServerHandler.this.sendData(restaurateur);
        return SocketUtils.receive(socket, Restaurateur.class);
    }
    
    public boolean registerCustomer (Customer customer) throws IOException, ClassNotFoundException
    {
      sendData("REGISTER_CUSTOMER");
      ServerHandler.this.sendData(customer);
      return SocketUtils.receive(socket, boolean.class);
    }
    
    public boolean registerRestaurateur (Restaurateur restaurateur) throws IOException, ClassNotFoundException
    {
      sendData("REGISTER_RESTAURATEUR");
      ServerHandler.this.sendData(restaurateur);
      return SocketUtils.receive(socket, boolean.class);
    }
    
    public ListRestaurant getRestaurants() throws IOException, ClassNotFoundException
    {
      sendData("GET_RESTAURANTS");
      ListRestaurant l = SocketUtils.receive(socket, ListRestaurant.class);
      for(int i=0; i<5; i++)
        System.out.println(l.getList().get(i).getName());
      return l;
    }
    
    public ListCuisines getCuisines() throws IOException, ClassNotFoundException
    {
      sendData("GET_CUISINES");
      return SocketUtils.receive(socket, ListCuisines.class);
    }
    
    public ListServices getServices() throws IOException, ClassNotFoundException
    {
      sendData("GET_SERVICES");
      return SocketUtils.receive(socket, ListServices.class);
    }
    
    public ListReview getReviews(int id) throws IOException, ClassNotFoundException
    {
      sendData("GET_REVIEWS");
      ServerHandler.this.sendData(id);
      return SocketUtils.receive(socket, ListReview.class);
    }
    
    public ListFavorite getFavorite() throws IOException, ClassNotFoundException
    {
      sendData("GET_REVIEWS");
      return SocketUtils.receive(socket, ListFavorite.class);
    }
    
    public ListOwned getOwned() throws IOException, ClassNotFoundException
    {
      sendData("GET_REVIEWS");
      return SocketUtils.receive(socket, ListOwned.class);
    }
    
    public void addRestaurant(Restaurant restaurant) throws IOException, ClassNotFoundException
    {
      sendData("ADD_RESTAURANT");
      ServerHandler.this.sendData(restaurant);
    }
    
    public void addReview(Review review) throws IOException, ClassNotFoundException
    {
      sendData("ADD_REVIEW");
      ServerHandler.this.sendData(review);
    }
    
    public void editReview(Review review) throws IOException, ClassNotFoundException
    {
      sendData("EDIT_REVIEW");
      ServerHandler.this.sendData(review);
    }
    
    public void removeReview(Review review) throws IOException, ClassNotFoundException
    {
      sendData("REMOVE_REVIEW");
      ServerHandler.this.sendData(review);
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
    
    public <T extends Serializable> void sendData(T thing) 
    {
        try {
            SocketUtils.send(socket, thing);
        } catch (IOException ex) {
            System.err.println("Errore nell'invio dell'oggetto: " + ex.getMessage());
        }
    }    
}
