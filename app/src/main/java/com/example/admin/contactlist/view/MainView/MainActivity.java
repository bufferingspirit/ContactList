package com.example.admin.contactlist.view.MainView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.contactlist.R;
import com.example.admin.contactlist.inject.MainInject.DaggerMainComponent;
import com.example.admin.contactlist.model.Contact;
import com.example.admin.contactlist.model.Result;
import com.example.admin.contactlist.view.ContactView.ContactActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    //TODO clean and minify

    @Inject
    MainPresenter presenter;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.ivPicture)
    ImageView ivPicture;
    @BindView(R.id.btnSaveContact)
    Button btnSaveContact;
    @BindView(R.id.btnLoadContact)
    Button btnLoadContact;
    @BindView(R.id.btnStartContactList)
    Button btnStartContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainComponent.create().inject(this);
        ButterKnife.bind(this);

        presenter.attachView(this);
        presenter.LoadContactView();
    }

    @OnClick({R.id.btnSaveContact, R.id.btnLoadContact, R.id.btnStartContactList})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSaveContact:
                //TODO deal with redundant bitmap load
                presenter.SaveContact();
                break;
            case R.id.btnLoadContact:
                presenter.LoadContactView();
                break;
            case R.id.btnStartContactList:
                Intent intent = new Intent(this, ContactActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void UpdateView(Contact contact){
        //TODO add model helper
        Result result = contact.getResults().get(0);
        String name = result.getName().getFirst() + " " + result.getName().getLast();
        System.out.println("API CALL: " + name);
        tvName.setText(name);
        String address = result.getLocation().getStreet()
                + " " + result.getLocation().getCity()
                + " " + result.getLocation().getState()
                + " " + result.getLocation().getPostcode();
        tvAddress.setText(address);
        tvEmail.setText(result.getEmail());
        //TODO fix this
        Picasso.with(this).load(result.getPicture().getThumbnail()).into(ivPicture);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //TODO need to make model parcelable
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //TODO need to make model parcelable
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.removeView();
    }
}
