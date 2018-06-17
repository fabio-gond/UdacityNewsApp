package com.example.fabio.udacity_newsapp;

import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.URL;

public class Api {

    /**
     * Create a drawable from the url of the image
     */
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            return Drawable.createFromStream(is, "src name");
        } catch (Exception e) {
            return null;
        }
    }
}
