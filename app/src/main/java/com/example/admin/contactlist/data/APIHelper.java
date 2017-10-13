package com.example.admin.contactlist.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.admin.contactlist.model.Contact.Contact;
import com.example.admin.contactlist.util.CONSTANTS;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Admin on 10/11/2017.
 */

public class APIHelper {
    private ContactAPI myContactAPI;

    public APIHelper() {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(CONSTANTS.USER_URL)
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

    public Observable<Bitmap> getImageObs(final String URL){
        return Observable.create(new ObservableOnSubscribe<Bitmap>() {

            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                Bitmap out = download_Image(URL);
                e.onNext(out);
                e.onComplete();
            }
        });
    }

    private Bitmap download_Image(String url) {

        Bitmap bmp =null;
        try{
            URL ulrn = new URL(url);
            HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
            InputStream is = con.getInputStream();
            bmp = BitmapFactory.decodeStream(is);
            if (null != bmp)
                return bmp;

        }catch(Exception e){}
        return bmp;
    }

}
