package com.salesDep.model;

import javafx.beans.property.StringProperty;

public abstract class Person{
    protected StringProperty fullName;
    protected StringProperty nameOfCompany;
    protected StringProperty address;
    protected StringProperty phoneNum;

    public Person(StringProperty fullName, StringProperty nameOfCompany, StringProperty address, StringProperty phoneNum) {
        this.fullName = fullName;
        this.nameOfCompany = nameOfCompany;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    public StringProperty getFullName() {
        return fullName;
    }

    public void setFullName(StringProperty fullName) {
        this.fullName = fullName;
    }

    public StringProperty getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(StringProperty nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public StringProperty getAddress() {
        return address;
    }

    public void setAddress(StringProperty address) {
        this.address = address;
    }

    public StringProperty getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(StringProperty phoneNum) {
        this.phoneNum = phoneNum;
    }

    public abstract void printInfo();
}

