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
//    private ProgressBar mProgressBar;
    private ImageView image;
    private TextView desc,date,title;

    public SingleDownloadTask( Context context, ImageView image, TextView desc , TextView date , TextView title) {
        mContext = context;
//        mProgressBar = progressBar;
        this.image = image;
        this.desc = desc;
        this.title = title;
        this.date = date;
    }

    @Override
    protected void onPreExecute() {

        //mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(final NewsItem newsItem) {

        //mProgressBar.setVisibility(View.INVISIBLE);
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


        desc.setText(mContent1+"\n\n"+mContent2+"\n\n"+mContent3+"\n\n"+mContent4+"\n");
        title.setText(mTitle);
        date.setText(mDate);


//        NewsAdapter.RecyclerViewClickListener listener = new NewsAdapter.RecyclerViewClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                NewsItem item = newsItems.get(position);
//                String webUrl = item.getShareUrl();
//                //Toast.makeText(mContext,"Url: " + webUrl,Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(mContext,NewsDetailActivity.class);
//                intent.putExtra("url",webUrl);
//                intent.putExtra("title", item.getHeading());
//                intent.putExtra("img",  item.getImageUrl());
//                intent.putExtra("date",item.getDate());
//                intent.putExtra("content1",item.getContent1());
//                intent.putExtra("content2",item.getContent2());
//                intent.putExtra("content3",item.getContent3());
//                intent.putExtra("content4",item.getContent4());
//                mContext.startActivity(intent);
//            }
//        };


//        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext);
//        NewsAdapter adapter = new NewsAdapter(newsItems,mContext,listener);
//        mRecyclerView.setLayoutManager(manager);
//        mRecyclerView.setAdapter(adapter);

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
            System.out.println(result);
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
//                JSONObject e;

//                int length = jsonObject.length();

                    shareUrl = "http://www.iniestanews.com/news.php?cid=";
//                    e = jsonArray.getJSONObject(length-i-1);
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
//                    newsItems.add(news);
                System.out.println(newsItem);
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
