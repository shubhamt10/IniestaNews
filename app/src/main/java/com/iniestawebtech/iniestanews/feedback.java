package com.iniestawebtech.iniestanews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
        name=v.findViewById(R.id.name);
        text=v.findViewById(R.id.messages);
        email=v.findViewById(R.id.mail);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(name.getText().toString().isEmpty()||email.getText().toString().isEmpty()||text.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(), "PlEASE ENTER VALID DETAILS", Toast.LENGTH_SHORT).show();
                }
                else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())

                {
                    Toast.makeText(getActivity(), "invalid email ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/html");
                    i.putExtra(Intent.EXTRA_EMAIL, new String[]{"customercare@iniestawebtech.com"});
                    i.putExtra(Intent.EXTRA_SUBJECT, "Feedback for IniestaNews App");
                    i.putExtra(Intent.EXTRA_TEXT, "Name : "+name.getText()+"\nMessage : "+text.getText());
                    try {
                        startActivity(Intent.createChooser(i, "Send feedback..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }
                //Toast.makeText(getActivity(), "Feedback submitted ", Toast.LENGTH_SHORT).show();

            }
        });
        return v;

    }
}




