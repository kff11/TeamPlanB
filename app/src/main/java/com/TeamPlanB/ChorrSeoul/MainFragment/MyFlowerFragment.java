package com.TeamPlanB.ChorrSeoul.MainFragment;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.TeamPlanB.ChorrSeoul.MyFlower.MyFlowerHelp;
import com.TeamPlanB.ChorrSeoul.MyFlower.MyFlowerList;
import com.TeamPlanB.ChorrSeoul.R;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlower;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerLv;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerNickname;
import com.bumptech.glide.Glide;

public class MyFlowerFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Intent intent;

    private ImageView main_imageView; // 메인 화분 이미지
    private Button help_btn, list_btn; // 버튼
    private TextView main_nickname, main_exp; // 텍스트뷰

    private String myFlower; // 메인 화분 이름

    private SP_MyFlower sp_myFlower;
    private SP_MyFlowerLv sp_myFlowerLv;
    private SP_MyFlowerNickname sp_myFlowerNickname;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_flower_fragment, container, false);

        sp_myFlower = new SP_MyFlower(getContext());
        sp_myFlowerLv = new SP_MyFlowerLv(getContext());
        sp_myFlowerNickname = new SP_MyFlowerNickname(getContext());

        // 메인 화분 가져오기
        if (sp_myFlower.getSharedString("MainFlower") != null) {
            myFlower = sp_myFlower.getSharedString("MainFlower");
        }
        // 내 화분 이미지 설정
        setImageView();

        // 애칭, 남은 출석 설정
        setTextView();

        help_btn = (Button) view.findViewById(R.id.myflower_help);
        list_btn = (Button) view.findViewById(R.id.myflower_list);

        // 버튼 이벤트
        help_btn.setOnClickListener(this);
        list_btn.setOnClickListener(this);

        return view;
    }

    public void setImageView() {
        main_imageView = (ImageView) view.findViewById(R.id.myflower_image);

        TypedArray lv_0 = getResources().obtainTypedArray(R.array.myFlower_Image_0);
        TypedArray lv_1 = getResources().obtainTypedArray(R.array.myFlower_Image_1);
        TypedArray lv_2 = getResources().obtainTypedArray(R.array.myFlower_Image_2);
        TypedArray lv_3 = getResources().obtainTypedArray(R.array.myFlower_Image_3);

        for (int i = 0; i < 6; i++) {
            if (myFlower.equals(MyFlowerList.myFlowerArray[i])) {
                if (sp_myFlowerLv.getSharedInteger(myFlower) == 1) {
                    Glide.with(this).load(lv_0.getDrawable(i)).into(main_imageView);
                } else if (sp_myFlowerLv.getSharedInteger(myFlower) == 2) {
                    Glide.with(this).load(lv_1.getDrawable(i)).into(main_imageView);
                } else if (sp_myFlowerLv.getSharedInteger(myFlower) == 3) {
                    Glide.with(this).load(lv_2.getDrawable(i)).into(main_imageView);
                } else {
                    Glide.with(this).load(lv_3.getDrawable(i)).into(main_imageView);
                }
            }
        }
    }
    public void setTextView(){
        main_exp = (TextView) view.findViewById(R.id.myflower_exp);
        main_nickname = (TextView) view.findViewById(R.id.myflower_nickname) ;

        main_exp.setText("성장까지 남은 출석 " + sp_myFlowerLv.getSharedInteger(myFlower) + "/4");
        main_nickname.setText(sp_myFlowerNickname.getSharedString(myFlower));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myflower_list:
                startActivity(new Intent(getActivity(), MyFlowerList.class));
                getActivity().overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
            case R.id.myflower_help:
                startActivity(new Intent(getActivity(), MyFlowerHelp.class));
                getActivity().overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        main_imageView = null;
        main_nickname = null;
        main_exp = null;
        intent = null;
        myFlower = null;
        view = null;
    }
}


