package com.farmacia.ds1718.reservafarmacia.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ListFarmacia extends ArrayList<Farmacia> implements Parcelable {


    public ListFarmacia() {

    }

    protected ListFarmacia(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        int size = this.size();

        //We have to write the list size, we need him recreating the list

        dest.writeInt(size);

        //We decided arbitrarily to write first the Name and later the Phone Number.

        for (int i = 0; i < size; i++) {

            Farmacia p = this.get(i);

            dest.writeString(p.getNombre());
            dest.writeString(p.getId());
            dest.writeString(p.getLat());
            dest.writeString(p.getLng());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListFarmacia> CREATOR = new Creator<ListFarmacia>() {
        @Override
        public ListFarmacia createFromParcel(Parcel in) {
            return new ListFarmacia(in);
        }

        @Override
        public ListFarmacia[] newArray(int size) {
            return new ListFarmacia[size];
        }
    };

    private void readFromParcel(Parcel in) {

        this.clear();

        //First we have to read the list size

        int size = in.readInt();

        //Reading remember that we wrote first the Name and later the Phone Number.

        //Order is fundamental

        for (int i = 0; i < size; i++) {

            Farmacia p = new Farmacia();

            p.setNombre(in.readString());
            p.setId(in.readString());
            p.setLat(in.readString());
            p.setLng(in.readString());
            this.add(p);

        }
    }
}
