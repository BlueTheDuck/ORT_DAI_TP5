package com.ducklings_corp.tp5;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentMovieDetails extends Fragment implements View.OnClickListener {
    View view;
    Button goBack;
    Movie movie;
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        view = layoutInflater.inflate(R.layout.movie_details_layout,viewGroup,false);

        goBack = view.findViewById(R.id.goBackToTheFuture);

        goBack.setOnClickListener(this);

        movie = ((MainActivity)getActivity()).movieRequest();

        return view;
    }

    public void onClick(View view){
        ((MainActivity)getActivity()).backToSearch();
    }
}
