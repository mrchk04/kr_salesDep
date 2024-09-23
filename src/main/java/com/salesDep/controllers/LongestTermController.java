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

public class LongestTermController {
    public Label labelLongestTerm;

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

    private String findContractWithLongestTerm(String filePath) {
        String maxContractId = null;
        int maxTerm = Integer.MIN_VALUE;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    int term = Integer.parseInt(parts[1]);
                    if (term > maxTerm) {
                        maxTerm = term;
                        maxContractId = parts[0];
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return (maxContractId != null) ? String.format("Найбільший термін виконання в контракту %s: %d днів.", maxContractId, maxTerm) : "Немає даних про контракти";
    }


    public void prepareWindow() {
        String maxTerm = findContractWithLongestTerm("orderData.txt");
        labelLongestTerm.setText(maxTerm);
    }
}
