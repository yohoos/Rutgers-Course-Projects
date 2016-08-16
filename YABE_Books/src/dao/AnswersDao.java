package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnswersDao {
	public static ArrayList<String[]> getAnswers() {
		  
        Connection conn = null;  
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<String[]> arr = new ArrayList<String[]>();
  
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
  
            pst = conn.prepareStatement("Select UserQuestion.QID, UserQuestion.Name, Question, Answer "
            		+ "From UserQuestion LEFT OUTER JOIN Answers ON UserQuestion.QID = Answers.QID");
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	String[] tuple = new String[4];
            	tuple[0] = rs.getString(1);
            	tuple[1] = rs.getString(2);
            	tuple[2] = rs.getString(3);
            	if (rs.getString(4) == null) {
            		tuple[3] = "No Answer";
            	} else {
            		tuple[3] = rs.getString(4);
            	}
            	arr.add(tuple);
            }
            
        } catch (Exception e) {
        	System.out.println("AnswersDao.java is not working");
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
        return arr;
	}
}
