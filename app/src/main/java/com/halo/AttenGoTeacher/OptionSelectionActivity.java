package com.halo.AttenGoTeacher;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class OptionSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView STUDINFO,GENOTP,TOTALINFO;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_selection);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,Activity_Login.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        GENOTP =(ImageView)findViewById(R.id.genotp);
        STUDINFO =(ImageView)findViewById(R.id.studinfo);
        TOTALINFO =(ImageView)findViewById(R.id.totalinfo);
        GENOTP.setOnClickListener(this);
        STUDINFO.setOnClickListener(this);
        TOTALINFO.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == STUDINFO){
            startActivity(new Intent(OptionSelectionActivity.this,StudentInfoActivity.class));
        }
        if (v == GENOTP){
            Intent intent =new Intent(OptionSelectionActivity.this,LastActivity.class);
            intent.putExtra("Selected Option", "genotp");
            startActivity(intent);
        }
        if (v == TOTALINFO){
            Intent intent =new Intent(OptionSelectionActivity.this,LastActivity.class);
            intent.putExtra("Selected Option", "total");
            startActivity(intent);
        }

    }
}
