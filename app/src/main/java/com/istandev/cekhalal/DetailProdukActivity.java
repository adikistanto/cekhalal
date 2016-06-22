package com.istandev.cekhalal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailProdukActivity extends AppCompatActivity {

    private String[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Detail");

        Bundle extras=getIntent().getExtras();
        array=extras.getStringArray("data");



        TextView t1 = (TextView) findViewById(R.id.textView_nama_produk);
        TextView t2 = (TextView) findViewById(R.id.textView_merek_produk);
        TextView t3 = (TextView) findViewById(R.id.textView_deskripsi_produk);
        ImageView im1 = (ImageView) findViewById(R.id.gambar_detail);
        t1.setText(array[0]);
        t2.setText("merek : "+array[1]);
        t3.setText("komposisi :\n"+array[3]);
        Picasso.with(this).load(array[2]).into(im1);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            int SUCCESS_RESULT=1;
            setResult(SUCCESS_RESULT, new Intent());
            finish();  //return to caller
            return true;
        }
        return false;
    }


}
