package com.iniesta.iniestanews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    private RecyclerView latestRecyclerView;
    private ProgressBar latestProgressBar;
    public String latestUrl ;
    //= "https://newsapi.org/v2/top-headlines?country=in&pageSize=20&apiKey=598ae4e3c5c940ff991d7f44b9f3dde6";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            latestUrl = getArguments().get("url").toString();
        }catch (NullPointerException e){
            e.getMessage();
            Toast.makeText(getContext(),latestUrl,Toast.LENGTH_LONG).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        latestRecyclerView = view.findViewById(R.id.latestRecyclerView);
        latestProgressBar = view.findViewById(R.id.latestProgressBar);
        new DownloadTask(latestRecyclerView,latestProgressBar,getContext()).execute(latestUrl);

        return view;
    }

}
