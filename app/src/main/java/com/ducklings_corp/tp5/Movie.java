package com.ducklings_corp.tp5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Movie extends AsyncTask<Void, Void, Bitmap> {
    public String title;
    public String plot;
    public int year;
    public String posterUrl;
    public String id;
    public Bitmap poster;

    protected Bitmap doInBackground(Void... voids) {
        Bitmap image = null;
        try {
            URL url = new URL(posterUrl);
            HttpURLConnection cnx;
            cnx = (HttpURLConnection) url.openConnection();
            if(cnx.getResponseCode()==200) {
                InputStream stream = cnx.getInputStream();
                BufferedInputStream buffer = new BufferedInputStream(stream);
                image = BitmapFactory.decodeStream(buffer);
                cnx.disconnect();
            }

            url = new URL(String.format("http://www.omdbapi.com/?apikey=ecb0530b&i=%s",id));
            cnx = (HttpURLConnection) url.openConnection();
            if(cnx.getResponseCode()==200) {
                InputStream body;
                InputStreamReader reader;

                body = cnx.getInputStream();
                reader = new InputStreamReader(body,"UTF-8");
                parseJson(reader);
                cnx.disconnect();
            }
        } catch (Exception e) {
            Log.d("image",e.getMessage());
        }
        return image;
    }

    private void parseJson(InputStreamReader reader) {
        JsonParser jsonParser = new JsonParser();
        JsonObject movieObject = jsonParser.parse(reader).getAsJsonObject();
        plot =  movieObject.get("plot").getAsString();
    }

    protected void onPostExecute(Bitmap image) {
        if(image==null) {
            Log.d("image","The image could not be downloaded");
        } else {
            Log.d("image","Image downloaded");
            poster = image;
        }
    }

    public void applyImage(View view) {
        try {
            if(poster==null)
                poster = this.get();
            ((ImageView) view.findViewById(R.id.moviePoster)).setImageBitmap(poster);
        } catch (Exception e) {
            Log.d("movie","Failed to download image: "+e.getMessage());
        }
    }
}
