package com.TeamPlanB.ChorrSeoul.MyFlower;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.TeamPlanB.ChorrSeoul.MainActivity;
import com.TeamPlanB.ChorrSeoul.R;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlower;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerLv;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerNickname;
import com.bumptech.glide.Glide;

public class MyFlowerLvUpActivity extends AppCompatActivity {

    private SP_MyFlower sp_myFlower;
    private SP_MyFlowerLv sp_myFlowerLv;
    private SP_MyFlowerNickname sp_myFlowerNickname;

    private String mainFlower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_flower_lv_up);

        sp_myFlower = new SP_MyFlower(this);
        sp_myFlowerLv = new SP_MyFlowerLv(this);
        sp_myFlowerNickname = new SP_MyFlowerNickname(this);

        mainFlower = sp_myFlower.getSharedString("MainFlower");

        TextView textView = (TextView) findViewById(R.id.myflower_lvup_nick);
        textView.setText(sp_myFlowerNickname.getSharedString(mainFlower) + "'s가 성장했습니다!");

        ImageView imageView = (ImageView) findViewById(R.id.myflower_lvup_image);

        TypedArray lv_0 = getResources().obtainTypedArray(R.array.myFlower_Image_0);
        TypedArray lv_1 = getResources().obtainTypedArray(R.array.myFlower_Image_1);
        TypedArray lv_2 = getResources().obtainTypedArray(R.array.myFlower_Image_2);
        TypedArray lv_3 = getResources().obtainTypedArray(R.array.myFlower_Image_3);

        for (int i = 0; i < MyFlowerList.myFlowerArray.length; i++) {
            if (mainFlower.equals(MyFlowerList.myFlowerArray[i])) {
                if (sp_myFlowerLv.getSharedInteger(MyFlowerList.myFlowerArray[i]) == 2) {
                    Glide.with(this).load(lv_1.getDrawable(i)).into(imageView);
                } else if (sp_myFlowerLv.getSharedInteger(MyFlowerList.myFlowerArray[i]) == 3) {
                    Glide.with(this).load(lv_2.getDrawable(i)).into(imageView);
                } else if (sp_myFlowerLv.getSharedInteger(MyFlowerList.myFlowerArray[i]) == 4) {
                    Glide.with(this).load(lv_3.getDrawable(i)).into(imageView);
                }
            }
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void onPause() {
        super.onPause();
        mainFlower = null;
    }
}
