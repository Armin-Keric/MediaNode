package com.backend.model;

import java.time.LocalDate;
import java.util.Calendar;

public class Game_details extends Media {
    private int playtime;
    private String platform;
    private String publisher;

    private boolean multiplayer;

    public Game_details(Integer id, String title, LocalDate release_date, String type, String description, String img_url, int playtime, String platform, String publisher, boolean multiplayer) {
        super(id, title, release_date, type, description, img_url);
        this.playtime = playtime;
        this.platform = platform;
        this.publisher = publisher;
        this.multiplayer = multiplayer;
    }

    public int getPlaytime() {
        return playtime;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        this.multiplayer = multiplayer;
    }
}
