package com.iniesta.iniestanews;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SingleDownloadTask extends AsyncTask<String,Integer, NewsItem > {
    NewsItem newsItem;
    private Context mContext;
    private ProgressBar mProgressBar;
    private ImageView image;
    private TextView desc,desc2,date,title;

    public SingleDownloadTask( Context context,ProgressBar progressBar, ImageView image, TextView desc ,TextView desc2, TextView date , TextView title) {
        mContext = context;
        mProgressBar = progressBar;
        this.image = image;
        this.desc = desc;
        this.desc2 = desc2;
        this.title = title;
        this.date = date;
    }

    @Override
    protected void onPreExecute() {
        mProgressBar.setVisibility(View.VISIBLE);
        desc.setVisibility(View.INVISIBLE);
        desc2.setVisibility(View.INVISIBLE);
        title.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onPostExecute(final NewsItem newsItem) {

        mProgressBar.setVisibility(View.INVISIBLE);
        desc.setVisibility(View.VISIBLE);
        desc2.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        String mContent1,mContent2,mContent3,mContent4;
        mContent1 = newsItem.getContent1();
        mContent2 = newsItem.getContent2();
        mContent3 = newsItem.getContent3();
        mContent4 = newsItem.getContent4();

        String mTitle = newsItem.getHeading();
        String mDate = newsItem.getDate();

        String imageurl = newsItem.getImageUrl();


        Glide.with(mContext)
                .load(imageurl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image);


        desc.setText(mContent1+"\n\n"+mContent2+"\n");
        desc2.setText(mContent3+"\n\n"+mContent4+"\n");
        title.setText(mTitle);
        date.setText(mDate);
    }

    @Override
    protected NewsItem doInBackground(String... strings) {

        URL mainUrl;
        HttpURLConnection urlConnection;
        String result = "";
        StringBuilder output = new StringBuilder();


        try {
            mainUrl = new URL(strings[0]);
            urlConnection = (HttpURLConnection) mainUrl.openConnection();
//            urlConnection.setReadTimeout(10000);
//            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();

            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }

            result = output.toString();
            String cid = "";
            String nid = "";
            String imageUrl = "";
            String heading = "";
            String content1 = "";
            String content2 = "";
            String content3 = "";
            String content4 = "";
            String dateString = "";
            String shareUrl = "";
            try {
                JSONObject e = new JSONObject(result);
                shareUrl = "http://www.iniestanews.com/news.php?cid=";
                cid = e.getString("cid");
                nid = e.getString("nid");
                imageUrl = e.getString("image");
                heading = e.getString("heading");
                content1 = e.getString("content_one");
                content2 = e.getString("content_two");
                content3 = e.getString("content_three");
                content4 = e.getString("content_four");
                dateString = e.getString("date");
                String[] dateArr = dateString.split(" ");
                String date = dateArr[0];
                shareUrl += cid + "&nid=" + nid;
                newsItem = new NewsItem(cid,nid,imageUrl, heading, content1,content2,content3,content4, date,shareUrl);
            }catch (Exception e){
                e.printStackTrace();
            }
            return newsItem;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return newsItem;
    }
}
