package com.TeamPlanB.ChorrSeoul.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.TeamPlanB.ChorrSeoul.MyFlower.MyFlowerInfo;
import com.TeamPlanB.ChorrSeoul.RecyclerViewItem;
import com.TeamPlanB.ChorrSeoul.R;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerLv;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class MyFlowerRecyclerViewAdapter extends RecyclerView.Adapter<MyFlowerRecyclerViewAdapter.ItemViewHolder> {
    private ArrayList<RecyclerViewItem> itemList = new ArrayList<RecyclerViewItem>();
    private Context context;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.myflower_recyclerview_item, parent, false);
        return new MyFlowerRecyclerViewAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        itemViewHolder.onBind(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 받을 데이터 추가
    public void addItem(RecyclerViewItem item) {

        itemList.add(item);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView nickname;
        private ViewGroup viewGroup;
        private Intent intent;
        private SP_MyFlowerLv sp_myFlowerLv;

        ItemViewHolder(View itemView) {
            super(itemView);
            nickname = itemView.findViewById(R.id.myflower_nickname_item);
            img = itemView.findViewById(R.id.myFlower_img);
            viewGroup = itemView.findViewById(R.id.myfRecyclerView_Layout);
        }

        void onBind(final RecyclerViewItem item) {
            Glide.with(context).load(item.getIcon()).into(img);
            nickname.setText(item.getNickname());
            sp_myFlowerLv = new SP_MyFlowerLv(context);

            viewGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sp_myFlowerLv.getSharedInteger(item.getTitle()) == 4) {
                        intent = new Intent(context, MyFlowerInfo.class);
                        switch (item.getTitle()) {
                            case "섬개야광나무":
                                intent.putExtra("myFlowerName", "섬개야광나무");
                                break;
                            case "기생꽃":
                                intent.putExtra("myFlowerName", "기생꽃");
                                break;
                            case "세뿔투구꽃":
                                intent.putExtra("myFlowerName", "세뿔투구꽃");
                                break;
                            case "광릉요강꽃":
                                intent.putExtra("myFlowerName", "광릉요강꽃");
                                break;
                            case "풍란":
                                intent.putExtra("myFlowerName", "풍란");
                                break;
                            case "가시연꽃":
                                intent.putExtra("myFlowerName", "가시연꽃");
                                break;
                        }
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "아직 성장이 덜 되었어요!!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}
