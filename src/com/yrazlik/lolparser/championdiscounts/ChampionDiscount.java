package com.yrazlik.lolparser.championdiscounts;

/**
 * Created by yrazlik on 01/04/17.
 */
public class ChampionDiscount {

    private String champId;
    private String createdAt;
    private String startDate;
    private String endDate;
    private String name;
    private String priceBeforeDiscount;
    private String priceAfterDiscount;
    private String imageUrl;

    public ChampionDiscount(String champId, String createdAt, String startDate, String endDate, String name, String priceBeforeDiscount, String priceAfterDiscount, String imageUrl) {
        this.champId = champId;
        this.createdAt = createdAt;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.priceBeforeDiscount = priceBeforeDiscount;
        this.priceAfterDiscount = priceAfterDiscount;
        this.imageUrl = imageUrl;
    }

    public String getChampId() {
        return champId;
    }

    public void setChampId(String champId) {
        this.champId = champId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public void setPriceBeforeDiscount(String priceBeforeDiscount) {
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    public String getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(String priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
