package com.mobtion.materialdemo.com.mobtion.materialdemo.resources;

import android.graphics.Bitmap;

/**
 * Created by agvenegas on 12/28/15.
 */
public class ImageItem {

    public ImageItem() {
        this.setImage(null);
        this.setTitle(null);
    }

    public ImageItem (Bitmap image, String title) {
        super();
        this.setImage(image);
        this.setTitle(title);
    }

    private Bitmap image;
    public Bitmap getImage() { return image; }
    public void setImage(Bitmap value) { this.image = value; }

    private String title;
    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = (value != null) ? value : Constants.EMPTY_STRING; }
}
