package com.backend.model;

import java.time.LocalDate;
import java.util.Calendar;

public class Media {
    private Integer id;
    private String title;
    private LocalDate release_date;
    String type;
    String description;
    String img_url;

    public Media(Integer id, String title, LocalDate release_date, String type, String description, String img_url) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.type = type;
        this.description = description;
        this.img_url = img_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
