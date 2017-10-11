package com.example.admin.contactlist.data;


import com.example.admin.contactlist.model.Contact;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Admin on 10/11/2017.
 */

public class DataHelper {

    DataCallback presenter;
    ContactAPIHelper service;
    public DataHelper(DataCallback presenter){
        this.presenter = presenter;
    };

    public void GetNetworkContact(){
        service.getContactApi()
                .getContactObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Contact>() {
                       @Override
                       public void onSubscribe(@NonNull Disposable d) {

                       }

                       @Override
                       public void onNext(@NonNull Contact contact) {
                           presenter.LoadContactNetwork(contact);
                       }

                       @Override
                       public void onError(@NonNull Throwable e) {
                           presenter.dataError(e.getMessage());
                       }

                       @Override
                       public void onComplete() {

                       }
                });

    }

    public void GetContactCache(){

    }

    public void SaveContact(){

    }

}
