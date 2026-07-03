package controller;

import model.Stanza.Generatore;
import model.Stanza.Luminosita;
import view.ViewImpl;

public class ControllerGeneratore {
	
	
	private Luminosita[] luminosita = Luminosita.values();
	private int contatoreLuminosita=0;
	private ViewImpl view ;



	
	public ControllerGeneratore(ViewImpl view) {
		this.view = view;
		
	}



	
	/**
	 * Method to modify the power of the light
	 * @param modificaLuminosita
	 */
	public void modificaLuminosita(int modificaLuminosita) {
	
		if(modificaLuminosita>0){
			
			if(!this.luminosita[Controller.getLog().getCarica()].equals(Luminosita.ALTISSIMA)){
				this.contatoreLuminosita=Controller.getLog().getCarica()+modificaLuminosita;
				Controller.getLog().setCarica(contatoreLuminosita);
				Generatore.getLog().setLuminosita(this.luminosita[Controller.getLog().getCarica()]);
				Controller.getLog().getControllerAstronauta().setParametri();
				this.view.UpdateGeneratore(Generatore.getLog().getCarica().intValue(), Generatore.getLog().getLuminosita().getNome());
			}
		}
		
		if(modificaLuminosita<0){
			
			if (! this.luminosita[Controller.getLog().getCarica()].equals(Luminosita.SPENTA)){
				this.contatoreLuminosita=Controller.getLog().getCarica()+modificaLuminosita;
				Controller.getLog().setCarica(contatoreLuminosita);
				Generatore.getLog().setLuminosita(this.luminosita[Controller.getLog().getCarica()]);
				Controller.getLog().getControllerAstronauta().setParametri();
				this.view.UpdateGeneratore(Generatore.getLog().getCarica().intValue(), Generatore.getLog().getLuminosita().getNome());
			}
		}
	}
	
	
	
	
	/**
	 * Method to set the initial light
	 */
	public void inizioLuminosita() {

		Generatore.getLog().setLuminosita(Luminosita.SPENTA);
		this.view.UpdateGeneratore(Generatore.getLog().getCarica().intValue(), Generatore.getLog().getLuminosita().getNome());
	}
	
	
	
	
	/**
	 * Method to notify that that button "Ricarica" is pressed
	 */
	public void ricaricaGeneratore() {
		boolean valore = Generatore.getLog().riCarica();
		if( ! valore){
			this.view.Show_Message("Non puoi ricaricare");
		}
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateGeneratore(Generatore.getLog().getCarica().intValue(), Generatore.getLog().getLuminosita().getNome());	
	}
	
	
	
	
	/**
	 * Method to set V_Generatore view
	 */
	public void setGeneratore(){
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateGeneratore(Generatore.getLog().getCarica().intValue(), Generatore.getLog().getLuminosita().getNome());
	}
}
