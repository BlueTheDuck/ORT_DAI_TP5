package com.ducklings_corp.tp5;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentMovies extends Fragment {
    View view;
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        view = layoutInflater.inflate(R.layout.search_layout,viewGroup,false);

        ((MainActivity)getActivity()).parametersRequest();

        return view;
    }

    public void setSearchParameters(int searchType,String text) {
        Log.d("log",searchType+"");
        Log.d("log",text);
    }
}
