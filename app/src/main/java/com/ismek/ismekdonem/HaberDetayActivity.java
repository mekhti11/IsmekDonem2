package com.ismek.ismekdonem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.ismek.ismekdonem.entity.Haber;

public class HaberDetayActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haber_detay);

        Bundle bundle = getIntent().getExtras();
        Haber haber = bundle.getParcelable("haber");
        webview = (WebView)findViewById(R.id.webview);

        Toast.makeText(this, haber.toString(), Toast.LENGTH_LONG).show();
        webview.loadUrl(haber.getLink());
    }
}
