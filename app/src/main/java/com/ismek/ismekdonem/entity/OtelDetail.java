package com.ismek.ismekdonem.entity;

import java.util.List;

public class OtelDetail {
	
	private List<String> imageList;
	private String htmlKonaklamaOzellikleri;
	private String htmlGuvencePaketi;
	private String htmlKonumBilgileri;
	private String htmlOtelOzellikleri;
	private String htmlUcretsizAktiviteler;
	private String htmlUcretliAktiviteler;
	private String htmlCocukAktiviteleri;
	private List<Oda> odaList;
	
	public List<String> getImageList() {
		return imageList;
	}
	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
	public String getHtmlKonaklamaOzellikleri() {
		return htmlKonaklamaOzellikleri;
	}
	public void setHtmlKonaklamaOzellikleri(String htmlKonaklamaOzellikleri) {
		this.htmlKonaklamaOzellikleri = htmlKonaklamaOzellikleri;
	}
	public String getHtmlGuvencePaketi() {
		return htmlGuvencePaketi;
	}
	public void setHtmlGuvencePaketi(String htmlGuvencePaketi) {
		this.htmlGuvencePaketi = htmlGuvencePaketi;
	}
	public String getHtmlKonumBilgileri() {
		return htmlKonumBilgileri;
	}
	public void setHtmlKonumBilgileri(String htmlKonumBilgileri) {
		this.htmlKonumBilgileri = htmlKonumBilgileri;
	}
	public String getHtmlOtelOzellikleri() {
		return htmlOtelOzellikleri;
	}
	public void setHtmlOtelOzellikleri(String htmlOtelOzellikleri) {
		this.htmlOtelOzellikleri = htmlOtelOzellikleri;
	}
	public String getHtmlUcretsizAktiviteler() {
		return htmlUcretsizAktiviteler;
	}
	public void setHtmlUcretsizAktiviteler(String htmlUcretsizAktiviteler) {
		this.htmlUcretsizAktiviteler = htmlUcretsizAktiviteler;
	}
	public String getHtmlUcretliAktiviteler() {
		return htmlUcretliAktiviteler;
	}
	public void setHtmlUcretliAktiviteler(String htmlUcretliAktiviteler) {
		this.htmlUcretliAktiviteler = htmlUcretliAktiviteler;
	}
	public String getHtmlCocukAktiviteleri() {
		return htmlCocukAktiviteleri;
	}
	public void setHtmlCocukAktiviteleri(String htmlCocukAktiviteleri) {
		this.htmlCocukAktiviteleri = htmlCocukAktiviteleri;
	}
	public List<Oda> getOdaList() {
		return odaList;
	}
	public void setOdaList(List<Oda> odaList) {
		this.odaList = odaList;
	}

}
