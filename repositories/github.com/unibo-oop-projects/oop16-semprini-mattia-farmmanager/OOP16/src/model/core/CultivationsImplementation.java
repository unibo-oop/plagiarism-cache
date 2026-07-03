package model.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import model.Implementations.State;
import model.Interfaces.CultivationModel;

public class CultivationsImplementation implements CultivationsModel, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -176307809762151579L;
	private List<CultivationModel> cultivations = new ArrayList<>();

	@Override
	public void addCultivation(final CultivationModel cultivation) {
		for(CultivationModel c : this.cultivations){
			if ((c.getField().getFieldID().equals(cultivation.getField().getFieldID()) && c.getState()!=State.DONE)||c.getSupply().getSupply().equals(cultivation.getSupply().getSupply())){
				throw new IllegalArgumentException("Error - you can't add this cultivation because there is already another cultivation in the same field that is not done!");
			}
		}
		this.cultivations.add(cultivation);
	}

	@Override
	public List<CultivationModel> getCultivations() {
		return this.cultivations;
	}
	
	@Override
	public CultivationModel getCultivation(String fieldID, String supply) {
		for(CultivationModel c : this.cultivations){
			if(c.getField().getFieldID().equals(fieldID)&& c.getSupply().getSupply().equals(supply)){
				return c;
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public void updateCultivations(List<CultivationModel> cultivations) {
		this.cultivations = cultivations;
	}


}
