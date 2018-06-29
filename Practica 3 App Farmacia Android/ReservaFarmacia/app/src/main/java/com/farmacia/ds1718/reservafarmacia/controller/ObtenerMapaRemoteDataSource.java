package com.farmacia.ds1718.reservafarmacia.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.farmacia.ds1718.reservafarmacia.model.Farmacia;
import com.farmacia.ds1718.reservafarmacia.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ObtenerMapaRemoteDataSource implements RemoteDataSource {

    private static ObtenerMapaRemoteDataSource INSTANCE = null;

    private final static String SERVER_URL = "http://85.55.247.119:8080/RESTful/obtenerFarmacias";
    private final static String LOG_TAG = "ProductRemoteDS";
    private Context context;
    private RequestQueue requestQueue;

    private ObtenerMapaRemoteDataSource(Context context){
        this.context = context;
        // Instantiate the RequestQueue
        requestQueue = Volley.newRequestQueue(context);
    }

    public static ObtenerMapaRemoteDataSource getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new ObtenerMapaRemoteDataSource(context);
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
                                products.add(getFarmaciaFromJSON(productJSON));
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

    private Farmacia getFarmaciaFromJSON(JSONObject productJSON) throws JSONException {

        Farmacia farmacia = new Farmacia(productJSON.getString("nombre"), productJSON.getString("lat"),
                productJSON.getString("lng"), productJSON.getString("id"));

        return farmacia;
    }
}
