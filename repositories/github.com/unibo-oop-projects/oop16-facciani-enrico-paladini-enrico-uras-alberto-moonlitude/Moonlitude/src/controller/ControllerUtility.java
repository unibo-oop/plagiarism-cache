package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import controller.Salvataggio.Salvataggio;
import view.ViewImpl;

public class ControllerUtility {
	
	
	private ViewImpl view;
	private Salvataggio salvataggio;
	
	
	
	
	public ControllerUtility(ViewImpl view,Salvataggio salvataggio){
		this.view = view;
		this.salvataggio = salvataggio;
	}
	
	
	
	
	/**
	 * Method that notify that the button "Salva" is pressed
	 * 
	 * @throws FileNotFoundException
	 * 								exception
	 * 
	 * @throws IOException
	 * 						exception
	 */
	public void salvaGioco() throws FileNotFoundException, IOException{
		this.salvataggio.salvaInteroGioco();	
	}

	
	
	
	/**
	 * Method that notify tha the button "Carica" is pressed
	 * 
	 * @throws FileNotFoundException
	 * 								exception
	 * 
	 * @throws ClassNotFoundException
	 * 								exception
	 * 
	 * @throws IOException
	 * 						exception
	 */
	public void caricaGioco() throws FileNotFoundException, ClassNotFoundException, IOException{
		Controller.getLog().getControllerAstronauta().setParametri();
		this.salvataggio.caricamentoGioco();
		startViewBaseSpaziale();
	}

	
	
	
	/**
	 * Method that notify that the button "Nuovo Gioco" is pressed
	 */
	public void startViewBaseSpaziale(){
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.SetPrincipale();
	}
}
