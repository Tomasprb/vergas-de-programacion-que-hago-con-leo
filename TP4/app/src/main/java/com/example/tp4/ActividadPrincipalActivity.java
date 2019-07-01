package com.example.tp4;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/*
WARNING: the code that follows will make you cry
     a safety dragon is provided below for your own security
                                                                 `.
                                                             .++:.
                                               .         `/sodN.
                                      `+hs:`   y-     `/yy:` d+`
                    `          ``    /Nd-     oN.   .sd+`    s+:
                  ++:      `+y++N: -h++-    :d+o  :hh:       `sNo://:-.`
                `+++Nhhhhd+NNs  +NN++++s.-od+N+ -hd:       `:oyy+o`
                s+++++++++++++++++++++++++++o.`sN+      .+hho- -+:
                `N+++s/::://+sh+++++N+++y+-  -+h.    `/hd+.    `Ny
                 -yo :hdhyssyhy:+++++d+No   +Ns    .s+s-        .yh:.-///:-.`
                  `+yo+` `.:+osd+++N/h+++- ++o   -y+o`        .:osy++:.`
            ``.-+ss/` :yy++++hsoosys+++++-++s  .yNo`     `:ohhs/.  hs
            `---`     /++do-     s++++++h-Nd``oNh.    -ohho:`      ++
                      `h-     `oNN+dN++N-d+:-d+o   :sdy/`          hy
                       ``    `y+N++++++::+++N+/`:s+h/`      ```....:+o.`
                          `s+NN+dN+++++ o+NhdNdN+h--/+syhhhyyssso++//sd/:-.`
                         `h++++++++++d  sh.+:/++++yo+:.`            +s`
                       `o++ddd++++++++hooyNN.+++y-                `s/         `.
 .:/ss ``:            .++++++++++++++++++Ny-:N+NN+Ndyo+:-.`      .h.          s:
    `h+h-d-           oyhhhN++++++++++/...:y+N: ``.-:/++ossssoo++sy::-.     .h+`
   -o++++++         `sN++++++++++++++++o::/+sysooooo++///+++++/:--.`     `:yN+-  ``
  /+:s++++y         /+++++++++++++++++++++N++dddddd+++N++++++++++++++hoyd+++h+oyy:
     `N+++/       -+:ysyyd++++++++++++++++++++++++++++++++++++++++++++++++++++h/``-`
      ./+h+:` `-od+++h++++++++++++++++++++++++++++++++++++N+N++++++++++++++++++N+s-
          h+NhN+++++N++++++dhd+++++++++++++++++++++++++dosyhyysoh+++++++++++++++N/
         s+++ `:sN++++:sysy+++++++++++++++++++++++++++y/++++++++s++++s`/h+++++++++s
         :++.    `/Nh    /+N++++++++++++++++++++N+ddd+-++++++++++.N++    -h++++++++.
         .d/      `d-     .s++++++++++++++++++ssyd+Ny``d+++++++++.-        y+++++++-
         .`       `        ++++++++++s++N+y+-y++++++`   +++++++++`         -++++++d
                           `N++++++y:`-`     :h++++d`     /d++++N+` `:     +++++++/
                           -+++y:`           `y+++NN+s:`    .odN++N+h/    `N++++++`
                           +++:             /dy+-.````         `o+++/     y++++++N:
                           /+y             s++                   /N+s     h++++++++do/-`
                    `-.   o+N.       .-`./y++h--                 `N+``:.`  :sdN++++++++++y+-`
                  :ooNNddN++Nd:    -h+++++hsyd++:          `/yds++++++Nh:-`   `.:osh+N++++++Ndo:`
                  `  :ysNhd+h/oo  .s--+++N`   /h/          o/-/y++y+h`               `.:+y++++++Ns.
                    +dys-/++y` `  `. s+hy:     `.             .NNs:+N.                     .++++++N:
                   `y    /o         :+          `/sy/`        ys:  -s-                       `y++++N
                    `    :          `.       .+d++h`         `/                               :+++++
                                           :h+++Nh/:+osyyhhhhhyysso+/::.``                   `h++++N
                                        `++++Nhyydho+/:-----:/+osyd+++++++N+dhyso+/::-----:+yN+++++/
                                     `:yN+Nyyyhh+`                   .-/oydN+++++++++++++++++++++d-
                            :+///+sy+++++++N+N++++dhhho-                    `-/oydNN+++++++++Nds-
                             `:oydNN+++++++++++N+dy+-`                             `.-:////-.`
                                    `+++++++++´

*/

