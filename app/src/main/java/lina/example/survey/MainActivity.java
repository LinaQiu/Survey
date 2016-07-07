package lina.example.survey;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private MyPagerAdapter mMyPagerAdapter;

    private ViewPager mViewPager;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        // Create the adapter that will return a fragment for each of the questions
        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mMyPagerAdapter);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.circle_indicator);
        indicator.setViewPager(mViewPager);
    }

    // Extend from SmartFragmentStatePagerAdapter now instead for more dynamic ViewPager items
    public static class MyPagerAdapter extends SmartFragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Question1Fragment
                    return Question1Fragment.newInstance();
                case 1: // Question2Fragment
                    return Question2Fragment.newInstance();
                case 2: // Question3Fragment
                    return Question3Fragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    @Subscribe
    public void onNextBtnClick(WeeklyDeviceSharingSurveyQuestions weeklyDeviceSharingSurveyQuestions) {
        switch (weeklyDeviceSharingSurveyQuestions.getFrom()) {
            case WeeklyDeviceSharingSurveyQuestions.From.WEEKLY_DEVICE_SHARING_SURVEY_QUESTION1:
                changeFragment(WeeklyDeviceSharingSurveyQuestions.From.WEEKLY_DEVICE_SHARING_SURVEY_QUESTION1);
                break;
            case WeeklyDeviceSharingSurveyQuestions.From.WEEKLY_DEVICE_SHARING_SURVEY_QUESTION2:
                changeFragment(WeeklyDeviceSharingSurveyQuestions.From.WEEKLY_DEVICE_SHARING_SURVEY_QUESTION2);
                break;
        }
    }

    public void changeFragment(String fromQuestion) {
        Fragment fragment = null;
        switch (fromQuestion){
            case WeeklyDeviceSharingSurveyQuestions.From.WEEKLY_DEVICE_SHARING_SURVEY_QUESTION1:
                fragment = new Question2Fragment();
                break;
            case WeeklyDeviceSharingSurveyQuestions.From.WEEKLY_DEVICE_SHARING_SURVEY_QUESTION2:
                fragment = new Question3Fragment();
                break;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (transaction.isAddToBackStackAllowed()){
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}
