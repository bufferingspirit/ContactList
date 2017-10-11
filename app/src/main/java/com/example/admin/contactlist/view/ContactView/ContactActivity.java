package com.example.admin.contactlist.view.ContactView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.contactlist.R;
import com.example.admin.contactlist.inject.ContactInject.DaggerContactComponent;

import javax.inject.Inject;

public class ContactActivity extends AppCompatActivity {


    //TODO add data inject
    @Inject
    ContactPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        DaggerContactComponent.create().inject(this);
    }
}
