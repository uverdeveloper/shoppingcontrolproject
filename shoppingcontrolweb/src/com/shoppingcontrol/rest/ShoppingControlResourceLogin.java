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

//http://localhost:8083/sistemacontroledegastosweb/rest/sistemacontrolegastoslogin/

@XmlRootElement
@Path("/sistemacontroledegastoslogin")

public class ShoppingControlResourceLogin {

	@GET
	@Path("{user}/{pwd}")	
	@Produces(MediaType.TEXT_PLAIN)
	public String logon(@PathParam("user") String user, @PathParam("pwd") String pwd) {
		
		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAOUsers dao = new ShoppingControlDAOUsers(conn);

		try {
			ShoppingControlUsersBean sistemaControleDeGastosUsersBean = new ShoppingControlUsersBean();
			sistemaControleDeGastosUsersBean.setUser(user);
			sistemaControleDeGastosUsersBean.setPassword(pwd);
			sistemaControleDeGastosUsersBean = dao.validUser(sistemaControleDeGastosUsersBean);
			
			return sistemaControleDeGastosUsersBean.getStatus();
		} catch (NullPointerException n) {
			n.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Sistema indisponível";
	}
	
	@GET
	@Path("{senhaAtual}/{novaSenha}/{confirmarNovaSenha}")	
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePwd(@PathParam("senhaAtual") String senhaAtual, @PathParam("novaSenha") String novaSenha,
			@PathParam("confirmarNovaSenha") String confirmarNovaSenha) {
		
		Connection conn = ConnectionFactory.getConnection();
		ShoppingControlDAOUsers dao = new ShoppingControlDAOUsers(conn);

		try {
			ShoppingControlUsersBean sistemaControleDeGastosUsersBean = new ShoppingControlUsersBean();
			sistemaControleDeGastosUsersBean.setPassword(senhaAtual);
			sistemaControleDeGastosUsersBean.setNovaSenha(novaSenha);
			sistemaControleDeGastosUsersBean.setConfirmarNovaSenha(confirmarNovaSenha);
			
			return dao.isPwdExist(sistemaControleDeGastosUsersBean);
		} catch (NullPointerException n) {
			n.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "NOK";
	}

}
