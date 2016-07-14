package lina.example.survey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lina on 2016-07-11.
 */
public class ChooseDeviceSharingSurveyPopUpDay extends AppCompatActivity {

    @BindView(R.id.item_answer_container)
    LinearLayout layoutAnswers;

    @BindView(R.id.btn_next_active)
    Button btnNextActive;

    @BindView(R.id.btn_next_inactive)
    Button btnNextInactive;

    private AnswersRadioButtonUtil answersRadioButtonUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_device_sharing_survey_pop_up_day);

        ButterKnife.bind(this);
        initContextor();
        initPreferencesHelper();

        initAnswersRadioButtonUtil();
        setAnswers();
        observeUserInput();
    }

    private void initContextor() {
        Contextor.getInstance().init(getApplicationContext());
    }

    private void initPreferencesHelper() {
        PreferencesHelper.init();
    }

    private void initAnswersRadioButtonUtil() {
        answersRadioButtonUtil = new AnswersRadioButtonUtil();
    }

    private void setAnswers() {
        answersRadioButtonUtil.addRadioButtons(getApplicationContext(), layoutAnswers,
            getResources().getStringArray(R.array.weekday));
        answersRadioButtonUtil.preFilledRadioButton(layoutAnswers, DataHelper.Key.WEEKDAY_ID);
    }

    private void observeUserInput() {
        ObservableHelper.radioGroupValidity(answersRadioButtonUtil.getRadioGroup())
            .subscribe(this::setSubmitBtnState, e -> Log.e("LinaTest: ", "validateSubmitButtonState: ", e));
    }

    private void setSubmitBtnState(boolean active) {
        btnNextActive.setVisibility(active ? View.VISIBLE : View.GONE);
        btnNextInactive.setVisibility(active ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.btn_next_active)
    public void onSubmitBtnClick() {
        answersRadioButtonUtil.saveSelectedValue(layoutAnswers, DataHelper.Key.WEEKDAY_ID);
        answersRadioButtonUtil.saveRadioStrings(layoutAnswers, DataHelper.Key.WEEKDAY_STRING);
        Log.e("LinaTest: ", DataHelper.getStringAnswers(DataHelper.Key.WEEKDAY_STRING));
        DataHelper.clearWeekdayChoice();
    }
}
