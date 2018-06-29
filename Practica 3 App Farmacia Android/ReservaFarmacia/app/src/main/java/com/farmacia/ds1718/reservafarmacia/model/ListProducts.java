package com.farmacia.ds1718.reservafarmacia.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ListProducts extends ArrayList<Product> implements Parcelable {

    public ListProducts() {

    }

    protected ListProducts(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        int size = this.size();

        //We have to write the list size, we need him recreating the list

        dest.writeInt(size);

        //We decided arbitrarily to write first the Name and later the Phone Number.

        for (int i = 0; i < size; i++) {

            Product p = this.get(i);

            dest.writeString(p.getNombre());
            dest.writeString(p.getDescripcion());
            dest.writeString(p.getDestacado());
            dest.writeString(p.getId());
            dest.writeString(p.getImagen());
            dest.writeString(p.getPrecio());
            dest.writeString(p.getStock());
            dest.writeString(p.getEstado());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListProducts> CREATOR = new Creator<ListProducts>() {
        @Override
        public ListProducts createFromParcel(Parcel in) {
            return new ListProducts(in);
        }

        @Override
        public ListProducts[] newArray(int size) {
            return new ListProducts[size];
        }
    };

    private void readFromParcel(Parcel in) {

        this.clear();

        //First we have to read the list size

        int size = in.readInt();

        //Reading remember that we wrote first the Name and later the Phone Number.

        //Order is fundamental

        for (int i = 0; i < size; i++) {

            Product p = new Product();

            p.setNombre(in.readString());
            p.setDescripcion(in.readString());
            p.setDestacado(in.readString());
            p.setId(in.readString());
            p.setImagen(in.readString());
            p.setPrecio(in.readString());
            p.setStock(in.readString());
            p.setEstado(in.readString());
            this.add(p);

        }
    }
}
