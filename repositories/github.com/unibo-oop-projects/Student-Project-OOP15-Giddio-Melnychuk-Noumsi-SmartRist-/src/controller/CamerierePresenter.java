package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import View.cardlayout.ViewCameriere;
import controller.MainWindowPresenter;
import pro_ristorante_oop.Pair;
import pro_ristorante_oop.Piatti;
import pro_ristorante_oop.persistence.FilePersistenceService;
import pro_ristorante_oop.persistence.PersistenceService;

public class CamerierePresenter implements ActionListener
{
	private MainWindowPresenter mainWindowPresenter;
	private ViewCameriere view;
	private PersistenceService model;
	private Map<Pair<Integer, Integer>, List<Piatti>> ordine;

	public CamerierePresenter(MainWindowPresenter mainWindowPresenter, ViewCameriere viewCameriere, String[] sarr, PersistenceService model)
	{
		this.mainWindowPresenter = mainWindowPresenter;
		this.view = viewCameriere;
		this.model = model;
		this.view.setMenuList(sarr);
		this.view.addButtonListener(this);
		this.ordine = model.readOrdine();
	}

	public CamerierePresenter(MainWindowPresenter mainWindowPresenter2, ViewCameriere cameriereView, Map<Pair<Integer, Integer>, List<Piatti>> orders,
			FilePersistenceService filePersistenceService) {
		// TODO Auto-generated constructor stub
		this.mainWindowPresenter = mainWindowPresenter;
		this.model = model;
		this.view = view;
		this.view.addButtonListener(this);
		this.ordine = ordine;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}

}
