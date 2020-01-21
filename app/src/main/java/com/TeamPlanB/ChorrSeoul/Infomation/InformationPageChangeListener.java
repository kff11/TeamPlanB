package com.TeamPlanB.ChorrSeoul.Infomation;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.TeamPlanB.ChorrSeoul.R;

public class InformationPageChangeListener implements ViewPager.OnPageChangeListener {
    private ImageView[] imageViews;

    public InformationPageChangeListener(ImageView[] imageViews){
        super();
        this.imageViews = imageViews;
    }
    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[position].setBackgroundResource(R.drawable.circle_black);
            if (position != i) {
                imageViews[i].setBackgroundResource(R.drawable.circle_grey);
            }
        }
    }

}
