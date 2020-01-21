package com.TeamPlanB.ChorrSeoul.MyFlower;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.TeamPlanB.ChorrSeoul.R;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_Like;
import com.TeamPlanB.ChorrSeoul.SharedPreference.SP_MyFlowerNickname;
import com.bumptech.glide.Glide;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MyFlowerInfo extends AppCompatActivity {
    private Intent intent;

    //내 화분 정보
    private String myFlower;
    private ImageView imageView, realimage;
    private TextView myFolwerName, matchDay, myFolwerNickName, myFlowerMatchdayNick;

    //실제 화분 정보
    private TextView myFlower_explan, myFlower_dstrb, myFlower_cpr, myFlower_grw, myFlower_protected;

    // SharedPreference
    private SP_Like sp_like = new SP_Like(this);
    private SP_MyFlowerNickname sp_myFlowerNickname = new SP_MyFlowerNickname(this);

    // 서울시 공공데이터 파싱
    private String apikey = "ppemHEoM5hInrUqDzTFIQMDrmUXjQwJglJu6W4pj4i0YCsa7578ejgvB9KW6ftln3Zoj%2FLQHRRCYd6D0wWdkZg%3D%3D";
    private String sumgae = "29626", giseng = "25856", sebul = "31938", yo = "29213", pungran = "30621", gasi = "38126";
    private String flowerNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myflower_info);

        // 액션바 설정
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        if (intent.getStringExtra("myFlowerName") != null) {
            myFlower = intent.getStringExtra("myFlowerName");
        }

        setInfo();

        // 데이터 파싱 스레드
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute();
    }

    public void setInfo() {
        matchDay = (TextView) findViewById(R.id.myfinfo_matchday);
        myFolwerNickName = (TextView) findViewById(R.id.myfInfo_nickname);
        myFlowerMatchdayNick = (TextView) findViewById(R.id.myfInfo_matchdayName);
        imageView = (ImageView) findViewById(R.id.myflower_info_image);
        myFolwerName = (TextView) findViewById(R.id.myflower_info_name);

        myFolwerName.setText(myFlower);
        myFolwerNickName.setText(sp_myFlowerNickname.getSharedString(myFlower));
        myFlowerMatchdayNick.setText(sp_myFlowerNickname.getSharedString(myFlower) + "'s 만난 날");
        matchDay.setText(sp_like.getSharedString(myFlower));

        switch (myFlower) {
            case "섬개야광나무":
                Glide.with(this).load(R.drawable.myflower_sum_3).into(imageView);
                flowerNo = sumgae;
                break;
            case "기생꽃":
                Glide.with(this).load(R.drawable.myflower_giseng_3).into(imageView);
                flowerNo = giseng;
                break;
            case "세뿔투구꽃":
                Glide.with(this).load(R.drawable.myflower_sebul_3).into(imageView);
                flowerNo = sebul;
                break;
            case "광릉요강꽃":
                Glide.with(this).load(R.drawable.myflower_yo_3).into(imageView);
                flowerNo = yo;
                break;
            case "풍란":
                Glide.with(this).load(R.drawable.myflower_pungran_3).into(imageView);
                flowerNo = pungran;
                break;
            case "가시연꽃":
                Glide.with(this).load(R.drawable.myflower_gasi_3).into(imageView);
                flowerNo = gasi;
                break;
        }
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

    class DownloadTask extends AsyncTask<String, Void, String> {
        private String result;

        @Override
        protected String doInBackground(String... strings) {
            try {
                boolean initem = false, inflwrDesc = false, inimgUrl = false, ingrwEvrntDesc = false, incprtCtnt = false, indstrb = false, inprtcPlnDesc = false;
                String item = null, flwDesc = null, imgUrl = null, grwEvrntDesc = null, cprtCtnt = null, dstrb = null, prtcPlnDesc = null;

                URL url = new URL("http://api.nature.go.kr/openapi/service/rest/PlantService/plntIlstrInfo?serviceKey=" + apikey + "&q1=" + flowerNo);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                int parserEvent = parser.getEventType();
                while (parserEvent != XmlPullParser.END_DOCUMENT) {
                    switch (parserEvent) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            // 이미지 주소
                            if (parser.getName().equals("imgUrl")) {
                                inimgUrl = true;
                            }
                            // 꽃설명
                            if (parser.getName().equals("flwrDesc")) {
                                inflwrDesc = true;
                            }
                            // 분포정보
                            if (parser.getName().equals("dstrb")) {
                                indstrb = true;
                            }
                            // 생육환경설명
                            if (parser.getName().equals("grwEvrntDesc")) {
                                ingrwEvrntDesc = true;
                            }
                            // 보호방안
                            if (parser.getName().equals("prtcPlnDesc")) {
                                inprtcPlnDesc = true;
                            }
                            // 저작권
                            if (parser.getName().equals("cprtCtnt")) {
                                incprtCtnt = true;
                            }
                            break;
                        case XmlPullParser.TEXT:
                            if (inimgUrl) {
                                imgUrl = parser.getText();
                                inimgUrl = false;
                            }
                            if (inflwrDesc) {
                                flwDesc = parser.getText();
                                inflwrDesc = false;
                            }
                            if (indstrb) {
                                dstrb = parser.getText();
                                indstrb = false;
                            }
                            if (ingrwEvrntDesc) {
                                grwEvrntDesc = parser.getText();
                                ingrwEvrntDesc = false;
                            }
                            if (inprtcPlnDesc) {
                                prtcPlnDesc = parser.getText();
                                inprtcPlnDesc = false;
                            }
                            if (incprtCtnt) {
                                cprtCtnt = parser.getText();
                                incprtCtnt = false;
                            }
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("item")) {
                                result = imgUrl + "#" + flwDesc + "#" + dstrb + "#" + grwEvrntDesc + "#" + prtcPlnDesc + "#" + cprtCtnt;
                            }
                            initem = false;
                            break;
                    }
                    parserEvent = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            String[] array = s.split("#");
            myFlower_explan = (TextView) findViewById(R.id.myflower_info_explan);
            myFlower_dstrb = (TextView) findViewById(R.id.myflower_info_dstrb);
            myFlower_grw = (TextView) findViewById(R.id.myflower_info_grw);
            myFlower_protected = (TextView) findViewById(R.id.myflower_info_protected);
            myFlower_cpr = (TextView) findViewById(R.id.myflower_info_cpr);
            realimage = (ImageView) findViewById(R.id.myflower_info_realimage);

            Glide.with(getApplicationContext()).load(array[0]).into(realimage);
            myFlower_explan.setText(array[1]);
            myFlower_dstrb.setText(array[2]);
            myFlower_grw.setText(array[3]);
            myFlower_protected.setText(array[4]);
            myFlower_cpr.setText(array[5]);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        myFlower = null;
        imageView = null;
        realimage = null;
        myFolwerName = null;
        matchDay = null;
        myFolwerNickName = null;
        myFlowerMatchdayNick = null;
        myFlower_explan = null;
        myFlower_dstrb = null;
        myFlower_cpr = null;
        myFlower_grw = null;
        myFlower_protected = null;
        flowerNo = null;
    }
}

