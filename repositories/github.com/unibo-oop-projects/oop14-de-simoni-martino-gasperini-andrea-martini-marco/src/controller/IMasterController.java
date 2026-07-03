package controller;


/**
 * 
 * 
 *
 *
 *  Interfaccia utile a implementare il polimorfismo e simulare l'ereditarietà multipla.
 *  @author Martino De Simoni
 */
/*	
 *	Per il progetto Piante contro Zombie non sono previsti master che siano anche slave. 
 *
 *	Mi interrogo sulla riutilizzabilità di questa classe in un'effettiva software house.
 *
 */

public interface IMasterController extends IController {

	void notifyMaster(String msg, Object args);
	
}
