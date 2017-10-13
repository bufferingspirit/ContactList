package com.example.admin.contactlist.view.ContactView;


import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.contactlist.R;
import com.example.admin.contactlist.model.StoredContact.StoredContact;
import com.example.admin.contactlist.util.BitmapUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.fragment;

/**
 * Created by Admin on 10/11/2017.
 */

public class ContactDialogue extends Dialog implements OnMapReadyCallback{

    Context context;
    StoredContact contact;

    MapView mapView;
    GoogleMap map;

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvStreetAddress)
    TextView tvStreetAddress;
    @BindView(R.id.tvCourseAddress)
    TextView tvCourseAddress;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.ivProfile)
    ImageView ivProfile;

    public ContactDialogue(@NonNull Context context, StoredContact contact) {
        super(context);
        this.context = context;
        this.contact = contact;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_dialog);
        ButterKnife.bind(this);

        // Gets the MapView from the XML layout and creates it
        mapView = this.findViewById(R.id.myMap);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        mapView.getMapAsync(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tvName.setText(contact.getFirstName() + " " + contact.getLastName());
        tvStreetAddress.setText(contact.getStreet());
        tvCourseAddress.setText(contact.getCity() + ", " + contact.getState() + ", " + contact.getPostcode());
        tvEmail.setText(contact.getEmail());
        ivProfile.setImageBitmap(BitmapUtil.getImage(contact.getLarge()));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
