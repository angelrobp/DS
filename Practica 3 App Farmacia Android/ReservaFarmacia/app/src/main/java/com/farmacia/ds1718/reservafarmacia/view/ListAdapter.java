package com.farmacia.ds1718.reservafarmacia.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.farmacia.ds1718.reservafarmacia.R;
import com.farmacia.ds1718.reservafarmacia.model.Product;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<String> {

    private Activity activity;
    ArrayList<Product> products;

    public ListAdapter(Activity activity, ArrayList<Product> products) {
        super(activity, R.layout.item_layout);
        this.activity = activity;
        this.products = products;
    }

    static class ViewHolder {
        protected TextView nameTextView;
    }

    public int getCount() {
        return products.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView,
                        final ViewGroup parent) {
        // inflamos nuestra vista con el layout
        View view = null;
        LayoutInflater inflator = activity.getLayoutInflater();
        view = inflator.inflate(R.layout.item_layout, null);
        final ViewHolder viewHolder = new ViewHolder();

        // *** instanciamos a los recursos
        viewHolder.nameTextView = (TextView) view
                .findViewById(R.id.nameTextView);

        // importante!!! establecemos el mensaje
        viewHolder.nameTextView.setText(products.get(position).getNombre());

        // *** instanciamos a los recursos
        viewHolder.nameTextView = (TextView) view
                .findViewById(R.id.precioText);

        // importante!!! establecemos el mensaje
        viewHolder.nameTextView.setText(products.get(position).getPrecio()+" €");

        return view;
    }

}