package buskinggo.seoul.com.buskinggo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment;
    BuskingListFragment buskingListFragment;
    BuskingRegisterFragment buskingRegisterFragment;
    SeoulBuskingFragment seoulBuskingFragment;
    MyPageFragment myPageFragment;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent mainIntent = getIntent();
        UserDTO userDTO = (UserDTO) mainIntent.getSerializableExtra("userDTO");

        System.out.println(userDTO.getUserNo() + userDTO.getMainPlace()+ userDTO.getNickName());
        homeFragment = new HomeFragment();
        buskingListFragment = new BuskingListFragment();
        buskingRegisterFragment = new BuskingRegisterFragment();
        seoulBuskingFragment = new SeoulBuskingFragment();
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
                        changeFragment(buskingListFragment);
                        break;
                    case R.id.busking_regist: // 버스킹 등록
                        changeFragment(buskingRegisterFragment);
                        break;
                    case R.id.seoul_street: // 거리예술존
                        changeFragment(seoulBuskingFragment);
                        break;
                    case R.id.my_page: // 마이페이지
                        changeFragment(myPageFragment);
                        break;
                }
                return true;
            }
        });

    }

    /*
    *  네비게이션 바 클릭시 fragment 교체
    * */
    public void changeFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        getSupportFragmentManager().popBackStack();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.commit();
    }

}
