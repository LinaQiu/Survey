package lina.example.survey;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    }
}
