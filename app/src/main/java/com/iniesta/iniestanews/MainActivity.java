package com.iniesta.iniestanews;

import android.content.Context;
import android.content.Intent;
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
    private PagerAdapter adapter;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(5);

        addFragments();

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    public void addFragments(){

        Bundle bundle1 = new Bundle();
        bundle1.putString("url", "https://newsapi.org/v2/top-headlines?country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
        Bundle bundle2 = new Bundle();
        bundle2.putString("url", "https://newsapi.org/v2/top-headlines?category=sports&country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
        Bundle bundle3 = new Bundle();
        bundle3.putString("url", "https://newsapi.org/v2/top-headlines?category=business&country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
        Bundle bundle4 = new Bundle();
        bundle4.putString("url", "https://newsapi.org/v2/top-headlines?category=science&country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
        Bundle bundle5 = new Bundle();
        bundle5.putString("url", "https://newsapi.org/v2/top-headlines?category=technology&country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");

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

        adapter.addFragment(fragment1, "Top");
        adapter.addFragment(fragment2, "Sports");
        adapter.addFragment(fragment3, "Business");
        adapter.addFragment(fragment4, "Science");
        adapter.addFragment(fragment5, "Technology");

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        Intent fragmentsActivity = new Intent(MainActivity.this,FragmentsActivity.class);
        switch (menuItem.getItemId()) {

            case R.id.nav_settings:

                fragmentsActivity.putExtra("type","settings");
                startActivity(fragmentsActivity);
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id., frag)
//                        .add(new DashboardFragment(), "dash")
//                        .addToBackStack("dash")
//                        .commit();
                break;
//            case R.id.nav_profile:
//                Fragment frag1 = new ProfileFragment();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, frag1)
//                        .add(new ProfileFragment(), "dash")
//                        .addToBackStack("dash")
//                        .commit();
//                break;
//
//            case R.id.nav_instructions:
//
//                Fragment frag2 = new InstructionFragment();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, frag2)
//                        .add(new InstructionFragment(), "dash")
//                        .addToBackStack("dash")
//                        .commit();
//                break;
//
//            case R.id.nav_feedback:
//                Fragment frag3 = new FeedbackFragment();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, frag3)
//                        .add(new FeedbackFragment(), "dash")
//                        .addToBackStack("dash")
//                        .commit();
//                break;
//
//            case R.id.nav_about_us:
//
//                Fragment frag4 = new AboutUsFragment();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, frag4)
//                        .add(new AboutUsFragment(), "dash")
//                        .addToBackStack("dash")
//                        .commit();
//                break;
//            case R.id.nav_logout:
//                showPopup();
//                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
