package com.example.admin.contactlist.inject.MainInject;

import com.example.admin.contactlist.view.MainView.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Admin on 10/11/2017.
 */

@Module
public class MainModule {

    @Provides
    MainPresenter provideMainPresenter(){
        return new MainPresenter();
    }
}
