package org.softwire.training.bookish;

//import com.mysql.cj.xdevapi.Statement;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import io.github.cdimascio.dotenv.Dotenv;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.softwire.training.bookish.models.database.Book;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws SQLException {

        Dotenv dotenv = Dotenv.load();

        String hostname = "localhost";
        String database = "bookish";
        String user = "root";
        String password =dotenv.get("MYSQL_PASS");
        String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

//        jdbcMethod(connectionString);
        jdbiMethod(connectionString);
    }

    private static void jdbcMethod(String connectionString) throws SQLException {
        System.out.println("JDBC method...");

        // TODO: print out the details of all the books (using JDBC)
        // See this page for details: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        Connection con = DriverManager.getConnection(connectionString);

        Statement stmt = null;
        String query = "SELECT * FROM books";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String title = rs.getString("title");
                System.out.println(title);

            }
        } catch (SQLException e ) {
//            JDBCTutorialUtilities.printSQLException(e);
            System.out.println(e);
        } finally {
            if (stmt != null) { stmt.close(); }
        }

    }

    private static void jdbiMethod(String connectionString) {
        System.out.println("\nJDBI method...");

        // TODO: print out the details of all the books (using JDBI)
        // See this page for details: http://jdbi.org
        // Use the "Book" class that we've created for you (in the models.database folder)

        Jdbi jdbi = Jdbi.create(connectionString);

//        List<Book> names = jdbi.withHandle(handle ->
//                handle.createQuery("SELECT title FROM books")
//                        .mapTo(Book.class)
//                        .list());


        Handle handle = jdbi.open();

        handle.registerRowMapper(ConstructorMapper.factory(Book.class));
        Set<Book> userSet = handle.createQuery("SELECT * FROM books")
                .mapTo(Book.class)
                .collect(Collectors.toSet());

        userSet.forEach( book -> System.out.println(book.getTitle()));
    }
}
