package theknife.obj.review;

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

public final class Review {
    
    /**
     * The content of the review, usually a textual description or feedback.
     */
    private String content;
    
    /**
     * The grade given in the review, typically an integer score.
     */
    private int grade;
    
    /**
     * The response to the review, typically an instance of {@link Response}.
     */
    private Response response;

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
     * @param content the content of the review
     * @param grade the grade given in the review
     */
    public Review(String content, int grade) {
        setContent(content);
        setGrade(grade);
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
    public final Response getResponse() {
        return response;
    }

    /**
     * Sets the response to the review.
     *
     * @param response the response to assign to the review
     */
    public final void setResponse(Response response) {
        this.response = response;
    }
    
}
