package theknife.obj.review;

/**
 * A class representing a response to a {@link Review}.
 * <p>
 * A response contains the content of the response and the associated review it pertains to.
 * </p>
 * 
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public final class Response {
    
    /**
     * The content of the response.
     */
    private String content;
    
    /**
     * The {@link Review} object that this response is related to.
     */
    private Review review;

    /**
     * Default constructor.
     * <p>
     * Initializes a new empty response without content or associated review.
     * </p>
     */
    public Response() {
    }

    /**
     * Constructor that initializes the response with specific content and associated review.
     *
     * @param content the content of the response
     * @param review the review that this response is related to
     */
    public Response(String content, Review review) {
        setContent(content);
        setReview(review);
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
    
}
