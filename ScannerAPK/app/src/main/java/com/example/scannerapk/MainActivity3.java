package com.example.scannerapk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
//activity4
public class MainActivity3 extends AppCompatActivity {
    private DataManager dataManager;
    TextView newTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        newTextView = findViewById(R.id.textView3);
        dataManager = DataManagerSingleton.getInstance(this);
        List<PresenceData> listPresence = dataManager.readPresence();
        Log.i("DEBUG",""+listPresence.size());

        for(PresenceData presence : listPresence)
        {
            Log.i("DEBUG",""+presence.getNum_Matricule());
            Student student = dataManager.readStudent(presence.getNum_Matricule());
            newTextView.append("\n\n"+presence.displayPresence()+""+student.toString()+""+presence.displaySituation());
        }
    }
}