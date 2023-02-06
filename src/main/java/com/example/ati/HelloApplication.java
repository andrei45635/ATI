package com.example.ati;

import com.example.ati.controller.HelloController;
import com.example.ati.repo.db.BedRepoDB;
import com.example.ati.repo.db.PacientRepoDB;
import com.example.ati.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 300);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        HelloController helloController = fxmlLoader.getController();
        helloController.setService(new Service(new PacientRepoDB(), new BedRepoDB()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}