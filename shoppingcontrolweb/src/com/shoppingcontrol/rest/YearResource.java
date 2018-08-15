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
import com.shoppingcontrol.bean.ShoppingControl;
import com.shoppingcontrol.dao.ShoppingControlDAO;

@XmlRootElement
@Path("/search1")

public class YearResource {

	@GET
	@Path("/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ShoppingControl> getByYear(@PathParam("year") String year) {

		List<ShoppingControl> list = new ArrayList<ShoppingControl>();

		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);
		try {
			list = dao.selectByYear(year);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

}
