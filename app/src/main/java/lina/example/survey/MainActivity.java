package lina.example.survey;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null) {
                return;
            }
            getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, Question1Fragment.newInstance()).commit();
        }

        ButterKnife.bind(this);
        initContextor();
        initPreferencesHelper();
    }

    private void initContextor() {
        Contextor.getInstance().init(getApplicationContext());
    }

    private void initPreferencesHelper() {
        PreferencesHelper.init();
    }
}
