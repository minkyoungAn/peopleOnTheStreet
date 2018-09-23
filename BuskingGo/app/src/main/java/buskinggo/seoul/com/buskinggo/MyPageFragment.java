package buskinggo.seoul.com.buskinggo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageFragment extends Fragment {
    String mParam1;
    String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public MyPageFragment(){}

    public static MyPageFragment newInstance(String param1, String param2) {
        MyPageFragment myPageFragment = new MyPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        myPageFragment.setArguments(args);
        return myPageFragment;

    }

    private ViewPager pager;
    private Fragment myLikeFragment;
    private Fragment myWantGoFragment;
    private Fragment myPastFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

        myLikeFragment = new MyLikeFragment();
        myWantGoFragment = new MyWantGoFragment();
        myPastFragment = new MyPastFragment();

        Button btnFirst = view.findViewById(R.id.btnFirst);
        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                pager.setCurrentItem(0);
            } });
        Button btnSecond = view.findViewById(R.id.btnSecond);
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                pager.setCurrentItem(1);
            } });
        Button btnThird = view.findViewById(R.id.btnThird);
        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                pager.setCurrentItem(2);
            } });

        pager = view.findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager()));
        pager.setCurrentItem(0);

        getActivity().setTitle("마이페이지");


        return view;
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if( position == 0 ) {
                return myLikeFragment;
            } else if ( position == 1 ) {
                return myWantGoFragment;
            } else {
                return myPastFragment;
            }
        }


        @Override
        public int getCount() {
            return 3;
        }
    }
}
