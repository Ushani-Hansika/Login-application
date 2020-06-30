package com.example.june_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.june_test.Database.DBHandler;

public class Registration extends AppCompatActivity {

    EditText etUserNameR, etAgeR, etPasswordR;
    Button btnRegiserR;
    RadioButton male, female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etUserNameR = findViewById(R.id.txtUserName_R);
        etAgeR = findViewById(R.id.txtAge_R);
        etPasswordR = findViewById(R.id.txtPassword_R);
        male = findViewById(R.id.rd_male);
        female = findViewById(R.id.rd_female);
        btnRegiserR = findViewById(R.id.btnRegister_R);


        //user registration
        btnRegiserR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(male.isChecked()){
                    gender = "Male";
                }else{
                    gender  = "Female";
                }

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long newId = dbHandler.addInfo(etUserNameR.getText().toString(),etAgeR.getText().toString(),etPasswordR.getText().toString(),gender);

                Toast.makeText(Registration.this, "User Added. User ID: "+newId, Toast.LENGTH_SHORT).show();


            }
        });
    }
}
