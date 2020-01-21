package com.TeamPlanB.ChorrSeoul.MyFlower;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.TeamPlanB.ChorrSeoul.Adapter.MyFlowerRecyclerViewAdapter;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerLv;
import com.TeamPlanB.ChorrSeoul.RecyclerViewItem;
import com.TeamPlanB.ChorrSeoul.R;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlower;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerNickname;

public class MyFlowerList extends AppCompatActivity {

    public static String[] myFlowerArray = {"섬개야광나무", "기생꽃", "세뿔투구꽃", "광릉요강꽃", "풍란", "가시연꽃"};

    // 리사이클러뷰
    private RecyclerView recyclerView;
    private MyFlowerRecyclerViewAdapter adapter;

    private SP_MyFlower sp_myFlower = new SP_MyFlower(this);
    private SP_MyFlowerLv sp_myFlowerLv = new SP_MyFlowerLv(this);
    private SP_MyFlowerNickname sp_myFlowerNickname = new SP_MyFlowerNickname(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myflower_list);

        setRecyclerView_myflower();

        // 액션바 설정
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void setRecyclerView_myflower() {
        recyclerView = findViewById(R.id.myFlower_RecyclerView);

        adapter = new MyFlowerRecyclerViewAdapter();

        // 레이아웃 설정
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        TypedArray lv_0 = getResources().obtainTypedArray(R.array.myFlower_Image_0);
        TypedArray lv_1 = getResources().obtainTypedArray(R.array.myFlower_Image_1);
        TypedArray lv_2 = getResources().obtainTypedArray(R.array.myFlower_Image_2);
        TypedArray lv_3 = getResources().obtainTypedArray(R.array.myFlower_Image_3);

        for (int i = 0; i < MyFlowerList.myFlowerArray.length; i++) {
            if (sp_myFlower.getSharedboolean(MyFlowerList.myFlowerArray[i])) {
                RecyclerViewItem item = new RecyclerViewItem();
                item.setTitle(MyFlowerList.myFlowerArray[i]);
                item.setNickname(sp_myFlowerNickname.getSharedString(myFlowerArray[i]));
                if (sp_myFlowerLv.getSharedInteger(myFlowerArray[i]) == 1) {
                    item.setIcon(lv_0.getDrawable(i));
                } else if (sp_myFlowerLv.getSharedInteger(myFlowerArray[i]) == 2) {
                    item.setIcon(lv_1.getDrawable(i));
                } else if (sp_myFlowerLv.getSharedInteger(myFlowerArray[i]) == 3) {
                    item.setIcon(lv_2.getDrawable(i));
                } else {
                    item.setIcon(lv_3.getDrawable(i));
                }
                adapter.addItem(item);
            }
        }
        adapter.notifyDataSetChanged();
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
}
