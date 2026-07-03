package controller;

import java.io.File;
import model.Fight;
import view.FightPanel;
import view.FightStartedPanel;
import view.FightStartedPanelImpl;
import view.StampaStorico;
import view.StampaStoricoImpl;

public class FightControllerImpl implements FightController {

	private Fight modelFight;
	private FightPanel fightPanel;

	public FightControllerImpl( Fight modelFight2, FightPanel fightPanel2){
		
		this.modelFight = modelFight2;
		this.fightPanel = fightPanel2;
		this.fightPanel.addObserver(this);
	}
	
	public void startFight(String cognome1, String cognome2){
		
		FightStartedPanel startedFight = new FightStartedPanelImpl(cognome1,cognome2);
		startedFight.addObserver(this);
		
	}
	
	public void stampaAtleti(){
		
		StampaStorico storico = new StampaStoricoImpl();
		modelFight.setListaMatch(modelFight.getListaMatchFile());
		storico.StampaStoricoMatch(modelFight.getListaMatch());
		storico.addObserverFight(this);
	}
	
	public String[] getScoreBlue(Integer punti){
		
		return modelFight.getScoreBlue(punti);
	}
	
	public String[] getScoreRed(Integer punti){
		
		return modelFight.getScoreRed(punti);
	}
	
	public String getWarningRed(Integer warnings){
		
		return modelFight.getWarningRed(warnings);
	}
	
	public String getWarningBlue(Integer warnings){
		
		return modelFight.getWarningBlue(warnings);
	}
	
	public void playSound(File file){
		
		modelFight.playSound(file);
	}
	
	public void insertListaMatchFile(){
		
		modelFight.insertListaMatchFile();
	}
	
	public void getListaMatchFile(){
		
		modelFight.getListaMatchFile();
	}
	
	public void insertListaMatch(String atleta1, String atleta2, String risultato){
	
		modelFight.insertListaMatch(atleta1, atleta2, risultato);
	}
}