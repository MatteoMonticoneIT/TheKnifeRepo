package theknife.obj.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import theknife.obj.lists.ListResponse;

/**
 * A class representing a review with content, rating, and an associated response.
 * <p>
 * This class allows you to store a review consisting of a textual content and a numerical rating.<br>
 * It also stores a {@link Response} to the review.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
@JsonPropertyOrder({
    "ID",
    "restaurantID",
    "username", 
    "rating", 
    "content", 
    "responses"
})
public final class Review {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The review's id.
     */
    @JsonProperty("ID")
    private int id;
    
    /**
     * The restaurant's id.
     */
    @JsonProperty("restaurantID")
    private int restaurantID;
    
    /**
     * The username of the review, given by a customer's username or the owner of the restaurant.
     */
    @JsonProperty("username")
    private String username;
    
    /**
     * The content of the review, usually a textual description or feedback.
     */
    @JsonProperty("content")
    private String content;
    
    /**
     * The rating given in the review, typically an integer score.
     */
    @JsonProperty("rating")
    private double rating;
    
    /**
     * The responses to the review, typically an instance of {@link ListResponse}.
     */
    @JsonProperty("responses")
    private ListResponse responses;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor.
     * <p>
     * Initializes a new {@code Review} object without any attributes set.
     * </p>
     */
    public Review() {
    }
    
    /**
     * Constructor that initializes a {@code Review} object with specific content and rating.
     *
     * @param id the id of the review
     * @param restaurantID the id of the restaurant where the review is added
     * @param username the username of the review
     * @param content the content of the review
     * @param rating the rating given in the review
     */
    public Review(int id, int restaurantID, String username, String content, double rating) {
        setId(id);
        setRestaurantID(restaurantID);
        setUsername(username);
        setContent(content);
        setRating(rating);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Returns the review's id.
     *
     * @return the review's id
     */
    public final int getId() {
        return id;
    }
    
    /**
     * Sets the review's id.
     *
     * @param id the new id to set
     */
    public final void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the restaurant's id.
     *
     * @return the restaurant's id
     */
    public int getRestaurantID() {
        return restaurantID;
    }
    
    /**
     * Sets the restaurant's id.
     *
     * @param restaurantID the new restaurant id to set
     */
    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }
    
    /**
     * Returns the username of the review.
     * 
     * @return the username of the review
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the review.
     * 
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Returns the content of the review.
     *
     * @return the content of the review
     */
    public final String getContent() {
        return content;
    }
    
    /**
     * Sets the content of the review.
     *
     * @param content the content to set for the review
     */
    public final void setContent(String content) {
        this.content = content;
    }
    
    /**
     * Returns the rating assigned in the review.
     *
     * @return the rating of the review
     */
    public final double getRating() {
        return rating;
    }
    
    /**
     * Sets the rating for the review.
     *
     * @param rating the rating to assign to the review
     */
    public final void setRating(double rating) {
        this.rating = rating;
    }
    
    /**
     * Returns the response to the review.
     *
     * @return the response to the review
     */
    public final ListResponse getResponses() {
        return responses;
    }
    
    /**
     * Sets the response to the review.
     *
     * @param responses the {@code ListResponse} to assign to the review
     */
    public final void setResponses(ListResponse responses) {
        this.responses = responses;
    }
    //</editor-fold>
}
