package com.halo.AttenGoTeacher;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class LastActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout rellay1, rellay2;
    private TextView Totalata,TitleOTP;
    private Button GenOTP;
    private Integer GGenerateOTP;
    private FirebaseAuth firebaseAuth;
    Timer timer;
    private DatabaseReference otpRef,subRef,TotalAttendenceCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        TotalAttendenceCount =FirebaseDatabase.getInstance().getReference("TotalAttendenceCount");
        subRef = FirebaseDatabase.getInstance().getReference("Teacher Info");
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,Activity_Login.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        TitleOTP = (TextView)findViewById(R.id.titalotp);
        Totalata = (TextView)findViewById(R.id.ata);
        GenOTP =(Button)findViewById(R.id.genrateotp);
        GenOTP.setOnClickListener(this);
        Intent intent = getIntent();
        String lo = intent.getStringExtra("Selected Option");
        if(lo == null) {
            lo = "empty string";
        }
        if (lo.equals("genotp")){
            rellay2.setVisibility(View.VISIBLE);
        }
        if (lo.equals("total")){
            rellay1.setVisibility(View.VISIBLE);
        }
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String formattedDate = df.format(c);
        subRef.child(user.getUid()).child("Subject").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sub = dataSnapshot.getValue(String.class);
                switch (sub){
                    case "Chemistry":
                        TotalAttendenceCount.child(formattedDate).child("ChemistryTotalCounter").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Totalata.setText(dataSnapshot.getValue(Integer.class).toString());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;
                    case "Physics":
                        TotalAttendenceCount.child(formattedDate).child("PhysicsTotalCounter").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Totalata.setText(dataSnapshot.getValue(Integer.class).toString());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;
                    case "Petroleum":
                        TotalAttendenceCount.child(formattedDate).child("PetroleumTotalCounter").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Totalata.setText(dataSnapshot.getValue(Integer.class).toString());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;
                    case "Maths":
                        TotalAttendenceCount.child(formattedDate).child("MathsTotalCounter").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Totalata.setText(dataSnapshot.getValue(Integer.class).toString());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v==GenOTP){
            GGenerateOTP =  new Random().nextInt(99999)+1;
            otpRef = FirebaseDatabase.getInstance().getReference("OTP");
            TitleOTP.setText(GGenerateOTP.toString());
            saveOTP();

        }

    }
    private void saveOTP() {
        Integer otp = GGenerateOTP;
        otpRef.setValue(otp);
        Integer ChemistryTotalCounter = 0;
        Integer PhysicsTotalCounter = 0;
        Integer PetroleumTotalCounter = 0;
        Integer MathsTotalCounter =0 ;
        Date c = Calendar.getInstance().getTime();
        UserAttendenceInfo userAttendenceInfo =new UserAttendenceInfo(ChemistryTotalCounter,PhysicsTotalCounter,PetroleumTotalCounter,MathsTotalCounter);
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String formattedDate = df.format(c);
        TotalAttendenceCount.child(formattedDate).setValue(userAttendenceInfo);
        Toast.makeText(LastActivity.this,"OTP is Saved",Toast.LENGTH_LONG).show();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(LastActivity.this,OptionSelectionActivity.class));
            }
        },20000);
    }
}
