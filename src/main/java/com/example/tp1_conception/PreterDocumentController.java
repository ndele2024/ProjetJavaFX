package com.example.tp1_conception;

import com.example.tp1_conception_data.Client;
import com.example.tp1_conception_data.Document;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PreterDocumentController {
    @FXML private ComboBox<Client> comboClient;
    @FXML private TextField rechercherClient;
    @FXML private VBox vboxRechercher;
    @FXML private VBox vboxAjouter;
    @FXML private AnchorPane anchreRechercher;
    @FXML private AnchorPane anchreAjouter;

    private ObservableList<Client> listeClient;
    private ArrayList<Document> listePret;

    public PreterDocumentController(){
        listeClient = FXCollections.observableArrayList(HelloController.gestionnaireBibliotheque.getListeClient().values());
        listePret = new ArrayList<>();
    }

    public void initialize(){
        comboClient.setItems(listeClient);

        //evenements sur les elements de l'interface
        comboClient.setOnAction(actionEvent -> {
            afficheZoneRecherche();
            rechercherClient.setText("");
            listePret.clear();
            vboxAjouter.getChildren().clear();
            vboxRechercher.getChildren().clear();
        });

        rechercherClient.setOnKeyReleased(keyEvent -> {
            afficheResultatRecherche();
        });

        comboClient.itemsProperty().addListener(new ChangeListener<ObservableList<Client>>() {
            @Override
            public void changed(ObservableValue<? extends ObservableList<Client>> observableValue, ObservableList<Client> clients, ObservableList<Client> t1) {

            }
        });
    }

    private void afficheResultatRecherche() {
        String texte = rechercherClient.getText();
        ArrayList<Document> listeResulat = HelloController.gestionnaireBibliotheque.rechercherAllDocument(texte);
        vboxRechercher.getChildren().clear();
        vboxRechercher.setSpacing(8.0);
        if(!listeResulat.isEmpty()){
            Label lab1 = new Label("Résultat de la recherche : "+listeResulat.size()+" document(s) trouvé(s)");
            lab1.setWrapText(true);
            lab1.setTextFill(Color.GREEN);
            lab1.setFont(new Font(15.0));
            Label lab2 = new Label("Cocher/Décocher un livre pour l'ajouter/retirer à la liste des prêts");
            lab2.setWrapText(true);
            lab2.setFont(new Font(15.0));
            lab2.setStyle("-fx-font-weight: bold; -fx-text-fill: blue");
            vboxRechercher.getChildren().addAll(lab1, lab2);
            for (Document doc : listeResulat){
                //HBox hBox = new HBox();
                //hBox.setPrefWidth(450.0);
                CheckBox checkBox = new CheckBox(doc.getTitre()+" ; "+doc.getAuteur()+" ; "+doc.getEditeur()+" ; "+doc.getAnneeEdition());
                checkBox.setWrapText(true);
                checkBox.setFont(new Font(14.0));
                if(!doc.isDisponible(1)){
                    checkBox.setDisable(true);
                    checkBox.setText(checkBox.getText()+" (En rupture)");
                    checkBox.setTextFill(Color.RED);
                }
                if(listePret.contains(doc)){
                    checkBox.setSelected(true);
                }
                checkBox.setOnAction(actionEvent -> {
                    if(checkBox.isSelected()){
                        //HelloController.afficheBoite(doc.getAuteur()+doc.getTitre(), "inform");
                        doc.setDatePret(new Date());
                        doc.setRetourne(false);
                        listePret.add(doc);
                        updateListePret();
                    }
                    else{
                        listePret.remove(doc);
                        updateListePret();
                    }
                });
                //hBox.getChildren().addAll(checkBox);
                vboxRechercher.getChildren().add(checkBox);
                //hBox.getChildren().clear();
            }
        }
        else {
            Label lab1 = new Label("Résultat de la recherche : 0 document trouvé");
            lab1.setTextFill(Color.RED);
            lab1.setFont(new Font(14.0));
            vboxRechercher.getChildren().add(lab1);
        }
    }

    private void updateListePret() {
        vboxAjouter.getChildren().clear();
        vboxAjouter.setSpacing(8.0);
        for (Document doc : listePret){
            CheckBox checkBox = new CheckBox(doc.getTitre()+" ; "+doc.getAuteur());
            checkBox.setWrapText(true);
            checkBox.setFont(new Font(14.0));
            checkBox.setSelected(true);
            vboxAjouter.getChildren().add(checkBox);
            checkBox.setOnAction(actionEvent -> {
                listePret.remove(doc);
                updateListePret();
                afficheResultatRecherche();
            });
        }
        if(!listePret.isEmpty()){
            Button bouton = new Button();
            bouton.setText("Enregistrer");
            bouton.setId("ajouterButton");
            bouton.setStyle("-fx-background-color: lightgreen; -fx-font-size: 14px; ");
            bouton.setCursor(Cursor.HAND);
            //date limite de retour
            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 15);
            Date datelimite = cal.getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Label lab = new Label("Date limite de retour : "+cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.FRANCE)+" "+dateFormat.format(datelimite));
            lab.setFont(new Font(14.0));
            lab.setWrapText(true);
            HBox hBox = new HBox();
            hBox.setPadding(new Insets(20.0,0.0,0.0,10.0));
            hBox.setSpacing(20.0);
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().add(bouton);
            vboxAjouter.getChildren().addAll(hBox, lab);
            bouton.setOnAction(actionEvent -> {
                saveListePret();
            });
        }
    }

    private void saveListePret() {
        //System.out.println(listePret);
        Client client = comboClient.getValue();
        HelloController.gestionnaireBibliotheque.preterDocument(client, listePret);
        //mise a jour de la quantité de chaque doc
        for (Document doc : listePret){
            doc.diminueQuantite(1);
            HelloController.gestionnaireBibliotheque.getListeDocument().replace(doc.getCote(), doc);
        }
        HelloController.afficheBoite("Enregistrement Effectué avec succès", "inform");
        //listePret.clear();
        anchreAjouter.setVisible(false);
        anchreRechercher.setVisible(false);
    }

    private void afficheZoneRecherche() {
        anchreRechercher.setVisible(true);
        anchreAjouter.setVisible(true);
    }
}
