package com.ismek.ismekdonem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_sample);
        Log.d("ISMEK","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ISMEK","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ISMEK","onResume");
    }
}
