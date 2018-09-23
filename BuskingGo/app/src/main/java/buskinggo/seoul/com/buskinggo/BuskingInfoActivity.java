package buskinggo.seoul.com.buskinggo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.Objects;

public class BuskingInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busking_info);
        Intent intent = getIntent();
        intent.getStringExtra("buskingNo");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");
        System.out.println(date +" "+ time +" "+ place);

        Toolbar toolbar = findViewById(R.id.busker_info_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

    }
}
