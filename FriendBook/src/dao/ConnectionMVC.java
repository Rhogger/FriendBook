package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMVC {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/friendbook?useSSL=false";
	private static Connection connection = null;

	public Connection getConnection() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(DBURL, "root", "masterkey");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
}
