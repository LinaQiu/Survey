package lina.example.survey;

import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxRadioGroup;
import com.jakewharton.rxbinding.widget.RxTextView;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import rx.Observable;

/**
 * Created by lina on 2016-07-07.
 */
public class ObservableHelper {

    public static Observable<Boolean> radioGroupValidity(RadioGroup radioGroup) {
        return RxRadioGroup.checkedChanges(radioGroup)
            .map(l -> l > 0)
            .distinctUntilChanged();
    }

    public static Observable<Boolean> checkedboxValidity(CheckBox checkBox) {
        return RxCompoundButton.checkedChanges(checkBox)
            .distinctUntilChanged();
    }

    public static Observable<Boolean> textInputLengthValidity(EditText editText) {
        return RxTextView.textChanges(editText)
            .map(CharSequence::toString)
            .map(String::trim)
            .map(String::length)
            .map(l -> l > 0)
            .distinctUntilChanged();
    }

}
