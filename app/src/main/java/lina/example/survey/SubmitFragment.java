package lina.example.survey;

import android.os.Bundle;

/**
 * Created by lina on 2016-07-07.
 */
public class SubmitFragment extends BaseFragment {

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
}
