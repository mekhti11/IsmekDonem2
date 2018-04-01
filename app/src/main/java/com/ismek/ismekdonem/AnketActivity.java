package com.ismek.ismekdonem;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AnketActivity extends AppCompatActivity {

    private RadioGroup rg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anket);

        rg1 = (RadioGroup)findViewById(R.id.rg1);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton rb = (RadioButton)radioGroup.findViewById(i);
                Toast.makeText(AnketActivity.this, "Puan : " + rb.getTag(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
