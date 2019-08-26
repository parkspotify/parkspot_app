package de.hdmstuttgart.parkspot.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPosition;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.SupportMapFragment;
import de.hdmstuttgart.parkspot.R;

import java.lang.ref.WeakReference;

/**
 * This file is part of Parkspot.      
 *
 * Parkspot is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation version 3 of the License.
 * Parkspot is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with Parkspot. 
 * If not, see http://www.gnu.org/licenses/.
 *
 * Copyright (c) 2019, Hochschule der Medien
 * Author: Monika Grabke
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
        map.addMapObject(new MapMarker(new GeoCoordinate(48.943394, 9.432170)));
        map.addMapObject(new MapMarker(new GeoCoordinate(48.782897, 9.181490)));
        map.addMapObject(new MapMarker(new GeoCoordinate(48.787061, 9.181640)));
        map.addMapObject(new MapMarker(new GeoCoordinate(48.786838, 9.175194)));
        map.addMapObject(new MapMarker(new GeoCoordinate(48.748388, 9.110238)));
        map.addMapObject(new MapMarker(new GeoCoordinate(48.747141, 9.102149)));
        map.addMapObject(new MapMarker(new GeoCoordinate(48.746032, 9.116121)));
        map.addMapObject(new MapMarker(new GeoCoordinate(48.745496, 9.109608)));
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
