package lina.example.survey;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    @BindView(R.id.weekly_device_survey_next_button_inactive)
    Button btnNextInactive;

    @BindView(R.id.Answer2)
    EditText etAnswer2;

    private final static String TAG = Question2Fragment.class.getSimpleName();

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
        observeUserInput();
    }

    public void onResume() {
        super.onResume();
        preFilledUserInput();
    }

    private void preFilledUserInput() {
        int deviceSharingTimes = DataHelper.getWeeklyDeviceSharingTimes();
        etAnswer2.setText(deviceSharingTimes >= 0 ? deviceSharingTimes+"" : "");
    }

    public void setQuestion(String question) {
        tvQuestion2.setText(question);
    }

    private void observeUserInput() {
        ObservableHelper.textInputLengthValidity(etAnswer2).subscribe(this::setNextBtnState,
            e -> Log.e(TAG, "validateNextButtonState: ", e));
    }

    private void setNextBtnState(boolean active) {
        btnNextActive.setVisibility(active ? View.VISIBLE : View.GONE);
        btnNextInactive.setVisibility(active ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.weekly_device_survey_next_button_active)
    public void onNextBtnClick() {
        String userAnswer = etAnswer2.getText().toString();
        Log.i("LinaTest", "userAnswer2: " + userAnswer);
        DataHelper.saveWeeklyDeviceSharingTimes(Integer.parseInt(userAnswer));

        if (userAnswer.equals("0")) {
            changeFragment(SubmitFragment.newInstance());
        }else {
            changeFragment(Question3Fragment.newInstance());
        }

        //EventBus.getDefault().post(new WeeklyDeviceSharingQuestions(WeeklyDeviceSharingQuestions.From.WEEKLY_DEVICE_SHARING_SURVEY_Q2));
    }
}
