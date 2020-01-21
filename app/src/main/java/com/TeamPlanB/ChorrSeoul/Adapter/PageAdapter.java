package com.TeamPlanB.ChorrSeoul.Adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

public class PageAdapter extends PagerAdapter {
    private ArrayList<View> picViews;
    private Context context;

    public PageAdapter(ArrayList<View> picViews, Context context) {
        super();
        this.picViews = picViews;
        this.context = context;
    }

    @Override
    public Object instantiateItem(View view, int position) {
        ((ViewPager) view).addView(picViews.get(position));
        return picViews.get(position);
    }

    @Override
    public void destroyItem(View view, int position, Object arg2) {
        ((ViewPager) view).removeView(picViews.get(position));
    }

    @Override
    public void finishUpdate(View arg0) {
    }

    @Override
    public int getCount() {
        return picViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void startUpdate(View arg0) {
    }

    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }


    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
