package com.ducklings_corp.tp5;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.*;

public class FragmentMovies extends Fragment implements View.OnClickListener {
    String baseUrl = "http://www.omdbapi.com/?apikey=ecb0530b&%s";
    String requestUrl = "";
    ListView listResults;
    Button goBack;
    View view;
    SearchData searchData;
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d("lol","Creating movies");
        view = layoutInflater.inflate(R.layout.movies_layout,viewGroup,false);

        searchData = ((MainActivity)getActivity()).parametersRequest();

        listResults = view.findViewById(R.id.listResults);
        goBack = view.findViewById(R.id.goBackToTheFuture);

        goBack.setOnClickListener(this);

        if(searchData.type==false) {
            Log.d("lol", "onCreateView: Creating URL");
            requestUrl = String.format(baseUrl,"s="+searchData.text);
        } else {
            throw new Error("lol");
        }

        return view;
    }

    private class GetMovies extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            HttpURLConnection cnx;
            try {
                url = new URL(requestUrl);
                cnx = (HttpURLConnection) url.openConnection();
                Log.d("OMDb","Cnx");
                if(cnx.getResponseCode()==200) {
                    InputStream body;
                    InputStreamReader reader;

                    body = cnx.getInputStream();
                    reader = new InputStreamReader(body,"UTF-8");
                    parseJson(reader);
                } else {
                    Log.d("OMDb", "Non 200 code");
                }
            } catch (Exception e) {
                Log.d("OMDb","Error: "+e.getMessage());
            }
            return null;
        }

        protected void onPostExecute(Void v) {
            super.onPostExecute(v);

            /*ListView listPlaces;
            listPlaces = view.findViewById(R.id.places);
            listPlaces.setAdapter(placesAdapter);*/
        }
    }

    private void parseJson(InputStreamReader reader) {
        JsonParser jsonParser = new JsonParser();
        JsonObject movieObject = jsonParser.parse(reader).getAsJsonObject();

        JsonArray listMovies = movieObject.get("Search").getAsJsonArray();
        int responses = movieObject.get("totalResults").getAsInt();
        boolean isOk = movieObject.get("Response").getAsBoolean();
        if(!isOk) {
            view.findViewById(R.id.showError).setVisibility(View.VISIBLE);
        }
        for(int i=0;i<responses;i++) {

        }
    }

    public void onClick(View view){
        ((MainActivity)getActivity()).backToSearch();
    }
}
