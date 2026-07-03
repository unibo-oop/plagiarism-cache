package model.core;

import java.util.List;

import model.Interfaces.SupplyModel;

public interface SuppliesModel {
	
	/**
	 * this method is used to store a supply
	 * @param supply
	 * @throws IllegalArgumentException if supply id is already present
	 */
	public void addSupply(SupplyModel supply);
	/**
	 * this method is used to get a specific supply
	 * @param supply
	 * @return supply
	 */
	
	public SupplyModel getSupply(String supply);
	
	/**
	 * this method is used to get stored supplies
	 * @return all stored supplies
	 */
	
	public List<SupplyModel> getSupplies();

}
