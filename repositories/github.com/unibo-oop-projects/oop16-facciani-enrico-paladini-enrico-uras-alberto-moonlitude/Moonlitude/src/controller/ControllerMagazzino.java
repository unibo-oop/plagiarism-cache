package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.BaseSpaziale;
import model.Oggetti.Materiale;
import model.Oggetti.Oggetto;
import model.Stanza.Magazzino;
import view.ViewImpl;

public class ControllerMagazzino {
	
	
	private ViewImpl view;
	
	
	
	
	public ControllerMagazzino(ViewImpl view) {
		this.view = view;
	}
	
	
	
	
	/**
	 * Method to convert map.key in array of string
	 * 
	 * @return
	 * 		array of string
	 */
	private String[] conversioneOggetti(){
		
		List<Oggetto> lista = new LinkedList<>(Magazzino.getLog().getOggetti().keySet());
		List<String> listaStringhe = new ArrayList<>(lista.size());
		String[] array = new String[listaStringhe.size()] ;
		for(int i = 0 ; i < lista.size(); i ++){
			
			listaStringhe.add(lista.get(i).getNome());
			
		}
		array = listaStringhe.toArray(array) ;
		return array;
	}
	
	
	
	
	/**
	 * 	Method to get item's quantity
	 * 
	 * @param numero
	 * 				index of the item in the list
	 * 
	 * @return
	 * 		integer
	 */
	public int conversioneNumeri(int numero){
		if(numero < 0 ){
			return 0;
		}
		List<Materiale> lista = new LinkedList<>(Magazzino.getLog().getOggetti().keySet());
		Materiale materiale = lista.get(numero);
		int quantita = BaseSpaziale.getLog().getQuantitativoOggetto(materiale);
		return quantita;
	}

	
	
	
	/**
	 *  Method to notify that the button "Aumenta Spazio" is pressed
	 */
	public void aumentaSpazioMagazzino(){
		
		boolean valore = Magazzino.getLog().aumentaSpazio();
		if(! valore){
			this.view.Show_Message("Non ci sono abbastanza mattoni ");
		}else{
			Controller.getLog().getPassaOre(Controller.getLog().getCostante());
		}
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateMagazzino(conversioneOggetti(),Magazzino.getLog().getSpazio()- Magazzino.getLog().getOggetti().size());
	}
	
	
	

	/**
	 *  Method to notify that the button "Libera slot" is pressed
	 *  
	 * @param nomeOggettoDaEliminare
	 */
	public void liberaSlotMagazzino(int posizioneOggettoInLista){
		
		List<Materiale> listaOggettiMagazzino = new LinkedList<>(Magazzino.getLog().getOggetti().keySet());
		Materiale materialeDaEliminare = listaOggettiMagazzino.get(posizioneOggettoInLista);
		int quanto = Magazzino.getLog().getQuantitaOggetto(materialeDaEliminare);
		Magazzino.getLog().rimuoviOggetto(materialeDaEliminare, quanto);
		Controller.getLog().getPassaOre(Controller.getLog().getCostante()*2);
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateMagazzino(conversioneOggetti(),Magazzino.getLog().getSpazio()- Magazzino.getLog().getOggetti().size());
	}
	
	
	
	
	/**
	 * Method to initialize  Parametri view
	 */
	public void setMagazzino(){
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateMagazzino(conversioneOggetti(),Magazzino.getLog().getSpazio()- Magazzino.getLog().getOggetti().size());
	}

}
