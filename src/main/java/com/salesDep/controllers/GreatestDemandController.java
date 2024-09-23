package com.salesDep.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GreatestDemandController {
    public Label labelGreatestDemand;
    public Button buttonOk;

    @FXML
    private void initialize() {
        // Ініціалізація, якщо потрібно
    }

    @FXML
    private void handleButtonOk() {
        Stage stage = (Stage) buttonOk.getScene().getWindow();
        stage.close();
    }

    private String findProductWithGreatestDemand(String filePath) {
        Map<String, Integer> productQuantityMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    for (int i = 2; i < parts.length; i++) {
                        String[] productInfo = parts[i].split(",");
                        if (productInfo.length == 3) {
                            String productType = productInfo[0];
                            int quantity = Integer.parseInt(productInfo[1]);
                            productQuantityMap.put(productType, productQuantityMap.getOrDefault(productType, 0) + quantity);
                        }
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        String maxProductType = null;
        int maxQuantity = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : productQuantityMap.entrySet()) {
            String productType = entry.getKey();
            int quantity = entry.getValue();
            if (quantity > maxQuantity) {
                maxQuantity = quantity;
                maxProductType = String.format("%s, %d", productType, quantity);
            }
        }

        return (maxProductType != null) ? String.format("Продукт з найбільшим попитом: %s одиниць", maxProductType) : "Немає даних про контракти";
    }

    public void prepareWindow() {
        String maxProductType = findProductWithGreatestDemand("orderData.txt");
        labelGreatestDemand.setText(maxProductType);
    }
}
