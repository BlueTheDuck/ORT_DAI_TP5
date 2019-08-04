package com.ducklings_corp.tp5;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FragmentSearch extends Fragment implements View.OnClickListener {
    Button doSearch;
    EditText textSearch;
    TextView showError;
    View view;
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        view = layoutInflater.inflate(R.layout.search_layout,viewGroup,false);

        doSearch = view.findViewById(R.id.doSearch);
        textSearch = view.findViewById(R.id.textSearch);
        showError = view.findViewById(R.id.showError);

        doSearch.setOnClickListener(this);

        return view;
    }

    public void onClick(View view){
        Log.d("log", "submting");
        if (textSearch.getText().toString().isEmpty()){
            showError.setVisibility(View.VISIBLE);
        } else{
            String searchText = textSearch.getText().toString();
            ((MainActivity)getActivity()).submitSearch(searchText);
        }
    }
}
