package com.farmacia.ds1718.reservafarmacia.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.farmacia.ds1718.reservafarmacia.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductRemoteDataSource implements RemoteDataSource{
    private static ProductRemoteDataSource INSTANCE = null;

    private final static String SERVER_URL = "http://85.55.247.119:8080/RESTful/obtenerArticulos/false/Todos";
    private final static String LOG_TAG = "ProductRemoteDS";
    private Context context;
    private RequestQueue requestQueue;

    private ProductRemoteDataSource(Context context){
        this.context = context;
        // Instantiate the RequestQueue
        requestQueue = Volley.newRequestQueue(context);
    }

    public static ProductRemoteDataSource getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new ProductRemoteDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void load(final RemoteDataSource.GetCallback callback) {

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                SERVER_URL,
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

        Product product = new Product(productJSON.getString("nombre"), productJSON.getString("descripcion"),
                productJSON.getString("precio"), productJSON.getString("stock"),
                productJSON.getString("imagen"),productJSON.getString("id"),productJSON.getString("destacado"),"true");

        //product.setNombre(productJSON.getString("nombre"));

        return product;
    }
}
