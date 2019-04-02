package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Punto2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punto2);

    }

public void Checkeo(View vista2){
    EditText MiIngreso;
    TextView MiView;
    CheckBox MiCheck;
    Toast MiTostada;
    String Manteca;
    MiCheck=findViewById(R.id.Limite);
    MiIngreso=findViewById(R.id.Contador);
    String palabra=MiIngreso.toString();
    MiView=findViewById(R.id.Output);
    MiView.setText("");
    if(MiCheck.isChecked()==true){
        if(MiIngreso.getText().length()>=10){
            int count = 0;
            for(int i=0; i < MiIngreso.length(); i++) {
                if(palabra.charAt(i) == 'a' || palabra.charAt(i) == 'A'){
                    count++;
                }
            }
            MiView.setText("La cantidad de A que hay es de: " + count);
        }
        else{
            Manteca="Lo lamento manito, menos de 10 caracteres permitidos";
            MiTostada=Toast.makeText(this,Manteca,Toast.LENGTH_SHORT);
            MiTostada.show();
        }
    }
    else{
        int count = 0;
        for(int i=0; i < MiIngreso.length(); i++) {
            if(palabra.charAt(i) == 'a' || palabra.charAt(i) == 'A'){
                count++;
            }
        }
        MiView.setText("La cantidad de A que hay es de: " + count);
    }
}
    public void Activity3(View vista){
        Intent j = new Intent(Punto2.this, Punto3.class);
        startActivity(j);
    }
}

