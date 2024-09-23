package com.salesDep.controllers;

import com.salesDep.MainApp;
import com.salesDep.model.Contract;
import com.salesDep.model.Customer;
import com.salesDep.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.salesDep.FileManager;

import java.io.IOException;
import java.util.List;


public class ContractCreatorController extends MainApp {
    @FXML
    private TextField fieldContractID;

    @FXML
    private TextField fieldContractTerm;

    @FXML
    private TableView<Product> tableProducts;

    @FXML
    private TableColumn<Product, String> columnName;

    @FXML
    private TableColumn<Product, Integer> columnQuantity;

    @FXML
    private TableColumn<Product, Double> columnCost;

    @FXML
    private Button buttonOk;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonAddProduct;

    @FXML
    private Button buttonDeleteProduct;

    private Stage dialogStage;
    private Contract contract;
    private boolean okClicked = false;
    private TableView<Contract> tableContract;
    private MainApp mainApp;


    @FXML
    private void initialize() {
        columnName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        columnQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        columnCost.setCellValueFactory(cellData -> cellData.getValue().costProperty().asObject());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
        ObservableList<Product> products = FXCollections.observableArrayList();

        FileManager.loadProductsFromFile(contract.getContractID().get(), products);

        fieldContractID.setText(contract.getContractID().get());
        fieldContractTerm.setText(Integer.toString(contract.getTerm().get()));
        tableProducts.setItems(products);
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            if (contract == null) {
                contract = new Contract(null, null, null);
            }

            contract.getContractID().set(fieldContractID.getText());

            // Перевірка, чи поле не є порожнім перед конвертацією
            String termText = fieldContractTerm.getText();
            if (!termText.isEmpty()) {
                contract.getTerm().set(Integer.parseInt(termText));
            }

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleAddProduct() {
        Product tempProduct = new Product();
        boolean okClicked = mainApp.showProductEditDialog(tempProduct);
        if (okClicked) {
            // Використовуйте змінний (modifiable) список
            ObservableList<Product> products = FXCollections.observableArrayList(contract.getProducts());
            products.add(tempProduct);
            contract.getProducts().set(products);
            tableProducts.setItems(products);
        }
    }

    @FXML
    private void handleDeleteProduct() {
        int selectedIndex = tableProducts.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tableProducts.getItems().remove(selectedIndex);
        } else {
            // Нічого не вибрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Нічого не обрано");
            alert.setHeaderText("Клієнта не було обрано");
            alert.setContentText("Будь ласка оберіть клієнта в таблиці.");
            alert.showAndWait();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (fieldContractID.getText() == null || fieldContractID.getText().trim().isEmpty()) {
            errorMessage += "Не коректний ID контракту!\n";
        }

        if (fieldContractTerm.getText() == null || fieldContractTerm.getText().trim().isEmpty()) {
            errorMessage += "Не коректний термін поставки!\n";
        } else {
            try {
                int term = Integer.parseInt(fieldContractTerm.getText());
                if (term <= 0) {
                    errorMessage += "Термін повинен мати позитивне число!\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Не коректний термін поставки! Введіть коректне значення.\n";
            }
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            // Display the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Не коректні поля");
            alert.setHeaderText("Будь ласка, введіть коректні поля.");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}