package ru.advantum.fedosov.insta.ui.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ru.advantum.bigbrother.android.R;
import ru.advantum.bigbrother.android.model.TransportPreview;
import ru.advantum.bigbrother.android.model.database.DbUtils;
import ru.advantum.bigbrother.android.ui.adapter.TransportListAdapter;

public class NavigationFragment extends Fragment {

    @InjectView(R.id.list)
    protected ListView mListView;
    @InjectView(R.id.fragment_navigation_find)
    EditText findText;

    private NavigationCallbacks mCallbacks;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private TransportListAdapter mAdapter;
    private TransportPreview mCurrentItem;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallbacks = (NavigationCallbacks) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationCallbacks interface");
        }
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_navigation, container, false);
        mListView = (ListView) root.findViewById(R.id.list);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mAdapter == null) mAdapter = new TransportListAdapter(DbUtils.getFiltredTransportList(null));
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener((parent, view, position, id)
                -> mCallbacks.onNavigationItemSelected(mAdapter.getItem(position)));
        findText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mAdapter.getTransportLikeGosNum(s.toString());
            }
        });
    }


    @Override
    public void onDetach() {
        mCallbacks = null;
        super.onDetach();
    }

    public void setSelectedItem(TransportPreview item) {
        mCurrentItem = item;
        if (mListView != null && mAdapter != null) {
            mListView.setItemChecked(mAdapter.getItemPosition(item) + mListView.getHeaderViewsCount(), true);
        }
    }


    public interface NavigationCallbacks {
        void onNavigationItemSelected(TransportPreview item);
    }

}
