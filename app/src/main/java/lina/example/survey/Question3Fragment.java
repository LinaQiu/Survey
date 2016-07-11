package lina.example.survey;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observer;
import rx.subjects.BehaviorSubject;

/**
 * Created by lina on 2016-07-06.
 */
public class Question3Fragment extends BaseFragment {

    @BindView(R.id.Question3)
    TextView tvQuestion3;

    @BindView(R.id.weekly_device_survey_next_button_active)
    Button btnNextActive;

    @BindView(R.id.weekly_device_survey_next_button_inactive)
    Button btnNextInactive;

    /*
    @BindView(R.id.cb_device_sharing_q3_a)
    CheckBox cbDeviceSharingQ3_a;

    @BindView(R.id.cb_device_sharing_q3_b)
    CheckBox cbDeviceSharingQ3_b;

    @BindView(R.id.cb_device_sharing_q3_c)
    CheckBox cbDeviceSharingQ3_c;

    @BindView(R.id.cb_device_sharing_q3_d)
    CheckBox cbDeviceSharingQ4_d;

    @BindView(R.id.cb_device_sharing_q3_e)
    CheckBox cbDeviceSharingQ3_e;
    */

    @BindView(R.id.et_please_specify)
    EditText etPleaseSpecify;

    @BindView(R.id.item_answer_container)
    LinearLayout llCheckboxes;

    private AnswersCheckboxUtil answersCheckboxUtil;

    private static final String TAG = Question3Fragment.class.getSimpleName();

    private static int checkedboxNum = 0;

    private BehaviorSubject<Integer> totalCheckedboxNum = BehaviorSubject.create(0);

    public static final Question3Fragment newInstance() {
        Bundle args =  new Bundle();
        Question3Fragment fragment = new Question3Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_question3_dynamical;
    }

    @Override
    public void setup(){
        initAnswersCheckboxUtil();
        setQuestion(getString(R.string.DeviceSharingQuestion3));
        setAnswers();
        observeAllCheckboxes(llCheckboxes);
    }

    @Override
    public void onResume(){
        super.onResume();
        preFilledUserInput();
    }

    private void initAnswersCheckboxUtil() {
        answersCheckboxUtil = new AnswersCheckboxUtil();
    }

    private void setQuestion(String question) {
        tvQuestion3.setText(question);
    }

    private void setAnswers() {
        answersCheckboxUtil.addCheckboxes(getContext(), llCheckboxes,
            getResources().getStringArray(R.array.DeviceSharing_Q3_Choices));
        answersCheckboxUtil.preFilledCheckboxes(llCheckboxes,
            DataHelper.Key.WEEKLY_DEVICE_SHARING_CHECKBOX_ID);
    }

    private void preFilledUserInput() {
        String deviceSharingSpecifyPeople = DataHelper.getStringAnswers(DataHelper.Key.WEEKLY_DEVICE_SHARING_SPECIFY_PEOPLE);
        etPleaseSpecify.setText(deviceSharingSpecifyPeople);
    }

    private void observeAllCheckboxes(ViewGroup parent){
        for (int i=0; i<parent.getChildCount();i++){
            View child = parent.getChildAt(i);
            CheckBox checkBox = (CheckBox) child;
            if (i<parent.getChildCount()-1){
                ObservableHelper.checkedboxValidity(checkBox).subscribe(this::countCheckedbox,
                    e -> Log.e(TAG, "validateNextButtonState: ", e));
            }else {
                ObservableHelper.checkedboxValidity(checkBox).subscribe(this::enablePleaseSpecifyEditText,
                    e -> Log.e(TAG, "validateNextButtonState: ", e));
                ObservableHelper.textInputLengthValidity(etPleaseSpecify).subscribe(this::countCheckedbox,
                    e -> Log.e(TAG, "validateNextButtonState: ", e));
            }
        }
        subscribeTotalCheckedboxNum();
    }

