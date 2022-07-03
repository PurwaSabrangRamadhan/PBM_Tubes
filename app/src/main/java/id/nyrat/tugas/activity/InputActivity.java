package id.nyrat.tugas.activity;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import id.nyrat.tugas.R;

public class InputActivity extends BaseActivity {

    Toolbar toolbar;
    Button btnNext;
    EditText edtName, edtSerialNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        findViews();
        initViews();
        initListeners();
    }

    @Override
    public void findViews() {
        toolbar = findViewById(R.id.toolbar);
        btnNext = findViewById(R.id.btnNext);
        edtName = findViewById(R.id.edtName);
        edtSerialNumber = findViewById(R.id.edtSerialNumber);
    }

    @Override
    public void initViews() {
        toolbar.setTitle("Form Nama");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar showSuccess = Snackbar.make(btnNext, "Input Success", Snackbar.LENGTH_SHORT);
//                showSuccess.setAction("Close", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        showSuccess.dismiss();
//                    }
//                });
//                showSuccess.show();
                Intent goForm1Activity = new Intent(InputActivity.this, Form1Activity.class);
                goForm1Activity.putExtra("name", edtName.getText().toString());
                goForm1Activity.putExtra("serialNumber", edtSerialNumber.getText().toString());
                startActivity(goForm1Activity);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText( this, "Pengisian dibatalkan", Toast.LENGTH_SHORT).show();
    }
}