package com.salesDep.controllers;

import com.salesDep.FileManager;
import com.salesDep.MainApp;
import com.salesDep.model.SalesEngineer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class SalesEngineerListController {
    public Label labelFullName;
    public Label labelNameOfCompany;
    public Label labelAddress;
    public Label labelPhoneNum;
    public TableView<SalesEngineer> tableEngineer;
    public TableColumn<SalesEngineer, String> tableName;
    public Label labelExp;

    private MainApp mainApp;

    public SalesEngineerListController() {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        List<SalesEngineer> salesEngineers = FileManager.readSalesEngineerFromFile();
        mainApp.getSalesEngineerData().addAll(salesEngineers);
        mainApp.setSalesEngineerData(FXCollections.observableArrayList(salesEngineers));
        tableEngineer.setItems(mainApp.getSalesEngineerData());
    }

    @FXML
    private void initialize() {
        tableName.setCellValueFactory(cellData -> cellData.getValue().getFullName());
        showSalesEngineerDetails(null);

        tableEngineer.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSalesEngineerDetails(newValue));
    }

    private void showSalesEngineerDetails(SalesEngineer salesEngineer) {
        if (salesEngineer != null) {
            labelFullName.setText(salesEngineer.getFullName().get());
            labelNameOfCompany.setText(salesEngineer.getNameOfCompany().get());
            labelAddress.setText(salesEngineer.getAddress().get());
            labelPhoneNum.setText(salesEngineer.getPhoneNum().get());
            labelExp.setText(Integer.toString(salesEngineer.getExperience().get()));
        } else {
            labelFullName.setText("");
            labelNameOfCompany.setText("");
            labelAddress.setText("");
            labelPhoneNum.setText("");
            labelExp.setText("");
        }
    }

    @FXML
    private void handleNewEngineer() {
        SalesEngineer tempEngineer = new SalesEngineer();
        boolean okClicked = mainApp.showEngineerEditDialog(tempEngineer);
        if (okClicked) {
            mainApp.getSalesEngineerData().add(tempEngineer);
            FileManager.saveSalesEngineerToFile(mainApp.getSalesEngineerData());
        }
    }

    @FXML
    private void handleEditEngineer() {
        SalesEngineer selectedPerson = tableEngineer.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showEngineerEditDialog(selectedPerson);
            if (okClicked) {
                showSalesEngineerDetails(selectedPerson);
                FileManager.saveSalesEngineerToFile(mainApp.getSalesEngineerData());
            }

        } else {
            // Нічого не вибрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void deleteEngineer() {
        int selectedIndex = tableEngineer.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            SalesEngineer selectedSalesEngineer = tableEngineer.getItems().remove(selectedIndex);
            FileManager.saveSalesEngineerToFile(mainApp.getSalesEngineerData());
            FileManager.deleteSalesEngineerFromFile(selectedSalesEngineer);
        } else {
            // Нічого не вибрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Нічого не обрано");
            alert.setHeaderText("Інженера з продажів не було обрано");
            alert.setContentText("Будь ласка оберіть інженера з продажів в таблиці.");
            alert.showAndWait();
        }
    }
}
