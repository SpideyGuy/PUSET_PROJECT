package com.example.spidey.helloworld.Activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spidey.helloworld.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView mTitle, mDescription, mReleaseDate;
    private TextView mRating;

    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Movie App");


        mTitle = (TextView) findViewById(R.id.detail_title);
        mDescription = (TextView) findViewById(R.id.detail_description);
        mReleaseDate = (TextView) findViewById(R.id.detail_release_date);
        mRating = (TextView) findViewById(R.id.detail_rating);

        mImage = (ImageView) findViewById(R.id.backdrop);

        Intent intent = getIntent();

        String imageUrl = "http://image.tmdb.org/t/p/w185" +
                "" + intent.getStringExtra("image");

        Picasso.with(DetailActivity.this).load(imageUrl).placeholder(R.drawable.pets).into(mImage);

        mTitle.setText(intent.getStringExtra("title"));
        mDescription.setText(intent.getStringExtra("description"));
        mReleaseDate.setText("Release Date: " + intent.getStringExtra("release_date"));
        mRating.setText("Rating: " + intent.getStringExtra("rating") + "/ 10");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }
}
