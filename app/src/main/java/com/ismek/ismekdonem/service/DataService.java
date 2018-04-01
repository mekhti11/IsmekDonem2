package com.ismek.ismekdonem.service;

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

            doc = Jsoup.connect(link).userAgent("Mozilla").get();
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

}
