package controller;

/**
 * 
 * Astratta degli slaveController. Ogni slave deve tenere traccia del suo master per comunicare con lui.
 *
 * @author Martino De Simoni
 */

public abstract class SlaveController implements ISlaveController{

	protected MasterPanelController master;
	
}
