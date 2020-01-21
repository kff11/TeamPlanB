package com.TeamPlanB.ChorrSeoul.SharedPreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SP_MyFlowerLv {
    private SharedPreferences sharedPreferences;
    private Context context;
    private static final String XML_FILE_NAME = "ChorrSeoul_MyFlowerLv"; // SharedPreferences xml file name.

    public SP_MyFlowerLv(Context context) {
        this.context = context;
    }

    public int getSharedInteger(String key){
        sharedPreferences = context.getSharedPreferences(XML_FILE_NAME, Activity.MODE_PRIVATE);
        int integer = sharedPreferences.getInt(key, -1);
        return integer;
    }

    public void setSharedInteger(String key, int integer) {
        sharedPreferences = context.getSharedPreferences(XML_FILE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, integer);
        editor.commit();

    }

    public void setSharedIntegerPlus(String key){
        sharedPreferences = context.getSharedPreferences(XML_FILE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int integer = getSharedInteger(key);
        editor.putInt(key, integer + 1);
        editor.commit();
    }

}
