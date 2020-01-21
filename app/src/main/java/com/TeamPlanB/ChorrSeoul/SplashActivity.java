package com.TeamPlanB.ChorrSeoul;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlower;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerLv;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerNickname;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SplashActivity extends AppCompatActivity {
    private SP_MyFlower sp_myFlower = new SP_MyFlower(this);
    private SP_MyFlowerLv sp_myFlowerLv = new SP_MyFlowerLv(this);
    private SP_MyFlowerNickname sp_myFlowerNickname = new SP_MyFlowerNickname(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        // 날짜 받기
        Date currentTime = Calendar.getInstance().getTime();
        String month = new SimpleDateFormat("MM", Locale.getDefault()).format(currentTime);

        switch (month) {
            case "12":
            case "01":
            case "02":
                sp_myFlower.setSharedString("season", "winter");
                break;
            case "03":
            case "04":
            case "05":
                sp_myFlower.setSharedString("season", "spring");
                break;
            case "06":
            case "07":
            case "08":
                sp_myFlower.setSharedString("season", "summer");
                break;
            case "09":
            case "10":
            case "11":
                sp_myFlower.setSharedString("season", "fall");
                break;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 처음 시작인지 판별
                if (sp_myFlower.getSharedboolean("FIRSTSTART")) {
                    // 출석 오류 잡기
                    if (sp_myFlowerLv.getSharedInteger(sp_myFlower.getSharedString("MainFlower")) == 4
                            && sp_myFlower.getSharedboolean("FINISH") == false) {
                        startActivity(new Intent(getApplicationContext(), NewFlowerActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        finish();
                        overridePendingTransition(0, 0);
                    }
                    // 이름 못 짓고 종료했을 경우
                    else if (sp_myFlowerLv.getSharedInteger(sp_myFlower.getSharedString("MainFlower")) >= 1) {
                        if (sp_myFlowerNickname.getSharedString(sp_myFlower.getSharedString("MainFlower")).equals("")) {
                            startActivity(new Intent(getApplicationContext(), GetFlowerActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            finish();
                            overridePendingTransition(0, 0);
                        } else {
                            Log.d("tq", "tq");
                            startActivity(new Intent(getApplicationContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            finish();
                            overridePendingTransition(0, 0);
                        }
                    }
                } else {
                    startActivity(new Intent(getApplicationContext(), BeginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    finish();
                    overridePendingTransition(0, 0);
                }

            }
        },  1000);
    }

    @Override
    public void onBackPressed() {
    }

}
