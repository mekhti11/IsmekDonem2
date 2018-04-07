package com.ismek.ismekdonem.entity;

import java.util.List;

public class Otel {

    private String imageUrl;
	private String otelAdi;
	private String lokasyon;
	private String promosyon;
	private String puan;
	private String hostelType;
	private String fiyat;
	private String indirimliFiyat;
	private String fiyatBilgi;
	private List<String> otelOzellikleri;
	private String otelDetay;
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getOtelAdi() {
		return otelAdi;
	}
	public void setOtelAdi(String otelAdi) {
		this.otelAdi = otelAdi;
	}
	public String getPromosyon() {
		return promosyon;
	}
	public void setPromosyon(String promosyon) {
		this.promosyon = promosyon;
	}
	public String getHostelType() {
		return hostelType;
	}
	public void setHostelType(String hostelType) {
		this.hostelType = hostelType;
	}
	public String getFiyatBilgi() {
		return fiyatBilgi;
	}
	public void setFiyatBilgi(String fiyatBilgi) {
		this.fiyatBilgi = fiyatBilgi;
	}
	public List<String> getOtelOzellikleri() {
		return otelOzellikleri;
	}
	public void setOtelOzellikleri(List<String> otelOzellikleri) {
		this.otelOzellikleri = otelOzellikleri;
	}
	public String getOtelDetay() {
		return otelDetay;
	}
	public void setOtelDetay(String otelDetay) {
		this.otelDetay = otelDetay;
	}
	public String getLokasyon() {
		return lokasyon;
	}
	public void setLokasyon(String lokasyon) {
		this.lokasyon = lokasyon;
	}

	public String getPuan() {
		return puan;
	}

	public void setPuan(String puan) {
		this.puan = puan;
	}

	public String getFiyat() {
		return fiyat;
	}

	public void setFiyat(String fiyat) {
		this.fiyat = fiyat;
	}

	public String getIndirimliFiyat() {
		return indirimliFiyat;
	}

	public void setIndirimliFiyat(String indirimliFiyat) {
		this.indirimliFiyat = indirimliFiyat;
	}

	@Override
	public String toString() {
		return "Otel [imageUrl=" + imageUrl + ", otelAdi=" + otelAdi + ", lokasyon=" + lokasyon + ", promosyon=" + promosyon + ", puan=" + puan + ", hostelType=" + hostelType
				+ ", fiyat=" + fiyat + ", indirimliFiyat=" + indirimliFiyat + ", fiyatBilgi=" + fiyatBilgi
				+ ", otelOzellikleri=" + otelOzellikleri + ", otelDetay=" + otelDetay + "]";
	}

}