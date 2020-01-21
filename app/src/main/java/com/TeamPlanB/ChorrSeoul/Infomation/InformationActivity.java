package com.TeamPlanB.ChorrSeoul.Infomation;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.TeamPlanB.ChorrSeoul.Adapter.PageAdapter;
import com.TeamPlanB.ChorrSeoul.R;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_Like;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener {

    private String gardenPlace; // 인텐트로 받아온 식물원
    private Intent intent; // 사용할 인텐트
    private ArrayList<View> picViews; // 사진 배열
    private View pic1, pic2, pic3; // 사진 뷰
    private ViewGroup viewLayout; // 레이아웃
    private ImageView img1, img2, img3, pointImg; // 사진 뷰에 들어갈 사진
    // 인디케이터
    private ImageView[] pointArray;
    private ViewGroup viewPoints;

    private LayoutInflater inflater; // inflater
    private ViewPager viewPager; // 식물원 뷰페이저
    private TextView gardenName, gardenAddress, gardenPrice, gardenTime, gardenRent; // 식물원 정보
    private Toolbar toolbar; // 액션바

    private SP_Like sp_like = new SP_Like(this); // 좋아요 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = getLayoutInflater();
        viewLayout = (ViewGroup) inflater.inflate(R.layout.activity_information, null);
        setContentView(viewLayout);

        // 툴바 설정
        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 식물원 이름 가져오기
        intent = getIntent();
        if (intent.getStringExtra("GardenName") != null) {
            gardenPlace = intent.getStringExtra("GardenName");
        }

        // 식물원 사진 설정
        setImageView();

        // 식물원 정보 설정
        setInfo();

        // 인디케이터 설정
        setIndicator();

        // 뷰페이저 설정
        viewPager = (ViewPager) viewLayout.findViewById(R.id.information_viewPager);

        // 뷰페이저 어뎁터 설정
        viewPager.setAdapter(new PageAdapter(picViews, this));
        viewPager.addOnPageChangeListener(new InformationPageChangeListener(pointArray));

    }

    // 식물원 사진 설정
    public void setImageView() {
        picViews = new ArrayList<View>();

        pic1 = inflater.inflate(R.layout.viewpager_image, null);
        pic2 = inflater.inflate(R.layout.viewpager_image, null);
        pic3 = inflater.inflate(R.layout.viewpager_image, null);

        img1 = pic1.findViewById(R.id.viewPager_Image_01);
        img2 = pic2.findViewById(R.id.viewPager_Image_02);
        img3 = pic3.findViewById(R.id.viewPager_Image_03);

        switch (gardenPlace) {
            case "마곡식물원":
                Glide.with(this).load(R.drawable.magok1).into(img1);
                Glide.with(this).load(R.drawable.magok2).into(img2);
                Glide.with(this).load(R.drawable.magok3).into(img3);
                break;
            case "관악산야외식물원":
                Glide.with(this).load(R.drawable.gwan1).into(img1);
                Glide.with(this).load(R.drawable.gwan2).into(img2);
                Glide.with(this).load(R.drawable.gwan3).into(img3);
                break;
            case "남산야외식물원":
                Glide.with(this).load(R.drawable.south1).into(img1);
                Glide.with(this).load(R.drawable.south2).into(img2);
                Glide.with(this).load(R.drawable.south3).into(img3);
                break;
            case "서울대공원식물원":
                Glide.with(this).load(R.drawable.seoul1).into(img1);
                Glide.with(this).load(R.drawable.seoul2).into(img2);
                Glide.with(this).load(R.drawable.seoul3).into(img3);
                break;
            case "서울어린이대공원식물원":
                Glide.with(this).load(R.drawable.child1).into(img1);
                Glide.with(this).load(R.drawable.child2).into(img2);
                Glide.with(this).load(R.drawable.child3).into(img3);
                break;
            case "서울숲곤충식물원":
                Glide.with(this).load(R.drawable.bug1).into(img1);
                Glide.with(this).load(R.drawable.bug2).into(img2);
                Glide.with(this).load(R.drawable.bug3).into(img3);
                break;
            case "서울창포원":
                Glide.with(this).load(R.drawable.changpo1).into(img1);
                Glide.with(this).load(R.drawable.changpo2).into(img2);
                Glide.with(this).load(R.drawable.changpo3).into(img3);
                break;
            case "전통염료식물원":
                Glide.with(this).load(R.drawable.dyes1).into(img1);
                Glide.with(this).load(R.drawable.dyes2).into(img2);
                Glide.with(this).load(R.drawable.dyes3).into(img3);
                break;
            case "푸른수목원":
                Glide.with(this).load(R.drawable.blue1).into(img1);
                Glide.with(this).load(R.drawable.blue2).into(img2);
                Glide.with(this).load(R.drawable.blue3).into(img3);
                break;
            case "홍릉수목원":
                Glide.with(this).load(R.drawable.hong1).into(img1);
                Glide.with(this).load(R.drawable.hong2).into(img2);
                Glide.with(this).load(R.drawable.hong3).into(img3);
                break;
        }
        picViews.add(pic1);
        picViews.add(pic2);
        picViews.add(pic3);
    }

    // 식물원 정보 설정
    public void setInfo() {
        gardenName = findViewById(R.id.garden_name);
        gardenAddress = findViewById(R.id.map_train);
        gardenPrice = findViewById(R.id.garden_price);
        gardenRent = findViewById(R.id.garden_rent);
        gardenTime = findViewById(R.id.garden_time);

        switch (gardenPlace) {
            case "마곡식물원":
                gardenName.setText("서울식물원");
                gardenAddress.setText(R.string.magokAdderss);
                gardenPrice.setText("어른 5,000원 \n청소년(13세 - 18세) 3,000원 \n어린이(6세 - 12세) 2,000원 \n제로페이 결제 시 30% 할인");
                gardenTime.setText("매일 09:30 - 18:00 - 평시(3~10월) / 입장마감 17:00 \n" +
                        "매일 09:30 - 17:00 - 동절기(11~2월) / 입장마감 16:00\n" +
                        "월요일 휴무 - (주제원 휴관) \n" +
                        "열린숲, 호수원, 습지원 연중무휴");
                gardenRent.setText("신분증 필수 지참\n" + "유모차 : 36개월 미만 유아 동반 방문객 선착순 대여\n" +
                        "휠체어 : 보행이 불편한 장애인 방문객 우선 대여");
                break;
            case "관악산야외식물원":
                gardenName.setText("관악산야외식물원");
                gardenAddress.setText(R.string.gwanakAdderss);
                gardenPrice.setText("무료");
                gardenTime.setText("매일 00:00 - 24:00");
                gardenRent.setText("");
                break;
            case "남산야외식물원":
                gardenName.setText("남산야외식물원");
                gardenAddress.setText(R.string.namsanAdderss);
                gardenPrice.setText("무료");
                gardenTime.setText("매일 00:00 - 24:00");
                gardenRent.setText("");
                break;
            case "서울대공원식물원":
                gardenName.setText("서울대공원식물원");
                gardenAddress.setText(R.string.bigparkAdderss);
                gardenPrice.setText("어른 5,000원\n청소년 3,000원\n어린이 2,000원\n제로페이 결제 시 30% 할인");
                gardenTime.setText("09:00 - 19:00 (3월 - 10월)\n09:00 - 18:00 (11월 - 2월)\n" +
                        "온실식물원 : 09:00 - 17:00");
                gardenRent.setText("유모차 및 휠체어");
                break;
            case "서울어린이대공원식물원":
                gardenName.setText("서울어린이대공원식물원");
                gardenAddress.setText(R.string.childAdderss);
                gardenPrice.setText("무료");
                gardenTime.setText("매일 10:00 - 17:00\n월요일 13:00 - 17:00");
                gardenRent.setText("");
                break;
            case "서울숲곤충식물원":
                gardenName.setText("서울숲곤충식물원");
                gardenAddress.setText(R.string.insectAdderss);
                gardenPrice.setText("무료");
                gardenTime.setText("매일 10:00 - 17:00\n월요일 휴무");
                gardenRent.setText("");
                break;
            case "서울창포원":
                gardenName.setText("서울창포원");
                gardenAddress.setText(R.string.changpoAdderss);
                gardenPrice.setText("무료");
                gardenTime.setText("평일 07:00 - 22:00");
                gardenRent.setText("");
                break;
            case "전통염료식물원":
                gardenName.setText("전통염료식물원");
                gardenAddress.setText(R.string.dyeAdderss);
                gardenPrice.setText("무료");
                gardenTime.setText("월요일 휴무\n화, 목, 금요일 09:00 - 18:00\n수, 토요일 09:00 - 21:00\n일요일 09:00 - 19:30\n1월 1일 휴원");
                gardenRent.setText("");
                break;
            case "푸른수목원":
                gardenName.setText("푸른수목원");
                gardenAddress.setText(R.string.blueAdderss);
                gardenPrice.setText("무료");
                gardenTime.setText("매일 05:00 - 22:00 / 연중무휴");
                gardenRent.setText("");
                break;
            case "홍릉수목원":
                gardenName.setText("홍릉수목원");
                gardenAddress.setText(R.string.hongleungAdderss);
                gardenPrice.setText("무료");
                gardenTime.setText("평일 10:30 - 15:30 / 3월~11월 (화~금) - 숲해설(10시30분,13시30분,15시30분)\n" +
                        "주말 09:00 - 18:00 / 3월~10월 (하절기) - 자유관람\n" +
                        "주말 10:30 - 14:00 / 3월~11월 - 숲해설 10시30분,14시)\n" +
                        "주말 09:00 - 17:00 / 11월~2월 (동절기) - 자유관람\n" +
                        "월요일 휴관");
                gardenRent.setText("");
                break;
        }
    }

    // 사진 다음, 이전
    public void pictureMove(View view) {
        switch (view.getId()) {
            case R.id.right:
                viewPager.setCurrentItem(getItem(+1), true);
                break;
            case R.id.left:
                viewPager.setCurrentItem(getItem(-1), true);
                break;
        }
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    // 인디케이터 설정
    public void setIndicator() {
        pointArray = new ImageView[picViews.size()];

        viewPoints = (ViewGroup) viewLayout.findViewById(R.id.information_viewGroup);
        viewPager = (ViewPager) viewLayout.findViewById(R.id.information_viewPager);

        for (int i = 0; i < picViews.size(); i++) {
            pointImg = new ImageView(InformationActivity.this);
            pointImg.setLayoutParams(new ViewGroup.LayoutParams(30, 30));
            pointImg.setPadding(40, 0, 40, 50);
            pointArray[i] = pointImg;

            if (i == 0) {
                pointArray[i].setBackgroundResource(R.drawable.circle_black);
            } else {
                pointArray[i].setBackgroundResource(R.drawable.circle_grey);
            }

            viewPoints.addView(pointArray[i]);
        }
    }

    // 버튼 클릭 이벤트
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.site:
                switch (gardenPlace) {
                    case "마곡식물원":
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://botanicpark.seoul.go.kr/")));
                        break;
                    case "서울대공원식물원":
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://grandpark.seoul.go.kr/main/plantMain.do?headerId=52382")));
                        break;
                    case "서울어린이대공원식물원":
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sisul.or.kr/open_content/childrenpark/guidance/nature/botanical.jsp")));
                        break;
                    case "서울창포원":
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://parks.seoul.go.kr/template/sub/irisgarden.do")));
                        break;
                    case "푸른수목원":
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://parks.seoul.go.kr/template/sub/pureun.do")));
                        break;
                    default:
                        Toast.makeText(InformationActivity.this, "사이트가 없습니다.", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case R.id.call:
                switch (gardenPlace) {
                    case "마곡식물원":
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02120"));
                        break;
                    case "관악산야외식물원":
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-880-3675"));
                        break;
                    case "남산야외식물원":
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-798-3771"));
                        break;
                    case "서울대공원식물원":
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-500-7862"));
                        break;
                    case "서울어린이대공원식물원":
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-450-9311"));
                        break;
                    case "서울숲곤충식물원":
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-460-2905"));
                        break;
                    case "서울창포원":
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-954-0031"));
                        break;
                    case "전통염료식물원":
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-2077-9000"));
                        break;
                    case "푸른수목원":
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-2686-3200"));
                        break;
                    case "홍릉수목원":
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-961-2777"));
                        break;
                }
                startActivity(intent);
                break;
            case R.id.map_btn:
                intent = new Intent(this, InformationMap.class);
                intent.putExtra("GardenName", gardenPlace);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        final CheckBox checkBox = (CheckBox) menu.findItem(R.id.toolbar_like).getActionView();
        checkBox.setButtonDrawable(R.drawable.toolbar_like_btn);
        checkBox.setChecked(sp_like.getSharedboolean(gardenPlace));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()) {
                    sp_like.setSharedboolean(gardenPlace, true);
                } else {
                    sp_like.setSharedboolean(gardenPlace, false);
                }
            }
        });
        return true;
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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
    @Override
    public void onPause(){
        super.onPause();
        intent = null;
        picViews = null;
        pic1 = null;
        pic2 = null;
        pic3 = null;
        viewLayout = null;
        img1 = null;
        img2 = null;
        img3 = null;
        pointImg = null;
        pointArray = null;
        viewPoints = null;
        viewPager = null;
        gardenName = null;
        gardenAddress = null;
        gardenPrice = null;
        gardenTime = null;
        gardenRent = null;
    }

}
