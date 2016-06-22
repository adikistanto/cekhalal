package com.istandev.cekhalal.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.istandev.cekhalal.DaftarProdukHalalActivity;
import com.istandev.cekhalal.DetailProdukActivity;
import com.istandev.cekhalal.R;
import com.istandev.cekhalal.customview.SquareImageView;
import com.istandev.cekhalal.entity.Produk;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ADIK on 07/01/2016.
 * Menampilkan daftar toko hasil pencarian
 */
public class ProdukHalalAdapter extends BaseAdapter {
    private Activity activity;
    String jenisProdukStr;
    //private ArrayList<HashMap<String, String>> data;
    private ArrayList<Produk> data_toko=new ArrayList<Produk>();

    private static LayoutInflater inflater = null;


    public ProdukHalalAdapter(Activity a, ArrayList<Produk> d) {
        activity = a; data_toko = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return data_toko.size();
    }
    public Object getItem(int position) {
        return data_toko.get(position);
    }
    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(activity);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_item_produk_halal, null);

            // set value into textview
            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_nama);
            //TextView textView1 = (TextView) gridView.findViewById(R.id.grid_item_merek);
            ImageView imageView = (ImageView) gridView.findViewById(R.id.gambar_produk);
            Produk data = data_toko.get(position);
            textView.setText(data.getNamaProduk());
           // textView1.setText(data.getMerekProduk());
            Picasso.with(activity).load(data.getGambar_produk()).into(imageView);

        } else {
            gridView = (View) convertView;
        }

        gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produk data = data_toko.get(position);
                String[] array = new String[]{data.getNamaProduk(),data.getMerekProduk(),data.getGambar_produk(),data.getBahan_produk()};
                Intent i = new Intent(activity, DetailProdukActivity.class);
                i.putExtra("data",array);
                activity.startActivity(i);
            }
        });

        return gridView;


    }
}
