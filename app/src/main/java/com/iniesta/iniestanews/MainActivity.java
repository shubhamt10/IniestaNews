package com.iniesta.iniestanews;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;

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
}
