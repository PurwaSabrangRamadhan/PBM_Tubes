package id.nyrat.tugas.activity;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import id.nyrat.tugas.R;

public class Form5Activity extends BaseActivity {

    public static final String URLINSERT = "http://up-pbm-tubes.apps.nyrat.id/create.php";

    Toolbar toolbar;
    Button btnPrevious, btnNext;
    Intent intentData;
    String intentName, intentSerialNumber, hasilFinal;
    String[] hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form5);

        findViews();
        initViews();
        initListeners();
    }

    @Override
    public void findViews() {
        toolbar = findViewById(R.id.toolbar);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        hasil = new String[5];
        hasil[0] = "Harddisk";
        hasil[1] = "Motherboard";
        hasil[2] = "SSD";
        hasil[3] = "RAM / Memory";
        hasil[4] = "Sistem Operasi";
        hasilFinal = hasil[new Random().nextInt((4) + 1)];
    }

    @Override
    public void initViews() {
        toolbar.setTitle("Form Pertanyaan 5");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        intentData = getIntent();
        if (!intentData.getStringExtra("name").isEmpty()){
            intentName = intentData.getStringExtra("name");
        } else {
            intentName = "No Name";
        }
        if (!intentData.getStringExtra("serialNumber").isEmpty()){
            intentSerialNumber = intentData.getStringExtra("serialNumber");
        } else {
            intentSerialNumber = "XXX";
        }
    }

    @Override
    public void initListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpan();
                Intent goResultActivity = new Intent(Form5Activity.this, ResultActivity.class);
                goResultActivity.putExtra("name", intentName);
                goResultActivity.putExtra("serialNumber", intentSerialNumber);
                goResultActivity.putExtra("kerusakan", hasilFinal);
                startActivity(goResultActivity);
            }
        });
    }

    private void simpan(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLINSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Form5Activity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Form5Activity.this, "Koneksi ke server gagal, cek settingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // posting parameter ke post url
                Map<String, String> params = new HashMap<String, String>();

                String kerusakan = "Kemungkinan kerusakan pada '" + hasilFinal + "'";
                params.put("nama", intentName);
                params.put("kerusakan", kerusakan);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
}