package com.yrazlik.lolparser.championdiscounts;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yrazlik on 01/04/17.
 */
public class ChampionDiscountsWriter {

    public static final String CHAMPION_DISCOUNTS_FILE = "lolchampiondiscounts.json";

    private PrintWriter writer;
    private List<ChampionDiscount> championDiscountsArray;

    public ChampionDiscountsWriter(List<ChampionDiscount> championDiscountsArray) {
        this.championDiscountsArray = championDiscountsArray;
    }

    public void write() {

        if(championDiscountsArray != null && championDiscountsArray.size() > 0) {
            List<ChampionDiscount> currentChampionDiscounts = removeLastFourDiscounts(getCurrentChampionDiscounts());
            List<ChampionDiscount> combinedChampionDiscounts = new ArrayList<>();
            combinedChampionDiscounts.addAll(championDiscountsArray);
            combinedChampionDiscounts.addAll(currentChampionDiscounts);
            try {
                writer = new PrintWriter(CHAMPION_DISCOUNTS_FILE, "UTF-8");
                writer.write(new Gson().toJson(combinedChampionDiscounts));
                writer.close();
                System.out.println("Successfully saved champion discounts file.");
            } catch (IOException e) {
                System.out.println("Error writing champion discounts file.");
                if (writer != null) {
                    writer.close();
                }
            }
        } else {
            System.out.println("No champion discounts found to write.");
        }
    }

    private List<ChampionDiscount> getCurrentChampionDiscounts() {
        try {
            Type listType = new TypeToken<List<ChampionDiscount>>(){}.getType();
            String content = new Scanner(new File(CHAMPION_DISCOUNTS_FILE)).useDelimiter("\\Z").next();
            return new Gson().fromJson(content, listType);
        } catch (FileNotFoundException e) {
            System.out.println("Error reading current champion discounts file.");
        }
        return null;
    }

    private List<ChampionDiscount> removeLastFourDiscounts(List<ChampionDiscount> currentChampionDiscounts) {

        if(currentChampionDiscounts != null) {
            if(currentChampionDiscounts.size() > 4) {
                for(int i = 0; i < 4; i++) {
                    currentChampionDiscounts.remove(currentChampionDiscounts.size() - 1);
                }
                return currentChampionDiscounts;
            }
        } else {
            currentChampionDiscounts = new ArrayList<>();
        }

        return currentChampionDiscounts;
    }
}
