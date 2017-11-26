/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Ajay
 */
public final class DatabaseHandler {
    private static DatabaseHandler handler;
    
   // private static final String DB_URL = "jdbc:mariadb://localhost:3306/ajay", "root", "123456";
    private static Connection conn=null;
    private static Statement stmt=null;

    public DatabaseHandler() {
         createConnection();
    }
    
    
    
    void createConnection(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/railway", "root", "123456");
           //conn = DriverManager.getConnection("jdbc:mariadb:railway");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet execQuery(String query){
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Execution at execQuery:datahandler" + ex.getLocalizedMessage());
            return null;
        } finally{
        }
        return result;
    }
    
        public boolean execAction(String qu){
            try {
                stmt = conn.createStatement();
                stmt.execute(qu);
                return true;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error:" + ex.getMessage(),"Error Occured",JOptionPane.ERROR_MESSAGE);
                System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
                return false;
            }finally{
                
            }
        }

}  
