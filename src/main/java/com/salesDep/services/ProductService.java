package com.salesDep.services;

public class ProductService {

    public boolean validateProductFields(String type, String quantityText, String costText) {
        try {
            int quantity = Integer.parseInt(quantityText);
            double cost = Double.parseDouble(costText);
            return !type.isEmpty() && cost >= 0.01 && cost <= 10000 && quantity > 0 && quantity <= 10000;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
