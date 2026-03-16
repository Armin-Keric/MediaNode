package com.backend.model;

import java.time.LocalDate;

public class Music_details extends Media {
    private int length;
    private String vocalist;
    private String producer;
    private String composer;

    public Music_details(Integer id, String title, LocalDate release_date, String type, String description, String img_url, int length, String vocalist, String producer, String composer) {
        super(id, title, release_date, type, description, img_url);
        this.length = length;
        this.vocalist = vocalist;
        this.producer = producer;
        this.composer = composer;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getVocalist() {
        return vocalist;
    }

    public void setVocalist(String vocalist) {
        this.vocalist = vocalist;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
