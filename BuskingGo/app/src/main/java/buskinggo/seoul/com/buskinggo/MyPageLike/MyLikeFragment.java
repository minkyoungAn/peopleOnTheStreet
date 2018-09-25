package buskinggo.seoul.com.buskinggo.MyPageLike;

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
import buskinggo.seoul.com.buskinggo.R;
import buskinggo.seoul.com.buskinggo.UserDTO;

public class MyLikeFragment extends Fragment {

    ArrayList<BuskerDTO> buskerList;
    MyLikeBuskerAdapter adapter;
    UserDTO userDTO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_like,null);
        if(this.getArguments() != null){
            userDTO = (UserDTO) this.getArguments().getSerializable("userDTO");
        }

        final GridView buskerListView = (GridView) view.findViewById(R.id.lv_my_like);
        TextView noList = view.findViewById(R.id.tv_no_list_my_like);
        buskerListView.setEmptyView(noList);

        buskerList = new ArrayList<>();
        adapter = new MyLikeBuskerAdapter(Objects.requireNonNull(getActivity()).getApplicationContext(),R.layout.my_like_busker_list_item, buskerList);
        buskerListView.setAdapter(adapter);

        // 공연리스트뷰 로드
        new AsyncMyLike(new AsyncListener() {

            @Override
            public void buskerComplete(BuskerDTO buskerDTO) {}
            @Override
            public void buskerComplete(ArrayList<BuskerDTO> buskerDTO) {
                if(buskerDTO != null){
                    buskerList.clear();
                    buskerList.addAll(buskerDTO);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void buskingComplete(ArrayList<BuskingDTO> buskingDTOS) {}
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
