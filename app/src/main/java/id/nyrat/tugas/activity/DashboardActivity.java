package id.nyrat.tugas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import id.nyrat.tugas.R;

public class DashboardActivity extends BaseActivity {

    Toolbar toolbar;
    Button btnInputItem, btnViewListItem;
    Intent intentData;
    TextView txtvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        findViews();
        initViews();
        initListeners();
    }

    @Override
    public void findViews() {
        toolbar = findViewById(R.id.toolbar);
        btnInputItem = findViewById(R.id.btnMainMenu);
        btnViewListItem = findViewById(R.id.btnHistory);
        txtvUsername = findViewById(R.id.tvName);
    }

    @Override
    public void initViews() {
        toolbar.setTitle("Dashboard");
        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//        intentData = getIntent();
//        if (!intentData.getStringExtra("username").isEmpty()){
//            txtvUsername.setText(intentData.getStringExtra("username"));
//        } else {
//            txtvUsername.setText(R.string.guest);
//        }
    }

    @Override
    public void initListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnInputItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInputBarang = new Intent(DashboardActivity.this, InputActivity.class);
                startActivity(goInputBarang);
            }
        });

        btnViewListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goListActivity = new Intent(DashboardActivity.this, listActivity.class);
                startActivity(goListActivity);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuItemSetting) {
            // Toast.makeText(this, "menuItemSetting Selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.subMenuItemPermission) {
            Intent goPermissionsActivity = new Intent(DashboardActivity.this, PermissionsActivity.class);
            startActivity(goPermissionsActivity);
        } else if (item.getItemId() == R.id.menuItemAbout) {
            Intent goAboutActivity = new Intent(DashboardActivity.this, AboutActivity.class);
            startActivity(goAboutActivity);
        }
        return super.onOptionsItemSelected(item);
    }
}