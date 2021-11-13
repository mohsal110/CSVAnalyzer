package com.example.csvanalyzer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 450);
        stage.setTitle("CSV Analyzer");
        stage.setScene(scene);
        stage.show();
        HelloController passwordController = fxmlLoader.getController();
//        passwordController.setCurrentInfo(info);
    }

    public static void main(String[] args) {
        launch();
    }
}