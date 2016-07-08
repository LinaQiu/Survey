package lina.example.survey;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lina on 2016-07-07.
 */
public class AnswersCheckboxUtil {

    public void addCheckboxes(Context context, LinearLayout linearLayout, String [] answers) {
        for (int i = 0; i < answers.length; i++) {
            CheckedTextView checkedTextView = (CheckedTextView) LayoutInflater.from(context)
                .inflate(R.layout.item_checkbox_answers, null);
            checkedTextView.setText(answers[i]);
            checkedTextView.setId(i);
            checkedTextView.setOnClickListener(v -> ((CheckedTextView) v).toggle());
            linearLayout.addView(checkedTextView);
        }
    }

    public Answer getCheckedValue(ViewGroup parent) {
        List<Integer> checkedIds = new ArrayList<>();
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            CheckedTextView checkedTextView = (CheckedTextView) child;
            if (checkedTextView.isChecked()) {
                checkedIds.add(checkedTextView.getId());
            }
        }
        return new Answer(checkedIds);
    }

    public void preFilledCheckboxes(ViewGroup parent, String key) {
        if (DataHelper.getAnswer(key) != null) {
            Answer answer = DataHelper.getAnswer(key);
            for (Integer id : answer.getViewId()) {
                View child = parent.findViewById(id);
                CheckedTextView checkedTextView = (CheckedTextView) child;
                checkedTextView.setChecked(true);
            }
        }
    }

    public void saveCheckedValue(ViewGroup parent, String key) {
        DataHelper.saveAnswer(key, getCheckedValue(parent));
    }

}
