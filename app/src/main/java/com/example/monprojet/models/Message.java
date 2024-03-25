package com.example.monprojet.models;


import java.util.Date;

public class Message {
    private String messageId, content;
    private String date_envoie;
    private String expediteur, destinataire;

    public  Message(){

    }

    public Message(String messageId, String content, String date_envoie, String expediteur, String destinataire) {
        this.messageId = messageId;
        this.content = content;
        this.date_envoie = date_envoie;
        this.expediteur = expediteur;
        this.destinataire = destinataire;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate_envoie() {
        return date_envoie;
    }

    public void setDate_envoie(String date_envoie) {
        this.date_envoie = date_envoie;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }


    public boolean isSentByCurrentUser(User currentUser) {
        return expediteur.equals(currentUser.getUserId());
    }
}
