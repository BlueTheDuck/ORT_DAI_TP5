package com.ducklings_corp.tp5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/*
 *
 * |   |   /---\   |
 * |   |  /     \  |        /\
 * |---|  |     |  |       /  \
 * |   |  \     /  |      /----\
 * |   |   \---/   |----  |    |
 *
 * */

public class MovieAdapter extends BaseAdapter {
    private ArrayList<Movie> _movies;
    private Context _context;

    public MovieAdapter(ArrayList<Movie> movies, Context context){
        _movies = movies;
        _context = context;
    }

    @Override
    public int getCount() {
        return _movies.size();
    }

    @Override
    public Movie getItem(int position) {
        return _movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        LayoutInflater layoutInflater;
        Movie movie = getItem(position);

        layoutInflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(R.layout.movie_list_item, parent, false);

        //((ImageView)view.findViewById(R.id.moviePoster)).setImageBitmap(movie.poster);
        movie.applyImage(view);
        ((TextView)view.findViewById(R.id.movieTitle)).setText(movie.title);
        ((TextView)view.findViewById(R.id.movieYear)).setText(movie.year+"");

        return view;
    }
}
