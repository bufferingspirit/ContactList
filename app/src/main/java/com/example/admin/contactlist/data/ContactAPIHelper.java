package com.example.admin.contactlist.data;

import com.example.admin.contactlist.model.Contact;
import com.example.admin.contactlist.util.CONSTANTS;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Admin on 10/11/2017.
 */

public class ContactAPIHelper {
    private ContactAPI myContactAPI;

    public ContactAPIHelper() {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(CONSTANTS.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        myContactAPI = retrofit.create(ContactAPI.class);
    }

    public ContactAPI getContactApi() {
        return myContactAPI;
    }


    public interface ContactAPI {

        @GET("api")
        Observable<Contact> getContactObservable();

    }

}
