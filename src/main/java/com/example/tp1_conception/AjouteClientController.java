package com.example.tp1_conception;

import com.example.tp1_conception_data.Adresse;
import com.example.tp1_conception_data.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AjouteClientController {

    @FXML private TextField referenceClient;
    @FXML private TextField nomClient;
    @FXML private DatePicker dateNaissance;
    @FXML private ChoiceBox<String> profession;
    @FXML private Button annulerButton1;
    @FXML private Button suivantButton1;
    @FXML private Label errorMesageIdentification;
    @FXML private AnchorPane anchrelabelPartie1;
    @FXML private AnchorPane anchredataPartie1;
    @FXML private AnchorPane anchredataPartie2;
    @FXML private AnchorPane anchrelabelPartie2;
    @FXML private AnchorPane anchredataPartie3;
    @FXML private AnchorPane anchrelabelPartie3;

    @FXML private TextField email;
    @FXML private TextField tel1;
    @FXML private TextField tel2;
    @FXML private TextField tel3;
    @FXML private TextField adresse1;
    @FXML private TextField adresse2;
    @FXML private TextField adresse3;
    @FXML private Button precedentButon2;
    @FXML private Button annulerButton2;
    @FXML private Button suivantButton2;
    @FXML private  Label errorMessageContact;

    @FXML private Button precedentButton3;
    @FXML private Button annulerButton3;
    @FXML private Button suivantButton3;
    @FXML private TextField userName;
    @FXML private PasswordField passwordHide1;
    @FXML private TextField passwordShow1;
    @FXML private ImageView showPasswordIcon1;
    @FXML private ImageView hidePasswordIcon1;
    @FXML private PasswordField passwordHide2;
    @FXML private TextField passwordShow2;
    @FXML private ImageView showPasswordIcon2;
    @FXML private ImageView hidePasswordIcon2;
    @FXML private Label passwordConfirmLabel;

    @FXML private AnchorPane anchredataPartie4;
    @FXML private AnchorPane anchrelabelPartie4;
    @FXML private  Label labelReference;
    @FXML private  Label labelNom;
    @FXML private  Label labelDate;
    @FXML private  Label labelProfession;
    @FXML private  Label labelEmail;
    @FXML private  Label labelTel;
    @FXML private  Label labelAdresse;
    @FXML private  Label labelUser;
    @FXML private Button precedentButton4;
    @FXML private Button suivantButton4;

    private ObservableList listeProfession;
    private boolean isdatenaissValide = false;

    public AjouteClientController() {
        listeProfession = FXCollections.observableArrayList("Ecrivain", "Etudiant", "Enseignant", "Medecin", "Ingénieur", "Infirmier(e)", "Autre");

    }

    @FXML public void initialize(){
        //remplissage de la choicebox profession
        profession.setItems(listeProfession);

        //evenements sur les boutons suivant et annuler
        annulerButton1.setOnAction(actionEvent -> {
            annulerFormulairepartie1();
        });
        suivantButton1.setOnAction(actionEvent -> {
            affichePartie2();
        });
        referenceClient.setOnKeyReleased(keyEvent -> {
            activeDesactiveBoutonSuivant1();
            //System.out.println(keyEvent.getText());
        });
        nomClient.setOnKeyReleased(keyEvent -> {
            activeDesactiveBoutonSuivant1();
            //System.out.println(keyEvent.getText());
        });
        dateNaissance.setOnAction(actionEvent -> {
            try {
                verifieDateNaissance();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            activeDesactiveBoutonSuivant1();
        });
        profession.setOnAction(actionEvent -> {
            activeDesactiveBoutonSuivant1();
        });

        precedentButon2.setOnAction(actionEvent -> {
            affichePartie1();
        });
        suivantButton2.setOnAction(actionEvent -> {
            affichePartie3();
        });

        annulerButton2.setOnAction(actionEvent -> {
            annulerFormulairepartie2();
        });

        email.setOnKeyReleased(keyEvent -> {
            verifieEmail();
            activeDesactiveBoutonSuivant2();
        });

        tel1.setOnKeyReleased(keyEvent -> {
            verifieTelephone1(keyEvent.getText(), tel1, tel2, 3, true);
            activeDesactiveBoutonSuivant2();
        });
        tel2.setOnKeyReleased(keyEvent -> {
            verifieTelephone1(keyEvent.getText(), tel2, tel3, 3, true);
            activeDesactiveBoutonSuivant2();
        });
        tel3.setOnKeyReleased(keyEvent -> {
            verifieTelephone1(keyEvent.getText(), tel3, tel3, 4, false);
            activeDesactiveBoutonSuivant2();
        });
        adresse1.setOnKeyReleased(keyEvent -> {
            verifieTelephone1(keyEvent.getText(), adresse1, adresse2, 9, false);
            activeDesactiveBoutonSuivant2();
        });
        adresse2.setOnKeyReleased(keyEvent -> {
            activeDesactiveBoutonSuivant2();
        });
        adresse3.setOnKeyReleased(keyEvent -> {
            verifieCodePostal();
            activeDesactiveBoutonSuivant2();
        });

        precedentButton3.setOnAction(actionEvent -> {
            affichePartie2();
        });

        annulerButton3.setOnAction(actionEvent -> {
            annulerFormulairepartie3();
        });
        hidePasswordIcon1.setOnMouseClicked(mouseEvent -> {
            hidePassword(passwordShow1, passwordHide1, showPasswordIcon1, hidePasswordIcon1);
        });
        showPasswordIcon1.setOnMouseClicked(mouseEvent -> {
            showPassword(passwordShow1, passwordHide1, showPasswordIcon1, hidePasswordIcon1);
        });
        passwordHide1.setOnKeyReleased(keyEvent -> {
            verifiePassword(passwordHide1, passwordShow1, validerPassword1(passwordHide1.getText()));
            reproduitPassword(passwordHide1, passwordShow1);
            activeDesactiveBoutonSuivant3();
        });
        passwordShow1.setOnKeyReleased(keyEvent -> {
            verifiePassword(passwordHide1, passwordShow1, validerPassword1(passwordShow1.getText()));
            passwordHide1.setText(passwordShow1.getText());
            activeDesactiveBoutonSuivant3();
        });

        hidePasswordIcon2.setOnMouseClicked(mouseEvent -> {
            hidePassword(passwordShow2, passwordHide2, showPasswordIcon2, hidePasswordIcon2);
        });
        showPasswordIcon2.setOnMouseClicked(mouseEvent -> {
            showPassword(passwordShow2, passwordHide2, showPasswordIcon2, hidePasswordIcon2);
        });

        passwordHide2.setOnKeyReleased(keyEvent -> {
            verifiePassword(passwordHide2, passwordShow2, validerPassword2(passwordHide2.getText(), passwordHide1.getText()));
            reproduitPassword(passwordHide2, passwordShow2);
            activeDesactiveBoutonSuivant3();
        });
        passwordShow2.setOnKeyReleased(keyEvent -> {
            verifiePassword(passwordHide2, passwordShow2, validerPassword2(passwordShow2.getText(), passwordShow1.getText()));
            passwordHide2.setText(passwordShow2.getText());
            activeDesactiveBoutonSuivant3();
        });

        suivantButton3.setOnAction(actionEvent -> {
            affichePartie4();
        });

        precedentButton4.setOnAction(actionEvent -> {
            affichePartie3();
        });

        suivantButton4.setOnAction(actionEvent -> {
            try {
                ajouterClient();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void affichePartie4() {
        LocalDate datenaiss = dateNaissance.getValue();
        // Create a DateTimeFormatter object to format LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = datenaiss.format(formatter);
        //Instant instant = Instant.from(datenaiss.atStartOfDay(ZoneId.systemDefault()));
        // date = Date.from(instant);
        String tel = tel1.getText() + tel2.getText() + tel3.getText();
        String adresse = adresse1.getText() + " " + adresse2.getText() + " " + adresse3.getText();
        labelReference.setText(referenceClient.getText());
        labelNom.setText(nomClient.getText());
        labelDate.setText(dateString);
        labelProfession.setText(profession.getValue());
        labelEmail.setText(email.getText());
        labelAdresse.setText(adresse);
        labelTel.setText(tel);
        labelUser.setText(userName.getText());

        anchredataPartie3.setVisible(false);
        anchrelabelPartie3.setVisible(false);
        anchredataPartie4.setVisible(true);
        anchrelabelPartie4.setVisible(true);
    }

    private void ajouterClient() throws IOException {
        LocalDate datenaiss = dateNaissance.getValue();
        Instant instant = Instant.from(datenaiss.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        String tel = tel1.getText() + tel2.getText() + tel3.getText();
        Adresse adresse = new Adresse(adresse1.getText(), adresse2.getText(), adresse3.getText());
        Client client = new Client(
            referenceClient.getText(),
            nomClient.getText(),
            date,
            profession.getValue(),
            email.getText(),
            tel,
            adresse,
            userName.getText(),
            passwordHide1.getText()
        );
        HelloController.gestionnaireBibliotheque.ajouteClient(client);
        //afficher une boite de dialogue
        HelloController.afficheBoite("Enregistrement Effectué avec succès", "inform");
        //efface tous les formulaires
        annulerFormulairepartie1();
        annulerFormulairepartie2();
        annulerFormulairepartie3();
        suivantButton1.setDisable(true);
        //retourner à la page initiale
        affichePartie1();
        //AnchorPane  anchor = FXMLLoader.load(Objects.requireNonNull(HelloController.class.getResource("accueil.fxml")));

        System.out.println(HelloController.gestionnaireBibliotheque.getListeClient().values().stream().toList());
    }

    private void activeDesactiveBoutonSuivant3() {
        suivantButton3.setDisable(
                userName.getText().isEmpty() || passwordHide1.getText().isEmpty() || passwordHide2.getText().isEmpty()
                || !validerPassword1(passwordHide1.getText()) || !validerPassword2(passwordHide1.getText(), passwordHide2.getText())
        );
    }

    private void verifiePassword(PasswordField passwordField, TextField textField, boolean valider) {
        if (valider){
            passwordField.setStyle("-fx-text-fill: black;");
            textField.setStyle("-fx-text-fill: black;");
        }
        else {
            passwordField.setStyle("-fx-text-fill: red; -fx-background-color: #e5d4d4; -fx-border-color: red");
            textField.setStyle("-fx-text-fill: red; -fx-background-color: #e5d4d4; -fx-border-color: red");
        }
    }

    private boolean validerPassword1(String password){
        boolean maj=false, chiffre=false, retour = true;
        if (password.length() < 8) retour = false;
        for (char c : password.toCharArray()){
            if (Character.isDigit(c)) {
                chiffre = true;
            }
            if (Character.isUpperCase(c)) {
                maj = true;
            }
        }
        if (!maj || !chiffre){
            retour = false;
        }
        if (password.equals(userName.getText())) retour = false;

        return retour;
    }

    private boolean validerPassword2(String password1, String password2){
        return Objects.equals(password1, password2);
    }

    private void hidePassword(TextField passwordShow, PasswordField passwordHide, ImageView showPasswordIcon, ImageView hidePasswordIcon) {
        passwordHide.setVisible(true);
        passwordShow.setVisible(false);
        showPasswordIcon.setVisible(true);
        hidePasswordIcon.setVisible(false);
    }

    private void showPassword(TextField passwordShow, PasswordField passwordHide, ImageView showPasswordIcon, ImageView hidePasswordIcon) {
        passwordHide.setVisible(false);
        passwordShow.setVisible(true);
        showPasswordIcon.setVisible(false);
        hidePasswordIcon.setVisible(true);
    }

    private void reproduitPassword(PasswordField passwordHide, TextField passwordShow) {
        passwordShow.setText(passwordHide.getText());
    }

    private void annulerFormulairepartie3() {
        userName.setText("");
        passwordHide1.setText(""); passwordShow1.setText("");
        passwordHide2.setText(""); passwordShow2.setText("");
        suivantButton3.setDisable(true);
    }

    private void verifieCodePostal() {
        if(validerCodepostal(adresse3.getText())){
            adresse3.setStyle("-fx-text-fill: black");
            errorMessageContact.setText("");
        }
        else {
            adresse3.setStyle("-fx-text-fill: red; -fx-background-color: #e5d4d4; -fx-border-color: red");
            errorMessageContact.setText("Code postal invalide");
        }
    }

    private boolean validerCodepostal(String codepostal) {
            String regex = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(codepostal);
            return matcher.matches();
    }

    private void activeDesactiveBoutonSuivant2() {
        suivantButton2.setDisable(
                email.getText().isEmpty() || tel1.getText().isEmpty() || tel2.getText().isEmpty() || tel3.getText().isEmpty()
                || adresse1.getText().isEmpty() || adresse2.getText().isEmpty() || adresse3.getText().isEmpty() ||
                        !validerEmail(email.getText()) || !validerCodepostal(adresse3.getText()) ||
                        !verifieTelephone1(tel3.getText(), tel3, tel3, 4, false)
        );
    }

    private boolean verifieTelephone1(String text, TextField source, TextField destinationFocus, int nb, boolean isFocussed) {
        boolean ret = false;
        if (text.toCharArray().length > 0) {
            char car = text.toCharArray()[0];
            if (car < 48 || car > 57 || source.getText().length() > nb) {
                String t = source.getText();
                //t=t.replace(car, '');
                if (t.isEmpty()) t = "";
                else t = replaceChar(t, car);
                source.setText(t);
                source.requestFocus();
            }
            if (source.getText().length() == nb){
                ret = true;
                if (isFocussed) {
                    destinationFocus.setDisable(false);
                    destinationFocus.requestFocus();
                }
            }
        }
        return ret;
    }

    private String replaceChar(String t, char car) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i)==car) continue;
            else ret.append(t.charAt(i));
        }
        return ret.toString();
    }

    private void verifieEmail() {
        if(!validerEmail(email.getText())) {
            email.setStyle("-fx-text-fill: red; -fx-background-color: #e5d4d4; -fx-border-color: red");
            errorMessageContact.setText("Adresse email invalide");
        }
        else {
            email.setStyle("-fx-text-fill: black");
            errorMessageContact.setText("");
        }
    }

    private boolean validerEmail(String email){
        String regex = "^[\\p{L}0-9!#$%&'*+\\/=?^_`{|}~-][\\p{L}0-9.!#$%&'*+\\/=?^_`{|}~-]{0,63}@[\\p{L}0-9-]+(?:\\.[\\p{L}0-9-]{2,7})*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private void annulerFormulairepartie2() {
        email.setText("");
        tel1.setText(""); tel2.setText(""); tel3.setText("");
        adresse1.setText(""); adresse2.setText(""); adresse3.setText("");
        errorMessageContact.setText("");
        suivantButton2.setDisable(true);
    }

    private void affichePartie3() {
        anchredataPartie2.setVisible(false);
        anchrelabelPartie2.setVisible(false);
        anchredataPartie4.setVisible(false);
        anchrelabelPartie4.setVisible(false);
        anchredataPartie3.setVisible(true);
        anchrelabelPartie3.setVisible(true);
    }

    private void affichePartie1() {
        anchredataPartie1.setVisible(true);
        anchrelabelPartie1.setVisible(true);
        anchredataPartie2.setVisible(false);
        anchrelabelPartie2.setVisible(false);
        anchredataPartie4.setVisible(false);
        anchrelabelPartie4.setVisible(false);
    }

    private void verifieDateNaissance() throws ParseException {

        LocalDate datenaiss = dateNaissance.getValue();
        Instant instant = Instant.from(datenaiss.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Date datemin = new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-01");
        if(date.after(datemin)) {
            isdatenaissValide = false;
            errorMesageIdentification.setText("La date de naissance N'est pas valide! l'utilisateur doit avoir plus de 6 ans");
            dateNaissance.setStyle("-fx-text-fill: red; -fx-background-color: #e5d4d4; -fx-border-color: red");
        }
        else {
            errorMesageIdentification.setText("");
            dateNaissance.setStyle("-fx-text-fill: black; -fx-background-color: white; -fx-border-color: #54b8d9");
            isdatenaissValide = true;
            //System.out.println("bonne date " + date);
        }

    }

    //activer ou desactiver le bouton suivant
    private void activeDesactiveBoutonSuivant1() {
        String ref = referenceClient.getText();
        String nom = nomClient.getText();
        LocalDate dateNaiss = dateNaissance.getValue();
        String prof = profession.getValue();
        suivantButton1.setDisable(ref.isEmpty() || nom.isEmpty() || dateNaiss == null || Objects.equals(prof, "--Selectionner--") || !isdatenaissValide);

    }

    private void affichePartie2() {
        anchredataPartie1.setVisible(false);
        anchrelabelPartie1.setVisible(false);
        anchredataPartie3.setVisible(false);
        anchrelabelPartie3.setVisible(false);
        anchredataPartie2.setVisible(true);
        anchrelabelPartie2.setVisible(true);
    }

    private void annulerFormulairepartie1() {
        referenceClient.setText("");
        nomClient.setText("");
        dateNaissance.setValue(null);
        profession.setValue("--Selectionner--");
        errorMesageIdentification.setText("");
        suivantButton1.setDisable(false);
    }

}
