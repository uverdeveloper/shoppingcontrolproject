package com.shoppingcontrol.bean;

public class ShoppingControl {

	private int id;
	private String application;
	private double value;
	private String buy_date;
	private String due_date;
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
	 * @return the application
	 */

	public String getApplication() {
		return application;
	}

	/**
	 * @param application
	 *            the application to set
	 */
	public void setApplication(String aplicacao) {
		this.application = aplicacao;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	public String getBuy_date() {
		return buy_date;
	}

	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ShoppingControl [id=" + id + ", application=" + application + ", value=" + value + ", buy_date="
				+ buy_date + ", due_date=" + due_date + ", description=" + description + "]";
	}

}
