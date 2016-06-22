package com.istandev.cekhalal;


import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.sudar.zxingorient.ZxingOrient;
import me.sudar.zxingorient.ZxingOrientResult;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        fm = getSupportFragmentManager();

        //Triger Button
        findViewById(R.id.scan_halal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ZxingOrient(MainActivity.this).initiateScan();
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
                Intent i = new Intent (MainActivity.this, IstilahBahanActivity.class);
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
        findViewById(R.id.laporan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, LaporActivity.class);
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

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        ZxingOrientResult scanningResult = ZxingOrient.parseActivityResult(requestCode, resultCode, intent);
        String scanContent = scanningResult.getContents();
        if (scanContent != null) {
            Bundle extras = new Bundle();
            extras.putString("nilai_barcode",scanContent);
            FragmentScan newFragment = new FragmentScan();
            newFragment.setArguments(extras);
            FragmentTransaction transactionFragment = this.getSupportFragmentManager().beginTransaction();
            transactionFragment.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transactionFragment.add(android.R.id.content, newFragment,"dialog").addToBackStack(null).commitAllowingStateLoss();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Tidak ditemukan barcode", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /*@Override
    public void onBackPressed() {
            super.onBackPressed();
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
    }*/


}
