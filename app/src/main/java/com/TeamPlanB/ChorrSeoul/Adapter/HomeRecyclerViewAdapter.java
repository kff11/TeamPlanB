package com.TeamPlanB.ChorrSeoul.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.TeamPlanB.ChorrSeoul.Infomation.InformationActivity;
import com.TeamPlanB.ChorrSeoul.MainActivity;
import com.TeamPlanB.ChorrSeoul.MyFlower.MyFlowerInfo;
import com.TeamPlanB.ChorrSeoul.R;
import com.TeamPlanB.ChorrSeoul.RecyclerViewItem;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerLv;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ItemViewHolder> {
    private ArrayList<RecyclerViewItem> itemList = new ArrayList<RecyclerViewItem>();
    private Context context;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.home_recyclerview_item, parent, false);
        return new ItemViewHolder(view);
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

        ItemViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.home_recyclerView_img);
        }

        void onBind(final RecyclerViewItem item) {
            Glide.with(context).load(item.getIcon()).
                    apply(RequestOptions.bitmapTransform(new MultiTransformation<Bitmap>(new CenterCrop(), new RoundedCorners(20)))).into(img);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, InformationActivity.class);
                    switch (item.getTitle()) {
                        case "마곡식물원":
                            intent.putExtra("GardenName", "마곡식물원");
                            break;
                        case "관악산야외식물원":
                            intent.putExtra("GardenName", "관악산야외식물원");
                            break;
                        case "남산야외식물원":
                            intent.putExtra("GardenName", "남산야외식물원");
                            break;
                        case "서울대공원식물원":
                            intent.putExtra("GardenName", "서울대공원식물원");
                            break;
                        case "서울어린이대공원식물원":
                            intent.putExtra("GardenName", "서울어린이대공원식물원");
                            break;
                        case "서울숲곤충식물원":
                            intent.putExtra("GardenName", "서울숲곤충식물원");
                            break;
                        case "서울창포원":
                            intent.putExtra("GardenName", "서울창포원");
                            break;
                        case "전통염료식물원":
                            intent.putExtra("GardenName", "전통염료식물원");
                            break;
                        case "푸른수목원":
                            intent.putExtra("GardenName", "푸른수목원");
                            break;
                        case "홍릉수목원":
                            intent.putExtra("GardenName", "홍릉수목원");
                            break;

                    }
                    context.startActivity(intent);
                    ((MainActivity) context).overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                }
            });

        }
    }
}
