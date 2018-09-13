package com.seoul.buskinggo.buskerconfigure;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seoul.buskinggo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuskerConfigureFragment extends Fragment {


    public BuskerConfigureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_busker_configure, container, false);
    }

}
