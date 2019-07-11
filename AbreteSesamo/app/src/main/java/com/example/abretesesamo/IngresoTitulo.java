package com.example.tp5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class IngresoTitulo extends Fragment implements View.OnClickListener
{
EditText Titulo;
Button Boton;
    public View OnCreateView(LayoutInflater infladorLayout, ViewGroup grupoVista, Bundle Riachuelo)
    {
    View VistaDevolver;
        VistaDevolver=infladorLayout.inflate(R.layout.ingreso_titulo, grupoVista,false);
        Titulo=VistaDevolver.findViewById(R.id.Textovich);
        Boton=VistaDevolver.findViewById(R.id.Botonovich);
        Boton.setOnClickListener(this);
        return VistaDevolver;
    }
    public void onClick(View VistaRecibida)
    {
        String Titulovich;
        Titulovich=Titulo.getText().toString();
        //esto te toca leo
    }
}
