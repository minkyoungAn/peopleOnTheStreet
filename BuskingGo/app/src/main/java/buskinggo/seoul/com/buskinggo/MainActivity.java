package buskinggo.seoul.com.buskinggo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    HomeFragment homeFragment;
    MyPageFragment myPageFragment;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.home_fragment);
        myPageFragment = new MyPageFragment();
        bottomNavigationView = findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home: // 홈

                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        break;
                    case R.id.busking_list: // 버스킹 목록

                        break;
                    case R.id.busking_regist: // 버스킹 등록

                        break;
                    case R.id.seoul_street: // 거리예술존

                        break;
                    case R.id.my_page: // 마이페이지
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, myPageFragment).commit();
                        break;
                }
                return true;
            }
        });

    }

}
