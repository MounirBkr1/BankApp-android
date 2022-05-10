package com.mnr.bankapp_v1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class CustomPopup extends Dialog {
    private String title,description,phoneNumber;

    private ImageButton yesButton;
    private TextView titleView,descriptionView;

    private static final int REQUEST_CALL=1;
    private TextView PhoneNumberView;



    public CustomPopup(Activity activity) {
        super(activity, androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dark);

//        //integrer layout my_popup_template)
//        setContentView(R.layout.my_popup_template);
//
//        this.title="mon titre";
//        this.description="ma description";
//        this.yesButton= findViewById(R.id.btnYes);
//        this.noButton=findViewById(R.id.btnNo);
//        this.titleView=findViewById(R.id.txtTitle);
//        this.descriptionView=findViewById(R.id.txtDescription);
//        this.PhoneNumberView=findViewById(R.id.txtPhone);
//
//        yesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Appeler();
//            }
//        });

    }

    private void Appeler() {
        //String number= PhoneNumberView.getText().toString();


    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageButton getYesButton() {
        return yesButton;
    }



    public void build(){
//        show();
//
//        //change value of title
//        titleView.setText(title);
//        descriptionView.setText(description);


    }


}
