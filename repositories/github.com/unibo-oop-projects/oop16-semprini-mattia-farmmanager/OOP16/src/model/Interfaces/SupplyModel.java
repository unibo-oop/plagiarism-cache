package model.Interfaces;

public interface SupplyModel {
	
	/**
	 * this method is used to set the Supplier
	 * @param supplier
	 */
	public void setSupplier(SupplierModel supplier);
	
	/**
	 * this method is used to set the plant
	 * @param plant
	 */
	public void setPlant(PlantModel plant);
	/**
	 * this method is used to set an "id" about the supply
	 * @param forniture
	 */
	
	public void setSupplyID(String forniture);
	/**
	 * this method is used to set description about the supply
	 * @param description
	 */
	
	public void setSupplyDescription(String description);
	/**
	 * this method is used to set the number of the plant	
	 * @param nPlants
	 */
	public void setNOfPlants(int nPlants);	
	/**
	 * this method is used to get the supplier of this supply
	 * @return
	 */
	
	public SupplierModel getSupplier();
	
	/**
	 * this mehtod is used to get the type of the plant in this supply
	 * @return Plant plant
	 */
	
	public PlantModel getPlant();
	
	/**
	 * this method is used to get the name of this supply
	 * @return supply
	 */
	
	public String getSupply();
	/**
	 * this method is used to get any description/trouble about this supply
	 * @return description
	 */
	
	public String getSupplyDescription();
	
	/**
	 * this mehtod is used to get the number of the plant in this supply
	 * @return nofplants
	 */
	
	
	public int getNOfPlants();
}
