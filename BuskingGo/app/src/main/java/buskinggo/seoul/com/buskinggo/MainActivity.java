package buskinggo.seoul.com.buskinggo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

        homeFragment = new HomeFragment();
        myPageFragment = new MyPageFragment();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().add(R.id.container, homeFragment).commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home: // 홈
                        changeFragment(homeFragment);
                        break;
                    case R.id.busking_list: // 버스킹 목록
                        //changeFragment(buskingListFragment); // 여기 들어가는 Fragment만 바꿔주면 됨!!
                        break;
                    case R.id.busking_regist: // 버스킹 등록
                        //changeFragment(buskerRegistFragment);
                        break;
                    case R.id.seoul_street: // 거리예술존
                        //changeFragment();
                        break;
                    case R.id.my_page: // 마이페이지
                        changeFragment(myPageFragment);
                        break;
                }
                return true;
            }
        });

    }

    public void changeFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        getSupportFragmentManager().popBackStack();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.commit();
    }

}
