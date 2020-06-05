package nerdygadgets.dal;

import nerdygadgets.shared.Utility;

import java.sql.*;
import java.util.Properties;

public class Database {
    private String url = "jdbc:mysql://localhost:3306/wideworldimporters?&serverTimezone=Europe/Amsterdam";
    private String username = "root";
    private String password = "";

    /**
     * Initializes a new instance of the Database class.
     * 
     * @exception ClassNotFoundException
     */
    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Utility.handleUnexpectedException(e);
        }
    }    

    /**
     * Opens a fresh connection to the database.
     * 
     * @exception Exception
     */
    public Connection open() throws SQLException {
        try {
            Properties props = new Properties();
            props.setProperty("user", username);
            props.setProperty("password", password);
            
            return DriverManager.getConnection(url, props);            
        } catch (SQLException e) {
            Utility.handleUnexpectedException(e);
            throw e;
        } 
    }

}