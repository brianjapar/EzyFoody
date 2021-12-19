package com.example.ezyfoody.model;

public class Food {
    private String foodName;
    private String foodType;
    private Integer foodPrice;
    private Integer foodImage;

    public Food(String foodName, String foodType, Integer foodPrice, Integer foodImage) {
        this.foodName = foodName;
        this.foodType = foodType;
        this.foodPrice = foodPrice;
        this.foodImage = foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public Integer getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Integer getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(Integer foodImage) {
        this.foodImage = foodImage;
    }
}
