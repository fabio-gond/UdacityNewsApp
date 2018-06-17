package com.example.fabio.udacity_newsapp;

public class Article {
    private String title;
    private String date;
    private String url;
    private String imgUrl;
    private String section;



    public String getImgUrl() {
        return imgUrl;
    }

    public Article(String title, String date, String url, String imgUrl, String section) {
        this.title = title;
        this.date = date;
        this.url = url;
        this.imgUrl = imgUrl;
        this.section = section;
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

    public String getSection() {
        return section;
    }
}
