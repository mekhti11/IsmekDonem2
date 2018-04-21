package com.ismek.ismekdonem;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.ismek.ismekdonem.entity.Oda;
import com.ismek.ismekdonem.entity.Otel;
import com.ismek.ismekdonem.entity.OtelDetail;
import com.ismek.ismekdonem.service.DataService;
import com.squareup.picasso.Picasso;

public class OtelDetayActivity extends AppCompatActivity {

    private Otel otel;
    OtelDetail otelDetails;
    private SliderLayout sliderLayout;
    private TextView txtOtelAdi;
    private LinearLayout llOdaList;
    LayoutInflater inflater;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otel_detay);

        inflater = getLayoutInflater();

        sliderLayout = (SliderLayout)findViewById(R.id.slider);
        txtOtelAdi = (TextView) findViewById(R.id.txtOtelAdi);
        llOdaList = (LinearLayout) findViewById(R.id.llOdaList);
        webView = (WebView) findViewById(R.id.webview);

        otel = getIntent().getParcelableExtra("otel");

        txtOtelAdi.setText(otel.getOtelAdi());

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
            otelDetails = otelDetail;
            createSlider();
            createRooms();
            StringBuilder sb = new StringBuilder();

            sb.append(otelDetails.getHtmlKonaklamaOzellikleri() + "<br/><br/>");
            sb.append(otelDetails.getHtmlKonumBilgileri() + "<br/><br/>");
            sb.append(otelDetails.getHtmlOtelOzellikleri() + "<br/><br/>");
            sb.append(otelDetails.getHtmlGuvencePaketi() + "<br/><br/>");
            sb.append(otelDetails.getHtmlCocukAktiviteleri() + "<br/><br/>");
            sb.append(otelDetails.getHtmlUcretliAktiviteler() + "<br/><br/>");
            sb.append(otelDetails.getHtmlUcretsizAktiviteler() + "<br/><br/>");
            webView.loadDataWithBaseURL(null, sb.toString(), "text/html", "UTF-8", null);

        }
    }

    public void createSlider(){
        if (otelDetails != null && otelDetails.getImageList() != null && otelDetails.getImageList().size() > 0){
            for (int i=0; i<otelDetails.getImageList().size(); i++){
                DefaultSliderView view = new DefaultSliderView(OtelDetayActivity.this);
                view.image(otelDetails.getImageList().get(i)).setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {

                    }
                }).setScaleType(BaseSliderView.ScaleType.Fit);
                sliderLayout.addSlider(view);
            }
        }
    }

    public void createRooms(){
        if (otelDetails != null && otelDetails.getOdaList() != null && otelDetails.getOdaList().size() > 0){
            for (int i =0; i<otelDetails.getOdaList().size();i++){
                Oda oda = otelDetails.getOdaList().get(i);
                View view = inflater.inflate(R.layout.cell_oda, llOdaList, false);
                final ExpandableRelativeLayout expOdaKapasitesi = (ExpandableRelativeLayout) view.findViewById(R.id.expOdaKapasitesi);
                TextView txtOdaAdi = (TextView)view.findViewById(R.id.txtOdaAdi);
                ImageView imgOda = (ImageView) view.findViewById(R.id.imgOda);
                TextView txtOdaKapasitesi = (TextView) view.findViewById(R.id.txtOdaKapasitesi);
                TextView txtOdaKapasitesiLink = (TextView) view.findViewById(R.id.txtOdaKapasitesiLink);
                TextView txtKisiBasiFiyat = (TextView) view.findViewById(R.id.txtKisiBasiFiyat);
                TextView txtToplamOdaFiyat = (TextView) view.findViewById(R.id.txtToplamOdaFiyat);

                expOdaKapasitesi.setExpanded(false);
                txtOdaKapasitesiLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (expOdaKapasitesi.isExpanded()){
                            expOdaKapasitesi.collapse();
                        }else{
                            expOdaKapasitesi.expand();
                        }
                    }
                });

                txtOdaAdi.setText(oda.getName());
                txtOdaKapasitesi.setText(oda.getOdaKapasitesi());
                txtKisiBasiFiyat.setText(oda.getKisiBasiFiyat());
                txtToplamOdaFiyat.setText(oda.getToplamOdaFiyati());
                Picasso.with(OtelDetayActivity.this).load(oda.getImage()).into(imgOda);



                llOdaList.addView(view);
            }
        }
    }







}
