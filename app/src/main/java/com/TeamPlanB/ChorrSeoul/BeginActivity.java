package com.TeamPlanB.ChorrSeoul;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.TeamPlanB.ChorrSeoul.MyFlower.MyFlowerList;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_Like;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlower;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerLv;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerNickname;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class BeginActivity extends AppCompatActivity {
    private long pressedTime;

    private int myFlowerNum;
    private Random random;
    private List<String> gardenPlaces;
    private EditText editText;
    private String nickname;
    private Date currentTime;

    private ImageView imageView;
    private Animation animation_in;
    private ViewGroup viewGroup;

    private SP_MyFlower sp_myFlower = new SP_MyFlower(this);
    private SP_MyFlowerLv sp_myFlowerLv = new SP_MyFlowerLv(this);
    private SP_Like sp_like = new SP_Like(this);
    private SP_MyFlowerNickname sp_myFlowerNickname = new SP_MyFlowerNickname(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        imageView = (ImageView) findViewById(R.id.begin_gif);
        viewGroup = (ViewGroup) findViewById(R.id.begin_layout);

        // 뷰 숨겨두기
        viewGroup.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);

        animation_in = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_in);


        imageView.setVisibility(View.VISIBLE);
        Glide.with(getApplicationContext()).load(R.drawable.dooropen).into(imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.setImageResource(R.drawable.dooropen_ex);
            }
        }, 3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewGroup.setVisibility(View.VISIBLE);
                viewGroup.startAnimation(animation_in);
            }
        }, 3300);
    }

    public void setMyFlower() {
        currentTime = Calendar.getInstance().getTime();
        random = new Random();
        myFlowerNum = random.nextInt(6);
        gardenPlaces = Arrays.asList(getResources().getStringArray(R.array.allGarden));

        for (int i = 0; i < MyFlowerList.myFlowerArray.length; i++) {
            sp_myFlower.setSharedboolean(MyFlowerList.myFlowerArray[i], false);
            sp_myFlowerLv.setSharedInteger(MyFlowerList.myFlowerArray[i], 0);
            sp_myFlowerNickname.setSharedString(MyFlowerList.myFlowerArray[i], "");
        }

        for (int i = 0; i < gardenPlaces.size(); i++) {
            sp_like.setSharedboolean(gardenPlaces.get(i), false);
            //sharedPreferencesUtil.setSharedString(gardenPlaces.get(i), gardenPlaces.get(i));
        }

        // 첫 화분 설정
        sp_myFlower.setSharedboolean(MyFlowerList.myFlowerArray[myFlowerNum], true);
        sp_myFlowerNickname.setSharedString(MyFlowerList.myFlowerArray[myFlowerNum], nickname);
        sp_myFlowerLv.setSharedInteger(MyFlowerList.myFlowerArray[myFlowerNum], 1);
        sp_like.setSharedString(MyFlowerList.myFlowerArray[myFlowerNum], new SimpleDateFormat("yyyy / MM / dd", Locale.getDefault()).format(currentTime));
        sp_myFlower.setSharedString("MainFlower", MyFlowerList.myFlowerArray[myFlowerNum]);
        sp_myFlower.setSharedInteger("myFlowerCount", 1);
        sp_myFlower.setSharedboolean("FINISH", false);

        // 첫 시작 판별
        sp_myFlower.setSharedboolean("FIRSTSTART", true);
    }

    public void onClick(View view) {
        // 애칭 이벤트
        editText = (EditText) findViewById(R.id.begin_firstNickname);

        if (editText.getText().toString().length() != 0) {
            nickname = editText.getText().toString();
            setMyFlower();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "애칭을 지어주세요!!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        if (pressedTime == 0) {
            Toast.makeText(BeginActivity.this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
            pressedTime = System.currentTimeMillis();
        } else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);
            if (seconds > 2000) {
                Toast.makeText(BeginActivity.this, " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
                pressedTime = 0;
            } else {
                super.onBackPressed();
                finish();
            }
        }
    }

    public void onPause() {
        super.onPause();
        imageView = null;
        random = null;
        gardenPlaces = null;
        editText = null;
        nickname = null;
        currentTime = null;
        animation_in = null;
        viewGroup = null;
    }
}
