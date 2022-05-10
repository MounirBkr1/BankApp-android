package com.mnr.bankapp_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ReclamationsActivity extends AppCompatActivity {
    EditText mEditTesxtTo;
    EditText mEditTeSubject;
    EditText mEditTeMessage;
    Button btnSend;
    ImageView buttonRetour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamations);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mEditTesxtTo=(EditText)findViewById(R.id.inputTo);
        mEditTeSubject=(EditText)findViewById(R.id.inputSubject);
        mEditTeMessage=(EditText)findViewById(R.id.inputMessage);
        btnSend=(Button) findViewById(R.id.btnSend);
        buttonRetour=(ImageView) findViewById(R.id.btnRetour);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
//                Intent emailIntent=new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("mailto:"+etTo.getText().toString()));
//                String[] recipients=new String[]{"miirbri1@gmail.com","",};
//                emailIntent.putExtra(Intent.EXTRA_EMAIL,recipients);
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT,etSubject.getText().toString());
//                emailIntent.putExtra(Intent.EXTRA_TEXT, etMessage.getText().toString());
//                startActivity(emailIntent);
            }
        });

        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TransactionActivity.class);

                startActivity(intent);
            }
        });


    }

    private void sendEmail() {
        String recipienList=mEditTesxtTo.getText().toString();
        String[] recipients=recipienList.split(",");

        String subject=mEditTeSubject.getText().toString();
        String message=mEditTeMessage.getText().toString();

        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);

        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent,"send email...."));




    }
}