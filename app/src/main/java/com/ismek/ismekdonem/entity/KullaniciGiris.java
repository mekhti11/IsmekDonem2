package com.ismek.ismekdonem.entity;

import android.databinding.BaseObservable;

/**
 * Created by yahya on 14.04.2018.
 */

public class KullaniciGiris extends BaseObservable{

    private String kullaniciAdi;
    private String sifre;
    private boolean beniHatirla;

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public boolean isBeniHatirla() {
        return beniHatirla;
    }

    public void setBeniHatirla(boolean beniHatirla) {
        this.beniHatirla = beniHatirla;
    }
}
