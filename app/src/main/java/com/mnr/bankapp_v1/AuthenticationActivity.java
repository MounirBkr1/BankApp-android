package com.mnr.bankapp_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class AuthenticationActivity extends AppCompatActivity {

    Button btnLogin;
    EditText username;
    EditText password;
    TextView registerNow;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        //facebook login
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
         */

        setContentView(R.layout.activity_authentication);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnLogin = findViewById(R.id.btnLogin);
        username = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        registerNow = findViewById(R.id.txtRegisterNow);

        dbHelper=new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user=username.getText().toString();
                String pass = password.getText().toString();


                if(user.equals("") || pass.equals(""))
                    Toast.makeText(AuthenticationActivity.this, "Veilez rensigner tous les champs !!!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUserPass = dbHelper.checkUsernamePassword(user,pass);
                    if(checkUserPass == true){
                        Toast.makeText(AuthenticationActivity.this, "connexion réussie !!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),TransactionActivity.class);
                        //Intent intent = new Intent(getApplicationContext(),AddTransactionActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(AuthenticationActivity.this, "informations d'identification non valides !!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnregistrementActivity.class);

                startActivity(intent);
            }
        });
    }
}