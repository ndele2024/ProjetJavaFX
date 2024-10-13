package com.example.tp1_conception;

import com.example.tp1_conception_data.GestionnaireBibliotheque;
import com.example.tp1_conception_data.Utilisateur;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class HelloController {
    @FXML
    private ImageView boutonPrecedent;
    @FXML
    private ImageView boutonSuivant;
    @FXML
    private Button accueilButton;
    @FXML
    private Button ajouterClientButton;
    @FXML
    private Button afficherClientbutton;
    @FXML
    private Button ajouterDocButton;
    @FXML
    private Button preterDocButton;
    @FXML
    private Button rechercherDocButon;

    @FXML
    private MenuItem ajouterClientMenu;
    @FXML
    private MenuItem afficherClientMenu;
    @FXML
    private MenuItem ajouterDocMenu;
    @FXML
    private MenuItem rechercherDocMenu;
    @FXML
    private MenuItem preterDocMenu;
    @FXML private MenuItem deconnexionMenu;
    @FXML private MenuItem fermerMenu;
    @FXML private Label labelDeconnexion;
    @FXML private Label labelZoom;
    @FXML private MenuBar menuApp;

    @FXML private BorderPane borderPaneHome;
    @FXML private HBox zoneDeconnexion;
    @FXML private AnchorPane zoneTravail;
    @FXML private Slider zoomSlider;
    @FXML private Label utilisateurLabel;
    @FXML private CheckMenuItem modeSombreMenu;
    @FXML private HBox zoneZoom;
    @FXML private AnchorPane zoneApplication; //noir
    @FXML private AnchorPane zoneMenu; //noir
    @FXML private AnchorPane zoneOutils; //blanc
    @FXML private AnchorPane zoneAriane; //noir
    @FXML private AnchorPane zoneBoutonNavigation1; //blanc
    @FXML private AnchorPane zoneBoutonNavigation2; //noir

    //Variable de la classe
    private ArrayList<String> listeNavigation;
    static GestionnaireBibliotheque gestionnaireBibliotheque;

    //AjouteClientController ajouteClientController;
    private int positionNavigation = -1;
    private String styleBoutonNavig;
    private int indexNavig = 1;


    public HelloController() throws ParseException {
        this.styleBoutonNavig = "-fx-border-color: #80d790;" +
                " -fx-text-fill: #3737af;" +
                " -fx-border-width: 3px 0px 3px 3px;" +
                " -fx-background-color: #ecf7ee";
        listeNavigation = new ArrayList<>();
        gestionnaireBibliotheque = new GestionnaireBibliotheque();
    }

    public void setBorderPaneHome(AnchorPane anchorPane){
        this.borderPaneHome.setCenter(anchorPane);
    }

    @FXML
    public void initialize() throws IOException {
        //initialisation de la page d'accueil
        AnchorPane  anchor = FXMLLoader.load(getClass().getResource("accueil.fxml"));
        borderPaneHome.setCenter(anchor);
        ajouterUrl("accueil");
        styleBouton(1);
        zoneZoom.setVisible(true);
        utilisateurLabel.setText(Utilisateur.defaultUser.getNom());

        //evenement sur les Boutons de navigation
        boutonPrecedent.setOnMouseClicked(mouseEvent -> {
            try {
                precedent();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        boutonSuivant.setOnMouseClicked(mouseEvent -> {
            try {
                suivant();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        accueilButton.setOnAction(actionEvent -> {
            try {
                navigation("accueil", true);
                indexNavig = 1;
                styleBouton(indexNavig);
                zoneZoom.setVisible(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ajouterClientButton.setOnAction(actionEvent -> {
            try {
                navigation("ajouteClient", true);
                indexNavig=5;
                styleBouton(indexNavig);
                zoneZoom.setVisible(false);
                System.out.println(HelloController.gestionnaireBibliotheque.getListeClient().values().stream().toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        afficherClientbutton.setOnAction(actionEvent -> {
            try {
                navigation("afficherClient", true);
                indexNavig=6;
                styleBouton(indexNavig);
                zoneZoom.setVisible(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        ajouterDocButton.setOnAction(actionEvent -> {
            try {
                navigation("ajouteDocument", true);
                indexNavig=2;
                styleBouton(indexNavig);
                zoneZoom.setVisible(false);
                //System.out.println(HelloController.gestionnaireBibliotheque.getListeDocument().values());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        preterDocButton.setOnAction(actionEvent -> {
            try {
                navigation("preterDocument", true);
                indexNavig=3;
                styleBouton(indexNavig);
                zoneZoom.setVisible(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        rechercherDocButon.setOnAction(actionEvent -> {
            try {
                navigation("rechercherDocument", true);
                indexNavig=4;
                styleBouton(indexNavig);
                zoneZoom.setVisible(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //evenements sur les menus
        afficherClientMenu.setOnAction(actionEvent -> {
            try {
                navigation("afficherClient", true);
                indexNavig=6;
                styleBouton(indexNavig);
                zoneZoom.setVisible(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        ajouterClientMenu.setOnAction(actionEvent -> {
            try {
                navigation("ajouteClient", true);
                indexNavig=5;
                styleBouton(indexNavig);
                zoneZoom.setVisible(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        ajouterDocMenu.setOnAction(actionEvent -> {
            try {
                navigation("ajouteDocument", true);
                indexNavig=2;
                styleBouton(indexNavig);
                zoneZoom.setVisible(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        preterDocMenu.setOnAction(actionEvent -> {
            try {
                navigation("preterDocument", true);
                indexNavig=3;
                styleBouton(indexNavig);
                zoneZoom.setVisible(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        rechercherDocMenu.setOnAction(actionEvent -> {
            try {
                navigation("rechercherDocument", true);
                indexNavig=4;
                styleBouton(indexNavig);
                zoneZoom.setVisible(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        zoneDeconnexion.setOnMouseClicked(mouseEvent -> {
            try {
                deconnexion();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        fermerMenu.setOnAction(actionEvent -> {
            Platform.exit();
            System.exit(0);
        });

        deconnexionMenu.setOnAction(actionEvent -> {
            try {
                deconnexion();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        /*zoneTravail.setOnScroll(scrollEvent -> {
            System.out.print(scrollEvent.getDeltaX() + " " +scrollEvent.getDeltaY());
            //zoneTravail.setScaleX(zoneTravail.getScaleX()+1.0);
            //zoneTravail.setScaleY(zoneTravail.getScaleY()+1.0);
        });*/

        // listener du slider
        zoomSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    borderPaneHome.getCenter().setScaleX((double) newValue/150);
                    borderPaneHome.getCenter().setScaleY((double) newValue/150);
        });

        modeSombreMenu.setOnAction(actionEvent -> {
            activDesactivMoseSombre();
        });

    }

    private void activDesactivMoseSombre() {
        if(modeSombreMenu.isSelected()){
            zoneApplication.getStyleClass().remove("fondZoneTravail");
            zoneApplication.getStyleClass().add("sombre");
            zoneMenu.getStyleClass().add("sombre");
            menuApp.getStyleClass().remove("tranparent");
            menuApp.getStyleClass().add("blanc");
            labelDeconnexion.getStyleClass().remove("textDanger");
            labelZoom.setStyle("-fx-text-fill: white");
            zoomSlider.setStyle("-fx-text-fill: white");
            zoneOutils.getStyleClass().remove("fondOutils");
            zoneOutils.getStyleClass().add("blanc");
            zoneAriane.getStyleClass().remove("blanc");
            zoneAriane.getStyleClass().add("sombre");
            zoneBoutonNavigation1.getStyleClass().remove("fondZoneTravail");
            zoneBoutonNavigation1.getStyleClass().add("blanc");
            zoneBoutonNavigation2.getStyleClass().remove("fondOutils");
            zoneBoutonNavigation2.getStyleClass().add("sombre");
            zoneTravail.getStyleClass().remove("fondZoneTravail");
            zoneTravail.getStyleClass().add("blanc");
            accueilButton.setDefaultButton(false);
            ajouterDocButton.setDefaultButton(false);
            preterDocButton.setDefaultButton(false);
            rechercherDocButon.setDefaultButton(false);
            ajouterClientButton.setDefaultButton(false);
            afficherClientbutton.setDefaultButton(false);
            setStyleBoutonNavig("-fx-border-color: #959695;" +
                    " -fx-text-fill: #0c0c0c;" +
                    " -fx-border-width: 3px 0px 3px 3px;" +
                    " -fx-background-color: #ffffff");
            styleBouton(indexNavig);
        }
        else {
            zoneApplication.getStyleClass().remove("sombre");
            zoneApplication.getStyleClass().add("fondZoneTravail");
            zoneMenu.getStyleClass().remove("sombre");
            menuApp.getStyleClass().add("tranparent");
            menuApp.getStyleClass().remove("blanc");
            labelDeconnexion.getStyleClass().add("textDanger");
            labelZoom.setStyle("-fx-text-fill: black");
            zoomSlider.setStyle("-fx-text-fill: black");
            zoneOutils.getStyleClass().remove("blanc");
            zoneOutils.getStyleClass().add("fondOutils");
            zoneAriane.getStyleClass().remove("sombre");
            zoneAriane.getStyleClass().add("blanc");
            zoneBoutonNavigation1.getStyleClass().add("fondZoneTravail");
            zoneBoutonNavigation1.getStyleClass().remove("sombre");
            zoneBoutonNavigation2.getStyleClass().remove("sombre");
            zoneBoutonNavigation2.getStyleClass().add("fondOutils");
            zoneTravail.getStyleClass().add("fondZoneTravail");
            zoneTravail.getStyleClass().remove("blanc");
            accueilButton.setDefaultButton(true);
            ajouterDocButton.setDefaultButton(true);
            preterDocButton.setDefaultButton(true);
            rechercherDocButon.setDefaultButton(true);
            ajouterClientButton.setDefaultButton(true);
            afficherClientbutton.setDefaultButton(true);
            setStyleBoutonNavig("-fx-border-color: #80d790;" +
                    " -fx-text-fill: #3737af;" +
                    " -fx-border-width: 3px 0px 3px 3px;" +
                    " -fx-background-color: #ecf7ee");
            styleBouton(indexNavig);
        }
    }

    private void styleBouton(int i) {
        switch (i){
            case 1:
                accueilButton.setStyle(styleBoutonNavig);
                ajouterDocButton.setStyle("");
                preterDocButton.setStyle("");
                rechercherDocButon.setStyle("");
                ajouterClientButton.setStyle("");
                afficherClientbutton.setStyle("");
                break;
            case 2:
                accueilButton.setStyle("");
                ajouterDocButton.setStyle(styleBoutonNavig);
                preterDocButton.setStyle("");
                rechercherDocButon.setStyle("");
                ajouterClientButton.setStyle("");
                afficherClientbutton.setStyle("");
                break;
            case 3:
                accueilButton.setStyle("");
                ajouterDocButton.setStyle("");
                preterDocButton.setStyle(styleBoutonNavig);
                rechercherDocButon.setStyle("");
                ajouterClientButton.setStyle("");
                afficherClientbutton.setStyle("");
                break;
            case 4:
                accueilButton.setStyle("");
                ajouterDocButton.setStyle("");
                preterDocButton.setStyle("");
                rechercherDocButon.setStyle(styleBoutonNavig);
                ajouterClientButton.setStyle("");
                afficherClientbutton.setStyle("");
                break;
            case 5:
                accueilButton.setStyle("");
                ajouterDocButton.setStyle("");
                preterDocButton.setStyle("");
                rechercherDocButon.setStyle("");
                ajouterClientButton.setStyle(styleBoutonNavig);
                afficherClientbutton.setStyle("");
                break;
            case 6:
                accueilButton.setStyle("");
                ajouterDocButton.setStyle("");
                preterDocButton.setStyle("");
                rechercherDocButon.setStyle("");
                ajouterClientButton.setStyle("");
                afficherClientbutton.setStyle(styleBoutonNavig);
                break;
        }
    }

    private void deconnexion() throws IOException {
        Parent racine = FXMLLoader.load(getClass().getResource("index.fxml"));
        HelloApplication.getInstance().getStage().getScene().setRoot(racine);
        listeNavigation.clear();
        positionNavigation = -1;
    }

    private void suivant() throws IOException {
        String url;
        if (positionNavigation < listeNavigation.size()-1) {
            url = listeNavigation.get(++positionNavigation);
            navigation(url, false);
        }
    }

    private void precedent() throws IOException {
        String url;
        if (positionNavigation > 0) {
            url = listeNavigation.get(--positionNavigation);
            navigation(url, false);
        }
    }

    public void navigation(String url, boolean isadd) throws IOException {
        AnchorPane anchor = null;
        switch (url) {
            case "accueil":
                anchor = FXMLLoader.load(getClass().getResource("accueil.fxml"));
                break;
            case "ajouteClient":
                anchor = FXMLLoader.load(getClass().getResource("ajouteClient.fxml"));
                break;
            case "afficherClient":
                anchor = FXMLLoader.load(getClass().getResource("afficherClient.fxml"));
                break;
            case "ajouteDocument":
                anchor = FXMLLoader.load(getClass().getResource("ajouteDocument.fxml"));
                break;
            case "preterDocument":
                anchor = FXMLLoader.load(getClass().getResource("preterDocument.fxml"));
                break;
            case "rechercherDocument":
                anchor = FXMLLoader.load(getClass().getResource("rechercherDocument.fxml"));
                break;

        }
        //affichage de la page
        borderPaneHome.setCenter(anchor);
        //ajout a la liste de navigation
        if(isadd)
            ajouterUrl(url);
        //System.out.println(listeNavigation);
    }

    //ajoute une URL de navigation dans la liste
    private void ajouterUrl(String url) {
        if (listeNavigation.isEmpty()){
            listeNavigation.add(url);
        }
        else {
            if (!listeNavigation.get(listeNavigation.size()-1).equals(url)){
                listeNavigation.add(url);
            }
        }
        positionNavigation = listeNavigation.size()-1;
        changeBoutonPrecSuiv();
    }

    //modifie les boutons precedent et suivants selon qu'il sont actif ou pas
    private void changeBoutonPrecSuiv(){
        //activation desactivation du bouton precedent ou suivant
        if ((listeNavigation.isEmpty())||(positionNavigation <= 0)) {
            boutonPrecedent.setImage(new Image(String.valueOf(getClass().getResource("images/icons8-annuler-961.png"))));
        }
        else {
            boutonPrecedent.setImage(new Image(String.valueOf(getClass().getResource("images/icons8-annuler-96.png"))));
        }
        if((listeNavigation.isEmpty())||(positionNavigation == listeNavigation.size()-1)){
            boutonSuivant.setImage(new Image(String.valueOf(getClass().getResource("images/icons8-refaire-961.png"))));
        }
        else {
            boutonSuivant.setImage(new Image(String.valueOf(getClass().getResource("images/icons8-refaire-96.png"))));
        }
    }

    public void setStyleBoutonNavig(String styleBoutonNavig) {
        this.styleBoutonNavig = styleBoutonNavig;
    }

    //affiche une boite de dialogue avec un message
    public static void afficheBoite(String msg, String type) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        switch (type) {
            case "error":
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText(msg);
                alert.show();
                break;
            case "confirm":
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText(msg);
                alert.show();
                break;
            case "inform":
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText(msg);
                alert.show();
                break;
            default:
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText(msg);
                alert.show();
                break;
        }
    }

}