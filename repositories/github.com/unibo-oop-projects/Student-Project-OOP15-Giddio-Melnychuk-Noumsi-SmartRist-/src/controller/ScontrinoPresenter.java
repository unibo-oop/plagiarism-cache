package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;


import View.cardlayout.ViewScontrino;
import pro_ristorante_oop.Pair;
import pro_ristorante_oop.Piatti;
import pro_ristorante_oop.Scontrino;
import pro_ristorante_oop.persistence.FilePersistenceService;
import pro_ristorante_oop.persistence.PersistenceService;

public class ScontrinoPresenter implements ActionListener {
	private final MainWindowPresenter mainWindowPresenter;
	private final ViewScontrino view;
	private final PersistenceService model;
	private final Map<Pair<Integer,Integer>,List<Piatti>> orders;
	List<Piatti> ord;
	private Scontrino o ;
	private String bill;
	private Integer tot;
	
	public ScontrinoPresenter(MainWindowPresenter mainWindowPresenter, ViewScontrino view, PersistenceService model){
		this.mainWindowPresenter = mainWindowPresenter;
		this.view = view;
		this.view.addButtonListener(this);
		this.model = model;
		this.orders = model.readOrdine();
	}
	
	public ScontrinoPresenter(MainWindowPresenter mainWindowPresenter, ViewScontrino view, 
			Double tot, FilePersistenceService model) {
		// TODO Auto-generated constructor stub
		this.mainWindowPresenter = mainWindowPresenter;
		this.view = view;
		this.view.addButtonListener(this);
		this.model = model;
		this.orders = model.readOrdine();
		this.view.SetTot(tot);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int j = 0;
		Integer tav = this.view.getTav();
		for(int i = 0;i<orders.size();i++){
			if(orders.containsKey(new Pair<Integer,Integer>(tav,i))){
				Pair<Integer, Integer> key = new Pair <Integer,Integer>(tav,j);
				ord = orders.get(key);
				this.o = new Scontrino();
				this.o.setTot(ord, null);
				j++;
			}
			else{
				j=0;
			}
		}
		this.mainWindowPresenter.presentSubViewScontrino( o.getTot());
	}

}
