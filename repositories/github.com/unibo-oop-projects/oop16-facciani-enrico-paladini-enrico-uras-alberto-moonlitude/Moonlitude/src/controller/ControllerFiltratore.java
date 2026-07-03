package controller;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;

import model.Model;
import model.Stanza.ContenitoreAcqua;
import model.Stanza.Filtratore;
import view.ViewImpl;

public class ControllerFiltratore{
	
	
	private ViewImpl view;
	private Model model;
	
	
	
	
	public ControllerFiltratore(ViewImpl view,  Model model) {
		this.model = model;
		this.view = view;
	}

	
	
	
	/**
	 * Method to convert list in array of string
	 * 
	 * @return
	 * 			array of string
	 */
	private String[] conversione(){
		List<ContenitoreAcqua> lista = new LinkedList<>(Filtratore.getLog().getContenitori());
		List<String> listaStringhe = new ArrayList<>(lista.size());
		String[] array = new String[listaStringhe.size()] ;
		for(int i = 0 ; i < lista.size(); i ++){
			
			listaStringhe.add(lista.get(i).getStato().toString());
			
		}
		array = listaStringhe.toArray(array) ;
		return array;
	}
	
	
	
	
	/**
	 * Method to get item's quantity 
	 * 
	 * @param 
	 * 			index of item in the list
	 * @return
	 * 			integer
	 */
	public int conversioneNumeri(int numero){
		if(numero < 0 ){
			return 0;
		}
		List<ContenitoreAcqua> lista = new LinkedList<>(Filtratore.getLog().getContenitori());
		ContenitoreAcqua materiale = lista.get(numero);
		int quantita = materiale.getRiempimento();
		return quantita;
	}

	
	
	
	/**
	 * Method to set the Filtratore view
	 */
	public void setFiltratore() {
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateFiltratore(conversione(),Filtratore.getLog().getAcquaRaccolta());
	}


	
	
	/**
	 * Method to notify that the button "Aggiungi" is pressed
	 */
	public void aggiungiContenitore(){
		Filtratore.getLog().aggiungiContenitore();
		this.view.UpdateFiltratore(conversione(),Filtratore.getLog().getAcquaRaccolta());
	}
	
	
	
	
	/**
	 * Method to notify that the button "Raccogli" is pressed
	 * 
	 * @param indiceListaContenitori
	 * 								index of container in the container's list
	 */
	public void spostaAcquaContenitore(int indiceListaContenitori){
		List<ContenitoreAcqua> lista = new LinkedList<>(Filtratore.getLog().getContenitori());
		ContenitoreAcqua contenitore = lista.get(indiceListaContenitori);
		Filtratore.getLog().aggiungiAcquaRaccolta(contenitore.spostaAcqua());
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateFiltratore(conversione(),Filtratore.getLog().getAcquaRaccolta());
	}
	
	
	
	
	/**
	 * Method to notify that the button "Bevi" is pressed
	 * 
	 * @param quantitaAcquaCollezionata
	 * 									amount of water to be drunk
	 */
	public void beviAcquaFiltratore(int quantitaAcquaCollezionata) {
		
		Filtratore.getLog().Bevi(this.model.getAstronauta(),quantitaAcquaCollezionata);
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateFiltratore(conversione(),Filtratore.getLog().getAcquaRaccolta());
	}

}
