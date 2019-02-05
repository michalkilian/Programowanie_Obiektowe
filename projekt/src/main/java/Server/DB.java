package Server;

import GeneralClasses.Meme;

import java.io.*;

import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

public class DB {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public void connect() throws SQLException {
        int numbOfAttempts = 0;
        while (numbOfAttempts < 3) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                conn =
                        DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/kilian2",
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
        if (numbOfAttempts == 3) {
            throw new SQLException();
        }

    }


    public ArrayList<Meme> getAll() throws SQLException {
        ArrayList<Meme> memeList = new ArrayList<>();
        try {
            connect();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM memes");

            addMemesFromQueryToList(memeList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
        return memeList;
    }


    public ArrayList<Meme> getSelectedTitle(String title) throws SQLException {
        ArrayList<Meme> memeList = new ArrayList<>();
        try {
            connect();
            stmt = conn.createStatement();


            PreparedStatement statement = conn.prepareStatement("SELECT * FROM memes WHERE title LIKE ?");
            statement.setString(1, ("%" + title));
            rs = statement.executeQuery();

            addMemesFromQueryToList(memeList);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
        return memeList;
    }

    public ArrayList<Meme> getSelectedTag(String tag) throws SQLException {
        ArrayList<Meme> memeList = new ArrayList<>();
        try {
            connect();
            stmt = conn.createStatement();


            PreparedStatement statement = conn.prepareStatement("SELECT * FROM memes WHERE tag LIKE ?");
            statement.setString(1, ("%" + tag));
            rs = statement.executeQuery();

            addMemesFromQueryToList(memeList);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
        return memeList;
    }

    public ArrayList<Meme> getSelectedAuthor(String author) throws SQLException {
        ArrayList<Meme> memeList = new ArrayList<>();
        try {
            connect();
            stmt = conn.createStatement();


            PreparedStatement statement = conn.prepareStatement("SELECT * FROM memes WHERE autisticpseudo LIKE ?");
            statement.setString(1, ("%" + author));
            rs = statement.executeQuery();

            addMemesFromQueryToList(memeList);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
        return memeList;
    }

    public String signIn(String username, String passwd) {
        try {
            connect();
            stmt = conn.createStatement();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE username LIKE ?");
            statement.setString(1, (username));
            rs = statement.executeQuery();

            if (checkSigning(passwd)) {
                return rs.getString(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanRes();
        }
        return null;
    }

    public boolean signUp(String username, String passwd, String autisticpseudo) {
        try {
            connect();
            stmt = conn.createStatement();

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            java.sql.Date sqlData = new java.sql.Date(date.getTime());


            PreparedStatement statement = conn.prepareStatement("INSERT INTO users (username, autisticpseudo, passwd, registerdate)" + " VALUES (?,?,?,?)");
            statement.setString(1, (username));
            statement.setString(2, (autisticpseudo));
            statement.setString(3, (passwd));
            statement.setDate(4, sqlData);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanRes();
        }
        return false;
    }

    private boolean checkSigning(String passwd) throws SQLException {
        rs.next();
        if (rs.getString(4).equals(passwd)) {
            return true;
        }
        return false;
    }


    public void addMeme(Meme meme) throws SQLException {
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            PreparedStatement statement = conn.prepareStatement("INSERT INTO memes (autisticpseudo, title, tag, meme)" + "VALUES (?,?,?,?)");
            statement.setString(1, meme.getAuthor());
            statement.setString(2, meme.getTitle());
            statement.setString(3, meme.getTag());
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(SwingFXUtils.fromFXImage(meme.getImage(), null), "png", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            statement.setBlob(4, is);
            statement.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
    }


    private void addMemesFromQueryToList(ArrayList<Meme> memeList) throws SQLException, IOException {
        while (rs.next()) {

            String author = rs.getString(2);
            String title = rs.getString(3);
            String tag = rs.getString(4);
            InputStream memeImage = rs.getBinaryStream(5);

            Image image = SwingFXUtils.toFXImage(ImageIO.read(memeImage), null);

            Meme meme = new Meme(tag, author, title, image);
            meme.setRating(String.valueOf(rs.getInt(6)));
            meme.setMemeID(rs.getInt(1));
            memeList.add(meme);
        }
    }

    public HashMap<String, String> getStats(String username) {
        HashMap<String, String> stats = new HashMap<>();
        try {
            connect();
            stmt = conn.createStatement();

            PreparedStatement userStatement = conn.prepareStatement("SELECT userid, autisticpseudo, registerdate " +
                    "FROM users WHERE username LIKE ?");
            userStatement.setString(1, username);
            rs = userStatement.executeQuery();
            rs.next();
            // String userId = rs.getString(1);
            String autisticPseudo = rs.getString(2);
            stats.put("registerDate", rs.getString(3));

            PreparedStatement statement = conn.prepareStatement("SELECT sum(rating), count(memeid), max(rating) FROM memes WHERE autisticpseudo LIKE ?");
            statement.setString(1, (autisticPseudo));
            rs = statement.executeQuery();
            rs.next();
            stats.put("karma", String.valueOf(rs.getInt(1)));
            stats.put("numberOfMemes", String.valueOf(rs.getInt(2)));
            stats.put("topMeme", String.valueOf(rs.getInt(3)));


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanRes();
        }

        return stats;
    }

    public boolean rateMeme(String username, int memeID) {
        boolean result = false;
        try {
            connect();
            stmt = conn.createStatement();

            PreparedStatement userStatement = conn.prepareStatement("SELECT userid FROM users WHERE username LIKE ?");
            userStatement.setString(1, username);
            rs = userStatement.executeQuery();
            rs.next();
            int userID = rs.getInt(1);


            PreparedStatement statement = conn.prepareStatement("INSERT INTO userratings values(? ,?)");
            statement.setInt(1, userID);
            statement.setInt(2, memeID);
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanRes();
        }
        return result;
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
}