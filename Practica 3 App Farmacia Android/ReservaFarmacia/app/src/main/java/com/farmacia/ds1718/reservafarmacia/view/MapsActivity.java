package com.farmacia.ds1718.reservafarmacia.view;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.farmacia.ds1718.reservafarmacia.R;
import com.farmacia.ds1718.reservafarmacia.controller.PagarRemoteDataSource;
import com.farmacia.ds1718.reservafarmacia.controller.ProductRemoteDataSource;
import com.farmacia.ds1718.reservafarmacia.controller.RemoteDataSource;
import com.farmacia.ds1718.reservafarmacia.controller.UserRemoteDataSource;
import com.farmacia.ds1718.reservafarmacia.model.ListFarmacia;
import com.farmacia.ds1718.reservafarmacia.model.ListProducts;
import com.farmacia.ds1718.reservafarmacia.model.Product;
import com.farmacia.ds1718.reservafarmacia.model.User;
import com.google.android.gms.common.internal.constants.ListAppsActivityContract;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    private ListFarmacia listaFarmacias;
    private ListProducts listaProductosCarrito;
    private User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Bundle b = getIntent().getExtras(); //Get the intent's extras

        listaProductosCarrito = b.getParcelable("productsCarrito"); //get our list

        usuario = b.getParcelable("user");

        listaFarmacias = b.getParcelable("farmacias");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        for (int i=0; i<listaFarmacias.size(); i++) {
            LatLng farmacia = new LatLng(Double.parseDouble(listaFarmacias.get(i).getLat()), Double.parseDouble(listaFarmacias.get(i).getLng()));
            mMap.addMarker(new MarkerOptions().position(farmacia).title(listaFarmacias.get(i).getNombre()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(farmacia));
            mMap.setMinZoomPreference(12);
        }

        mMap.setOnMarkerClickListener(this);

/*
        LatLng position = new LatLng(-34, 151);
        Marker amarker = mMap.addMarker(new MarkerOptions().position(position).title("Hello World"));
        amarker.setTag(new Farmacia()); //Aqui se pasa la farmacia
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));*/
        //Luego para saber la farmacia asociada se usa Farmacia farmacia = (Farmacia) amarker.getTag();
    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        Log.d("MAPAS", "ENTRA");
        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        PagarRemoteDataSource pagarRemoteDataSource = PagarRemoteDataSource.getInstance(getApplicationContext(), usuario.getId());
        pagarRemoteDataSource.load(new PagarRemoteDataSource.GetCallback() {
            @Override
            public void onLoaded(List<Object> respuesta) {
                if(respuesta.get(0) instanceof  String) {
                    if(respuesta.get(0).equals("false")) {
                        CharSequence text = "Error en el pago";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                    }
                    else {
                        CharSequence text = "Pago realizado";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent); //Start Activity
                        finish();
                    }
                }
            }

            @Override
            public void onError() {
                CharSequence text = "Error en el pago";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }
        });

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}
