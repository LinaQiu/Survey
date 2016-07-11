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

   /*
    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setup() {
        initContextor();
        initPreferencesHelper();
        showWeeklyDeviceSharingQuestion1();
    }

    @Override
    public void showWeeklyDeviceSharingQuestion1() {
        fragmentLoader.loadFragment(Question1Fragment.newInstance(), R.id.fragment_container);
    }

    @Override
    public void showWeeklyDeviceSharingQuestion2() {
        fragmentLoader.loadFragment(Question2Fragment.newInstance(), R.id.fragment_container);
    }

    @Override
    public void showWeeklyDeviceSharingQuestion3() {
        fragmentLoader.loadFragment(Question3Fragment.newInstance(), R.id.fragment_container);
    }

    @Override
    public void showSubmitPage() {
        fragmentLoader.loadFragment(SubmitFragment.newInstance(), R.id.fragment_container);
    }

    @Subscribe
    public void onNextBtnClick(WeeklyDeviceSharingQuestions weeklyDeviceSharingQuestions) {
        switch (weeklyDeviceSharingQuestions.getFrom()){
            case WeeklyDeviceSharingQuestions.From.WEEKLY_DEVICE_SHARING_SURVEY_Q1:
                showWeeklyDeviceSharingQuestion2();
                break;
            case WeeklyDeviceSharingQuestions.From.WEEKLY_DEVICE_SHARING_SURVEY_Q2:
                showWeeklyDeviceSharingQuestion3();
                break;
            case WeeklyDeviceSharingQuestions.From.WEEKLY_DEVICE_SHARING_SURVEY_Q3:
                showSubmitPage();
                break;
        }
    }
    */
}
