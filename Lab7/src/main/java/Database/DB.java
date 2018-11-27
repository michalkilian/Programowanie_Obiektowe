package Database;

import java.sql.*;

/**
 * Created by student on 2018-11-27.
 */
public class DB{
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    public void connect() {
        int numbOfAttempts = 0;
        while (numbOfAttempts < 3) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn =
                        DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/kilian",
                                "kilian", "v0HhS9XnMy1ACkai");
                numbOfAttempts = 3;
            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                numbOfAttempts += 1;

            } catch (Exception e) {
                numbOfAttempts += 1;
                e.printStackTrace();
            }
        }
    }

    public void listAll() {
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            rs = stmt.executeQuery("SELECT * FROM books");

            printAll();
        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
    }

    public void selectAuthor(String surname) {
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM books WHERE author LIKE ?");
            statement.setString(1, ("%"+surname));
            rs = statement.executeQuery();

            printAll();
        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
    }

    public void selectISBN(String isbn) {
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM books WHERE isbn = ?");
            statement.setString(1, isbn);
            rs = statement.executeQuery();

            printAll();
        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
    }

    public void addBook(String isbn, String title, String author, String year){
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            PreparedStatement statement = conn.prepareStatement("INSERT INTO books (isbn, title, author, year)" + "VALUES (?,?,?,?)");
            statement.setString(1, isbn);
            statement.setString(2, title);
            statement.setString(3, author);
            statement.setString(4, year);
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

    private void printAll() throws SQLException {
        while (rs.next()) {

            String isbn = rs.getString(1);
            String title = rs.getString(2);
            String author = rs.getString(3);
            String year = rs.getString(4);
            System.out.println("ISBN " + isbn);
            System.out.println("Title " + title);
            System.out.println("Author " + author);
            System.out.println("Year " + year +'\n');
        }
    }

}

