package com.example.scannerapk;

public class Admin {
    public int id_Admin;
    public String username;
    public String password;
    public String num_Matricule;

    public Admin(int id_Admin, String username, String password, String num_Matricule) {
        this.setId_Admin(id_Admin);
        this.setUsername(username);
        this.setPassword(password);
        this.setNum_Matricule(num_Matricule);
    }

    public int getId_Admin() {
        return id_Admin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNum_Matricule() {
        return num_Matricule;
    }

    public void setId_Admin(int id_Admin) {
        this.id_Admin = id_Admin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNum_Matricule(String num_Matricule) {
        this.num_Matricule = num_Matricule;
    }
}
