package com.shoppingcontrol.rest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import com.connectionfactory.ConnectionFactory;
import com.shoppingcontrol.bean.ShoppingControl;
import com.shoppingcontrol.dao.ShoppingControlDAO;

@XmlRootElement
@Path("/notes")

public class AllResource {

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ShoppingControl> getAll() {

		List<ShoppingControl> list = new ArrayList<ShoppingControl>();

		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);

		try {
			list = dao.selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	/*@Produces(MediaType.APPLICATION_JSON)*/
	public void insertAll(ShoppingControl shoppingControl) {

		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);

		try {
			dao.insertData(shoppingControl);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//return "Dados inseridos com sucesso";
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public String delete(ShoppingControl shoppingControl){
		
		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);
		
		try {
			dao.deleteBD(shoppingControl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Dados deletados com sucesso.";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(ShoppingControl shoppingControl){
		
		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);
		
		try {
			dao.updateData(shoppingControl);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	
}
