package lina.example.survey;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Lina on 2016-07-10.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected FragmentLoader fragmentLoader;

    private Unbinder unbinder;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        initFragmentLoader();
        setup();
    }

    public void findViews() {
        if (0 != getLayout()) {
            setContentView(getLayout());
            unbinder = ButterKnife.bind(this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public abstract int getLayout();

    public abstract void setup();

    private void initFragmentLoader() {
        fragmentLoader = new FragmentLoader(getSupportFragmentManager());
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        EventBus.getDefault().post("EventBus is destroyed.");
        super.onDestroy();
    }

    /**
     * EventBus' Default event implementation
     */
    @Subscribe
    public void onDefaultEvent(DefaultEvent defaultEvent) {
        // no-op by default
    }
}
