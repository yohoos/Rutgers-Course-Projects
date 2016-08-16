package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetEarningsDao {
	public static ArrayList<String[]> getEarnings(int num) {
		  
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
            
            if (num == 0) { //find total earnings
                pst = conn.prepareStatement("Select Sum(Earnings.Final) FROM "
                		+ "(Select Bid.AuctionName,  MAX(Bid.Amount) as Final FROM "
                		+ "BookListing JOIN Bid ON BookListing.AuctionName = Bid.AuctionName "
                		+ "Where Status = ? GROUP BY Bid.AuctionName) as Earnings");
                pst.setString(1, "CLOSED");
                rs = pst.executeQuery();
                while (rs.next()) {
                	String[] tuple = new String[1];
                	tuple[0] = rs.getString(1);
                	arr.add(tuple);
                }
            } else if (num == 1) {
            	pst = conn.prepareStatement("Select BookTitle, Sum(Final.Total) "
            			+ "FROM (Select AuctionName, MAX(Amount) as Total From Bid Group By AuctionName) as Final "
            			+ "JOIN BookListing ON BookListing.AuctionName = Final.AuctionName "
            			+ "Group By BookTitle");
            	rs = pst.executeQuery();
            	while (rs.next()) {
            		String[] tuple = new String[2];
            		tuple[0] = rs.getString(1);
            		tuple[1] = rs.getString(2);
            		arr.add(tuple);
            	}
            } else if (num == 2){
            	pst = conn.prepareStatement("Select GenreName, SubGenreName, Sum(Final.Total) "
            			+ "FROM (Select AuctionName, MAX(Amount) as Total From Bid Group By AuctionName) as Final "
            			+ "JOIN BookListing ON BookListing.AuctionName = Final.AuctionName "
            			+ "Group By GenreName, SubGenreName");
            	rs = pst.executeQuery();
            	while (rs.next()) {
            		String[] tuple = new String[3];
            		tuple[0] = rs.getString(1);
            		tuple[1] = rs.getString(2);
            		tuple[2] = rs.getString(3);
            		arr.add(tuple);
            	}
            } else if (num == 3) {
            	pst = conn.prepareStatement("Select BookListing.Name, Sum(Final.Total) "
            			+ "FROM (Select AuctionName, MAX(Amount) as Total From Bid Group By AuctionName) as Final "
            			+ "JOIN BookListing ON BookListing.AuctionName = Final.AuctionName "
            			+ "Group By BookListing.Name");
            	rs = pst.executeQuery();
            	while (rs.next()) {
            		String[] tuple = new String[2];
            		tuple[0] = rs.getString(1);
            		tuple[1] = rs.getString(2);
            		arr.add(tuple);
            	}
            } else if (num == 4) {
            	pst = conn.prepareStatement("Select BookTitle, Sum(Final.Total) "
            			+ "FROM (Select AuctionName, MAX(Amount) as Total From Bid Group By AuctionName) as Final "
            			+ "JOIN BookListing ON BookListing.AuctionName = Final.AuctionName "
            			+ "Group By BookTitle Order By Sum(Final.Total) Desc Limit 5");
            	rs = pst.executeQuery();
            	while (rs.next()) {
            		String[] tuple = new String[2];
            		tuple[0] = rs.getString(1);
            		tuple[1] = rs.getString(2);
            		arr.add(tuple);
            	}
            } else if (num == 5) {
            	pst = conn.prepareStatement("Select BookListing.Name, Sum(Final.Total) "
            			+ "FROM (Select AuctionName, MAX(Amount) as Total From Bid Group By AuctionName) as Final "
            			+ "JOIN BookListing ON BookListing.AuctionName = Final.AuctionName "
            			+ "Group By BookListing.Name Order By Sum(Final.Total) Desc Limit 5");
            	rs = pst.executeQuery();
            	while (rs.next()) {
            		String[] tuple = new String[2];
            		tuple[0] = rs.getString(1);
            		tuple[1] = rs.getString(2);
            		arr.add(tuple);
            	}
            }
            
        } catch (Exception e) {
        	System.out.println("GetEarningsDao.java is not working");
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
