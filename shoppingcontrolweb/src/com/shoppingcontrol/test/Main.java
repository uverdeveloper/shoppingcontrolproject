package com.shoppingcontrol.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shoppingcontrol.bean.ShoppingControl;
import com.shoppingcontrol.dao.ShoppingControlDAO;

public class Main {

	public static void main(String[] args){

		Connection conn = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String buyDate = sdf.format(date);
		
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);
		
		
		// INSERT
		/*ShoppingControl shoppingControl = new ShoppingControl();
		shoppingControl.setId(1);
		shoppingControl.setApplication("compra0");
		shoppingControl.setValue(3.00);
		shoppingControl.setBuy_date(buyDate);
		shoppingControl.setDescription("n/a");
		
		try {
			dao.insertData(shoppingControl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		// SELECT
		/*try {
			List<ShoppingControl> list = new ArrayList<ShoppingControl>();
			list = dao.selectAll();
			
			for(ShoppingControl data : list) {
				System.out.println(data.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		// SELECT BY YEAR
		/*try {
			List<ShoppingControl> list = new ArrayList<ShoppingControl>();
			list = dao.selectByYear(buyDate);
			
			for(ShoppingControl data : list) {
				System.out.println(data.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		// SELECT BY MONTH
				/*try {
					List<ShoppingControl> list = new ArrayList<ShoppingControl>();
					list = dao.selectByMonth(buyDate);
					
					for(ShoppingControl data : list) {
						System.out.println(data.toString());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
		
		// UPDATE BY ID
		/*try {
			dao.updateData(shoppingControl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		// GET ID
		/*try {
			System.out.println(dao.lastId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		// GET SHOPPING OF THE SUM
		/*try {
			System.out.println("SUM = " + dao.sumValues(buyDate));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
