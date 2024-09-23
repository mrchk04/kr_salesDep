package com.salesDep.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;

public class Contract implements Printable{
    private ListProperty<Product> products;
    private IntegerProperty term;
    private StringProperty contractID;


    public Contract(StringProperty contractID, IntegerProperty term, ListProperty<Product> products) {
        this.products = new SimpleListProperty<>(FXCollections.observableArrayList());
        if (products != null) {
            this.products.bindBidirectional(products);
        }
        this.term = term;
        this.contractID = contractID;
    }

    public Contract() {
        this(new SimpleStringProperty(""), new SimpleIntegerProperty(0), new SimpleListProperty<Product>());
    }

    public ListProperty<Product> getProducts() {
        return products;
    }

    public void setProducts(ListProperty<Product> products) {
        this.products = products;
    }

    public IntegerProperty getTerm() {
        return term;
    }

    public void setTerm(IntegerProperty term) {
        this.term = term;
    }

    public StringProperty getContractID() {
        return contractID;
    }

    public void setContractID(StringProperty contractID) {
        this.contractID = contractID;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
    @Override
    public void printInfo(){
        for (Product product: products){
            product.printInfo();
        }
        System.out.println("Термін: "+term);
        System.out.println("ID контракту: "+contractID);
    }
}
