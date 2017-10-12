package com.example.admin.contactlist.view.ContactView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.admin.contactlist.R;
import com.example.admin.contactlist.inject.ContactInject.DaggerContactComponent;
import com.example.admin.contactlist.model.Contact;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity implements ContactContract.ContactView {

    @Inject
    ContactPresenter presenter;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;

    ArrayList<Contact> c;
    ContactAdapter adapter;

    @BindView(R.id.contactList)
    RecyclerView contactList;

    View stuff_view, error_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        stuff_view = getLayoutInflater().inflate(R.layout.activity_contact, null);
        error_view = getLayoutInflater().inflate(R.layout.activity_contact_blank, null);
        setContentView(stuff_view);
        ButterKnife.bind(this);
        DaggerContactComponent.create().inject(this);

        c = new ArrayList<>();
        adapter = new ContactAdapter(c);
        initRecyclerView();
        presenter.attachView(this);
        presenter.LoadContactList();
    }

    private void initRecyclerView() {
        layoutManager = new LinearLayoutManager(getApplicationContext());
        itemAnimator = new DefaultItemAnimator();
        contactList.setLayoutManager(layoutManager);
        contactList.setItemAnimator(itemAnimator);
        contactList.setHasFixedSize(true);
        contactList.setItemViewCacheSize(20);
        contactList.setDrawingCacheEnabled(true);
        contactList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        contactList.setAdapter(adapter);

    }

    @Override
    public void showError(String error) {
        //TODO deal with raw error
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void UpdateView(ArrayList<Contact> c) {
        if(c == null){
            setContentView(error_view);
        } else {
            this.c.clear();
            this.c.addAll(c);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.removeView();
    }
}
