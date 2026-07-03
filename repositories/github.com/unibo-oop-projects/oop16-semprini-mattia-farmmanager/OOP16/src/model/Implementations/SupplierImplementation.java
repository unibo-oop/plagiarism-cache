package model.Implementations;

import java.io.Serializable;

import model.Interfaces.SupplierModel;

public class SupplierImplementation implements SupplierModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4353979561388893923L;
	private String name;
	private String address;
	private String telephone;
	
	public SupplierImplementation(String name, String address, String telephone) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SupplierImplementation other = (SupplierImplementation) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		return true;
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
