package Database;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        DB db = new DB();
        db.selectAuthor("Murakami");
        db.selectISBN("1234567891239");
        db.listAll();
    }
}
