package service_pack;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService(serviceName = "myservice")
public class myservice {
     // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/java_lab4_db";
    private static final String user = "root";
    private static final String password = "***";
    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    @WebMethod(operationName = "getBookList")
    public Vector<String> getBookList() {
        //return value 
        Vector<String> result = new Vector<String>();
        //sql query
        String query = "select name from books";
        //connecting jdbc driver
        try {
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) { e.printStackTrace(); }
        //connecting to db
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String bookName = rs.getString(1);
                result.add(bookName);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return result;
    }

    @WebMethod(operationName = "getBook")
    public String getBook(String bookName) {
        //return value
        String result = null;
        //path for book on server
        String path = null;
        //sql query
        String query = "select location, name from books where name = '" + bookName + "'";
        //connecting jdbc driver
        try {
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) { e.printStackTrace(); }
        //connecting to db
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                path = rs.getString(1) + rs.getString(2);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        //reading from file
        try(FileReader reader = new FileReader(path)) {
            //read one character
            int c;
            result = "";
            while((c = reader.read()) != -1){
                result += (char)c;
            } 
        } catch(IOException ex) { System.out.println(ex.getMessage()); }   
        return result;
    }
}
