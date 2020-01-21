package com.TeamPlanB.ChorrSeoul.Infomation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.TeamPlanB.ChorrSeoul.R;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

public class InformationMap extends AppCompatActivity implements OnMapReadyCallback {

    private MapFragment mapFragment;
    private String GardenPlace;

    private TextView map_Train;
    private TextView map_Bus;

    private Toolbar toolbar; // 액션바

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_map);

        Intent intent = getIntent();

        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (intent.getStringExtra("GardenName") != null) {
            GardenPlace = intent.getStringExtra("GardenName");
        }

        setInfo();

        FragmentManager fm = getSupportFragmentManager();
        mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);
    }

    private void setInfo() {
        map_Train = findViewById(R.id.map_train);
        map_Bus = findViewById(R.id.map_bus);

        switch (GardenPlace) {
            case "마곡식물원":
                map_Train.setText("- 9호선 마곡나루역 3,4번 출구\n- 9호선 양천향교역 8번 출구\n- 5호선 마곡역 2번 출구");
                map_Bus.setText("- 지선 : 6629, 6631, 6642, 6645, 6648, 6712\n- 마을 : 강서 05-1 , 강서 07\n" +
                        "- 간선 : 601, 605, 654, 672\n- 광역 : 2000, 3000, 3000A");
                break;
            case "관악산야외식물원":
                map_Train.setText("- 2호선 서울대입구역 2번 출구");
                map_Bus.setText("- 지선 : 5511, 5513, 5516\n- 간선 : 501");
                break;
            case "남산야외식물원":
                map_Train.setText("- 6호선 한강진역 1번 출구\n- 6호선 이태원역 2번 출구");
                map_Bus.setText("- 마을 : 용산 03\n- 간선 : 402, 405");
                break;
            case "서울대공원식물원":
                map_Train.setText("- 4호선 대공원역 1,2,3번 출구");
                map_Bus.setText("- 간선 : 502");
                break;
            case "서울어린이대공원식물원":
                map_Train.setText("- 5호선 아차산역 4번 출구\n- 7호선 어린이대공원역 1번 출구");
                map_Bus.setText("- 일반 : 70, 119\n- 지선 : 2216, 2221, 2311, 3216, 4212\n- 간선 : 130, 303, 320, 370, 721, N30, N61, N62\n" +
                        "- 광역 : 9403\n- 직행 : 3500, 9301\n- 시외 : 3001\n- 공항 : 6013");
                break;
            case "서울숲곤충식물원":
                map_Train.setText("- 2호선 뚝섬역 8번 출구\n- 분당선 서울숲역 3번 출구");
                map_Bus.setText("- 지선 : 2014, 2224, 2412, 2413\n- 마을 : 성동 13\n- 간선 : 121, 141, 145, 148, 463");
                break;
            case "서울창포원":
                map_Train.setText("- 1,7호선 도봉산역 2번 출구");
                map_Bus.setText("- 일반 : 5, 7, 10, 10-1, 10-2, 10-3, 36, 39, 39-1, 39-4, 72, 72-3, 118, 133\n- 마을 : 도봉 09" +
                        "\n- 간선 : 106, 107, 108, 140, 150, 160, N16\n" +
                        "- 직행 - 1100\n- 시외 : 3003, 3005");
                break;
            case "전통염료식물원":
                map_Train.setText("- 경의선, 4호선 이촌역 2번 출구");
                map_Bus.setText("- 지선 : 2016, 3012, 6211\n- 간선 : 100, 400, 502\n- 공항 : 6010");
                break;
            case "푸른수목원":
                map_Train.setText("- 7호선 온수역 2번 출구");
                map_Bus.setText("- 지선 : 6614, 6615\n- 마을 : 021-1, 구로 07");
                break;
            case "홍릉수목원":
                map_Train.setText("- 6호선 고려대역 3번 출구");
                map_Bus.setText("- 지선 : 1226\n- 간선 : 201, 273");
                break;

        }
    }

    // 네이버 지도
    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        // 줌 설정
        naverMap.setMinZoom(13.0);
        naverMap.setMaxZoom(17.0);
        Marker marker = new Marker();
        LatLng location = null;

        switch (GardenPlace) {
            case "마곡식물원":
                location = new LatLng(37.566491, 126.832003);
                marker.setCaptionText("서울식물원");
                break;
            case "관악산야외식물원":
                location = new LatLng(37.461672, 126.948043);
                marker.setCaptionText("관악산야외식물원");
                break;
            case "남산야외식물원":
                location = new LatLng(37.542395, 126.996276);
                marker.setCaptionText("남산야외식물원");
                break;
            case "서울대공원식물원":
                location = new LatLng(37.425830, 127.022805);
                marker.setCaptionText("서울대공원식물원");
                break;
            case "서울어린이대공원식물원":
                location = new LatLng(37.548238, 127.080840);
                marker.setCaptionText("서울어린이대공원식물원");
                break;
            case "서울숲곤충식물원":
                location = new LatLng(37.541406, 127.039209);
                marker.setCaptionText("서울숲곤충식물원");
                break;
            case "서울창포원":
                location = new LatLng(37.689350, 127.048076);
                marker.setCaptionText("서울창포원");
                break;
            case "전통염료식물원":
                location = new LatLng(37.524435, 126.981605);
                marker.setCaptionText("전통염료식물원");
                break;
            case "푸른식물원":
                location = new LatLng(37.483986, 126.825598);
                marker.setCaptionText("푸른식물원");
                break;
            case "홍릉식물원":
                location = new LatLng(37.593534, 127.043963);
                marker.setCaptionText("홍릉식물원");
                break;

        }

        // 카메라 위치 지정
        CameraPosition cameraPosition = new CameraPosition(location, 13);

        // 마커 설정
        marker.setPosition(location);

        // 마커 표시, 카메라 이동
        marker.setMap(naverMap);
        naverMap.setCameraPosition(cameraPosition);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
}