    private void subscribeTotalCheckedboxNum() {
        totalCheckedboxNum.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "Completed");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Integer totalCheckedboxNum) {
                setNextBtnState(totalCheckedboxNum);
            }
        });
    }

    private void countCheckedbox(boolean active){
        if (active) {
            checkedboxNum++;
            totalCheckedboxNum.onNext(checkedboxNum);
        }else {
            if (checkedboxNum > 0) {
                checkedboxNum--;
                totalCheckedboxNum.onNext(checkedboxNum);
            }
        }
    }

    private void enablePleaseSpecifyEditText(boolean active) {
        etPleaseSpecify.setEnabled(active ? true : false);
    }

    private void setNextBtnState(int totalCheckedboxNum) {
        btnNextActive.setVisibility(totalCheckedboxNum > 0 ? View.VISIBLE : View.GONE);
        btnNextInactive.setVisibility(totalCheckedboxNum > 0 ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.weekly_device_survey_next_button_active)
    public void onNextBtnClick(){
        //Log.e(TAG, getAllCheckboxesAnswer());
        //DataHelper.saveWeeklyDeviceSharingPeople(getAllCheckboxesAnswer());
        answersCheckboxUtil.saveCheckedboxesIds(llCheckboxes, DataHelper.Key.WEEKLY_DEVICE_SHARING_CHECKBOX_ID);
        answersCheckboxUtil.saveCheckedboxStrings(llCheckboxes, DataHelper.Key.WEEKLY_DEVICE_SHARING_CHECKBOX_STRING);
        DataHelper.saveStringAnswers(DataHelper.Key.WEEKLY_DEVICE_SHARING_SPECIFY_PEOPLE, etPleaseSpecify.getText().toString());
        changeFragment(SubmitFragment.newInstance());
        //EventBus.getDefault().post(new WeeklyDeviceSharingQuestions(WeeklyDeviceSharingQuestions.From.WEEKLY_DEVICE_SHARING_SURVEY_Q3));
    }

     /*
    private void observeAllCheckboxes(){
        observeSingleCheckbox(cbDeviceSharingQ3_a);
        observeSingleCheckbox(cbDeviceSharingQ3_b);
        observeSingleCheckbox(cbDeviceSharingQ3_c);
        observeSingleCheckbox(cbDeviceSharingQ4_d);
        observeSingleCheckbox(cbDeviceSharingQ3_e);

        subscribeTotalCheckedboxNum();
    }


    private void observeSingleCheckbox(CheckBox checkBox) {
        if (checkBox == cbDeviceSharingQ3_e) {
            ObservableHelper.checkedboxValidity(checkBox).subscribe(this::enablePleaseSpecifyEditText,
                e -> Log.e(TAG, "validateNextButtonState: ", e));
            ObservableHelper.textInputLengthValidity(etPleaseSpecify).subscribe(this::countCheckedbox,
                e -> Log.e(TAG, "validateNextButtonState: ", e));
        } else {
            ObservableHelper.checkedboxValidity(checkBox).subscribe(this::countCheckedbox,
                e -> Log.e(TAG, "validateNextButtonState: ", e));
        }
    }
    */

    /*
    private String getCheckboxAnswer(CheckBox checkBox) {
        if (checkBox.isChecked()) {
            if (checkBox != cbDeviceSharingQ3_e) {
                return checkBox.getText().toString();
            } else {
                String specifyPeople = etPleaseSpecify.getText().toString();
                return checkBox.getText().toString() + specifyPeople;
            }
        }
        return null;
    }

    private String getAllCheckboxesAnswer() {
        return getCheckboxAnswer(cbDeviceSharingQ3_a)+", "+ getCheckboxAnswer(cbDeviceSharingQ3_b)+", "+ getCheckboxAnswer(cbDeviceSharingQ3_c)
            +", "+ getCheckboxAnswer(cbDeviceSharingQ4_d)+", "+ getCheckboxAnswer(cbDeviceSharingQ3_e);
    }
    */
}
