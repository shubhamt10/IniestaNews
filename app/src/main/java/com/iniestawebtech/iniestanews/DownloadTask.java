package com.iniestawebtech.iniestanews;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadTask extends AsyncTask<String,Integer, List<NewsItem>> {
    private List<NewsItem> newsItems = new ArrayList<>();
    private Context mContext;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    RecyclerView.LayoutManager manager;
    NewsAdapter adapter;

    public DownloadTask(RecyclerView recyclerView, ProgressBar progressBar, Context context) {
        mContext = context;
        mRecyclerView = recyclerView;
        mProgressBar = progressBar;
    }

    public void clearAll(RecyclerView recyclerView)
    {
        mRecyclerView = recyclerView;
        newsItems.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPreExecute() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(final List<NewsItem> newsItems) {

        mProgressBar.setVisibility(View.INVISIBLE);

        NewsAdapter.RecyclerViewClickListener listener = new NewsAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                NewsItem item = newsItems.get(position);
                String webUrl = item.getShareUrl();
                //Toast.makeText(mContext,"Url: " + webUrl,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext,NewsDetailActivity.class);
                intent.putExtra("url",webUrl);
                intent.putExtra("title", item.getHeading());
                intent.putExtra("img",  item.getImageUrl());
                intent.putExtra("date",item.getDate());
                intent.putExtra("content1",item.getContent1());
                intent.putExtra("content2",item.getContent2());
                intent.putExtra("content3",item.getContent3());
                intent.putExtra("content4",item.getContent4());
                mContext.startActivity(intent);
            }
        };


        manager = new LinearLayoutManager(mContext);
        adapter = new NewsAdapter(newsItems,mContext,listener);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected List<NewsItem> doInBackground(String... strings) {

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
                JSONArray jsonArray = new JSONArray(result);
                JSONObject e;

                int length = jsonArray.length();

                for (int i=0;i<jsonArray.length();i++) {
                    shareUrl = "http://www.iniestanews.com/news.php?cid=";
                    e = jsonArray.getJSONObject(length-i-1);
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
                    NewsItem news = new NewsItem(cid,nid,imageUrl, heading, content1,content2,content3,content4, date,shareUrl);
                    newsItems.add(news);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return newsItems;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return newsItems;
    }
}
