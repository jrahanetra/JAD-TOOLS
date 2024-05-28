package com.example.scannerapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//activity
public class MainActivity4 extends AppCompatActivity {

    Button login_btn;
    EditText username;
    EditText password;

    TextView textView;
    private DataManager dataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        username = findViewById(R.id.username);
        password = findViewById(R.id.mdp_user);
        textView = findViewById(R.id.sendText);

        dataManager = DataManagerSingleton.getInstance(this);

        //Inserer les eleves
        dataManager.insertStudent("Jason" ,"L2","1");
        dataManager.insertStudent("Antsa" ,"L2","2");
        dataManager.insertStudent("Dihary" ,"L2","4");
        dataManager.insertStudent("LoÏc" ,"L2","3");

        //creer l'administrateur qui est un eleve meme
        dataManager.insertAdmin("AdminScan","admin2024","3");

        login_btn = findViewById(R.id.btn_login);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dataManager.isAdmin(username.getText().toString(),password.getText().toString())){
                    Intent startNewActivity = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(startNewActivity);
                }
                else
                    textView.setText("Erreur : Veuillez vérifier votre identité");

            }
        });


    }
}