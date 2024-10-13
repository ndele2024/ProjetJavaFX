package com.example.tp1_conception;

import com.example.tp1_conception_data.Document;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class RechercherDocumentController {
    @FXML private TextField titreDoc;
    @FXML private TextField auteurDoc;
    @FXML private ChoiceBox<String> anneeDoc;
    @FXML private ChoiceBox<String> typeDoc;
    @FXML private Button annulerBoutton;
    @FXML private Button rechercherBouton;
    @FXML private VBox zoneResultat;

    private ObservableList<String> listeAnnee;
    private ObservableList<String> listeTypeDoc;

    public RechercherDocumentController() {
        ArrayList<String> anneeliste = new ArrayList<>();
        for (int i = 1990; i <= 2024; i++)
            anneeliste.add(i+"");
        listeAnnee = FXCollections.observableArrayList(anneeliste);
        listeTypeDoc = FXCollections.observableArrayList("Livre", "Roman", "Magazine", "Manuel scolaire", "Bande dessinée");
    }

    public void initialize(){
        anneeDoc.setItems(listeAnnee);
        typeDoc.setItems(listeTypeDoc);

        annulerBoutton.setOnAction(actionEvent -> {
            effacerFormulaire();
        });

        titreDoc.setOnKeyReleased(keyEvent -> {
            activerBoutonRechercher();
        });
        auteurDoc.setOnKeyReleased(keyEvent -> {
            activerBoutonRechercher();
        });
        anneeDoc.setOnAction(actionEvent -> {
            activerBoutonRechercher();
        });
        typeDoc.setOnAction(actionEvent -> {
            activerBoutonRechercher();
        });
        rechercherBouton.setOnAction(actionEvent -> {
            rechercherDocument();
        });
    }

    private void rechercherDocument() {
        zoneResultat.getChildren().clear();
        zoneResultat.setSpacing(10);
        String titre = titreDoc.getText();
        String auteur = auteurDoc.getText();
        String type = typeDoc.getValue();
        String annee = anneeDoc.getValue();

        ArrayList<Document> resultat = HelloController.gestionnaireBibliotheque.rechercherAllDocument(titre);
        ArrayList<Document> newResult = HelloController.gestionnaireBibliotheque.rechercherAllDocument(auteur);
        for (Document document : newResult){
            if (!resultat.contains(document)){
                resultat.add(document);
            }
        }
        //resultat.addAll();
        Label lab = new Label();
        lab.setFont(new Font(16.0));
        lab.setWrapText(true);
        if (resultat.isEmpty()){
            lab.setText("Résultat de la recherche : 0 document trouvé");
            zoneResultat.getChildren().add(lab);
        }
        else {
            lab.setText("Résultat de la recherche : "+resultat.size()+" document(s) trouvé(s)");
            zoneResultat.getChildren().add(lab);
            for (Document doc : resultat){
                Label label = new Label();
                label.setFont(new Font(15.0));
                label.setWrapText(true);
                String dispo = doc.isDisponible(1) ? "disponible "+doc.getQuantite()+" exemplaires" : "En rupture";
                label.setText("- "+doc.getTitre()+" ; "+doc.getAuteur()+" ; Edition "+doc.getEditeur()+" ; "+doc.getAnneeEdition()+" ; "+dispo);
                zoneResultat.getChildren().add(label);
            }
        }

    }

    private void effacerFormulaire() {
        titreDoc.setText("");
        auteurDoc.setText("");
        anneeDoc.setValue("--Selectionner--");
        typeDoc.setValue("--Selectionner--");
        rechercherBouton.setDisable(true);
        zoneResultat.getChildren().clear();
    }

    private void activerBoutonRechercher() {
        rechercherBouton.setDisable(
                titreDoc.getText().isEmpty() || auteurDoc.getText().isEmpty()
        );
    }


}
