package com.example.scannerapk;

public class Student {

    public int id_Student;
    public String name_Studend;
    public String level_Student;
    public String num_Matricule;

    public Student(int id_Student, String name_Studend, String level_Student, String num_Matricule) {
        this.setId_Student(id_Student);
        this.setName_Studend(name_Studend);
        this.setLevel_Student(level_Student);
        this.setNum_Matricule(num_Matricule);
    }

    public int getId_Student() {
        return id_Student;
    }

    public void setId_Student(int id_Student) {
        this.id_Student = id_Student;
    }

    public String getName_Studend() {
        return name_Studend;
    }

    public void setName_Studend(String name_Studends) {
        this.name_Studend = name_Studends;
    }

    public String getLevel_Student() {
        return level_Student;
    }

    public void setLevel_Student(String level_Student) {
        this.level_Student = level_Student;
    }

    public String getNum_Matricule() {
        return num_Matricule;
    }

    public void setNum_Matricule(String num_Matricule) {
        this.num_Matricule = num_Matricule;
    }


    @Override
    public String toString() {
        return " | " + name_Studend + "| Graduate : " + level_Student + "| Num_Matricule : "+num_Matricule;
    }
}
