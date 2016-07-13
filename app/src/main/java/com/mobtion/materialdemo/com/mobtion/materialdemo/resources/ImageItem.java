package com.mobtion.materialdemo.com.mobtion.materialdemo.resources;

import android.graphics.Bitmap;

/**
 * Created by agvenegas on 12/28/15.
 */
public class ImageItem {

    public ImageItem() {
        this.setImage(null);
        this.setTitle(null);
        this.setName(null);
    }

    public ImageItem (Bitmap image, String title, String name) {
        super();
        this.setImage(image);
        this.setTitle(title);
        this.setName(name);
    }

    private Bitmap image;
    public Bitmap getImage() { return image; }
    public void setImage(Bitmap value) { this.image = value; }

    private String title;
    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = (value != null) ? value : Constants.EMPTY_STRING; }

    private String name;
    public String getName() { return name; }
    public void setName(String value) { this.name = (value != null) ? value : Constants.EMPTY_STRING; }
}
