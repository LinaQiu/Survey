package lina.example.survey;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

/**
 * Created by lina on 2016-06-30.
 */
public abstract class SmartFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private SparseArray<Fragment> registerdFragments = new SparseArray<Fragment>();

    public SmartFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Register the fragment when the item is instantiated
    @Override
    public Object instantiateItem(ViewGroup container, int position){
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registerdFragments.put(position, fragment);
        return fragment;
    }

    // Unregister when the item is inactive
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        registerdFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    // Returns the fragment for the position (if instantiated)
    public Fragment getRegisteredFragment(int position) {
        return registerdFragments.get(position);
    }

}
