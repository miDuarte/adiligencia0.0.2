package com.example.adiligencia01;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

//1. crie uma nova Map Activity
public class exercicioMaps extends AppCompatActivity implements OnMapReadyCallback {
    private DrawerLayout drawer;
    private GoogleMap mMap;

    private static final String[] LOCATION_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int INITIAL_REQUEST=1337;
    private static final int LOCATION_REQUEST=INITIAL_REQUEST+3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng caxias = new LatLng(-29.176283,-51.229030);
        LatLng gramado= new LatLng(-29.366857,-50.869902);
        LatLng porto = new LatLng(-30.047595,-51.184702);
        LatLng torres = new LatLng(-29.337934,-49.727090);
        LatLng sfpaula = new LatLng(-29.531041,-50.516140);

        //2. neste novo mapa insira Markers para as cidades de Caxias do Sul, Gramado, Porto Alegre e Torres.
        mMap.addMarker(new MarkerOptions().position(caxias).title("Marker em Caxias do Sul"));
        mMap.addMarker(new MarkerOptions().position(gramado).title("Marker em Gramado"));
        mMap.addMarker(new MarkerOptions().position(porto).title("Marker em Porto Alegre"));
        mMap.addMarker(new MarkerOptions().position(torres).title("Marker em Torres"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(caxias));

        //3. ajuste o zoom do mapa para que as quatro cidades apareçam ao carregar
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sfpaula, 12.0f));
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //4. crie polylines que liguem as 4 cidades
        mMap.addPolyline(new PolylineOptions()
                .add(caxias,gramado,porto,torres)
                .width(5)
                .color( Color.RED));
        //5. Encontre uma localização central (no meio das cidades marcadas) e crie neste um circulo com largura suficiente para cobrir todas as cidades
        mMap.addCircle(
                new CircleOptions()
                .center(sfpaula)
                .radius(2000)
                .strokeWidth(3f)
                .strokeColor(Color.RED)
                .fillColor(Color.argb(70,150,50,50))
        );
    }
}
