package theknife.obj.review;

import java.io.Serializable;

/**
 * A class representing a review with content, rating, and an associated response.
 * <p>
 * This class allows you to store a review consisting of a textual content and a numerical rating.<br>
  * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       760743 (CO)
 */
public final class Review implements Serializable
{    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private static final long serialVersionUID = 1L;
    /**
     * The review's id.
     */
    private int             id;
    
    /**
     * The restaurant's id.
     */
    private int             restaurantID;
    
    /**
     * The username of the review, given by a customer's username or the owner of the restaurant.
     */
    private String          username;

    private int customerID;
    
    /**
     * The content of the review, usually a textual description or feedback.
     */
    private String          content;
    
    /**
     * The rating given in the review, typically an integer score.
     */
    private double          rating;
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor.
     * <p>
     * Initializes a new {@code Review} object without any attributes set.
     * </p>
     */
    public Review() {}
    
    /**
     * Constructor that initializes a {@code Review} object with specific content and rating.
     *
     * @param id the id of the review
     * @param restaurantID the id of the restaurant where the review is added
     * @param username the username of the review
     * @param content the content of the review
     * @param rating the rating given in the review
     */
    public Review(int id, int restaurantID, int customerID, String username, String content, double rating)
    {
      setID             (id);
      setRestaurantID   (restaurantID);
      setCustomerID     (customerID);
      setUsername       (username);
      setContent        (content);
      setRating         (rating);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Returns the review's id.
     *
     * @return the review's id
     */
    public final int            getID           ()                          {return id;}
    
    /**
     * Returns the restaurant's id.
     *
     * @return the restaurant's id
     */
    public       int            getRestaurantID ()                          {return restaurantID;}
    
    /**
     * Returns the username of the review.
     * 
     * @return the username of the review
     */
    public       String         getUsername     ()                          {return username;}
    
    /**
     * Returns the content of the review.
     *
     * @return the content of the review
     */
    public final String         getContent      ()                          {return content;}
    
    /**
     * Returns the rating assigned in the review.
     *
     * @return the rating of the review
     */
    public final double         getRating       ()                          {return rating;}

    public final int getcustomerID (){return customerID;};
    /**
     * Sets the review's id.
     *
     * @param id the new id to set
     */
    public final void           setID           (int id)                    {this.id            = id;}

    /**
     * Sets the restaurant's id.
     *
     * @param restaurantID the new restaurant id to set
     */
    public       void           setRestaurantID (int restaurantID)          {this.restaurantID  = restaurantID;}

    /**
     * Sets the username of the review.
     * 
     * @param username the username to set
     */

    public       void           setUsername     (String username)           {this.username      = username;}
    public void setCustomerID (int customerID) {this.customerID = customerID;}
    /**
     * Sets the content of the review.
     *
     * @param content the content to set for the review
     */
    public final void           setContent      (String content)            {this.content       = content;}

    /**
     * Sets the rating for the review.
     *
     * @param rating the rating to assign to the review
     */
    public final void           setRating       (double rating)             {this.rating        = rating;}

    public int getInt() {
        return 0;
    }
    //</editor-fold>
}
