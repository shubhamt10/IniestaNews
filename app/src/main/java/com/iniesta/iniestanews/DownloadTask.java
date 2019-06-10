package com.iniesta.iniestanews;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

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

    public DownloadTask(RecyclerView recyclerView, ProgressBar progressBar, Context context) {
        mContext = context;
        mRecyclerView = recyclerView;
        mProgressBar = progressBar;
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
                String webUrl = item.getUrl();
                Toast.makeText(mContext,"Url: " + webUrl,Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mContext,WebActivity.class);
//                intent.putExtra("webUrl",webUrl);
//                mContext.startActivity(intent);
            }
        };
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext);
        NewsAdapter adapter = new NewsAdapter(newsItems,mContext,listener);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected List<NewsItem> doInBackground(String... strings) {

        URL mainUrl;
        HttpURLConnection urlConnection;
        String result = "";

        try {
            mainUrl = new URL(strings[0]);
            urlConnection = (HttpURLConnection) mainUrl.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);

            int data = reader.read();
            while (data != -1){
                char current = (char) data;
                result += current;
                data = reader.read();
            }

            JSONObject object = new JSONObject(result);
            JSONArray articles = object.getJSONArray("articles");
            int length = object.getInt("totalResults");

            JSONObject article;
            String title,url,urlImage,description;
            for (int i=0;i<20 && i<length;i++) {
                article = articles.getJSONObject(i);
                title = article.getString("title");
                url = article.getString("url");
                urlImage = article.getString("urlToImage");
                description = article.getString("description");
                if (urlImage.equals("null")){
                    newsItems.add(new NewsItem(title,url,"empty",description));
                }else {
                    newsItems.add(new NewsItem(title, url, urlImage,description));
                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return newsItems;
    }
}
