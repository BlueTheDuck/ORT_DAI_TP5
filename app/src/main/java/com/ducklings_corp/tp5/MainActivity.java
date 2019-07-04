package com.ducklings_corp.tp5;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {
    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFragment(R.id.searchFragment,new FragmentSearch(),"search");
        createFragment(R.id.moviesFragment,new FragmentMovies(),"movies");
    }

    private void createFragment(int id, Fragment fragment, String tag) {
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(id,fragment,tag);
        transaction.commit();
    }
}
