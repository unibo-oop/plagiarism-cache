package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import View.cardlayout.ViewScontrino;
import View.cardlayout.ViewMenu;
import View.cardlayout.MainWindow;
import View.cardlayout.ViewLogin;
import View.cardlayout.ViewPrenotation;
import View.cardlayout.ViewCuoco;
import View.cardlayout.ViewImpiegati;
import View.cardlayout.ViewCameriere;
import controller.CamerierePresenter;
import pro_ristorante_oop.Pair;
import pro_ristorante_oop.Persona;
import pro_ristorante_oop.Piatti;
import pro_ristorante_oop.authentication.TransientAuthenticationService;
import pro_ristorante_oop.persistence.FilePersistenceService;
import pro_ristorante_oop.persistence.PersistenceService;
import pro_ristorante_oop.persistence.TransientPersistenceService;

public class MainWindowPresenter implements ActionListener
{

	private final MainWindow mainView;
	private final PersistenceService model;
	private Integer tot;
	private String conto;

	public MainWindowPresenter(PersistenceService model)
	{
		this.model = model;
		this.mainView = new MainWindow();
		// initialize the sub view menu
		// Read the piatti and pass them to the menu so that the piatti will be
		// shown in the Menu
		List<Piatti> piatti = model.readPiatti();
		this.mainView.initializeViewMenu(piatti);
	}

	public void initializeMainWindowsControllerLayer()
	{
		this.mainView.initializeControllerLayer();
		this.mainView.getMenu().addActionListener(this);
		this.mainView.getCuoco().addActionListener(this);
		this.mainView.getCameriere().addActionListener(this);
		this.mainView.getScontrino().addActionListener(this);
	}

	public MainWindow getInitializedMainWindow()
	{
		return this.mainView;
	}

	public void presentSubView(String s)
	{
		switch (s)
		{
		case MainWindow.LOGINPANEL:
			ViewLogin loginView = (ViewLogin) this.mainView.getView(ViewLogin.getIndex());
			new LoginPresenter(this, loginView, new TransientAuthenticationService(new TransientPersistenceService()));
			break;
		case MainWindow.PRENOTATIONPANEL:
			ViewPrenotation prenotationView = (ViewPrenotation) this.mainView.getView(ViewPrenotation.getIndex());
			new PrenotationPresenter(this, prenotationView, new FilePersistenceService());
			this.mainView.show(MainWindow.PRENOTATIONPANEL);
			break;
		case MainWindow.CUOCOPANEL:
			ViewCuoco cuocoView = (ViewCuoco) this.mainView.getView(ViewCuoco.getIndex());
			new CuocoPresenter(this, cuocoView, new FilePersistenceService());
			this.mainView.show(MainWindow.CUOCOPANEL);
			break;
		case MainWindow.CAMERIEREPANEL:
			ViewCameriere viewCameriere = (ViewCameriere) this.mainView.getView(ViewCameriere.getIndex());
			new CamerierePresenter(this, viewCameriere, new String[] {}, new FilePersistenceService());
			this.mainView.show(MainWindow.CAMERIEREPANEL);
			break;
		case MainWindow.SCONTRINOPANEL:
			ViewScontrino viewScontrino = (ViewScontrino) this.mainView.getView(ViewScontrino.getIndex());
			new ScontrinoPresenter(this, viewScontrino, new FilePersistenceService());
			this.mainView.show(MainWindow.SCONTRINOPANEL);
			break;
		case MainWindow.IMPIEGATIPANEL:
			ViewImpiegati viewImpiegati = (ViewImpiegati) this.mainView.getView(ViewImpiegati.getIndex());
			new ImpiegatiPresenter(this, viewImpiegati, new FilePersistenceService());
			this.mainView.show(MainWindow.IMPIEGATIPANEL);
			break;
		default:
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("Menu"))
		{
			this.presentSubViewMenu();
		} else if (e.getActionCommand().equals("Cuoco"))
		{
			this.presentSubView(MainWindow.CUOCOPANEL);
		} else if (e.getActionCommand().equals("Cameriere"))
		{
			this.presentSubView(MainWindow.CAMERIEREPANEL);
		} else if (e.getActionCommand().equals("Scontrino"))
		{
			this.presentSubView(MainWindow.SCONTRINOPANEL);
		}
	}

	public void presentSubViewCuoco(Map<Pair<Integer, Integer>, List<Piatti>> orders)
	{
		ViewCuoco cuocoView = (ViewCuoco) this.mainView.getView(ViewCuoco.getIndex());
		new CuocoPresenter(this, cuocoView, orders, new FilePersistenceService());
		this.mainView.show(MainWindow.CUOCOPANEL);
	}

	public void presentSubViewMenu()
	{
		ViewMenu menuView = (ViewMenu) this.mainView.getView(ViewMenu.getIndex());
		this.mainView.show(MainWindow.MENUPANEL);
		new MenuPresenter(this, menuView, new FilePersistenceService());
	}

	public void presentSubViewCameriere(String[] menuList)
	{
		ViewCameriere cameriereView = (ViewCameriere) this.mainView.getView(ViewCameriere.getIndex());
		new CamerierePresenter(this, cameriereView, menuList, new FilePersistenceService());
		this.mainView.show(MainWindow.CAMERIEREPANEL);
	}

	public void presentSubViewScontrino( Double tot)
	{
		ViewScontrino viewScontrino = (ViewScontrino) this.mainView.getView(ViewScontrino.getIndex());
		new ScontrinoPresenter(this, viewScontrino, tot, new FilePersistenceService());
		this.mainView.show(MainWindow.SCONTRINOPANEL);
	}

	public void presentSubViewImpiegati(List<Persona> impiegati)
	{
		ViewImpiegati viewImpiegati = (ViewImpiegati) this.mainView.getView(ViewScontrino.getIndex());
		new ImpiegatiPresenter(this, viewImpiegati, new FilePersistenceService());
		this.mainView.show(MainWindow.IMPIEGATIPANEL);
	}

}
