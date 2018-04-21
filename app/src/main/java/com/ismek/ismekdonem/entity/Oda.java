package com.ismek.ismekdonem.entity;

import java.util.List;

public class Oda {
	
	private String name;
	private String hostelType;
	private String image;
	private String kisiBasiFiyat;
	private String kisiBasiIndirimliFiyat;
	private String toplamOdaFiyati;
	private String odaKapasitesi;
	private String indirim;
	private List<OdaGunFiyatBilgisi> odaGunFiyatBilgisiList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHostelType() {
		return hostelType;
	}
	public void setHostelType(String hostelType) {
		this.hostelType = hostelType;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getKisiBasiFiyat() {
		return kisiBasiFiyat;
	}
	public void setKisiBasiFiyat(String kisiBasiFiyat) {
		this.kisiBasiFiyat = kisiBasiFiyat;
	}
	public String getKisiBasiIndirimliFiyat() {
		return kisiBasiIndirimliFiyat;
	}
	public void setKisiBasiIndirimliFiyat(String kisiBasiIndirimliFiyat) {
		this.kisiBasiIndirimliFiyat = kisiBasiIndirimliFiyat;
	}
	public String getToplamOdaFiyati() {
		return toplamOdaFiyati;
	}
	public void setToplamOdaFiyati(String toplamOdaFiyati) {
		this.toplamOdaFiyati = toplamOdaFiyati;
	}
	public String getOdaKapasitesi() {
		return odaKapasitesi;
	}
	public void setOdaKapasitesi(String odaKapasitesi) {
		this.odaKapasitesi = odaKapasitesi;
	}
	public String getIndirim() {
		return indirim;
	}
	public void setIndirim(String indirim) {
		this.indirim = indirim;
	}
	public List<OdaGunFiyatBilgisi> getOdaGunFiyatBilgisiList() {
		return odaGunFiyatBilgisiList;
	}
	public void setOdaGunFiyatBilgisiList(List<OdaGunFiyatBilgisi> odaGunFiyatBilgisiList) {
		this.odaGunFiyatBilgisiList = odaGunFiyatBilgisiList;
	}

}
