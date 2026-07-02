package it.unibo.infomanager.infomng.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;

import it.unibo.infomanager.infomng.view.ClientiGUI;
import it.unibo.infomanager.infomng.view.FattureGUI;
import it.unibo.infomanager.infomng.view.FornitoriGUI;
import it.unibo.infomanager.infomng.view.ScontriniGUI;

class ListOfObjectImpl<T> implements Navigator<T> {
	private Integer posizione;
	private List<T> oggetti;
	private JFrame view;
	
	protected ListOfObjectImpl(Collection<T> elementi, JFrame view){
		this.oggetti = new ArrayList<>();
		this.oggetti.addAll(elementi);
		this.posizione = -1;
		this.view = view;
		if(this.view.getClass().equals(ClientiGUI.class)){
			((ClientiGUI)this.view).setNavigator(this);
		}
		else if(this.view.getClass().equals(FornitoriGUI.class)){
			((FornitoriGUI)this.view).setNavigator(this);
		}
	}
	
	protected ListOfObjectImpl(Collection<T> elementi){
		this(elementi, null);
	}
	
	@Override
	public T avanti(){
		if(this.oggetti.isEmpty()){
			return null;
		}
		if(posizione < this.oggetti.size() - 1){
			this.posizione += 1;
		}
		
		T oggetto = this.oggetti.get(this.posizione);
		if(view != null){
			if(this.view.getClass().equals(ClientiGUI.class)){
				((ClientiGUI)this.view).setTextFields(oggetto);
			}
			else if(this.view.getClass().equals(FornitoriGUI.class)){
				((FornitoriGUI)this.view).setTextFields(oggetto);
			}
			else if(this.view.getClass().equals(FattureGUI.class)){
				((FattureGUI)this.view).setTextField(oggetto);
			}
			else if(this.view.getClass().equals(ScontriniGUI.class)){
				((ScontriniGUI)this.view).setTextField(oggetto);
			}
		}
		
		return oggetto;
	}
	
	@Override
	public T indietro(){
		if(this.oggetti.isEmpty()){
			return null;
		}
		if(this.posizione > 0){
			this.posizione -= 1;
		}
		T oggetto = this.oggetti.get(this.posizione);
		if(view != null){
			if(this.view.getClass().equals(ClientiGUI.class)){
				((ClientiGUI)this.view).setTextFields(oggetto);
			}
			else if(this.view.getClass().equals(FornitoriGUI.class)){
				((FornitoriGUI)this.view).setTextFields(oggetto);
			}
			else if(this.view.getClass().equals(FattureGUI.class)){
				((FattureGUI)this.view).setTextField(oggetto);
			}
			else if(this.view.getClass().equals(ScontriniGUI.class)){
				((ScontriniGUI)this.view).setTextField(oggetto);
			}
		}
		
		return oggetto;
	}
	
	@Override
	public Boolean isPrimo(){
		return this.posizione == 0;
	}
	
	@Override
	public Boolean isUltimo(){
		return this.posizione == (this.oggetti.size() - 1);
	}
}
