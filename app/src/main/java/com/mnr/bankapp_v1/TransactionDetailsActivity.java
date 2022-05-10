package com.mnr.bankapp_v1;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;

public class TransactionDetailsActivity extends AppCompatActivity {

    TextView compte;
    TextView desc;
    TextView montant;
    TextView date;
    TextView solde;
    TextView ref;

    Button btnReclamation;
    Button btnLocalisation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);
    }

    @Override
    protected void onResume() {
        super.onResume();

        compte = findViewById(R.id.compte);
        desc = findViewById(R.id.description);
        montant = findViewById(R.id.montant);
        date = findViewById(R.id.date);
        solde = findViewById(R.id.solde);
        ref = findViewById(R.id.reference);

        btnReclamation=(Button) findViewById(R.id.btnReclamation);
        btnLocalisation=(Button) findViewById(R.id.btnLocalisation);


        Bundle b = getIntent().getExtras();
        Transaction tr = (Transaction) b.get("transactionObject");

        compte.setText(tr.getNumCompte());
        desc.setText(tr.getLabel());
        montant.setText(tr.getPrice());
        date.setText(tr.getDate());
        solde.setText(""+tr.getSolde());
        ref.setText(tr.getNumRef());



        //navigation
        btnReclamation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ReclamationsActivity.class);
                startActivity(intent);
            }
        });



        btnLocalisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });



    }
}