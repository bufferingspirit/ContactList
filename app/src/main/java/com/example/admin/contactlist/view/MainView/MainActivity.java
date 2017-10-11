 package com.example.admin.contactlist.view.MainView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.contactlist.R;
import com.example.admin.contactlist.inject.MainInject.DaggerMainComponent;

import javax.inject.Inject;

 public class MainActivity extends AppCompatActivity {

     //TODO clean and minify

     @Inject
     MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainComponent.create().inject(this);
    }
}
