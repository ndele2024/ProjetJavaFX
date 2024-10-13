package com.example.tp1_conception_data;

import java.util.Date;

public class Document {
    //private String isbn;
    private String cote;
    private String titre;
    private String auteur;
    private String editeur;
    private int anneeEdition;
    private String typeDocument;
    private int quantite;
    private Date datePret;
    private Boolean isRetourne;

    public Document(String cote, String titre, String auteur, String editeur, int anneeEdition, String typeDocument, int quantite) {
        //this.isbn = isbn;
        this.cote = cote;
        this.titre = titre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.anneeEdition = anneeEdition;
        this.typeDocument = typeDocument;
        this.quantite = quantite;
        datePret = null;
        isRetourne = true;
    }

    public void ajouteQuantite(int quantite) {
        this.quantite += quantite;
    }
    public void diminueQuantite(int quantite) {
        this.quantite -= quantite;
    }
    public boolean isDisponible(int val){return this.quantite > val;}


    public String getCote() {
        return cote;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }
    public String getEditeur() {
        return editeur;
    }

    public int getAnneeEdition() {
        return anneeEdition;
    }

    public Date getDatePret() {
        return datePret;
    }

    public void setDatePret(Date datePret) {
        this.datePret = datePret;
    }

    public void setRetourne(Boolean retourne) {
        isRetourne = retourne;
    }

    public Boolean getRetourne() {
        return isRetourne;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public int getQuantite() {
        return quantite;
    }
}
