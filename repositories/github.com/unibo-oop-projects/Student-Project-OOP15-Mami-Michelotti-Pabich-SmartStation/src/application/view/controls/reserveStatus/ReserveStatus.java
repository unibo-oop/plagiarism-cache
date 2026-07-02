package application.view.controls.reserveStatus;

/**
 * Interface that contain all methods needed to manage a ReserveStatus.
 * @author Marcin Pabich
 */
public interface ReserveStatus {
	
    /**
     * Get the remaining reserve quantity.
     * @return reserve quantity
     */
    String getRemain();

    /**
     * Set the remaining reserve quantity.
     * @param value the quantity to set
     */
    void setRemain(String value);
    
    /**
     * Get the remaining reserve quantity.
     * @return reserve quantity
     */
    String getMaxReserve();

    /**
     * Set the remaining reserve quantity.
     * @param value the quantity to set
     */
    void setMaxReserve(String value);
    
    /**
     * Get the progress of reserve.
     * @return double that represent the value
     */
    Double getProgress();
    
    /**
     * Set the progress of reserve.
     * @param value from 0.0 (empty) to 1.0 (full)
     */
    void setProgress(Double value);
    
    
    /**
     * Get the fuel name.
     * @return the fuel name
     */
    String getFuelName();
    
    /**
     * Set the fuel name.
     * @param name the nam of the fuel
     */
    void setFuelName(String name);
    
    /**
     * Set the price of fuel.
     * @param value price of fule
     */
    void setPrice(String value);
    
    /**
     * Get the price of fuel.
     * @return the price of fuel
     */
    String getPrice();
    
}
