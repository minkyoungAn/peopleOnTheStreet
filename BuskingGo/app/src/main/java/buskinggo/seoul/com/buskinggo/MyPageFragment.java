package buskinggo.seoul.com.buskinggo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import buskinggo.seoul.com.buskinggo.configure.ConfigureActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageFragment extends Fragment {


    public MyPageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_my_page, container, false);
        ImageButton settingButton = (ImageButton) view.findViewById(R.id.settingButton);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent configureIntent = new Intent(getView().getContext(), ConfigureActivity.class);
                startActivity(configureIntent);
            }
        });

        return view;
    }

}
