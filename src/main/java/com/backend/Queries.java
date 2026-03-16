package com.backend;

public class Queries {
    public String getAllMedia() {
        return "SELECT * FROM media ORDER BY title ASC";
    }
    /*
    @ToDo
    Maybe make a statement afterwards that sorts by rating DESC/ASC
     */

    public String getMediaByType(String type) {
        // type wäre dann 'Anime', 'Game', 'Movie' etc.
        return "SELECT * FROM media WHERE type = '" + type + "' ORDER BY title ASC";
    }

    //media details and specific details....
    public String getTVShowDetails() {
        return "SELECT m.*, t.episodeCount, t.director, t.seasons, t.actors, t.actors_img" +
                "FROM media m" +
                "JOIN tvshow_details t ON m.id = t.id";
    }

    public String getGameDetails() {
        return "SELECT m.*, g.platform, g.publisher, g.playtime, g.mulitplayer" +
                "FROM media m " +
                "JOIN game_details g ON m.id = g.id";
    }

    public String getMovieDetails() {
        return "SELECT m.*, mo.length, mo.director, mo.actors, mo.actors_img" +
                "FROM media m " +
                "JOIN movie_details mo ON m.id = mo.id";
    }

    public String getBookDetails() {
        return "SELECT m.*, b.pageCount, b.publisher, b.writer," +
                "FROM media m " +
                "JOIN book_details b ON m.id = b.id";
    }

    public String getMusicDetails() {
        return "SELECT m.*, mu.length, mu.vocalist, mu.producer, mu.composer" +
                "FROM media m " +
                "JOIN music_details g ON m.id = mu.id";
    }
}
