package com.seoul.buskinggo.buskerconfigure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.seoul.buskinggo.R;


public class BuskerConfigureActivity extends AppCompatActivity {
    public static final String EXTRA_TASK_ID = "TASK_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busker_configure);
        /*// Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        // Get the requested task id
        String taskId = getIntent().getStringExtra(EXTRA_TASK_ID);


        // Fragment 생성하기
        BuskerConfigureFragment buskerConfigureFragment = (BuskerConfigureFragment) getSupportFragmentManager().findFragmentById(
                R.id.contentFrame);

        if (buskerConfigureFragment == null) {
            buskerConfigureFragment = BuskerConfigureFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.contentFrame, buskerConfigureFragment).commit();
        }

        // Presenter 생성하기
        // Create the presenter
        new BuskerConfigurePresenter(
                taskId,
                Injection.provideTasksRepository(getApplicationContext()),
                taskDetailFragment);*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
