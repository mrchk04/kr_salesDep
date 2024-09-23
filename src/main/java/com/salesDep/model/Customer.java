package com.salesDep.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer extends Person implements Printable{

    public Customer() {
        this(new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty(""),
                new SimpleStringProperty(""));
    }

    public Customer(StringProperty fullName, StringProperty nameOfCompany, StringProperty address,
                    StringProperty phoneNum) {
        super(fullName, nameOfCompany, address, phoneNum);
    }

    @Override
    public void printInfo() {
        System.out.println("ПІБ: " + fullName);
        System.out.println("Назва компанії: " + nameOfCompany);
        System.out.println("Адрес: " + address);
        System.out.println("Номер телефону: " + phoneNum);
    }
}
