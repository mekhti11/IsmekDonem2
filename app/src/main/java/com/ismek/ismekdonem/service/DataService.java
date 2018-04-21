package com.ismek.ismekdonem.service;

import com.ismek.ismekdonem.entity.Oda;
import com.ismek.ismekdonem.entity.OdaGunFiyatBilgisi;
import com.ismek.ismekdonem.entity.Otel;
import com.ismek.ismekdonem.entity.OtelDetail;
import com.ismek.ismekdonem.entity.Urun;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DataService {

    public List<Urun> findUrunByLink(String link){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        Document doc;
        List<Urun> uruns = new ArrayList<>();
        try {

            doc = Jsoup.connect(link).userAgent("Mozilla").timeout(60000).get();
            Element elUrunUl = doc.select("div#view > ul").get(0);
            Elements elUrunList = elUrunUl.select("li");
            for (int i = 0; i < elUrunList.size(); i++) {
                Element elUrun = elUrunList.get(i);
                Urun urun = new Urun();

                Elements elImages = elUrun.select("img");
                if (elImages != null && elImages.size() > 0) {
                    Element elImage = elImages.get(0);
                    urun.setResimUrl(elImage.attr("data-original"));
                    urun.setBaslik(elUrun.select("a[class].plink").get(0).attr("title"));
                    urun.setDetayLink(elUrun.select("a[class].plink").get(0).attr("href"));
                    urun.setFiyat(decimalFormat.parse(elUrun.select("a[class].newPrice").get(0).select("ins").get(0).html().replace("<span content=\"TRY\">TL</span>", "").trim()).doubleValue());
                    try {
                        urun.setEskiFiyat(decimalFormat.parse(elUrun.select("a[class].oldPrice").get(0).select("del").get(0).html().replace("TL", "").trim()).doubleValue());
                    } catch (Exception e) {
                    }
                    Elements elKargoInfoList = elUrun.select("span.freeShipping");
                    if (elKargoInfoList != null && elKargoInfoList.size()>0) {
                        urun.setKargoBilgisi("Ücretsiz Kargo");
                    }

                    Elements elIndirimList = elUrun.select("div.discount");
                    if (elIndirimList != null && elIndirimList.size() > 0) {
                        Element elIndirim = elIndirimList.get(0);
                        urun.setIndirimYuzdesi(Integer.parseInt(elIndirim.select("span.ratio").get(0).html()));
                    }

                    Elements elRatingTextList = elUrun.select("span.ratingText");
                    if (elRatingTextList != null && elRatingTextList.size() > 0) {
                        Element elRatingText = elRatingTextList.get(0);
                        String text = elRatingText.html().replace("(", "").replace(")", "").replace(",", "");
                        urun.setOylamaSayisi(Integer.parseInt(text));
                    }

                    Elements elRatingList = elUrun.select("span.rating");
                    if (elRatingList != null && elRatingList.size() > 0) {
                        Element elRating = elRatingList.get(0);
                        urun.setOylamaYuzdesi(Integer.parseInt(elRating.attr("class").replace("rating r", "")));
                    }
                    uruns.add(urun);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return uruns;
    }

    public List<Otel> getOtelsByLink(String link){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        Document doc;
        List<Otel> otels = new ArrayList<>();
        try {
            doc = Jsoup.connect(link).userAgent("Mozilla").timeout(60000).get();
            Element elOtels = doc.select("div#HotelList").get(0);
            Elements elOtelList = elOtels.select("article");
            for (int i = 0; i < elOtelList.size(); i++) {
                Element elOtel = elOtelList.get(i);
                Otel otel = new Otel();
                otel.setImageUrl(elOtel.select("img").get(0).attr("data-src"));
                otel.setOtelAdi(elOtel.select("div[class].panel-heading").get(0).select("a").get(0).html());
                otel.setLokasyon(elOtel.select("div[class].col-lg-5").get(0).select("p").get(0).html().replaceAll("<i class=\"fa fa-map-marker\" aria-hidden=\"true\"></i> ", ""));
                otel.setPromosyon(elOtel.select("p[class].erk-promo").get(0).html());
                try {
                    otel.setPuan(elOtel.select("div[class].score").get(0).select("span").get(0).html());
                }catch (Exception e){
                    e.printStackTrace();
                }
                Elements elHostelTypeList = elOtel.select("p[class].hostel-type");
                if (elHostelTypeList != null && elHostelTypeList.size() > 0)
                    otel.setHostelType(elOtel.select("p[class].hostel-type").get(0).html());
                try {
                    otel.setFiyat(elOtel.select("p[class].currency").get(0).html().replaceAll("<small class=\"price-currency\">", "").replaceAll(" TL</small>", ""));
                }catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    otel.setIndirimliFiyat(elOtel.select("p[class].discount-price").get(0).html().replaceAll("<small class=\"price-currency\">", "").replaceAll(" TL</small>", ""));
                }catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    otel.setFiyatBilgi(elOtel.select("p[class].single-text").get(0).html());
                }catch (Exception e){
                    e.printStackTrace();
                }

                Elements elOzellikler = elOtel.select("ul[class].hotelFeaturesList").get(0).select("li");
                if (elOzellikler != null && elOzellikler.size() > 0) {
                    List<String> ozellikler = new ArrayList<>();
                    for (int j = 0; j < elOzellikler.size(); j++) {
                        Element elOzellik = elOzellikler.get(j);
                        ozellikler.add(elOzellik.html().replaceAll("<i class=\"hotelFeaturesTooltip-icon\"></i> ", ""));
                    }
                    otel.setOtelOzellikleri(ozellikler);
                }
                otel.setOtelDetay("https://www.tatilsepeti.com" + elOtel.select("a[class].btn-primary").get(0).attr("href"));
                otels.add(otel);

            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return otels;
    }

    public OtelDetail getOtelDetail(String link){
        OtelDetail otelDetail = new OtelDetail();
        Document doc;
        try {
            doc = Jsoup.connect(link).userAgent("Mozilla").timeout(60000).get();


            Elements elOtelImages = doc.select("div[class].photo-gallery").get(0).select("div[class].item");

            //Otel resimleri cekiliyor
            if (elOtelImages != null && elOtelImages.size()>0) {
                List<String> otelImages = new ArrayList<>();
                for (int i = 0; i < elOtelImages.size(); i++) {
                    otelImages.add(elOtelImages.get(i).select("img").get(0).attr("data-src"));
                }
                otelDetail.setImageList(otelImages);
            }

            //Odalar cekiliyor
            Elements elOdalar = doc.select("div#dev-roomList").get(0).select("div#roomContainer");
            if (elOdalar != null && elOdalar.size() > 0) {
                List<Oda> odas = new ArrayList<>();
                for (int i = 0; i < elOdalar.size(); i++) {
                    Oda oda = new Oda();
                    Element elOda = elOdalar.get(i);

                    Elements elOdaKapasitesiList = elOda.select("div[class].well");
                    if (elOdaKapasitesiList != null && elOdaKapasitesiList.size() > 0) {
                        oda.setOdaKapasitesi(elOdaKapasitesiList.get(0).html());
                    }else{
                        continue;
                    }

                    oda.setName(elOda.select("li[class].active").get(0).select("a").get(0).html());
                    oda.setImage(elOda.select("div[class].roomImg").get(0).select("img").get(0).attr("src"));
                    oda.setKisiBasiIndirimliFiyat(elOda.select("div[class].price-panel__body__discount-price").html().replace("<small class=\"price-currency\">", "").replace("\n", "").trim().replace("</small>", "").trim());
                    oda.setKisiBasiFiyat(elOda.select("div[class].price-panel__body__old-price").html().replace("<small class=\"price-currency\">", "").replace("\n", "").trim().replace("</small>", "").trim());
                    oda.setToplamOdaFiyati(elOda.select("div[class].total-price").html().replace("<small class=\"price-currency\">", "").replace("\n", "").trim().replace("</small>", "").trim());
                    oda.setIndirim(elOda.select("div[class].price-panel__body__tooltip").get(0).html());

                    //Oda bilgileri cekiliyor
                    Element elOdaBilgiTemp = elOda.select("div[class].collapse-price-panel").get(0);
                    Elements elOdaGunFiyatBilgisiList = elOdaBilgiTemp.select("div[class].price-panel");
                    if (elOdaGunFiyatBilgisiList != null && elOdaGunFiyatBilgisiList.size() > 0) {
                        List<OdaGunFiyatBilgisi> odaGunFiyatBilgisiList = new ArrayList<>();
                        for (int j = 0; j < elOdaGunFiyatBilgisiList.size(); j++) {
                            OdaGunFiyatBilgisi odaGunFiyatBilgisi = new OdaGunFiyatBilgisi();
                            Element elOdaGunFiyatBilgisi = elOdaGunFiyatBilgisiList.get(j);
                            odaGunFiyatBilgisi.setGun(elOdaGunFiyatBilgisi.select("div[class].price-panel__head").get(0).html());
                            odaGunFiyatBilgisi.setFiyat(elOdaGunFiyatBilgisi.select("div[class].price-panel__body").get(0).html().replace("<small class=\"price-currency\">", "").replace("\n", "").trim().replace("</small>", "").trim());
                            if (elOdaGunFiyatBilgisi.attr("title") == null || "".equals(elOdaGunFiyatBilgisi.attr("title"))) {
                                odaGunFiyatBilgisi.setMusait(true);
                            }else{
                                odaGunFiyatBilgisi.setMusait(false);
                            }
                            odaGunFiyatBilgisiList.add(odaGunFiyatBilgisi);
                        }
                        oda.setOdaGunFiyatBilgisiList(odaGunFiyatBilgisiList);
                    }

                    odas.add(oda);
                }
                otelDetail.setOdaList(odas);

            }
            otelDetail.setHtmlKonumBilgileri(doc.select("div[class].location-info").get(0).html());
            otelDetail.setHtmlOtelOzellikleri(doc.select("div[class].room-spect").get(0).html());
            otelDetail.setHtmlUcretsizAktiviteler(doc.select("div[class].free-activities").get(0).html());
            otelDetail.setHtmlUcretliAktiviteler(doc.select("div[class].paid-activities").get(0).html());
            otelDetail.setHtmlCocukAktiviteleri(doc.select("div[class].activities-for-children").get(0).html());
            otelDetail.setHtmlGuvencePaketi(doc.select("div#dev-insurance").get(0).html());
            otelDetail.setHtmlKonaklamaOzellikleri(doc.select("div[class].accommodation").get(0).html());

        }catch (Exception e) {
            e.printStackTrace();
        }
        return otelDetail;
    }

}
