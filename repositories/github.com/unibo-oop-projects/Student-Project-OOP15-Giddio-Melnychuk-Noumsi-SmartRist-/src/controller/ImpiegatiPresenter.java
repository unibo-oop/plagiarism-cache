package controller;

import java.util.List;

import View.cardlayout.ViewImpiegati;
import View.cardlayout.ViewLogin;
import pro_ristorante_oop.Persona;
import pro_ristorante_oop.authentication.AuthenticationService;
import pro_ristorante_oop.persistence.FilePersistenceService;

public class ImpiegatiPresenter {
	private final MainWindowPresenter mainWindowPresenter;
	private ViewImpiegati view;
	private final FilePersistenceService model;
	private final List<Persona> pres;

	public ImpiegatiPresenter(MainWindowPresenter mainWindowPresenter, ViewImpiegati viewImpiegati,
			FilePersistenceService filePersistenceService) {
		// TODO Auto-generated constructor stub
		this.mainWindowPresenter = mainWindowPresenter;
		this.view = viewImpiegati;
		this.model = filePersistenceService;
		this.pres = this.model.readPersone();
	}

}
