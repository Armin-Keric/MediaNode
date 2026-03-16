package com.backend.model;

import java.time.LocalDate;

public class Book_details extends Media {
    private int pageCount;
    private String publisher;
    private String writer;

    public Book_details(Integer id, String title, LocalDate release_date, String type, String description, String img_url, int pageCount, String publisher, String writer) {
        super(id, title, release_date, type, description, img_url);
        this.pageCount = pageCount;
        this.publisher = publisher;
        this.writer = writer;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
