package com.example.tp1_conception;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static HelloApplication instance;

    private Stage stage;

    public HelloApplication() {
        instance = this;
    }

    public static HelloApplication getInstance() {
        return instance;
    }



    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("index.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("XYZ BILIOTHEQUE & LIBRAIRIE");
        stage.getIcons().add(new Image(String.valueOf(HelloApplication.class.getResource("images/iconeBiblio.png"))));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getStage() {
        return stage;
    }
}