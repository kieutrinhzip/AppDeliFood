package edu.poly.delifood.model;

import java.io.Serializable;

public class NewProduct implements Serializable {
    int id;
    String newName;
    String newImage;
    String newPrice;
    String newDes;
    int newType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewImage() {
        return newImage;
    }

    public void setNewImage(String newImage) {
        this.newImage = newImage;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getNewDes() {
        return newDes;
    }

    public void setNewDes(String newDes) {
        this.newDes = newDes;
    }

    public int getNewType() {
        return newType;
    }

    public void setNewType(int newType) {
        this.newType = newType;
    }
}
