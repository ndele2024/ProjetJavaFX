package com.example.tp1_conception_data;

public class Utilisateur {
    private String nom;
    private String fonction;
    private String user;
    private String password;

    public static Utilisateur defaultUser = new Utilisateur("ADMINISTRATEUR", "ADMIN", "admin", "admin");

    public Utilisateur(String nom, String fonction, String user, String password) {
        this.nom = nom;
        this.fonction = fonction;
        this.user = user;
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}


