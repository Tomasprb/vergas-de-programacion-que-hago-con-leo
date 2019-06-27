package com.example.tp4;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BuscarCategoria extends Fragment  {
    ListView lista;
    ArrayList<String> datosLista;
    ArrayAdapter<String> miAdaptador;
    BuscarCategoria.tareaAsincronica miTarea;
    //Button mostrar;
    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup grupo, Bundle riachuelo){
        View Zurdito=inflador.inflate(R.layout.layout_buscar_categoria, grupo, false);

       // mostrar=Zurdito.findViewById(R.id.btnBuscarCat);
        //mostrar.setOnClickListener(this);
        datosLista=new ArrayList<>();
        lista=Zurdito.findViewById(R.id.ListaVista);
        lista.setOnItemClickListener(Escuchador);
        Log.d("AccesoAPI", "Comienzo el proceso");

        miAdaptador = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, datosLista);

        miTarea = new BuscarCategoria.tareaAsincronica();
        miTarea.execute();


        lista.setVisibility(View.VISIBLE);
        return Zurdito;
    }

    private class tareaAsincronica extends AsyncTask<Void,Void,Void> {
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

        @Override
        protected void onPostExecute (Void aVoid) {
            super.onPostExecute(aVoid);
            lista.setAdapter(miAdaptador);
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
            Log.d("ProcesarJSON", "Termino de leer el objeto");

        }catch (Exception error){
            Log.d("LecturaJSON", "Fallo algo. El error fue " + error);
        }
    }
    AdapterView.OnItemClickListener Escuchador = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int posicionSeleccionada, long id) {
            /*ActividadPrincipal = (ActividadPrincipalActivity) getActivity();
            activity.setCategory(categories.get(selected));
            Log.d("","Setted category");*/
        }
    };
    /*public void onClick(View vista){
        lista.setAdapter(miAdaptador);
        int posicionSeleccionada;
        posicionSeleccionada = lista.getSelectedItemPosition();
        Log.d("MiLista", "Posicion seleccionada: " + posicionSeleccionada);
        String elementoPosicionSeleccionada;
        elementoPosicionSeleccionada = (String) lista.getItemAtPosition(posicionSeleccionada);
        Log.d("MiLista", "Elemento en la posicion seleccionada: " + elementoPosicionSeleccionada);

        lista.setVisibility(View.VISIBLE);
    }*/
}
