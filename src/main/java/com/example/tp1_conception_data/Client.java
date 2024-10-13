package com.example.tp1_conception_data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Client {
    private  String reference;
    private String nom;
    private Date dateNaissance;
    private String profession;
    private String email;
    private String telephone;
    private Adresse adresse;
    private String username;
    private String password;
    //private static int nombreClient = 0;

    public Client(String reference, String nom, Date dateNaissance, String profession, String email, String telephone, Adresse adresse, String username, String password) {
        this.reference = reference;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.profession = profession;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.username = username;
        this.password = password;
        /*LocalDate localDate = dateNaissance.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int an = localDate.getYear();
        String mois = month < 10 ? "0"+month: String.valueOf(month);
        this.reference = nom.substring(0,2) + mois + an;*/
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "("+reference+") "+nom;
    }
}
