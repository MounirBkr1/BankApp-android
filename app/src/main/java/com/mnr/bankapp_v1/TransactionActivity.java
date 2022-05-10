package com.mnr.bankapp_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {

    ListView trans;
    Button btnAdd;
    ImageButton btnDelete;
    ArrayList<Transaction> listTrans;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
    }


    @Override
    protected void onResume() {
        super.onResume();
        trans = findViewById(R.id.transactions);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);


        DBHelper dbHelper = new DBHelper(this);


//*********1st methode********
        //Transaction tr1 = new Transaction(R.drawable.icontrans,"transaction1","295","12/12/21","97876534","R1234567",10000);
//        Transaction tr2 = new Transaction(R.drawable.icontrans,"transaction2","455","13/12/21","97876534","R1234568",14000);
//        Transaction tr3 = new Transaction(R.drawable.icontrans,"transaction3","2450","22/12/21","97876534","R1234569",20000);
//        Transaction tr4 = new Transaction(R.drawable.icontrans,"transaction4","2150","29/12/21","97876534","R1234560",17000);
//        Transaction tr5 = new Transaction(R.drawable.icontrans,"transaction5","695","01/12/21","97876534","R1234564",10900);

        //dbManager.addTransaction(tr1);
//        dbManager.addTransaction(tr2);
//        dbManager.addTransaction(tr3);
//        dbManager.addTransaction(tr4);
//        dbManager.addTransaction(tr5);

        //*********2nd methode(attention a nullPointerException a la 1ere ouverture de TtransactionActivity)********
//        Bundle extras = getIntent().getExtras();
//        String label= new String(extras.getString("label"));
//        String price = new String(extras.getString("price"));
//        String date = new String(extras.getString("date"));
//        String compte = new String(extras.getString("compte"));
//        String reference = new String(extras.getString("reference"));
//        String solde = new String(extras.getString("solde"));

        //Transaction tr = new Transaction(R.drawable.icontrans,label,price,date,compte,reference,Double.parseDouble(solde));

        // dbManager.addTransaction(tr);

        //**************** 3rd methode=ajouter directement a DB aupres d'activité AddTransaction**************

      listTrans = (ArrayList<Transaction>) dbHelper.getAllTransactions();


        TransactionAdapter adapter = new TransactionAdapter(this, R.layout.cellule, listTrans);
        trans.setFocusable(true);
        trans.setAdapter(adapter);
        trans.setEnabled(true);
        trans.setItemsCanFocus(false);

        trans.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), TransactionDetailsActivity.class);
                intent.putExtra("transactionObject", listTrans.get(position));
                startActivity(intent);

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddTransactionActivity.class);

                startActivity(intent);
            }
        });



    }
}