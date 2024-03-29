package com.ducklings_corp.tp5;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
    FragmentManager manager;
    FragmentTransaction transaction;
    private String _searchText;
    private Movie _movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _searchText = null;
        _movie = null;
        backToSearch();
    }

    private void createFragment(int id, Fragment fragment, String tag) {
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(id,fragment,tag);
        transaction.commit();
    }

    public void submitSearch(String searchText) {
        _searchText = searchText;
        createFragment(R.id.fragment,new FragmentMovies(),"movieArrayList");
    }
    public String parametersRequest() {
        return _searchText;
    }


    public void displayMovieDetails(Movie movie) {
        _movie = movie;
        createFragment(R.id.fragment,new FragmentMovieDetails(),"movieDetails");
    }
    public Movie movieRequest() {
        if(_movie==null) {
            throw new Error("No movie was chosen");
        }
        return _movie;
    }


    public void backToSearch() {
        createFragment(R.id.fragment,new FragmentSearch(),"search");
        _searchText = null;
        _movie = null;
    }
}
