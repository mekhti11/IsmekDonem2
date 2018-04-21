package com.ismek.ismekdonem.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Otel implements Parcelable {

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.imageUrl);
		dest.writeString(this.otelAdi);
		dest.writeString(this.lokasyon);
		dest.writeString(this.promosyon);
		dest.writeString(this.puan);
		dest.writeString(this.hostelType);
		dest.writeString(this.fiyat);
		dest.writeString(this.indirimliFiyat);
		dest.writeString(this.fiyatBilgi);
		dest.writeStringList(this.otelOzellikleri);
		dest.writeString(this.otelDetay);
	}

	public Otel() {
	}

	protected Otel(Parcel in) {
		this.imageUrl = in.readString();
		this.otelAdi = in.readString();
		this.lokasyon = in.readString();
		this.promosyon = in.readString();
		this.puan = in.readString();
		this.hostelType = in.readString();
		this.fiyat = in.readString();
		this.indirimliFiyat = in.readString();
		this.fiyatBilgi = in.readString();
		this.otelOzellikleri = in.createStringArrayList();
		this.otelDetay = in.readString();
	}

	public static final Parcelable.Creator<Otel> CREATOR = new Parcelable.Creator<Otel>() {
		@Override
		public Otel createFromParcel(Parcel source) {
			return new Otel(source);
		}

		@Override
		public Otel[] newArray(int size) {
			return new Otel[size];
		}
	};
}