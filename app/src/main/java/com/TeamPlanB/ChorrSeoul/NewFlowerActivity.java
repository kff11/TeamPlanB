package com.TeamPlanB.ChorrSeoul;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.TeamPlanB.ChorrSeoul.MyFlower.MyFlowerList;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_Like;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlower;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerLv;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerNickname;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class NewFlowerActivity extends AppCompatActivity {
    private TextView nickname;
    private ImageView imageView;
    private String mainFlower;
    private Date currentTime;

    private TextView first;
    private ViewGroup second, third;
    private Animation animation_in;

    private SP_MyFlowerNickname sp_myFlowerNickname = new SP_MyFlowerNickname(this);
    private SP_MyFlower sp_myFlower = new SP_MyFlower(this);
    private SP_MyFlowerLv sp_myFlowerLv = new SP_MyFlowerLv(this);
    private SP_Like sp_like = new SP_Like(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_flower);

        mainFlower = sp_myFlower.getSharedString("MainFlower");

        animation_in = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_in);
        first = (TextView) findViewById(R.id.new_flower_first);
        second = (ViewGroup) findViewById(R.id.new_flower_second);
        third = (ViewGroup) findViewById(R.id.new_flower_third);

        second.setVisibility(View.GONE);
        third.setVisibility(View.GONE);


        nickname = (TextView) findViewById(R.id.final_nickname);
        imageView = (ImageView) findViewById(R.id.new_imageView);

        nickname.setText(sp_myFlowerNickname.getSharedString(mainFlower));

        switch (mainFlower) {
            case "섬개야광나무":
                Glide.with(this).load(R.drawable.myflower_sum_3).into(imageView);
                break;
            case "기생꽃":
                Glide.with(this).load(R.drawable.myflower_giseng_3).into(imageView);
                break;
            case "세뿔투구꽃":
                Glide.with(this).load(R.drawable.myflower_sebul_3).into(imageView);
                break;
            case "광릉요강꽃":
                Glide.with(this).load(R.drawable.myflower_yo_3).into(imageView);
                break;
            case "풍란":
                Glide.with(this).load(R.drawable.myflower_pungran_3).into(imageView);
                break;
            case "가시연꽃":
                Glide.with(this).load(R.drawable.myflower_gasi_3).into(imageView);
                break;
        }

        first.setAnimation(animation_in);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                second.setVisibility(View.VISIBLE);
                second.startAnimation(animation_in);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        third.setVisibility(View.VISIBLE);
                        third.startAnimation(animation_in);
                    }
                }, 2700);
            }
        }, 2700);

    }

    public void onClick(View view) {
        while (true) {
            int num = new Random().nextInt(MyFlowerList.myFlowerArray.length);
            currentTime = Calendar.getInstance().getTime();
            if (sp_myFlower.getSharedboolean(MyFlowerList.myFlowerArray[num]) != true) {
                sp_myFlower.setSharedboolean(MyFlowerList.myFlowerArray[num], true);
                sp_myFlowerLv.setSharedInteger(MyFlowerList.myFlowerArray[num], 1);
                sp_like.setSharedString(MyFlowerList.myFlowerArray[num], new SimpleDateFormat("yyyy / MM / dd", Locale.getDefault()).format(currentTime));
                sp_myFlower.setSharedString("MainFlower", MyFlowerList.myFlowerArray[num]);
                sp_myFlower.setSharedIntegerPlus("myFlowerCount");
                break;
            }
        }
        startActivity(new Intent(this, GetFlowerActivity.class));
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        finish();
    }
    @Override
    public void onBackPressed(){
    }

    @Override
    public void onPause(){
        super.onPause();
        nickname = null;
        imageView = null;
        currentTime = null;
        first = null;
        second = null;
        third = null;
        animation_in = null;
    }
}
