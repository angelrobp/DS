package com.farmacia.ds1718.reservafarmacia.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farmacia.ds1718.reservafarmacia.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRemoteDataSource  implements RemoteDataSource {
    private static UserRemoteDataSource INSTANCE = null;

    private final static String USER_URL = "http://85.55.247.119:8080/RESTful/login";
    private final static String LOG_TAG = "UserRemoteDS";
    private Context context;
    private static String usuario, pass;
    private RequestQueue requestQueue;

    private UserRemoteDataSource(Context context){
        this.context = context;
        // Instantiate the RequestQueue
        requestQueue = Volley.newRequestQueue(context);
    }

    public static UserRemoteDataSource getInstance(Context context, String mUsuario, String mPass){
        if(INSTANCE == null){
            INSTANCE = new UserRemoteDataSource(context);
        }
        usuario = mUsuario;
        pass = mPass;
        return INSTANCE;
    }

    @Override
    public void load(final GetCallback callback) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, USER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VOLLEY", response);
                List<Object> users = new ArrayList<>();
                User usuario = new User();
                usuario.setId(response);
                users.add(usuario);
                callback.onLoaded(users);
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
                params.put("nombre", usuario);
                params.put("pass", pass);
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
