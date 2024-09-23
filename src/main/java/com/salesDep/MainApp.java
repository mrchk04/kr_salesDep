package com.salesDep;

import com.salesDep.controllers.ContractCreatorController;
import com.salesDep.controllers.CustomerCreatorController;
import com.salesDep.controllers.ProductCreatorController;
import com.salesDep.controllers.SalesEngineerCreatorController;
import com.salesDep.model.Contract;
import com.salesDep.model.Customer;
import com.salesDep.model.Product;
import com.salesDep.model.SalesEngineer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("MainApp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Відділ збуту готової продукції");
        stage.setScene(scene);
        stage.show();
    }
    private ObservableList<Contract> contractData = FXCollections.observableArrayList();
    private ObservableList<Customer> customerData = FXCollections.observableArrayList();
    private ObservableList<SalesEngineer> salesEngineerData = FXCollections.observableArrayList();

    public MainApp() {
    }

    public void setContractData(ObservableList<Contract> contracts) {
        contractData.setAll(contracts);
    }
    public void setCustomerData(ObservableList<Customer> customers) {
        customerData.setAll(customers);
    }
    public void setSalesEngineerData(ObservableList<SalesEngineer> salesEngineers) {
        salesEngineerData.setAll(salesEngineers);
    }
    public ObservableList<Contract> getContractData() {
        return contractData;
    }
    public ObservableList<Customer> getCustomerData() {
        return customerData;
    }
    public ObservableList<SalesEngineer> getSalesEngineerData(){
        return salesEngineerData;
    }

    public MainApp(ObservableList<Contract> contractData, ObservableList<Customer> customerData, ObservableList<SalesEngineer> salesEngineerData) {
        this.contractData = contractData;
        this.customerData = customerData;
        this.salesEngineerData = salesEngineerData;
    }

    public boolean showCustomerEditDialog(Customer customer) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("controllers/CustomerCreator.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            CustomerCreatorController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCustomer(customer);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showEngineerEditDialog(SalesEngineer salesEngineer) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("controllers/SalesEngineerCreator.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            SalesEngineerCreatorController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSalesEngineer(salesEngineer);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showContractEditDialog(Contract contract) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("controllers/ContractCreator.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Contract");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ContractCreatorController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setContract(contract);

            controller.setMainApp(this);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showProductEditDialog(Product product) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("controllers/ProductCreator.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ProductCreatorController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(product);

            controller.setMainApp(this);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}