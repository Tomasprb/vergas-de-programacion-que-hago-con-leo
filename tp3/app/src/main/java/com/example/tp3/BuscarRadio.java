package com.example.tp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class BuscarRadio extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_radio);
        SeekBar inputRad;
        inputRad = findViewById(R.id.SelectRadio);


        inputRad.setProgress(3);


        inputRad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView Progreso;

                Progreso = findViewById(R.id.Progreso);

                Progreso.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void BuscarRad(View vista)
    {
        SeekBar inputRad;
        inputRad = findViewById(R.id.SelectRadio);
        EditText Ingreso;
        EditText Ingreso2;
        EditText Ingreso3;


        Ingreso = findViewById(R.id.Lat);
        Ingreso2 = findViewById(R.id.Long);
        Ingreso3 = findViewById(R.id.Cat);


        float coordX, coordY;
        int rad;

        Ingreso = findViewById(R.id.Lat);
        Ingreso2 = findViewById(R.id.Long);

        coordX = Float.parseFloat(Ingreso.getText().toString());
        coordY = Float.parseFloat(Ingreso2.getText().toString());
        rad = inputRad.getProgress();
        Intent irAMostrarRadio;
        irAMostrarRadio = new Intent(BuscarRadio.this, MostrarRadio.class);
        String categoriaSeleccionada = Ingreso3.getText().toString();
        Bundle PaqueteRancio;
        PaqueteRancio = new Bundle();
        PaqueteRancio.putString("categoriaSeleccionada", categoriaSeleccionada);
        PaqueteRancio.putFloat("X",coordX);
        PaqueteRancio.putFloat("Y",coordY);
        PaqueteRancio.putInt("Radio",rad);
        irAMostrarRadio.putExtras(PaqueteRancio);
        startActivity(irAMostrarRadio);
    }
}
