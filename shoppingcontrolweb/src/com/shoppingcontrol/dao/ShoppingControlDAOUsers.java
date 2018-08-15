package com.shoppingcontrol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.connectionfactory.ConnectionFactory;
import com.shoppingcontrol.bean.ShoppingControlUsersBean;

public class ShoppingControlDAOUsers extends ConnectionFactory {

	static ShoppingControlUsersBean shoppingControlUserBean = new ShoppingControlUsersBean();

	public ShoppingControlDAOUsers(Connection conn) {
		super(conn);
	}

	private String selectDBUser = "select * from user_pwd_table where users = '";
	private String selectDBPwdUser = "select * from user_pwd_table where user_pwd = '";

	static Statement st;
	static ResultSet rs;
	static PreparedStatement ps;

	java.sql.Connection con = ConnectionFactory.getConnection();
	{
		try {
			st = con.createStatement();
			ps = con.prepareStatement(selectDBUser);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public ShoppingControlUsersBean validUser(ShoppingControlUsersBean shoppingControlUserBean)
			throws SQLException {

		rs = ps.executeQuery(selectDBUser + shoppingControlUserBean.getUser() + "' and user_pwd = '"
				+ shoppingControlUserBean.getPassword() + "'");

		while (rs.next()) {
			shoppingControlUserBean = new ShoppingControlUsersBean();

			shoppingControlUserBean.setUser(rs.getString("users"));
			shoppingControlUserBean.setPassword(rs.getString("user_pwd"));
			shoppingControlUserBean.setStatus("OK");
		}

		st.close();

		return shoppingControlUserBean;

	}

	public String isPwdExist(ShoppingControlUsersBean shoppingControlUserBean) throws SQLException {

		boolean isPwdExist = false;

		rs = ps.executeQuery(selectDBPwdUser + shoppingControlUserBean.getPassword() + "'");

		while (rs.next()) {

			shoppingControlUserBean.setUser(rs.getString("users"));

			isPwdExist = true;

		}

		if ((shoppingControlUserBean.getNovaSenha()
				.equals(shoppingControlUserBean.getConfirmarNovaSenha()) && isPwdExist)) {

			PreparedStatement pStmt = con.prepareStatement(
					"update user_pwd_table set user_pwd = '" + shoppingControlUserBean.getConfirmarNovaSenha()
							+ "' where user_pwd = '" + shoppingControlUserBean.getPassword() + "'");

			shoppingControlUserBean.setSenhaAlterada("OK");
			
			pStmt.execute();
			pStmt.close();

			return shoppingControlUserBean.getSenhaAlterada();
		}

		st.close();

		return shoppingControlUserBean.getSenhaAlterada();
	}
	
	public String signInUser(ShoppingControlUsersBean controleDeGastosUsersBean) throws SQLException{
				
		String query = "insert into users_table values(?)";
		
		PreparedStatement pStmt = con.prepareStatement(query);
		
		if(controleDeGastosUsersBean.getPassword().equals(controleDeGastosUsersBean.getConfirmarNovaSenha())) {
		
		pStmt.setString(1, controleDeGastosUsersBean.getUser());
		
		pStmt.execute();
		pStmt.close();
		
			String queryUserPlusPwd = "insert into user_pwd_table values(?,?)";
			
			PreparedStatement pStmt2 = con.prepareStatement(queryUserPlusPwd);
			
			pStmt2.setString(1, controleDeGastosUsersBean.getUser());
			pStmt2.setString(2, controleDeGastosUsersBean.getPassword());
			
			controleDeGastosUsersBean.setStatus("OK");
		
			pStmt2.execute();
			pStmt2.close();
		}
		
		
		
		return controleDeGastosUsersBean.getStatus();
		
	}
}
