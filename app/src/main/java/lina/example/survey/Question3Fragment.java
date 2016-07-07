package lina.example.survey;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lina on 2016-07-06.
 */
public class Question3Fragment extends Fragment {
    public static final Question3Fragment newInstance() {
        Bundle args =  new Bundle();
        Question3Fragment fragment = new Question3Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question3, container, false);
    }
}
