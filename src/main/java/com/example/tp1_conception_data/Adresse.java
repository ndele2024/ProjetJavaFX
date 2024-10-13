package com.example.tp1_conception_data;

public class Adresse {
    private String numeroUnite;
    private String rue;
    private String codePostal;

    public Adresse(String numeroUnite, String rue, String codePostal) {
        this.numeroUnite = numeroUnite;
        this.rue = rue;
        this.codePostal = codePostal;
    }

    public String getNumeroUnite() {
        return numeroUnite;
    }

    public String getRue() {
        return rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setNumeroUnite(String numeroUnite) {
        this.numeroUnite = numeroUnite;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "numeroUnite='" + numeroUnite + '\'' +
                ", rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                '}';
    }
}
