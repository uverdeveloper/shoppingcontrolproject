package com.shoppingcontrol.rest;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import com.connectionfactory.ConnectionFactory;
import com.shoppingcontrol.bean.ShoppingControlUsersBean;
import com.shoppingcontrol.dao.ShoppingControlDAOUsers;


@XmlRootElement
@Path("/sistemacontroledegastoscadastro")
public class ShoppingControlResourceRegister {

	@GET
	@Path("{user}/{password}/{confirmPassword}")	
	@Produces(MediaType.TEXT_PLAIN)
	public String signIn(@PathParam("user") String user, @PathParam("password") String password,
			@PathParam("confirmPassword") String confirmPassword) {
		
		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAOUsers dao = new ShoppingControlDAOUsers(conn);
		
		try {
			ShoppingControlUsersBean sistemaControleDeGastosUsersBean = new ShoppingControlUsersBean();
			sistemaControleDeGastosUsersBean.setUser(user);
			sistemaControleDeGastosUsersBean.setPassword(password);
			sistemaControleDeGastosUsersBean.setConfirmarNovaSenha(confirmPassword);

			dao.signInUser(sistemaControleDeGastosUsersBean);
			
			return sistemaControleDeGastosUsersBean.getStatus();
			
		} catch (NullPointerException n) {
			n.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "NOK";
	}
	
}
