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
@Path("/search2")

public class MonthResource {

	@GET
	@Path("/{month}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ShoppingControl> getByMonth(@PathParam("month") String month, @PathParam("year") String year) {

		List<ShoppingControl> list = new ArrayList<ShoppingControl>();

		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);

		try {
			list = dao.selectByMonth(month, year);
		} catch (Exception e) {
		}

		return list;
	}

}
