/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.DBConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author pc
 */
public class DepartmentDAO {
    public static Connection connection = null;
    
    public DepartmentDAO() {
        if(connection == null){
            connection = new DBConnection().getConnection();
        }
    }
    
    public DefaultTableModel listDepartments() {
        try {
            DefaultTableModel table = new DefaultTableModel();
            
            table.addColumn("No.");
            table.addColumn("Name");
            table.addColumn("Location");
            
            CallableStatement cstmt = connection.prepareCall("{ ? = call FN_LIST}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            
            cstmt.execute();
            
            ResultSet rs = ((OracleCallableStatement)cstmt).getCursor(1);
            
            String[] data = new String[3];
            
            while(rs.next()) {
                data[0] = String.valueOf(rs.getInt("DEPTNO"));
                data[1] = rs.getString("DNAME");
                data[2] = rs.getString("LOC");
                
                table.addRow(data);
            }
            return table;
        } catch (Exception e) {
            System.out.println("Error getting departments");
            e.printStackTrace();
            return null;
        }
    }
    
    public String findDepartmentByNo(String number) {
        try {
            CallableStatement cstmt = connection.prepareCall("{ ? = call FN_FINDBYDEPTNO(?)}");
            
            cstmt.setString(2, number);
            cstmt.registerOutParameter(1, OracleTypes.VARCHAR);
            
            cstmt.execute();
            
            String name = ((OracleCallableStatement)cstmt).getString(1);
            
            return name;
        } catch (Exception e) {
            return null;
        }
    }
}
