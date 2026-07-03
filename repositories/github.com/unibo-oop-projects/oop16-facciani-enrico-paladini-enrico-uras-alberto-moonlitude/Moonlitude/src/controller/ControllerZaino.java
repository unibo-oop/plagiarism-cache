package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import model.BaseSpaziale;
import model.Oggetti.Cibo;
import model.Oggetti.CommonEquipaggiamento;
import model.Oggetti.Contenitore;
import model.Oggetti.Materiale;
import model.Oggetti.Oggetto;
import model.Oggetti.TutaSpaziale;
import model.Oggetti.Zaino;
import view.ViewImpl;

public class ControllerZaino{
	
	
	private ViewImpl view;
	
	
	
	
	public ControllerZaino(ViewImpl view) {
		this.view = view;
	}

	


	/**
	 * Method to get item's quantity 
	 * 
	 * @param 
	 * 			index of item in the list
	 * @return
	 * 			integer
	 */
	public int conversioneNumero(int numero) {
		List<Oggetto> lista = new LinkedList<>(Zaino.getLog().getOggetti().keySet());
		Oggetto materiale = lista.get(numero);
		int quantita = Zaino.getLog().getQuantitaOggetto(materiale); 
		return quantita;
	}
	
	
	
	
	/**
	 * Method to convert map.key in array of string
	 * 
	 * @return
	 * 			array of string
	 */
	private String[] conversione() {
		List<Oggetto> lista = new LinkedList<>(Zaino.getLog().getOggetti().keySet());
		List<String> listaStringhe = new ArrayList<>(lista.size());
		String[] array = new String[listaStringhe.size()] ;
		for(int i = 0 ; i < lista.size(); i ++){
			
			listaStringhe.add(lista.get(i).getNome());
			
		}
		array = listaStringhe.toArray(array) ;
		return array;
	}


	
	
	/**
	 * Method to initialize  Zaino view
	 */
	public void setZainoView() {
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateZaino(conversione());
	}

	
	
	
	/**
	 * Method to find an item from a string
	 * 
	 * @param stringa
	 * 				string
	 * @return
	 */
	public Oggetto determinaOggetto(String stringa){
		
		for ( Cibo cibo : Cibo.values()){
			if(cibo.getNome().equals(stringa)){
				return cibo;
			}
		}
		
		for ( Materiale materiale : Materiale.values()){
			if(materiale.getNome().equals(stringa)){
				return materiale;
			}
		}
		
		for ( CommonEquipaggiamento equipaggiamento : CommonEquipaggiamento.values()){
			if(equipaggiamento.getNome().equals(stringa)){
				return equipaggiamento;
			}
		}
		
		for ( TutaSpaziale tutaSpaziale : TutaSpaziale.values()){
			if(tutaSpaziale.getNome().equals(stringa)){
				return tutaSpaziale;
			}
		}
		
		return null;
		
	}
	
	/**
	 * Method to notify that the button "Sposta" is pressed
	 * 
	 * @param numero
	 * 				index of item in bag's items list
	 */
	public void spostaOggettoZaino(String nomeDaRicercare) {
		
		Oggetto oggetto = determinaOggetto(nomeDaRicercare);
		int quantita = Zaino.getLog().getQuantitaOggetto(oggetto);
		int rimanente = BaseSpaziale.getLog().aggiungiQuantitativoOggetto(oggetto, quantita);
		if(rimanente >= Contenitore.getMax() ){
			this.view.Show_Message("Spazio insufficiente nel contenitore!");
		} else if(rimanente >= 0){
			Zaino.getLog().rimuoviOggetto(oggetto, quantita);
			Controller.getLog().getPassaOre(Controller.getLog().getCostante());
		} else {
			Zaino.getLog().rimuoviOggetto(oggetto, quantita+rimanente);
			Controller.getLog().getPassaOre(Controller.getLog().getCostante());
			this.view.Show_Message("All'interno dello zaino rimane "+ Zaino.getLog().getQuantitaOggetto(oggetto));
		}
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateZaino(conversione());
	}
}
