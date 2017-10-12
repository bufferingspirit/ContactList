package com.example.admin.contactlist.inject.ContactInject;

import com.example.admin.contactlist.view.ContactView.ContactActivity;

import dagger.Component;

/**
 * Created by Admin on 10/11/2017.
 */

@Component(modules = ContactModule.class)
public interface ContactComponent {

    void inject(ContactActivity contactActivity);

}
