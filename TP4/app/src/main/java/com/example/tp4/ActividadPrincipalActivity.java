package com.example.tp4;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;

public class ActividadPrincipalActivity extends Activity {
    FragmentManager adminFragments;
    FragmentTransaction transacFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        adminFragments = getFragmentManager();
    }

    public void bCategoria(View vista){
        BuscarCategoria frgCat;
        frgCat = new BuscarCategoria();

        transacFragments = adminFragments.beginTransaction();
        transacFragments.replace(R.id.Fragment, frgCat);
        transacFragments.commit();

    }
}
