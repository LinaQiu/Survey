package lina.example.survey;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lina on 2016-07-06.
 */
public class Question2Fragment extends BaseFragment {

    @BindView(R.id.Question2)
    TextView tvQuestion2;

    @BindView(R.id.weekly_device_survey_next_button_active)
    Button btnNextActive;

    @BindView(R.id.Answer2)
    EditText etAnswer2;

    public static final Question2Fragment newInstance() {
        Bundle args =  new Bundle();
        Question2Fragment fragment = new Question2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_question2;
    }

    @Override
    public void setup() {
        setQuestion(getString(R.string.DeviceSharingQuestion2_head)+" Wednesday, "+getString(R.string.DeviceSharingQuestion2_tail));
    }

    public void setQuestion(String question) {
        tvQuestion2.setText(question);
    }

    @OnClick(R.id.weekly_device_survey_next_button_active)
    public void onNextBtnClick() {
        String userAnswer = etAnswer2.getText().toString();
        Log.i("LinaTest", "userAnswer2: " + userAnswer);
        if (userAnswer.equals("0")) {
            changeFragment(SubmitFragment.newInstance());
        }else {
            changeFragment(Question3Fragment.newInstance());
        }
    }
}
