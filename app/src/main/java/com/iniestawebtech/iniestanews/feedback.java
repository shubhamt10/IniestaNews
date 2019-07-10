package com.iniestawebtech.iniestanews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class feedback extends Fragment {
    ImageButton submit;
    EditText name,email,text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);
        submit=v.findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=v.findViewById(R.id.name);
                text=v.findViewById(R.id.messages);
                email=v.findViewById(R.id.mail);
                Toast.makeText(getActivity(), "Feedback submitted ", Toast.LENGTH_SHORT).show();

            }
        });
        return v;

    }
}




