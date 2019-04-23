package com.example.eltp3;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class ActividadPrincipal extends Activity {
    ImageButton[] AButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);


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
        int idButtonA = vista.getId();
        CambiarImagen((new ImageButton[] {AButton[4]}));
        switch(idButtonA){
            case R.id.Imagen1:
                CambiarImagen(new ImageButton[]{AButton[0],AButton[1],AButton[3]});
                break;
            case R.id.Imagen2:
                CambiarImagen(new ImageButton[]{AButton[0],AButton[1],AButton[2]});
                break;
            case R.id.Imagen3:
                CambiarImagen(new ImageButton[]{AButton[1],AButton[2],AButton[5]});
                break;
            case R.id.Imagen4:
                CambiarImagen(new ImageButton[]{AButton[0],AButton[3],AButton[6]});
                break;
            case R.id.Imagen5:
                CambiarImagen(new ImageButton[]{AButton[1],AButton[3],AButton[5], AButton[7]});
                break;
            case R.id.Imagen6:
                CambiarImagen(new ImageButton[]{AButton[2],AButton[5],AButton[8]});
                break;
            case R.id.Imagen7:
                CambiarImagen(new ImageButton[]{AButton[3],AButton[6],AButton[7]});
                break;
            case R.id.Imagen8:
                CambiarImagen(new ImageButton[]{AButton[6],AButton[7],AButton[8]});
                break;
            case R.id.Imagen9:
                CambiarImagen(new ImageButton[]{AButton[5],AButton[7],AButton[8]});
                break;
        }
    }

    public void CambiarImagen( ImageButton[] Cambio){
        Drawable.ConstantState stateImg1 = ContextCompat.getDrawable(this, R.drawable.descarga).getConstantState();
        Drawable.ConstantState aux;
        for (ImageButton Imagen : Cambio){
            aux = Imagen.getDrawable().getConstantState();
            if (stateImg1 == aux){
                Imagen.setImageResource(R.drawable.peron);
            }
            else{
                Imagen.setImageResource(R.drawable.descarga);
            }
        }
    }

    protected void onCreate( ImageButton[] AButton){

        AButton[0] = (ImageButton) findViewById(R.id.Imagen1);
        AButton[1] = (ImageButton) findViewById(R.id.Imagen2);
        AButton[2] = (ImageButton) findViewById(R.id.Imagen3);
        AButton[3] = (ImageButton) findViewById(R.id.Imagen4);
        AButton[4] = (ImageButton) findViewById(R.id.Imagen5);
        AButton[5] = (ImageButton) findViewById(R.id.Imagen6);
        AButton[6] = (ImageButton) findViewById(R.id.Imagen7);
        AButton[7] = (ImageButton) findViewById(R.id.Imagen8);
        AButton[8] = (ImageButton) findViewById(R.id.Imagen9);
    }

}
