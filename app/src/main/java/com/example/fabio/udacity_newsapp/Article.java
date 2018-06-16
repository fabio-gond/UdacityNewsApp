package com.example.fabio.udacity_newsapp;

public class Article {
    private String title;
    private String description;
    private int time;

    public Article(String title, String description, int time) {
        this.title = title;
        this.description = description;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getTime() {
        return time;
    }
}
