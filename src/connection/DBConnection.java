/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author pc
 */
public class DBConnection {
    private Connection conn = null;
    
    public DBConnection() {
        openConnection();
    }
    
    private void openConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "scott");
            if(conn != null){
                System.out.println("Successful connection");
            }
        } catch(SQLException | ClassNotFoundException ex) {
            System.out.println("Error connecting to database");
        }
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
