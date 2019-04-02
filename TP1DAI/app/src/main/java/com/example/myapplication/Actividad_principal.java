package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Actividad_principal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
    }
    public void BotonMostrar(View vista){
        Toast Alerta;
        String MensajeAlerta;

        TextView MiCartel;
        TextView MiCartel2;
        TextView MiCartel3;
        EditText Ingreso1;
        Ingreso1=findViewById(R.id.txtIngreso1);
        EditText Ingreso2;
        Ingreso2=findViewById(R.id.txtIngreso2);
        int Exedentes;
        int Largo1=Ingreso1.getText().length();
        int Largo2=Ingreso2.getText().length();
        String secuencia1=Ingreso1.getText().toString();
        String secuencia2=Ingreso2.getText().toString();

        MiCartel=findViewById(R.id.txtView1);
        MiCartel2=findViewById(R.id.txtView2);
        MiCartel3=findViewById(R.id.txtView3);

        if(Largo1==0||Largo2==0)
        {

            MensajeAlerta="Ingresa las palabras faltantes";
            Alerta=Toast.makeText(this,MensajeAlerta,Toast.LENGTH_SHORT);
            Alerta.show();
        }
        else{
        if(Largo1>Largo2){
            Exedentes=Largo1-Largo2;
        }
        else{
            Exedentes=Largo2-Largo1;
        }
        String Sec1=secuencia1.substring(0,3);
        String Sec2=secuencia2.substring(0,3);
        MiCartel.setText("la primera palabra es de "+Largo1+" caracteres y la segunda palabra es de "+Largo2+" caracteres");
        MiCartel2.setText("los caracteres exedentes son "+Exedentes);
        MiCartel3.setText(Sec1+Sec2);
    }
    }

    public void Activity2(View vista){
        Intent k = new Intent(Actividad_principal.this, Punto2.class);
        startActivity(k);
    }

}
