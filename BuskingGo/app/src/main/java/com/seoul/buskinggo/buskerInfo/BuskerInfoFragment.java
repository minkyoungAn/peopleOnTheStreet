package com.seoul.buskinggo.buskerInfo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seoul.buskinggo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuskerInfoFragment extends Fragment {


    public BuskerInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_busker_info, container, false);
    }

}
