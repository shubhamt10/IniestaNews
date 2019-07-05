package com.iniesta.iniestanews;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class HomeFragment extends Fragment {

    private RecyclerView latestRecyclerView;
    private ProgressBar latestProgressBar;
    private AdView mAdView;
    public String latestUrl ;
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            latestUrl = getArguments().get("url").toString();
        }catch (NullPointerException e){
            e.getMessage();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);

        latestRecyclerView = view.findViewById(R.id.latestRecyclerView);
        latestProgressBar = view.findViewById(R.id.latestProgressBar);
        Sprite wave = new Wave();
        latestProgressBar.setIndeterminateDrawable(wave);

        init();

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
//                .addTestDevice("67219F41FBCD43C8B4236BC141E830D9")
//                .addTestDevice("8F3D79A8C87E3791214A712C9FBAE215").build();
        mAdView.loadAd(adRequest);

//        mAdView.setAdListener(new AdListener(){
//            @Override
//            public void onAdClosed() {
//                super.onAdClosed();
//            }
//
//            @Override
//            public void onAdFailedToLoad(int i) {
//                System.out.println("Ad failed to load");
//                AdRequest adRequest = new AdRequest.Builder().build();
//                mAdView.loadAd(adRequest);
//                super.onAdFailedToLoad(i);
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//                super.onAdLeftApplication();
//            }
//
//            @Override
//            public void onAdOpened() {
//                super.onAdOpened();
//            }
//
//            @Override
//            public void onAdLoaded() {
//                super.onAdLoaded();
//            }
//
//            @Override
//            public void onAdClicked() {
//                super.onAdClicked();
//            }
//
//            @Override
//            public void onAdImpression() {
//                super.onAdImpression();
//            }
//        });

        return view;
    }
    @Override
    public void onResume() {
        SharedPreferences sp = getActivity().getSharedPreferences("check" , Context.MODE_PRIVATE);
        int a=sp.getInt("cj",0);

        if(a==0)
        {

            view.setBackgroundColor(getResources().getColor(R.color.black));
        }
        else {

            view.setBackgroundColor(getResources().getColor(R.color.white));
        }
        super.onResume();
    }

    public void init() {
        if (!(AppStatus.getInstance(getContext()).isOnline())) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Network Issue");
            builder.setMessage("Check Your Internet Connection");
            builder.setIcon(R.drawable.png);
            builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    init();
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else{
            new DownloadTask(latestRecyclerView,latestProgressBar,getContext()).execute(latestUrl);
        }
    }

}

//play store
//ca-app-pub-5589355018838308~7806932209
//ca-app-pub-5589355018838308/7745963274

//ca-app-pub-5589355018838308~9779092060
//ca-app-pub-5589355018838308/4655344622

//test
//ca-app-pub-3940256099942544~3347511713
//ca-app-pub-3940256099942544/6300978111

//    ProgressBar progressBar = view.findViewById(R.id.spin_kit);
//    Sprite wave = new Wave();
//progressBar.setIndeterminateDrawable(wave);