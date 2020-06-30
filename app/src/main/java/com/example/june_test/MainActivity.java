package com.example.june_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.june_test.Database.DBHandler;

public class MainActivity extends AppCompatActivity {

    EditText etUserNameM, etPasswordM;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserNameM = findViewById(R.id.etUserName_M);
        etPasswordM = findViewById(R.id.etPassword_M);

        btnLogin = findViewById(R.id.btnLogin_M);
        btnRegister = findViewById(R.id.btnRegister_M);

        //redirect to the registration page
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Registration.class);
                startActivity(i);
            }
        });


        //login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                if (dbHandler.LoginUser(etUserNameM.getText().toString(), etPasswordM.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Successfully Loggedin", Toast.LENGTH_SHORT).show();
                    Intent i1 = new Intent(getApplicationContext(), Welcome.class);
                    startActivity(i1);

                }else{
                    Toast.makeText(MainActivity.this, "Invalid User Details", Toast.LENGTH_SHORT).show();
                    etUserNameM.setText(null);
                    etPasswordM.setText(null);
                }
            }
        });

    }


}
