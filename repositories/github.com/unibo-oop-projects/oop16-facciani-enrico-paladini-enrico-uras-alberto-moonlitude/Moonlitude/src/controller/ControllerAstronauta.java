package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Model;
import model.Oggetti.CommonEquipaggiamento;
import model.Oggetti.Equipaggiamento;
import view.ViewImpl;

public class ControllerAstronauta {

	private Model model;
	private ViewImpl view ;
	private String bonus = "   Ossigeno: "+Equipaggiamento.getLog().getOssigeno()+"    Risorse: "+Equipaggiamento.getLog().getRisorse()+"    Tempo: "+Equipaggiamento.getLog().getTempo();

	
	
	public ControllerAstronauta(ViewImpl view,Model model){
		this.model = model;
		this.view = view;
	}
	
	
	
	
	/**
	 * Method to convert equipment's items in string
	 * 
	 * @return
	 * 		array of equipment's items
	 */
	private String[] conversione(){
		
		List<CommonEquipaggiamento> lista = new LinkedList<>(Equipaggiamento.getLog().getOggettiEquipaggiabili());
		List<String> listaStringhe = new ArrayList<>(lista.size());
		String[] array = new String[listaStringhe.size()] ;
		for(int i = 0 ; i < lista.size(); i ++){
			
			listaStringhe.add(lista.get(i).getNome());
			
		}
		array = listaStringhe.toArray(array) ;
		return array;
	}
	
	
	
	
	/**
	 * Method to set survival parameters
	 */
	public void setParametri(){
		
		int fame = this.model.getAstronauta().getParametri().getFame();
		int sete = this.model.getAstronauta().getParametri().getSete();
		int ossigeno = this.model.getAstronauta().getParametri().getOssigeno();
		int ora = this.model.getTempo().getOra();
		String date = this.model.getTempo().getDate();
		this.view.SetParametri(fame, sete, ossigeno, ora, date, Controller.getLog().getCondizioneAtmosferica());	
	}
	
	
	
	
	/**
	 * Method to set Equipaggiamento view
	 */
	public void setEquipaggiamento() {
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateEquipaggiamento(conversione(),Equipaggiamento.getLog().getTuta().getNome(),this.bonus , Equipaggiamento.getLog().getEquipaggio().getNome());
	}
	
	
	
	
	/**
	 * Method to notify that the button "Seleziona" is pressed
	 * 
	 * @param indiceListaEquipaggiamento
	 * 									index of list of equipped items 
	 */
	public void selezionaEquipaggiamento(int indiceListaEquipaggiamento){
		
		List<CommonEquipaggiamento> lista = new LinkedList<>(Equipaggiamento.getLog().getOggettiEquipaggiabili());
		CommonEquipaggiamento equipaggiamento = lista.get(indiceListaEquipaggiamento);
		Equipaggiamento.getLog().aggiuntaEquipaggiamento(equipaggiamento);
		this.bonus = "   Ossigeno: "+Equipaggiamento.getLog().getOssigeno()+"    Risorse: "+Equipaggiamento.getLog().getRisorse()+"    Tempo: "+Equipaggiamento.getLog().getTempo();
		Controller.getLog().getControllerAstronauta().setParametri();
		this.view.UpdateEquipaggiamento(conversione(),Equipaggiamento.getLog().getTuta().getNome(),this.bonus , Equipaggiamento.getLog().getEquipaggio().getNome());
	}
}
