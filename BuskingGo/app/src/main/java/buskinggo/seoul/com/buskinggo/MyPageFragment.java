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

import com.astuetz.PagerSlidingTabStrip;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageFragment extends Fragment {

    public MyPageFragment(){}

    private Fragment myLikeFragment;
    private Fragment myWantGoFragment;
    private Fragment myPastFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

        myLikeFragment = new MyLikeFragment();
        myWantGoFragment = new MyWantGoFragment();
        myPastFragment = new MyPastFragment();

        ViewPager pager = view.findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter(getChildFragmentManager()));
        pager.setOffscreenPageLimit(3);


        pager.setCurrentItem(0);
        PagerSlidingTabStrip tabs = view.findViewById(R.id.tabs);
        tabs.setShouldExpand(true);
        tabs.setViewPager(pager);

        Objects.requireNonNull(getActivity()).setTitle("마이페이지");


        return view;
    }

    private String[] pageTitle = {"좋아요", "가볼래요", "지난버스킹"};

    private class PagerAdapter extends FragmentPagerAdapter {
        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitle[position];
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
