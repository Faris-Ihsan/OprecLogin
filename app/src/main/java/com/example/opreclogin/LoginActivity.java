package com.example.opreclogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button signup, signin;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        signup      = findViewById(R.id.buttonSignup);
        signin      = findViewById(R.id.buttonSignin);
        username    = findViewById(R.id.uname);
        password    = findViewById(R.id.password);


        //sign in (Log in)
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String struname = username.getText().toString();
                String strpasswd = password.getText().toString();
                Boolean login = db.checkLogin(struname, strpasswd);
                if (login == true) {
                    boolean updateSession = db.updateSession("ada", 1);
                    if (updateSession == true){
                        Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, AfterLogin.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void register(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }


}