package com.example.tp3;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MostrarObjetos extends Activity {
    ArrayList<String> datosLista;
    ListView miListaObjetos;
    ArrayAdapter<String> miAdaptador;
    String categoriaRecibida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_objetos);
        int posicionSeleccionada;
        miListaObjetos = findViewById(R.id.Objetos);
        Bundle datosRecibidos;
        datosRecibidos = this.getIntent().getExtras();
        categoriaRecibida = datosRecibidos.getString("categoriaSeleccionada");
        datosLista=new ArrayList<>();
        Log.d("AccesoAPI2", "Comienzo el proceso");

        miAdaptador = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, datosLista);

        tareaAsincronica miTarea = new tareaAsincronica();
        miTarea.execute();
    }
    private class tareaAsincronica extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids){
            try{
                URL miRuta = new URL("http://epok.buenosaires.gob.ar/buscar/?texto=" + categoriaRecibida + "&categoria=" + categoriaRecibida);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                Log.d("AccesoAPI2","Me conecto");
                if(miConexion.getResponseCode()==200){
                    Log.d("AccesoAPI","Me conecto");
                    InputStream cuerpoRespuesta = miConexion.getInputStream();
                    InputStreamReader lectorRespuesta = new InputStreamReader(cuerpoRespuesta,"UTF-8");
                    procesarJSONLeido(lectorRespuesta);
                } else{
                    Log.d("AccesoAPI2","Error en la conexion");
                }
                miConexion.disconnect();
            }catch(Exception error){
                Log.d("AccesoAPI2","Hubo un error al conectarme " + error.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            miListaObjetos.setAdapter(miAdaptador);
        }

    }

    public void procesarJSONLeido(InputStreamReader streamLeido){
        JsonReader JSONLeido =new JsonReader(streamLeido);
        try{
            JSONLeido.beginObject();
            while(JSONLeido.hasNext()){
                String nombreElementoActual=JSONLeido.nextName();
                Log.d("LecturaJSON2", "Aca hay algo");
                switch (nombreElementoActual) {
                    case "totalFull":
                        int cantidadObjetos=JSONLeido.nextInt();
                        Log.d("LecturaJSON2", "La cantidad de objetos es: " + cantidadObjetos);
                        break;
                    case "instancias":
                        JSONLeido.beginArray();
                        Log.d("LecturaJSON2", "array");
                        while(JSONLeido.hasNext()){
                            JSONLeido.beginObject();
                            Log.d("LecturaJSON2", "object");
                            while (JSONLeido.hasNext()) {
                                nombreElementoActual = JSONLeido.nextName();
                                if (nombreElementoActual.equals("headline")) {
                                    String valorElementoActual = JSONLeido.nextString();
                                    Log.d("LecturaJSON2", "Valor leido: " + valorElementoActual);
                                    datosLista.add(valorElementoActual);
                                } else {
                                    JSONLeido.skipValue();
                                }
                            }
                            JSONLeido.endObject();
                        }
                        JSONLeido.endArray();
                        break;
                    default:
                        JSONLeido.skipValue();
                        break;
                }

            }
            JSONLeido.endObject();

        }catch (Exception error){

        }
    }

}
