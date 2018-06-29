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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farmacia.ds1718.reservafarmacia.model.Product;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteProductRemoteDataSource implements RemoteDataSource{
    private static DeleteProductRemoteDataSource INSTANCE = null;

    private final static String USER_URL = "http://85.55.247.119:8080/RESTful/disminuirProductoCarrito";
    private final static String LOG_TAG = "SendRemoteDS";
    private Context context;
    private static String idP, idU;
    private RequestQueue requestQueue;

    private DeleteProductRemoteDataSource(Context context) {
        this.context = context;
        // Instantiate the RequestQueue
        requestQueue = Volley.newRequestQueue(context);
    }

    public static DeleteProductRemoteDataSource getInstance(Context context, String mIdP, String mIdU) {
        if (INSTANCE == null) {
            INSTANCE = new DeleteProductRemoteDataSource(context);
        }
        idU = mIdU;
        idP = mIdP;
        return INSTANCE;
    }

    @Override
    public void load(final GetCallback callback) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, USER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VOLLEY", response);
                List<Object> productos = new ArrayList<>();
                Product producto = new Product();
                producto.setEstado(response);
                productos.add(producto);
                callback.onLoaded(productos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_producto", idP);
                params.put("id_usuario", idU);
                params.put("cantidad", "1");
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = new String(response.data, StandardCharsets.UTF_8);
                    //responseString = String.valueOf(response.data);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        requestQueue.add(stringRequest);
    }
}
