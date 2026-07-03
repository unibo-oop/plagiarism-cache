package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.BaseSpaziale;
import model.Model;
import model.Oggetti.Cibo;
import model.Stanza.Cucina;
import model.Stanza.Refrigeratore;
import view.ViewImpl;

public class ControllerRefrigeratore {
	
	
	private ViewImpl view ;
	private Model model;


	
	
	public ControllerRefrigeratore(ViewImpl view,Model model) {
		this.model = model;
		this.view = view;
	}



	
	/**
	 * Method to convert map.value in array of integer
	 * 
	 * @return
	 * 			array of integer
	 */
	public int conversioneNumeri(int numero){
		if ( numero < 0 ){
			return 0;
		}
		List<Cibo> lista = new LinkedList<>(Refrigeratore.getLog().getOggetti().keySet());
		Cibo cibo = lista.get(numero);
		int quantita = BaseSpaziale.getLog().getQuantitativoOggetto(cibo);
	
		return quantita;
	
	}
	
	
	
	
	/**
	 * Method to convert map.key in array of string
	 * 
	 * @return
	 * 			array of string
	 */
	private String[] conversioneOggetti(){
	
			List<Cibo> lista = new LinkedList<>(Refrigeratore.getLog().getOggetti().keySet());
			List<String> listaStringhe = new ArrayList<>(lista.size());
			String[] array = new String[listaStringhe.size()] ;
			for(int i = 0 ; i < lista.size(); i ++){
				
				listaStringhe.add(lista.get(i).getNome());
				
			}
			array = listaStringhe.toArray(array) ;
			return array;
		}
	
	
	
	
	/**
	 *  Method to notify that the button "Aumenta Spazio" is pressed
	 */
	public void aumentaSpazioRefrigeratore(){
		
		boolean valore = Refrigeratore.getLog().aumentaSpazio();
		if( ! valore){
			this.view.Show_Message("Non ci sono abbastanza mattoni");
		}else{
			Controller.getLog().getPassaOre(Controller.getLog().getCostante());
		}
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateRefrigeratore(conversioneOggetti(),Refrigeratore.getLog().getSpazio()- Refrigeratore.getLog().getOggetti().size());	
	}
	
	
	
	
	/**
	 *  Method to notify that the button "Mangia" is pressed
	 *  
	 * @param posizioneCiboDaMangiare
	 * @param quanto
	 */
	public void mangiaCiboRefrigeratore(int posizioneCiboDaMangiare){
		
		List<Cibo> listaCiboRefrigeratore = new LinkedList<>(Refrigeratore.getLog().getOggetti().keySet());
		Cibo ciboDaMangiare = listaCiboRefrigeratore.get(posizioneCiboDaMangiare);	
		Cucina.getLog().mangia(this.model.getAstronauta(), ciboDaMangiare, Controller.getLog().getCostante());
		Controller.getLog().getPassaOre(Controller.getLog().getCostante());
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateRefrigeratore(conversioneOggetti(),Refrigeratore.getLog().getSpazio()- Refrigeratore.getLog().getOggetti().size());	
	}
	
	
	
	
	/**
	 *  Method to notify that the button "Libera slot" is pressed
	 *  
	 * @param posizioneOggettoDaEliminare
	 * @param quanto
	 */
	public void liberaSlotRefrigeratore(int posizioneOggettoDaEliminare){
		
		List<Cibo> listaCiboRefrigeratore = new LinkedList<>(Refrigeratore.getLog().getOggetti().keySet());
		Cibo ciboDaEliminare = listaCiboRefrigeratore.get(posizioneOggettoDaEliminare);
		int quanto = Refrigeratore.getLog().getQuantitaOggetto(ciboDaEliminare);
		Refrigeratore.getLog().rimuoviOggetto(ciboDaEliminare, quanto);
		Controller.getLog().getPassaOre(Controller.getLog().getCostante());
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateRefrigeratore(conversioneOggetti(),Refrigeratore.getLog().getSpazio()- Refrigeratore.getLog().getOggetti().size());	
	}
	
	
	
	
	/**
	 * Method to initialize  Refrigeratore view
	 */
	public void setRefrigeratore(){
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateRefrigeratore(conversioneOggetti(),Refrigeratore.getLog().getSpazio()- Refrigeratore.getLog().getOggetti().size());	
	}
}
