package com.farmacia.ds1718.reservafarmacia.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
    private String id;
    private String nombre;

    public User() {
        this.id = "false";
        this.nombre = "nulo";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    protected User(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
            dest.writeString(nombre);
            dest.writeString(id);
    }

    private void readFromParcel(Parcel in) {
            setNombre(in.readString());
            setId(in.readString());
    }
}
