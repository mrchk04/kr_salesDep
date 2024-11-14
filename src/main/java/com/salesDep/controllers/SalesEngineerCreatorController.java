package com.salesDep.controllers;

import com.salesDep.model.SalesEngineer;
import com.salesDep.services.SalesEngineerService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SalesEngineerCreatorController {

    @FXML
    private TextField fieldExp, fieldFullName, fieldNameOfCompany, fieldPhoneNum, fieldAddress;
    @FXML
    private Button buttonOk, buttonCancel;

    private Stage dialogStage;
    private SalesEngineer salesEngineer;
    private boolean okClicked = false;
    private final SalesEngineerService service = new SalesEngineerService();

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
        String errorMessage = service.validateSalesEngineerData(
                fieldFullName.getText(),
                fieldNameOfCompany.getText(),
                fieldPhoneNum.getText(),
                fieldAddress.getText(),
                fieldExp.getText()
        );

        if (errorMessage.isEmpty()) {
            service.updateSalesEngineer(salesEngineer,
                    fieldFullName.getText(),
                    fieldNameOfCompany.getText(),
                    fieldPhoneNum.getText(),
                    fieldAddress.getText(),
                    Integer.parseInt(fieldExp.getText())
            );
            okClicked = true;
            dialogStage.close();
        } else {
            showErrorDialog(errorMessage);
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Некоректні поля");
        alert.setHeaderText("Будь ласка, введіть коректні дані.");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
