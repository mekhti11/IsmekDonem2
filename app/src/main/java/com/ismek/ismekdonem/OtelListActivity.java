package com.ismek.ismekdonem;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ismek.ismekdonem.adapter.OtelListAdapter;
import com.ismek.ismekdonem.entity.Otel;
import com.ismek.ismekdonem.listener.HaberListener;
import com.ismek.ismekdonem.service.DataService;

import java.util.List;

public class OtelListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Otel> otels;
    private ProgressDialog progressDialog;

    private String lokasyon, basTarih, bitTarih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otel_list);

        lokasyon = getIntent().getStringExtra("lokasyon");
        basTarih = getIntent().getStringExtra("basTarih");
        bitTarih = getIntent().getStringExtra("bitTarih");

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        progressDialog = new ProgressDialog(OtelListActivity.this);
        progressDialog.setMessage("Lütfen bekleyiniz...");
        progressDialog.setTitle("Yükleniyor");
        progressDialog.setCancelable(false);

        new AsyncGetOtels().execute("https://www.tatilsepeti.com/"+lokasyon+"-otelleri?ara=oda:2;tarih:"+basTarih+","+bitTarih+";click:true");
    }

    public class AsyncGetOtels extends AsyncTask<String,Void,List<Otel>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected List<Otel> doInBackground(String... strings) {
            DataService dataService = new DataService();
            return dataService.getOtelsByLink(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Otel> otels) {
            super.onPostExecute(otels);
            progressDialog.dismiss();
            OtelListAdapter adapter = new OtelListAdapter(OtelListActivity.this, otels, new HaberListener() {
                @Override
                public void onDetail(View view, int position) {

                }
            });

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

}
