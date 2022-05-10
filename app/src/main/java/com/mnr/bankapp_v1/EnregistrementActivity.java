package com.mnr.bankapp_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnregistrementActivity extends AppCompatActivity {

    EditText username,password,retapperPassword,email;
    Button btnRegister;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement);
    }

    @Override
    protected void onResume() {
        super.onResume();


        username=(EditText) findViewById(R.id.editTextTextUsername);
        password=(EditText) findViewById(R.id.editTextTextPassword);
        retapperPassword=(EditText) findViewById(R.id.editTextTextRetaperPassword);
        email=(EditText) findViewById(R.id.editTextTextEmail);
        btnRegister= (Button) findViewById(R.id.buttonRegister);

        dbHelper=new DBHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= username.getText().toString();
                String pass= password.getText().toString();
                String rePass= retapperPassword.getText().toString();
                String eml= email.getText().toString();

                if(user.equals("") || pass.equals("") || rePass.equals("") || eml.equals(""))
                    Toast.makeText(EnregistrementActivity.this, "veillez renseigner tous les chanps vides", Toast.LENGTH_LONG).show();
                else{
                    if(pass.equals(rePass)){
                        Boolean checkUser= dbHelper.checkUsername(user);
                        if(checkUser == false){
                            Boolean insert= dbHelper.insert(user,pass,eml);
                            if(insert == true){
                                Toast.makeText(EnregistrementActivity.this, "Enregistré avec succès !!", Toast.LENGTH_LONG).show();

                                //rediriger sur page d'authentification
                                Intent intent = new Intent(getApplicationContext(),AuthenticationActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(EnregistrementActivity.this, "échec de l'enregistrement !!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(EnregistrementActivity.this, "Username exist deja !!, veillez sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(EnregistrementActivity.this, "mot de passe ne correspondant pas", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}