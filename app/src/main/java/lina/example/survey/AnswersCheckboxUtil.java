package lina.example.survey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lina on 2016-07-07.
 */
public class AnswersCheckboxUtil {

    public void addCheckboxes(Context context, LinearLayout linearLayout, String [] answers) {
        for (int i = 0; i < answers.length; i++) {
            CheckBox checkBox = (CheckBox) LayoutInflater.from(context)
                .inflate(R.layout.item_checkbox_answers, null);
            checkBox.setText(answers[i]);
            checkBox.setId(i);
            //checkBox.setOnClickListener(v -> ((CheckedTextView) v).toggle());
            linearLayout.addView(checkBox);
        }
    }

    public Answer getCheckedValue(ViewGroup parent) {
        List<Integer> checkedIds = new ArrayList<>();
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            CheckBox checkBox = (CheckBox) child;
            if (checkBox.isChecked()) {
                checkedIds.add(checkBox.getId());
            }
        }
        return new Answer(checkedIds);
    }

    public void preFilledCheckboxes(ViewGroup parent, String key) {
        if (DataHelper.getAnswer(key) != null) {
            Answer answer = DataHelper.getAnswer(key);
            for (Integer id : answer.getViewId()) {
                View child = parent.findViewById(id);
                CheckBox checkBox = (CheckBox) child;
                checkBox.setChecked(true);
            }
        }
    }

    public void saveCheckedboxesIds(ViewGroup parent, String key) {
        DataHelper.saveAnswer(key, getCheckedValue(parent));
    }

    public void saveCheckedboxStrings(ViewGroup parent, String key) {
        String deviceSharingPeople = "";
        if (getCheckedValue(parent) != null) {
            Answer answer = getCheckedValue(parent);
            for (Integer id : answer.getViewId()) {
                View child = parent.findViewById(id);
                CheckBox checkBox = (CheckBox) child;
                deviceSharingPeople+=checkBox.getText().toString()+", ";
            }
        }
        DataHelper.saveStringAnswers(key, deviceSharingPeople);
    }
}
