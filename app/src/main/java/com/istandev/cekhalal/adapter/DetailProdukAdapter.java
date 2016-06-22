package com.istandev.cekhalal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.istandev.cekhalal.R;
import com.istandev.cekhalal.entity.IstilahBahan;
import com.istandev.cekhalal.entity.Produk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADIK on 26/04/2016.
 */
public class DetailProdukAdapter extends RecyclerView.Adapter<DetailProdukAdapter.MyViewHolder> {

    private List<IstilahBahan> daftar_produk;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama_bahan, istilah_lain;

        public MyViewHolder(View view) {
            super(view);
            nama_bahan = (TextView) view.findViewById(R.id.nama_bahan);
            istilah_lain = (TextView) view.findViewById(R.id.istilah_lain);
        }
    }


    public DetailProdukAdapter(List<IstilahBahan> daftar_produk) {
        this.daftar_produk = daftar_produk;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_istilah_bahan, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        IstilahBahan movie = daftar_produk.get(position);
       // IstilahBahan movie = IstilahBahan.findById(IstilahBahan.class, 1);
        holder.nama_bahan.setText(movie.getNama_bahan());
        holder.istilah_lain.setText(movie.getIstilah_lain());
    }

    @Override
    public int getItemCount() {
        return daftar_produk.size();
    }
}
