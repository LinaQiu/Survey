package lina.example.survey;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lina on 2016-07-05.
 */
public class WeeklyDeviceSharingSurveyQuestions {

    @From
    private String from;

    public WeeklyDeviceSharingSurveyQuestions(@From String from) {
        this.from = from;
    }

    public String getFrom() {return from; }

    @StringDef({"weeklySurveyQ1", "weeklySurveyQ2", "weeklySurveyQ3"})
    @Retention(RetentionPolicy.SOURCE)
    public @interface From {
        String WEEKLY_DEVICE_SHARING_SURVEY_QUESTION1 = "weeklySurveyQ1";
        String WEEKLY_DEVICE_SHARING_SURVEY_QUESTION2 = "weeklySurveyQ2";
        String WEEKLY_DEVICE_SHARING_SURVEY_QUESTION3 = "weeklySurveyQ3";
    }
}
