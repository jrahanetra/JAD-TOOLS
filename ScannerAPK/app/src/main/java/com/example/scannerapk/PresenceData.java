package com.example.scannerapk;

public class PresenceData {
    private int id_Presence;
    private String date;
    private String heure;
    private String matiere;
    private String situation;
    private String num_Matricule;

    public PresenceData(int id_Presence, String date, String heure, String matiere,String situation,String num_Matricule) {
        this.setId_Presence(id_Presence);
        this.setDate(date);
        this.setHeure(heure);
        this.setMatiere(matiere);
        this.setSituation(situation);
        this.setNum_Matricule(num_Matricule);

    }

    public int getId_Presence() {
        return id_Presence;
    }

    public void setId_Presence(int id_Presence) {
        this.id_Presence = id_Presence;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getSituation() { return situation; }

    public void setSituation(String situation) { this.situation = situation; }

    public String getNum_Matricule() {
        return num_Matricule;
    }

    public void setNum_Matricule(String num_Matricule) {
        this.num_Matricule = num_Matricule;
    }

    public String displayPresence()
    {
        return " |Date : "+date+"\n id_Presence : "+id_Presence+" |Matiere : "+matiere+" |Heure : "+heure;
    }
    public String displaySituation()
    {
        return " |Situation : "+situation;
    }

}
