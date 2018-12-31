package Server;

import GeneralClasses.Meme;
import java.io.*;

import java.sql.*;
import java.util.ArrayList;

public class DB{
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    public void connect() throws SQLException {
        int numbOfAttempts = 0;
        while (numbOfAttempts < 3) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                conn =
                        DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/kilian",
                                "kilian2", "4HU6ZHVf6YAJhzJo");
                break;
            } catch (SQLException ex) {


                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                numbOfAttempts += 1;
                // handle any errors




            } catch (Exception e) {
                numbOfAttempts += 1;
                e.printStackTrace();
            }
        }
        if (numbOfAttempts == 3){
            throw new SQLException();
        }

    }


    public ArrayList<Meme> getAll(){
        ArrayList<Meme> memeList = new ArrayList<>();
        try {
            connect();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM memes");

            addMemesFromQueryToList(memeList);
        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
        return memeList;
    }



    public ArrayList<Meme> getSelectedTitle(String title) {
        ArrayList<Meme> memeList = new ArrayList<>();
        try {
            connect();
            stmt = conn.createStatement();


            PreparedStatement statement = conn.prepareStatement("SELECT * FROM memes WHERE title LIKE ?");
            statement.setString(1, ("%"+title));
            rs = statement.executeQuery();

            addMemesFromQueryToList(memeList);

        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
        return memeList;
    }

    public ArrayList<Meme> getSelectedTag(String tag) {
        ArrayList<Meme> memeList = new ArrayList<>();
        try {
            connect();
            stmt = conn.createStatement();


            PreparedStatement statement = conn.prepareStatement("SELECT * FROM memes WHERE tag LIKE ?");
            statement.setString(1, ("%"+tag));
            rs = statement.executeQuery();

            addMemesFromQueryToList(memeList);

        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
        return memeList;
    }

    public ArrayList<Meme> getSelectedAuthor(String author) {
        ArrayList<Meme> memeList = new ArrayList<>();
        try {
            connect();
            stmt = conn.createStatement();


            PreparedStatement statement = conn.prepareStatement("SELECT * FROM memes WHERE autisticpseudo LIKE ?");
            statement.setString(1, ("%"+author));
            rs = statement.executeQuery();

            addMemesFromQueryToList(memeList);

        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
        return memeList;
    }


    public void addMeme(Meme meme){
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            PreparedStatement statement = conn.prepareStatement("INSERT INTO memes (autisticpseudo, title, tag, meme)" + "VALUES (?,?,?,?)");
            statement.setString(1, meme.getAuthor());
            statement.setString(2, meme.getTitle());
            statement.setString(3, meme.getTag());
            statement.setBlob(4, meme.getImage());
            statement.executeUpdate();

        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
    }

    private void cleanRes() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) {
            } // ignore
            rs = null;
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) {
            } // ignore

            stmt = null;
        }
    }


    private void addMemesFromQueryToList(ArrayList<Meme> bookList) throws SQLException {
        while (rs.next()) {

            String author = rs.getString(1);
            String title = rs.getString(2);
            String tag = rs.getString(3);
            InputStream memeImage = rs.getBinaryStream(4);
            Meme meme = new Meme(tag, author, title, memeImage);
            bookList.add(meme);
        }
    }

}