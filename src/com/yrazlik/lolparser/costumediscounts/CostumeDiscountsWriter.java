package com.yrazlik.lolparser.costumediscounts;

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
public class CostumeDiscountsWriter {
    public static final String COSTUME_DISCOUNTS_FILE = "lolcostumediscounts.json";

    private PrintWriter writer;
    private List<CostumeDiscount> costumeDiscountsArray;

    public CostumeDiscountsWriter(List<CostumeDiscount> costumeDiscountsArray) {
        this.costumeDiscountsArray = costumeDiscountsArray;
    }

    public void write() {

        if(costumeDiscountsArray != null && costumeDiscountsArray.size() > 0) {
            List<CostumeDiscount> currentCostumeDiscounts = removeLastFourDiscounts(getCurrentCostumeDiscounts());
            List<CostumeDiscount> combinedCostumeDiscounts = new ArrayList<>();
            combinedCostumeDiscounts.addAll(costumeDiscountsArray);
            combinedCostumeDiscounts.addAll(currentCostumeDiscounts);
            try {
                writer = new PrintWriter(COSTUME_DISCOUNTS_FILE, "UTF-8");
                writer.write(new Gson().toJson(combinedCostumeDiscounts));
                writer.close();
                System.out.println("Successfully saved costume discounts file.");
            } catch (IOException e) {
                System.out.println("Error writing costume discounts file.");
                if (writer != null) {
                    writer.close();
                }
            }
        } else {
            System.out.println("No costume discounts found to write.");
        }
    }

    private List<CostumeDiscount> getCurrentCostumeDiscounts() {
        try {
            Type listType = new TypeToken<List<CostumeDiscount>>(){}.getType();
            String content = new Scanner(new File(COSTUME_DISCOUNTS_FILE)).useDelimiter("\\Z").next();
            return new Gson().fromJson(content, listType);
        } catch (FileNotFoundException e) {
            System.out.println("Error reading current costume discounts file.");
        }
        return null;
    }

    private List<CostumeDiscount> removeLastFourDiscounts(List<CostumeDiscount> currentCostumeDiscounts) {

        if(currentCostumeDiscounts != null) {
            if(currentCostumeDiscounts.size() > 4) {
                for(int i = 0; i < 4; i++) {
                    currentCostumeDiscounts.remove(currentCostumeDiscounts.size() - 1);
                }
                return currentCostumeDiscounts;
            }
        } else {
            currentCostumeDiscounts = new ArrayList<>();
        }

        return currentCostumeDiscounts;
    }
}
