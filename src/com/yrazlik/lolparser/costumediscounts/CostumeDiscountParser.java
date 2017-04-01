package com.yrazlik.lolparser.costumediscounts;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yrazlik on 01/04/17.
 */
public class CostumeDiscountParser {

    private String url;
    private List<CostumeDiscount> costumeDiscountsArray;

    public CostumeDiscountParser(String url) {
        this.url = url;
    }

    public CostumeDiscountParser parse() {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
            getCostumeDiscounts(document);
        } catch (IOException e) {
            return null;
        }
        return this;
    }

    private void getCostumeDiscounts(Document document) {
        try {
            costumeDiscountsArray = new ArrayList<>();
            Elements costumeDiscounts = document.getElementsByClass("gs-container default-2-col");

            if (costumeDiscounts != null) {
                Elements costumeDiscountElements = costumeDiscounts.get(0).children();
                for (Element e : costumeDiscountElements) {
                    List<String> dates = getDates(document);

                    String createdAt = dates.get(0);
                    String startDate = dates.get(0);
                    String endDate = dates.get(1);
                    String name = getName(e);

                    String [] prices = getPrices(e);
                    String priceBeforeDiscount = prices[0];
                    String priceAfterDiscount = prices[1];

                    String imageUrl = getImageUrl(e);

                    costumeDiscountsArray.add(new CostumeDiscount(createdAt, startDate, endDate, name, priceBeforeDiscount, priceAfterDiscount, imageUrl));
                }
            }
        }catch (Exception e) {
            System.out.println("Error parsing costume discounts page.");
        }
    }

    public List<String> getDates(Document document) {
        return Arrays.asList(document.title().replaceAll("[^.?0-9]+", " ").trim().split(" "));
    }

    public String getName(Element e) {
        return e.select("div").get(0).select("h4").get(0).text();
    }

    public String getImageUrl(Element e) {
        return e.select("div").get(0).getElementsByClass("lightbox cboxElement").get(0).getElementsByTag("img").get(0).absUrl("src");
    }

    public String[] getPrices(Element e) {
        return e.select("div").get(0).select("p").get(0).text().split(" ");
    }

    public void saveToFile() {
        new CostumeDiscountsWriter(costumeDiscountsArray).write();
    }
}
