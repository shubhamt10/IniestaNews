package com.iniesta.iniestanews;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.iniesta.iniestanews.services.Configuration;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private NavigationView navig;
    private PagerAdapter adapter;
    DrawerLayout drawer;
    String strAppLink ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-5589355018838308~9779092060");


        toolbar=findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        navig=findViewById(R.id.nav_view);
        adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(6);

        addFragments();
        strAppLink=" ";
        final String appPackageName = getApplicationContext().getPackageName();
        strAppLink = "https://play.google.com/store/apps/details?id=" + appPackageName;

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View view =navigationView.inflateHeaderView(R.layout.nav_header_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Toast.makeText(this, FirebaseInstanceId.getInstance().getToken(), Toast.LENGTH_SHORT).show();

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals("registrationComplete")) {
                    // fcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic("global");
                }
            }
        };

    }

    public void addFragments(){

        Bundle bundle1 = new Bundle();
        bundle1.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=1");
        Bundle bundle2 = new Bundle();
        bundle2.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=2");
        Bundle bundle3 = new Bundle();
        bundle3.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=4");
        Bundle bundle4 = new Bundle();
        bundle4.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=7");
        Bundle bundle5 = new Bundle();
        bundle5.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=8");
        Bundle bundle6 = new Bundle();
        bundle6.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=11");

        HomeFragment fragment1 = new HomeFragment();
        fragment1.setArguments(bundle1);
        HomeFragment fragment2 = new HomeFragment();
        fragment2.setArguments(bundle2);
        HomeFragment fragment3 = new HomeFragment();
        fragment3.setArguments(bundle3);
        HomeFragment fragment4 = new HomeFragment();
        fragment4.setArguments(bundle4);
        HomeFragment fragment5 = new HomeFragment();
        fragment5.setArguments(bundle5);
        HomeFragment fragment6 = new HomeFragment();
        fragment6.setArguments(bundle6);

        adapter.addFragment(fragment1, "Latest News");
        adapter.addFragment(fragment2, "National News");
        adapter.addFragment(fragment3, "Sports");
        adapter.addFragment(fragment4, "Gadgets");
        adapter.addFragment(fragment5, "Business");
        adapter.addFragment(fragment6, "World Cup");

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        Intent fragmentsActivity = new Intent(MainActivity.this,FragmentsActivity.class);
        switch (menuItem.getItemId()) {
            case R.id.latest_news:
                fragmentsActivity.putExtra("type","latest");
                startActivity(fragmentsActivity);
                break;
            case R.id.nav_entertainment:
                fragmentsActivity.putExtra("type","entertainment");
                startActivity(fragmentsActivity);
                break;
            case R.id.nav_politics:
                fragmentsActivity.putExtra("type","politics");
                startActivity(fragmentsActivity);
                break;
            case R.id.nav_gadgets:
                fragmentsActivity.putExtra("type","gadgets");
                startActivity(fragmentsActivity);
                break;
            case R.id.nav_sports:
                fragmentsActivity.putExtra("type","sports");
                startActivity(fragmentsActivity);
                break;
            case R.id.nav_business:
                fragmentsActivity.putExtra("type","business");
                startActivity(fragmentsActivity);
                break;
            case R.id.nav_health:
                fragmentsActivity.putExtra("type","health");
                startActivity(fragmentsActivity);
                break;
            case R.id.nav_education:
                fragmentsActivity.putExtra("type","education");
                startActivity(fragmentsActivity);
                break;
            case R.id.world_news:
                fragmentsActivity.putExtra("type","world");
                startActivity(fragmentsActivity);
                break;
            case R.id.national_news:
                fragmentsActivity.putExtra("type","national");
                startActivity(fragmentsActivity);
                break;
            case R.id.nav_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,strAppLink);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.nav_feedback:
                fragmentsActivity.putExtra("type","feedback");
                startActivity(fragmentsActivity);
                break;
            case R.id.nav_contact_us:
                fragmentsActivity.putExtra("type","contactUs");
                startActivity(fragmentsActivity);
                break;
            case R.id.nav_settings:
                fragmentsActivity.putExtra("type","settings");
                startActivity(fragmentsActivity);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    @Override
//    public void onResume() {
//       // Toast.makeText(getContext(), "in resume", Toast.LENGTH_SHORT).show();
//        SharedPreferences sp = getSharedPreferences("check" , Context.MODE_PRIVATE);
//        int a=sp.getInt("cj",0);
//
//        if(a==0)
//        {
//            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
//            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
//        }
//        else {
//            toolbar.setBackgroundColor(getResources().getColor(R.color.primaryGray));
//            tabLayout.setTabIconTintResource(R.color.black);
//            tabLayout.setBackgroundColor(getResources().getColor(R.color.primaryGray));
//            //navig.setItemBackground(getResources().getColor(R.color.primaryGray));
//            toolbar.setTitleTextColor(getResources().getColor(R.color.black));
//            toolbar.setSubtitleTextColor(getResources().getColor(R.color.black));
//            //view.setBackgroundColor(getResources().getColor(R.color.white));
//        }
//        super.onResume();
//    }


    @Override
    protected void onRestart() {
        //Toast.makeText(this, FirebaseInstanceId.getInstance().getToken(), Toast.LENGTH_SHORT).show();
        Log.i("hello", FirebaseInstanceId.getInstance().getToken());
        super.onRestart();
    }

    @Override
    protected void onPause() {
        //Toast.makeText(this, FirebaseInstanceId.getInstance().getToken(), Toast.LENGTH_SHORT).show();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        Log.i("hello", FirebaseInstanceId.getInstance().getToken());
        super.onPause();
    }

    @Override
    protected void onResume() {
        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Configuration.REGISTRATION_COMPLETE));
        // register new push postUrlFromNotification receiver
        // by doing this, the activity will be notified each time a new postUrlFromNotification arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Configuration.PUSH_NOTIFICATION));
        super.onResume();
    }
}
