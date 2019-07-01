package com.example.tp4;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BuscarCategoria extends Fragment  {
    ListView lista;
    ArrayList<String> datosLista;
    ArrayList<String> nombresLista;
    ArrayAdapter<String> miAdaptador;
    BuscarCategoria.tareaAsincronica miTarea;
    EditText TextoCoordenadaX;
    EditText TextoCoordenadaY;
    SeekBar SeekBarRadio;
    TextView TextoProgresoRadio;
    boolean geo;
    ActividadPrincipalActivity algo;
    //Button mostrar;
    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup grupo, Bundle riachuelo){
        View Zurdito;
        ActividadPrincipalActivity algo = (ActividadPrincipalActivity) getActivity();
        geo = algo.getGeo();
        if(geo)
        {
            Zurdito = inflador.inflate(R.layout.layout_buscar_geo, grupo, false);
            TextoCoordenadaX = Zurdito.findViewById(R.id.coordenadaX);
            TextoCoordenadaY = Zurdito.findViewById(R.id.coordenadaY);
            SeekBarRadio = Zurdito.findViewById(R.id.Radio);
            TextoProgresoRadio = Zurdito.findViewById(R.id.ProgresoRadio);
            SeekBarRadio.setOnSeekBarChangeListener(new ListenerSeekbar());
            SeekBarRadio.setProgress(1);
        }
        else{
            Zurdito = inflador.inflate(R.layout.layout_buscar_categoria, grupo, false);
        }
        datosLista=new ArrayList<>();
        nombresLista=new ArrayList<>();
        lista=Zurdito.findViewById(R.id.ListaVista);
        lista.setOnItemClickListener(Escuchador);
        Log.d("AccesoAPI", "Comienzo el proceso");

        miAdaptador = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, datosLista);

        miTarea = new BuscarCategoria.tareaAsincronica();
        miTarea.execute();


        lista.setVisibility(View.VISIBLE);
        return Zurdito;
    }
    private class ListenerSeekbar implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            TextoProgresoRadio.setText("Radio: " + progress + "m");
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar){

        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar){

        }
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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String categoriaSeleccionada;
                categoriaSeleccionada = datosLista.get(position);
                String nombreCategoriaSeleccionada;
                nombreCategoriaSeleccionada = nombresLista.get(position);
                if(geo)
                {
                    if(TextoCoordenadaY.getText().length()==0||TextoCoordenadaX.getText().length()==0)
                    {

                        AlertDialog.Builder builder;
                        DialogInterface.OnClickListener listenerAdvertencia;
                        listenerAdvertencia = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        };
                        builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Ambas coordenadas deben estar completas");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Aceptar", listenerAdvertencia);
                        builder.setTitle("Advertencia");
                        AlertDialog advertenciaCoordenadaVacia = builder.create();
                        advertenciaCoordenadaVacia.show();
                    }
                    else {
                        if (SeekBarRadio.getProgress() == 0) {
                            SeekBarRadio.setProgress(1);
                        }
                        float coordenadaX = new Float(TextoCoordenadaX.getText().toString());
                        float coordenadaY = new Float(TextoCoordenadaY.getText().toString());
                        algo.guardarLocalizacion(coordenadaY, coordenadaX, SeekBarRadio.getProgress());
                        algo.irAMostrarListaLugares(categoriaSeleccionada, nombreCategoriaSeleccionada);
                    }
                }
                else
                {
                    algo.irAMostrarListaLugares(categoriaSeleccionada, nombreCategoriaSeleccionada);
                }
            }

    };

}
