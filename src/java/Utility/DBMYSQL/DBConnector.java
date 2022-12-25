/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility.DBMYSQL;

import java.sql.*;

/**
 *
 * @author LENOVO
 */
public class DBConnector {
    public static DBConnector GetConnection() {
        if (connector == null) {
            connector = new DBConnector();
        }
        return connector;
    }
    
    public String QueryExec(String query) {
        //String error = "no error";
        try {
            stmt = con.createStatement();
            stmt.execute(query);
        }
        catch (Exception e) {
           error = e.toString() + " : " + query;
        }
        return error;
    }
    
    public int QueryExecUpd(String query){
        int rowsImpacted = 0;
        try {
            stmt = con.createStatement();
            rowsImpacted = stmt.executeUpdate(query);
        }
        catch (Exception e) {
            error = e.toString() + " : " + query;
        }
        return rowsImpacted;
    }
    
    public ResultSet QueryExecRS(String query) {
        //String error = "no error";
        try {
            stmt = con.createStatement();
            return stmt.executeQuery(query);
        }
        catch (Exception e) {
            error = e.toString() + " : " + query;
            return null;
        }
    }
    
    public String getError() {
        return error;
    }
    
    private DBConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummyresponses?useSSL=false","root","Test123!");
                    //"jdbc:mysql://localhost:3306/dummyresponses?autoReconnect=true&useSSL=false");
                    //"jdbc:mysql://localhost:3306/dummyresponses","root","Test123!");
            error = "0"; // no error
            isConnected = true;
        }
        catch (Exception e) {
            error = e.toString();
            isConnected = false;
        }
    }
    Statement stmt = null;
    static DBConnector connector = null;
    boolean isConnected = false;
    Connection con = null;
    String error = "no connection"; // last error generated
}
