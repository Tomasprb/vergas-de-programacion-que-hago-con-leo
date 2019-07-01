package com.example.tp4;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MostrarTraumas extends Fragment {
    String idLugar;
    ArrayList<String> listaTraumas;
    ArrayAdapter<String> adapterPropiedades;
    ListView viewlistaPropiedades;
    String direccion;

    @Override
    public View onCreateView (LayoutInflater infladorDeLayouts, ViewGroup grupoVista, Bundle datos) {
        Log.d("Trauma", "Llegué al mostrardatosobjeto");
        View vistaADevolver;
        vistaADevolver = infladorDeLayouts.inflate(R.layout.layout_buscar_categoria, grupoVista, false);
        Log.d("Trauma", "Inflé lel layout");
        ActividadPrincipalActivity actividadAnfitriona;
        actividadAnfitriona = (ActividadPrincipalActivity) getActivity();
        idLugar = actividadAnfitriona.getIdObjetoABuscar();
        Log.d("Trauma", "ID objeto");
        listaTraumas = new ArrayList<>();
        adapterPropiedades = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listaTraumas);
        TareaAsincronica task = new TareaAsincronica();
        viewlistaPropiedades = vistaADevolver.findViewById(R.id.ListaVista);
        task.execute();
        Log.d("Trauma", "Ejecute la tareaasincronica");
        return vistaADevolver;
    }

    private class TareaAsincronica extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground (Void... voids){
            try {
                URL urlAPI = new URL("http://epok.buenosaires.gob.ar/getObjectContent/?id=" + idLugar);
                Log.d("Trauma", "Se conecto a la URL: " + urlAPI.toString());
                HttpURLConnection conexionAAPI = (HttpURLConnection) urlAPI.openConnection();
                if (conexionAAPI.getResponseCode() == 200) {
                    Log.d("Trauma", "Se conecto bien");
                    InputStream respuestaAPI;
                    respuestaAPI = conexionAAPI.getInputStream();
                    InputStreamReader lectorRespuesta;
                    lectorRespuesta = new InputStreamReader(respuestaAPI, "UTF-8");
                    procesarJSON(lectorRespuesta);
                }
            } catch (Exception e) {
                Log.d("Trauma", e.toString());
            }
            try {
                URL miUrl = new URL("http://servicios.usig.buenosaires.gob.ar/normalizar/?direccion=" +
                        direccion + ", caba&geocodificar=true");
                Log.d("Trauma", "rquestCoord: " + "http://servicios.usig.buenosaires.gob.ar/normalizar/?direccion=" +
                        direccion + ", caba&geocodificar=true");
                HttpURLConnection conexion = (HttpURLConnection) miUrl.openConnection();
                if(conexion.getResponseCode()==200){
                    Log.d("Trauma", "Me conecte con la 2da API");
                    InputStream cadenaRespuesta;
                    cadenaRespuesta = conexion.getInputStream();
                    InputStreamReader lectorCadena;
                    lectorCadena = new InputStreamReader(cadenaRespuesta, "UTF-8");
                    procesarCoordenadas(lectorCadena);
                }
            }
            catch (Exception e){
                Log.d("Trauma", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute (Void avoid) {
            super.onPostExecute(avoid);
            viewlistaPropiedades.setAdapter(adapterPropiedades);
        }
    }

    private void procesarCoordenadas(InputStreamReader lectorRespuesta){
        JsonReader JReader;
        JReader = new JsonReader(lectorRespuesta);
        try {
            Log.d("Trauma", "Empiezo el trauma");
            JReader.beginObject();
            while (JReader.hasNext()) {
                String nombreActual;
                nombreActual = JReader.nextName();
                Log.d("Trauma", "El nombre actual es: " + nombreActual);
                if (nombreActual.equals("direccionesNormalizadas"))
                {
                    JReader.beginArray();
                    while (JReader.hasNext())
                    {
                        Log.d("Trauma", "array");
                        JReader.beginObject();
                        while (JReader.hasNext()) {
                            Log.d("Trauma", "direcciones");
                            nombreActual = JReader.nextName();
                            Log.d("Trauma", "El nombre actual (array) es: " + nombreActual);
                            if (nombreActual.equals("coordenadas")) {
                                Log.d("Trauma", "coordenadas");
                                JReader.beginObject();
                                while (JReader.hasNext()) {
                                    nombreActual = JReader.nextName();
                                    Log.d("Trauma", "El nombre actual (coordenadas) es: " + nombreActual);
                                    if (nombreActual.equals("x")) {
                                        Log.d("Trauma", "latitud");
                                        String coordX = JReader.nextString();
                                        listaTraumas.add("Latitud: " + coordX);
                                        Log.d("Trauma", "Latitud: " + coordX);
                                    }
                                    else
                                    {
                                        if (nombreActual.equals("y")) {
                                            Log.d("Trauma", "longitud");
                                            String coordY = JReader.nextString();
                                            listaTraumas.add("Longitud: " + coordY);
                                            Log.d("Trauma", "Latitud: " + coordY);
                                        }
                                        else
                                        {
                                            JReader.skipValue();
                                        }
                                    }
                                }
                                JReader.endObject();
                            }
                            else
                            {
                                JReader.skipValue();
                            }
                        }
                    }
                    JReader.endArray();
                }
                else
                {
                    JReader.skipValue();
                }
            }
            JReader.endObject();
        }catch (Exception e)
        {
            Log.d("Trauma", "Error JSON");
        }
    }

    private void procesarJSON (InputStreamReader lectorRespuesta) {
        JsonReader lectorJSON;
        lectorJSON = new JsonReader(lectorRespuesta);
        Log.d("Trauma", "Declare el JSONReader");
        int contPosicionLista;
        contPosicionLista = 0;
        String nombrePropiedad;
        String datoActual;
        try {
            lectorJSON.beginObject();
            Log.d("Trauma", "Empece a leer la respuesta");
            while (lectorJSON.hasNext()) {
                nombrePropiedad = lectorJSON.nextName();
                Log.d("Trauma", "La propiedad actual es " + nombrePropiedad);
                if (nombrePropiedad.equals("contenido")) {
                    lectorJSON.beginArray();
                    Log.d("Trauma", "Empece a leer el array");
                    while (lectorJSON.hasNext()) {
                        lectorJSON.beginObject();
                        Log.d("Trauma", "Empece a leer un objeto");
                        while (lectorJSON.hasNext())
                        {
                            nombrePropiedad = lectorJSON.nextName();
                            Log.d("Trauma", "La propiedad actual es " + nombrePropiedad);
                            switch (nombrePropiedad) {
                                case "nombre":
                                    datoActual = lectorJSON.nextString();

                                            listaTraumas.add(datoActual);
                                            Log.d("Trauma", "Agregue un dato a la lista. El valor actual de la posicion es " + listaTraumas.get(contPosicionLista));


                                    break;
                                case "valor":
                                    datoActual = lectorJSON.nextString();
                                        listaTraumas.set(contPosicionLista, listaTraumas.get(contPosicionLista) + ": " + datoActual);
                                        Log.d("Trauma", "Agregue un dato a la lista. El valor actual de la posicion es " + listaTraumas.get(contPosicionLista));
                                        contPosicionLista++;

                                    break;
                                default:
                                    lectorJSON.skipValue();
                                    Log.d("Trauma", "Saltee un valor");
                                    break;
                            }
                        }
                        lectorJSON.endObject();
                        Log.d("Trauma", "Termine un objeto");
                    }
                    lectorJSON.endArray();
                    Log.d("Trauma", "Termine el array");
                }else {
                    if (nombrePropiedad.equals("direccionNormalizada"))
                    {
                        direccion = lectorJSON.nextString();
                    }
                    else {
                        lectorJSON.skipValue();
                        Log.d("Trauma", "Saltee un valor");
                    }
                }
            }
            lectorJSON.endObject();;
            Log.d("Trauma", "Termine de leer el JSON");

        } catch (Exception e) {
            Log.d("Trauma", "muerte al JSON");
        }
    }
}

