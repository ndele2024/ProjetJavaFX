package com.example.tp1_conception;

import com.example.tp1_conception_data.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class IndexController {
    @FXML private TextField userConnexion;
    @FXML private TextField passwordConnexion;
    @FXML private Button connexionButton;
    @FXML private Label errorConnexion;
    @FXML private TextField passwordShow;
    @FXML private ImageView hidePasswordImage;
    @FXML private ImageView viewPasswordImage;

    public void initialize(){
        userConnexion.setOnKeyReleased(keyEvent -> {
            //int ch = (int) keyEvent.getText().toCharArray()[0];
            if (keyEvent.getText().toCharArray().length > 0)
            {
                if(keyEvent.getText().toCharArray()[0] == 13){
                    try {
                        connexion();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        passwordConnexion.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getText().toCharArray().length > 0)
            {
                if(keyEvent.getText().toCharArray()[0] == 13){
                    try {
                        connexion();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            passwordShow.setText(passwordConnexion.getText());
        });

        passwordShow.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getText().toCharArray().length > 0)
            {
                if(keyEvent.getText().toCharArray()[0] == 13){
                    try {
                        connexion();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            passwordConnexion.setText(passwordShow.getText());
        });

        connexionButton.setOnAction(actionEvent -> {
            try {
                connexion();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        viewPasswordImage.setOnMouseClicked(mouseEvent -> {
            passwordShow.setVisible(true);
            passwordConnexion.setVisible(false);
            hidePasswordImage.setVisible(true);
            viewPasswordImage.setVisible(false);
        });

        hidePasswordImage.setOnMouseClicked(mouseEvent -> {
            passwordShow.setVisible(false);
            passwordConnexion.setVisible(true);
            hidePasswordImage.setVisible(false);
            viewPasswordImage.setVisible(true);
        });

    }

    private void connexion() throws IOException {
        String user = userConnexion.getText();
        String pass = passwordConnexion.getText();
        if (!user.equals(Utilisateur.defaultUser.getUser()) || !pass.equals(Utilisateur.defaultUser.getPassword())) {
            errorConnexion.setText("Nom d'utilisateur ou mot de passe incorrect");
            errorConnexion.setVisible(true);
        }
        else {
            //connexion de l'utilisateur
            Parent racine = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            HelloApplication.getInstance().getStage().getScene().setRoot(racine);
        }
    }

    private void activeDesactiveBouton() {
        connexionButton.setDisable(
                userConnexion.getText().isEmpty() || passwordConnexion.getText().isEmpty()
        );
    }

}
