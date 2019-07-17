package com.iniestawebtech.iniestanews;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class NewsDetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private ImageView imageView;
    private TextView title, Description, desc2, date;
    private boolean isHideToolbarView = false;
    private FrameLayout date_behavior;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private String mUrl, mImg, mTitle, mDate, mContent1, mContent2, mContent3, mContent4, cid, nid;
    private AdView mAdViewDetails, mAdViewLarge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);


        mAdViewDetails = findViewById(R.id.adViewDetails);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewDetails.loadAd(adRequest);

        mAdViewLarge = findViewById(R.id.adViewLarge);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdViewLarge.loadAd(adRequest2);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");

        title = findViewById(R.id.title);
        appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(this);
        date_behavior = findViewById(R.id.date_behavior);
        imageView = findViewById(R.id.backdrop);
        Description = findViewById(R.id.description);
        desc2 = findViewById(R.id.description2);
        date = findViewById(R.id.date);

        Intent intent = getIntent();
        //mUrl = intent.getStringExtra("webUrl");

        mUrl = intent.getStringExtra("url");
        mImg = intent.getStringExtra("img");
        mTitle = intent.getStringExtra("title");
        mDate = intent.getStringExtra("date");
        mContent1 = intent.getStringExtra("content1");
        mContent2 = intent.getStringExtra("content2");
        mContent3 = intent.getStringExtra("content3");
        mContent4 = intent.getStringExtra("content4");
        nid = intent.getStringExtra("nid");
        cid = intent.getStringExtra("cid");
        if (Build.VERSION.SDK_INT > 26) {
            Description.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
        Description.setText(mContent1 + "\n\n" + mContent2 + "\n");
        desc2.setText(mContent3 + "\n\n" + mContent4 + "\n");
        title.setText(mTitle);
        date.setText(mDate);

        if (nid != null) {
            ProgressBar newsProgressBar;
            newsProgressBar = findViewById(R.id.newsProgressBar);
            Sprite threeBounce = new ThreeBounce();
            newsProgressBar.setIndeterminateDrawable(threeBounce);

            String url = "http://www.iniestanews.com/api/singlenewsapi.php?nid=" + nid;
            mUrl = "http://www.iniestanews.com/news.php?cid=" + cid + "&nid=" + nid;

            new SingleDownloadTask(this, newsProgressBar, imageView, Description, desc2, date, title).execute(url);
        } else {

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.error(Utils.getRandomDrawbleColor());

            Glide.with(this)
                    .load(mImg)
                    .apply(requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);

        }

//         Toast.makeText(this, mUrl, Toast.LENGTH_SHORT).show();

//        appbar_title.setText(mSource);
//        appbar_subtitle.setText(mUrl);
//        date.setText(Utils.DateFormat(mDate));
//        title.setText(mTitle);

//        String author;
//        if (mAuthor != null){
//            author = " \u2022 " + mAuthor;
//        } else {
//            author = "";
//        }

//        time.setText(mSource + author + " \u2022 " + Utils.DateToTimeFormat(mDate));
//
//        initWebView(mUrl);

    }

//    private void initWebView(String url){
//        WebView webView = findViewById(R.id.webView);
//        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setBuiltInZoomControls(true);
//        webView.getSettings().setDisplayZoomControls(false);
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl(url);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {
            date_behavior.setVisibility(View.GONE);
            getSupportActionBar().setTitle("Iniesta News");
//            titleAppbar.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;

        } else if (percentage < 1f && !isHideToolbarView) {
            date_behavior.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("");
//            titleAppbar.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

//        if (id == R.id.view_web){
//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(mUrl));
//            startActivity(i);
//            return true;
//        }
        if (id == R.id.share) {
            try {

//                    Intent i = new Intent(Intent.ACTION_SEND);
//                    i.putExtra(Intent.EXTRA_SUBJECT, mSource);
//                    String body = mUrl;
//                    i.putExtra(Intent.EXTRA_TEXT, body);
//                    startActivity(Intent.createChooser(i, "Share with :"));

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, mUrl);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);


            } catch (Exception e) {
                Toast.makeText(this, "Cannot be shared", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}






