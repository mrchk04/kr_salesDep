package com.salesDep.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class AverageAmountController {
    @FXML
    private Label labelAverageAmount;

    @FXML
    private Button buttonOk;

    @FXML
    private void initialize() {
        // Ініціалізація, якщо потрібно
    }

    @FXML
    private void handleButtonOk() {
        Stage stage = (Stage) buttonOk.getScene().getWindow();
        stage.close();
    }

    private double calculateAverageAmountFromFile(String filePath) {
        double totalAmount = 0;
        int contractCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    for (int i = 2; i < parts.length; i++) {
                        String[] productInfo = parts[i].split(",");
                        if (productInfo.length == 3) {
                            int quantity = Integer.parseInt(productInfo[1]);
                            totalAmount += quantity;
                        }
                    }
                    contractCount++;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Розрахунок середньої кількості продуктів
        return (contractCount > 0) ? (totalAmount / contractCount) : 0;
    }

    public void prepareWindow() {
        // Виклик методу для розрахунку середньої кількості продуктів
        double averageAmount = calculateAverageAmountFromFile("orderData.txt");
        DecimalFormat decimalFormat = new DecimalFormat( "#.##" );
        String averageAmountStr = decimalFormat.format(averageAmount);

        // Встановлення тексту для labelAverageAmount
        labelAverageAmount.setText("Середня кількість: " + averageAmountStr + " одиниць.");
    }
}
