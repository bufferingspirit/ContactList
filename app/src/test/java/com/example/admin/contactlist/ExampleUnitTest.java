package com.example.admin.contactlist;

import com.example.admin.contactlist.inject.MainInject.DaggerMainComponent;
import com.example.admin.contactlist.view.MainView.MainPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Inject
    MainPresenter presenter;


    @Before
    public void Setup(){
        //DaggerMainComponent.create().inject();
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}