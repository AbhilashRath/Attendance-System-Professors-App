package com.halo.AttenGoTeacher;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout rellay1, rellay2;
    private Button PNext,PFnish;
    private EditText PName,PSubject,PTNOL,PNOLIW;
    private DatabaseReference databaseReference;
    private DatabaseReference SubjectInfo;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        databaseReference = FirebaseDatabase.getInstance().getReference("Teacher Info");

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,Activity_Login.class));
        }
        PName=(EditText)findViewById(R.id.pname);
        PTNOL=(EditText)findViewById(R.id.ptnol);
        PNOLIW=(EditText)findViewById(R.id.pnoliw);
        PSubject=(EditText)findViewById(R.id.psubject);
        PFnish=(Button)findViewById(R.id.pfnish);
        PFnish.setOnClickListener(this);
        PNext=(Button)findViewById(R.id.pnext);
        PNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if ( v == PNext){
            rellay1.setVisibility(View.INVISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
        if (v == PFnish){
            saveUserInfromation();

        }

    }

    private void saveUserInfromation() {
        String Name = PName.getText().toString().trim();
        String Subject = PSubject.getText().toString().trim();
        String TNOL = PTNOL.getText().toString().trim();
        String NOLIW = PNOLIW.getText().toString().trim();
        String sub =PSubject.getText().toString().toUpperCase().trim();
        if (sub.equals("PHYSICS")){
            SubjectInfo = FirebaseDatabase.getInstance().getReference(("PhysicsInformation"));
        }
        else if (sub.equals("CHEMISTRY")){
            SubjectInfo = FirebaseDatabase.getInstance().getReference(("ChemistryInformation"));
        }
        else if (sub.equals("PETROLEUM")){
            SubjectInfo = FirebaseDatabase.getInstance().getReference(("PetroleumInformation"));
        }
        else if (sub.equals("MATHS")){
            SubjectInfo = FirebaseDatabase.getInstance().getReference(("MathsInformation"));
        }

        SubjectInformation subjectInformation = new SubjectInformation(TNOL,NOLIW);

        SubjectInfo.setValue(subjectInformation);

        UserInformation userInformation =new UserInformation(Name,Subject);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(this,"Information Saved",Toast.LENGTH_LONG).show();
        user = null;
        startActivity(new Intent(this,Activity_Login.class));

    }

}
