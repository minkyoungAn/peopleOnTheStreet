package buskinggo.seoul.com.buskinggo;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

public class BuskerInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busker_info);

        Toolbar toolbar = findViewById(R.id.busker_info_toolbar);
        TextView tvName = findViewById(R.id.busker_info_name);
        TextView tvJenre = findViewById(R.id.busker_info_jenre_item);
        TextView tvPlace = findViewById(R.id.busker_info_place_item);
        TextView ivFavorite = findViewById(R.id.busker_info_favorite);
        TextView tvIntroduce = findViewById(R.id.busker_info_introduce_item);

        // DB 저장된 값 로드
        




    }

}
