package de.hdmstuttgart.parkspot.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.here.android.mpa.common.*;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.SupportMapFragment;
import de.hdmstuttgart.parkspot.R;

import java.lang.ref.WeakReference;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HereMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HereMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HereMapFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Context context;
    private View view;

    // map embedded in the map fragment
    private Map map;

    // map fragment embedded in this activity
    private SupportMapFragment mapFragment;

    private PositioningManager positioningManager;
    private boolean paused = false;

    public HereMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HereMapFragment.
     */
    public static HereMapFragment newInstance() {
        HereMapFragment fragment = new HereMapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_here_map, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        paused = false;
        initialize();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        PositioningManager.getInstance().stop();
        paused = true;
    }

    private void initialize() {
        if (mapFragment == null) {
            // Search for the map fragment to finish setup by calling init().
            mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.heremap);

            mapFragment.init(new OnEngineInitListener() {
                @Override
                public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {
                    if (error == OnEngineInitListener.Error.NONE) {
                        // retrieve a reference of the map from the map fragment
                        map = mapFragment.getMap();
                        // Set the map center to the Vancouver region (no animation)
                        map.setCenter(new GeoCoordinate(48.78232, 9.17702, 0.0), Map.Animation.NONE);
                        // Set the zoom level to the average between min and max
                        map.setZoomLevel(map.getMaxZoomLevel() + map.getMinZoomLevel());
                        addMarkers();
                    } else {
                        System.out.println("ERROR: Cannot initialize Map Fragment");
                    }
                }
            });
        }
        //start getting location updates based on GPS and Network
        PositioningManager.getInstance().start(PositioningManager.LocationMethod.GPS_NETWORK);
        PositioningManager.getInstance().addListener(new WeakReference<>(positionListener));
    }

    private void addMarkers() {
        map.addMapObject(new MapMarker(new GeoCoordinate(21.94563, -164.24798)));
        map.addMapObject(new MapMarker(new GeoCoordinate(54.89209, 52.47664)));
        map.addMapObject(new MapMarker(new GeoCoordinate(-23.15056, -171.56628)));
        map.addMapObject(new MapMarker(new GeoCoordinate(-28.45507, 133.15978)));
        map.addMapObject(new MapMarker(new GeoCoordinate(58.71674, 129.53751)));
        map.addMapObject(new MapMarker(new GeoCoordinate(-8.24891, -108.56783)));
        map.addMapObject(new MapMarker(new GeoCoordinate(-28.92105, 77.98870)));
        map.addMapObject(new MapMarker(new GeoCoordinate(12.84797, -153.63357)));
        map.addMapObject(new MapMarker(new GeoCoordinate(58.69578, 57.04733)));
        map.addMapObject(new MapMarker(new GeoCoordinate(-7.49030, 152.50843)));
        map.addMapObject(new MapMarker(new GeoCoordinate(50.70225, 77.50784)));
        map.addMapObject(new MapMarker(new GeoCoordinate(-43.97845, 49.14345)));
    }

    // Define positioning listener
    private PositioningManager.OnPositionChangedListener positionListener = new PositioningManager.OnPositionChangedListener() {

        public void onPositionUpdated(PositioningManager.LocationMethod method, GeoPosition position, boolean isMapMatched) {
            // set the center only when the app is in the foreground to reduce CPU consumption
            if (!paused) {
                if (map != null) {
                    map.setCenter(position.getCoordinate(), Map.Animation.NONE);
                    // display position indicator
                    map.getPositionIndicator().setVisible(true);
                }
            }
        }

        public void onPositionFixChanged(PositioningManager.LocationMethod method,
                                         PositioningManager.LocationStatus status) {
        }
    };

    public interface OnFragmentInteractionListener {
    }
}
