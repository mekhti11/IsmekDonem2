package com.ismek.ismekdonem.service;

import com.ismek.ismekdonem.entity.Otel;
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

}
