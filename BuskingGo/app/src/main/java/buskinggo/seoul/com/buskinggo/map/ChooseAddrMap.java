package buskinggo.seoul.com.buskinggo.map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.nhn.android.mapviewer.overlay.NMapResourceProvider;

import java.util.HashMap;

import buskinggo.seoul.com.buskinggo.R;

public class ChooseAddrMap extends NMapActivity implements View.OnClickListener {
    private final String TAG = "ChooseAddrMap";

    private NMapResourceProvider nMapResourceProvider;
    private NMapOverlayManager mapOverlayManager;

    TextView nowAddr;

    double longitude;
    double latitude;

    String apptNo;
    int requestCode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busking_choose_addr);
        Intent intent = getIntent();
        requestCode = intent.getIntExtra("requestCode", 0);

        init();

        TextView tv = findViewById(R.id.tv_add_addr);
        ImageView placeImg1 = findViewById(R.id.iv_center_place_above);
        ImageView placeImg2 = findViewById(R.id.iv_center_place);
        nowAddr = findViewById(R.id.tv_add_addr_now);
        Button ok = findViewById(R.id.btn_map_ok);
        ok.setOnClickListener(this);
        tv.bringToFront();
        placeImg1.bringToFront();
        placeImg2.bringToFront();
        nowAddr.bringToFront();


    }

    private void init() {

        NMapView mMapView = findViewById(R.id.map_view_non);
        mMapView.setClientId(getResources().getString(R.string.n_id)); // 클라이언트 아이디 값 설정
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.setScalingFactor(1.7f);
        mMapView.requestFocus();

        mMapView.setOnMapStateChangeListener(changeListener);
        mMapView.setOnMapViewTouchEventListener(mapListener);

        nMapResourceProvider = new NMapViewerResourceProvider(this);
        mapOverlayManager = new NMapOverlayManager(this, mMapView, nMapResourceProvider);
    }

    private NMapView.OnMapStateChangeListener changeListener = new NMapView.OnMapStateChangeListener() {
        @Override
        public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
            Log.e(TAG, "OnMapStateChangeListener onMapInitHandler : ");
        }


        @Override
        public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {
            Log.e(TAG, "OnMapStateChangeListener onMapCenterChange : " + nGeoPoint.getLatitude() + " ㅡ  " + nGeoPoint.getLongitude());
            longitude = nGeoPoint.getLongitude();
            latitude = nGeoPoint.getLatitude();

            GeocodeToAddress geocodeToAddress = new GeocodeToAddress(new AddressAsyncResponse() {

                @Override
                public void processFinish(HashMap<String, String> hashMap) {
                    String address = null;
                    if (hashMap != null) address = hashMap.get("address");
                    nowAddr.setText(address);
                }
            });
            geocodeToAddress.execute(longitude + "," + latitude);
        }

        @Override
        public void onMapCenterChangeFine(NMapView nMapView) {
            Log.e(TAG, "OnMapStateChangeListener onMapCenterChangeFine : ");
        }

        @Override
        public void onZoomLevelChange(NMapView nMapView, int i) {
            Log.e(TAG, "OnMapStateChangeListener onZoomLevelChange : " + i);
        }

        @Override
        public void onAnimationStateChange(NMapView nMapView, int i, int i1) {
            Log.e(TAG, "OnMapStateChangeListener onAnimationStateChange : ");
        }
    };

    private NMapView.OnMapViewTouchEventListener mapListener = new NMapView.OnMapViewTouchEventListener() {
        @Override
        public void onLongPress(NMapView nMapView, MotionEvent motionEvent) {
            Log.e(TAG, "OnMapViewTouchEventListener onLongPress : ");
        }

        @Override
        public void onLongPressCanceled(NMapView nMapView) {
            Log.e(TAG, "OnMapViewTouchEventListener onLongPressCanceled : ");
        }

        @Override
        public void onTouchDown(NMapView nMapView, MotionEvent motionEvent) {
            Log.e(TAG, "OnMapViewTouchEventListener onTouchDown : ");
        }

        @Override
        public void onTouchUp(NMapView nMapView, MotionEvent motionEvent) {
            Log.e(TAG, "OnMapViewTouchEventListener onTouchUp : ");

        }

        @Override
        public void onScroll(NMapView nMapView, MotionEvent motionEvent, MotionEvent motionEvent1) {
            Log.e(TAG, "OnMapViewTouchEventListener onScroll : ");

        }

        @Override
        public void onSingleTapUp(NMapView nMapView, MotionEvent motionEvent) {
            Log.e(TAG, "OnMapViewTouchEventListener onSingleTapUp : ");


        }
    };

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_map_ok:
                // 중심 경위도와 주소 전달.
                Intent intent = getIntent();
                String address = nowAddr.getText().toString();
                intent.putExtra("longitude", longitude);
                intent.putExtra("latitude", latitude);
                intent.putExtra("mapAddr", address);

                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}