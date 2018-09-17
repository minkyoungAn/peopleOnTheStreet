package buskinggo.seoul.com.buskinggo;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

public class BuskerInfoActivity extends Activity {
    int userNo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busker_info);

        Toolbar toolbar = findViewById(R.id.busker_info_toolbar);
        final TextView tvName = findViewById(R.id.busker_info_name);
        final TextView tvJenre = findViewById(R.id.busker_info_jenre_item);
        final TextView tvPlace = findViewById(R.id.busker_info_place_item);
        final TextView tvFavorite = findViewById(R.id.busker_info_favorite);
        final TextView tvIntroduce = findViewById(R.id.busker_info_introduce_item);

        // DB 저장된 값 로드
        AsyncBuskerInfo asyncBuskerInfo = new AsyncBuskerInfo(new AsyncListener() {
            @Override
            public void taskComplete(BuskerDTO buskerDTO) {
                tvName.setText(buskerDTO.getBuskerName());
                tvJenre.setText(buskerDTO.getGenre());
                tvPlace.setText(buskerDTO.getMainPlace());
                if(buskerDTO.getFavorite() != 0){
                    // 있으면 하트 칠하고 숫자 표시
                    //tvFavorite.setText();
                }

                tvIntroduce.setText(buskerDTO.getIntroduce());
            }
        });
        asyncBuskerInfo.execute(userNo);
        




    }

}
