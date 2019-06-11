package com.iniesta.iniestanews;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class FragmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        String type = getIntent().getStringExtra("type");
        Fragment fragment;
        if (type.equals("settings")){
//            fragment = new settings();
//            getSupportFragmentManager().beginTransaction()
//              .replace(R.id.fragments_container,fragment)
//                    .add(new settings, "dash")
//                    .addToBackStack("dash")
//                        .commit();
        }

    }
}
