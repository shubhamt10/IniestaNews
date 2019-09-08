package com.iniesta.iniestanews;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class FragmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        Toolbar toolbar = findViewById(R.id.fragments_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String type = getIntent().getStringExtra("type");
        Bundle bundle1 = new Bundle();
        Fragment fragment = new HomeFragment();
        switch (type){
            case "latest":
                getSupportActionBar().setTitle("Latest News");
                bundle1.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=1");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1); break;
            case "entertainment":
                getSupportActionBar().setTitle("Entertainment");
                bundle1.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=5");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1); break;
            case "politics":
                getSupportActionBar().setTitle("Politics");
                bundle1.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=12");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1); break;
            case "gadgets":
                getSupportActionBar().setTitle("Gadgets");
                bundle1.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=7");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1); break;
            case "sports":
                getSupportActionBar().setTitle("Sports");
                bundle1.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=4");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1); break;
            case "business":
                getSupportActionBar().setTitle("Business");
                bundle1.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=8");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1); break;
            case "health":
                getSupportActionBar().setTitle("Health");
                bundle1.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=9");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1); break;
            case "education":
                getSupportActionBar().setTitle("Education");
                bundle1.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=10");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1); break;
            case "world":
                getSupportActionBar().setTitle("World");
                bundle1.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=3");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1); break;
            case "national":
                getSupportActionBar().setTitle("National");
                bundle1.putString("url", "http://www.iniestanews.com/api/ineisatapi.php?cid=2");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1); break;
            case "feedback":
                fragment = new feedback();break;
            case "contactUs":
                fragment = new About_us();break;
            case "settings":
                fragment = new settings();break;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.fragments_container,fragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentsActivity.super.onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
