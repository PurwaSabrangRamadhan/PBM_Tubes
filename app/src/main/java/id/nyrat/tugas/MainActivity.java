package id.nyrat.tugas;

import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import id.nyrat.tugas.activity.BaseActivity;
import id.nyrat.tugas.activity.DashboardActivity;

public class MainActivity extends BaseActivity {

    Toolbar toolbar;
    Button btnLogin;
    EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();
        initListeners();
    }

    @Override
    public void findViews() {
        toolbar = findViewById(R.id.toolbar);
        btnLogin = findViewById(R.id.btnLogin);
        edtUsername = findViewById(R.id.edtSerialNumber);
        edtPassword = findViewById(R.id.edtPassword);
    }

    @Override
    public void initViews() {
        setSupportActionBar(toolbar);
    }

    @Override
    public void initListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goDashboard = new Intent(MainActivity.this, DashboardActivity.class);
                goDashboard.putExtra("username", edtUsername.getText().toString());
                goDashboard.putExtra("password", edtPassword.getText().toString());
                startActivity(goDashboard);
            }
        });
    }
}