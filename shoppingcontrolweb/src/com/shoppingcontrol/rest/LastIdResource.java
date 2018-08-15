package com.shoppingcontrol.rest;

import java.sql.Connection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import com.connectionfactory.ConnectionFactory;
import com.shoppingcontrol.dao.ShoppingControlDAO;

@XmlRootElement
@Path("/organizingId")
public class LastIdResource {

	@GET
	@Path("/lastId")
	@Produces(MediaType.APPLICATION_JSON)
	public int returnLastId(){
		
		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);
		
		try {
			return dao.lastId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
