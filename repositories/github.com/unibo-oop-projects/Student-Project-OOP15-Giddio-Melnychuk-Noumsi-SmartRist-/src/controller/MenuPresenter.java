package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import View.ViewCuoco;
import View.cardlayout.ViewMenu;
import View.cardlayout.MainWindow;
import View.cardlayout.ViewPrenotation;
import pro_ristorante_oop.Pair;
import pro_ristorante_oop.Piatti;
import pro_ristorante_oop.TypePlate;
import pro_ristorante_oop.persistence.FilePersistenceService;
import pro_ristorante_oop.persistence.PersistenceService;

public class MenuPresenter implements ActionListener
{

	private final MainWindowPresenter mainWindowPresenter;
	private final ViewMenu view;
	private final PersistenceService model;

	public MenuPresenter(MainWindowPresenter mainWindowPresenter, ViewMenu view, PersistenceService model)
	{
		this.mainWindowPresenter = mainWindowPresenter;
		this.view = view;
		this.model = model;
		this.view.addButtonListener(this);
	}

	/**
	 * Whenn the ordina button is pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Map<Pair<Integer,Integer>,List<Piatti>> selectedOrder = this.getSelectedOrder();
		model.saveOrder(selectedOrder);
		this.mainWindowPresenter.presentSubViewCuoco(selectedOrder);
	}
	
	private Map<Pair<Integer,Integer>,List<Piatti>> getSelectedOrder()
	{
		String[] piat = this.view.getPiats();
		/**
		 * The cuoco view needs the data from the menu. We decide to save the data into the database as a new order and then the
		 * Cuoco view reads the new order from the the database.
		 * 
		 * Another solution would be to pass the data directly to the cuoco view but then we need 
		 * a reference to the cuoco view.
		 */
		List<Piatti> selectedPiattis = new LinkedList<Piatti>();
		Map<Pair<Integer,Integer>,List<Piatti>> orders = this.model.readOrdine();
		
		for (String s: piat)
		{
			List<Piatti> pi = this.model.readPiatti();
			for (int j=0;j<pi.size();j++ )
			{
				if (pi.get(j).getname().equals(s))
				{
					selectedPiattis.add(new Piatti(pi.get(j).getname(),pi.get(j).getcost(),pi.get(j).getID(),pi.get(j).getType()));
				}	
			}
		}
		int j=0;
		Map<Pair<Integer,Integer>,List<Piatti>> selectedOrder = new HashMap<>();
	
			do{
				
				if(!orders.containsKey(new Pair<Integer,Integer> (Integer.valueOf(this.view.getTav()),j))){
				selectedOrder.put(new Pair<Integer,Integer> (Integer.valueOf(this.view.getTav()),j),selectedPiattis);
				break;
				}
				j++;
			}while(j<orders.size());
		return selectedOrder;
	}

	

}
