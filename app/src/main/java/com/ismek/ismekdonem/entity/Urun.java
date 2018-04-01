package com.ismek.ismekdonem.entity;

public class Urun {

    private String resimUrl;
	private String baslik;
	private double fiyat;
	private double eskiFiyat;
	private String kargoBilgisi;
	private int indirimYuzdesi;
	private int oylamaSayisi;
	private int oylamaYuzdesi;
	public String getResimUrl() {
		return resimUrl;
	}
	public void setResimUrl(String resimUrl) {
		this.resimUrl = resimUrl;
	}
	public String getBaslik() {
		return baslik;
	}
	public void setBaslik(String baslik) {
		this.baslik = baslik;
	}
	public double getFiyat() {
		return fiyat;
	}
	public void setFiyat(double fiyat) {
		this.fiyat = fiyat;
	}
	public double getEskiFiyat() {
		return eskiFiyat;
	}
	public void setEskiFiyat(double eskiFiyat) {
		this.eskiFiyat = eskiFiyat;
	}
	public String getKargoBilgisi() {
		return kargoBilgisi;
	}
	public void setKargoBilgisi(String kargoBilgisi) {
		this.kargoBilgisi = kargoBilgisi;
	}
	public int getIndirimYuzdesi() {
		return indirimYuzdesi;
	}
	public void setIndirimYuzdesi(int indirimYuzdesi) {
		this.indirimYuzdesi = indirimYuzdesi;
	}
	public int getOylamaSayisi() {
		return oylamaSayisi;
	}
	public void setOylamaSayisi(int oylamaSayisi) {
		this.oylamaSayisi = oylamaSayisi;
	}
	public int getOylamaYuzdesi() {
		return oylamaYuzdesi;
	}
	public void setOylamaYuzdesi(int oylamaYuzdesi) {
		this.oylamaYuzdesi = oylamaYuzdesi;
	}
	@Override
	public String toString() {
		return "Urun [resimUrl=" + resimUrl + ", baslik=" + baslik + ", fiyat=" + fiyat + ", eskiFiyat=" + eskiFiyat
				+ ", kargoBilgisi=" + kargoBilgisi + ", indirimYuzdesi=" + indirimYuzdesi + ", oylamaSayisi="
				+ oylamaSayisi + ", oylamaYuzdesi=" + oylamaYuzdesi + "]";
	}

}
