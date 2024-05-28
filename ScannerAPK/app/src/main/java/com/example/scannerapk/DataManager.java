package com.example.scannerapk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataManager extends SQLiteOpenHelper {

    public static final String DB_Name = "APKscan.db";
    public static final int DB_Version = 1;
    public DataManager(Context context)
    {
        super(context,DB_Name,null,DB_Version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "Create TABLE Student (" +
                        "id_Student integer primary key autoincrement, " +
                        "name_Student text not null, " +
                        "level_Student text not null, " +
                        "num_Matricule_Student text not null" +
                        ")";
        String newStrSql = "Create Table Presence  (" +
                           "id_Presence integer primary key autoincrement," +
                           "date text not null," +
                           "heure text not null," +
                           "matiere text not null," +
                           "situation text not null," +
                           "num_Matricule_Student text," +
                           "foreign key(num_Matricule_Student) references Student(num_Matricule_Student)" +
                           ")";
        String createAdmin = "Create Table Admin (" +
                             "id_Admin integer primary key autoincrement," +
                             "username text not null," +
                             "mot_de_passe text not null," +
                             "num_Matricule_Student text," +
                             "foreign key(num_Matricule_Student) references Student(num_Matricule_Student)" +
                             ")";
        db.execSQL(strSql);
        db.execSQL(newStrSql);
        db.execSQL(createAdmin);
        Log.i("DataBase", "onCreate invoked ");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertStudent(String name,String level,String num_Matricule)
    {
        String strSql = "Insert into Student (name_Student,level_Student,num_Matricule_Student) values" +
                        "('"+name+"','"+level+"','"+num_Matricule+"')";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(strSql);
        Log.i("DataBase", "insertStudent invoked ");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertPresence(String matiere, String num_Matricule,String situation)
    {
        // Obtention de la date et de l'heure actuelles
        LocalDateTime currentDateTime;
        currentDateTime = LocalDateTime.now();

        // Formattage de la date et de l'heure
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDateTime.format(formatter); // Formatage de la date

        formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentDateTime.format(formatter); // Formatage de l'heure

        String strSql = "Insert into Presence (date,heure,matiere,situation,num_Matricule_Student) values" +
                        "('"+formattedDate+"','"+formattedTime+"','"+matiere+"','"+situation+"','"+num_Matricule+"')";

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(strSql);

        Log.i("DataBase", "insertPresence invoked ");
    }

    public void insertAdmin(String username,String password, String num_Matricule)
    {
        String str = "Insert into Admin (username, mot_de_passe,num_Matricule_Student) values" +
                     "('"+username+"','"+password+"','"+num_Matricule+"')";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(str);
        Log.i("DataBase","Admin create");
    }

    // Méthode pour mettre à jour une colonne spécifique dans une table
    public void updatePresence(String matricule,String subject) {
        String strSql = "Update Presence set situation='Present(e)' where num_Matricule_Student='"+matricule+"' and matiere='"+subject+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(strSql);
        Log.i("DataBase", "updatePresence invoked ");
    }

    public boolean isAdmin(String username, String password)
    {
        List<Admin> listAdmin = new ArrayList<>();
        String strSql = "Select* from Admin";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while (! cursor.isAfterLast()){
            Admin admin = new Admin(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3));
            listAdmin.add(admin);
            cursor.moveToNext();
        }
        cursor.close();

        int check = 0;
        for (Admin admin : listAdmin){
            if(admin.username.equals(username) && admin.password.equals(password)){
                check ++;
            }
        }
        Log.i("DataBase", ""+check);
        if (check > 0)
            return true;
        else
            return false;
    }


    public boolean isStudent(String num_Matricule) {
        List<Student> listStudent = new ArrayList<>();
        String strSql = "select * from Student";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();

        while(! cursor.isAfterLast()) {
            Student student = new Student(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3));

            listStudent.add(student);
            cursor.moveToNext();
        }
        cursor.close();

        int check = 0;
        for (Student student : listStudent) {
            Log.i("Verification", "" + student.num_Matricule + "" + num_Matricule);
            if (student.num_Matricule != null && student.num_Matricule.equals(num_Matricule)) {
                check++;
            }
        }
        Log.i("Verification", "check :" + check);

        return check > 0;
    }

    public List<PresenceData> readPresence()
    {
        List<PresenceData> listPresence = new ArrayList<>();

        String strSql = "Select * from Presence";

        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while(! cursor.isAfterLast())
        {
            PresenceData presenceData = new PresenceData(cursor.getInt(0),cursor.getString(1),
                    cursor.getString(2),cursor.getString(3), cursor.getString(4), cursor.getString(5));

            listPresence.add(presenceData);
            cursor.moveToNext();
        }
        cursor.close();
        return listPresence;
    }
    public Student readStudent(String matricule) {
        String newStrSql = "Select * from Student where num_Matricule_Student = '"+matricule+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(newStrSql,null);
        Student student = null;

        if (cursor != null && cursor.moveToFirst()) {
            // Le curseur contient des données, tu peux les lire
            student = new Student(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
        else
        {
            Log.i("Database","le curseur ne contient pas de donnee");
        }

        // Assure-toi de fermer le curseur et la base de données
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return student;
    }
}
