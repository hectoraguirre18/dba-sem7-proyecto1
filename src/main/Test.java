/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import connection.DBConnection;

/**
 *
 * @author pc
 */
public class Test {

    public static DBConnection connection = null;
    
    public static void main(String[] args) {
        try {
            DBConnection conn = new DBConnection();
            conn.getConnection();
        } catch (Exception e) {
        }
    }
    
}
