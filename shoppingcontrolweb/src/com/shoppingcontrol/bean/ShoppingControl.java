package com.shoppingcontrol.bean;

public class ShoppingControl {

	private int id;
	private String application;
	private double value;
	private String buy_date;
	private String description = "N/A";

	/**
	 * @return id
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the aplicacao
	 */

	public String getApplication() {
		return application;
	}

	/**
	 * @param aplicacao the aplicacao to set
	 */
	public void setApplication(String aplicacao) {
		this.application = aplicacao;
	}

	/**
	 * @return the valor
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValue(double valor) {
		this.value = valor;
	}

	public String getBuy_date() {
		return buy_date;
	}

	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}

	/**
	 * @return the observacao
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param observacao the observacao to set
	 */
	public void setDescription(String observacao) {
		this.description = observacao;
	}

	@Override
	public String toString() {
		return "ShoppingControl [id=" + id + ", application=" + application + ", value=" + value + ", buy_date="
				+ buy_date + ", description=" + description + "]";
	}

}
