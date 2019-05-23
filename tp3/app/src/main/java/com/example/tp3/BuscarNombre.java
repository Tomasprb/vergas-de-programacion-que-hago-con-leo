package com.example.tp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BuscarNombre extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_nombre);
    }
    public void MostrarOXN(View vista){
        EditText MiEdit;
        MiEdit=(EditText) findViewById(R.id.nombre);
        Intent irAMostrarObjetos;
        irAMostrarObjetos = new Intent(BuscarNombre.this, MostrarObjetos.class);
        String categoriaSeleccionada = MiEdit.getText().toString();
        Bundle catSeleccionada;
        catSeleccionada = new Bundle();
        catSeleccionada.putString("categoriaSeleccionada", categoriaSeleccionada);
        irAMostrarObjetos.putExtras(catSeleccionada);
        startActivity(irAMostrarObjetos);
    }
}
