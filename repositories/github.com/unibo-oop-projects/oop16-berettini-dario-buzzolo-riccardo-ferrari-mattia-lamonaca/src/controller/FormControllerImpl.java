package controller;

import java.util.ArrayList;
import java.util.List;
import model.Athlete;
import model.Belt;
import model.Form;
import view.FormPanel;
import view.FormStartedPanel;
import view.FormStartedPanelImpl;
import view.StampaStorico;
import view.StampaStoricoImpl;

public class FormControllerImpl implements FormController {
	
	private Form modelForm;	
	private FormPanel formPanel;

	public FormControllerImpl( Form modelForm2, FormPanel formPanel2){
		
		this.modelForm = modelForm2;
		this.formPanel = formPanel2;
		this.formPanel.addObserver(this);
	}
	
	public void startForm(String nome, String cognome, Belt cintura, String forma){
			
		FormStartedPanel formStarted = new FormStartedPanelImpl(nome, cognome, cintura, forma);
		formStarted.addObserverForm(this);
	}
	
	public void stampaAtleti(){
			
		StampaStorico storico = new StampaStoricoImpl();
		storico.StampaStoricoForma(getListaAtletiFile());
		storico.addObserverForm(this);
	}
		
	public String[] getScoreRed(int punti){
		
		return modelForm.getScoreRed(punti);	
	}
		
	public List<Athlete> getListaAtletiForma(){
			
		return modelForm.getListaAtletiForma();
	}
	
	public void addAtleta(Athlete atleta){
			
		modelForm.addAtletiForma(atleta);
	}
	
	public void addListaAtletiFile(){
			
		modelForm.insertListaAtletiFormaFile();
	}
	
	public ArrayList<Athlete> getListaAtletiFile(){
		
		return modelForm.getListaAtletiFormaFile();
	}		
}
