package com.TeamPlanB.ChorrSeoul.MainFragment;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.TeamPlanB.ChorrSeoul.Adapter.LikeRecyclerViewAdapter;
import com.TeamPlanB.ChorrSeoul.RecyclerViewItem;
import com.TeamPlanB.ChorrSeoul.R;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_Like;

import java.util.Arrays;
import java.util.List;

public class LikeFragment extends Fragment {
    private View view;
    private List<String> gardenPlaces;

    private LikeRecyclerViewAdapter adapter;

    private SP_Like sp_like;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.like_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.like_RecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        sp_like =  new SP_Like(getContext());
        adapter = new LikeRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        TypedArray icons = getResources().obtainTypedArray(R.array.indoorGarden_Image);

        gardenPlaces = Arrays.asList(getResources().getStringArray(R.array.allGarden));

        for (int i = 0; i < gardenPlaces.size(); i++) {
            if (sp_like.getSharedboolean(gardenPlaces.get(i))) {
                RecyclerViewItem item = new RecyclerViewItem();
                item.setTitle(gardenPlaces.get(i));
                item.setIcon(icons.getDrawable(i));
                adapter.addItem(item);
            }
        }
        adapter.notifyDataSetChanged();
        return view;
    }
    @Override
    public void onPause(){
        super.onPause();
        view = null;
    }

}

