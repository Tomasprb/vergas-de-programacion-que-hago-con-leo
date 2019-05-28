package com.example.tp3;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class actividadPrincipal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);


    }


    public void BuscarCategoria(View vista){
        Intent irABuscarCategorias;
        irABuscarCategorias = new Intent(actividadPrincipal.this,BuscarCat.class);
        startActivity(irABuscarCategorias);


    }
    public void BuscarNombre (View vista){
        Intent irABuscarNombre;
        irABuscarNombre = new Intent(actividadPrincipal.this,BuscarNombre.class);
        startActivity(irABuscarNombre);


    }

    public void BuscarUbicacion (View vista){
        Intent irABuscarRadio;
        irABuscarRadio = new Intent(actividadPrincipal.this,BuscarRadio.class);
        startActivity(irABuscarRadio);
    }
}
