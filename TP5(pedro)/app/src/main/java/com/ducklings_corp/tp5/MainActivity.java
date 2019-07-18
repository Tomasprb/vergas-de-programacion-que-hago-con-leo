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
    private SearchData _searchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backToSearch();
    }

    private void createFragment(int id, Fragment fragment, String tag) {
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(id,fragment,tag);
        transaction.commit();
    }

    public void submitSearch(SearchData searchData) {
        createFragment(R.id.fragment,new FragmentMovies(),"movies");
        _searchData = searchData;
    }
    public SearchData parametersRequest() {
        return _searchData;
    }

    public void backToSearch() {
        createFragment(R.id.fragment,new FragmentSearch(),"search");
        _searchData = null;
    }
}
