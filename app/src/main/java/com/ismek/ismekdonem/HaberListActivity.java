package com.ismek.ismekdonem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ismek.ismekdonem.adapter.HaberAdapter;
import com.ismek.ismekdonem.entity.Haber;
import com.ismek.ismekdonem.listener.HaberListener;

import java.util.ArrayList;
import java.util.List;

public class HaberListActivity extends AppCompatActivity {

    private List<Haber> haberList;
    private RecyclerView list;
    private HaberAdapter haberAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haber_list);

        list = (RecyclerView)findViewById(R.id.list);

        createSampleData();

        haberAdapter = new HaberAdapter(HaberListActivity.this, haberList, new HaberListener() {
            @Override
            public void onDetail(View view, int position) {
                Haber haber = haberList.get(position);
                Intent i = new Intent(HaberListActivity.this,HaberDetayActivity.class);
                Bundle bundle = new Bundle();
                //bundle.putString("link",haber.getLink());
                bundle.putParcelable("haber",haber);
                i.putExtras(bundle);
                startActivity(i);
                //Toast.makeText(HaberListActivity.this, haberList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        list.setLayoutManager(layoutManager);
        list.setAdapter(haberAdapter);

    }

    public void createSampleData(){

        haberList = new ArrayList<>();

        Haber haber1 = new Haber();
        haber1.setImageUrl("https://cdn1.ntv.com.tr/gorsel/1Ssm2izmhU-ZlmgHbmNuSQ.jpg");
        haber1.setTitle("Günün spor gazeteleri (25 Mart 2018)");
        haber1.setSummary("Günün öne çıkan spor gelişmeleri neler? Fenerbahçe, Galatasaray, Beşiktaş ve Trabzonspor’da günün gelişmeleri neler? Transfer döneminde hangi futbolcuların transferi konuşuluyor? Gazetelerin spor manşetlerine, spor ve transfer haberlerine göz atın.");
        haber1.setPublished("25-03-2018");
        haber1.setLink("https://www.ntv.com.tr/galeri/spor/gunun-spor-gazeteleri-25-mart-2018,l_SCfxjcD0mmhb8EMt5ZFA");
        haber1.setAuthor("NTV");
        haberList.add(haber1);

        Haber haber = new Haber();
        haber.setImageUrl("https://cdn1.ntv.com.tr/gorsel/cgk9oBbnoU2zDiMJ2zQ9Lg.jpg");
        haber.setTitle("Hırvat futbolcu sahada hayatını kaybetti");
        haber.setSummary("Hırvat futbolcu Boban, maç sırasında göğsüne top gelmesinin ardından vefat etti");
        haber.setPublished("25-03-2018");
        haber.setLink("https://www.ntv.com.tr/spor/hirvat-futbolcu-sahada-hayatini-kaybetti,9Kl1OzD-qkuZotObVR-xFQ");
        haber.setAuthor("NTV");
        haberList.add(haber);
        haberList.add(haber);
        haberList.add(haber);
        haberList.add(haber);
        haberList.add(haber);
        haberList.add(haber);
        haberList.add(haber);
        haberList.add(haber);

    }
}
