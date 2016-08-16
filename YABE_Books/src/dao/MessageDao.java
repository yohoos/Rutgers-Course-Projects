package dao;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement; 
import java.sql.SQLException;  

public class MessageDao {
	public static int store(String name, String message) {

        Connection conn = null;  
        PreparedStatement pst = null;  
        int rs = 0;  
  
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
                     .prepareStatement("Insert Into UserQuestion(Name, Question) Values(?,?)");
             pst.setString(1, name);  
             pst.setString(2, message);
   
             rs = pst.executeUpdate();
        } catch (Exception e) {
        	System.out.println(e);
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
        }
		
		return rs;
	}

}
