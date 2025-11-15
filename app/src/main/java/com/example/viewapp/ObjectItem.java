package com.example.viewapp;

public class ObjectItem {
    public int imageResId;
    public String name;
    public String category;
    public String extendedText;
    public String imageResName;

    public ObjectItem(int imageResId, String imageResName, String name, String category, String extendedText) {
        this.imageResId = imageResId;
        this.imageResName = imageResName;
        this.name = name;
        this.category = category;
        this.extendedText = extendedText;
    }

    public ObjectItem(int imageResId, String name, String extendedText) {
        this(imageResId, "", name, "", extendedText);
    }
}
