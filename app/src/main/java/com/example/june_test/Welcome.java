package com.example.june_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.june_test.Database.DBHandler;

import java.util.List;

public class Welcome extends AppCompatActivity {

    EditText etUserName, etAge, etUN;
    Button btnsearch;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        etUserName = findViewById(R.id.etUserName_WC);
        etAge = findViewById(R.id.etAge_WC);
        etUN  = findViewById(R.id.etUN_WC);

        btnsearch = findViewById(R.id.btnSearch_WC);



        //find user by name
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List user = dbHandler.readALlInfo(etUserName.getText().toString());

                if(user.isEmpty()){
                    Toast.makeText(Welcome.this, "No user", Toast.LENGTH_SHORT).show();
                    etUserName.setText(null);
                }
                else{
                    Toast.makeText(Welcome.this, "User Found", Toast.LENGTH_SHORT).show();
                    etUN.setText(user.get(0).toString());
                    etAge.setText(user.get(1).toString());
                }
            }
        });


    }
}
