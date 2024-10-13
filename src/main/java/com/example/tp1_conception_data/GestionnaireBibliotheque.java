package com.example.tp1_conception_data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class GestionnaireBibliotheque {
    private HashMap<String, Client> listeClient;
    private HashMap<Client, ArrayList<Document>> listePret;
    //gestionnaire de documents
    private HashMap<String, Document> listeDocument;


    public GestionnaireBibliotheque() throws ParseException {
        this.listeClient = new HashMap<>();
        this.listePret = new HashMap<>();
        this.listeDocument = new HashMap<>();
        donneeDeBaseClient();
        donneDeBaseDocument();
    }

    public HashMap<String, Client> getListeClient() {
        return listeClient;
    }

    public HashMap<String, Document> getListeDocument() {
        return listeDocument;
    }

    public HashMap<Client, ArrayList<Document>> getListePret() {
        return listePret;
    }

    public void ajouteClient(Client client) {
        listeClient.put(client.getReference(), client);
    }

    public void ajouterDocument(Document document) {listeDocument.put(document.getCote(), document);}

    public String modifierQuantite(String reference, int val, boolean mode) {
        String retour = "Enregistrement effectué";
        Document doc = listeDocument.get(reference);
        if(mode)
            doc.ajouteQuantite(val);
        else {
            if(doc.isDisponible(val))
                doc.diminueQuantite(val);
            else
                retour = "Ce Document n'est pas disponible";
        }
        return  retour;
    }

    public Document rechercherDocument(String data){
        Document retour = null;
        if(listeDocument.containsKey(data.toLowerCase())){
            retour = listeDocument.get(data);
        }
        else{
            for (Document doc : listeDocument.values()) {
                if (doc.getTitre().toLowerCase().contains(data.toLowerCase()) || doc.getAuteur().toLowerCase().contains(data.toLowerCase())) {
                    retour = doc;
                }
            }
        }
        return retour;
    }

    public ArrayList<Document> rechercherAllDocument(String data){
        ArrayList<Document> retour = new ArrayList<>();
        if(listeDocument.containsKey(data)){
            retour.add(listeDocument.get(data));
        }
        else{
            for (Document doc : listeDocument.values()) {
                if (doc.getTitre().toLowerCase().contains(data.toLowerCase()) || doc.getAuteur().toLowerCase().contains(data.toLowerCase())) {
                    retour.add(doc);
                }
            }
        }
        return retour;
    }

    public String rechercherClient(String val) {
        String retour = "";
        if(listeClient.containsKey(val)) {
            retour = val;
        }
        else {
            for (Client client : listeClient.values()) {
                if (Objects.equals(client.getNom().toLowerCase(), val.toLowerCase()) || String.valueOf(client.getTelephone()).equals(val)) {
                    retour = client.getReference();
                }
            }
        }
        return retour;
    }

    public void preterDocument(Client client, ArrayList<Document> listeDoc){
        if (listePret.containsKey(client)){
            ArrayList<Document> listeInitDoc = listePret.get(client);
            listeInitDoc.addAll(listeDoc);
              listePret.replace(client, listeInitDoc);
        }
        else
            listePret.put(client, listeDoc);
    }

    private void donneeDeBaseClient() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01");
        listeClient.put("client01", new Client("client01", "Martin Samy", date, "Etudiant", "abc@abc.com", "0123456789", new Adresse("1100", "rue qwerty", "A1A2B2"), "martins", "martins"));
        listeClient.put("client02", new Client("client02","Claire Jet", date, "Medecin", "abc@abc.com", "0123456789", new Adresse("1100", "rue qwerty", "A1A2B2"), "clairej", "clairej"));
        listeClient.put("client03", new Client("client03","Paul Hochon", date, "Medecin", "abc@abc.com", "0123456789", new Adresse("1100", "rue qwerty", "A1A2B2"), "paulh", "paulh"));
    }

    private void donneDeBaseDocument(){
        listeDocument.put("liv01", new Document("liv01", "Au delà de l'image", "Jean Ping", "Lacoste", 2005, "livre", 5));
        listeDocument.put("liv02", new Document("liv02", "Bienvenue chez toi", "Raymond Rael", "Chenelière", 2010, "livre", 10));
        listeDocument.put("mag01", new Document("mag01", "Started", "Antique Media", "Antique Media", 2024, "magazine", 20));
        listeDocument.put("mag02", new Document("mag02", "Science futuriste", "Laboratoire de science", "robot", 2024, "magazine", 8));
        listeDocument.put("rom01", new Document("rom01", "Danger dans la ville", "Leblanc Gobin", "Pens", 2010, "roman", 5));
        listeDocument.put("ouv01", new Document("ouv01", "Informatique niveau5", "ballack et Rowle", "chenelière", 2013, "manuel scolaire", 5));
    }

}
