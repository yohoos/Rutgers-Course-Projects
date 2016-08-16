package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AutoBidThread extends Thread {

	static String url = "jdbc:mysql://localhost:3306/";
	static String dbName = "yabe22";
	static String driver = "com.mysql.jdbc.Driver";
	static String userName = "root";
	static String password = "Becaused7Ka";

	public static boolean addAutoBid(String name, String auctionName, String balance) {
		boolean status;
		
		Connection conn = null;
		PreparedStatement pst = null;

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);

			pst = conn.prepareStatement("INSERT INTO AutoBid Values(?,?,?)");
			pst.setString(1, name);
			pst.setString(2, auctionName);
			pst.setString(3, balance);

			pst.executeUpdate();

			status = InsertBid.insertBid(name, auctionName, "0.0");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			status = false;
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

	@Override
	public void run() {
		Connection conn = null;
		Statement stListings = null;
		Statement stAccounts = null;
		Statement stAuctionMaxBid = null;
		Statement stUserMaxBid = null;
		Statement stUserBalance = null;
		
		ResultSet allListings = null;
		ResultSet allAccounts = null;
		ResultSet auctionMaxBid = null;
		ResultSet userMaxBid = null;
		ResultSet userBalance = null;
		
		PreparedStatement pst = null;

		String getAllListings = "SELECT * FROM yabe22.BookListing;";
		String getAllUsers = "SELECT * FROM yabe22.Account;";

		System.out.println("AutoBid Running");

		while (!interrupted()) {
			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url + dbName, userName, password);

				stListings = conn.createStatement();
				allListings = stListings.executeQuery(getAllListings);
				
				stAccounts = conn.createStatement();
				allAccounts = stAccounts.executeQuery(getAllUsers);

				while (allAccounts.next()) {
					String name = allAccounts.getString("Name");
					
					while (allListings.next()) {
						String auctionName = allListings.getString("AuctionName");
						
						String getAuctionMaxBid = 
								"SELECT MAX(Amount) FROM Bid WHERE Bid.AuctionName='" + auctionName + "';";
						stAuctionMaxBid = conn.createStatement();
						auctionMaxBid = stAuctionMaxBid.executeQuery(getAuctionMaxBid);
						auctionMaxBid.next();
						String auctionMaxBidString = auctionMaxBid.getString(1);
						
						String getUserMaxBid = 
								"SELECT MAX(Amount) FROM Bid "
										+ "WHERE Bid.AuctionName='" + auctionName + "' "
										+ "AND Bid.Name='" + name + "';";
						stUserMaxBid = conn.createStatement();
						userMaxBid = stUserMaxBid.executeQuery(getUserMaxBid);
						userMaxBid.next();
						String userMaxBidString = userMaxBid.getString(1);
						
						if (userMaxBidString.compareTo(auctionMaxBidString) < 0) {
							String getUserBalance = 
									"SELECT Balance FROM AutoBid "
											+ "WHERE Bid.AuctionName='" + auctionName + "' "
											+ "AND Bid.Name='" + name + "';";
							stUserBalance = conn.createStatement();
							userBalance = stUserBalance.executeQuery(getUserBalance);
							userBalance.next();
							String userBalanceString = userBalance.getString(1);
							
							double auctionMaxBidDouble = Double.parseDouble(auctionMaxBidString);
							double userBalanceDouble = Double.parseDouble(userBalanceString);
							if (userBalanceDouble - auctionMaxBidDouble > 1.0) {
								String newUserBalanceString = (userBalanceDouble - 1.0) + "";
								String updateBalance = 
										"UPDATE AutoBid SET Balance='" + newUserBalanceString + "' "
												+ "WHERE AutoBid.AuctionName='" + auctionName + "' "
												+ "AND AutoBid.Name='" + name + "';";
								pst = conn.prepareStatement(updateBalance);
								pst.executeUpdate();
								
								InsertBid.insertBid(name, auctionName, (auctionMaxBidDouble + 1.0) + "");
							}
						}
					}
					
					System.out.println(name + "'s AutoBid done.");
				}
				System.out.println("all done.");
				Thread.sleep(10000);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				interrupt();
				allListings = null;
				allAccounts = null;
				auctionMaxBid = null;
				userMaxBid = null;
				userBalance = null;
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stListings != null) {
					try {
						stListings.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stAccounts != null) {
					try {
						stAccounts.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stAuctionMaxBid != null) {
					try {
						stAuctionMaxBid.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stUserMaxBid != null) {
					try {
						stUserMaxBid.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stUserBalance != null) {
					try {
						stUserBalance.close();
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
