package com.mnr.bankapp_v1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mnr.bankapp_v1.databinding.ActivityMapsBinding;

import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {
    Context context=this;
    private GoogleMap googleMap;


    private ActivityMapsBinding binding;

    //activity view
    String title,description,phoneNumber;

    //for popup
    private AlertDialog dialog;
   private AlertDialog.Builder builder;

    private static final int REQUEST_CALL=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageView ImgViewRetour = findViewById(R.id.imgRetour);
        ImgViewRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TransactionDetailsActivity.class);
                //Intent intent = new Intent(getApplicationContext(),AddTransactionActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        //googleMap = googleMap;
        // Add a marker in casablanca and move the camera
        LatLng MarketCasablanca = new LatLng(33.606875, -7.621369);
        googleMap.addMarker(new MarkerOptions().position(MarketCasablanca)
                .title("Market Casablanca")
                .snippet("Market Casablanca est un magasin qui vend de la marchandise en gros et en detail"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(MarketCasablanca));

        // Add a marker in tanger and move the camera
        LatLng MarketTanger = new LatLng(35.75697, -5.81773);
        googleMap.addMarker(new MarkerOptions().position(MarketTanger)
                .title("Market Tanger")
                .snippet("Market Tanger  est un magasin qui vend de la marchandise en gros et en detail"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(MarketTanger));

        // Add a marker in fes the camera
        LatLng MarketFes = new LatLng(34.03684, -5.00538);
        googleMap.addMarker(new MarkerOptions().position(MarketFes)
                .title("Market Fes")
                .snippet("Market Fes  est un magasin qui vend de la marchandise en gros et en detail"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(MarketFes));

        // Add a marker in rabat and move the camera
        LatLng MarketRabat = new LatLng(34.01489, -6.84977);
        googleMap.addMarker(new MarkerOptions().position(MarketRabat )
                .title("Market Rabat")
                .snippet("Market rabat est un magasin qui vend de la marchandise en gros et en detail"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(MarketRabat ));

        googleMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);



        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                LayoutInflater li= LayoutInflater.from(context);
                final View v= li.inflate(R.layout.addmarker,null);
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setView(v);

                builder.setCancelable(false);
                builder.setPositiveButton("create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText title=(EditText) v.findViewById(R.id.etTitle);
                        EditText snippet=(EditText) v.findViewById(R.id.etDescription);

                        if(googleMap != null) {
                            googleMap.addMarker(new MarkerOptions()
                                    .title(title.getText().toString())
                                    .snippet(snippet.getText().toString())
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                                    .position(latLng)

                            );
                        }
                    }
                });

                builder.setNegativeButton("cancel",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialoge,int which){
                        dialog.cancel();
                    }
                });
                AlertDialog alert=builder.create();
                alert.show();


            }
        });

    }

    @Override
    public boolean onMarkerClick(Marker marker){
        //don here what you need
        Toast.makeText(this,"my position"+marker.getPosition().latitude,Toast.LENGTH_LONG).show();


                //recuperer popup or template
                View vpop=getLayoutInflater().inflate(R.layout.my_popup_template,null);

                 ImageButton yesButton,closeButton;
                 TextView titleView,descriptionView,phoneNumberView;
                 String name= marker.getTitle();

//                title="mon titre";
//                description="ma description";
//                phoneNumber="00000000000";

                titleView=vpop.findViewById(R.id.txtTitle);
                descriptionView=vpop.findViewById(R.id.txtDescription);
                phoneNumberView=vpop.findViewById(R.id.txtPhone);
                yesButton=vpop.findViewById(R.id.btnYes);
                closeButton=vpop.findViewById(R.id.btnClose);

                titleView.setText(marker.getTitle().toUpperCase(Locale.ROOT));
        descriptionView.setText(marker.getSnippet());
                if (marker.getId().equals("m0"))
                {
                    phoneNumber="0522066024";
                    phoneNumberView.setText(phoneNumber);
//                    titleView.setText("Market Casablanca");
//                    descriptionView.setText("Market Casablanca est un magasin qui vend de la marchandise en gros et en detail");
                }
                else if (marker.getId().equals("m1"))
                {
                    phoneNumber="0538066025";
                    phoneNumberView.setText(phoneNumber);

                }
                else if (marker.getId().equals("m2"))
                {
                    phoneNumber="0523660252";
                    phoneNumberView.setText(phoneNumber);

                }
                else if (marker.getId().equals("m3"))
                {
                    phoneNumber="0537066023";
                    phoneNumberView.setText(phoneNumber);

                }
                else{
                    phoneNumberView.setText("0000000000");
                    Toast.makeText(MapsActivity.this, "pas de telephone a appeler", Toast.LENGTH_LONG).show();
                }



                //appeler Call phone
                yesButton.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MapsActivity.this, "appel en cours ....", Toast.LENGTH_LONG).show();
                        appeler(phoneNumber);
                    }
                });

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


               builder=new AlertDialog.Builder(MapsActivity.this);
               builder.setView(vpop);
                dialog=builder.create();
               dialog.show();


        //*********simple popup***********
        /*
        AlertDialog.Builder myPopup= new AlertDialog.Builder(this);
        myPopup.setIcon(R.drawable.ic_business);
        myPopup.setTitle(""+marker.getTitle());
        myPopup.setMessage("Bienvenue à "+marker.getTitle());
        myPopup.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppelerMarket();
            }


        });
        myPopup.setNegativeButton("NON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        myPopup.show();

        */

        return true;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void appeler(String telephone) {
        String s="tel:"+telephone;
        Intent intent= new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(s));

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        } else {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
    }

//on click sur info du marker
    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {

    }
}