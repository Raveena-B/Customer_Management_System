package com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.Database.DBHelper;
import com.example.maincrud.R;

public class ProfileManagement extends AppCompatActivity {

    EditText username,dob,password;
    Button add,updateprofile;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.et_NamePM);
        dob = findViewById(R.id.et_dobPM);
        password = findViewById(R.id.et_passwordPM);
        add = findViewById(R.id.btn_Add);
        updateprofile = findViewById(R.id.btn_Update);
        male = findViewById(R.id.rb_male);
        female = findViewById(R.id.rb_female);

        updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditProfile.class) ;
                startActivity(intent);
            }
        });


        //add

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pass the string value to the gender

                if(male.isChecked()){
                    gender="male";
                }
                else{
                    gender="female";
                }


                DBHelper dbHelper = new DBHelper(getApplicationContext());
                long newID = dbHelper.addInfo(username.getText().toString(),dob.getText().toString(),password.getText().toString(),gender);
                Toast.makeText(ProfileManagement.this, "User Added , User ID :"+newID, Toast.LENGTH_SHORT).show();

                Intent intent =new Intent(getApplicationContext(),EditProfile.class);
                startActivity(intent);

                username.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(true);
                female.setChecked(false);

            }
        });

    }



}