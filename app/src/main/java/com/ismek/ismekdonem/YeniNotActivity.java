package com.ismek.ismekdonem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ismek.ismekdonem.entity.Not;

import java.util.Date;

public class YeniNotActivity extends AppCompatActivity {

    private EditText edBaslik, edNot;
    private Button btnKaydet;

    private int RESULT_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_not);

        edBaslik = (EditText) findViewById(R.id.edBaslik);
        edNot = (EditText) findViewById(R.id.edNot);
        btnKaydet = (Button) findViewById(R.id.btnKaydet);

        if (getIntent() != null && getIntent().getParcelableExtra("not") != null){
            RESULT_CODE = 1001;
            Not not = getIntent().getParcelableExtra("not");
            edBaslik.setText(not.getBaslik());
            edNot.setText(not.getNot());
        }

    }

    public void onClickKaydet(View view){
        if (TextUtils.isEmpty(edBaslik.getText().toString()) || TextUtils.isEmpty(edNot.getText().toString())){
            Toast.makeText(this, "Lütfen boş alanları doldurun!", Toast.LENGTH_SHORT).show();
            return;
        }
        Not not = new Not();
        not.setBaslik(edBaslik.getText().toString());
        not.setNot(edNot.getText().toString());
        not.setOlusturulmaZamani(new Date());
        Intent returnIntent = new Intent();
        returnIntent.putExtra("not",not);
        setResult(RESULT_CODE,returnIntent);
        finish();
    }
}
