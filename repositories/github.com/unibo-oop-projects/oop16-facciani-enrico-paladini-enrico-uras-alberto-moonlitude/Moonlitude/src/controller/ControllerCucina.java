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

public class ControllerCucina {

	
	private ViewImpl view;
	private Model model;
	
	
	
	
	public ControllerCucina(ViewImpl view,Model model) {
		this.model = model;
		this.view = view;
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
			array = listaStringhe.toArray(array);
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
		List<Cibo> lista = new LinkedList<>(Refrigeratore.getLog().getOggetti().keySet());
		Cibo materiale = lista.get(numero);
		int quantita = BaseSpaziale.getLog().getQuantitativoOggetto(materiale);
		return quantita;
	}
		
	
	
	
	/**
	 * Method to initialize  Cucina view
	 */
	public void setCucina() {
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateCucina(conversioneOggetti());	
	}

	
	
	
	/**
	 *  Method to notify that the button "Mangia" is pressed
	 *  
	 * @param numero
	 */
	public void mangiaCucina(int numero) {
		
		List<Cibo> ciboInCucina = new LinkedList<>(Refrigeratore.getLog().getOggetti().keySet());
		Cibo ciboSelezionato = ciboInCucina.get(numero);
		Cucina.getLog().mangia( this.model.getAstronauta(), ciboSelezionato, Controller.getLog().getCostante());
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateCucina(conversioneOggetti());	
	}
}
