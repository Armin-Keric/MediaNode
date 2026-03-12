package com.backend.model;

import java.time.LocalDate;

public class Movie_details extends Media {
    private int length;
    private String director;
    private String actors;
    private String actors_img;

    public Movie_details(Integer id, String title, LocalDate release_date, String type, String description, String img_url, int length, String director, String actors, String actorsImg) {
        super(id, title, release_date, type, description, img_url);
        this.length = length;
        this.director = director;
        this.actors = actors;
        actors_img = actorsImg;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getActors_img() {
        return actors_img;
    }

    public void setActors_img(String actors_img) {
        this.actors_img = actors_img;
    }
}


