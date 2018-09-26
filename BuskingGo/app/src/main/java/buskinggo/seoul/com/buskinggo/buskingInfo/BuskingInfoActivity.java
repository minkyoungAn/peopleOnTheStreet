package buskinggo.seoul.com.buskinggo.buskingInfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import buskinggo.seoul.com.buskinggo.AsyncListener;
import buskinggo.seoul.com.buskinggo.AsyncPhotoListener;
import buskinggo.seoul.com.buskinggo.BuskerDTO;
import buskinggo.seoul.com.buskinggo.BuskingDTO;
import buskinggo.seoul.com.buskinggo.utils.PhotoResizing;
import buskinggo.seoul.com.buskinggo.R;
import buskinggo.seoul.com.buskinggo.utils.AsyncPhoto;

public class BuskingInfoActivity extends AppCompatActivity {
    int userNo; // 현재 유저
    int buskingNo; // 선택한 버스커
    int want = 0; // 가볼래요 유무

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busking_info);
        Intent intent = getIntent();
        userNo = intent.getIntExtra("userNo", 0);
        buskingNo = intent.getIntExtra("buskingNo", 0);

        Toolbar toolbar = findViewById(R.id.busking_info_toolbar);
        setSupportActionBar(toolbar); // 툴바
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        // DB 저장된 값 로드
        AsyncBuskingInfo asyncBuskingInfo = new AsyncBuskingInfo(new AsyncListener() {
            @Override
            public void buskerComplete(BuskerDTO buskerDTO) {}
            @Override
            public void buskerComplete(ArrayList<BuskerDTO> buskerDTOS) {}
            @Override
            public void buskingComplete(ArrayList<BuskingDTO> buskingDTOS) {}

            @Override
            public void buskingComplete(BuskingDTO buskingDTO) {

                TextView tvName = findViewById(R.id.busking_info_name);
                TextView tvDate = findViewById(R.id.busking_info_date_Item);
                TextView tvTime = findViewById(R.id.busking_info_time_item);
                TextView tvPlace = findViewById(R.id.busking_info_place_item);
                TextView tvIntroduce = findViewById(R.id.busking_info_introduce_item);
                TextView tvWantSum= findViewById(R.id.busking_info_want_cnt);
                ImageView ivWant = findViewById(R.id.busking_info_want_img);

                if(buskingDTO.getMyWant() != 0){
                    // 있으면 별 칠하기
                    ivWant.setImageResource(R.drawable.ic_star_white_24dp);
                    want = 1;
                }
                String time = buskingDTO.getBuskingTime();
                time = time.substring(0,5);

                tvName.setText(buskingDTO.getBuskerName());
                tvDate.setText(buskingDTO.getBuskingDate());
                tvTime.setText(time);
                tvPlace.setText(buskingDTO.getPlace());
                tvIntroduce.setText(buskingDTO.getIntroduce());
                tvWantSum.setText(String.valueOf(buskingDTO.getWantSum()));

                // 프로필이미지 출력
                if(buskingDTO.getPhoto()!=null){
                    bitmapImgDownload(buskingDTO.getPhoto());
                }

            }

        });
        asyncBuskingInfo.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userNo, buskingNo);

        final TextView tvWantSum= findViewById(R.id.busking_info_want_cnt);
        final ImageView ivWant = findViewById(R.id.busking_info_want_img);

        // 가볼래요 클릭이벤트
        ivWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(want == 1){
                    ivWant.setImageResource(R.drawable.ic_star_border_white_24dp);
                    want = 0;
                    int cnt = Integer.parseInt(tvWantSum.getText().toString()) - 1;
                    tvWantSum.setText(String.valueOf(cnt));
                    // 좋아요 삭제
                    new AsyncUpdateWant().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userNo, buskingNo, 0);

                }else if (want == 0){
                    ivWant.setImageResource(R.drawable.ic_star_white_24dp);
                    want = 1;
                    int cnt = Integer.parseInt(tvWantSum.getText().toString()) + 1;
                    tvWantSum.setText(String.valueOf(cnt));
                    // 좋아요 추가
                    new AsyncUpdateWant().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userNo, buskingNo, 1);
                }

            }
        });

    }

    void bitmapImgDownload(String photo) {
        // 이미지 다운로드
        AsyncPhoto asyncPhoto = new AsyncPhoto(new AsyncPhotoListener() {
            @Override
            public void taskComplete(File file) {
                if(file != null){
                    ImageView ivPhoto = findViewById(R.id.busking_info_photo);
                    Bitmap bitmap = new PhotoResizing().loadPictureWithResize(file, 160);
                    ivPhoto.setImageBitmap(bitmap);
                }
            }
        });
        asyncPhoto.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, photo, "buskingPhoto");

    }
}
