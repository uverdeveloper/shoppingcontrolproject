package com.shoppingcontrol.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.connectionfactory.ConnectionFactory;
import com.shoppingcontrol.bean.ShoppingControl;

public class ShoppingControlDAO extends ConnectionFactory {

	static ShoppingControl ShoppingControl = new ShoppingControl();

	private String selectData = "select id, application, value, buy_date, description from expense where buy_date like '%";
	
	private double sum = 0.0;
	
	static Statement st;
	static CallableStatement cStmt;
	static ResultSet rs;

	public ShoppingControlDAO(Connection conn) {
		super(conn);
	}

	Connection con = ConnectionFactory.getConnection();
	{
		try {
			st = con.createStatement();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public List<ShoppingControl> selectAll() throws SQLException {

		cStmt = con.prepareCall("{call getAllApplications()}");
		rs = cStmt.executeQuery();		
		
		List<ShoppingControl> listShoppingControl = new ArrayList<ShoppingControl>();
		
		while (rs.next()) {
			// NECESSARIO, SENAO O OBJETO TRAZ SEMPRE O ULTIMO OBJETO NA MEMORIA
			ShoppingControl shoppingControl = new ShoppingControl();
			
			shoppingControl.setId(rs.getInt("id"));
			shoppingControl.setApplication(rs.getString("application"));
			shoppingControl.setValue(rs.getDouble("value"));
			shoppingControl.setBuy_date(rs.getString("buy_date"));
			shoppingControl.setDescription(rs.getString("description"));

			listShoppingControl.add(shoppingControl);
		}

		cStmt.close();

		return listShoppingControl;

	}

	public List<ShoppingControl> selectByYear(String date) throws SQLException {
		
		String year = date.substring(6);
		
		rs = st.executeQuery(selectData + year + "%' order by buy_date");

		List<ShoppingControl> listSelectByYear = new ArrayList<ShoppingControl>();

		while (rs.next()) {
			// NECESSARIO, SENAO O OBJETO TRAZ SEMPRE O ULTIMO OBJETO NA MEMORIA
			ShoppingControl shoppingControl = new ShoppingControl();

			shoppingControl.setId(rs.getInt("id"));
			shoppingControl.setApplication(rs.getString("application"));
			shoppingControl.setValue(rs.getDouble("value"));
			shoppingControl.setBuy_date(rs.getString("buy_date"));
			shoppingControl.setDescription(rs.getString("description"));
			
			listSelectByYear.add(shoppingControl);
		}

		st.close();

		return listSelectByYear;
	}

	public List<ShoppingControl> selectByMonth(String date) throws SQLException {

		String month = date.substring(3, date.length()-5);
		
		rs = st.executeQuery(selectData + month + "%' order by buy_date");

		List<ShoppingControl> listSelectBDPorMes = new ArrayList<ShoppingControl>();

		while (rs.next()) {
			// NECESSARIO, SENAO O OBJETO TRAZ SEMPRE O ULTIMO OBJETO NA MEMORIA
			ShoppingControl shoppingControl = new ShoppingControl();
			
			shoppingControl.setId(rs.getInt("id"));
			shoppingControl.setApplication(rs.getString("application"));
			shoppingControl.setValue(rs.getDouble("value"));
			shoppingControl.setBuy_date(rs.getString("buy_date"));
			shoppingControl.setDescription(rs.getString("description"));
			
			listSelectBDPorMes.add(shoppingControl);
		}

		st.close();

		return listSelectBDPorMes;
	}

	public void insertData(ShoppingControl ShoppingControl) throws Exception {

		String query = "insert into expense(id, application, value, buy_date, description)" + "values(?,?,?,?,?)";

		PreparedStatement pStmt = con.prepareStatement(query);
		
		pStmt.setInt(1, ShoppingControl.getId());
		pStmt.setString(2, ShoppingControl.getApplication());
		pStmt.setDouble(3, ShoppingControl.getValue());
		pStmt.setString(4, ShoppingControl.getBuy_date());
		pStmt.setString(5, ShoppingControl.getDescription());

		pStmt.execute();
		pStmt.close();

	}
	
	public void deleteBD(ShoppingControl shoppingControl) throws Exception{
		
		String query = "delete from expense where id = ?";
		
		PreparedStatement pStmt = con.prepareStatement(query);
		
		pStmt.setInt(1, shoppingControl.getId());		
		
		pStmt.execute();
		pStmt.close();
	}
	
	/*public String valueDisponible(String mes, int ano, double value) throws Exception{
		
		String valueDisponible;
		
		String query = "select sum(valor) as soma from expense_control "
		+ "where mes = '" + mes + "' and ano = " + ano;
		
		rs = st.executeQuery(query);
		
		this.sum = Double.valueOf(String.format(Locale.US, "%.2f", Math.ceil(this.sum)));
		value = Double.valueOf(String.format(Locale.US, "%.2f", Math.ceil(value)));
		
		while(rs.next()){
			
			this.sum += rs.getDouble(1); 
		}
		
		valueDisponible = Double.toString(value-sum);
	
		st.close();
		
		
		return valueDisponible;
	}*/
	
	public void updateData(ShoppingControl shoppingControl) throws Exception{
		
		String query = "update expense set application = ?, value = ?, buy_date = ?, description = ? where id = ? ";
		
		PreparedStatement pStmt = con.prepareStatement(query);
		
		pStmt.setString(1, shoppingControl.getApplication().toUpperCase());
		pStmt.setDouble(2, shoppingControl.getValue());
		pStmt.setString(3, shoppingControl.getBuy_date());
		pStmt.setString(4, shoppingControl.getDescription().toUpperCase());
		pStmt.setInt(5, shoppingControl.getId());
		
		pStmt.executeUpdate();
		pStmt.close();
	}

	public int lastId() throws Exception {
		
		rs = st.executeQuery("select id from expense order by id desc limit 1");
		
		ShoppingControl shoppingControl = new ShoppingControl();
		
		while(rs.next()){
			
			shoppingControl.setId(rs.getInt("ID"));
		}
		
		st.close();		
		
		return shoppingControl.getId()+1;
	}
	
	public String sumValues(String date) throws Exception {
		
		String total;
		String month = date.substring(3, date.length()-5);
		String year = date.substring(6);		
		
		rs = st.executeQuery("select sum(value) as total from expense where buy_date like '%" + month
				+ "%' and buy_date like '%" + year + "%'");
		
		this.sum = Double.valueOf(String.format(Locale.US, "%.2f", Math.ceil(this.sum)));
		
		while(rs.next()){
			
			this.sum += rs.getDouble(1); 
		}
		
		total = Double.toString(this.sum);
		
		st.close();
		
		return total;
	}
}
