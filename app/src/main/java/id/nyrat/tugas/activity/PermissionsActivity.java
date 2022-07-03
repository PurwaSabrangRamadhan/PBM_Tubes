package id.nyrat.tugas.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.switchmaterial.SwitchMaterial;

import id.nyrat.tugas.R;

public class PermissionsActivity extends BaseActivity {

    Toolbar toolbar;
    SwitchMaterial switchPermissionFile, switchPermissionCamera, switchPermissionLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        findViews();
        initViews();
        initListeners();

        permissionCheck();
    }

    @Override
    public void findViews() {
        toolbar = findViewById(R.id.toolbar);
        switchPermissionFile = findViewById(R.id.switchPermissionFile);
        switchPermissionCamera = findViewById(R.id.switchPermissionCamera);
        switchPermissionLocation = findViewById(R.id.switchPermissionLocation);
    }

    @Override
    public void initViews() {
        toolbar.setTitle("Permissions");
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
        switchPermissionFile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    if (ContextCompat.checkSelfPermission(PermissionsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(PermissionsActivity.this, "Perizinan sudah pernah disetujui.", Toast.LENGTH_SHORT).show();
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            new MaterialAlertDialogBuilder(PermissionsActivity.this)
                                    .setMessage("Anda sudah pernah menolak memberikan perizinan ini, harap berikan perizinan secara manual di pengaturan.")
                                    .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.cancel())
                                    .show();
                            switchPermissionFile.setChecked(false);
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                            } else {
                                Toast.makeText(PermissionsActivity.this, "Perizinan disetujui.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(PermissionsActivity.this, "Perizinan disetujui.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        switchPermissionCamera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    if (ContextCompat.checkSelfPermission(PermissionsActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(PermissionsActivity.this, "Perizinan sudah pernah disetujui.", Toast.LENGTH_SHORT).show();
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            new MaterialAlertDialogBuilder(PermissionsActivity.this)
                                    .setMessage("Anda sudah pernah menolak memberikan perizinan ini, harap berikan perizinan secara manual di pengaturan.")
                                    .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.cancel())
                                    .show();
                            switchPermissionCamera.setChecked(false);
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                requestPermissions(new String[]{Manifest.permission.CAMERA},2);
                            } else {
                                Toast.makeText(PermissionsActivity.this, "Perizinan disetujui.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(PermissionsActivity.this, "Perizinan disetujui.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        switchPermissionLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    if (ContextCompat.checkSelfPermission(PermissionsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(PermissionsActivity.this, "Perizinan sudah pernah disetujui.", Toast.LENGTH_SHORT).show();
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                            new MaterialAlertDialogBuilder(PermissionsActivity.this)
                                    .setMessage("Anda sudah pernah menolak memberikan perizinan ini, harap berikan perizinan secara manual di pengaturan.")
                                    .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.cancel())
                                    .show();
                            switchPermissionLocation.setChecked(false);
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},3);
                            } else {
                                Toast.makeText(PermissionsActivity.this, "Perizinan disetujui.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(PermissionsActivity.this, "Perizinan disetujui.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                switchPermissionFile.setChecked(true);
            } else {
                Toast.makeText(PermissionsActivity.this, "Perizinan ditolak.", Toast.LENGTH_SHORT).show();
                switchPermissionFile.setChecked(false);
            }
        } else if (requestCode == 2) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                switchPermissionCamera.setChecked(true);
            } else {
                Toast.makeText(PermissionsActivity.this, "Perizinan ditolak.", Toast.LENGTH_SHORT).show();
                switchPermissionCamera.setChecked(false);
            }
        } else if (requestCode == 3) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                switchPermissionLocation.setChecked(true);
            } else {
                Toast.makeText(PermissionsActivity.this, "Perizinan ditolak.", Toast.LENGTH_SHORT).show();
                switchPermissionLocation.setChecked(false);
            }
        }
    }

    public void permissionCheck(){
        if (ContextCompat.checkSelfPermission(PermissionsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            switchPermissionFile.setChecked(true);
        }
        if (ContextCompat.checkSelfPermission(PermissionsActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            switchPermissionCamera.setChecked(true);
        }
        if (ContextCompat.checkSelfPermission(PermissionsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            switchPermissionLocation.setChecked(true);
        }
    }
}