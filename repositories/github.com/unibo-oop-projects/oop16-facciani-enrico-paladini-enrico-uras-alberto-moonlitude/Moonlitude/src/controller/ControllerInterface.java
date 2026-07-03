package controller;

import java.util.Map;
import model.Oggetti.Oggetto;
import view.ViewImpl;

public interface ControllerInterface {

  
    
    
    /**
     * Method to get a constant
     */
    public int getCostante();

    
    
    
    /**
     * Method to set the charge of generator's room
     * 
     * @param carica
     * 				the new charge
     */
    public void setCarica(int carica);
    
    
    
    
    /**
     * Method to get the charge
     * 
     * @return
     * 			the charge
     */
    public int getCarica();
    
    
    
    
    /**
     * 	Method to set the map of esploration's items
     * 
     * @param map
     * 			map
     */
    public void setMap(Map<Oggetto,Integer> map);
    
    
    
    
    /**
     * Method to get the map of esploration's items
     * 
     * @return
     * 			map
     */
    public Map<Oggetto,Integer> getMap();
    
    
    
    
    /**
     * Method to modify the events in the game
     * 
     * @param numero
     * 				amount of hour for the time passage
     */
     public void getPassaOre(int numero);
     
     
     
     
     /**
      * Method to notify if the player win the game
      */
    public void winGame();
     
     
    
    
     /**
      * Method to get the atmospheric condition
      * 
      * @return
      * 		string
      */
     public String getCondizioneAtmosferica();
     
     
     
     
    /**
     * Method to set the game
     */
    public void setGioco();
    
    
    
    
	/**
	 * Method to set all the views
	 * 
	 * @param view
	 * 				ViewImpl object to set views
	 */
	public void setView(ViewImpl view);
    
    
	
	
	/**
	 * Method to get ControllerAstronave's instance
	 * 
	 * @return
	 * 			instance 
	 */
	public ControllerAstronave getControllerAstronave();
	
	
	
	
	/**
	 * Method to get ControllerAstronauta's instance
	 * 
	 * @return
	 * 			instance
	 */
	public ControllerAstronauta getControllerAstronauta();
	
	
	
	
	/**
	 * Method to get ControllerFiltratore's instance
	 * 
	 * @return
	 * 			instance
	 */
	public ControllerFiltratore getControllerFiltratore();
	
	
	
	
	/**
	 * Method to get ControllerCucina's instance
	 * 
	 * @return
	 * 			instance
	 */
	public ControllerCucina getControllerCucina();
	
	
	
	
	/**
	 * Method to get ControllerGeneratore's instance
	 * 
	 * @return
	 * 			instance
	 */
	public ControllerEsplorazione getControllerEsplorazione();
	
	
	
	
	/**
	 * Method to get ControllerGeneratore's instance
	 * 
	 * @return
	 * 			instance
	 */
	public ControllerGeneratore getControllerGeneratore();
	
	
	
	
	/**
	 * Method to get ControllerGiardinoPianta's instance
	 * 
	 * @return
	 * 			instance
	 */
	public ControllerGiardinoPianta getControllerGiardinoPianta();
	
	
	
	
	/**
	 * Method to get ControllerLaboratorio's instance
	 * 
	 * @return
	 * 			instance
	 */
	public ControllerLaboratorio getControllerLaboratorio();
	
	
	
	
	/**
	 * Method to get ControllerMagazzino's instance
	 * 
	 * @return
	 * 			instance
	 */
	public ControllerMagazzino getControllerMagazzino();
	
	
	
	
	/**
	 * Method to get ControlleUtility's instance
	 * 
	 * @return
	 * 			instance
	 */
	public ControllerUtility getControllerUtility();
	
	
	
	
	/**
	 * Method to get ControllerRadar's
	 * 
	 * @return
	 * 			instance
	 */
	public ControllerRadar getControllerRadar();
	
	
	
	
	/**
	 * Method to get ControllerRefrigeratore's instance
	 * 
	 * @return
	 * 			instance
	 */
	public ControllerRefrigeratore getControllerRefrigeratore();
	
	
	
	
	/**
	 * Method to get ControllerZaino's instance
	 * 
	 * @return
	 * 			instance
	 */
	public ControllerZaino getControllerZaino();
}
