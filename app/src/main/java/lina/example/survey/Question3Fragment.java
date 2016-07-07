package lina.example.survey;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lina on 2016-07-06.
 */
public class Question3Fragment extends BaseFragment {

    @BindView(R.id.Question3)
    TextView tvQuestion3;

    @BindView(R.id.weekly_device_survey_next_button_active)
    Button btnNextActive;

    public static final Question3Fragment newInstance() {
        Bundle args =  new Bundle();
        Question3Fragment fragment = new Question3Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_question3;
    }

    @Override
    public void setup(){
        setQuestion(getString(R.string.DeviceSharingQuestion3));
    }

    public void setQuestion(String question) {
        tvQuestion3.setText(question);
    }

    @OnClick(R.id.weekly_device_survey_next_button_active)
    public void onNextBtnClick(){
        changeFragment(SubmitFragment.newInstance());
    }
}
