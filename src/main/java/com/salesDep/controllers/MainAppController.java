package com.salesDep.controllers;

import com.salesDep.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainAppController extends MainApp {
    public Button buttonCustomerList;
    public Button buttonHighestValue;
    public Button buttonLongestTerm;
    public Button buttonGreatestDemand;
    public Button buttonAverageAmount;
    public Button buttonSalesEngineerList;
    @FXML
    private Button buttonContractList;

    private MainApp mainApp;

    @FXML
    private void initialize() {
        buttonContractList.setOnAction(e -> openContractListWindow());
        buttonCustomerList.setOnAction(e -> openCustomerListWindow());
        buttonSalesEngineerList.setOnAction(e -> openSalesEngineerListWindow());
    }

    private void openContractListWindow() {
        try {
            // Завантаження FXML-файлу та створення сцени
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ContractList.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Список контрактів");
            stage.setScene(new Scene(root));

            // Показати вікно
            stage.show();

            ContractListController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openCustomerListWindow() {
        try {
            // Завантаження FXML-файлу та створення сцени
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CustomerList.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Список клієнтів");
            stage.setScene(new Scene(root));

            // Показати вікно
            stage.show();

            CustomerListController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openSalesEngineerListWindow() {
        try {
            // Завантаження FXML-файлу та створення сцени
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SalesEngineerList.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Список інженерів з продажу");
            stage.setScene(new Scene(root));

            // Показати вікно
            stage.show();

            SalesEngineerListController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAverageAmountButtonClick() {
        try {
            // Завантаження FXML-файлу нового вікна
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AverageAmount.fxml"));
            AnchorPane pane = loader.load();

            // Створення нового вікна
            Stage stage = new Stage();
            stage.setTitle("Середня кількість продукції в контракті");
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            // Отримання контролера нового вікна
            AverageAmountController amountController = loader.getController();
            amountController.prepareWindow();

            // Показ нового вікна
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleHighestValueButtonClick() {
        try {
            // Завантаження FXML-файлу нового вікна
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HighestValue.fxml"));
            AnchorPane pane = loader.load();

            // Створення нового вікна
            Stage stage = new Stage();
            stage.setTitle("Найбільша вартість контракту");
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            // Отримання контролера нового вікна
            HighestValueController valueController = loader.getController();
            valueController.prepareWindow();

            // Показ нового вікна
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLongestTermButtonClick() {
        try {
            // Завантаження FXML-файлу нового вікна
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LongestTerm.fxml"));
            AnchorPane pane = loader.load();

            // Створення нового вікна
            Stage stage = new Stage();
            stage.setTitle("Найдовший термін постваки");
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            // Отримання контролера нового вікна
            LongestTermController termController = loader.getController();
            termController.prepareWindow();

            // Показ нового вікна
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGreatestDemandButtonClick() {
        try {
            // Завантаження FXML-файлу нового вікна
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GreatestDemand.fxml"));
            AnchorPane pane = loader.load();

            // Створення нового вікна
            Stage stage = new Stage();
            stage.setTitle("Найбільший попит");
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            // Отримання контролера нового вікна
            GreatestDemandController demandController = loader.getController();
            demandController.prepareWindow();

            // Показ нового вікна
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
