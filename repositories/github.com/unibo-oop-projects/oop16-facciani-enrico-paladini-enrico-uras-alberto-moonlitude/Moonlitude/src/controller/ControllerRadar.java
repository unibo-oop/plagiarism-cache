package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Posizione;
import model.Stanza.Radar;
import view.ViewImpl;

public class ControllerRadar {
	
	
	private ViewImpl view;
	
	
	
	
	public ControllerRadar(ViewImpl view) {
		this.view = view;
	}

	
	
	/**
	 * Method to get uncovered places in the world
	 * 
	 * @return
	 * 			array of string 
	 */
	public String[] getPosizione(){
		
		String[] luoghi = conversionePosizione();
		return luoghi;
	}



	/**
	 * Method to convert position from map.key to array of string
	 * @return
	 * 			array of string
	 */
	private String[] conversionePosizione(){
		
		List<Posizione> lista = new LinkedList<>(Radar.getLog().getPosti(true));
		List<String> listaStringhe = new ArrayList<>(lista.size());
		String[] array = new String[listaStringhe.size()] ;
		for(int i = 0 ; i < lista.size(); i ++){
			
			listaStringhe.add(lista.get(i).toString());
			
		}
		array = listaStringhe.toArray(array) ;
		return array;
	}
	

	
	
	/**
	 *  Method to notify that the button "Lancia drone" is pressed
	 */
	public void lanciaDroneRadar(){
		int valore = Radar.getLog().lanciaDrone();
		if(valore == -1){
			this.view.Show_Message("Non ci sono droni nel magazzino");
		}else if(valore == 0){
			this.view.Show_Message("Tutti i luoghi sono gia' stati scoperti!");
		}else{
			Controller.getLog().getPassaOre(Controller.getLog().getCostante());
		}
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateRadar(conversionePosizione());
	}
	
	
	
	
	/**
	 * Method to initialize Radar view
	 */
	public void setRadar(){
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateRadar(conversionePosizione());	
	}
}
