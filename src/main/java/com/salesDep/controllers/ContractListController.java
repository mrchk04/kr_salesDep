package com.salesDep.controllers;

import com.salesDep.FileManager;
import com.salesDep.MainApp;
import com.salesDep.model.Contract;
import com.salesDep.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.List;


public class ContractListController {
    public Label labelIDContract;
    public Label labelTermContract;
    public Button buttonCreateContract;
    public Button buttonEditContract;
    public Button buttonDeleteContract;
    public TableColumn<Contract, String> tableIDContract;
    public TableColumn<Contract, String> tableNameCustomer;
    public TableView<Contract> tableContract;
    public TableView<Product> tableProductContract;
    public TableColumn<Product, String> columnName;
    public TableColumn<Product, Integer> tableQuantity;
    public TableColumn<Product, Double> columnCost;

    private MainApp mainApp;

    public ContractListController() {
    }

    @FXML
    private void initialize() {
        tableIDContract.setCellValueFactory(cellData -> cellData.getValue().getContractID());

        showContractDetails(null);
        tableContract.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                Contract selectedContract = tableContract.getSelectionModel().getSelectedItem();
                showContractDetails(selectedContract);
            }
        });

        columnName.setCellValueFactory(cellData -> cellData.getValue().getProductType());
        tableQuantity.setCellValueFactory(cellData -> cellData.getValue().getQuantity().asObject());
        columnCost.setCellValueFactory(cellData -> cellData.getValue().getCost().asObject());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        List<Contract> contracts = FileManager.readContractsFromFile();
        mainApp.setContractData(FXCollections.observableArrayList(contracts));
        tableContract.setItems(mainApp.getContractData());
    }

    private void showContractDetails(Contract contract) {
        if (contract != null) {
            ObservableList<Product> products = contract.getProducts();

            labelIDContract.setText(contract.getContractID().get());
            labelTermContract.setText(String.valueOf(contract.getTerm().getValue()));
            tableProductContract.setItems(products);
        } else {
            labelIDContract.setText("");
            labelTermContract.setText("");
            tableProductContract.setItems(null);
        }
    }

    @FXML
    private void deleteContract() {
        int selectedIndex = tableContract.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Contract selectedContract = tableContract.getItems().remove(selectedIndex);
            FileManager.saveContractsToFile(mainApp.getContractData());
            FileManager.deleteContractsFromFile(selectedContract);
        } else {
            // Нічого не вибрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Нічого не обрано");
            alert.setHeaderText("Контракт не було обрано");
            alert.setContentText("Будь ласка оберіть контракт в таблиці.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewContract() {
        Contract tempContract = new Contract();
        boolean okClicked = mainApp.showContractEditDialog(tempContract);
        if (okClicked) {
            mainApp.getContractData().add(tempContract);
            FileManager.saveContractsToFile(mainApp.getContractData());
        }
    }

    @FXML
    private void handleEditContract() {
        Contract selectedContract = tableContract.getSelectionModel().getSelectedItem();
        if (selectedContract != null) {
            boolean okClicked = mainApp.showContractEditDialog(selectedContract);
            if (okClicked) {
                showContractDetails(selectedContract);
                FileManager.saveContractsToFile(mainApp.getContractData());
            }

        } else {
            // Нічого не вибрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Нічого не обрано");
            alert.setHeaderText("Контракт не було обрано");
            alert.setContentText("Будь ласка оберіть контракт в таблиці.");
            alert.showAndWait();
        }
    }
}
