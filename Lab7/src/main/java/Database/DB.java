package Database;

import javafx.scene.control.Button;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by student on 2018-11-27.
 */
public class DB{
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    public void connect() throws SQLException {
        int numbOfAttempts = 0;
        while (numbOfAttempts < 3) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn =
                        DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/kilian",
                                "kilian", "v0HhS9XnMy1ACkai");
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


    public ArrayList<Book> getAll(){
        ArrayList<Book> bookList = new ArrayList<>();
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            rs = stmt.executeQuery("SELECT * FROM books");

            addBooksFromQueryToList(bookList);
        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
        return bookList;
    }



    public ArrayList<Book> getSelectedAuthor(String surname) {
        ArrayList<Book> bookList = new ArrayList<>();
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM books WHERE author LIKE ?");
            statement.setString(1, ("%"+surname));
            rs = statement.executeQuery();

            addBooksFromQueryToList(bookList);

        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
        return bookList;
    }

    public ArrayList<Book> getSelectedISBN(String isbn) {
        ArrayList<Book> bookList = new ArrayList<>();
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM books WHERE isbn = ?");
            statement.setString(1, isbn);
            rs = statement.executeQuery();

            addBooksFromQueryToList(bookList);
        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
        return bookList;
    }

    public void printAllBooks() {
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

    public void printSelectedAuthor(String surname) {
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

    public void printSelectedISBN(String isbn) {
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

    public void addBook(Book book){
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            PreparedStatement statement = conn.prepareStatement("INSERT INTO books (isbn, title, author, year)" + "VALUES (?,?,?,?)");
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getYear());
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
            Book book = new Book(isbn, title, author, year);
            book.print();
        }
    }

    private void addBooksFromQueryToList(ArrayList<Book> bookList) throws SQLException {
        while (rs.next()) {

            String isbn = rs.getString(1);
            String title = rs.getString(2);
            String author = rs.getString(3);
            String year = rs.getString(4);
            Book book = new Book(isbn, title, author, year);
            bookList.add(book);
        }
    }

}

