package ru.fedosov.insta.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import ru.fedosov.insta.annotation.FragmentRef;

/**
 * Created by yurchenko on 31.05.2016.
 */
@FragmentRef(knifeEnabled = true, busEnabled = true)
public abstract class BaseMapFragment extends BaseAbstractFragment implements OnMapReadyCallback {

    protected static final double DEFAULT_LAT = 55.749792;
    protected static final double DEFAULT_LON = 37.632495;

    protected final static String TAG = "MonitorFragment";
    protected Callback mCallback;
    protected MapView mMapView;

    private GoogleMap mMap;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (Callback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement BaseMapFragment.mCallback interface");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMapView.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.getMapAsync(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        mMap = null;
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mMapView.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        prepareClusters();
        if (mCallback != null) mCallback.onMapReady(mMap);
    }

    protected abstract void prepareClusters();

    protected LatLngBounds getBounds(LatLng one, LatLng two) {
        return new LatLngBounds(
                new LatLng(Math.min(one.latitude, two.latitude), Math.min(one.longitude, two.longitude)),
                new LatLng(Math.max(one.latitude, two.latitude), Math.max(one.longitude, two.longitude)));
    }

    public interface Callback {
        void onMapReady(GoogleMap googleMap);

    }

    protected GoogleMap getMap() {
        return mMap;
    }
}
