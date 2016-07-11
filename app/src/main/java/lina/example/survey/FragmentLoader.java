package lina.example.survey;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Lina on 2016-07-10.
 */
public class FragmentLoader {

    private final FragmentManager fragmentManager;

    public FragmentLoader(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void loadFragment(Fragment fragment, int fragmentHolderId) {
        loadFragment(fragment, fragmentHolderId, null);
    }

    public void loadFragment(Fragment fragment, int fragmentHolderId, boolean addToStack) {
        getFragmentTransaction(fragmentHolderId, addToStack, null)
            .replace(fragmentHolderId, fragment, null).commit();
    }

    public void loadFragment(Fragment fragment, int fragmentHolderId, String tag) {
        getFragmentTransaction(fragmentHolderId, true, tag).replace(fragmentHolderId, fragment, tag)
            .commit();
    }

    protected FragmentTransaction getFragmentTransaction(int fragmentHolderId, boolean addToStack,
        String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (addToStack && isAnyFragmentOnScreen(fragmentHolderId)) {
            transaction.addToBackStack(tag);
        }

        if (!addToStack) {
            closeAllFragment();
        }

        return transaction;
    }

    protected boolean isAnyFragmentOnScreen(int fragmentHolderId) {
        return getCurrentFragment(fragmentHolderId) != null;
    }

    protected Fragment getFragment(String tag) {
        return fragmentManager.findFragmentByTag(tag);
    }

    protected Fragment getCurrentFragment(int fragmentHolderId) {
        return fragmentManager.findFragmentById(fragmentHolderId);
    }

    public void closeCurrentFragment() {
        fragmentManager.popBackStack();
    }

    public void closeAllFragment() {
        fragmentManager.popBackStack(null, fragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
