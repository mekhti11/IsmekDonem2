package com.ismek.ismekdonem;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ismek.ismekdonem.entity.Soru;

import java.util.ArrayList;
import java.util.List;

public class DinamikAnketActivity extends AppCompatActivity {

    private LinearLayout llSoru;

    private List<Soru> soruList;

    private int[] puanArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinamik_anket);

        soruList = new ArrayList<>();
        llSoru = (LinearLayout)findViewById(R.id.llSoru);

        Soru soru1 = new Soru();
        soru1.setSoru("Soru1");
        soru1.setSecenek1("İyi");
        soru1.setSecenek1Puan("100");
        soru1.setSecenek2("Orta");
        soru1.setSecenek2Puan("50");
        soru1.setSecenek3("Kötü");
        soru1.setSecenek3Puan("0");
        soruList.add(soru1);

        Soru soru2 = new Soru();
        soru2.setSoru("Soru2");
        soru2.setSecenek1("İyi");
        soru2.setSecenek1Puan("100");
        soru2.setSecenek2("Orta");
        soru2.setSecenek2Puan("50");
        soru2.setSecenek3("Kötü");
        soru2.setSecenek3Puan("0");
        soruList.add(soru2);

        Soru soru3 = new Soru();
        soru3.setSoru("Soru3");
        soru3.setSecenek1("İyi");
        soru3.setSecenek1Puan("100");
        soru3.setSecenek2("Orta");
        soru3.setSecenek2Puan("50");
        soru3.setSecenek3("Kötü");
        soru3.setSecenek3Puan("0");
        soruList.add(soru3);

        Soru soru4 = new Soru();
        soru4.setSoru("Soru4");
        soru4.setSecenek1("İyi");
        soru4.setSecenek1Puan("100");
        soru4.setSecenek2("Orta");
        soru4.setSecenek2Puan("50");
        soru4.setSecenek3("Kötü");
        soru4.setSecenek3Puan("0");
        soruList.add(soru4);

        puanArray = new int[soruList.size()];

        LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < soruList.size(); i++) {
            final int index = i;
            Soru soru = soruList.get(i);
            View view = inflater.inflate(R.layout.soru, llSoru, false);
            TextView txtSoru = (TextView) view.findViewById(R.id.txtSoru);
            txtSoru.setText(soru.getSoru());

            RadioButton rb1 = (RadioButton)view.findViewById(R.id.rb1);
            RadioButton rb2 = (RadioButton)view.findViewById(R.id.rb2);
            RadioButton rb3 = (RadioButton)view.findViewById(R.id.rb3);
            rb1.setText(soru.getSecenek1());
            rb2.setText(soru.getSecenek2());
            rb3.setText(soru.getSecenek3());

            rb1.setTag(soru.getSecenek1Puan());
            rb2.setTag(soru.getSecenek2Puan());
            rb3.setTag(soru.getSecenek3Puan());

            RadioGroup rg = (RadioGroup) view.findViewById(R.id.rg);
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                    RadioButton rb = (RadioButton)radioGroup.findViewById(id);
                    puanArray[index]= Integer.parseInt(rb.getTag().toString());
                }
            });

            llSoru.addView(view);
        }
    }

    public void onClickHesapla(View view){
        int total = 0;
        for (int i = 0; i < puanArray.length; i++) {
            total += puanArray[i];
        }
        Toast.makeText(this, "Total Puan : " + total, Toast.LENGTH_LONG).show();
    }
}
