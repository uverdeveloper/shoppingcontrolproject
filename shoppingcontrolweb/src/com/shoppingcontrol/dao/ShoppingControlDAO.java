package com.shoppingcontrol.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.connectionfactory.ConnectionFactory;
import com.shoppingcontrol.bean.ShoppingControl;

public class ShoppingControlDAO extends ConnectionFactory {

	static ShoppingControl ShoppingControl = new ShoppingControl();

	private String selectData = "select id, application, value, buy_date, due_date, description from expense where buy_date like '%";
	
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
			shoppingControl.setDue_date(rs.getString("due_date"));
			shoppingControl.setDescription(rs.getString("description"));

			listShoppingControl.add(shoppingControl);
		}

		cStmt.close();

		return listShoppingControl;

	}

	public List<ShoppingControl> selectByYear(String year) throws SQLException {
		// APENAS PARA TESTE COM URL DO SERVICE
//		date = "13/08/2018";
		
		rs = st.executeQuery(selectData + year + "' order by id");

		List<ShoppingControl> listSelectByYear = new ArrayList<ShoppingControl>();

		while (rs.next()) {
			// NECESSARIO, SENAO O OBJETO TRAZ SEMPRE O ULTIMO OBJETO NA MEMORIA
			ShoppingControl shoppingControl = new ShoppingControl();

			shoppingControl.setId(rs.getInt("id"));
			shoppingControl.setApplication(rs.getString("application"));
			shoppingControl.setValue(rs.getDouble("value"));
			shoppingControl.setBuy_date(rs.getString("buy_date"));
			shoppingControl.setDue_date(rs.getString("due_date"));
			shoppingControl.setDescription(rs.getString("description"));
			
			listSelectByYear.add(shoppingControl);
		}

		st.close();

		return listSelectByYear;
	}

	public List<ShoppingControl> selectByMonth(String month, String year) throws SQLException {

		String date = month+"/"+year;
		
		rs = st.executeQuery(selectData + date + "' order by id");

		List<ShoppingControl> listSelectBDPorMes = new ArrayList<ShoppingControl>();

		while (rs.next()) {
			// NECESSARIO, SENAO O OBJETO TRAZ SEMPRE O ULTIMO OBJETO NA MEMORIA
			ShoppingControl shoppingControl = new ShoppingControl();
			
			shoppingControl.setId(rs.getInt("id"));
			shoppingControl.setApplication(rs.getString("application"));
			shoppingControl.setValue(rs.getDouble("value"));
			shoppingControl.setBuy_date(rs.getString("buy_date"));
			shoppingControl.setDue_date(rs.getString("due_date"));
			shoppingControl.setDescription(rs.getString("description"));
			
			listSelectBDPorMes.add(shoppingControl);
		}

		st.close();

		return listSelectBDPorMes;
	}

	public void insertData(ShoppingControl shoppingControl) throws Exception {

		String query = "insert into expense(id, application, value, buy_date, due_date, description)" + "values(?,?,?,?,?,?)";

		PreparedStatement pStmt = con.prepareStatement(query);
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateToday = new Date(0);
		String registerDate = sdf.format(dateToday);*/
		
		pStmt.setInt(1, shoppingControl.getId());
		pStmt.setString(2, shoppingControl.getApplication());
		pStmt.setDouble(3, shoppingControl.getValue());
		pStmt.setString(4,shoppingControl.getBuy_date());
		pStmt.setString(5, shoppingControl.getDue_date());
		pStmt.setString(6, shoppingControl.getDescription());

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
	
	public void updateData(ShoppingControl shoppingControl) throws Exception{
		
		String query = "update expense set application = ?, value = ?, buy_date = ?, due_date = ?, description = ? where id = ? ";
		
		PreparedStatement pStmt = con.prepareStatement(query);
		
		pStmt.setString(1, shoppingControl.getApplication().toUpperCase());
		pStmt.setDouble(2, shoppingControl.getValue());
		pStmt.setString(3, shoppingControl.getBuy_date());
		pStmt.setString(4, shoppingControl.getDue_date());
		pStmt.setString(5, shoppingControl.getDescription().toUpperCase());
		pStmt.setInt(6, shoppingControl.getId());
		
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
	
	public String sumValues(String month, String year) throws Exception {
		
		// concat mes e ano
		
		String date_buy = month+"/"+year;
		
		String total;
		/*String month = date.substring(3, date.length()-5);
		String year = date.substring(6);*/		
		
		rs = st.executeQuery("select sum(value) as total from expense where buy_date like '%" + date_buy + "'");
		
		this.sum = Double.valueOf(String.format(Locale.US, "%.2f", Math.ceil(this.sum)));
		
		while(rs.next()){
			
			this.sum += rs.getDouble(1); 
		}
		
		total = Double.toString(this.sum);
		
		st.close();
		
		return total;
	}
}
