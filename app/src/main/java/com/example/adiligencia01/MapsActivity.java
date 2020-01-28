package com.example.adiligencia01;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private DrawerLayout drawer;
    private GoogleMap mMap;

    //declara variaveis para permissão location
    private static final String[] LOCATION_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int INITIAL_REQUEST=1337;
    private static final int LOCATION_REQUEST=INITIAL_REQUEST+3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar myToolbar = ( Toolbar ) findViewById(R.id.my_toolbar);
        setSupportActionBar( myToolbar );
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

        @Override
        public void onMapReady ( GoogleMap googleMap ) {
            mMap = googleMap;

            LatLng sydney = new LatLng(-29.7549941, -51.150283);
            LatLng esteio = new LatLng(-29.8524632, -51.1845758);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker em São Leopoldo")).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pin));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.addMarker(new MarkerOptions().position(esteio).title("Marker em Esteio"));

            //setar o zoom do mapa
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12.0f));
            //mostrar controle de zoom
            mMap.getUiSettings().setZoomControlsEnabled(true);
            //verifica se é permitido ao aplicativo pegar a localização atual do dispositivo
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                mMap.getUiSettings().setCompassEnabled(true);
            }else{
                //caso ainda não tenha dada a permissão,solicitar a permissão
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    requestPermissions( LOCATION_PERMS, LOCATION_REQUEST );
                }
            }

            //adiciona rota
            mMap.addPolyline(new PolylineOptions()
                    .add(sydney, esteio)
                    .width(5)
                    .color(Color.RED));


            mMap.addCircle(
                    new CircleOptions()
                            .center(esteio)
                            .radius(1580.0)
                            .strokeWidth(3f)
                            .strokeColor(Color.RED)
                            .fillColor(Color.argb(70,150,50,50))

            );
        }
    private void showGPSDisabledAlertToUser(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
    }

