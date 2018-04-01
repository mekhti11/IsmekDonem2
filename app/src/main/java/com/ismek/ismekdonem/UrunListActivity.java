package com.ismek.ismekdonem;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ismek.ismekdonem.entity.Kategori;
import com.ismek.ismekdonem.entity.Urun;
import com.ismek.ismekdonem.service.DataService;

import java.util.List;

public class UrunListActivity extends AppCompatActivity {

    Kategori kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_list);

        Bundle bundle = getIntent().getExtras();
        kategori = bundle.getParcelable("kategori");

        AsyncGetUrunList asyncGetUrunList = new AsyncGetUrunList();
        asyncGetUrunList.execute();
    }

    private class AsyncGetUrunList extends AsyncTask<Void,Void,List<Urun>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Urun> doInBackground(Void... voids) {
            DataService dataService = new DataService();
            return dataService.findUrunByLink(kategori.getLink());
        }

        @Override
        protected void onPostExecute(List<Urun> uruns) {
            super.onPostExecute(uruns);
            Log.d("ISMEK",uruns.toString());
        }
    }
}
