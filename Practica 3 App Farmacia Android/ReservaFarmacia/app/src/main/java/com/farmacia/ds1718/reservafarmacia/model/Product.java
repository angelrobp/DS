package com.farmacia.ds1718.reservafarmacia.model;

public class Product {
    private String nombre;
    private String descripcion;
    private String precio;
    private String stock;
    private String imagen;
    private String id;
    private String destacado;
    private String estado;
    private String precioTotal;
    private String cantidad;


    public Product() {
        estado="false";
        this.nombre = "nulo";
        this.descripcion = "nulo";
        this.precio = "nulo";
        this.stock = "nulo";
        this.imagen = "nulo";
        this.id = "nulo";
        this.destacado = "nulo";
        this.estado = "nulo";
    }

    public Product(String nombre, String descripcion, String precio, String stock, String imagen, String id, String destacado, String estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
        this.id = id;
        this.destacado = destacado;
        this.estado = estado;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Product(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestacado() {
        return destacado;
    }

    public void setDestacado(String destacado) {
        this.destacado = destacado;
    }
}
