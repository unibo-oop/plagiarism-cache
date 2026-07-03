package controller;

import java.util.HashMap;

import fileManager.LevelReader;
import fileManager.Livello;
import gui.CampaignPanel;
import gui.PlantButton;

/**
 * 
 * Controller del pannello di gioco
 * 
 * @author Martino De Simoni
 *
 */

public class GameController extends PanelController<CampaignPanel>{

	private final HashMap<String,Livello> livelli; 
	private final Integer COLS;
	private final Integer ROWS;
	private final String shovel= "shovelMessage";
	private final String slaveTermination;
	
	public GameController(final String filePathDatiLivelli, final Integer _COLS, final Integer _ROWS, 
			final PlantButton[] _plantButtons,final MasterPanelController _master, final String _termination){
		
		livelli = new LevelReader(filePathDatiLivelli).leggiDatiLivelli(); 
		COLS = _COLS;
		ROWS = _ROWS;
		
		controlledPanel = new CampaignPanel(_plantButtons, COLS,ROWS, this, shovel);
		
		master = _master;
		
		slaveTermination = _termination;
	}
	
	@Override
	public void slaveHasTerminated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyController(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
}
