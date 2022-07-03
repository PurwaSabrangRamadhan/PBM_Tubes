package id.nyrat.tugas.activity;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.nyrat.tugas.R;

public class Form4Activity extends BaseActivity {

    Toolbar toolbar;
    Button btnPrevious, btnNext;
    Intent intentData;
    String intentName, intentSerialNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form4);

        findViews();
        initViews();
        initListeners();
    }

    @Override
    public void findViews() {
        toolbar = findViewById(R.id.toolbar);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
    }

    @Override
    public void initViews() {
        toolbar.setTitle("Form Pertanyaan 4");
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
                Intent goForm5Activity = new Intent(Form4Activity.this, Form5Activity.class);
                goForm5Activity.putExtra("name", intentName);
                goForm5Activity.putExtra("serialNumber", intentSerialNumber);
                startActivity(goForm5Activity);
            }
        });
    }
}