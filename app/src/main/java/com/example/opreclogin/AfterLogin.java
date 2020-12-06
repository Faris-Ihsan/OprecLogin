package com.example.opreclogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AfterLogin extends AppCompatActivity {

    DatabaseHelper db;
    Button log_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        db = new DatabaseHelper(this);

        log_out = findViewById(R.id.buttonLogout);

        /*
        boolean checkSession = db.checkSession("ada");
        if (checkSession == true){
            Intent intent = new Intent(AfterLogin.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        */

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean updatesesi = db.updateSession("kosong", 1);
                if (updatesesi == true){

                    Toast.makeText(getApplicationContext(), "Berhasil Logout", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AfterLogin.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        });



    }
}