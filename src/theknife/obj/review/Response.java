package theknife.obj.review;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public final class Response {
    private String content;
    private Review review;

    public Response() {
    }

    public Response(String content, Review review) {
        setContent(content);
        setReview(review);
    }

    public final String getContent() {
        return content;
    }

    public final void setContent(String content) {
        this.content = content;
    }

    public final Review getReview() {
        return review;
    }

    public final void setReview(Review review) {
        this.review = review;
    }
    
}
