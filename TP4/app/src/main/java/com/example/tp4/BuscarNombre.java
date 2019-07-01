package com.example.tp4;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class BuscarNombre extends Fragment implements View.OnClickListener {
        EditText campoTextoNombre;
        Button btnBuscarNombre;
        String idObjetoElegido;
        String request;
        ArrayList<String> listaNombres;
        ArrayList<String> listaIDs;
        ArrayAdapter<String> adaptador;
        ListView listViewObjetos;
        TareaAsincronica miTarea;
        AlertDialog advertenciaTextoVacio;
        TextView nabo;
        int traerDesde;
        int cantItems;
        String textoIngresado;

@Override
public View onCreateView (LayoutInflater infladorDeLayouts, ViewGroup grupoVista, Bundle datos) {

        listaNombres = new ArrayList<>();
        listaIDs = new ArrayList<>();
        CrearAdvertenciaCompletarTexto();

        adaptador = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listaNombres);
        idObjetoElegido = null;
        request = "http://epok.buenosaires.gob.ar/buscar/?texto=";
        View vistaADevolver;
        vistaADevolver = infladorDeLayouts.inflate(R.layout.layout_buscar_nombre, grupoVista, false);
        campoTextoNombre = vistaADevolver.findViewById(R.id.TextoBuscar);
        listViewObjetos = vistaADevolver.findViewById(R.id.ListaObjetos);
        btnBuscarNombre = vistaADevolver.findViewById(R.id.btnBuscarNombre);
        nabo = vistaADevolver.findViewById(R.id.nabo);
        listViewObjetos.setOnItemClickListener(listenerObjetosLista);
        btnBuscarNombre.setOnClickListener(this);
        return vistaADevolver;
        }
public void onClick (View vistaRecibida) {
        nabo.setVisibility(View.GONE);
        listViewObjetos.setVisibility(View.VISIBLE);
        listaNombres.clear();
        request  = "http://epok.buenosaires.gob.ar/buscar/?texto=";
        textoIngresado = campoTextoNombre.getText().toString();
        if(textoIngresado.equals(""))
        {
        Log.d("validar", "Texto vacio");
        advertenciaTextoVacio.show();
        }
        else {
        request += textoIngresado;
        Log.d("API", "Mando a ejecutar la tarea");
        miTarea = new TareaAsincronica();
        miTarea.execute();
        }
        }

private class TareaAsincronica extends AsyncTask<Void, Void, Void>{
    @Override
    protected Void doInBackground(Void... voids){
        try {
            Log.d("API", "La request es: " + request);
            URL ruta = new URL(request);
            HttpURLConnection conexion;
            conexion = (HttpURLConnection) ruta.openConnection();
            Log.d("API", "Me conecto");
            if(conexion.getResponseCode()==200)
            {
                Log.d("API", "Conexion OK");
                InputStream respuesta;
                respuesta = conexion.getInputStream();
                InputStreamReader lectorRespuesta;
                lectorRespuesta = new InputStreamReader(respuesta, "UTF-8");
                ProcesarJson(lectorRespuesta);
                Log.d("API", "Termine de leer");
            }
            else {
                Log.d("API", "Conexion no OK");
            }
            conexion.disconnect();
        } catch (Exception error) {
            Log.d("API", "Error: " + error.getMessage());
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void avoid){
        super.onPostExecute(avoid);
        if(listaNombres.size()==0)
        {
            nabo.setVisibility(View.VISIBLE);
            listViewObjetos.setVisibility(View.GONE);
        }
        else {
            Log.d("API", "Muestro la lista");
            adaptador.notifyDataSetChanged();
            listViewObjetos.setAdapter(adaptador);
            Log.d("API", "Agregue el adapter");
        }
    }
}

    private void ProcesarJson(InputStreamReader respuestaLeida){
        JsonReader JotaSONLeido = new JsonReader(respuestaLeida);
        try {
            Log.d("API", "Empiezo a leer la respuesta JSON");
            JotaSONLeido.beginObject();
            while (JotaSONLeido.hasNext()){
                String nombreActual = JotaSONLeido.nextName();
                Log.d("API", "El elemento actual es: " + nombreActual);
                if (nombreActual.equals("instancias")) {
                    JotaSONLeido.beginArray();
                    Log.d("API", "Leo las instancias");
                    while (JotaSONLeido.hasNext()) {
                        JotaSONLeido.beginObject();
                        Log.d("API", "Leo un objeto del array");
                        while (JotaSONLeido.hasNext()) {
                            nombreActual = JotaSONLeido.nextName();
                            Log.d("API", "La propiedad de la instancia actual es: " + nombreActual);
                            if (nombreActual.equals("nombre")) {
                                String nombreObj = JotaSONLeido.nextString();
                                if(nombreObj.toLowerCase().contains(campoTextoNombre.getText().toString().toLowerCase()))
                                {
                                    Log.d("API", "Agrego " + nombreObj);
                                    listaNombres.add(nombreObj);
                                    while (JotaSONLeido.hasNext()) {
                                        nombreActual = JotaSONLeido.nextName();
                                        if (nombreActual.equals("id")) {
                                            String idActual;
                                            idActual = JotaSONLeido.nextString();
                                            listaIDs.add(idActual);
                                        } else {
                                            JotaSONLeido.skipValue();
                                        }
                                    }
                                }
                            }
                            else
                            {
                                JotaSONLeido.skipValue();
                            }
                        }
                        JotaSONLeido.endObject();
                    }
                    JotaSONLeido.endArray();
                }
                else
                {
                    JotaSONLeido.skipValue();
                }
            }
            JotaSONLeido.endObject();
        }catch (Exception error)
        {
            Log.d("API", "ErrorJSON");
        }
    }

    private void CrearAdvertenciaCompletarTexto()
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
        builder.setMessage("Debe ingresar un nombre a buscar");
        builder.setCancelable(false);
        builder.setPositiveButton("Aceptar", listenerAdvertencia);
        builder.setTitle("Advertencia");
        advertenciaTextoVacio = builder.create();

    }



    AdapterView.OnItemClickListener listenerObjetosLista = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ActividadPrincipalActivity actividadAnfitriona;
            actividadAnfitriona = (ActividadPrincipalActivity) getActivity();
            idObjetoElegido = listaIDs.get(position);
            actividadAnfitriona.MostrarDatosObjeto(idObjetoElegido);
        }
    };

}