package com.example.scannerapk;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;


import java.util.List;

//activity 3
public class MainActivity2 extends AppCompatActivity {

    private DataManager dataManager;

    private Button button;
    private ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button = findViewById(R.id.buttonToShow);
        btn = findViewById(R.id.btnFinish);

        dataManager = DataManagerSingleton.getInstance(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(getApplicationContext(),MainActivity3.class);
                startActivity(newActivity);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dataManager.close();
    }
}