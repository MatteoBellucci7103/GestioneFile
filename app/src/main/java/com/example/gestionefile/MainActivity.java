package com.example.gestionefile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    EditText txtNomeFile;
    Button btnLeggi;
    Button btnScrivi;

    Gestore gest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNomeFile =findViewById(R.id.txtNomeFIle);
        btnLeggi=findViewById(R.id.btnLeggi);
        btnScrivi=findViewById(R.id.btnScrivi);

        btnLeggi.setOnClickListener(new View.OnClickListener()
        {
            @Override
                    public void onClick(View view){
                Context c = getApplicationContext();
                String percorso = c.getPackageCodePath();
                String righeLette = gest.leggiFile("nomeFile", getApplicationContext());
                Toast.makeText(getApplicationContext(), "percorso", Toast.LENGTH_LONG).show();
            }
        });
        btnScrivi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                String esito = gest.scriviFile("prova.txt", getApplicationContext());
                Toast.makeText(getApplicationContext(), esito, Toast.LENGTH_LONG).show();
            }

        });


    }
}