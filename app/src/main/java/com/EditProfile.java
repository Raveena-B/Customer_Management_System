package com;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.Database.DBHelper;
import com.example.maincrud.R;

import java.util.List;

public class EditProfile extends AppCompatActivity {


    EditText username,dob,password;
    Button edit,delete,serach;
    RadioButton male,female;
    String gender;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        username = findViewById(R.id.et_NameEP);
        dob = findViewById(R.id.et_dobEP);
        password = findViewById(R.id.et_PaswordEP);
        edit = findViewById(R.id.btn_edit);
        delete = findViewById(R.id.btn_delete);
        serach = findViewById(R.id.btn_Search);
        male = findViewById(R.id.btn_male);
        female = findViewById(R.id.rb_femal);



        //search


        serach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DBHelper dbHelper = new DBHelper(getApplicationContext());
                List user = dbHelper.readAllInfo(username.getText().toString());

                if(user.isEmpty()){
                    Toast.makeText(EditProfile.this, "No user", Toast.LENGTH_SHORT).show();
                    username.setText(null);
                }
                else{

                    Toast.makeText(EditProfile.this, "User Found , User Name"+user.get(0).toString(), Toast.LENGTH_SHORT).show();
                   username.setText(user.get(0).toString());
                   dob.setText(user.get(1).toString());
                   password.setText(user.get(2).toString());

                   if(user.get(3).toString().equals("Male")){
                       male.setChecked(true);
                   }
                   else{
                       female.setChecked(true);
                   }
                }
            }
        });



        //update

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(male.isChecked()){
                    gender="male";
                }
                else{
                    gender="female";
                }

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                Boolean status = dbHelper.updateInfo(username.getText().toString(),dob.getText().toString(),password.getText().toString(),gender);

                if(status){
                    Toast.makeText(EditProfile.this, "User Updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EditProfile.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });







        //delete

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                dbHelper.deleteInfo(username.getText().toString());

                Toast.makeText(EditProfile.this, "User Deleted", Toast.LENGTH_SHORT).show();

                username.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(false);
                female.setChecked(false);
            }
        });
    }
}