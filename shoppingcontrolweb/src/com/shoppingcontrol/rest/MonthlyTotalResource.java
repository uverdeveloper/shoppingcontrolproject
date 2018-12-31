package com.shoppingcontrol.rest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import com.connectionfactory.ConnectionFactory;
import com.shoppingcontrol.dao.ShoppingControlDAO;

@XmlRootElement
@Path("/monthlyTotal")

public class MonthlyTotalResource {

	@GET
	@Path("/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> totalValues (@PathParam("year") String year){
		
		List<String> monthlyTotalList = new ArrayList<String>();
		
		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);
				
		try {
			monthlyTotalList = dao.monthlyTotal(year);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monthlyTotalList;
	}
}
