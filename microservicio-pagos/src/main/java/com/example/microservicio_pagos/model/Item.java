package com.example.microservicio_pagos.model;

import java.math.BigDecimal;

public class Item {
    private String id;
    private String title;
    private Integer quantity;
    private BigDecimal unitPrice;
    private String currencyId;
    private String description;
    private String pictureUrl;
    private String categoryId;

    // Constructor por defecto
    public Item() {}

    // Constructor con todos los campos
    public Item(String id, String title, Integer quantity, BigDecimal unitPrice, String currencyId, String description, String pictureUrl, String categoryId) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.currencyId = currencyId;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.categoryId = categoryId;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", currencyId='" + currencyId + '\'' +
                ", description='" + description + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}
