package com.ducklings_corp.tp5;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.*;

public class FragmentMovies extends Fragment implements View.OnClickListener {
    ArrayList<Movie> movieArrayList;
    String baseUrl = "http://www.omdbapi.com/?apikey=ecb0530b&%s";
    String requestUrl = "";
    ListView listResults;
    Button goBack;
    View view;
    String searchText;
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d("lol","Creating movieArrayList");
        view = layoutInflater.inflate(R.layout.movies_layout,viewGroup,false);

        searchText = ((MainActivity)getActivity()).parametersRequest();
        movieArrayList = new ArrayList<>();

        goBack = view.findViewById(R.id.goBackToTheFuture);
        listResults = view.findViewById(R.id.listResults);

        goBack.setOnClickListener(this);
        listResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)getActivity()).displayMovieDetails(movieArrayList.get(position));
            }
        });


        Log.d("lol", "onCreateView: Creating URL");
        requestUrl = String.format(baseUrl,"s="+ searchText);

        new GetMovies().execute();


        return view;
    }

    private class GetMovies extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            HttpURLConnection cnx;
            try {
                url = new URL(requestUrl);
                cnx = (HttpURLConnection) url.openConnection();
                Log.d("OMDb","Cnx");
                if(cnx.getResponseCode()==200) {
                    InputStream body;
                    InputStreamReader reader;

                    body = cnx.getInputStream();
                    reader = new InputStreamReader(body,"UTF-8");
                    parseJson(reader);
                } else {
                    Log.d("OMDb", "Non 200 code");
                }
            } catch (Exception e) {
                Log.d("OMDb","Error: "+e.getMessage());
            }
            return null;
        }

        protected void onPostExecute(Void v) {
            super.onPostExecute(v);

            MovieAdapter movieList = new MovieAdapter(movieArrayList,getActivity());
            listResults.setAdapter(movieList);
        }
    }

    private void parseJson(InputStreamReader reader) {
        JsonParser jsonParser = new JsonParser();
        JsonObject movieObject = jsonParser.parse(reader).getAsJsonObject();

        JsonArray listMovies = movieObject.get("Search").getAsJsonArray();
        boolean isOk = movieObject.get("Response").getAsBoolean();
        if(!isOk) {
            view.findViewById(R.id.showError).setVisibility(View.VISIBLE);
        }
        for(int i=0;i<listMovies.size();i++) {
            JsonObject movieJson = listMovies.get(i).getAsJsonObject();
            Movie movie = new Movie();
            movie.title = movieJson.get("Title").getAsString();
            movie.year = movieJson.get("Year").getAsInt();
            movie.id =  movieJson.get("imdbID").getAsString();
            movie.posterUrl = movieJson.get("Poster").getAsString();
            Log.d("Json", String.format("Pelicula: %s | ID: %s", movie.title,i));
            movie.execute();
            movieArrayList.add(movie);
        }
        for(Movie movie: movieArrayList) {
            try {
                //movie.poster = movie.get();
                Log.d("Json",String.format("%s finished downloading poster",movie.title));
            } catch (Exception e) {
                Log.d("Json","Error in async task downloading image");
            }
        }
    }

    public void onClick(View view){
        ((MainActivity)getActivity()).backToSearch();
    }
}
