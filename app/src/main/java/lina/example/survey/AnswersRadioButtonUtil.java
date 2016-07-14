package lina.example.survey;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lina on 2016-07-11.
 */
public class AnswersRadioButtonUtil {

    private RadioGroup radioGroup;

    private void initRadioGroup(Context context) {
        radioGroup = new RadioGroup(context);
        radioGroup.setLayoutParams(
            new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        radioGroup.setGravity(Gravity.RIGHT);
    }

    public RadioGroup getRadioGroup() {
        return radioGroup;
    }

    public void addRadioButtons(Context context, LinearLayout linearLayout,
        String[] answers) {
        initRadioGroup(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < answers.length; i++) {
            RadioButton radioButton = (RadioButton) LayoutInflater.from(context)
                .inflate(R.layout.item_radio_button_answer, null);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setText(answers[i]);
            Log.e("LinaTest: ", answers[i]);
            radioButton.setTextColor(Color.BLACK);
            radioButton.setId(i + 1);
            radioGroup.addView(radioButton);
        }

        linearLayout.addView(radioGroup);
    }

    public Answer getSelectedValue(ViewGroup parent) {
        List<Integer> checkedIds = new ArrayList<>();
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            RadioGroup radio = (RadioGroup) child;
            checkedIds.add(radio.getCheckedRadioButtonId());
        }

        return new Answer(checkedIds);
    }

    public void preFilledRadioButton(ViewGroup parent, String key) {
        if (DataHelper.getAnswer(key) != null) {
            Answer answer = DataHelper.getAnswer(key);
            for (Integer id : answer.getViewId()) {
                View child = parent.findViewById(id);
                RadioButton radioButton = (RadioButton) child;
                radioButton.setChecked(true);
            }
        }
    }

    public void saveSelectedValue(ViewGroup parent, String key) {
        DataHelper.saveAnswer(key, getSelectedValue(parent));
    }

    public void saveRadioStrings(ViewGroup parent, String key) {
        String weekday = "";
        if (getSelectedValue(parent) != null) {
            Answer answer = getSelectedValue(parent);
            for (Integer id : answer.getViewId()) {
                View child = parent.findViewById(id);
                RadioButton radioButton = (RadioButton) child;
                weekday=radioButton.getText().toString();
            }
        }
        DataHelper.saveStringAnswers(key, weekday);
    }

}
