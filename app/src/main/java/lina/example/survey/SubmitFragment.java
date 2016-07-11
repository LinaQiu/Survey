package lina.example.survey;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lina on 2016-07-07.
 */
public class SubmitFragment extends BaseFragment {

    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private static final String TAG = SubmitFragment.class.getSimpleName();

    public static final SubmitFragment newInstance() {
        Bundle args =  new Bundle();
        SubmitFragment fragment = new SubmitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_submit;
    }

    @Override
    public void setup() {

    }

    @OnClick(R.id.btn_submit)
    public void onSubmitBtnClick() {
        Log.e(TAG, DataHelper.getStringAnswers(DataHelper.Key.WEEKLY_DEVICE_SHARING_OPINION) + DataHelper.getWeeklyDeviceSharingTimes()
            +DataHelper.getStringAnswers(DataHelper.Key.WEEKLY_DEVICE_SHARING_CHECKBOX_STRING)+DataHelper.getStringAnswers(DataHelper.Key.WEEKLY_DEVICE_SHARING_SPECIFY_PEOPLE));
    DataHelper.clearWeeklyDeviceSharingSurveyAnswers();
    }
}
