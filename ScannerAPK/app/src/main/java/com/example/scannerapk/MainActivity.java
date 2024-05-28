package com.example.scannerapk;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

//activity 2
public class MainActivity extends AppCompatActivity {

    Button scan_btn;

    TextView textView;

    private DataManager dataManager;

    private ImageButton sendSubject;
    private EditText matiere;

    public String subject;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataManager = DataManagerSingleton.getInstance( this );
        scan_btn = findViewById(R.id.scanner);
        sendSubject = findViewById(R.id.btnToSubject);

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator( MainActivity.this);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setPrompt("Scan a QR Code");
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.initiateScan();
            }
        });

        sendSubject.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                matiere = findViewById(R.id.matiere);
                subject = matiere.getText().toString();
                dataManager.insertPresence(subject,"1","Absent(e)");
                dataManager.insertPresence(subject,"2","Absent(e)");
                dataManager.insertPresence(subject,"3","Absent(e)");
                dataManager.insertPresence(subject,"4","Absent(e)");

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult != null)
        {
            String contents = intentResult.getContents();

            boolean truth = dataManager.isStudent(contents);
            if (truth == true)
            {
                dataManager.updatePresence(contents,subject);
                Intent startNewActivity = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(startNewActivity);
            }
            else
            {
                Intent startNewActivity1 = new Intent(getApplicationContext(),ErreurActivity.class);
                startActivity(startNewActivity1);
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}