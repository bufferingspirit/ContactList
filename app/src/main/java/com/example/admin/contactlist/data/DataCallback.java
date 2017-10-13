package com.example.admin.contactlist.data;

import com.example.admin.contactlist.model.Contact.Contact;
import com.example.admin.contactlist.model.StoredContact.StoredContact;

import java.util.ArrayList;

/**
 * Created by Admin on 10/11/2017.
 */

public interface DataCallback {
    void ParseContactNetwork(Contact contact);
    void ParseContactCache(ArrayList<StoredContact> contact);
    void dataError(String s);
}
