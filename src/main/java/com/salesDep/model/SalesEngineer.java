package com.salesDep.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SalesEngineer extends Person implements Printable{
    private IntegerProperty experience;

    public SalesEngineer(StringProperty fullName, StringProperty nameOfCompany, StringProperty address, StringProperty phoneNum, IntegerProperty experience) {
        super(fullName, nameOfCompany, address, phoneNum);
        this.experience = experience;
    }

    public SalesEngineer() {
        this(new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty(""),
                new SimpleStringProperty(""), new SimpleIntegerProperty());

    }

    public IntegerProperty getExperience() {
        return experience;
    }

    public void setExperience(IntegerProperty experience) {
        this.experience = experience;
    }

    @Override
    public void printInfo() {
        System.out.println("ПІБ: " + fullName);
        System.out.println("Назва компанії: " + nameOfCompany);
        System.out.println("Адрес: " + address);
        System.out.println("Номер телефону: " + phoneNum);
        System.out.println("Стаж: " + experience);
    }
}
