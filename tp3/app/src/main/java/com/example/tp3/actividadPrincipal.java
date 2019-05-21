package com.example.tp3;

import android.app.Activity;
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
ArrayList<String> datosLista;
ListView miListaCategorias;
ArrayAdapter<String> miAdaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);

        datosLista=new ArrayList<>();
        miListaCategorias = findViewById(R.id.ListaVista);
        miListaCategorias.setOnItemClickListener(Escuchador);
        Log.d("AccesoAPI", "Comienzo el proceso");

        miAdaptador = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, datosLista);

        tareaAsincronica miTarea = new tareaAsincronica();
        miTarea.execute();
    }
    private class tareaAsincronica extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids){
            try{
                URL miRuta = new URL("http://epok.buenosaires.gob.ar/getCategorias");
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                Log.d("AccesoAPI","Me conecto");
                if(miConexion.getResponseCode()==200){
                    Log.d("AccesoAPI","Me conecto");
                    InputStream cuerpoRespuesta = miConexion.getInputStream();
                    InputStreamReader lectorRespuesta = new InputStreamReader(cuerpoRespuesta,"UTF-8");
                    procesarJSONLeido(lectorRespuesta);
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

    public void procesarJSONLeido(InputStreamReader streamLeido){
        JsonReader JSONLeido =new JsonReader(streamLeido);
        try{
            JSONLeido.beginObject();
            while(JSONLeido.hasNext()){
                String nombreElementoActual=JSONLeido.nextName();
                Log.d("LecturaJSON", "Aca hay algo");
                if(nombreElementoActual.equals("cantidad_de_categorias")){
                    int cantidadCategorias=JSONLeido.nextInt();
                    Log.d("LecturaJSON", "La cantidad de categorias es: " + cantidadCategorias);
                }else {
                    JSONLeido.beginArray();
                    Log.d("LecturaJSON", "array");
                    while(JSONLeido.hasNext()){
                        JSONLeido.beginObject();
                        Log.d("LecturaJSON", "object");
                        while (JSONLeido.hasNext()) {
                            nombreElementoActual = JSONLeido.nextName();
                            if (nombreElementoActual.equals("nombre_normalizado")) {
                                String valorElementoActual = JSONLeido.nextString();
                                Log.d("LecturaJSON", "Valor leido: " + valorElementoActual);
                                datosLista.add(valorElementoActual);
                            } else {
                                JSONLeido.skipValue();
                            }
                        }
                    JSONLeido.endObject();
                    }
                    JSONLeido.endArray();
                }
            }
            JSONLeido.endObject();

        }catch (Exception error){

        }
    }

    public void BuscarCategoria(View vista){
        ListView miListView;
        miListView=findViewById(R.id.ListaVista);
        miListView.setAdapter(miAdaptador);
        int posicionSeleccionada;
        posicionSeleccionada = miListView.getSelectedItemPosition();
        Log.d("MiLista", "Posicion seleccionada: " + posicionSeleccionada);
        String elementoPosicionSeleccionada;
        elementoPosicionSeleccionada = (String) miListView.getItemAtPosition(posicionSeleccionada);
        Log.d("MiLista", "Elemento en la posicion seleccionada: " + elementoPosicionSeleccionada);

        miListView.setVisibility(View.VISIBLE);
    }
    AdapterView.OnItemClickListener Escuchador = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int posicionSeleccionada, long id) {
            Log.d("MiLista", "Posicion seleccionada: " + posicionSeleccionada);
            Log.d("MiLista", "Elemento en la posicion seleccionada: " + miListaCategorias.getItemAtPosition(posicionSeleccionada));
        }
    };
}
