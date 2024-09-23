package com.salesDep;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SalesEngineerList extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SalesEngineer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Список замовників");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
