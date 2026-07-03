package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.Posizione;
import model.Oggetti.Contenitore;
import model.Oggetti.Oggetto;
import model.Oggetti.Zaino;
import model.Stanza.Radar;
import view.ViewImpl;



public class ControllerEsplorazione{
	

	private ViewImpl view;

	
	
	
	public ControllerEsplorazione(ViewImpl view){
		this.view = view;	
	}
	
	
	
	
	/**
	 * Method to convert map.key in array of string
	 * 
	 * @return
	 * 			array of string
	 */
	private String[] conversione(){
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
	 * Method to get the amount of object
	 * 
	 * @param numero
	 * 				index of item in the list
	 * @return
	 * 			the amount
	 */
	public int getQuantitaOggettiOttenuti(int numero){
		List<Oggetto> lista = new LinkedList<>(Controller.getLog().getMap().keySet());
		Oggetto oggetto = lista.get(numero);
		int quantita = Controller.getLog().getMap().get(oggetto);
		return quantita;
		
	}
	
	
	
	
	/**
	 * Method to get the amount of item
	 * 
	 * @param numero
	 * 				index of the item in list
	 * @return
	 * 			the amount
	 */
	public int getQuantitaOggettiZaino(int numero){
		List<Oggetto> lista = new LinkedList<>(Zaino.getLog().getOggetti().keySet());
		Oggetto oggetto = lista.get(numero);
		int quantita = Zaino.getLog().getQuantitaOggetto(oggetto);
		return quantita;
	}
	
	
	
	
	/**
	 * Method to convert map.value in array of integer
	 * 
	 * @return
	 * 			array of integer
	 */
	private String[] conversioneOggettiOttenuti(){
		List<Oggetto> lista = new LinkedList<>(Controller.getLog().getMap().keySet());
		List<String> listaStringhe = new ArrayList<>(lista.size());
		String[] array = new String[listaStringhe.size()] ;
		for(int i = 0 ; i < lista.size(); i ++){
			
			listaStringhe.add(lista.get(i).getNome());
			
		}
		array = listaStringhe.toArray(array) ;
		return array;
	}
	
	
	
	
	/**
	 * Method to notify that the button Esplora is pressed
	 */
	public void bottoneEsplora(){
		this.view.SetEsploratore_Zaino();	
	}
	
	
	
	
	/**
	 * Method to notify that the button "Aggiungi" is pressed
	 * 
	 * @param nomeOggetto
	 * 					name of item to be added in backpack
	 */
	public void bottoneAggiungi(String nomeOggetto){

		Oggetto oggetto = Controller.getLog().getControllerZaino().determinaOggetto(nomeOggetto);
		int quantita = Controller.getLog().getMap().get(oggetto);
		int rimanente = Zaino.getLog().aggiungiOggetto(oggetto, quantita);
		if(rimanente >= Contenitore.getMax() ){
		this.view.Show_Message("Spazio insufficiente nel contenitore!");
		} else {
		Controller.getLog().getMap().remove(oggetto);
		}
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateEsploratoreZaino(conversione(), conversioneOggettiOttenuti());
	}
	
	
	
	
	/**
	 * Method to notify tha the button "Rimuovi" is pressed
	 * 
	 * @param nomeOggetto
	 * 						name of item to be deleted
	 */
	public void bottoneRimuovi(String nomeOggetto){
		
		Oggetto oggetto = Controller.getLog().getControllerZaino().determinaOggetto(nomeOggetto);
		int quantita = Zaino.getLog().getOggetti().get(oggetto);
		Zaino.getLog().rimuoviOggetto(oggetto, quantita);
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateEsploratoreZaino(conversione(), conversioneOggettiOttenuti());		
	}
	
	
	
	
	/**
	 * Method to set Esploratore Zaino view
	 */
	public void setViewEsplorazioneZaino(){
		Controller.getLog().getControllerAstronauta().setParametri();
		Map<Oggetto,Integer>mappaOggettiOttEsplora = Posizione.esplorazione(Radar.getLog().getPosti(true).get(this.view.Get_Selected_Esplorazione()), this.view.Get_OreEsplorazione(), true);
		Controller.getLog().setMap(mappaOggettiOttEsplora);
		Controller.getLog().getPassaOre(this.view.Get_OreEsplorazione());
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateEsploratoreZaino(conversione(), conversioneOggettiOttenuti());
	}
	
	
	
	
	/**
	 * Method to notify that the button "Avanti" is pressed
	 */
	public void bottoneAvanti(){
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.SetPrincipale();
	}
	
	
	
	
	/**
	 * Method to set Esplora view
	 */
	public void setEsploraView(){
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateEsplorazione(Controller.getLog().getControllerRadar().getPosizione());
	}
}
