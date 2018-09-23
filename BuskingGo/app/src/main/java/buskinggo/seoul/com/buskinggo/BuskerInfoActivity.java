package buskinggo.seoul.com.buskinggo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BuskerInfoActivity extends AppCompatActivity {
    int userNo = 1;
    int buskerNo = 3;
    int favorite = 0;

    ArrayList<BuskingDTO> buskingList;
    MyBuskingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busker_info);

        Toolbar toolbar = findViewById(R.id.busker_info_toolbar);
        final ListView buskingListView = findViewById(R.id.lv_busker_info);
        TextView noList = findViewById(R.id.tv_no_list_my_busking);
        buskingListView.setEmptyView(noList);

        buskingList = new ArrayList<>();
        adapter = new MyBuskingListAdapter(getApplicationContext(),R.layout.my_busking_list_item, buskingList);
        buskingListView.setAdapter(adapter);

        // 공연리스트뷰 로드
        new AsyncBuskingListByBusker(new AsyncListener() {
            @Override
            public void taskComplete(BuskerDTO buskerDTO) {}

            @Override
            public void taskComplete(ArrayList<BuskingDTO> buskingDTOS) {
                if(buskingDTOS != null){
                    buskingList.clear();
                    buskingList.addAll(buskingDTOS);
                    adapter.notifyDataSetChanged();
                }

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, buskerNo);


        // DB 저장된 값 로드
        AsyncBuskerInfo asyncBuskerInfo = new AsyncBuskerInfo(new AsyncListener() {
            @Override
            public void taskComplete(BuskerDTO buskerDTO) {
                TextView tvName = findViewById(R.id.busker_info_name);
                TextView tvJenre = findViewById(R.id.busker_info_jenre_item);
                TextView tvPlace = findViewById(R.id.busker_info_place_item);
                TextView tvIntroduce = findViewById(R.id.busker_info_introduce_item);
                TextView tvFavorite = findViewById(R.id.busker_info_favorite_cnt);
                ImageView ivFavorite = findViewById(R.id.busker_info_favorite_img);

                if(buskerDTO.getMyFavorite() != 0){
                    // 있으면 하트 칠하기
                    ivFavorite.setImageResource(R.drawable.ic_favorite_white_24dp);
                    favorite = 1;
                }
                // 총 좋아요 수 출력
                tvFavorite.setText(String.valueOf(buskerDTO.getFavorite()));
                tvName.setText(buskerDTO.getBuskerName());
                tvJenre.setText(buskerDTO.getGenre());
                tvPlace.setText(buskerDTO.getMainPlace());
                tvIntroduce.setText(buskerDTO.getIntroduce());

                // 이미지처리 필요
            }

            @Override
            public void taskComplete(ArrayList<BuskingDTO> buskingDTOS) {}
        });
        asyncBuskerInfo.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userNo, buskerNo);

        final TextView tvFavorite = findViewById(R.id.busker_info_favorite_cnt);
        final ImageView ivFavorite = findViewById(R.id.busker_info_favorite_img);

        // 좋아요 클릭이벤트
        ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("좋아요 클릭" + favorite);
                if(favorite == 1){
                    ivFavorite.setImageResource(R.drawable.ic_favorite_border_white_24dp);
                    favorite = 0;
                    int cnt = Integer.parseInt(tvFavorite.getText().toString()) - 1;
                    tvFavorite.setText(String.valueOf(cnt));
                    // 좋아요 삭제
                    new AsyncUpdateLike().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userNo, buskerNo, 0);

                }else if (favorite == 0){
                    ivFavorite.setImageResource(R.drawable.ic_favorite_white_24dp);
                    favorite = 1;
                    int cnt = Integer.parseInt(tvFavorite.getText().toString()) + 1;
                    tvFavorite.setText(String.valueOf(cnt));
                    // 좋아요 추가
                    new AsyncUpdateLike().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userNo, buskerNo, 1);
                }

            }
        });
    }

}
