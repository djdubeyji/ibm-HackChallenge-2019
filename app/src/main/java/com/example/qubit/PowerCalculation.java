package com.example.qubit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PowerCalculation extends AppCompatActivity implements View.OnClickListener {
TextView customerid,power;
    EditText devices,rating,Mobileno;
    String devicename,ratingentered;
String customeridGOT,powerGOT;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    Button save,signout;
    int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_calculation);
        power=(TextView)findViewById(R.id.power);
        devices=(EditText)findViewById(R.id.devices);
        rating=(EditText)findViewById(R.id.rating);
        save=(Button)findViewById(R.id.Save);
        save.setOnClickListener(this);
        signout=(Button)findViewById(R.id.signouuut);
        signout.setOnClickListener(this);
        Mobileno=(EditText)findViewById(R.id.mobileno);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        SupportingClass s=new SupportingClass();

    }

    @Override
    public void onClick(View v) {
        if (v == save)
        {
            devicename = devices.getText().toString();
            ratingentered=rating.getText().toString();
            customeridGOT=Mobileno.getText().toString();
            powerGOT="500";
            Toast.makeText(getApplicationContext(),""+devicename+"                 "+ratingentered,Toast.LENGTH_LONG).show();
            SupportingClass  supportingclass=new SupportingClass(customeridGOT,devicename,ratingentered,powerGOT);
            databaseReference.child("Customer").push().child("customeridGOT").setValue(supportingclass.getCustomeridGOT());
            databaseReference.child("Customer").push().child("devicename").setValue(supportingclass.getDevicename());
            databaseReference.child("Customer").push().child("ratingentered").setValue(supportingclass.getRatingentered());
            databaseReference.child("Customer").push().child("powerGOT").setValue(supportingclass.getPowerGOT());
            count++;
        }
        if(v==signout)
        {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            firebaseAuth.signOut();
        }
    }
}
