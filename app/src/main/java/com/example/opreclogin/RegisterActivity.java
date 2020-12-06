package com.example.opreclogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button signup;
    EditText email, username, password, passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        signup          = findViewById(R.id.ERROR_WAE_IEU_BUTTON_LIEURRR);
        email           = findViewById(R.id.emailSignUp);
        username        = findViewById(R.id.usernameSignUp);
        password        = findViewById(R.id.passwordSignUp);
        passwordConfirm = findViewById(R.id.confirmPassword);

        //Registrasi bos

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stremail = email.getText().toString();
                String struname = username.getText().toString();
                String strpassword = password.getText().toString();
                String strpasswordconfirm = passwordConfirm.getText().toString();
                if (strpassword.equals(strpasswordconfirm)) {
                    boolean regist = db.insertUser(stremail, struname, strpassword);
                    if (regist == true) {
                        Toast.makeText(getApplicationContext(), "Berhasil Terdaftar", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Gagal Daftar", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Password tidak cocok", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

}