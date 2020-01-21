package com.TeamPlanB.ChorrSeoul.MainFragment;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.TeamPlanB.ChorrSeoul.Adapter.HomeRecyclerViewAdapter;
import com.TeamPlanB.ChorrSeoul.RecyclerViewItem;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerLv;
import com.TeamPlanB.ChorrSeoul.R;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlower;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.Arrays;
import java.util.List;

import kr.go.seoul.airquality.AirQualityButtonTypeB;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private View view;

    // 출석체크
    private ImageView stamp_Bg;
    private Button stamp_Btn;
    private ImageView stamp_01;
    private ImageView stamp_02;
    private ImageView stamp_03;
    private ImageView stamp_04;

    private String season; // 계절

    // 큐알코드
    private IntentIntegrator qrScan;

    // 서울시 주요기능 API KEY
    private String key = "46706d73596b666636334244645469";
    private AirQualityButtonTypeB buttonTypeB;

    // 추천 식물원
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private HomeRecyclerViewAdapter adapter;

    // 쉐어드프리퍼런스
    private SP_MyFlower sp_myFlower;
    private SP_MyFlowerLv sp_myFlowerLv;

    private MultiTransformation multiOption; // 글라이드 옵션

    private LayoutInflater _inflater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        _inflater = getLayoutInflater();

        // 쉐어드프리퍼런스 클래스
        sp_myFlower = new SP_MyFlower(getContext());
        sp_myFlowerLv = new SP_MyFlowerLv(getContext());

        // 이미지 뷰 센터 크롭 후 둥글게 만들기
        multiOption = new MultiTransformation(new CenterCrop(), new RoundedCorners(30));

        // 출석체크 설정
        stamp_Btn = (Button) view.findViewById(R.id.stamp_btn);
        stamp_Btn.setOnClickListener(this);
        setStamp();

        // 서울시 주요기능 API 대기정보
        buttonTypeB = (AirQualityButtonTypeB) view.findViewById(R.id.type_B);
        buttonTypeB.setOpenAPIKey(key);
        buttonTypeB.setButtonImage(R.drawable.icon_info);

        // 추천 식물원 설정
        setImageView_rcmd();

        return view;
    }

    public void setImageView_rcmd() {
        recyclerView = view.findViewById(R.id.home_recyclerView_rcmd);

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        adapter = new HomeRecyclerViewAdapter();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        TypedArray img_gardenArray = getResources().obtainTypedArray(R.array.rcmdGarden_Image);
        List<String> title_gardenArray = Arrays.asList(getResources().getStringArray(R.array.rcmdGarden_name));

        for (int i = 0; i < img_gardenArray.length(); i++) {
            RecyclerViewItem item = new RecyclerViewItem();
            item.setTitle(title_gardenArray.get(i));
            item.setIcon(img_gardenArray.getDrawable(i));
            adapter.addItem(item);
        }
        adapter.notifyDataSetChanged();
    }

    public void setStamp() {
        stamp_Bg = (ImageView) view.findViewById(R.id.stamp);
        stamp_Btn = view.findViewById(R.id.stamp_btn);
        stamp_01 = view.findViewById(R.id.stamp1);
        stamp_02 = view.findViewById(R.id.stamp2);
        stamp_03 = view.findViewById(R.id.stamp3);
        stamp_04 = view.findViewById(R.id.stamp4);

        ImageView[] stampArray = {stamp_01, stamp_02, stamp_03, stamp_04};
        season = sp_myFlower.getSharedString("season");

        // 출석 버튼 이미지 설정
        switch (season) {
            case "winter":
                stamp_Bg.setBackgroundResource(R.drawable.stamp_bg_winter);
                for (int i = 1; i <= stampArray.length; i++) {
                    if (sp_myFlowerLv.getSharedInteger(sp_myFlower.getSharedString("MainFlower")) >= i) {
                        Glide.with(this).load(R.drawable.stamp_winter_press).into(stampArray[i - 1]);
                    } else {
                        Glide.with(this).load(R.drawable.stamp_winter).into(stampArray[i - 1]);
                    }
                }
                break;
            case "spring":
                stamp_Bg.setBackgroundResource(R.drawable.stamp_bg_spring);
                for (int i = 1; i <= stampArray.length; i++) {
                    if (sp_myFlowerLv.getSharedInteger(sp_myFlower.getSharedString("MainFlower")) >= i) {
                        Glide.with(this).load(R.drawable.stamp_spring_press).into(stampArray[i - 1]);
                    } else {
                        Glide.with(this).load(R.drawable.stamp_spring).into(stampArray[i - 1]);
                    }
                }
                break;
            case "summer":

                stamp_Bg.setBackgroundResource(R.drawable.stamp_bg_summer);
                for (int i = 1; i <= stampArray.length; i++) {
                    if (sp_myFlowerLv.getSharedInteger(sp_myFlower.getSharedString("MainFlower")) >= i) {
                        Glide.with(this).load(R.drawable.stamp_summer_press).into(stampArray[i - 1]);
                    } else {
                        Glide.with(this).load(R.drawable.stamp_summer).into(stampArray[i - 1]);
                    }
                }
                break;
            case "fall":
                //stamp_Bg.setBackgroundResource(R.drawable.stamp_bg_autumn);
                Glide.with(this).load(R.drawable.stamp_bg_autumn).apply(RequestOptions.bitmapTransform(multiOption)).into(stamp_Bg);
                for (int i = 1; i <= stampArray.length; i++) {
                    if (sp_myFlowerLv.getSharedInteger(sp_myFlower.getSharedString("MainFlower")) >= i) {
                        Glide.with(this).load(R.drawable.stamp_autumn_press).into(stampArray[i - 1]);
                    } else {
                        Glide.with(this).load(R.drawable.stamp_autumn).into(stampArray[i - 1]);
                    }
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stamp_btn:
                if (sp_myFlower.getSharedboolean("FINISH") != true) {
                    qrScan = new IntentIntegrator(getActivity());
                    qrScan.setOrientationLocked(false);
                    qrScan.setPrompt("큐알코드를 태그하세요!");
                    qrScan.initiateScan();
                }
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        stamp_01 = null;
        stamp_02 = null;
        stamp_03 = null;
        stamp_04 = null;
        stamp_Bg = null;
        view = null;
    }
}
