package id.nyrat.tugas.activity;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.nyrat.tugas.R;

public class ResultActivity extends BaseActivity {

    Toolbar toolbar;
    Intent intentData;
    String intentName, intentSerialNumber, intentKerusakan, hasilFinal;
    TextView tvName, tvSerialNumber, tvHasilDescription;
    Button btnMainMenu, btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findViews();
        initViews();
        initListeners();
    }

    @Override
    public void findViews() {
        toolbar = findViewById(R.id.toolbar);
        tvName = findViewById(R.id.tvName);
        tvSerialNumber = findViewById(R.id.tvSerialNumber);
        tvHasilDescription = findViewById(R.id.tvHasilDescription);
        btnMainMenu = findViewById(R.id.btnMainMenu);
        btnHistory = findViewById(R.id.btnHistory);
    }

    @Override
    public void initViews() {
        toolbar.setTitle("Hasil Pemeriksaan");
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
        if (!intentData.getStringExtra("kerusakan").isEmpty()){
            intentKerusakan = intentData.getStringExtra("kerusakan");
        } else {
            intentKerusakan = "Kerusakan Unknown";
        }
        tvName.setText(intentName);
        tvSerialNumber.setText(intentSerialNumber);
        hasilFinal = "Perangkat anda diperkirakan memiliki kerusakan pada '" + intentKerusakan + "'";
        tvHasilDescription.setText(hasilFinal);
    }

    @Override
    public void initListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goDashboardActivity = new Intent(ResultActivity.this, DashboardActivity.class);
                startActivity(goDashboardActivity);
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goListActivity = new Intent(ResultActivity.this, listActivity.class);
                startActivity(goListActivity);
            }
        });
    }
}