package lina.example.survey;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

/**
 * Created by lina on 2016-07-07.
 */
public class PreferencesHelper {

    private static final String USAGE_PATTERN_STUDY_PREFERENCES = "UsagePatternStudyPref";

    private static SharedPreferences sharedPreferences;

    private static SharedPreferences.Editor editor;

    private static Context getContext() {
        return Contextor.getInstance().getContext();
    }

    public static void init() {
        sharedPreferences = getContext().getSharedPreferences(USAGE_PATTERN_STUDY_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void saveData(@DataHelper.Key String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public static void saveData(@DataHelper.Key String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public static void saveData(@DataHelper.Key String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void saveData(@DataHelper.Key String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public static <T> void saveData(@DataHelper.Key String key, List<T> list) {
        editor.putString(key, gson().toJson(list));
        editor.commit();
    }

    public static <T> void saveData(@DataHelper.Key String key, T obj) {
        editor.putString(key, gson().toJson(obj));
        editor.commit();
    }

    public static String getString(@DataHelper.Key String key) {
        return sharedPreferences.getString(key, null);
    }

    public static long getLong(@DataHelper.Key String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public static boolean getBoolean(@DataHelper.Key String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public static int getInt(@DataHelper.Key String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public static Object getObject(@DataHelper.Key String key, Class clz) {
        return gson().fromJson(getString(key), clz);
    }

    public static void clearData(@DataHelper.Key String key) {
        editor.remove(key);
        editor.commit();
    }

    public static void clearData() {
        editor.clear();
        editor.commit();
    }

    private static Gson gson() {
        return new Gson();
    }

}
