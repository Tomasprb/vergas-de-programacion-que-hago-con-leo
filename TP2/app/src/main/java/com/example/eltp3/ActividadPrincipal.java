package com.example.eltp3;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class ActividadPrincipal extends Activity {
    ImageButton[] AButton;
    Random num1=new Random();
    Integer Num1A9=num1.nextInt(10);
    Random num2=new Random();
    Integer Num1A92=num2.nextInt(10);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        AButton = new ImageButton[9];
        inicializarVector();
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
            AButton[i].setEnabled(false);
        }

    }
public void BotonNombre(View vista)
{
    TextView txt=(TextView)findViewById(R.id.txtCaptcha);
    TextView Mitexto;
    EditText MiEdit;
    Mitexto=(TextView) findViewById(R.id.ViewNombre);
    MiEdit=(EditText) findViewById(R.id.IngresoNombre);
    Mitexto.setText("bienvenido "+MiEdit.getText());
    txt.setText(Num1A9 + " + " + Num1A92 + " = ");
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
        Toast MostrarVictoria;
        String mensaje;
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
        if (Victoria(AButton) == true){
            mensaje = "Ganaste!!!!!!!!!!!!";
            MostrarVictoria = Toast.makeText(this,mensaje,Toast.LENGTH_LONG);
            MostrarVictoria.show();
            for (int i = 0; i <= 8; i++) {
            AButton[i].setEnabled(false); 
            }
        }
    }
    public boolean Victoria(ImageButton[] algo){
        boolean Victoria = false;
        int ContP = 0;
        int ContV = 0;
        Drawable.ConstantState stateImg1 = ContextCompat.getDrawable(this, R.drawable.descarga).getConstantState();
        Drawable.ConstantState aux;
        for (ImageButton Imagen : algo){
            aux = Imagen.getDrawable().getConstantState();
            if (stateImg1 == aux){
                ContV++;
            }
            else{
                ContP++;
            }
            if(ContV == 9 || ContP == 9){
                Victoria = true;
            }
        }
        return Victoria;
    }
    public void inicializarVector(){

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
    public void Captcha(View vista)
    {
        EditText edit=(EditText)findViewById(R.id.editCaptcha);
        EditText edit2=(EditText)findViewById(R.id.IngresoNombre);
        int cap1=0;
        if(edit.length()!=0) {
             cap1 = Integer.parseInt(edit.getText().toString());
        }

        if(cap1==Num1A9+Num1A92&&edit2.getText().toString()!=""){
            for (int i = 0; i <= 8; i++) {
                AButton[i].setEnabled(true);
            }
        }
    }

}
