package com.example.eltp3;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class ActividadPrincipal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        ImageButton[] AButton;
        AButton = new ImageButton[9];

        AButton[0] = (ImageButton) findViewById(R.id.Imagen1);
        AButton[1] = (ImageButton) findViewById(R.id.Imagen2);
        AButton[2] = (ImageButton) findViewById(R.id.Imagen3);
        AButton[3] = (ImageButton) findViewById(R.id.Imagen4);
        AButton[4] = (ImageButton) findViewById(R.id.Imagen5);
        AButton[5] = (ImageButton) findViewById(R.id.Imagen6);
        AButton[6] = (ImageButton) findViewById(R.id.Imagen7);
        AButton[7] = (ImageButton) findViewById(R.id.Imagen8);
        AButton[8] = (ImageButton) findViewById(R.id.Imagen9);

        for (int i = 0; i <= 8; i++){

            Random a;
            a = new Random();
            Integer NR;
            NR = a.nextInt(2);

            if (NR == 1){
                AButton[i].setImageResource(R.drawable.descarga);
            }else{
                AButton[i].setImageResource(R.drawable.peron);
            }
        }

    }
public void BotonNombre(View vista)
{
    TextView Mitexto;
    EditText MiEdit;
    MiEdit=findViewById(R.id.IngresoNombre);
    Mitexto=findViewById(R.id.ViewNombre);
    Mitexto.setText("bienvenido "+MiEdit);

}
    public void ApretarBoton(View vista){

    }

    public void CambiarImagen(View vista){

    }



}
