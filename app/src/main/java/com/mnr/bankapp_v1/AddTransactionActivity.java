package com.mnr.bankapp_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddTransactionActivity extends AppCompatActivity {

    //ImageView icon;
    EditText  editLabel,
    editPrice,
    editDate,
    editCompte,
    editReference,
    editSolde;
    Button btnAjouter,btnAnnuler;

    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
    }

    @Override
    protected void onResume() {
        super.onResume();



        //icon = findViewById(R.id.imageViewIcon);
        editLabel=(EditText) findViewById(R.id.editTextLabel);
        editPrice=(EditText) findViewById(R.id.editTextPrice);
        editDate=(EditText)findViewById(R.id.editTextDate);
        editCompte=(EditText)findViewById(R.id.editTextCompte);
        editReference=(EditText)findViewById(R.id.editTextReference);
        editSolde=(EditText)findViewById(R.id.editTextSolde);
        btnAjouter=(Button) findViewById(R.id.buttonAjouter);
        btnAnnuler=(Button) findViewById(R.id.btnAnnuler);





        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //dbManager.addTransaction(tr);


                Intent intent = new Intent(getApplicationContext(),TransactionActivity.class);
                ajouterEnregistrement();
//                intent.putExtra("label",label.getText().toString());
//                intent.putExtra("price",price.getText().toString());
//                intent.putExtra("date",date.getText().toString());
//                intent.putExtra("compte",compte.getText().toString());
//                intent.putExtra("reference",reference.getText().toString());
//                intent.putExtra("solde",solde.getText().toString());

                startActivity(intent);
            }



            private void ajouterEnregistrement() {

                    String label = editLabel.getText().toString();
                    String price = editPrice.getText().toString();
                    String date = editDate.getText().toString();
                    String compte = editCompte.getText().toString();
                    String reference = editReference.getText().toString();
                    String solde = editSolde.getText().toString();

                    Transaction tr = new Transaction(R.drawable.icontrans, label, price, date, compte, reference, Double.parseDouble(solde));
                    dbHelper.addTransaction(tr);

            }
        });


        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TransactionActivity.class);

                startActivity(intent);
            }
        });
    }
}