package com.example.ezyfoody.model;

public class Order {
    String foodName;
    Integer foodPrice;
    Integer quantity;
    Integer foodImage;

    public Order(String foodName, Integer foodPrice, Integer quantity, Integer foodImage) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.quantity = quantity;
        this.foodImage = foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(Integer foodImage) {
        this.foodImage = foodImage;
    }
}
