package com.salesDep.controllers;

import com.salesDep.MainApp;
import com.salesDep.model.Contract;
import com.salesDep.model.Product;
import com.salesDep.services.ContractService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


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
    private ContractService contractService = new ContractService();


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
        fieldContractID.setText(contract.getContractID().get());
        fieldContractTerm.setText(Integer.toString(contract.getTerm().get()));
        ObservableList<Product> products = contractService.loadProductsForContract(contract.getContractID().get());
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
        String termText = fieldContractTerm.getText();
        if (!contractService.validateContractTerm(termText)) {
            showError("Некоректний термін контракту");
            return false;
        }
        return true;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Помилка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}