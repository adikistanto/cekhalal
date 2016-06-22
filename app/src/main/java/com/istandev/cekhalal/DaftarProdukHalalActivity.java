package com.istandev.cekhalal;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.istandev.cekhalal.entity.Produk;
import com.istandev.cekhalal.utility.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DaftarProdukHalalActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    JSONParser jParser = new JSONParser();
    JSONArray daftarProduk = null;
    String url_read_produk = "http://adikistanto.informatikaundip.com/cekhalal/read_produk_halal.php";
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_PRODUK = "produk";
    public static final String TAG_NAMA_PRODUK = "nama_produk";
    public static final String TAG_MEREK_PRODUK = "merek_produk";
    public static final String TAG_PABRIK_PRODUK = "pabrik_produk";
    public static final String TAG_BAHAN_PRODUK = "bahan_produk";
    public static final String TAG_STATUS_PRODUK = "status_produk";
    public static final String TAG_GAMBAR_PRODUK = "gambar_produk";
    public static final String TAG_KATEGORI_PRODUK = "kategori_produk";


    ArrayList<Produk> daftar_produk_makanan_pokok = new ArrayList<Produk>();
    ArrayList<Produk> daftar_produk_makanan_cepat_saji = new ArrayList<Produk>();
    ArrayList<Produk> daftar_produk_air_mineral = new ArrayList<Produk>();
    ArrayList<Produk> daftar_produk_soft_drink = new ArrayList<Produk>();
    ArrayList<Produk> daftar_produk_perlengkapan = new ArrayList<Produk>();

    private ProgressBar progressBar;
    private TextView progressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_produk_halal);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);




        progressBar = (ProgressBar) findViewById(R.id.progresbar);
        //progressText = (TextView) findViewById(R.id.memuat);

        new ReadProdukTask().execute();

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentProdukHalal(), "Makanan Pokok");
        adapter.addFragment(new FragmentProdukHalal(), "Makanan Cepat Saji");
        adapter.addFragment(new FragmentProdukHalal(), "Air Mineral");
        adapter.addFragment(new FragmentProdukHalal(), "Soft Drink");
        adapter.addFragment(new FragmentProdukHalal(), "Perlengkapan");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            Bundle extras1 = new Bundle();
            if(title.equalsIgnoreCase("Makanan Pokok")){
                extras1.putParcelableArrayList("arraylist", daftar_produk_makanan_pokok);
            }else if(title.equalsIgnoreCase("Makanan Cepat Saji")){
                extras1.putParcelableArrayList("arraylist", daftar_produk_makanan_cepat_saji);
            }else if(title.equalsIgnoreCase("Air Mineral")){
                extras1.putParcelableArrayList("arraylist", daftar_produk_air_mineral);
            }else if(title.equalsIgnoreCase("Soft Drink")){
                extras1.putParcelableArrayList("arraylist", daftar_produk_soft_drink);
            }else if(title.equalsIgnoreCase("Perlengkapan")){
                extras1.putParcelableArrayList("arraylist", daftar_produk_perlengkapan);
            }

            fragment.setArguments(extras1);
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
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
                Toast.makeText(DaftarProdukHalalActivity.this, "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }

            if(result.equalsIgnoreCase("no results"))
            {
                Toast.makeText(DaftarProdukHalalActivity.this, "Data empty", Toast.LENGTH_LONG).show();
            }else{

                viewPager = (ViewPager) findViewById(R.id.viewpager);
                setupViewPager(viewPager);

                tabLayout = (TabLayout) findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(viewPager);

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

                        tempToko = new Produk(
                                c.getString(TAG_NAMA_PRODUK),//nama
                                c.getString(TAG_MEREK_PRODUK),//merek
                                c.getString(TAG_PABRIK_PRODUK),//pabrik
                                c.getString(TAG_BAHAN_PRODUK),//bahan
                                c.getString(TAG_STATUS_PRODUK),//status
                                c.getString(TAG_GAMBAR_PRODUK)//gambar
                        );
                        //daftar_toko_makanan.add(tempToko);
                        if (c.getString(TAG_KATEGORI_PRODUK).equalsIgnoreCase("Makanan Pokok")){
                            daftar_produk_makanan_pokok.add(tempToko);
                        }else if (c.getString(TAG_KATEGORI_PRODUK).equalsIgnoreCase("Makanan Cepat Saji")){
                            daftar_produk_makanan_cepat_saji.add(tempToko);
                        }else if (c.getString(TAG_KATEGORI_PRODUK).equalsIgnoreCase("Air Mineral")){
                            daftar_produk_air_mineral.add(tempToko);
                        }else if (c.getString(TAG_KATEGORI_PRODUK).equalsIgnoreCase("Soft Drink")){
                            daftar_produk_soft_drink.add(tempToko);
                        }else if (c.getString(TAG_KATEGORI_PRODUK).equalsIgnoreCase("Perlengkapan")){
                            daftar_produk_perlengkapan.add(tempToko);
                        }
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
