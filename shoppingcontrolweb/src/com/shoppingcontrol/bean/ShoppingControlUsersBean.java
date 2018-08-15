package com.shoppingcontrol.bean;

public class ShoppingControlUsersBean {

	private String user;
	private String password;
	private String status = "NOK";
	private String novaSenha;
	private String confirmarNovaSenha;
	private String senhaAlterada = "NOK";

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmarNovaSenha() {
		return confirmarNovaSenha;
	}

	public void setConfirmarNovaSenha(String confirmarNovaSenha) {
		this.confirmarNovaSenha = confirmarNovaSenha;
	}

	public String getSenhaAlterada() {
		return senhaAlterada;
	}

	public void setSenhaAlterada(String senhaAlterada) {
		this.senhaAlterada = senhaAlterada;
	}

	@Override
	public String toString() {
		return "ShoppingControlUsersBean [user=" + user + ", password=" + password + ", status=" + status
				+ ", novaSenha=" + novaSenha + ", confirmarNovaSenha=" + confirmarNovaSenha + ", senhaAlterada="
				+ senhaAlterada + "]";
	}

}
