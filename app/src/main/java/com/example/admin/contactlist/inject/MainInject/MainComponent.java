package com.example.admin.contactlist.inject.MainInject;

import com.example.admin.contactlist.view.MainView.MainActivity;

import dagger.Component;


/**
 * Created by Admin on 10/11/2017.
 */

@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);
}
