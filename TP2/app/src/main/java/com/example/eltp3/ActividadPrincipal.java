package com.example.eltp3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActividadPrincipal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
    }
void BotonNombre(View vista)
{
    TextView Mitexto;
    EditText MiEdit;
    MiEdit=findViewById(R.id.IngresoNombre);
    Mitexto=findViewById(R.id.ViewNombre);
    Mitexto.setText("bienvenido "+MiEdit);

}





}
