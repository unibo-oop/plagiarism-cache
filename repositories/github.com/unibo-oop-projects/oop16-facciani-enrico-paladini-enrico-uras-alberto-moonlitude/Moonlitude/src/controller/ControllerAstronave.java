package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Astronave;
import model.Oggetti.Materiale;
import model.Stanza.Magazzino;
import view.ViewImpl;

public class ControllerAstronave  {
	
	
	private ViewImpl view;

	
	
	
	public ControllerAstronave(ViewImpl view) {
		this.view = view;
	}

	
	
	
	/**
	 * Method to convert map.key in array of string
	 * 
	 * @return
	 * 			array of string
	 */
	private String[] conversione(){
		List<Materiale> lista = new LinkedList<>(Astronave.getLog().getPezziDanneggiati().keySet());
		List<String> listaStringhe = new ArrayList<>(lista.size());
		String[] array = new String[listaStringhe.size()] ;
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
		List<Integer> lista = new LinkedList<>(Astronave.getLog().getPezziDanneggiati().values());
		int quantita = lista.get(numero);
		return quantita;
	}
	
	
	

	/**
	 *  Method to notify that the button "Ricarica" is pressed
	 *  
	 */
	public void ricaricaScudiAstronave() {
		boolean valore = Astronave.getLog().ricaricaScudi(Materiale.SFERA_ENERGIA,Controller.getLog().getCostante());
		if( !valore){
			this.view.Show_Message("Non è possibile ricaricare gli scudi!");
		}else{
			Controller.getLog().getPassaOre(Controller.getLog().getCostante());
		}
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateAstronave(conversione(), Astronave.getLog().getScudo(),Magazzino.getLog().getQuantitaOggetto(Materiale.SFERA_ENERGIA));	
		Controller.getLog().winGame();
	}
	
	
	
	
	/**
	 * Method to notify that the button "Sostituisci" is pressed
	 * 
	 * @param posizioneMaterialeNellaMappa
	 * 									index of damaged piece to be replaced
	 */
	public void sostituisciPezziRottiAstronave(int posizioneMaterialeNellaMappa) {

		List<Materiale> listaOggettiRotti =	new LinkedList<>(Astronave.getLog().getPezziDanneggiati().keySet());
		Materiale materialeDaSostituire= listaOggettiRotti.get(posizioneMaterialeNellaMappa);
		boolean valore = Astronave.getLog().ripara(materialeDaSostituire);
		if(!valore){
			this.view.Show_Message("Non hai abbastanza materiali");
		}else{
			Controller.getLog().getPassaOre(Controller.getLog().getCostante()*2);
		}
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateAstronave(conversione(), Astronave.getLog().getScudo(),Magazzino.getLog().getQuantitaOggetto(Materiale.SFERA_ENERGIA));
		Controller.getLog().winGame();
	}

	
	
	
	/**
	 * Method to initialize  Astronave view
	 */
	public void setViewAstronave(){
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateAstronave(conversione(), Astronave.getLog().getScudo(),Magazzino.getLog().getQuantitaOggetto(Materiale.SFERA_ENERGIA));
		
	}
		
}
