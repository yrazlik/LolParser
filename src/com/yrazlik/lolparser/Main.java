package com.yrazlik.lolparser;

import com.yrazlik.lolparser.championdiscounts.ChampionDiscountParser;
import com.yrazlik.lolparser.costumediscounts.CostumeDiscountParser;
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
public class Main {

    private static final String DISCOUNTS_URL =
            "http://tr.leagueoflegends.com/tr/news/store/sales/sampiyon-ve-kostum-indirimi-3103-0304-0";

    public static void main(String[] args) throws Exception {
        new CostumeDiscountParser(DISCOUNTS_URL).parse().saveToFile();
        new ChampionDiscountParser(DISCOUNTS_URL).parse().saveToFile();

    }
}
