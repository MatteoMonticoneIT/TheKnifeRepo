package theknife.obj.review;

import java.io.Serializable;

/**
 * A class representing a response to a {@link Review}.
 * <p>
 * A response contains the content of the response and the associated review it pertains to.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public final class Response implements Serializable
{   
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private static final long serialVersionUID = 1L;
    /**
     * The response's id.
     */
    private int     id;
    
    /**
     * The review's id.
     */
    private int    reviewID;
    
    /**
     * The restaurateur's id.
     */
    private int    restaurateurID;
    /**
     * The username of the response, given by a customer's username or the owner of the restaurant.
     */
    private String  username;
    
    /**
     * The content of the response.
     */
    private String  content;
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor.
     * <p>
     * Initializes a new {@code Response} object without any attributes set.
     * </p>
     */
    public Response() {}
    
    /**
     * Constructor that initializes the {@code Response} object with specific content and associated review.
     *
     * @param id the id of the response
     * @param reviewID the id of the review where the response is added
     * @param restaurateurID the id of the restaurateur that writes the response
     * @param username the username of the response
     * @param content the content of the response
     */
    public Response(int id, int reviewID, int restaurateurID, String username, String content) 
    {
      setId         (id);
      setReviewID   (reviewID);
      setUsername   (username);
      setContent    (content);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Returns the response's id.
     *
     * @return the response's id
     */
    public final int    getId       ()                  {return id;}
    
    /**
     * Returns the review's id.
     *
     * @return the review's id
     */
    public       int    getReviewID ()                  {return reviewID;}
    
    /**
     * Returns the review's id
     * 
     * @return the restaurateur's id
     */
    public      int     getRestaurateurID   ()          {return restaurateurID;}    
    /**
     * Returns the username of the response.
     * 
     * @return the username of the response
     */
    public       String getUsername ()                  {return username;}
    
    /**
     * Returns the content of the response.
     *
     * @return the content of the response
     */
    public final String getContent  ()                  {return content;}
    
    /**
     * Sets the response's id.
     *
     * @param id the new id to set
     */
    public final void   setId       (int id)            {this.id        = id;}

    /**
     * Sets the review's id.
     *
     * @param reviewID the new review id to set
     */
    public       void   setReviewID (int reviewID)      {this.reviewID  = reviewID;}
    
    /**
     * Sets the restaurateur's id
     * 
     * @param restaurateurID the new restaurateur's id to set
     */
    public       void   setRestaurateurID (int restaurateurID) {this.restaurateurID = restaurateurID;}
    /**
     * Sets the username of the review.
     * 
     * @param username the username to set
     */
    public       void   setUsername (String username)   {this.username  = username;}
     
    /**
     * Sets the content of the response.
     *
     * @param content the content to set for the response
     */
    public final void   setContent  (String content)    {this.content   = content;}
    //</editor-fold>
}
