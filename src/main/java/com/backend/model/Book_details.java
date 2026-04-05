package com.backend.model;

import com.backend.Database;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

//UHM please make table
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
    public static Book_details getBookDetails(int mediaId) throws SQLException {
        Database database = Database.getInstance();
        Connection c = database.getConnection();

        String sql = "SELECT m.*, b.pagecount, b.publisher, b.writer, b.languages " +
                "FROM media m JOIN book_details b ON m.id = b.id " +
                "WHERE m.id = ?";

        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setInt(1, mediaId);
        ResultSet res = stmt.executeQuery();

        if (res.next()) {
            return new Book_details(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getObject("release_date", LocalDate.class),
                    res.getString("type"),
                    res.getString("description"),
                    res.getString("img_url"),
                    res.getInt("pagecount"),
                    res.getString("publisher"),
                    res.getString("writer")
            );
        }
        return null;
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
