package model.Interfaces;

import java.util.Calendar;
import java.util.List;

import model.Implementations.State;

public interface CultivationModel {
	/**
	 * this method is used to set the day of plant
	 * 
	 * @param startDate
	 */

	public void setStartDate(Calendar startDate);

	/**
	 * this method is used to set the number of plants planted in this field
	 * 
	 * @param nOfPlants
	 */

	public void setNOfPlants(int nOfPlants);

	/**
	 * this method is used to set where do this plants come from
	 * 
	 * @param forniture
	 */

	public void setSupply(SupplyModel forniture);

	/**
	 * this method is used to set where this cultivation is planted
	 * 
	 * @param field
	 */

	public void setField(FieldModel field);

	/**
	 * this method is used to set if there are some trouble or something to
	 * know about this specific cultivation
	 * 
	 * @param Annotation
	 */

	public void setAnnotation(String Annotation);
	
	/**
	 * this method is used to know when this cultivation was planted
	 * @return SimpleDataFormat data
	 */

	public Calendar getStartDate();
	
	/**
	 * this method is used to know when this cultivation was fully grown and ready for harvest
	 * @return SimpleDataFormat data
	 */

	public Calendar getEndDate();
	
	/**
	 * this method is used to know the number of the plant
	 * @return int nOfPlants
	 */

	public int getNOfPlants();
	
	/**
	 * this method is used to get the supply of this specific cultivation
	 * @return SupplyModel supply
	 */

	public SupplyModel getSupply();
	
	/**
	 * this method is used to get the field
	 * @return FieldModel field
	 */

	public FieldModel getField();
	
	/**
	 * this method is used to get the annotation about this cultivation
	 * @return String annotation
	 */

	public String getAnnotation();
	
	/**
	 * return if the cultivation has ended his cycle
	 * @return 
	 */
	public boolean isReady();
	
	/**
	 * this method is used to get the number of days needed before  the cultivation is ready to harvest
	 * @return n of days
	 */
	public int getLeftDays();
	
	/**
	 * this method is used to get the state of this cultivation
	 * @return the current state
	 */
	
	public State getState();
	
	/**
	 * this method is used to set the state of this cultivation
	 * @param state
	 */
	
	public void setState(State state);
	
	/**
	 * this method is used to add a treatment to the list of this cultivation
	 * @param phytosanitary
	 * @param date
	 * @param description
	 * @throws Exception if the treatment date is < of this cultivation
	 */
	
	public void addTreatment(PhytosanitaryModel phytosanitary, Calendar date, String description) throws Exception;
	
	/**
	 * this method is used to get the treatments done on this cultivation
	 * @return the list of treatments
	 */
	
	public List<TreatmentModel> getTreatments();

}
