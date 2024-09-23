package com.salesDep.controllers;

import com.salesDep.model.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerCreatorController {
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
    private Customer customer;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        fieldFullName.setText(customer.getFullName().get());
        fieldNameOfCompany.setText(customer.getNameOfCompany().get());
        fieldAddress.setText(customer.getAddress().get());
        fieldPhoneNum.setText(customer.getPhoneNum().get());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            if (customer != null) {
                customer.getFullName().set(fieldFullName.getText());
                customer.getNameOfCompany().set(fieldNameOfCompany.getText());
                customer.getAddress().set(fieldAddress.getText());
                customer.getPhoneNum().set(fieldPhoneNum.getText());

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

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            // Показуємо повідомлення про помилку.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Не коректні поля");
            alert.setHeaderText("Будь ласка, введіть коректні поля.");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
