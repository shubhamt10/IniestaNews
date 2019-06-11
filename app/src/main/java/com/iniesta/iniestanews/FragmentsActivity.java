package com.iniesta.iniestanews;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class FragmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        Toolbar toolbar = findViewById(R.id.fragments_toolbar);
        setSupportActionBar(toolbar);

        String type = getIntent().getStringExtra("type");
        Bundle bundle1 = new Bundle();
//        bundle1.putString("url", "https://newsapi.org/v2/top-headlines?country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
        Fragment fragment = new HomeFragment();
        switch (type){
            case "latest":
                getSupportActionBar().setTitle("Latest News");
                bundle1.putString("url", "https://newsapi.org/v2/top-headlines?country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1);
                break;
            case "entertainment":
                getSupportActionBar().setTitle("Entertainment");
                bundle1.putString("url", "https://newsapi.org/v2/top-headlines?category=entertainment&country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1);
                break;
            case "politics":
                getSupportActionBar().setTitle("Politics");
                bundle1.putString("url", "https://newsapi.org/v2/top-headlines?category=general&country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1);
                break;
            case "gadgets":
                getSupportActionBar().setTitle("Gadgets");
                bundle1.putString("url", "https://newsapi.org/v2/top-headlines?category=technology&country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1);
                break;
            case "delhiNCR":
                getSupportActionBar().setTitle("Delhi-NCR");
                bundle1.putString("url", "https://newsapi.org/v2/top-headlines?category=general&country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1);
                break;
            case "sports":
                getSupportActionBar().setTitle("Sports");
                bundle1.putString("url", "https://newsapi.org/v2/top-headlines?category=sports&country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1);
                break;
            case "business":
                getSupportActionBar().setTitle("Business");
                bundle1.putString("url", "https://newsapi.org/v2/top-headlines?category=business&country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1);
                break;
            case "health":
                getSupportActionBar().setTitle("Health");
                bundle1.putString("url", "https://newsapi.org/v2/top-headlines?category=health&country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1);
                break;
            case "education":
                getSupportActionBar().setTitle("Education");
                bundle1.putString("url", "https://newsapi.org/v2/top-headlines?category=general&country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1);
                break;
            case "world":
                getSupportActionBar().setTitle("World");
                bundle1.putString("url", "https://newsapi.org/v2/top-headlines?category=general&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1);
                break;
            case "national":
                getSupportActionBar().setTitle("National");
                bundle1.putString("url", "https://newsapi.org/v2/top-headlines?category=general&country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6");
                fragment = new HomeFragment();
                fragment.setArguments(bundle1);
                break;
            case "feedback":
                fragment = new feedback();
                break;
            case "contactUs":
                fragment = new settings();
                break;
            case "settings":
                fragment = new settings();
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.fragments_container,fragment)
                .commit();

    }
}
