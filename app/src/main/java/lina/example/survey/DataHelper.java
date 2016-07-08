package lina.example.survey;

import com.google.gson.Gson;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lina on 2016-07-07.
 */
public class DataHelper {

    @StringDef({ "weeklyDeviceSharingOpinion", "weeklyDeviceSharingTimes", "weeklyDeviceSharingPeople"})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Key {

        String WEEKLY_DEVICE_SHARING_Opinion = "weeklyDeviceSharingOpinion";

        String WEEKLY_DEVICE_SHARING_TIMES = "weeklyDeviceSharingTimes";

        String WEEKLY_DEVICE_SHARING_PEOPLE = "weeklyDeviceSharingPeople";
    }
    private static Gson gson() {
        return new Gson();
    }

    public static void saveAnswer(@DataHelper.Key String key, Answer answer) {
        PreferencesHelper.saveData(key, answer);
    }

    public static Answer getAnswer(@DataHelper.Key String key) {
        return (Answer) PreferencesHelper.getObject(key, Answer.class);
    }

    public static void clearAnswers(String ... keys) {
        for (String key : keys) {
            clearAnswers(key);
        }
    }

    public static void saveWeeklyDeviceSharingOpinion(String sharingOpinion) {
        PreferencesHelper.saveData(Key.WEEKLY_DEVICE_SHARING_Opinion, sharingOpinion);
    }

    public static String getWeeklyDeviceSharingOpinion() {
        return PreferencesHelper.getString(Key.WEEKLY_DEVICE_SHARING_Opinion);
    }

    public static void saveWeeklyDeviceSharingTimes(int sharingTimes) {
        PreferencesHelper.saveData(Key.WEEKLY_DEVICE_SHARING_TIMES, sharingTimes);
    }

    public static int getWeeklyDeviceSharingTimes() {
        return PreferencesHelper.getInt(Key.WEEKLY_DEVICE_SHARING_TIMES);
    }

    public static void clearAnswers(@DataHelper.Key String key) {
        PreferencesHelper.clearData(key);
    }

    public static void clearWeeklyDeviceSharingSurveyAnswers() {
        clearAnswers(new String[] {Key.WEEKLY_DEVICE_SHARING_Opinion,
            Key.WEEKLY_DEVICE_SHARING_TIMES,
            Key.WEEKLY_DEVICE_SHARING_PEOPLE});
    }

}
