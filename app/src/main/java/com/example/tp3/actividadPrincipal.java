package com.example.tp3;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class actividadPrincipal extends Activity {
ArrayList<String> datosLista;
ArrayAdapter<String> miAdaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
        miAdaptador = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, datosLista);
        ListView miListView;
        miListView=findViewById(R.id.ListaVista);
        miListView.setAdapter(miAdaptador);
    }
    private class tareaAsincronica extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids){
            try{
                URL miRuta = new URL("http://epok.buenosaires.gob.ar/getCategorias");
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                Log.d("AccesoAPI","Me conecto");
                if(miConexion.getResponseCode()=200){
                    Log.d("AccesoAPI","Me conecto");
                    InputStream cuerpoRespuesta = miConexion.getInputStream();
                    InputStreamReader lectorRespuesta = new InputStreamReader(cuerpoRespuesta,"UTF-8");
                } else{
                    Log.d("AccesoAPI","Error en la conexion");
                }
                miConexion.disconnect();
            }catch(Exception error){
                Log.d("AccesoAPI","Hubo un error al conectarme " + error.getMessage());
            }
            return null;
        }

    }

    void BuscarCategoria(View vista){

    }
}
