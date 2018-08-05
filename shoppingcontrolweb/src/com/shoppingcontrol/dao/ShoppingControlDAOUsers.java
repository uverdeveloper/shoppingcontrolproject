package com.shoppingcontrol.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.connectionfactory.ConnectionFactory;
import com.shoppingcontrol.bean.ShoppingControlUsersBean;

public class ShoppingControlDAOUsers extends ConnectionFactory {

	static ShoppingControlUsersBean sistemaControleDeGastosUsersBean = new ShoppingControlUsersBean();

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

	public ShoppingControlUsersBean validUser(ShoppingControlUsersBean sistemaControleDeGastosUsersBean)
			throws SQLException {

		rs = ps.executeQuery(selectDBUser + sistemaControleDeGastosUsersBean.getUser() + "' and user_pwd = '"
				+ sistemaControleDeGastosUsersBean.getPassword() + "'");

		while (rs.next()) {
			sistemaControleDeGastosUsersBean = new ShoppingControlUsersBean();

			sistemaControleDeGastosUsersBean.setUser(rs.getString("users"));
			sistemaControleDeGastosUsersBean.setPassword(rs.getString("user_pwd"));
			sistemaControleDeGastosUsersBean.setStatus("OK");
		}

		st.close();

		return sistemaControleDeGastosUsersBean;

	}

	public String isPwdExist(ShoppingControlUsersBean sistemaControleDeGastosUsersBean) throws SQLException {

		boolean isPwdExist = false;

		rs = ps.executeQuery(selectDBPwdUser + sistemaControleDeGastosUsersBean.getPassword() + "'");

		while (rs.next()) {

			sistemaControleDeGastosUsersBean.setUser(rs.getString("users"));

			isPwdExist = true;

		}

		if ((sistemaControleDeGastosUsersBean.getNovaSenha()
				.equals(sistemaControleDeGastosUsersBean.getConfirmarNovaSenha()) && isPwdExist)) {

			PreparedStatement pStmt = con.prepareStatement(
					"update user_pwd_table set user_pwd = '" + sistemaControleDeGastosUsersBean.getConfirmarNovaSenha()
							+ "' where user_pwd = '" + sistemaControleDeGastosUsersBean.getPassword() + "'");

			sistemaControleDeGastosUsersBean.setSenhaAlterada("OK");
			
			pStmt.execute();
			pStmt.close();

			return sistemaControleDeGastosUsersBean.getSenhaAlterada();
		}

		st.close();

		return sistemaControleDeGastosUsersBean.getSenhaAlterada();
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
