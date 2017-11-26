/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railways;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ajay
 */
public class SqlConnection {
   
    /**
     *
     * @return 
     */
    public static Connection Connector() throws SQLException {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ajay", "root", "123456");
                System.out.println("connected");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE,null,ex);
                
            }
        return null;
    }
}