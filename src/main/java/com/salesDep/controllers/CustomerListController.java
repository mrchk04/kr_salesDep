package com.salesDep.controllers;

import com.salesDep.FileManager;
import com.salesDep.MainApp;
import com.salesDep.model.Customer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class CustomerListController {
    public Label labelFullName;
    public Label labelNameOfCompany;
    public Label labelAddress;
    public Label labelPhoneNum;
    public TableView<Customer> tableCustomer;
    public TableColumn<Customer, String> tableNameCustomer;
    public TableColumn<Customer, String> tableIDCustomer;

    private MainApp mainApp;

    public CustomerListController() {
    }

    @FXML
    private void initialize() {
        //tableIDCustomer.setCellValueFactory(cellData -> cellData.getValue().customerIDProperty());
        tableNameCustomer.setCellValueFactory(cellData -> cellData.getValue().getFullName());
        showCustomerDetails(null);

        tableCustomer.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCustomerDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        List<Customer> customers = FileManager.readCustomersFromFile();
        mainApp.getCustomerData().addAll(customers);
        mainApp.setCustomerData(FXCollections.observableArrayList(customers));
        tableCustomer.setItems(mainApp.getCustomerData());
    }

    private void showCustomerDetails(Customer customer) {
        if (customer != null) {
            labelFullName.setText(customer.getFullName().get());
            labelNameOfCompany.setText(customer.getNameOfCompany().get());
            labelAddress.setText(customer.getAddress().get());
            labelPhoneNum.setText(customer.getPhoneNum().get());
        } else {
            labelFullName.setText("");
            labelNameOfCompany.setText("");
            labelAddress.setText("");
            labelPhoneNum.setText("");
        }
    }

    @FXML
    private void handleNewCustomer() {
        Customer tempPerson = new Customer();
        boolean okClicked = mainApp.showCustomerEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getCustomerData().add(tempPerson);
            FileManager.saveCustomersToFile(mainApp.getCustomerData());
        }
    }

    @FXML
    private void handleEditCustomer() {
        Customer selectedPerson = tableCustomer.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showCustomerEditDialog(selectedPerson);
            if (okClicked) {
                showCustomerDetails(selectedPerson);
                FileManager.saveCustomersToFile(mainApp.getCustomerData());
            }

        } else {
            // Нічого не вибрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Нічого не обрано");
            alert.setHeaderText("Клієнта не було обрано");
            alert.setContentText("Будь ласка оберіть клієнта в таблиці.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteCustomer() {
        int selectedIndex = tableCustomer.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Customer selectedCustomer = tableCustomer.getItems().remove(selectedIndex);
            FileManager.saveCustomersToFile(mainApp.getCustomerData());
            FileManager.deleteCustomerFromFile(selectedCustomer);
        } else {
            // Нічого не вибрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Нічого не обрано");
            alert.setHeaderText("Клієнта не було обрано");
            alert.setContentText("Будь ласка оберіть клієнта в таблиці.");
            alert.showAndWait();
        }
    }
}
