package com.salesDep.services;

public class ProductService {

    public boolean validateProductFields(String type, String quantityText, String costText) {
        try {
            int quantity = Integer.parseInt(quantityText);
            double cost = Double.parseDouble(costText);
            return !type.isEmpty() && quantity > 0 && cost > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
