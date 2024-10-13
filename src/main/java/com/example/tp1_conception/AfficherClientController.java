package com.example.tp1_conception;

import com.example.tp1_conception_data.Client;
import com.example.tp1_conception_data.Document;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AfficherClientController {
    @FXML private ComboBox<Client> comboClient;
    @FXML private AnchorPane zoneResultat;
    @FXML private VBox zoneResultatRetour;
    @FXML private Label labelReference;
    @FXML private  Label labelNom;
    @FXML private Label labelDate;
    @FXML private Label labelEmail;
    @FXML private Label labelTel;
    @FXML private Label labelAdresse;
    @FXML private Label labelUser;
    @FXML private Label labelProfession;

    private ObservableList<Client> listeClient;

    public AfficherClientController(){
        listeClient = FXCollections.observableArrayList(HelloController.gestionnaireBibliotheque.getListeClient().values());
    }

    public void initialize() {
        comboClient.setItems(listeClient);

        //evenements sur les elements de l'interface
        comboClient.setOnAction(actionEvent -> {
            zoneResultatRetour.getChildren().clear();
            zoneResultat.setVisible(true);
            afficheResultat();
            afficheResultat1();
        });

    }

    private void afficheResultat1() {
        Client client = comboClient.getValue();
        Date datenaiss = client.getDateNaissance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = dateFormat.format(datenaiss);

        String adresse = client.getAdresse().getNumeroUnite() + " " + client.getAdresse().getRue()+ " " + client.getAdresse().getCodePostal();
        labelReference.setText(client.getReference());
        labelNom.setText(client.getNom());
        labelDate.setText(dateString);
        labelProfession.setText(client.getProfession());
        labelEmail.setText(client.getEmail());
        labelAdresse.setText(adresse);
        labelTel.setText(client.getTelephone());
        labelUser.setText(client.getUsername());
    }

    private void afficheResultat() {
        Client client = comboClient.getValue();
        String t = HelloController.gestionnaireBibliotheque.getListePret().isEmpty() ?
                client.getNom()+" n'a aucun document preter" : "Liste des Documents prêtés par "+client.getNom();
        Label labClient = new Label(t);
        labClient.setFont(new Font(16.0));

        GridPane grille = new GridPane();
        grille.setVgap(10.0);
        grille.setHgap(10.0);
        grille.setAlignment(Pos.CENTER);
        Text text0 = new Text("Code document");
        text0.setFont(new Font("bold",14));
        grille.add(text0, 0, 0);

        Text text1 = new Text("Titre document");
        text1.setFont(new Font("bold",14));
        grille.add(text1, 1, 0);

        Text text2 = new Text("Date limite de retour");
        text2.setFont(new Font("bold",14));
        grille.add(text2, 2, 0);

        Text text3 = new Text("Retard / penalité");
        text3.setFont(new Font("bold",14));
        grille.add(text3, 3, 0);

        Text text4 = new Text("Cocher si retourné");
        text4.setFont(new Font("bold",14));
        grille.add(text4, 4, 0);

        ArrayList<Document> listeDoc = HelloController.gestionnaireBibliotheque.getListePret().get(client);
        System.out.println(HelloController.gestionnaireBibliotheque.getListePret().values());
        System.out.println(client);
        System.out.println(listeDoc);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        if (listeDoc!=null) {
            for (int i = 0; i < listeDoc.size(); i++) {
                Document doc = listeDoc.get(i);
                grille.add(new Text(doc.getCote()), 0, i + 1);
                grille.add(new Text(doc.getTitre()), 1, i + 1);
                grille.add(new Text(dateFormat.format(doc.getDatePret())), 2, i + 1);

                long diffEnMillis = Math.abs(now.getTime() - doc.getDatePret().getTime());
                long diff = TimeUnit.DAYS.convert(diffEnMillis, TimeUnit.MILLISECONDS);
                grille.add(new Text(diff+"Jour / 0$"), 3, i + 1);
                CheckBox checkBox = new CheckBox();
                grille.add(checkBox, 4, i + 1);

                checkBox.setOnAction(actionEvent -> {
                    retournerDocument(comboClient.getValue() ,doc);
                });
            }
        }
        zoneResultatRetour.getChildren().addAll(labClient, grille);

    }

    private void retournerDocument(Client client, Document doc) {
       ArrayList<Document> liste = HelloController.gestionnaireBibliotheque.getListePret().get(client);
       liste.remove(doc);
       HelloController.gestionnaireBibliotheque.getListePret().replace(client, liste);

        doc.ajouteQuantite(1);
        HelloController.gestionnaireBibliotheque.getListeDocument().replace(doc.getCote(), doc);
        zoneResultatRetour.getChildren().clear();
        HelloController.afficheBoite("Document retourné par le client", "inform");
        afficheResultat();
    }

}
