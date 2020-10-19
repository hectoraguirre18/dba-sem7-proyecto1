/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.DBConnection;
import static controller.DepartmentDAO.connection;
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
public class EmployeeDAO {
    public static Connection connection = null;
    
    public EmployeeDAO() {
        if(connection == null){
            connection = new DBConnection().getConnection();
        }
    }
    
    public DefaultTableModel listEmployees() {
        try {
            DefaultTableModel table = new DefaultTableModel();
            
            table.addColumn("No.");
            table.addColumn("Name");
            table.addColumn("Job");
            table.addColumn("MGR");
            table.addColumn("Hire Date");
            table.addColumn("Salary");
            table.addColumn("Commission");
            table.addColumn("Department Number");
            
            CallableStatement cstmt = connection.prepareCall("{ ? = call FN_EMPLIST}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            
            cstmt.execute();
            
            ResultSet rs = ((OracleCallableStatement)cstmt).getCursor(1);
            
            String[] data = new String[8];
            
            while(rs.next()) {
                data[0] = String.valueOf(rs.getInt("EMPNO"));
                data[1] = rs.getString("ENAME");
                data[2] = rs.getString("JOB");
                data[3] = String.valueOf(rs.getInt("MGR"));
                data[4] = String.valueOf(rs.getDate("HIREDATE"));
                data[5] = String.valueOf(rs.getInt("SAL"));
                data[6] = String.valueOf(rs.getInt("COMM"));
                data[7] = String.valueOf(rs.getInt("DEPTNO"));
                
                table.addRow(data);
            }
            return table;
        } catch (Exception e) {
            System.out.println("Error getting employees");
            e.printStackTrace();
            return null;
        }
    }
    
    public void insertEmployee(String number, String name, String job, String mgr, String hireDate, String sal, String comm, String deptNo) {
        try {
            CallableStatement cstmt = connection.prepareCall("{ call FN_INSERTEMP(?, ?, ?, ?, ?, ?, ?, ?)}");
            
            cstmt.setString(1, number);
            cstmt.setString(2, name);
            cstmt.setString(3, job);
            cstmt.setString(4, mgr);
            cstmt.setString(5, hireDate);
            cstmt.setString(6, sal);
            cstmt.setString(7, comm);
            cstmt.setString(8, deptNo);
            
            cstmt.execute();
        } catch (Exception e) {
            System.out.println("Error inserting department: " + e);
        }
    }
    
    public void deleteEmpByNo(String number) {
        try {
            CallableStatement cstmt = connection.prepareCall("{ call FN_DELETEBYEMP(?)}");
            
            cstmt.setString(1, number);
            
            cstmt.execute();
            
        } catch (Exception e) {
            System.out.println("Error deleting Employee: " + e);
        }
    }
}
