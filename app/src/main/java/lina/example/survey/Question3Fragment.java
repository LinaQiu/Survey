package lina.example.survey;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
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

    @Nullable
    @BindView(R.id.item_answer_container)
    LinearLayout llAnswer3;

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

    @BindView(R.id.et_please_specify)
    EditText etPleaseSpecify;

    private static final String TAG = Question3Fragment.class.getSimpleName();

    private static int checkedboxNum = 0;

    BehaviorSubject<Integer> totalCheckedboxNum = BehaviorSubject.create(0);

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
        observeAllCheckboxes();
    }

    public void setQuestion(String question) {
        tvQuestion3.setText(question);
    }

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
        //answersCheckboxUtil.saveCheckedValue(llAnswer3, DataHelper.Key.WEEKLY_DEVICE_SHARING_PEOPLE);
        changeFragment(SubmitFragment.newInstance());
    }
}
