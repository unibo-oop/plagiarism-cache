package model.Implementations;

import java.io.Serializable;

import model.Interfaces.EmployeeModel;

public class EmployeeImplementation implements EmployeeModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2813157124746223024L;
	private String name;
	private String surname;
	private String telephone;
	private String CF;

	public EmployeeImplementation(String name, String surname, String telephone, String cf) {
		this.name = name;
		this.surname = surname;
		this.telephone = telephone;
		this.CF = cf;
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}

	@Override
	public void setSurname(String surname) {
		this.surname=surname;
	}

	@Override
	public void setTelephone(String telephone) {
		this.telephone=telephone;
	}

	@Override
	public void setCF(String CF) {
		this.CF=CF;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getSurname() {
		return this.surname;
	}

	@Override
	public String getCF() {
		return this.CF;
	}

	@Override
	public String getTelephone() {
		return this.telephone;
	}

}
