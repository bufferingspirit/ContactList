package com.example.admin.contactlist;

import com.example.admin.contactlist.data.DataCallback;
import com.example.admin.contactlist.data.DataHelper;
import com.example.admin.contactlist.model.Contact.Contact;
import com.example.admin.contactlist.view.MainView.MainContract;
import com.example.admin.contactlist.view.MainView.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by Admin on 10/13/2017.
 */

public class MainPresenterTest {

    @Mock
    MainContract.MainView view;
    @Mock
    DataCallback callback;
    @Mock
    MainPresenter presenter;
    @Mock
    Contact contact;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter();
        presenter.attachView(view);
        contact = new Contact();
    }

}
