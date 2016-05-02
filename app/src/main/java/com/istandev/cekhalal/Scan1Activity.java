package com.istandev.cekhalal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import me.sudar.zxingorient.ZxingOrient;
import me.sudar.zxingorient.ZxingOrientResult;


public class Scan1Activity extends AppCompatActivity implements View.OnClickListener{

    private final String LOG_TAG = Scan1Activity.class.getSimpleName();

    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = null;
        i = new Intent(getBaseContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan1);
        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);

        /*Intent intent = new Intent(getApplicationContext(),CaptureActivity.class);
        intent.setAction("com.google.zxing.client.android.SCAN");
        intent.putExtra("SAVE_HISTORY", false);
        startActivityForResult(intent, 0);
*/

        new ZxingOrient(Scan1Activity.this).initiateScan();

        //findViewById(R.id.scan_button).setO
    }
    public void onClick(View v){
        if(v.getId()==R.id.scan_button){
            new ZxingOrient(Scan1Activity.this).initiateScan();

            /*IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();*/
            /*Intent intent = new Intent(getApplicationContext(),CaptureActivity.class);
            intent.setAction("com.google.zxing.client.android.SCAN");
            intent.putExtra("SAVE_HISTORY", false);
            startActivityForResult(intent, 0);
*/
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        /*if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                Log.v(LOG_TAG, "contents: " + contents);
                contentTxt.setText("CONTENT: " + contents);

            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
                Log.v(LOG_TAG, "RESULT_CANCELED");
                Toast toast = Toast.makeText(getApplicationContext(),
                        "No scan data received!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

*/        ZxingOrientResult scanningResult = ZxingOrient.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
