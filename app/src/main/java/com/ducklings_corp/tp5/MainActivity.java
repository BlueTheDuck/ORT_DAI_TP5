package com.ducklings_corp.tp5;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    FragmentManager manager;
    FragmentTransaction transaction;
    private int _searchType;
    private String _text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFragment(R.id.fragment,new FragmentSearch(),"search");
    }

    private void createFragment(int id, Fragment fragment, String tag) {
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(id,fragment,tag);
        transaction.commit();
    }

    public void submitSearch(int searchType,String text) {
        createFragment(R.id.fragment,new FragmentMovies(),"movies");
        _searchType = searchType;
        _text = text;
    }
    public void parametersRequest() {
        //hay que hacer una clase con dos atributos
    }
}
