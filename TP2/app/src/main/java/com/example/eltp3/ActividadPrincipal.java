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
public void BotonNombre(View vista)
{
    TextView Mitexto;
    EditText MiEdit;
    MiEdit=findViewById(R.id.IngresoNombre);
    Mitexto=findViewById(R.id.ViewNombre);
    Mitexto.setText("bienvenido "+MiEdit);

}
    public void btnImagen1(View vista)
    {}
    public void btnImagen2(View vista)
    {}
    public void btnImagen3(View vista)
    {}
    public void btnImagen4(View vista)
    {}
    public void btnImagen5(View vista)
    {}
    public void btnImagen6(View vista)
    {}
    public void btnImagen7(View vista)
    {}
    public void btnImagen8(View vista)
    {}
    public void btnImagen9(View vista)
    {}
}
