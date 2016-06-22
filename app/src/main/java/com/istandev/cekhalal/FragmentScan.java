package com.istandev.cekhalal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.istandev.cekhalal.adapter.ProdukHalalAdapter;
import com.istandev.cekhalal.entity.Produk;
import com.istandev.cekhalal.utility.JSONParser;
import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADIK on 19/05/2016.
 */
public class FragmentScan extends DialogFragment {

    JSONParser jParser = new JSONParser();
    JSONArray daftarProduk = null;
    String url_read_produk = "http://adikistanto.informatikaundip.com/cekhalal/scan_status_halal.php";
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_PRODUK = "produk";
    public static final String TAG_NAMA_PRODUK = "nama_produk";
    public static final String TAG_MEREK_PRODUK = "merek_produk";
    public static final String TAG_PABRIK_PRODUK = "pabrik_produk";
    public static final String TAG_BAHAN_PRODUK = "bahan_produk";
    public static final String TAG_STATUS_PRODUK = "status_produk";
    public static final String TAG_GAMBAR_PRODUK = "gambar_produk";
    public static final String TAG_KATEGORI_PRODUK = "kategori_produk";

    private ProgressBar progressBar;
    private TextView namaTV,merekTV,pabrikTV,bahanTV,kategoriTV;

    private String nama,merek,pabrik,nilai_barcode,kategori, bahan,url;

    private ImageView gambarIV;

    private LinearLayout ser,unser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hasil_scan, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progresbar);
        namaTV = (TextView) rootView.findViewById(R.id.nama);
        merekTV = (TextView) rootView.findViewById(R.id.merek);
        pabrikTV = (TextView) rootView.findViewById(R.id.pabrik);
        kategoriTV = (TextView) rootView.findViewById(R.id.kategori);
        bahanTV = (TextView) rootView.findViewById(R.id.bahan);
        gambarIV = (ImageView) rootView.findViewById(R.id.gambar_produk);

        ser = (LinearLayout) rootView.findViewById(R.id.bersertifikat);
        unser = (LinearLayout) rootView.findViewById(R.id.takbersertifikat);

        new ReadProdukTask().execute();

        Button but= (Button) rootView.findViewById(R.id.tutup);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = (DialogFragment) getFragmentManager().findFragmentByTag("dialog");
                if (dialogFragment != null) { dialogFragment.dismiss(); }
            }
        });

        Button but1= (Button) rootView.findViewById(R.id.lapor);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = (DialogFragment) getFragmentManager().findFragmentByTag("dialog");
                if (dialogFragment != null) { dialogFragment.dismiss(); }
                startActivity(new Intent(getActivity(),LaporActivity.class));
            }
        });

        Bundle extras = getArguments();
        if (extras != null) {
            nilai_barcode = extras.getString("nilai_barcode");
        }

        return rootView;
    }


    class ReadProdukTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
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
            if(result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(getActivity(), "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }

            if(result.equalsIgnoreCase("no results"))
            {
                unser.setVisibility(View.VISIBLE);
                ser.setVisibility(View.GONE);
            }else{
                unser.setVisibility(View.GONE);
                ser.setVisibility(View.VISIBLE);
                Log.v("nama",nama);
                namaTV.setText(nama);
                merekTV.setText("merek :"+merek);
                pabrikTV.setText("perusahaan :"+pabrik);
                kategoriTV.setText("kategori :"+kategori);
                bahanTV.setText("komposisi :\n"+bahan);
                Picasso.with(getContext()).load(url).into(gambarIV);
            }

        }


        public String getTokoList()
        {
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            parameter.add(new BasicNameValuePair("nilai_barcode", nilai_barcode));
            try {
                Log.v("tes","tes");
                JSONObject json = jParser.makeHttpRequest(url_read_produk,"POST", parameter);
                int success = json.getInt(TAG_SUCCESS);
                Log.v("sukes",""+json.getInt(TAG_SUCCESS));
                if (success == 1) { //Ada record Data (SUCCESS = 1)
                    //Getting Array of daftar_mhs
                    daftarProduk = json.getJSONArray(TAG_PRODUK);
                    // looping through All daftar_mhs
                    for (int i = 0; i < 1 ; i++){
                        JSONObject c = daftarProduk.getJSONObject(i);

                            nama= c.getString(TAG_NAMA_PRODUK);//nama
                            merek= c.getString(TAG_MEREK_PRODUK);//merek
                            pabrik=c.getString(TAG_PABRIK_PRODUK);//pabrik
                            bahan= c.getString(TAG_BAHAN_PRODUK);//bahan
                                //c.getString(TAG_STATUS_PRODUK),//status
                            kategori =c.getString(TAG_KATEGORI_PRODUK);//gambar
                            url = c.getString(TAG_GAMBAR_PRODUK);
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
