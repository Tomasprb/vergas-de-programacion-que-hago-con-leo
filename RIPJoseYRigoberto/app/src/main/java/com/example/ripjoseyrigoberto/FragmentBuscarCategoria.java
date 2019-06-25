package com.example.ripjoseyrigoberto;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FragmentBuscarCategoria extends Fragment {
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
        miListaCategorias.setOnItemClickListener(pesadilla);

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
                    Log.d("algoMuyOriginal","Terapeuta: el JSON no existe, no puede hacerte daño. JSON: iniciando conexion");
                    InputStream cuerpoRespuesta = miConexion.getInputStream();
                    InputStreamReader lectorRespuesta = new InputStreamReader(cuerpoRespuesta,"UTF-8");
                    procesarJSONLeido(lectorRespuesta);
                }else {
                    Log.d("algoMuyOriginal"," error en la conexion");
                }
            }
            catch(Exception error){
            Log.d("AccesoAPI","Hubo un error al conectarme " + error.getMessage());
            }
            return null;

        }
    }

    public void procesarJSONLeido(InputStreamReader streamLeido){
        JsonReader JSONLeido = new JsonReader(streamLeido);
        try {
            JSONLeido.beginObject();
            while (JSONLeido.hasNext()){
                String nombreElementoActual=JSONLeido.nextName();
                Log.d("algoMuyOriginal","un trauma salvaje ha aparecido");
                if (nombreElementoActual.equals("cantidad_de_categorias")){
                    int cantidadCategorias=JSONLeido.nextInt();
                    Log.d("algoMuyOriginal","la cantidad de traumas salvajes es: " + cantidadCategorias);
                }else {
                    JSONLeido.beginArray();
                    Log.d("algoMuyOriginal","array");
                    while (JSONLeido.hasNext()){
                        JSONLeido.beginObject();
                        Log.d("algoMuyOriginal","object");
                        while (JSONLeido.hasNext()){
                            nombreElementoActual = JSONLeido.nextName();
                            if (nombreElementoActual.equals("nombre_normalizado")){
                                String valorElementoActual = JSONLeido.nextString();
                                Log.d("algoMuyOriginal","Terapeuta: ¿que viste?¿que paso? Yo: " + valorElementoActual);
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
        }catch(Exception error){
            Log.d("AccesoAPI","Hubo un error al conectarme " + error.getMessage());
        }
        Log.d("algoMuyOriginal", "fuera de mi patio niños apestosos");
    }

    public void MostrarLista(View vista){
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
    AdapterView.OnItemClickListener pesadilla = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int posicionSeleccionada, long id) {

        }
    };
}
