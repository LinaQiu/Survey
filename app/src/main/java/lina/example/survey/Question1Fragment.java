package lina.example.survey;

import org.greenrobot.eventbus.EventBus;

import android.app.Activity;
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
public class Question1Fragment extends BaseFragment {

    @BindView(R.id.Question1)
    TextView tvQuestion1;

    @BindView(R.id.weekly_device_survey_next_button_active)
    Button btnNextActive;

    @BindView(R.id.weekly_device_survey_back_button)
    Button btnBack;

    @BindView(R.id.Answer1)
    EditText etAnswer1;

    public static final Question1Fragment newInstance() {
        Bundle args =  new Bundle();
        Question1Fragment fragment = new Question1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_question1;
    }

    @Override
    public void setup(){
        setQuestion(getString(R.string.DeviceSharingQuestion1));
        disableBackBtn();
    }

    public void setQuestion(String question) {
        tvQuestion1.setText(question);
    }

    public void disableBackBtn() {
        btnBack.setClickable(false);
        btnBack.setVisibility(View.GONE);
    }

    @OnClick(R.id.weekly_device_survey_next_button_active)
    public void onNextBtnClick() {
        String userAnswer = etAnswer1.getText().toString();
        Log.i("LinaTest", "userAnswer1: " + userAnswer);
        changeFragment(Question2Fragment.newInstance());
    }
}
