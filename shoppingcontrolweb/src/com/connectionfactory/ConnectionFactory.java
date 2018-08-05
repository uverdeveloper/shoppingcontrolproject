package com.connectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionFactory {

	protected Connection conn;

	public ConnectionFactory(Connection conn) {
		this.conn = conn;
	}

	public static Connection getConnection() {

		String url = "jdbc:mysql://localhost:3306/purchase_historic?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&verifyServerCertificate=false&useSSL=true&noAccessToProcedureBodies=true";
		String username = "root";
		String password = "root";

		Connection connection;
		try {

			connection = DriverManager.getConnection(url, username, password);

			return connection;
		}catch (NullPointerException e){
			System.err.println(e.getMessage());
			return null;
		}		catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
