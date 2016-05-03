package com.istandev.cekhalal;

import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.istandev.cekhalal.adapter.ProdukHalalAdapter;
import com.istandev.cekhalal.customview.ExpandableGridView;
import com.istandev.cekhalal.entity.Produk;

import java.util.ArrayList;


public class FragmentProdukHalal extends android.support.v4.app.Fragment {
    ArrayList<Produk> daftar_produk = new ArrayList<Produk>();

    public FragmentProdukHalal() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_panduan1, container, false);

        ExpandableGridView gridview = (ExpandableGridView) rootView.findViewById(R.id.gridview);
        gridview.setExpanded(true);

        Bundle extras = getArguments();
        if (extras != null) {
            daftar_produk = extras.getParcelableArrayList("arraylist");
            gridview.setAdapter(new ProdukHalalAdapter(getActivity(),daftar_produk));
        }
        return rootView;
    }
}