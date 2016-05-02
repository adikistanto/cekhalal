package com.istandev.cekhalal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Triger Button
        findViewById(R.id.scan_halal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, Scan1Activity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.daftar_produk_halal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, DaftarProdukHalalActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.istilah_bahan_baku).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, IstilahActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.panduan_sertifikasi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, PanduanActivity.class);
                startActivity(i);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_akun) {
            Intent i = new Intent (MainActivity.this, AkunActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_pengaturan) {
            Intent i = new Intent (MainActivity.this, PengaturanActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_tentang) {
            Intent i = new Intent (MainActivity.this, TentangActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
