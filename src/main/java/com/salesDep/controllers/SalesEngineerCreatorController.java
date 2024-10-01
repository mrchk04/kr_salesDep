package com.salesDep.controllers;

import com.salesDep.InputValidator;
import com.salesDep.model.SalesEngineer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

        // Перевіряємо, чи поля не пусті
        errorMessage += InputValidator.validateNotEmpty(fieldFullName.getText(), "Повне ім'я");
        errorMessage += InputValidator.validateNotEmpty(fieldNameOfCompany.getText(), "Назва компанії");
        errorMessage += InputValidator.validateNotEmpty(fieldPhoneNum.getText(), "Номер телефону");
        errorMessage += InputValidator.validateNotEmpty(fieldAddress.getText(), "Адреса");
        errorMessage += InputValidator.validateNotEmpty(fieldExp.getText(), "Стаж");

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            // Показуємо повідомлення про помилку
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);  // Використовуємо dialogStage, а не dialogStage1
            alert.setTitle("Не коректні поля");
            alert.setHeaderText("Будь ласка, введіть коректні поля.");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
