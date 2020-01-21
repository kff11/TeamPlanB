package com.TeamPlanB.ChorrSeoul;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.TeamPlanB.ChorrSeoul.MainFragment.GardenListFragment;
import com.TeamPlanB.ChorrSeoul.MainFragment.HomeFragment;
import com.TeamPlanB.ChorrSeoul.MainFragment.LikeFragment;
import com.TeamPlanB.ChorrSeoul.MainFragment.MyFlowerFragment;
import com.TeamPlanB.ChorrSeoul.MyFlower.MyFlowerLvUpActivity;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_Like;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlower;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerLv;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private long pressedTime; // 뒤로가기 시간

    private FragmentManager fragmentManager = getSupportFragmentManager();

    // 하단바 fragment 들
    private HomeFragment homeFragment = new HomeFragment();
    private GardenListFragment gardenListFragment = new GardenListFragment();
    private LikeFragment likeFragment = new LikeFragment();
    private MyFlowerFragment myFlowerFragment = new MyFlowerFragment();

    // SharedPreference
    private SP_Like sp_like = new SP_Like(this);
    private SP_MyFlower sp_myFlower = new SP_MyFlower(this);
    private SP_MyFlowerLv sp_myFlowerLv = new SP_MyFlowerLv(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // 첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();

        // 하단 바 이벤트
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_home: {
                        transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_list: {
                        transaction.replace(R.id.frame_layout, gardenListFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_like: {
                        transaction.replace(R.id.frame_layout, likeFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_myflower: {
                        transaction.replace(R.id.frame_layout, myFlowerFragment).commitAllowingStateLoss();
                        break;
                    }
                }
                return true;
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면

                try {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //data를 json으로 변환
                    JSONObject obj = new JSONObject(result.getContents());
                    String qrName = obj.getString("name");
                    if (qrName != null) {
                        switch (qrName) {
                            case "magok":
                                Toast.makeText(this, "마곡식물원 출석되었습니다!", Toast.LENGTH_LONG).show();
                                break;
                            case "gwan":
                                Toast.makeText(this, "관악산야외식물원 출석되었습니다!", Toast.LENGTH_LONG).show();
                                break;
                            case "namsan":
                                Toast.makeText(this, "남산야외식물원 출석되었습니다!", Toast.LENGTH_LONG).show();
                                break;
                            case "seoul":
                                Toast.makeText(this, "서울대공원식물원 출석되었습니다!", Toast.LENGTH_LONG).show();
                                break;
                            case "child":
                                Toast.makeText(this, "서울어린이대공원식물원 출석되었습니다!", Toast.LENGTH_LONG).show();
                                break;
                            case "bug":
                                Toast.makeText(this, "서울숲곤충식물원 출석되었습니다!", Toast.LENGTH_LONG).show();
                                break;
                            case "changpo":
                                Toast.makeText(this, "서울창포원 출석되었습니다!", Toast.LENGTH_LONG).show();
                                break;
                            case "dyes":
                                Toast.makeText(this, "전통염료식물원 출석되었습니다!", Toast.LENGTH_LONG).show();
                                break;
                            case "blue":
                                Toast.makeText(this, "푸른수목원 출석되었습니다!", Toast.LENGTH_LONG).show();
                                break;
                            case "hong":
                                Toast.makeText(this, "홍릉원수목원 출석되었습니다!", Toast.LENGTH_LONG).show();
                                break;
                        }
                        while (true) {
                            if (sp_myFlowerLv.getSharedInteger(sp_myFlower.getSharedString("MainFlower")) < 4) {
                                sp_myFlowerLv.setSharedIntegerPlus(sp_myFlower.getSharedString("MainFlower"));
                                if (sp_myFlowerLv.getSharedInteger(sp_myFlower.getSharedString("MainFlower")) == 4
                                        && sp_myFlower.getSharedInteger("myFlowerCount") == 6) {
                                    sp_myFlower.setSharedboolean("FINISH", true);
                                    startActivity(new Intent(this, MyFlowerLvUpActivity.class));
                                    finish();
                                    break;
                                } else if (sp_myFlowerLv.getSharedInteger(sp_myFlower.getSharedString("MainFlower")) == 4) {
                                    startActivity(new Intent(this, NewFlowerActivity.class));
                                    finish();
                                    break;
                                }
                                startActivity(new Intent(this, MyFlowerLvUpActivity.class));
                                finish();
                                break;
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void onBackPressed() {
        if (pressedTime == 0) {
            Toast.makeText(MainActivity.this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
            pressedTime = System.currentTimeMillis();
        } else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);
            if (seconds > 2000) {
                Toast.makeText(MainActivity.this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
                pressedTime = 0;
            } else {
                super.onBackPressed();
                finish();
            }
        }
    }

}