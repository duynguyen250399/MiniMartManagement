/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DB {
    public static Connection makeConnection() throws ClassNotFoundException, SQLException{        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = null;
        String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=MiniMartDB";
        con = DriverManager.getConnection(connectionString, "sa", "123");
        
        return con;
    }
}
