package lina.example.survey;

import android.content.Context;

/**
 * Created by lina on 2016-07-07.
 */
public class Contextor {

    private static Contextor instance;

    private Context context;

    public static Contextor getInstance() {
        if (null == instance) {
            instance = new Contextor();
        }

        return instance;
    }

    public void init(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
