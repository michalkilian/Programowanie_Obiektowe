package Workers;

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
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
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

    public void printAllWorkers() {
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            rs = stmt.executeQuery("SELECT * FROM workers");

            printAll();
        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            cleanRes();
        }
    }


    public void addWorker(Worker worker){
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            PreparedStatement statement = conn.prepareStatement("INSERT INTO workers (pesel, imie, nazwisko, wynagrodzenie)" + "VALUES (?,?,?,?)");
            statement.setString(1, worker.getPesel());
            statement.setString(2, worker.getImie());
            statement.setString(3, worker.getNazwisko());
            statement.setDouble(4, worker.getWynagrodzenie());
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

            String pesel = rs.getString(1);
            String name = rs.getString(2);
            String surname = rs.getString(3);
            Double wynagrodzenie = rs.getDouble(4);
            Worker worker = new Worker(pesel, name, surname, wynagrodzenie);
            worker.print();
        }
    }

    private void addWorkersFromQueryToList(ArrayList<Worker> workerList) throws SQLException {
        while (rs.next()) {

            String pesel = rs.getString(1);
            String name = rs.getString(2);
            String surname = rs.getString(3);
            Double wynagrodzenie = rs.getDouble(4);
            Worker worker = new Worker(pesel, name, surname, wynagrodzenie);
            workerList.add(worker);
        }
    }

}