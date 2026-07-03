package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Model;
import model.Oggetti.Cibo;
import model.Stanza.Giardino;
import model.Stanza.Pianta;
import model.Stanza.Refrigeratore;
import view.ViewImpl;

public class ControllerGiardinoPianta {
	
	
	private ViewImpl view ;
	private Model model;
	
	
	
	
	public ControllerGiardinoPianta(ViewImpl view,Model model) {
		this.model = model;
		this.view = view;
	}

	
	
	
	/**
	 * Method to convert list of Plant's instances in array of string
	 * 
	 * @return
	 * 			array of string
	 */
	private String[] conversione(){
		List<Pianta> lista = new LinkedList<>(Giardino.getLog().getPiante());
		List<String> listaStringhe = new ArrayList<>(lista.size());
		String[] array = new String[listaStringhe.size()] ;
		for(int i = 0 ; i < lista.size(); i ++){
			
			listaStringhe.add(lista.get(i).getCommonPianta().getNome());
			
		}
		array = listaStringhe.toArray(array) ;
		return array;
	}

	
	
	
	/**
	 * Method to convert map.keys in an array of string
	 * 
	 * @return
	 * 		 	possible plants to be planted in an array of string
	 */
	public String[] getPianteDisponibili(){
		
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
	 * Method to find CommonPianta from a string
	 * 
	 * @param nomePiantaDaCercare
	 * 							string of the plant
	 * @return
	 * 			the relative CommonPianta		
	 */
	private Cibo cercoCibo(String nomePiantaDaCercare){
		
		for ( Cibo cp : Cibo.values()){
			
			if(cp.getNome().equals(nomePiantaDaCercare)){
				return  cp;
			}
		}
		return null;
	}
	
	
	
	
	/**
	 *  Method to notify that the button "Aggiungi slot" is pressed
	 */
	public void aggiungiSlotGiardino() {
		boolean valore = Giardino.getLog().aggiungiSlots();
		if(! valore){
			this.view.Show_Message("Non ci sono abbastanza mattoni ");
		}else{
			Controller.getLog().getPassaOre(Controller.getLog().getCostante());
		}
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateGiardino(conversione(),getPianteDisponibili());
	}
	
	
	
	
	/**
	 *  Method to notify that the button "Aggiungi Pianta" is pressed
	 *  
	 * @param nomePiantaDaAggiungere
	 * 								plant's name to be added to the garden
	 */
	public void aggiungiPiantaGiardino(String nomePiantaDaAggiungere) {

		Cibo nome = cercoCibo(nomePiantaDaAggiungere);
		Pianta pianta = new Pianta(nome.getPianta());
		Giardino.getLog().aggiungiPianta(pianta);
		this.view.UpdateGiardino(conversione(),getPianteDisponibili());
	}

	

	
	/**
	 *  Method to notify that the button "Annaffia" is pressed
	 *  
	 * @param posizioneDellaPiantaInLista
	 * 									index in plant's list of the plant to be watered	
	 */
	public void annaffiaPianta(int posizioneDellaPiantaInLista){
		
		List<Pianta> listaDiPiante =  Giardino.getLog().getPiante();
		Pianta piantaDaAnnaffiare = listaDiPiante.get(posizioneDellaPiantaInLista);
		int valore = piantaDaAnnaffiare.annaffia();
		if(valore == -2){
			this.view.Show_Message("Non c'e' acqua nel filtratore!");
		}else if(valore == -1){
			this.view.Show_Message("La pianta non puo' ancora essere annaffiata!");
		}else if( valore == 0 ){
			this.view.Show_Message("La pianta e' gia' stata annaffiata!");
		}else if(valore == 1){
			this.view.Show_Message("Pianta annaffiata!");
			Controller.getLog().getPassaOre(Controller.getLog().getCostante());
		}
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdatePianta(piantaDaAnnaffiare.getCommonPianta().getNome(),piantaDaAnnaffiare.getStato().toString(), piantaDaAnnaffiare.getOreDaAnnaffiare(), piantaDaAnnaffiare.getOrePiantata());
		this.view.UpdateGiardino(conversione(),getPianteDisponibili());
	}

	
	
	
	/**
	 *  Method to notify that the button "Prendi Pianta" is pressed
	 *  
	 * @param numeroDellaPiantaInLista
	 * 									plant's position in list
	 */
	public void prendiPianta(int numeroDellaPiantaInLista) {
		List<Pianta> listaDiPiante =  Giardino.getLog().getPiante();
		Pianta piantaDaAnnaffiare = listaDiPiante.get(numeroDellaPiantaInLista);
		boolean valore = Giardino.getLog().prendiPianta(numeroDellaPiantaInLista);
		if(!valore){
			this.view.Show_Message("La pianta non puo' essere raccolta!");
		}else{
			Controller.getLog().getPassaOre(Controller.getLog().getCostante());
		}
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdatePianta(piantaDaAnnaffiare.getCommonPianta().getNome(),piantaDaAnnaffiare.getStato().toString(), piantaDaAnnaffiare.getOreDaAnnaffiare(), piantaDaAnnaffiare.getOrePiantata());
		this.view.UpdateGiardino(conversione(),getPianteDisponibili());
		
	}
	



	/**
	 *  Method to notify that the button that represent a single plant is pressed
	 */
	public void interfacciaPianta(int indiceDellaPiantaNellaLista) {
	
		List<Pianta> listaPianta = Giardino.getLog().getPiante();
		Pianta pianta = listaPianta.get(indiceDellaPiantaNellaLista);
		this.view.UpdatePianta(pianta.getCommonPianta().getNome(),pianta.getStato().toString(), pianta.getOreDaAnnaffiare(), pianta.getOrePiantata());
		this.view.UpdateGiardino(conversione(),getPianteDisponibili());
	}
	
	
	
	
	/**
	 * Method to notify that the button "Ristoro" is pressed
	 */
	public void ristoroGiardino(){
		this.model.getTempo().ristoro();
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateGiardino(conversione(),getPianteDisponibili());
	}
	
	
	
	
	/**
	 * Method to initialize  Giardino view
	 */
	public void setGiardino(){
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateGiardino(conversione(),getPianteDisponibili());
	}
}
