package com.farmacia.ds1718.reservafarmacia.controller;

import org.json.JSONException;

import java.util.List;

public interface RemoteDataSource {
    void load(GetCallback callback) throws JSONException;

    public interface GetCallback{
        void onLoaded(List<Object> obj);
        void onError();
    }
}
