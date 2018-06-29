package com.farmacia.ds1718.reservafarmacia.model;

public class Farmacia {
    private String nombre;
    private String lat;
    private String lng;
    private String id;

    public Farmacia() {
        this.nombre = "nulo";
        this.lat = "nulo";
        this.lng = "nulo";
        this.id = "nulo";
    }

    public Farmacia(String nombre, String lat, String lng, String id) {
        this.nombre = nombre;
        this.lat = lat;
        this.lng = lng;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
