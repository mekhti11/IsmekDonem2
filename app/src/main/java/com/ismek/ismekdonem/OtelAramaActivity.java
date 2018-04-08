package com.ismek.ismekdonem;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OtelAramaActivity extends AppCompatActivity {

    private int DP_TYPE = 1;

    private EditText edLokasyon, edBasTarihi, edBitTarihi;
    private Button btnAra;

    Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otel_arama);

        context = this;

        edLokasyon = (EditText) findViewById(R.id.edLokasyon);
        edBasTarihi = (EditText) findViewById(R.id.edBasTarihi);
        edBitTarihi = (EditText) findViewById(R.id.edBitTarihi);
        btnAra = (Button) findViewById(R.id.btnAra);

        edBasTarihi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DP_TYPE = 1;
                new DatePickerDialog(context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edBitTarihi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DP_TYPE = 2;
                new DatePickerDialog(context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnAra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OtelAramaActivity.this,OtelListActivity.class);
                i.putExtra("lokasyon",edLokasyon.getText().toString());
                i.putExtra("basTarih",edBasTarihi.getText().toString());
                i.putExtra("bitTarih",edBitTarihi.getText().toString());
                startActivity(i);
            }
        });

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }

        };
    }

    private void updateDate() {
        if (DP_TYPE ==1)
            edBasTarihi.setText(sdf.format(myCalendar.getTime()));
        else
            edBitTarihi.setText(sdf.format(myCalendar.getTime()));
    }
}
