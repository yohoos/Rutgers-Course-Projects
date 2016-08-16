package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertAnswerDao {
	public static boolean insertAnswer(String qid, String answer, String username) {
		  
        Connection conn = null;  
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean status = false;
  
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
  
            pst = conn.prepareStatement("Insert into Answers(Answer, QID, Name) Values(?,?,?)");
            pst.setString(1, answer);
            pst.setString(2, qid);
            pst.setString(3, username);
            int check = pst.executeUpdate();
            status = true;
        } catch (Exception e) {
        	System.out.println("InsertAnswerDao.java is not working");
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
        return status;
	}
}
