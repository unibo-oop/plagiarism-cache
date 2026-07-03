package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.BaseSpaziale;
import model.Model;
import model.Oggetti.CommonCraft;
import model.Oggetti.Materiale;
import model.Oggetti.Oggetto;
import model.Stanza.Laboratorio;
import view.ViewImpl;

public class ControllerLaboratorio {
	
	
	private ViewImpl view ;
	private Model model;
	
	
	
	
	public ControllerLaboratorio(ViewImpl view, Model model){
		this.model = model;
		this.view = view;
	}
	
	
	
	
	/**
	 * Method to convert map.key in array of string
	 * 
	 * @return
	 * 		array of string
	 */
	private String[] conversioneOggetti(){
		List<Oggetto> lista = new LinkedList<>(Laboratorio.getLog().getMaterialiNonStudiati());
		List<String> listaStringhe = new ArrayList<>(lista.size());
		String[] array = new String[listaStringhe.size()];
		
		for(int i = 0 ; i < lista.size(); i ++){
				
			listaStringhe.add(lista.get(i).getNome());
				
		}
		
		array = listaStringhe.toArray(array) ;
		return array;
	}
	
	
	
	
	/**
	 * Method to convert map.key in array of string
	 * 
	 * @return
	 * 		array of string
	 */
	private String[] conversioneOggettiCraftabili(){
		List<CommonCraft> lista = new LinkedList<>(Laboratorio.getLog().getDisponibiliDaCraftare());
		List<String> listaStringhe = new ArrayList<>(lista.size());
		String[] array = new String[listaStringhe.size()];
		
		for(int i = 0 ; i < lista.size(); i ++){
				
			listaStringhe.add(lista.get(i).getNome());
				
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
		List<Materiale> lista = new LinkedList<>(Laboratorio.getLog().getMaterialiNonStudiati());
		Materiale materiale = lista.get(numero);
		int quantita = BaseSpaziale.getLog().getQuantitativoOggetto(materiale);
		return quantita;
	}
			
	
	
	
	/**
	 * Method to get a representation of CommonCraft
	 * 
	 * @param indice
	 * 				index of CommonCraft in list
	 * @return
	 * 			string
	 */
	public String getOggettiRichiesti(int indice){
		List<CommonCraft> lista = new LinkedList<>(Laboratorio.getLog().getDisponibiliDaCraftare());
		CommonCraft commonCraft = lista.get(indice);
		String string = CommonCraft.rappresenta(commonCraft);
		return string;
	}
	
	
	
	
	/**
	 *  Method to notify that the button "Ricerca" is pressed
	 *  
	 * @param numeroOggettoInLista
	 * 								object's position in list
	 */
	public void ricercaLaboratorio(int nomeOggettoDaRicercare) {
		
		List<Materiale> listaMaterialeDaRicercare = new LinkedList<>(Laboratorio.getLog().getMaterialiNonStudiati());
		Materiale materialeDaRicercare= listaMaterialeDaRicercare.get(nomeOggettoDaRicercare);
		Laboratorio.getLog().ricerca(materialeDaRicercare);
		Controller.getLog().getPassaOre(Controller.getLog().getCostante());
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateLaboratorio(conversioneOggetti(),conversioneOggettiCraftabili());
	}


	
	
	/**
	 *  Method to notify that the button "Crafta" is pressed
	 *  
	 * @param numeroOggettoDaCraftare
	 * 								object's position in list
	 */
	public void craftaLaboratorio(int numeroOggettoDaCraftare) {
	
		List<CommonCraft> listaMaterialiCraftabili = new LinkedList<>(Laboratorio.getLog().getDisponibiliDaCraftare());
		CommonCraft materiale = listaMaterialiCraftabili.get(numeroOggettoDaCraftare);
		int valore = Laboratorio.getLog().craft(materiale, this.model.getAstronauta());
		if(valore == -1){
			this.view.Show_Message("Non hai abbastanza materiale per craftare l'oggetto!");
		}else if(valore == 0 ){
			this.view.Show_Message("Crafting fallito, hai perso tutti gli oggetti!");
			Controller.getLog().getPassaOre(Controller.getLog().getCostante()*2);
		}else{
			this.view.Show_Message("Crafting riuscito!");
			Controller.getLog().getPassaOre(Controller.getLog().getCostante()*2);
		}
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateLaboratorio(conversioneOggetti(),conversioneOggettiCraftabili());
	}
	
	
	
		
	/**
	 * Method to initialize  Laboratorio view
	 */
	public void setLaboratorio(){
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateLaboratorio(conversioneOggetti(),conversioneOggettiCraftabili());
	}
}
