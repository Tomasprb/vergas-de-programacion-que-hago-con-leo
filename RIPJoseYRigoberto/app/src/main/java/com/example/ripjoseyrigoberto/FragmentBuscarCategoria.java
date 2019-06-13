package com.example.ripjoseyrigoberto;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FragmentBuscarCategoria extends Fragment implements View.OnClickListener {
    ArrayList<String> datosLista;
    ListView miListaCategorias;
    ArrayAdapter<String> miAdaptador;
    public View onCreateView(LayoutInflater cheeseBorger, ViewGroup grupoDeLaVista, Bundle riachuelo){
        Log.d("algoMuyOriginal", "onCreateView buscarCategoria");

        Log.d("algoMuyOriginal", "declaro la variable que va a tener la cheeseBorger");
        View vistaAdevolver;

        Log.d("algoMuyOriginal", "le asigno la cheeseBorger");
        vistaAdevolver=cheeseBorger.inflate(R.layout.activity_buscar_categoria, grupoDeLaVista, false);

        Log.d("algoMuyOriginal", "referencio los elementos");
        super.onCreate(riachuelo);
        datosLista = new ArrayList<>();
        miListaCategorias = vistaAdevolver.findViewById(R.id.ListaVista);

        Log.d("algoMuyOriginal", "comienzan los traumas");
        miAdaptador= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, datosLista);
        FragmentBuscarCategoria.tareaAsincronica miTrauma = new FragmentBuscarCategoria.tareaAsincronica();
        miTrauma.execute();

        Log.d("algoMuyOriginal", "le asigno el onclick");
        miListaCategorias.setOnClickListener(this);

        Log.d("algoMuyOriginal", "devuelvo la vista gorda");
        return vistaAdevolver;
    }
    private class tareaAsincronica extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids){
            try {
                URL miRuta = new URL("http://epok.buenosaires.gob.ar/getCategorias");
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                if (miConexion.getResponseCode()==200){
                    Log.d("algoMuyOriginal","Me conecto");
                    
                }
            }
            catch(Exception error){
            Log.d("AccesoAPI","Hubo un error al conectarme " + error.getMessage());
        }
            return null;

        }
    }
    public void onClick(View vistaRecibida){
        Log.d("algoMuyOriginal", "fuera de mi patio niños apestosos");


    }
}
