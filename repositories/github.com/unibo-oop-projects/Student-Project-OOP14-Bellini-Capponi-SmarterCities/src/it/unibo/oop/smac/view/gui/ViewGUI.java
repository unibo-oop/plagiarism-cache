package it.unibo.oop.smac.view.gui;

import it.unibo.oop.smac.controller.IStreetObserverObserver;
import it.unibo.oop.smac.datatypes.IStreetObserver;
import it.unibo.oop.smac.view.IView;
import it.unibo.oop.smac.view.gui.locationpanel.ILocationPanel;
import it.unibo.oop.smac.view.gui.locationpanel.OpenStreetMapPanel;
import it.unibo.oop.smac.view.gui.mainpanel.IMainPanel;
import it.unibo.oop.smac.view.gui.mainpanel.MainPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * Implementazione dell'interfaccia IView con utilizzo di GUI. Questa classe e' implementata secondo
 * il pattern Observer(metodi attachObservers) ed anche secondo il pattern Facade, facendo da
 * "facciata" per i vari pannelli di cui si compone la GUI.
 * 
 * @author Federico Bellini
 */
public class ViewGUI extends JFrame implements IView {

  private static final long serialVersionUID = 6107931182231615768L;

  /**
   * Dimensioni iniziali della finestra.
   */
  private static final int DEFAULT_WIDTH = (Toolkit.getDefaultToolkit().getScreenSize().width / 3) * 2;
  private static final int DEFAULT_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().width / 2;

  /**
   * Icona della finestra.
   */
  private static final ImageIcon FRAME_ICON = new ImageIcon(
      ViewGUI.class.getResource("/images/smac-icon.png"));

  /**
   * Pannello principale dell'applicazione contenente le informazioni sugli osservatori.
   */
  private final IMainPanel mainPanel = new MainPanel();

  /**
   * Pannello contenente una mappa che descrive la posizione degli StreetObserver.
   */
  private final ILocationPanel locationPanel = new OpenStreetMapPanel();

  /**
   * Contenitore dei pannelli da visualizzare nell'interfacccia grafica
   */
  private final JTabbedPane tabbedPane = new JTabbedPane();

  /**
   * Costruttore pubblico della GUI.
   */
  public ViewGUI() {
    // creation of the frame
    super("SmarterCities");
    this.setLayout(new BorderLayout());
    this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setIconImage(FRAME_ICON.getImage());

    // creation of tabbedPanel

    this.addTab(" Informations ", mainPanel.getPanel());
    this.addTab(" Locations ", locationPanel.getPanel());

    this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    this.setVisible(true);
  }

  /**
   * Aggiunge una tab alla visuale grafica.
   * 
   * @param title
   *          Titolo che dovrà avere la tab
   * @param component
   *          Componente che dovrà essere formato dalla tab
   */
  protected void addTab(final String title, final Component component) {
    this.tabbedPane.addTab(title, component);
  }

  /**
   * Questo metodo mostra all'utente che un nuovo {@link IStreetObserver} e' appena stato connesso.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} appena connesso.
   */
  @Override
  public void addStreetObserver(final IStreetObserver streetObserver) {
    this.mainPanel.addStreetObserver(streetObserver);
    this.locationPanel.addStreetObserver(streetObserver);
  }

  /**
   * Questo metodo segnala all'utente che c'e' stato un passaggio sotto un'osservatore.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} che ha compiuto l'avvistamento.
   */
  @Override
  public void newSighting(final IStreetObserver streetObserver) {
    this.mainPanel.notifySighting(streetObserver);
    this.locationPanel.notifySighting(streetObserver);
  }

  /**
   * Questo metodo deve attaccare l'Observer passato come parametro agli StreetObserver presenti. In
   * questo modo e' l'Observer preso come paramentro che gestisce il comportamento dell'applicazione
   * quando vengono fatte delle richieste su degli StreetObserver da parte della View.
   * 
   * @param soo
   *          L'{@link IStreetObserverObserver} da attaccare agli StreetObserver presenti nella
   *          View.
   */
  @Override
  public void attachStreetObserverObserver(final IStreetObserverObserver soo) {
    this.mainPanel.attachStreetObserverObserver(soo);
  }

}
