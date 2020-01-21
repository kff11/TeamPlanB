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
import android.widget.Toast;

import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlower;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerNickname;
import com.bumptech.glide.Glide;

public class GetFlowerActivity extends AppCompatActivity {
    private EditText editText;
    private ImageView imageView;
    private ViewGroup viewGroup;
    private String nickname;
    private Animation animation_in;

    private SP_MyFlowerNickname sp_myFlowerNickname = new SP_MyFlowerNickname(this);
    private SP_MyFlower sp_myFlower = new SP_MyFlower(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_flower);

        viewGroup = (ViewGroup) findViewById(R.id.get_layout);
        viewGroup.setVisibility(View.GONE);
        imageView = null;

        imageView = (ImageView) findViewById(R.id.get_Image);

        animation_in = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_in);



        Glide.with(this).load(R.drawable.dooropen).into(imageView);
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

    public void onClick(View view) {
        editText = (EditText) findViewById(R.id.get_nickname);

        if (editText.getText().toString().length() != 0) {
            nickname = editText.getText().toString();
            sp_myFlowerNickname.setSharedString(sp_myFlower.getSharedString("MainFlower"), nickname);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "애칭을 지어주세요!!",Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public void onBackPressed(){
    }

    @Override
    public void onPause(){
        super.onPause();
        imageView = null;
    }
}
