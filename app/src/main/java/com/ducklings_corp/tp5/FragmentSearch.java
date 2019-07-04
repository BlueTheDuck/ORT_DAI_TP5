package com.ducklings_corp.tp5;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentSearch extends Fragment {
    View view;
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        view = layoutInflater.inflate(R.layout.search_layout,viewGroup,false);

        return view;
    }
}
