package com.example.spidey.helloworld.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.spidey.helloworld.Adapter.MovieAdapter;
import com.example.spidey.helloworld.Asset.VolleySingleton;
import com.example.spidey.helloworld.Model.MovieModel;
import com.example.spidey.helloworld.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieList extends AppCompatActivity {

    ListView listData;

    MovieAdapter movieAdapter;

    private RequestQueue mRequestQueue;

    private VolleySingleton mVolleySingleton;

    String string_url = "https://api.themoviedb.org/3/movie/popular?api_key=06b4464f14a06a1483b90cba6933e7e7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);


        listData = (ListView) findViewById(R.id.movieList);

        mRequestQueue = mVolleySingleton.getRequestQueue();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, string_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    ArrayList<MovieModel> movieModels = getMovieList(response);

                    movieAdapter = new MovieAdapter(MovieList.this, movieModels);
                    listData.setAdapter(movieAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(jsonObjectRequest);


    }


    private ArrayList<MovieModel> getMovieList(JSONObject movieListJson) throws JSONException {

        final String TMDB_RESULTS = "results";
        final String TMDB_TITLE = "title";
        final String TMDB_ID = "id";
        final String TMDB_IMAGE = "poster_path";
        final String TMDB_OVERVIEW = "overview";
        final String TMDB_VOTE = "vote_average";
        final String TMDB_RELEASE = "release_date";

        ArrayList<MovieModel> movies = new ArrayList<>();

//        JSONObject movieListJson = new JSONObject(movieList);
        JSONArray movieArray = movieListJson.getJSONArray(TMDB_RESULTS);

        for (int i = 0; i < movieArray.length(); i++) {


            JSONObject movieJson = movieArray.getJSONObject(i);


            MovieModel movie = new MovieModel();


            movie.setId(movieJson.getInt(TMDB_ID));
            movie.setTitle(movieJson.getString(TMDB_TITLE));
            movie.setImage(movieJson.getString(TMDB_IMAGE));
            movie.setOverview(movieJson.getString(TMDB_OVERVIEW));
            movie.setRating(movieJson.getDouble(TMDB_VOTE));
            movie.setReleaseDate(movieJson.getString(TMDB_RELEASE));

            movies.add(movie);
        }
        return movies;
    }

}
