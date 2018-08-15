package com.shoppingcontrol.rest;

import java.sql.Connection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import com.connectionfactory.ConnectionFactory;
import com.shoppingcontrol.dao.ShoppingControlDAO;

@XmlRootElement
@Path("/sumValues")

public class SumResource {

	@GET
	@Path("/{month}/{year}")
	@Produces(MediaType.TEXT_PLAIN)
	public String totalValues(@PathParam("month") String month, @PathParam("year") String year){
		
		String total = null;
		
		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);
				
		try {
			total = dao.sumValues(month, year);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
}
