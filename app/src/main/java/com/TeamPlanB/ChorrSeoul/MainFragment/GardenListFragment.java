package com.TeamPlanB.ChorrSeoul.MainFragment;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.TeamPlanB.ChorrSeoul.Adapter.GardenRecyclerViewAdapter;
import com.TeamPlanB.ChorrSeoul.R;
import com.TeamPlanB.ChorrSeoul.RecyclerViewItem;

import java.util.Arrays;
import java.util.List;

public class GardenListFragment extends Fragment {

    private GardenRecyclerViewAdapter adapter;
    private View view;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.garden_list_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.gardenlist_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new GardenRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        // 리사이클러뷰 아이템 설정
        getItem();

        return view;
    }

    private void getItem() {
        List<String> listTitle = Arrays.asList(getResources().getStringArray(R.array.allGarden));
        List<String> listAdrs = Arrays.asList(getResources().getStringArray(R.array.allGarden_address));

        // 이미지 배열
        TypedArray icons = getResources().obtainTypedArray(R.array.indoorGarden_Image);

        for (int i = 0; i < listTitle.size(); i++) {
            RecyclerViewItem item = new RecyclerViewItem();
            Drawable icon = icons.getDrawable(i);
            item.setTitle(listTitle.get(i));
            item.setadrStr(listAdrs.get(i));
            item.setIcon(icon);

            adapter.addItem(item);
        }

        adapter.notifyDataSetChanged();
    }
    @Override
    public void onPause(){
        super.onPause();
        view = null;
    }
}

