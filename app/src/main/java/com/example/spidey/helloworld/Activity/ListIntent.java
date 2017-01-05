package com.example.spidey.helloworld.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.spidey.helloworld.Adapter.MovieAdapter;
import com.example.spidey.helloworld.Model.MovieModel;
import com.example.spidey.helloworld.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class ListIntent extends AppCompatActivity {

    ListView listData;

    ProgressDialog progressDialog;

    ArrayAdapter<String> dataAdapter;

    MovieAdapter movieAdapter;

//    String[] listDataArray = {"Hello", "Bonjour", "Hi", "Nihao", "Ciao"};
//    int[] listDataIntArray = {1, 2, 3, 4, 5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_intent);

        Toolbar toolbar;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);

        listData = (ListView) findViewById(R.id.listData);

        new FetchMovieList().execute();


//        dataAdapter = new ArrayAdapter<String>(ListIntent.this, android.R.layout.simple_list_item_1, new ArrayList<String>());
////
//        listData.setAdapter(dataAdapter);

//        for (String individual_data : listDataArray) {
//            dataAdapter.add(individual_data);
//        }

    }


    private class FetchMovieList extends AsyncTask<Void, Void, ArrayList<MovieModel>> {

        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(ListIntent.this);

            progressDialog.setMessage("Loading...fetching data from the server");

            progressDialog.show();


            super.onPreExecute();
        }

        @Override
        protected ArrayList<MovieModel> doInBackground(Void... params) {

            String string_url = "https://api.themoviedb.org/3/movie/popular?api_key=06b4464f14a06a1483b90cba6933e7e7";

            HttpURLConnection urlConnection = null;
            BufferedReader bufferedReader = null;

            StringBuffer stringBuffer;


            URL url = null;
            try {

                // URL build part
                url = new URL(string_url);


                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect(); //url connection established

                InputStream inputStream = urlConnection.getInputStream();  //fetching data
                stringBuffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }

                if (stringBuffer.length() == 0) {
                    return null;
                }

                String movieList = stringBuffer.toString();    // till this step; the data has been fetched in the form of string

                Log.d("fetcheddata", movieList);

                ArrayList<MovieModel> movieModels = getMovieList(movieList);

                return movieModels;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<MovieModel> movieModels) {
            super.onPostExecute(movieModels);
            progressDialog.hide();

            movieAdapter = new MovieAdapter(ListIntent.this, movieModels);
            listData.setAdapter(movieAdapter);

//            for (int i = 0; i < movieModels.size(); i++) {
//                MovieModel movieModel = movieModels.get(i);
//                dataAdapter.add(movieModel.getTitle());
//            }


        }
    }

    private ArrayList<MovieModel> getMovieList(String movieList) throws JSONException {

        final String TMDB_RESULTS = "results";
        final String TMDB_TITLE = "title";
        final String TMDB_ID = "id";
        final String TMDB_IMAGE = "poster_path";
        final String TMDB_OVERVIEW = "overview";
        final String TMDB_VOTE = "vote_average";
        final String TMDB_RELEASE = "release_date";

        ArrayList<MovieModel> movies = new ArrayList<>();

        JSONObject movieListJson = new JSONObject(movieList);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
