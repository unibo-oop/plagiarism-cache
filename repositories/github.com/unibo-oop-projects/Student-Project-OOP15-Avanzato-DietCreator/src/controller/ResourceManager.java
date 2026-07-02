package controller;

import java.io.Serializable;
import java.util.List;
import model.FoodValues;
import model.Profile;

/**
 * Questa interfaccia dichiara i metodi del resource manager
 * 
 */
public interface ResourceManager {
	
    /**
     * Questo metodo salva un oggetto dato su un file
     * 
     * @param data
     *      il parametro data indica l'oggetto da salvare
     *      
     * @param fileName
     *      fileName indica il nome del file sul quale salvare l'oggetto
     * 
     * @throws
     *      lancia eccezione in caso di errore nel salvataggio e localizzazione del file
     */
	void save(Serializable data, String fileName) throws Exception;
	
	/**
     * Questo metodo carica una lista di profili da un file
     *      
     * @param fileName
     *      fileName indica il nome del file dal quale caricare la lista
     * 
     * @throws
     *      lancia eccezione in caso di errore nel caricamento e localizzazione del file
     *      
     * @return
     *      restituisce una lista di profili
     */
	List<Profile> loadProfiles(String fileName) throws Exception;
	
	/**
     * Questo metodo carica una lista di cibi da un file
     *      
     * @param fileName
     *      fileName indica il nome del file dal quale caricare la lista
     * 
     * @throws
     *      lancia eccezione in caso di errore nel caricamento e localizzazione del file
     *      
     * @return
     *      restituisce una lista di cibi
     */
	List<FoodValues> loadFoodValues(String fileName) throws Exception;
}
