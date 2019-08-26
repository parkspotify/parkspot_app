package de.hdmstuttgart.parkspot.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.hdmstuttgart.parkspot.MyRaspberryLocationRecyclerViewAdapter;
import de.hdmstuttgart.parkspot.R;
import de.hdmstuttgart.parkspot.dummy.DummyContent;

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

public class RaspberryLocationListFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RaspberryLocationListFragment() {
    }

    public static RaspberryLocationListFragment newInstance() {
        RaspberryLocationListFragment fragment = new RaspberryLocationListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_raspberrylocation_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyRaspberryLocationRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DummyContent.DummyItem mItem);
    }
}
