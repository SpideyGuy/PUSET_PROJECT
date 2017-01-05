package com.example.spidey.helloworld.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spidey.helloworld.Asset.DbHelperClass;
import com.example.spidey.helloworld.R;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    EditText username, password;

    String username_string, password_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btn_login);

        btnRegister = (Button) findViewById(R.id.btn_register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username_string = username.getText().toString();
                password_string = password.getText().toString();


                DbHelperClass db = new DbHelperClass(LoginActivity.this);
                Cursor cursor = db.getData(username_string, password_string);

                if (cursor.getCount() > 0) {
                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);

                    mainIntent.putExtra("username", username_string);
                    mainIntent.putExtra("password", password_string);

                    startActivity(mainIntent);
                    username.getText().clear();
                    password.getText().clear();

                    Log.d("username", username_string);
                    Log.d("password", password_string);

                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Sorry your credentials are wrong. Please enter the valid credentials", Toast.LENGTH_LONG).show();
                    username.getText().clear();
                    password.getText().clear();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
