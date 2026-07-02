package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import View.cardlayout.ViewCameriere;
import View.cardlayout.ViewCuoco;
import View.cardlayout.ViewPrenotation;
import View.cardlayout.MainWindow;
import pro_ristorante_oop.Pair;
import pro_ristorante_oop.Persona;
import pro_ristorante_oop.Piatti;
import pro_ristorante_oop.persistence.FilePersistenceService;
import pro_ristorante_oop.persistence.PersistenceService;

public class CuocoPresenter implements ActionListener
{

	private final MainWindowPresenter mainWindowPresenter;
	private final ViewCuoco view;
	private final PersistenceService model;

	public CuocoPresenter(MainWindowPresenter mainWindowPresenter, ViewCuoco view, PersistenceService model)
	{
		this.mainWindowPresenter = mainWindowPresenter;
		this.model = model;
		this.view = view;
		this.view.addPrendeOrdineButtonListener(this);
		this.view.addCucinaButtonListener(this);
		this.view.addOrder(model.readOrdine());
	}

	public CuocoPresenter(MainWindowPresenter mainWindowPresenter, ViewCuoco view,
			Map<Pair<Integer, Integer>, List<Piatti>> ordine, PersistenceService model)
	{
		this.mainWindowPresenter = mainWindowPresenter;
		this.model = model;
		this.view = view;
		this.view.addPrendeOrdineButtonListener(this);
		this.view.addCucinaButtonListener(this);
		this.view.addOrder(ordine);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Which button has been clicked?
		if (e.getActionCommand().equals("Prende Ordine"))
		{
			System.out.println("I am here: Prende ordine");
			//@TODO Sascha: We take all of the list of cuoco and put it in anjother list under the first one
			
			String name = this.view.getName();
			String surname = this.view.getSurname();
			PersistenceService service = new FilePersistenceService();
			List<Persona> list = service.readPersone();

			/*for (Persona p : list)
			{
				if (surname.equals(p.getCognome()) && name.equals(p.getNome()))
				{
					
				}
			}*/
			this.view.setPiats2(this.view.getListPrende());
			this.view.clearList();

		} else if (e.getActionCommand().equals("Cucina"))
		{
			//@TODO Sascha: We take all the data from the secomd list and assign it to a cameriere
			System.out.println("I am here: Cucina");
			//By the following statement the cameriere view is displayed. If the cameriere view needs some data then the data needs to be passed
			//in the presentSubViewCameriere() method: e.g. this.mainWindowPresenter.presentSubViewCameriere(List of some data) like in the 
			//presentSubViewCuoco method of the MainWindowPresenter class 
			this.mainWindowPresenter.presentSubViewCameriere(this.view.getListPrende());
		}
	}

}
