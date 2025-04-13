package theknife.obj.review;

import theknife.obj.lists.ListResponse;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public final class Review {
    private String content;
    private int grade;
    private ListResponse listResponse;

    public Review() {
    }

    public Review(String content, int grade) {
        setContent(content);
        setGrade(grade);
    }

    public final String getContent() {
        return content;
    }

    public final void setContent(String content) {
        this.content = content;
    }

    public final int getGrade() {
        return grade;
    }

    public final void setGrade(int grade) {
        this.grade = grade;
    }

    public final ListResponse getListResponse() {
        return listResponse;
    }

    public final void setListResponse(ListResponse listResponse) {
        this.listResponse = listResponse;
    }
    
}
