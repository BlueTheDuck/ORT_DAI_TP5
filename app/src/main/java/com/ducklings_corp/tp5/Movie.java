package com.ducklings_corp.tp5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Movie extends AsyncTask<Void,Void, Bitmap> {
    public String title;
    public String posterUrl;
    public String id;
    public int year;
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
        } catch (Exception e) {
            Log.d("image",e.getMessage());
        }
        return image;
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
