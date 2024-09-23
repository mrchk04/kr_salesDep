package com.salesDep.model;

import javafx.beans.property.*;

public class Product implements Printable{
    private StringProperty productType;
    private IntegerProperty quantity;
    private DoubleProperty cost;

    public Product(String productType, Integer quantity, Double cost) {
        this.productType = new SimpleStringProperty(productType != null ? productType : "");
        this.quantity = new SimpleIntegerProperty(quantity != null ? quantity : 0);
        this.cost = new SimpleDoubleProperty(cost != null ? cost : 0.0);
    }

    public Product() {
        this("",0,0.0);
    }

    public StringProperty getProductType() {
        return productType;
    }

    public void setProductType(StringProperty productType) {
        this.productType = productType;
    }

    public IntegerProperty getQuantity() {
        return quantity;
    }

    public void setQuantity(IntegerProperty quantity) {
        this.quantity = quantity;
    }

    public DoubleProperty getCost() {
        return cost;
    }

    public void setCost(DoubleProperty cost) {
        this.cost = cost;
    }
    @Override
    public void printInfo() {
        System.out.println("Продукти:");
        System.out.println("    - Тип продукту: " + productType);
        System.out.println("    - Кількість: " + quantity);
        System.out.println("    - Вартість: " + cost + "$");
    }

    public StringProperty nameProperty() {
        return productType;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public DoubleProperty costProperty() {
        return cost;
    }

}

