package com.farmacia.ds1718.reservafarmacia.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.farmacia.ds1718.reservafarmacia.R;
import com.farmacia.ds1718.reservafarmacia.controller.DeleteProductRemoteDataSource;
import com.farmacia.ds1718.reservafarmacia.controller.ObtenerMapaRemoteDataSource;
import com.farmacia.ds1718.reservafarmacia.controller.RemoteDataSource;
import com.farmacia.ds1718.reservafarmacia.controller.SendProductRemoteDataSource;
import com.farmacia.ds1718.reservafarmacia.model.Farmacia;
import com.farmacia.ds1718.reservafarmacia.model.ListFarmacia;
import com.farmacia.ds1718.reservafarmacia.model.ListProducts;
import com.farmacia.ds1718.reservafarmacia.model.Product;
import com.farmacia.ds1718.reservafarmacia.model.User;

import java.util.ArrayList;
import java.util.List;

public class CarritoActivity extends AppCompatActivity {

    private ListProducts listaProductosCarrito = null;
    private ListProducts listaProductos = null;
    private Product selectedProduct = null;
    private User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        Bundle b = getIntent().getExtras(); //Get the intent's extras

        listaProductosCarrito = b.getParcelable("productsCarrito"); //get our list
        listaProductos = b.getParcelable("products");
        usuario = b.getParcelable("user");

/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();

                b.putParcelable("products", listaProductos); //Insert list in a Bundle object
                b.putParcelable("user", usuario); //Insert list in a Bundle object

                Intent intent = new Intent(getApplicationContext(), ProductsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                intent.putExtras(b); //Insert the Bundle object in the Intent' Extras

                startActivity(intent); //Start Activity
                finish();
            }
        });

        // creamos el listado
        ListAdapter listAdapter = new ListAdapter(this, listaProductosCarrito);

        // establecemos el adaptador en la lista
        ListView listView = (ListView) findViewById(R.id.listaCarrito);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                Log.d("DATO: ",  listaProductosCarrito.get(position).toString());
                selectedProduct = listaProductosCarrito.get(position);

            }
        });

        final Button finalizar = findViewById(R.id.finalizarButton);
        finalizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ObtenerMapaRemoteDataSource deleteProductRemoteDataSource = ObtenerMapaRemoteDataSource.getInstance(getApplicationContext());
                deleteProductRemoteDataSource.load(new RemoteDataSource.GetCallback() {
                    @Override
                    public void onLoaded(List<Object> farmacias) {
                        Log.d("LOADED","loadPlaces: onPlacesLoaded");
                        ListFarmacia listaFarmacias = new ListFarmacia();
                        if(farmacias.get(0) instanceof Farmacia) {
                            for (int i=0; i<farmacias.size(); i++) {
                                listaFarmacias.add((Farmacia) farmacias.get(i));
                            }
                            Bundle b = new Bundle();

                            b.putParcelable("products", listaProductosCarrito); //Insert list in a Bundle object
                            b.putParcelable("user", usuario); //Insert list in a Bundle object
                            b.putParcelable("farmacias", listaFarmacias); //Insert list in a Bundle object

                            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            intent.putExtras(b); //Insert the Bundle object in the Intent' Extras

                            startActivity(intent); //Start Activity
                        }

                    }

                    @Override
                    public void onError() {
                        Log.d("ERROR","loadPlaces: onError");
                        CharSequence text = "Error al descontar.";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                    }
                });



            }
        });

        final Button descontar = findViewById(R.id.descontarButton);
        descontar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = selectedProduct.getId();
                selectedProduct = null;
                DeleteProductRemoteDataSource deleteProductRemoteDataSource = DeleteProductRemoteDataSource.getInstance(getApplicationContext(), id, usuario.getId());
                deleteProductRemoteDataSource.load(new RemoteDataSource.GetCallback() {
                    @Override
                    public void onLoaded(List<Object> products) {
                        Log.d("LOADED","loadPlaces: onPlacesLoaded");
                        listaProductosCarrito = new ListProducts();
                        if(products.get(0) instanceof Product) {
                            for (int i=0; i<products.size(); i++) {
                                listaProductosCarrito.add((Product) products.get(i));
                            }
                        }
                        // creamos el listado
                        ListAdapter listAdapter = new ListAdapter(getParent(), listaProductosCarrito);

                        // establecemos el adaptador en la lista
                        ListView listView = (ListView) findViewById(R.id.listaCarrito);
                        listView.setAdapter(listAdapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                                Log.d("DATO: ",  listaProductosCarrito.get(position).toString());
                                selectedProduct = listaProductosCarrito.get(position);

                            }
                        });

                        CharSequence text = "Producto descontado.";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                    }

                    @Override
                    public void onError() {
                        Log.d("ERROR","loadPlaces: onError");
                        CharSequence text = "Error al descontar.";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                    }
                });
            }
        });
/*
        final Button eliminar = findViewById(R.id.eliminarButton);
        eliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = listaProductosCarrito.get(position).getId();
                SendProductRemoteDataSource sendProductRemoteDataSource = SendProductRemoteDataSource.getInstance(getApplicationContext(), id, usuario.getId());
                sendProductRemoteDataSource.load(new RemoteDataSource.GetCallback() {
                    @Override
                    public void onLoaded(List<Object> products) {
                        Log.d("LOADED","loadPlaces: onPlacesLoaded");
                        ListProducts listaProductosCarrito = new ListProducts();
                        if(products.get(0) instanceof Product) {
                            for (int i=0; i<products.size(); i++) {
                                listaProductosCarrito.add((Product) products.get(i));
                            }
                        }

                        Bundle b = new Bundle();

                        b.putParcelable("products", listaProductosCarrito); //Insert list in a Bundle object
                        b.putParcelable("user", usuario); //Insert list in a Bundle object

                        Intent intent = new Intent(getApplicationContext(), CarritoActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        intent.putExtras(b); //Insert the Bundle object in the Intent' Extras

                        startActivity(intent); //Start Activity

                        CharSequence text = "Producto agregado.";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                    }

                    @Override
                    public void onError() {
                        Log.d("ERROR","loadPlaces: onError");
                        CharSequence text = "Error al agregar.";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                    }
                });
            }
        });
        */
    }

}
