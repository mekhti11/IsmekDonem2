package com.ismek.ismekdonem;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ismek.ismekdonem.entity.Otel;
import com.ismek.ismekdonem.entity.OtelDetail;
import com.ismek.ismekdonem.service.DataService;

public class OtelDetayActivity extends AppCompatActivity {

    private Otel otel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otel_detay);

        otel = getIntent().getParcelableExtra("otel");

        new AsyncGetOtelDetail().execute(otel.getOtelDetay());

    }

    private class AsyncGetOtelDetail extends AsyncTask<String,Void,OtelDetail>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected OtelDetail doInBackground(String... strings) {
            DataService dataService = new DataService();
            return dataService.getOtelDetail(strings[0]);
        }

        @Override
        protected void onPostExecute(OtelDetail otelDetail) {
            super.onPostExecute(otelDetail);
            Log.d("ISMEKKK",otelDetail.toString());
        }
    }
}
