package com.example.tp1_conception;

import com.example.tp1_conception_data.Document;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Objects;

public class AjouteDocumentController {
    @FXML private TextField codeDocument;
    @FXML private TextField titreDocument;
    @FXML private TextField auteurDocument;
    @FXML private TextField editeurDocument;
    @FXML private ChoiceBox<Integer> anneeDocument;
    @FXML private ChoiceBox<String> typeDocument;
    @FXML private Spinner<Integer> quantiteDocument;
    @FXML private Button annulerAddDocument;
    @FXML private Button suivantAddDocument;

    private ObservableList<Integer> listeAnnee;
    private ObservableList<String> listeTypeDoc;

    public AjouteDocumentController(){
        ArrayList<Integer> anneeliste = new ArrayList<>();
        for (int i = 1990; i <= 2024; i++)
            anneeliste.add(i);
        listeAnnee = FXCollections.observableArrayList(anneeliste);
        listeTypeDoc = FXCollections.observableArrayList("Livre", "Roman", "Magazine", "Manuel scolaire", "Bande dessinée");

    }

    public void initialize(){
        //initialisation des choice box
        anneeDocument.setItems(listeAnnee);
        typeDocument.setItems(listeTypeDoc);
        quantiteDocument.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100));

        //evenement sur les boutons anniuler et suivant
        annulerAddDocument.setOnAction(actionEvent -> {
            annulerFormulaire();
        });
        suivantAddDocument.setOnAction(actionEvent -> {
            enregisterDocument();
        });
        codeDocument.setOnKeyReleased(keyEvent -> {
            activerBoutonSuivant();
        });
        titreDocument.setOnKeyReleased(keyEvent -> {
            activerBoutonSuivant();
        });
        auteurDocument.setOnKeyReleased(keyEvent -> {
            activerBoutonSuivant();
        });
        editeurDocument.setOnKeyReleased(keyEvent -> {
            activerBoutonSuivant();
        });
        anneeDocument.setOnAction(actionEvent -> {
            activerBoutonSuivant();
        });
        typeDocument.setOnAction(actionEvent -> {
            activerBoutonSuivant();
            genererCode();
        });
        quantiteDocument.setOnKeyReleased(actionEvent -> {
            activerBoutonSuivant();
        });




    }

    private void genererCode() {
        if(!Objects.equals(typeDocument.getValue(), "--Selectionner--")) {
            String str = typeDocument.getValue().substring(0, 3);
            int x = HelloController.gestionnaireBibliotheque.getListeDocument().size() + 1;
            if (x < 10) str += "00" + x;
            else if (x < 100) str += "0" + x;
            else str += "" + x;
            codeDocument.setText(str);
        }
    }

    private void activerBoutonSuivant() {
        suivantAddDocument.setDisable(
                titreDocument.getText().isEmpty()||auteurDocument.getText().isEmpty()
                ||editeurDocument.getText().isEmpty()|| typeDocument.getValue().equals("--Selectionner--")
                ||anneeDocument.getValue()==1990
        );
    }

    private void enregisterDocument() {
        Document doc = new Document(codeDocument.getText(), titreDocument.getText(), auteurDocument.getText(), editeurDocument.getText(), anneeDocument.getValue(), typeDocument.getValue(), quantiteDocument.getValueFactory().getValue());
        HelloController.gestionnaireBibliotheque.ajouterDocument(doc);
        HelloController.afficheBoite("Enregistrement du document effectué", "inform");
        annulerFormulaire();
    }

    private void annulerFormulaire() {
        codeDocument.setText("");
        titreDocument.setText("");
        auteurDocument.setText("");
        editeurDocument.setText("");
        anneeDocument.setValue(1990);
        typeDocument.setValue("--Selectionner--");
        quantiteDocument.getValueFactory().setValue(1);
        suivantAddDocument.setDisable(true);
    }

}
