package com.example.phonebook;

import java.util.ArrayList;

public class Contact {

    String tit;
    String ph;
    int image;
    int id;

    public Contact() {
        this.tit = tit;
        this.ph = ph;
        this.image = image;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
