package com.example.tp1_conception;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class AccueilController {
    @FXML private StackPane anchreImage;
    @FXML private ImageView imageAccueil;

    public void initialize(){
        imageAccueil.setFitWidth(anchreImage.getWidth());
    }

}
