package id.nyrat.tugas.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.nyrat.tugas.R;
import id.nyrat.tugas.adapter.Data;
import id.nyrat.tugas.adapter.DataAdapter;

public class listActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    public static final String URLTAMPIL = "http://up-pbm-tubes.apps.nyrat.id/view.php";
    public static final String URLDELETE = "http://up-pbm-tubes.apps.nyrat.id/delete.php";
    public static final String URLINSERT = "http://up-pbm-tubes.apps.nyrat.id/create.php";
    public static final String URLUBAH = "http://up-pbm-tubes.apps.nyrat.id/edit.php";

    Toolbar toolbar;
    ListView lvItems;
    AlertDialog.Builder dialog;
    SwipeRefreshLayout swipe;
    List<Data> itemlist = new ArrayList<Data>();
    DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        findViews();
        initViews();
        initListeners();
    }

    @Override
    public void findViews() {
        toolbar = findViewById(R.id.toolbar);
        lvItems = findViewById(R.id.lvItems);
        swipe = findViewById(R.id.swipe);
    }

    @Override
    public void initViews() {
        toolbar.setTitle("Riwayat Pemeriksaan");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        adapter = new DataAdapter(listActivity.this, itemlist);
        lvItems.setAdapter(adapter);
    }

    @Override
    public void initListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        swipe.setOnRefreshListener(this);
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
                itemlist.clear();
                adapter.notifyDataSetChanged();
                callVolley();
            }
        });
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemlist.get(position).getId();
                final CharSequence[] dialogitem = {"Delete"};
                dialog = new AlertDialog.Builder(listActivity.this);
                // dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // TODO Auto-generated method stub
                        if (which == 0) {
                            hapus(idx);
                        }
                    }
                }).show();
                return false;
            }
        });
    }

    @Override
    public void onRefresh() {
        // itemlist.clear();
        // adapter.notifyDataSetChanged();
        callVolley();
    }

    private void callVolley() {
        itemlist.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(URLTAMPIL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Data item = new Data();
                        item.setId(obj.getString("id"));
                        item.setNama(obj.getString("nama"));
                        item.setKerusakan(obj.getString("kerusakan"));

                        // menambah item ke array
                        itemlist.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifikasi adanya perubahan data pada adapter
                adapter.notifyDataSetChanged();
                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(listActivity.this, "Koneksi ke server gagal, cek koneksi internet Anda.", Toast.LENGTH_LONG).show();
                swipe.setRefreshing(false);
            }
        });

        // manambah request ke request queue
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jArr);
    }

    private void hapus(String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLDELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callVolley();
                        Toast.makeText(listActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(listActivity.this, "Koneksi ke server gagal, cek settingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // posting parameter ke post url
                Map<String, String> params = new HashMap<String, String>();

                params.put("id", id);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
}