package com.salesDep.services;

import com.salesDep.InputValidator;
import com.salesDep.model.SalesEngineer;

public class SalesEngineerService {

    public String validateSalesEngineerData(String fullName, String companyName, String phoneNum, String address, String experience) {
        String errorMessage = "";

        errorMessage += InputValidator.validateNotEmpty(fullName, "Повне ім'я");
        errorMessage += InputValidator.validateNotEmpty(companyName, "Назва компанії");
        errorMessage += InputValidator.validateNotEmpty(phoneNum, "Номер телефону");
        errorMessage += InputValidator.validateNotEmpty(address, "Адреса");
        errorMessage += InputValidator.validateNotEmpty(experience, "Стаж");

        return errorMessage;
    }

    public void updateSalesEngineer(SalesEngineer salesEngineer, String fullName, String companyName, String phoneNum, String address, int experience) {
        salesEngineer.getFullName().set(fullName);
        salesEngineer.getNameOfCompany().set(companyName);
        salesEngineer.getAddress().set(address);
        salesEngineer.getPhoneNum().set(phoneNum);
        salesEngineer.getExperience().set(experience);
    }
}
