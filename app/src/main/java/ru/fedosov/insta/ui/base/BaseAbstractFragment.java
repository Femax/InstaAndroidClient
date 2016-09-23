package ru.fedosov.insta.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import ru.fedosov.insta.annotation.FragmentRef;
import ru.fedosov.insta.event.BaseEvent;


/**
 * Base fragment implementation. Use this class for auto apply basic functionality
 * <p>
 * Use annotation {@link FragmentRef} for configuring fragment basic workaround.
 * Class is not final and can be modified in project.
 * <pre><code>
 *     {@literal @}FragmentRef(resource = R.layout.fragment_main, knifeEnabled = true,
 *     busEnabled = true)
 *     public class MainFragment extends BaseAbstractFragment {
 *         ...
 *     }
 * </code></pre>
 * </p>
 * @see FragmentRef
 *
 */
@FragmentRef
public abstract class BaseAbstractFragment extends Fragment {

    protected final static String EXTRA_STATE = "EXTRA_STATE";
    protected final static String EXTRA_ID = "EXTRA_ID";
    private FragmentRef fragmentRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentRef = getClass().getAnnotation(FragmentRef.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return fragmentRef != null && fragmentRef.resource() != 0
                ? inflater.inflate(fragmentRef.resource(), container, false)
                : super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (fragmentRef.knifeEnabled()) ButterKnife.bind(this, view);
        if (fragmentRef.busEnabled()) EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBaseEvent(BaseEvent unused) {
        // do nothing. for EventBus, if it would be registered
    }

    @Override
    public void onDestroyView() {
        if (fragmentRef.busEnabled()) EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
