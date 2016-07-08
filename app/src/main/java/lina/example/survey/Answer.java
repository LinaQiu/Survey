package lina.example.survey;

import java.util.List;

/**
 * Created by lina on 2016-07-07.
 */
public class Answer {

    private List<Integer> viewId;

    public Answer(List<Integer> viewId) {
        this.viewId = viewId;
    }

    public void setViewId(List<Integer> viewId) {
        this.viewId = viewId;
    }

    public List<Integer> getViewId() {
        return viewId;
    }

}
