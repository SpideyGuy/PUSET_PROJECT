package com.example.spidey.helloworld.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spidey.helloworld.Asset.NetworkUtils;
import com.example.spidey.helloworld.R;

public class MainActivity extends AppCompatActivity {

    TextView dynamicText;

    TextView link;
    TextView linkVolley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        dynamicText = (TextView) findViewById(R.id.dynamicText);

        link = (TextView) findViewById(R.id.link);
        linkVolley = (TextView) findViewById(R.id.linkVolley);


        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtils.isConnected(MainActivity.this)) {
                    Intent listIntent = new Intent(MainActivity.this, ListIntent.class);
                    startActivity(listIntent);
                } else {
                    Toast.makeText(MainActivity.this, "Please connect with the internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

        linkVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtils.isConnected(MainActivity.this)) {
                    Intent volleyIntent = new Intent(MainActivity.this, MovieList.class);
                    startActivity(volleyIntent);
                }
            }
        });

        dynamicText.setText(username + password);

        Toast.makeText(MainActivity.this, username + password, Toast.LENGTH_LONG).show();

    }
}
