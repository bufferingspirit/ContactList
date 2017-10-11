package com.example.admin.contactlist.inject.ContactInject;

/**
 * Created by Admin on 10/11/2017.
 */

import com.example.admin.contactlist.view.ContactView.ContactPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactModule {

    @Provides
    ContactPresenter provideContactPresenter(){
        return new ContactPresenter();
    }
}
