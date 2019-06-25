package de.hdmstuttgart.parkspot.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.hdmstuttgart.parkspot.R;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnMapFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    private Context context;
    private View parentView;
    private MapView map;
    private MyLocationNewOverlay mLocationOverlay;

    private OnMapFragmentInteractionListener mListener;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MapFragment.
     */
    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Configuration.getInstance().setUserAgentValue(context.getPackageName());
        parentView =inflater.inflate(R.layout.fragment_map, container, false);
        setMapConfigs();
        return parentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof OnMapFragmentInteractionListener) {
            mListener = (OnMapFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMapFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume(); //needed for compass, location overlays
        //add current location to map
        if (mLocationOverlay == null)
            mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(context), map);
        mLocationOverlay.enableMyLocation();
        mLocationOverlay.enableFollowLocation();
        map.getOverlays().add(mLocationOverlay);
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();  //needed for compass, location overlays
    }

    private void setMapConfigs() {
        map = parentView.findViewById(R.id.mapview);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        map.getController().setZoom(20.0);
        map.setMinZoomLevel(2.0);
        map.setHorizontalMapRepetitionEnabled(false);
        map.setVerticalMapRepetitionEnabled(false);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnMapFragmentInteractionListener {
    }
}
