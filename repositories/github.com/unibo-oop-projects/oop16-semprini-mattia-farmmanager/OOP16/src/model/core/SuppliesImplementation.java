package model.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import model.Interfaces.SupplyModel;

public class SuppliesImplementation implements SuppliesModel, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6400682167742625819L;
	private List<SupplyModel> supplies = new ArrayList<>();

	@Override
	public void addSupply(SupplyModel supply) {
		for(SupplyModel s : this.supplies){
			if(s.getSupply().equals(supply.getSupply())){
				throw new IllegalArgumentException("this supply has been already added");
			}
		}
		this.supplies.add(supply);
	}

	@Override
	public List<SupplyModel> getSupplies() {
		return this.supplies;
	}

	@Override
	public SupplyModel getSupply(String supply) {
		for(SupplyModel s : this.supplies){
			if (s.getSupply().equals(supply)){
				return s;
			}
		}
		throw new NoSuchElementException();
	}

}
