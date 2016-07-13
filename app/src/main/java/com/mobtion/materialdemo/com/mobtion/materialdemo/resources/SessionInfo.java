package com.mobtion.materialdemo.com.mobtion.materialdemo.resources;

import java.util.ArrayList;

/**
 * Created by agvenegas on 12/30/15.
 */
public class SessionInfo {

    public int selected_photo = -1;

    private SessionInfo() {
        super();
        image_item = new ArrayList<ImageItem>();
        this.setImageItem(null);
    }

    public static SessionInfo instance = null;
    public static SessionInfo getInstance() {
        if (SessionInfo.instance == null) {
            SessionInfo.instance = new SessionInfo();
        }
        return SessionInfo.instance;
    }

    public static void endInstance() {
        SessionInfo.instance = null;
    }

    private ArrayList<ImageItem> image_item;
    public ArrayList<ImageItem> getImageItem() {
        return this.image_item;
    }
    public void setImageItem(ArrayList<ImageItem> list) {
        this.image_item = (list != null) ? list : new ArrayList<ImageItem>();
    }
}