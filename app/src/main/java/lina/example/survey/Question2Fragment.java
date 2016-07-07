package lina.example.survey;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lina on 2016-07-06.
 */
public class Question2Fragment extends Fragment {
    public static final Question2Fragment newInstance() {
        Bundle args =  new Bundle();
        Question2Fragment fragment = new Question2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question2, container, false);
    }
}
