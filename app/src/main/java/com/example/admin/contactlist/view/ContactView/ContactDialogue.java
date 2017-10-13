package com.example.admin.contactlist.view.ContactView;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.contactlist.R;
import com.example.admin.contactlist.model.StoredContact.StoredContact;
import com.example.admin.contactlist.util.BitmapUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 10/11/2017.
 */

public class ContactDialogue extends Dialog {

    Context context;
    StoredContact contact;

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
}
