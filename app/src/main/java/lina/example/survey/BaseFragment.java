package lina.example.survey;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import butterknife.Unbinder;

/**
 * Created by lina on 2016-07-07.
 */
public abstract class BaseFragment extends Fragment{

    private Activity activity;

    private Unbinder unbinder;

    public BaseFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                activity.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        restore(savedInstanceState);
        setup();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public abstract int getLayout();

    public abstract void setup();

    public void restore(Bundle savedInstanceState) {
        // no-op by default
    }

    @OnClick(R.id.weekly_device_survey_back_button)
    @Optional
    public void onBackBtnClick() {
        activity.onBackPressed();
    }

    public void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (transaction.isAddToBackStackAllowed()){
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

}
