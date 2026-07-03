package model.core;

import java.util.List;

import model.Interfaces.CultivationModel;

public interface CultivationsModel {
	
	/**
	 * this method is used to add a cultivation
	 * @param cultivation
	 * @throws IllegalArgumentException if there is already a cultivation in the same field with state != DONE or the supply is already used
	 */
	public void addCultivation(CultivationModel cultivation);
	
	/**
	 * this method is used to get all the cultivations saved
	 * @return cultivations
	 */
	public List<CultivationModel> getCultivations();
	
	/**
	 * this method is used to get a specific cultivation throught field id and supply
	 * @param fieldID
	 * @param supply
	 * @return the cultivation
	 */	
	public CultivationModel getCultivation(String fieldID, String supply);
	
	/**
	 * this method is used to replace stored array with the given one
	 * @param cultivations
	 */
	public void updateCultivations(List<CultivationModel> cultivations);
	
}
