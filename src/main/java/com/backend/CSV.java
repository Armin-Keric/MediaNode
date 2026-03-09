package com.backend;

import java.io.*;
import java.util.Calendar;

public class CSV {
    private JDBC connection;
    private String query_media;
    private String query_game_details;

    protected void addToDB() throws FileNotFoundException {
        String line;
        //@ToDo
        //look into the date object and see how you want to do this....
        Calendar release_date = Calendar.getInstance();
        connection = new JDBC();

        release_date.add(Calendar.YEAR, 1);

        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {

            while ((line = br.readLine()) != null) {
                String[] values;

                values = line.split(";");

                //GUIDE: title;release_date;type;description;img_url

                query_media = "INSERT INTO media (title, release_date, type, description, img_url) VALUES" + "(" + "'" + values[0].replace("'", "") + "'" + "," + "'" + values[1] + "'" + "," + "'" + values[2] + "'" + "," + "'" + values[3].replace("'", "") + "'" + "," + "'" + values[4] + "'" + ")" + ";";
                //query_game_details = "INSERT INTO game_details (id, playtime, platform, publisher, multiplayer) VALUES" + "(" + "'" + values[0].replace("'", "") + "'" + "," + "'" + values[1] + "'" + "," + "'" + values[2] + "'" + "," + "'" + values[3].replace("'", "") + "'" + "," + "'" + values[4] + "'" + ")" + ";";
                connection.openConnection(query_media);
                System.out.println(query_media);
            }
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }
}
