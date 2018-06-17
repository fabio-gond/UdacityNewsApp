package com.example.fabio.udacity_newsapp;

public class Article {
    private String title;
    private String date;
    private String url;
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public Article(String title, String date, String url, String imgUrl) {

        this.title = title;
        this.date = date;
        this.url = url;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