public class ActividadPrincipalActivity extends Activity {
    FragmentManager adminFragments;
    FragmentTransaction transacFragments;
    String idObjetoABuscar;
    String categoriaSeleccionada;
    String nombreCategoriaSeleccionada;
    String requestListaLugares;
    ArrayList<Fragment> stackFragments;
    float coordenadaY;
    float coordenadaX;
    int radio;
    boolean geo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        adminFragments = getFragmentManager();
    }
    public String getIdObjetoABuscar () {

        return idObjetoABuscar;
    }
    public String getNombreCategoriaSeleccionada()
    {
        return nombreCategoriaSeleccionada;
    }
    public boolean getGeo(){
        return geo;
    }

    public String getRequestListaLugares()
    {
        return requestListaLugares;
    }

    public String getCategoriaSeleccionada(){

        return categoriaSeleccionada;
    }

    public void guardarLocalizacion(float coordenadaYMapa, float coordenadaXMapa, int radioMapa)
    {
        coordenadaX = coordenadaXMapa;
        coordenadaY = coordenadaYMapa;
        radio = radioMapa;
    }
    public void bCategoria(View vista){
        BuscarCategoria frgCat;
        frgCat = new BuscarCategoria();
        geo = false;

        transacFragments = adminFragments.beginTransaction();
        transacFragments.replace(R.id.Fragment, frgCat);
        transacFragments.commit();


    }
    public void bNombre(View vista){
        BuscarNombre frgCat;
        frgCat = new BuscarNombre();

        transacFragments = adminFragments.beginTransaction();
        transacFragments.replace(R.id.Fragment, frgCat);
        transacFragments.commit();

    }
    public void bGeo(View vista){
        BuscarCategoria frgCat;
        frgCat = new BuscarCategoria();
        geo = true;

        transacFragments = adminFragments.beginTransaction();
        transacFragments.replace(R.id.Fragment, frgCat);
        transacFragments.commit();

    }
    public void MostrarDatosObjeto (String idObjeto) {
        idObjetoABuscar = idObjeto;
        MostrarTraumas fragmentMostrarDatosObjeto;
        fragmentMostrarDatosObjeto = new MostrarTraumas();
        transacFragments = adminFragments.beginTransaction();
        transacFragments.replace(R.id.Fragment, fragmentMostrarDatosObjeto);
        transacFragments.commit();
        stackFragments.add(fragmentMostrarDatosObjeto);
    }
    public void irAMostrarListaLugares(String categoria, String nombreCategoria)
    {
        categoriaSeleccionada = categoria;
        nombreCategoriaSeleccionada = nombreCategoria;
        if(geo) {
            requestListaLugares = "http://epok.buenosaires.gob.ar/reverseGeocoderLugares/?x=" + coordenadaX + "&y=" + coordenadaY + "&categorias=" + categoria + "&radio=" + radio;
        }
        else
        {
            requestListaLugares = "http://epok.buenosaires.gob.ar/buscar/?texto=" +
                    nombreCategoriaSeleccionada + "&categoria=" + categoriaSeleccionada;

        }
        Log.d("Trauma", "La request es: " + requestListaLugares);
        MostrarTraumas ListaTraumas;
        ListaTraumas = new MostrarTraumas();

        transacFragments = adminFragments.beginTransaction();
        transacFragments.replace(R.id.Fragment, ListaTraumas);
        transacFragments.commit();
        stackFragments.add(ListaTraumas);
    }
}
