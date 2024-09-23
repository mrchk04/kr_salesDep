package com.salesDep.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HighestValueController {


    public Label labelHighestValue;
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

    private String findContractWithMaxValue(String filePath) {
        String maxContractId = null;
        double maxValue = Double.MIN_VALUE;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    double contractValue = calculateContractValue(parts);
                    if (contractValue > maxValue) {
                        maxValue = contractValue;
                        maxContractId = parts[0];
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return (maxContractId != null) ? String.format("Найбільша вартість в контракту %s: %.2f $", maxContractId, maxValue) : "Немає даних про контракти";
    }

    private double calculateContractValue(String[] parts) {
        double contractValue = 0;

        for (int i = 2; i < parts.length; i++) {
            String[] productInfo = parts[i].split(",");
            if (productInfo.length == 3) {
                int quantity = Integer.parseInt(productInfo[1]);
                double price = Double.parseDouble(productInfo[2]);
                contractValue += quantity * price;
            }
        }

        return contractValue;
    }
    public void prepareWindow() {
        String highestValue = findContractWithMaxValue("orderData.txt");
        labelHighestValue.setText(highestValue);
    }
}
