package lina.example.survey;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lina on 2016-07-10.
 */
public class WeeklyDeviceSharingQuestions {

    @From
    private String from;

    public WeeklyDeviceSharingQuestions(@From String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    @StringDef({"weeklyDeviceSharingSurveyQuestion1", "weeklyDeviceSharingSurveyQuestion2", "weeklyDeviceSharingSurveyQuestion3"})
    @Retention(RetentionPolicy.SOURCE)
    public @interface From {

        String WEEKLY_DEVICE_SHARING_SURVEY_Q1 = "weeklyDeviceSharingSurveyQuestion1";

        String WEEKLY_DEVICE_SHARING_SURVEY_Q2 = "weeklyDeviceSharingSurveyQuestion2";

        String WEEKLY_DEVICE_SHARING_SURVEY_Q3 = "weeklyDeviceSharingSurveyQuestion3";

    }
}
