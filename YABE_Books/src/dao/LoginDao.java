package dao;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.ArrayList;  
  
public class LoginDao {  
    public static ArrayList<String> validate(String name, String pass) {          
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
        ArrayList<String> arr = new ArrayList<String>();
  
        String url = "jdbc:mysql://localhost:3306/";  
        String dbName = "yabe22";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "Becaused7Ka";  
//        String password = "magicdust50";
        try {  
            Class.forName(driver).newInstance();  
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);  
  
            pst = conn  
                    .prepareStatement("select * from Account where Name=? and Password=?");  
            pst.setString(1, name);
            pst.setString(2, pass);  
  
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	arr.add(rs.getString(1));
            	arr.add(rs.getString(2));
            	arr.add(rs.getString(3));
            	arr.add(rs.getString(4));
            }
  
        } catch (Exception e) {  
            System.out.println("LoginDao has failed!");  
        } finally {  
            if (conn != null) {  
                try {  
                    conn.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (pst != null) {  
                try {  
                    pst.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (rs != null) {  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return arr;  
    }  
}  