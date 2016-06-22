package com.istandev.cekhalal;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.istandev.cekhalal.adapter.DetailProdukAdapter;
import com.istandev.cekhalal.entity.IstilahBahan;
import com.istandev.cekhalal.entity.Produk;
import com.istandev.cekhalal.utility.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IstilahBahanActivity extends AppCompatActivity {
    private List<IstilahBahan> daftar_produk = new ArrayList<IstilahBahan>();
    private DetailProdukAdapter mAdapter;

    JSONParser jParser = new JSONParser();
    JSONArray daftarProduk = null;
    String url_read_produk = "http://adikistanto.informatikaundip.com/cekhalal/read_istilah_bahan.php";
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_PRODUK = "produk";
    public static final String TAG_NAMA_BAHAN = "nama_bahan";
    public static final String TAG_ISTILAH_LAIN = "istilah_lain";

    private ProgressBar progressBar;

    IstilahBahan istilahBahan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istilah_bahan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Istilah Bahan Baku");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progresbar);
        new  ReadProdukTask().execute();
    }

    class ReadProdukTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
            //progressText.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... sText) {
            String returnResult = getTokoList(); //memanggil method getTokoList()
            return returnResult;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            //progressText.setVisibility(View.GONE);
            if(result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(IstilahBahanActivity.this, "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }

            if(result.equalsIgnoreCase("no results"))
            {
                Toast.makeText(IstilahBahanActivity.this, "Data empty", Toast.LENGTH_LONG).show();
            }else{
                RecyclerView myList = (RecyclerView) findViewById(R.id.rv_istilah_bahan);
                myList.setNestedScrollingEnabled(false);
                mAdapter = new DetailProdukAdapter(daftar_produk);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(IstilahBahanActivity.this,LinearLayoutManager.VERTICAL, false);
                myList.setLayoutManager(mLayoutManager);
                myList.setItemAnimator(new DefaultItemAnimator());
                myList.setAdapter(mAdapter);

            }

        }


        public String getTokoList()
        {
            Produk tempToko;
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                Log.v("tes","tes");
                JSONObject json = jParser.makeHttpRequest(url_read_produk,"POST", parameter);
                Log.v("tes1","tes1");
                int success = json.getInt(TAG_SUCCESS);
                Log.v("sukes",""+json.getInt(TAG_SUCCESS));
                if (success == 1) { //Ada record Data (SUCCESS = 1)
                    //Getting Array of daftar_mhs
                    daftarProduk = json.getJSONArray(TAG_PRODUK);
                    // looping through All daftar_mhs
                    for (int i = 0; i < daftarProduk.length() ; i++){
                        JSONObject c = daftarProduk.getJSONObject(i);
                        Log.v("nama_bahan",c.getString(TAG_NAMA_BAHAN));
                        Log.v("istilah_lain",c.getString(TAG_ISTILAH_LAIN));
                        IstilahBahan istilahBahan = new IstilahBahan(c.getString(TAG_NAMA_BAHAN), c.getString(TAG_ISTILAH_LAIN));
                        daftar_produk.add(istilahBahan);

                    }
                    return "OK";
                }
                else {
                    //Tidak Ada Record Data (SUCCESS = 0)
                    return "no results";
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception Caught";
            }
        }

    }


}
