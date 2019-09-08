package com.iniesta.iniestanews;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

public class settings extends Fragment {
    Switch b1;
    SharedPreferences  sp;
    SharedPreferences.Editor editor ;
    CardView c1;
    View v;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sp = getActivity().getSharedPreferences("check" ,Context.MODE_PRIVATE);
        editor = sp.edit();
        v = inflater.inflate(R.layout.fragment_settings, container, false);
        c1 = v.findViewById(R.id.card1);
        b1 = v.findViewById(R.id.button);
        if(sp.getInt("cj",0)==1) {
            b1.setChecked(true);
            v.setBackgroundColor(getResources().getColor(R.color.white));
            c1.setCardBackgroundColor(getResources().getColor(R.color.white)); }
        else {
            v.setBackgroundColor(getResources().getColor(R.color.black));
        }
        b1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked) {
                   editor.putInt("cj",1).apply();
                   v.setBackgroundColor(getResources().getColor(R.color.white));
                   c1.setCardBackgroundColor(getResources().getColor(R.color.white)); }
               else {
                   v.setBackgroundColor(getResources().getColor(R.color.black));
                   editor.putInt("cj",0).apply();
               }
            }
       });
        return v; }
}

