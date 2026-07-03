package model.Implementations;

import java.io.Serializable;

import model.Interfaces.PlantModel;
import model.Interfaces.SupplierModel;
import model.Interfaces.SupplyModel;

public class SupplyImplementation implements SupplyModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2401542728366605893L;
	private SupplierModel supplier;
	private PlantModel plant;
	private String fornitureID;
	private String description;
	private int nPlants;

	public SupplyImplementation(SupplierModel supplier, PlantModel plant, String fornitureID, String description,
			int nPlants) {
		this.supplier = supplier;
		this.plant = plant;
		this.fornitureID = fornitureID;
		this.description = description;
		this.nPlants = nPlants;
	}

	@Override
	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	@Override
	public void setPlant(PlantModel plant) {
		this.plant = plant;
	}

	@Override
	public void setSupplyID(String forniture) {
		this.fornitureID = forniture;
	}

	@Override
	public void setSupplyDescription(String description) {
		this.description = description;
	}

	@Override
	public void setNOfPlants(int nPlants) {
		this.nPlants = nPlants;
	}

	@Override
	public SupplierModel getSupplier() {
		return this.supplier;
	}

	@Override
	public PlantModel getPlant() {
		return this.plant;
	}

	@Override
	public String getSupply() {
		return this.fornitureID;
	}

	@Override
	public String getSupplyDescription() {
		return this.description;
	}

	@Override
	public int getNOfPlants() {
		return this.nPlants;
	}

	@Override
	public String toString() {
		return "SupplyImplementation [supplier=" + supplier.getName() + ", plant=" + plant.getName() + ", fornitureID=" + fornitureID
				+ ", description=" + description + ", nPlants=" + nPlants + "]";
	}

}
