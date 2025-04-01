package theknife.obj.review;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public class Review {
    private String content;
    private int grade;
    private Response response;

    public Review() {
    }

    public Review(String content, int grade) {
        setContent(content);
        setGrade(grade);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
