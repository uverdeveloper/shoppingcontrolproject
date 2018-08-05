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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import com.connectionfactory.ConnectionFactory;
import com.shoppingcontrol.bean.ShoppingControl;
import com.shoppingcontrol.bean.ShoppingControl;
import com.shoppingcontrol.dao.ShoppingControlDAO;

//http://localhost:8083/sistemacontroledegastosweb/rest/sistemacontrolegastos/

@XmlRootElement
@Path("/sistemacontroledegastos")

public class ShoppingControlResource {

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

	@GET
	@Path("{ano}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ShoppingControl> getByYear(@PathParam("ano") String ano) {

		List<ShoppingControl> list = new ArrayList<ShoppingControl>();

		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);
		try {
			list = dao.selectByYear(ano);
		} catch (Exception e){
			e.printStackTrace();
		}

		return list;

	}

	@GET
	@Path("{mes}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ShoppingControl> getByMonth(@PathParam("mes") String mes) {

		List<ShoppingControl> list = new ArrayList<ShoppingControl>();

		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);

		try {
			list = dao.selectByMonth(mes);
		} catch (Exception e) {
		}

		return list;
	}
	
	@GET
	@Path("soma/{data}")
	@Produces(MediaType.TEXT_PLAIN)
	public String totalValues(@PathParam("mes") String data){
		
		String total = null;
		
		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);
				
		try {
			total = dao.sumValues(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	/*@GET
	@Path("soma/{mes}/{ano}/{valorDisponivel}")
	@Produces(MediaType.TEXT_PLAIN)
	public String valueRemaining(@PathParam("mes") String mes, @PathParam("ano") int ano, @PathParam("valorDisponivel") double valorDisponivel){
		
		String valueRemaining = null;
		
		Connection conn = ConnectionFactory.getConnection();
		SistemaControleDeGastosDAO dao = new SistemaControleDeGastosDAO(conn);
				
		try {
			valueRemaining = dao.valueDisponible(mes, ano, valorDisponivel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valueRemaining;
	}*/

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertAll(ShoppingControl ShoppingControl) {

		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);

		try {
			dao.insertData(ShoppingControl);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Dados inseridos com sucesso";
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public String delete(ShoppingControl ShoppingControl){
		
		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);
		
		try {
			dao.deleteBD(ShoppingControl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Dados deletados com sucesso.";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(ShoppingControl ShoppingControl){
		
		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAO dao = new ShoppingControlDAO(conn);
		
		try {
			dao.updateData(ShoppingControl);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
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
