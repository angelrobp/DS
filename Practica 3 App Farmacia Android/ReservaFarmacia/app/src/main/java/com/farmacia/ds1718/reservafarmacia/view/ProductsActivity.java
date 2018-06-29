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
import android.widget.ListView;
import android.widget.Toast;

import com.farmacia.ds1718.reservafarmacia.R;
import com.farmacia.ds1718.reservafarmacia.controller.ObtenerCarritoRemoteDataSource;
import com.farmacia.ds1718.reservafarmacia.controller.ProductRemoteDataSource;
import com.farmacia.ds1718.reservafarmacia.controller.RemoteDataSource;
import com.farmacia.ds1718.reservafarmacia.controller.SendProductRemoteDataSource;
import com.farmacia.ds1718.reservafarmacia.model.ListProducts;
import com.farmacia.ds1718.reservafarmacia.model.Product;
import com.farmacia.ds1718.reservafarmacia.model.User;

import org.json.JSONException;

import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    private ListProducts listaProductos = null;
    private ListProducts listaProductosCarrito = null;
    private User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        listaProductosCarrito = new ListProducts();

        Bundle b = getIntent().getExtras(); //Get the intent's extras

        listaProductos = b.getParcelable("products"); //get our list

        usuario = b.getParcelable("user");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObtenerCarritoRemoteDataSource obtenerCarritoRemoteDataSource = ObtenerCarritoRemoteDataSource.getInstance(getApplicationContext(), usuario.getId());
                try {
                    obtenerCarritoRemoteDataSource.load(new RemoteDataSource.GetCallback() {
                        @Override
                        public void onLoaded(List<Object> products) {
                            Log.d("LOADED","loadPlaces: onPlacesLoaded");
                            listaProductosCarrito = new ListProducts();
                            if(products.get(0) instanceof Product) {
                                for (int i=0; i<products.size(); i++) {
                                    listaProductosCarrito.add((Product) products.get(i));
                                }
                            }
                            CharSequence text = "Producto agregado.";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                            toast.show();

                            Bundle b = new Bundle();


                            b.putParcelable("productsCarrito", listaProductosCarrito); //Insert list in a Bundle object
                            b.putParcelable("products", listaProductos); //Insert list in a Bundle object
                            b.putParcelable("user", usuario); //Insert list in a Bundle object

                            Intent intent = new Intent(getApplicationContext(), CarritoActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            intent.putExtras(b); //Insert the Bundle object in the Intent' Extras

                            startActivity(intent); //Start Activity

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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // creamos el listado
        ListAdapter listAdapter = new ListAdapter(this, listaProductos);

        // establecemos el adaptador en la lista
        ListView listView = (ListView) findViewById(R.id.listaProductos);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                Log.d("DATO: ",  listaProductos.get(position).toString());

                String id = listaProductos.get(position).getId();
                SendProductRemoteDataSource sendProductRemoteDataSource = SendProductRemoteDataSource.getInstance(getApplicationContext(), id, usuario.getId());
                sendProductRemoteDataSource.load(new RemoteDataSource.GetCallback() {
                    @Override
                    public void onLoaded(List<Object> products) {
                        Log.d("LOADED","loadPlaces: onPlacesLoaded");
                        listaProductosCarrito = new ListProducts();
                        if(products.get(0) instanceof Product) {
                            for (int i=0; i<products.size(); i++) {
                                listaProductosCarrito.add((Product) products.get(i));
                            }
                        }

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
    }



}
