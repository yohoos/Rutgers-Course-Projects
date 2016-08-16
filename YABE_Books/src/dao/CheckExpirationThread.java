package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

public class CheckExpirationThread extends Thread {

	@Override
	public void run() {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "yabe22";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "Becaused7Ka";

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst  = null;
		
		String query = "SELECT * FROM yabe22.BookListing;";

		String remove = "DELETE FROM yabe22.BookListing ";
		String add = "INSERT INTO yabe22.ClosedListing ";
		String update = "UPDATE BookListing SET Status='CLOSED' ";
		
//		System.out.println("CheckExpirationThread running!");

		while (!interrupted()) {
			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url + dbName, userName, password);

				st = conn.createStatement();
				rs = st.executeQuery(query);

				while (rs.next()) {
					Timestamp ts = rs.getTimestamp("EndTime");
					Calendar thisEnd = Calendar.getInstance();
					thisEnd.setTimeInMillis(ts.getTime());
					Calendar now = Calendar.getInstance();
					if (thisEnd.compareTo(now) < 0) {
						if (rs.getString("Status").equalsIgnoreCase("CLOSED")) {
							continue;
						}
//						PreparedStatement pst = conn.prepareStatement(remove  +
//								"WHERE AuctionName='" + rs.getString("AuctionName") + "';");
//						pst.executeUpdate();
//						
//						pst = conn.prepareStatement(add + 
//								"VALUES (" + rs.getString("AuctionName") + ");");
//						pst.executeUpdate();
						
						
						pst = conn.prepareStatement(update + 
								"WHERE AuctionName='" + rs.getString("AuctionName") + "';");
						pst.executeUpdate();
//						System.out.println(rs.getString("AuctionName") + " now closed");
					}
				}
//				System.out.println("All done");
				Thread.sleep(10000);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				interrupt();
				rs = null;
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (st != null) {
					try {
						st.close();
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
		}
	}
}
