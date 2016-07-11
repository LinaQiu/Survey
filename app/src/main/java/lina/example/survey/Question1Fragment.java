package lina.example.survey;

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
public class Question1Fragment extends BaseFragment {

    @BindView(R.id.Question1)
    TextView tvQuestion1;

    @BindView(R.id.weekly_device_survey_next_button_active)
    Button btnNextActive;

    @BindView(R.id.weekly_device_survey_next_button_inactive)
    Button btnNextInactive;

    @BindView(R.id.weekly_device_survey_back_button)
    Button btnBack;

    @BindView(R.id.Answer1)
    EditText etAnswer1;

    private final static String TAG = Question1Fragment.class.getSimpleName();

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
        observeUserInput();
    }

    public void onResume() {
        super.onResume();
        preFilledUserInput();
    }

    private void preFilledUserInput() {
        String deviceSharingOpinion = DataHelper.getStringAnswers(DataHelper.Key.WEEKLY_DEVICE_SHARING_OPINION);
        etAnswer1.setText(deviceSharingOpinion != null ? deviceSharingOpinion+"" : "");
    }

    private void setQuestion(String question) {
        tvQuestion1.setText(question);
    }

    private void observeUserInput() {
        ObservableHelper.textInputLengthValidity(etAnswer1).subscribe(this::setNextBtnState,
            e -> Log.e(TAG, "validateNextButtonState: ", e));
    }

    private void disableBackBtn() {
        btnBack.setClickable(false);
        btnBack.setVisibility(View.GONE);
    }

    private void setNextBtnState(boolean active) {
        btnNextActive.setVisibility(active ? View.VISIBLE : View.GONE);
        btnNextInactive.setVisibility(active ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.weekly_device_survey_next_button_active)
    public void onNextBtnClick() {
        String userAnswer = etAnswer1.getText().toString();
        Log.i("LinaTest", "userAnswer1: " + userAnswer);
        DataHelper.saveStringAnswers(DataHelper.Key.WEEKLY_DEVICE_SHARING_OPINION, userAnswer);
        changeFragment(Question2Fragment.newInstance());
        //EventBus.getDefault().post(new WeeklyDeviceSharingQuestions(WeeklyDeviceSharingQuestions.From.WEEKLY_DEVICE_SHARING_SURVEY_Q1));
    }
}
