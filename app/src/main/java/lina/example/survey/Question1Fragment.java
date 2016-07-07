package lina.example.survey;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lina on 2016-07-06.
 */
public class Question1Fragment extends Fragment {

    @BindView(R.id.Question1)
    TextView tvQuestion1;

    @BindView(R.id.weekly_device_survey_next_button_active)
    Button btnNextActive;

    @BindView(R.id.Answer1)
    EditText etAnswer1;

    public static final Question1Fragment newInstance() {
        Bundle args =  new Bundle();
        Question1Fragment fragment = new Question1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View question1 = inflater.inflate(R.layout.fragment_question1, container, false);
        ButterKnife.bind(this, question1);
        tvQuestion1.setText(getString(R.string.DeviceSharingQuestion1));
        return question1;
    }

    @OnClick(R.id.weekly_device_survey_next_button_active)
    public void onNextBtnClick() {
        String userAnswer = etAnswer1.getText().toString();
        Log.i("LinaTest", "userAnswer1: " + userAnswer);
        changeFragment();
        EventBus.getDefault()
            .post(new WeeklyDeviceSharingSurveyQuestions(WeeklyDeviceSharingSurveyQuestions.From.WEEKLY_DEVICE_SHARING_SURVEY_QUESTION1));
    }

    public void changeFragment() {
        Fragment fragment = Question2Fragment.newInstance();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (transaction.isAddToBackStackAllowed()){
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}
