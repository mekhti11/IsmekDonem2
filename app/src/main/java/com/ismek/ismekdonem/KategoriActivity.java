package com.ismek.ismekdonem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ismek.ismekdonem.adapter.KategoriAdapter;
import com.ismek.ismekdonem.entity.Kategori;
import com.ismek.ismekdonem.listener.HaberListener;

import java.util.ArrayList;
import java.util.List;

public class KategoriActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KategoriAdapter kategoriAdapter;
    private List<Kategori> kategoriList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        kategoriListeDoldur();

        kategoriAdapter = new KategoriAdapter(kategoriList, new HaberListener() {
            @Override
            public void onDetail(View view, int position) {
                Kategori kategori = kategoriList.get(position);
                Intent i = new Intent(getApplicationContext(),UrunListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("kategori",kategori);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(kategoriAdapter);

    }

    public void kategoriListeDoldur(){
        kategoriList = new ArrayList<>();

        Kategori k1 = new Kategori();
        k1.setAdi("Giyim & Ayakkabı");
        k1.setLink("https://www.n11.com/ayakkabi-ve-canta");
        kategoriList.add(k1);

        Kategori k2 = new Kategori();
        k2.setAdi("Elektronik");
        k2.setLink("https://www.n11.com/telefon-ve-aksesuarlari");
        kategoriList.add(k2);

        Kategori k3 = new Kategori();
        k3.setAdi("Ev ve Yaşam");
        k3.setLink("https://www.n11.com/mobilya");
        kategoriList.add(k3);
    }
}
