package model.Implementations;

import java.io.Serializable;

import model.Interfaces.CustomerModel;

public class CustomerImplementation implements CustomerModel, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5269061246866360591L;
	private String name;
	private String address;
	private String telephone;
	
	public CustomerImplementation(String name, String address, String telephone) {
		this.name=name;
		this.address=address;
		this.telephone=telephone;
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}

	@Override
	public void setAddress(String address) {
		this.address=address;

	}

	@Override
	public void setTelephone(String telephone) {
		this.telephone=telephone;

	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getAddress() {
		return this.address;
	}

	@Override
	public String getTelephone() {
		return this.telephone;
	}

}
