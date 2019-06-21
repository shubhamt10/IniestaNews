package com.iniesta.iniestanews;

import android.content.Context;
import android.content.Intent;
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
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private NavigationView navig;
    private PagerAdapter adapter;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        navig=findViewById(R.id.nav_view);
        adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(6);

        addFragments();

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
            case R.id.nav_delhiNCR:
                fragmentsActivity.putExtra("type","delhiNCR");
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
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Iniesta News");
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
}
