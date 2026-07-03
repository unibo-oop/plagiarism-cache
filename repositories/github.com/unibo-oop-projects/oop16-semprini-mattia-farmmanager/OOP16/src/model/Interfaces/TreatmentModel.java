package model.Interfaces;

import java.util.Calendar;

public interface TreatmentModel {
		
	/**
	 * this method is used to set the Phytosanitary
	 * @param phytosanitary
	 */
	public void setPhytosanitary(PhytosanitaryModel phytosanitary);
	
	/**
	 * this method is used to set the date of this treatment
	 * @param date
	 */
	
	public void setDate(Calendar date);
	
	/**
	 * this method is used to set the description of this treatment
	 * @param description
	 */
	
	public void setDescription(String description);
	
	/**
	 * this method return the phytosanitary used
	 * @return the phytosanitary
	 */
	
	public PhytosanitaryModel getPhytosanitary();
	
	/**
	 * this method is used to get the description
	 * @return description
	 */
	
	public String getDescription();
	
	/**
	 * this method return a Calendar which contains the date of this cultivation
	 * @return the date
	 */
	
	public Calendar getDate();
	
	/**
	 * this method return if the cycle of this treatments is ended (getLeftDays<=0)
	 * @return
	 */
	
	public boolean isReady();
	
	/**
	 * this method is used to get left days of this treatments
	 * @return the number of days
	 */
	
	public int getLeftDays();
	
}
