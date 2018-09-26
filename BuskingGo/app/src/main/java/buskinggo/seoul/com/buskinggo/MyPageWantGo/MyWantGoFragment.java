package buskinggo.seoul.com.buskinggo.MyPageWantGo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import buskinggo.seoul.com.buskinggo.AsyncListener;
import buskinggo.seoul.com.buskinggo.BuskerDTO;
import buskinggo.seoul.com.buskinggo.BuskingDTO;
import buskinggo.seoul.com.buskinggo.MyPageLike.AsyncMyLike;
import buskinggo.seoul.com.buskinggo.MyPageLike.MyLikeBuskerAdapter;
import buskinggo.seoul.com.buskinggo.R;
import buskinggo.seoul.com.buskinggo.UserDTO;
import buskinggo.seoul.com.buskinggo.buskerInfo.MyBuskingListAdapter;

public class MyWantGoFragment extends Fragment {
    ArrayList<BuskingDTO> buskingList;
    MyWantBuskingAdapter adapter;
    UserDTO userDTO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_my_want_go,null);
        if(this.getArguments() != null){
            userDTO = (UserDTO) this.getArguments().getSerializable("userDTO");
        }

        final GridView buskerListView = view.findViewById(R.id.lv_my_want);
        TextView noList = view.findViewById(R.id.tv_no_list_my_want);
        buskerListView.setEmptyView(noList);

        buskingList = new ArrayList<>();
        adapter = new MyWantBuskingAdapter(Objects.requireNonNull(getActivity()).getApplicationContext(),R.layout.my_want_busking_list_item, buskingList);
        buskerListView.setAdapter(adapter);

        // 공연리스트뷰 로드
        new AsyncMyWant(new AsyncListener() {

            @Override
            public void buskerComplete(BuskerDTO buskerDTO) {}
            @Override
            public void buskerComplete(ArrayList<BuskerDTO> buskerDTO) {}
            @Override
            public void buskingComplete(ArrayList<BuskingDTO> buskingDTOS) {
                if(buskingDTOS != null){
                    buskingList.clear();
                    buskingList.addAll(buskingDTOS);
                    adapter.notifyDataSetChanged();
                }

            }
            @Override
            public void buskingComplete(BuskingDTO buskingDTOS) {}


        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userDTO.getUserNo());


        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
