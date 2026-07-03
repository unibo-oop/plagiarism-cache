package controller;

import gui.MainFrame;

import java.util.HashMap;

/**
 * 
 * Si intende il Master dei PanelController, che intuitivamente gestisce una risorsa di tipo frame.
 * 
 * @author Martino De Simoni
 */


public abstract class MasterPanelController implements IMasterController{

	@SuppressWarnings("rawtypes")
	protected HashMap<String,PanelController> slaves = new HashMap<>();
	protected MainFrame frame;
	
}
