package com.salesDep.controllers;

import com.salesDep.MainApp;
import com.salesDep.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProductCreatorController {
    public TextField fieldType;
    public TextField fieldQuantity;
    public TextField fieldCost;
    public Button buttonOk;
    public Button buttonCancel;

    private Stage dialogStage1;
    private Product product;
    private boolean okClicked = false;
    private MainApp mainApp;


    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage1 = dialogStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setProduct(Product product) {
        this.product = product;
        fieldType.setText(product.getProductType().get());
        fieldQuantity.setText(Integer.toString(product.getQuantity().get()));
        fieldCost.setText(Double.toString(product.getCost().get()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            if (product != null) {
                product.getProductType().set(fieldType.getText());
                product.getQuantity().set(Integer.parseInt(fieldQuantity.getText()));
                product.getCost().set(Double.parseDouble(fieldCost.getText()));

                okClicked = true;
                dialogStage1.close();
            }
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage1.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (fieldType.getText() == null || fieldType.getText().isEmpty()) {
            errorMessage += "Не коректний тип продукту!\n";
        }
        if (fieldQuantity.getText() == null || fieldQuantity.getText().isEmpty()) {
            errorMessage += "Не коректна кількість!\n";
        } else {
            try {
                int qntt = Integer.parseInt(fieldQuantity.getText());
                if (qntt <= 0) {
                    errorMessage += "Кількість повина мати позитивне число!\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Не коректна кількість! Введіть коректне значення.\n";
            }
        }
        if (fieldCost.getText() == null || fieldCost.getText().isEmpty()) {
            errorMessage += "Не коректна вартість!\n";
        }else {
            try {
                int cost = Integer.parseInt(fieldQuantity.getText());
                if (cost <= 0) {
                    errorMessage += "Вартість повина мати позитивне число!\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Не коректна вартість! Введіть коректне значення.\n";
            }
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            // Показуємо повідомлення про помилку.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage1);
            alert.setTitle("Не коректні поля");
            alert.setHeaderText("Будь ласка, введіть коректні поля.");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
