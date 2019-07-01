package com.example.tp4;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MostrarListaTraumas extends Fragment {
    ArrayList<String> listaLugares;
    ArrayList<String> listaIDs;
    ListView viewListaLugares;
    ArrayAdapter<String> adapterListaLugares;

    View vistaADevolver;
    String categoriaSeleccionada;
    String nombreCategoriaSeleccionada;
    String request;
    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup grupo, Bundle datos)
    {

        vistaADevolver = inflador.inflate(R.layout.layout_buscar_categoria, grupo, false);

        ActividadPrincipalActivity actPrincipal;
        actPrincipal = (ActividadPrincipalActivity) getActivity();
        categoriaSeleccionada = actPrincipal.getCategoriaSeleccionada();
        nombreCategoriaSeleccionada = actPrincipal.getNombreCategoriaSeleccionada();
        request = actPrincipal.getRequestListaLugares();

        viewListaLugares = vistaADevolver.findViewById(R.id.ListaVista);
        listaLugares = new ArrayList<>();
        listaIDs = new ArrayList<>();
        adapterListaLugares = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listaLugares);
        viewListaLugares.setOnItemClickListener(listenerListaLugares);
        TareaAsinconica miTarea = new TareaAsinconica();
        miTarea.execute();


        return vistaADevolver;
    }

    AdapterView.OnItemClickListener listenerListaLugares = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String idLugarSeleccionado;
            idLugarSeleccionado = listaIDs.get(position);
            ActividadPrincipalActivity actividadAnfitriona;
            actividadAnfitriona = (ActividadPrincipalActivity) getActivity();
            actividadAnfitriona.MostrarDatosObjeto(idLugarSeleccionado);
        }
    };

    private class TareaAsinconica extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground (Void... voids)
        {
            try {

                URL rutaConexion = new URL(request);

                HttpURLConnection conexionATrauma;
                conexionATrauma = (HttpURLConnection) rutaConexion.openConnection();
                if (conexionATrauma.getResponseCode()==200)
                {
                    Log.d("Trauma", "Me llegó la info de la Trauma");
                    InputStream streamRespuesta;
                    streamRespuesta = conexionATrauma.getInputStream();
                    Log.d("Trauma", "Obtuve el stream de respuesta");
                    InputStreamReader lectorStreamRespuesta;
                    lectorStreamRespuesta = new InputStreamReader(streamRespuesta, "UTF-8");
                    Log.d("Trauma", "Leí el stream de respuesta");
                    ProcesarJson(lectorStreamRespuesta);
                }
            } catch (Exception error) {
                Log.d("Trauma", "Falle en la conexion");
            }
            return null;
        }
        @Override
        protected void onPostExecute (Void aVoid)
        {
            super.onPostExecute(aVoid);

            /*if(listaLugares.size()==0)
            {
                TextView TextoListaVacia = vistaADevolver.findViewById(R.id.ListaVacia);
                TextoListaVacia.setVisibility(View.VISIBLE);
                viewListaLugares.setVisibility(View.GONE);
            }
            else {
                viewListaLugares.setAdapter(adapterListaLugares);
                Log.d("Trauma", "¿Funciono?");
            }*/

            //viewListaCategorias.setAdapter(adaptadorListaCategorias);
            viewListaLugares.setAdapter(adapterListaLugares);
            Log.d("Trauma", "¿Funciono?");
        }
    }

    private void ProcesarJson(InputStreamReader respuestaLeida)
    {
        String nombreActual;
        JsonReader JSONLeido;
        JSONLeido = new JsonReader(respuestaLeida);
        try {
            Log.d("Trauma", "Empiezo a leer la respuesta JSON");
            JSONLeido.beginObject();
            while (JSONLeido.hasNext()){
                nombreActual = JSONLeido.nextName();
                Log.d("Trauma", "El elemento actual es: " + nombreActual);
                if (nombreActual.equals("instancias")) {
                    JSONLeido.beginArray();
                    Log.d("Trauma", "Leo las instancias");
                    while (JSONLeido.hasNext()) {
                        Log.d("Trauma", "Comienzo un item de la lista");
                        JSONLeido.beginObject();
                        Log.d("Trauma", "Leo un objeto del array");
                        while (JSONLeido.hasNext()) {
                            nombreActual = JSONLeido.nextName();
                            Log.d("Trauma", "La propiedad de la instancia actual es: " + nombreActual);
                            if (nombreActual.equals("nombre")) {
                                String nombreObj = JSONLeido.nextString();
                                Log.d("Trauma", "Agrego " + nombreObj);
                                listaLugares.add(nombreObj);
                                while (JSONLeido.hasNext()) {
                                    nombreActual = JSONLeido.nextName();
                                    if (nombreActual.equals("id")) {
                                        String idActual;
                                        idActual = JSONLeido.nextString();
                                        listaIDs.add(idActual);
                                    } else {
                                        JSONLeido.skipValue();
                                    }
                                }
                            }
                            else
                            {
                                JSONLeido.skipValue();
                            }
                        }
                        JSONLeido.endObject();
                        Log.d("Trauma", "Termine de leer un objeto del JSON");
                    }
                    JSONLeido.endArray();
                }
                else
                {
                    JSONLeido.skipValue();
                }
            }
            JSONLeido.endObject();
        }catch (Exception error)
        {
            Log.d("Trauma", "ErrorJSON");
            Log.d("Trauma", error.toString());
        }
    }
}
