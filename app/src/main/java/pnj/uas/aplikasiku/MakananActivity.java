package pnj.uas.aplikasiku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pnj.uas.aplikasiku.adapter.AdapterMakanan;
import pnj.uas.aplikasiku.model.ModelMakanan;
import pnj.uas.aplikasiku.utils.Config;

public class MakananActivity extends AppCompatActivity {
    ListView listView;
    Button btnAdd;
    AdapterMakanan adapterMakanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makanan);

        listView = findViewById(R.id.listView);
        btnAdd =  findViewById(R.id.btnAdd);
        adapterMakanan = new AdapterMakanan(this, R.layout.item_list);
        listView.setAdapter(adapterMakanan);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ModelMakanan modelMakanan = (ModelMakanan) parent.getAdapter().getItem(position);

                Intent intent = new Intent(MakananActivity.this, DetailMakananActivity.class);
                intent.putExtra("id", modelMakanan.getId());
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(MakananActivity.this, AddMakananActivity.class);
                startActivity(inten);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ModelMakanan modelMakanan = (ModelMakanan) parent.getAdapter().getItem(position);
                final String id_makanan = modelMakanan.getId();
                new AlertDialog.Builder(MakananActivity.this)
                        .setTitle("Hapus Data Harga Makanan")
                        .setMessage("Apakah Yakin Ingin Menghapus Data Ini?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delete(id_makanan);
                            }
                        }).setNegativeButton(android.R.string.no, null).show();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_TambahData) {
            Intent intent = new Intent(MakananActivity.this, AddMakananActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config._LIST_MAKANAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RESPONSE", "" + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("makanan");
                            ArrayList<ModelMakanan> datas = new ArrayList<>();

                            for (int i=0; i<jsonArray.length(); i++){
                                JSONObject item = jsonArray.getJSONObject(i);

                                ModelMakanan modelMakanan = new ModelMakanan();
                                modelMakanan.setId(item.getString("id"));
                                modelMakanan.setNama(item.getString("name"));
                                modelMakanan.setHarga(item.getString("harga"));

                                datas.add(modelMakanan);
                            }
                            adapterMakanan.clear();
                            adapterMakanan.addAll(datas);
                            adapterMakanan.notifyDataSetChanged();

                        }catch (JSONException ex) {
                            Log.e("Error : ", ""+ex.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RESPONSE", ""+error.getMessage());
            }
        });

        queue.add(stringRequest);
    }

    void delete(String id_makanan) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config._DELETE_MAKANAN+"?id="
                +id_makanan,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RESPONSE",""+response);

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.getString("status").equals("OK")){
                                Toast.makeText(MakananActivity.this, jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                onResume();
                            }else{
                                Toast.makeText(MakananActivity.this, jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException ex){
                            Toast.makeText(MakananActivity.this, "" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MakananActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> parameter = new HashMap<>();
                parameter.put("id", getIntent().getExtras().getString("id", "0"));

                return parameter;
            }
        };

        requestQueue.add(stringRequest);
    }
}
