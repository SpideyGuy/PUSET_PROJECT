package com.example.spidey.helloworld.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spidey.helloworld.Activity.DetailActivity;
import com.example.spidey.helloworld.Model.MovieModel;
import com.example.spidey.helloworld.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by spidey on 1/4/17.
 */

public class MovieAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<MovieModel> movieList = new ArrayList<>();

    public MovieAdapter(Context context, ArrayList<MovieModel> movies) {
        this.mContext = context;
        this.movieList = movies;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_row, parent, false);
        }

        MovieModel currentModel = (MovieModel) getItem(position);

        String imageUrl = "http://image.tmdb.org/t/p/w185" +
                "" + movieList.get(position).getImage();

        ImageView image = (ImageView) convertView.findViewById(R.id.movieImage);

        Picasso.with(mContext).load(imageUrl).placeholder(R.drawable.pets).into(image);

        TextView title = (TextView) convertView.findViewById(R.id.movieTitle);
        TextView rating = (TextView) convertView.findViewById(R.id.movieRating);
        TextView releaseDate = (TextView) convertView.findViewById(R.id.movieReleaseDate);

        title.setText(currentModel.getTitle());
        rating.setText(String.valueOf(currentModel.getRating() + " /10"));
        releaseDate.setText(currentModel.getReleaseDate());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(mContext, DetailActivity.class);

                mainIntent.putExtra("title", movieList.get(position).getTitle());
                mainIntent.putExtra("description", movieList.get(position).getOverview());
                mainIntent.putExtra("release_date", movieList.get(position).getReleaseDate());
                mainIntent.putExtra("rating", movieList.get(position).getRating());
                mainIntent.putExtra("image", movieList.get(position).getImage());

                mContext.startActivity(mainIntent);

                Toast.makeText(mContext, "This is the toast message", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
