package com.yrazlik.lolparser.championdiscounts;

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
public class ChampionDiscountParser {

    private String url;
    private List<ChampionDiscount> championDiscountsArray;


    public ChampionDiscountParser(String url) {
        this.url = url;
    }

    public ChampionDiscountParser parse() {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
            getChampionDiscounts(document);
        } catch (IOException e) {
            return null;
        }
        return this;
    }

    private void getChampionDiscounts(Document document) {
        try {
            championDiscountsArray = new ArrayList<>();
            Elements championDiscounts = document.getElementsByClass("gs-container default-4-col");

            if (championDiscounts != null) {
                Elements costumeDiscountElements = championDiscounts.get(0).children();
                for (Element e : costumeDiscountElements) {
                    List<String> dates = getDates(document);

                    String champId = getChampId();
                    String createdAt = dates.get(0);
                    String startDate = dates.get(0);
                    String endDate = dates.get(1);
                    String name = getName(e);

                    String [] prices = getPrices(e);
                    String priceBeforeDiscount = prices[0];
                    String priceAfterDiscount = prices[1];

                    String imageUrl = getImageUrl(name);

                    championDiscountsArray.add(new ChampionDiscount(champId, createdAt, startDate, endDate, name, priceBeforeDiscount, priceAfterDiscount, imageUrl));
                }
            }
        }catch (Exception e) {
            System.out.println("Error parsing champion discounts page.");
        }
    }

    public String getChampId() {
        return "0";
    }

    public List<String> getDates(Document document) {
        return Arrays.asList(document.title().replaceAll("[^.?0-9]+", " ").trim().split(" "));
    }

    public String getName(Element e) {
        return e.select("div").get(0).select("h4").get(0).text();
    }

    public String getImageUrl(String champName) {
        return "http://ddragon.leagueoflegends.com/cdn/7.6.1/img/champion/" + champName.replaceAll("[^a-zA-Z]", "").trim().toString() + ".png";
    }

    public String[] getPrices(Element e) {
        return e.select("div").get(0).select("p").get(0).text().split(" ");
    }

    public void saveToFile() {
        new ChampionDiscountsWriter(championDiscountsArray).write();
    }
}
