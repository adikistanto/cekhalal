package com.istandev.cekhalal;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.istandev.cekhalal.utility.ImageUpload;
import com.istandev.cekhalal.utility.JSONParser;
import com.istandev.cekhalal.utility.SessionManager;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LaporActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2;

    EditText EditTxtnamaproduk, EditTxthargaproduk, EditTxtpabrikproduk;
    Button ButKirim;
    String namaprodukStr,merekprodukStr, pabrikprodukStr;

    JSONParser jsonParser = new JSONParser();
    String url_tambah_produk = "http://adikistanto.informatikaundip.com/cekhalal/lapor_produk.php";

    File directory =  new File("/sdcard/");
    String imagePath="";
    String image_name;
    String upLoadServerUri = "http://adikistanto.informatikaundip.com/cekhalal/upload_foto_lapor.php";
    String photo_url;
    ImageView showImg;
    ImageUpload imgUpload = new ImageUpload();

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(this);

        session.checkLogin();

        // Selector
        showImg = (ImageView) findViewById(R.id.showImg);
        EditTxtnamaproduk = (EditText) findViewById(R.id.nama_produk);
        EditTxthargaproduk = (EditText) findViewById(R.id.merek_produk);
        EditTxtpabrikproduk = (EditText) findViewById(R.id.pabrik_produk);
        ButKirim = (Button) findViewById(R.id.button_lapor);


        findViewById(R.id.scrollView).setVisibility(View.VISIBLE);
        findViewById(R.id.linear).setVisibility(View.GONE);

        showImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectImage();
            }
        });

        ButKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namaprodukStr = EditTxtnamaproduk.getText().toString();
                merekprodukStr = EditTxthargaproduk.getText().toString();
                pabrikprodukStr = EditTxtpabrikproduk.getText().toString();

                if((namaprodukStr.isEmpty())||(merekprodukStr.isEmpty())||(pabrikprodukStr.isEmpty())||(imagePath.equalsIgnoreCase(""))){
                    Toast.makeText(LaporActivity.this,"Harap Lengkapi semua isian",Toast.LENGTH_SHORT).show();
                }else{
                    new LaporProdukTask().execute();
                }

            }
        });

        image_name = "NS"+String.valueOf(System.currentTimeMillis());
    }

    private void selectImage() {
        final CharSequence[] items = { "Kamera", "Koleksi Foto", "Batal" };
        AlertDialog.Builder builder = new AlertDialog.Builder(LaporActivity.this);
        builder.setTitle("Tambah Gambar");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Kamera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Koleksi Foto")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Batal")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                directory.mkdirs();
                File destination = new File(directory,
                        image_name + ".jpg");
                imagePath = destination.getAbsolutePath();
                Log.v("imagePath",imagePath);
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                showImg.setImageBitmap(thumbnail);
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                String[] projection = { MediaStore.MediaColumns.DATA };
                CursorLoader cursorLoader = new CursorLoader(this,selectedImageUri, projection, null, null,
                        null);
                Cursor cursor =cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String image_location = cursor.getString(column_index);
                Log.v("imagePath",imagePath);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(image_location, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(image_location, options);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(directory,
                        image_name + ".jpg");
                imagePath = destination.getAbsolutePath();
                Log.v("imagePath",imagePath);
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                showImg.setImageBitmap(bm);
            }
        }
    }

    class LaporProdukTask extends AsyncTask<String, String, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(LaporActivity.this);
            dialog.setMessage("Mengirim laporan...");
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();
        }

        protected String doInBackground(String... args) {

            imgUpload.uploadFile(LaporActivity.this,imagePath,upLoadServerUri);

            photo_url = "http://adikistanto.informatikaundip.com/cekhalal/img_produk/"+image_name+".jpg";

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("nama_produk", namaprodukStr));
            params.add(new BasicNameValuePair("merek_produk", merekprodukStr));
            params.add(new BasicNameValuePair("pabrik_produk", pabrikprodukStr));
            params.add(new BasicNameValuePair("gambar_produk", photo_url));

            // getting JSON Object
            // Note that create Post url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_tambah_produk, "POST", params);

            // check for success tag
            try {
                int success = json.getInt("success");
                Log.v("keterangan_sukses", ""+success);
                if (success == 1) {
                    // closing this screen
                    //finish();
                    return "OK";
                } else {
                    return "gagal_database";
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return "gagal_koneksi_or_exception";
            }
        }

        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            if (result.equalsIgnoreCase("gagal_database")){
                dialog.dismiss();
                Toast.makeText(LaporActivity.this, "Gagal mengirim laporan",  Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("gagal_koneksi_or_exception")){
                dialog.dismiss();
                Toast.makeText(LaporActivity.this, "Terjadi masalah! Silahkan cek koneksi Anda",  Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("OK")){
                dialog.dismiss();
                Toast.makeText(LaporActivity.this, "Berhasil menambah laporan",  Toast.LENGTH_SHORT).show();
                findViewById(R.id.scrollView).setVisibility(View.GONE);
                findViewById(R.id.linear).setVisibility(View.VISIBLE);
              //  Intent i = new Intent(LaporActivity.this, DaftarProdukActivity.class);
               // i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //i.putExtra("id_user", idpenggunaStr);
                //startActivity(i);
                //finish();
            }
        }
    }

}
