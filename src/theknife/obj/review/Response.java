package theknife.obj.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@JsonPropertyOrder({
    "username", 
    "content", 
    "review"
})
public final class Response {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The username of the response, given by a customer's username or the owner of the restaurant.
     */
    @JsonProperty("username")
    private String username;
    
    /**
     * The content of the response.
     */
    @JsonProperty("content")
    private String content;
    
    /**
     * The {@link Review} object that this response is related to.
     */
    @JsonProperty("review")
    private Review review;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor.
     * <p>
     * Initializes a new {@code Response} object without any attributes set.
     * </p>
     */
    public Response() {
    }
    
    /**
     * Constructor that initializes the {@code Response} object with specific content and associated review.
     *
     * @param username the username of the response
     * @param content the content of the response
     * @param review the review that this response is related to
     */
    public Response(String username, String content, Review review) {
        setUsername(username);
        setContent(content);
        setReview(review);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Returns the username of the response.
     * 
     * @return the username of the response
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
     * Returns the content of the response.
     *
     * @return the content of the response
     */
    public final String getContent() {
        return content;
    }
    
    /**
     * Sets the content of the response.
     *
     * @param content the content to set for the response
     */
    public final void setContent(String content) {
        this.content = content;
    }
    
    /**
     * Returns the review that this response is associated with.
     *
     * @return the review that this response is related to
     */
    public final Review getReview() {
        return review;
    }
    
    /**
     * Sets the review that this response is associated with.
     *
     * @param review the review to associate with the response
     */
    public final void setReview(Review review) {
        this.review = review;
    }
    //</editor-fold>
}
