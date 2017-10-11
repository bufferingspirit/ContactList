package com.example.admin.contactlist.data;

import com.example.admin.contactlist.model.Contact;

/**
 * Created by Admin on 10/11/2017.
 */

public interface DataCallback {
    void LoadContactNetwork(Contact contact);
    void dataError(String s);
}
