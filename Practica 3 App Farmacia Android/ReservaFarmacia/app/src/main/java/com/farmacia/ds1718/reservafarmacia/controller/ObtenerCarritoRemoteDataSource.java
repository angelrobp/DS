package com.farmacia.ds1718.reservafarmacia.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farmacia.ds1718.reservafarmacia.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObtenerCarritoRemoteDataSource implements RemoteDataSource {

    private static ObtenerCarritoRemoteDataSource INSTANCE = null;

    private static String SERVER_URL = "http://85.55.247.119:8080/RESTful/obtenerCarrito";
    private final static String LOG_TAG = "ProductRemoteDS";
    private Context context;
    private static String id;
    private RequestQueue requestQueue;

    private ObtenerCarritoRemoteDataSource(Context context){
        this.context = context;
        // Instantiate the RequestQueue
        requestQueue = Volley.newRequestQueue(context);
    }

    public static ObtenerCarritoRemoteDataSource getInstance(Context context, String mId){
        if(INSTANCE == null){
            INSTANCE = new ObtenerCarritoRemoteDataSource(context);
        }
        id = mId;

        return INSTANCE;
    }

    @Override
    public void load(final RemoteDataSource.GetCallback callback) throws JSONException {

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                SERVER_URL+"/"+id,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(LOG_TAG,response.toString());
                        try {
                            List<Object> products = new ArrayList<>();
                            for (int i = 0;i<response.length();i++) {
                                JSONObject productJSON = response.getJSONObject(i);
                                products.add(getProductFromJSON(productJSON));
                            }

                            callback.onLoaded(products);
                        } catch (JSONException e) {
                            Log.d(LOG_TAG,"Ha fallado: " + e.getMessage().toString());
                            callback.onError();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(LOG_TAG,error.toString());
                callback.onError();
            }
        });
        requestQueue.add(request);
    }

    private Product getProductFromJSON(JSONObject productJSON) throws JSONException {

        Product product = new Product();
        product.setId(productJSON.getString("id_producto"));
        product.setEstado("true");
        product.setPrecio(productJSON.getString("precio"));
        product.setPrecioTotal(productJSON.getString("precioTotal"));
        product.setNombre(productJSON.getString("nombre"));
        product.setCantidad(productJSON.getString("cantidad"));

        //product.setNombre(productJSON.getString("nombre"));

        return product;
    }
}
