package com.example.admin.contactlist.model;

/**
 * Created by Admin on 10/11/2017.
 */

public class Images {

    public String user_id;
    public byte[] thumbnail;
    public byte[] large;

    public Images(String user_id, byte[] thumbnail, byte[] large) {
        this.user_id = user_id;
        this.thumbnail = thumbnail;
        this.large = large;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public byte[] getLarge() {
        return large;
    }

    public void setLarge(byte[] large) {
        this.large = large;
    }
}
