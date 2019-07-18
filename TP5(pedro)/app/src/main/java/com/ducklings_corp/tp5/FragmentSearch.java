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
    RadioButton searchGeneral, searchId;
    RadioGroup searchType;
    EditText textSearch;
    TextView showError;
    View view;
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        view = layoutInflater.inflate(R.layout.search_layout,viewGroup,false);

        doSearch = view.findViewById(R.id.doSearch);
        searchGeneral = view.findViewById(R.id.searchGeneral);
        searchId = view.findViewById(R.id.searchId);
        searchType = view.findViewById(R.id.searchType);
        textSearch = view.findViewById(R.id.textSearch);
        showError = view.findViewById(R.id.showError);

        doSearch.setOnClickListener(this);

        return view;
    }

    public void onClick(View view){
        Log.d("log", "submting");
        if (textSearch.getText().toString().isEmpty()){
            showError.setVisibility(View.VISIBLE);
        } else if (searchType.getCheckedRadioButtonId()==-1){
            showError.setVisibility(View.VISIBLE);
        }else{
            SearchData searchData = new SearchData();
            searchData.text = textSearch.getText().toString();
            searchData.type = searchType.getCheckedRadioButtonId()==R.id.searchId;
            ((MainActivity)getActivity()).submitSearch(searchData);
        }
    }
}
