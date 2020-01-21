package com.TeamPlanB.ChorrSeoul;

import android.graphics.drawable.Drawable;

public class RecyclerViewItem {
    private String titleStr;
    private String adrStr;
    private String nickStr;
    private Drawable icon;

    public void setTitle(String title) {
        titleStr = title;
    }

    public void setadrStr(String adrStr) {
        this.adrStr = adrStr;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setNickname(String nickStr) {
        this.nickStr = nickStr;
    }

    public String getTitle() {
        return this.titleStr;
    }

    public String getadrStr() {
        return adrStr;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getNickname() {
        return nickStr;
    }
}
