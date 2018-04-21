package com.ismek.ismekdonem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ismek.ismekdonem.util.SharedPreferenceUtils;

public class LoginActivity extends AppCompatActivity {

    private EditText edKullaniciAdi, edSifre;
    private CheckBox ckBeniHatirla;
    private Button btnGiris;

    private SharedPreferenceUtils sharedPreferenceUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferenceUtils = new SharedPreferenceUtils(LoginActivity.this);

        edKullaniciAdi = (EditText) findViewById(R.id.edKullaniciAdi);
        edSifre = (EditText) findViewById(R.id.edSifre);
        ckBeniHatirla = (CheckBox) findViewById(R.id.ckBeniHatirla);
        btnGiris = (Button) findViewById(R.id.btnGiris);

        if (sharedPreferenceUtils.getBoolanValue("benihatirla",false)){
            edKullaniciAdi.setText(sharedPreferenceUtils.getStringValue("kullanici",""));
            edSifre.setText(sharedPreferenceUtils.getStringValue("sifre",""));
            ckBeniHatirla.setChecked(true);
        }
    }

    public void giris(View view){
        if (TextUtils.isEmpty(edKullaniciAdi.getText().toString()) || TextUtils.isEmpty(edSifre.getText().toString())){
            Toast.makeText(getApplicationContext(),"Lütfen kullanıcı adı ver şifre alanını doldurun!",Toast.LENGTH_LONG).show();
            return;
        }
        if (ckBeniHatirla.isChecked()){
            sharedPreferenceUtils.setValue("kullanici",edKullaniciAdi.getText().toString());
            sharedPreferenceUtils.setValue("sifre",edSifre.getText().toString());
        }else{
            sharedPreferenceUtils.removeKey("kullanici");
            sharedPreferenceUtils.removeKey("sifre");
            sharedPreferenceUtils.removeKey("benihatirla");
        }
        sharedPreferenceUtils.setValue("benihatirla",ckBeniHatirla.isChecked());
        Intent i = new Intent(LoginActivity.this,LoggedActivity.class);
        startActivity(i);

    }


}
