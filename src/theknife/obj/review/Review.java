package theknife.obj.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import theknife.obj.lists.ListResponse;

/**
 * A class representing a review with content, grade, and an associated response.
 * <p>
 * This class allows you to store a review consisting of a textual content and a numerical grade.<br>
 * It also stores a {@link Response} to the review.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
@JsonPropertyOrder({
    "username", 
    "grade", 
    "content", 
    "responses"
})
public final class Review {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
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
     * The grade given in the review, typically an integer score.
     */
    @JsonProperty("grade")
    private int grade;
    
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
     * Constructor that initializes a {@code Review} object with specific content and grade.
     *
     * @param username the username of the review
     * @param content the content of the review
     * @param grade the grade given in the review
     */
    public Review(String username, String content, int grade) {
        setUsername(username);
        setContent(content);
        setGrade(grade);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
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
     * Returns the grade assigned in the review.
     *
     * @return the grade of the review
     */
    public final int getGrade() {
        return grade;
    }
    
    /**
     * Sets the grade for the review.
     *
     * @param grade the grade to assign to the review
     */
    public final void setGrade(int grade) {
        this.grade = grade;
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
