package com.salesDep.controllers;

import com.salesDep.model.SalesEngineer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SalesEngineerCreatorController {
    public TextField fieldExp;
    public Button buttonOk;
    public Button buttonCancel;
    @FXML
    private TextField fieldFullName;

    @FXML
    private TextField fieldNameOfCompany;

    @FXML
    private TextField fieldPhoneNum;

    @FXML
    private TextField fieldAddress;

    private Stage dialogStage;
    private SalesEngineer salesEngineer;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setSalesEngineer(SalesEngineer salesEngineer) {
        this.salesEngineer = salesEngineer;
        fieldFullName.setText(salesEngineer.getFullName().get());
        fieldNameOfCompany.setText(salesEngineer.getNameOfCompany().get());
        fieldAddress.setText(salesEngineer.getAddress().get());
        fieldPhoneNum.setText(salesEngineer.getPhoneNum().get());
        fieldExp.setText(Integer.toString(salesEngineer.getExperience().get()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            if (salesEngineer != null) {
                salesEngineer.getFullName().set(fieldFullName.getText());
                salesEngineer.getNameOfCompany().set(fieldNameOfCompany.getText());
                salesEngineer.getAddress().set(fieldAddress.getText());
                salesEngineer.getPhoneNum().set(fieldPhoneNum.getText());
                salesEngineer.getExperience().set(Integer.parseInt(fieldExp.getText()));
                okClicked = true;
                dialogStage.close();
            }
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (fieldFullName.getText() == null || fieldFullName.getText().isEmpty()) {
            errorMessage += "Не коректне ім'я!\n";
        }
        if (fieldNameOfCompany.getText() == null || fieldNameOfCompany.getText().isEmpty()) {
            errorMessage += "Не коректна назва компанії!\n";
        }
        if (fieldAddress.getText() == null || fieldAddress.getText().isEmpty()) {
            errorMessage += "Не коректний адрес!\n";
        }

        if (fieldPhoneNum.getText() == null || fieldPhoneNum.getText().isEmpty()) {
            errorMessage += "Не коректний номер телефону!\n";
        }

        if (fieldExp.getText() == null || fieldExp.getText().isEmpty()){
            errorMessage += "Не коректний стаж!\n";
        }else {
            try {
                int exp = Integer.parseInt(fieldExp.getText());
                if (exp <= 0) {
                    errorMessage += "Стаж повинен мати позитивне число!\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Не коректний стаж! Введіть коректне значення.\n";
            }
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            // Показуємо повідомлення про помилку.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Не коректні поля");
            alert.setHeaderText("Виправіть не коректні поля");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
