package it.unibo.oop.smac.controller;

import it.unibo.oop.smac.database.model.DatabaseNotFoundException;
import it.unibo.oop.smac.database.model.StreetObserverModelDatabase;
import it.unibo.oop.smac.datatypes.IInfoStreetObserver;
import it.unibo.oop.smac.datatypes.ISighting;
import it.unibo.oop.smac.datatypes.IStreetObserver;
import it.unibo.oop.smac.datatypes.InfoStreetObserver;
import it.unibo.oop.smac.model.IStreetObserverModel;
import it.unibo.oop.smac.view.IView;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementazione del controller dell'applicazione. Tutta l'applicazione e' strutturata secondo il
 * pattern MVC. Questo controller funge anche da Observer per la parte della View.
 * 
 * @author Federico Bellini
 */
public class Controller implements IController {

  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

  /**
   * View dell'applicazione.
   */
  private final List<IView> views = new LinkedList<IView>();

  /**
   * Model dell'applicazione.
   */
  private final IStreetObserverModel model;

  /**
   * Observer che si metterà in ascolto degli eventi passati dalla GUI. Riceve un'
   * {@link IStreetObserver} del quale ricava le informazioni dal Model dell'applicazione. In caso
   * di qualche malfunzionamento del Model, restituisco un oggetto contenente nessuna informazione.
   */
  private final IStreetObserverObserver observer = new IStreetObserverObserver() {
    @Override
    public IInfoStreetObserver getStreetObserverInfo(final IStreetObserver streetObserver) {
      IInfoStreetObserver info;
      try {
        info = model.getStreetObserverInfo(streetObserver);
      } catch (IllegalArgumentException | DatabaseNotFoundException e) {
        LOGGER.error("Error in fetching data ", e);
        // in caso di malfunzionamenti restituisco un info vuota
        info = new InfoStreetObserver.Builder().build();
      }
      return info;
    }
  };

  /**
   * Costruttore pubblico della classe, senza prendere nessuna view di default. Si considera che
   * saranno aggiunte successivamente
   */
  public Controller() {
    model = StreetObserverModelDatabase.getInstance();
  }

  /**
   * Costruttore pubblico della classe. Come parametro prende l'oggetto che compone la View
   * dell'applicazione.
   * 
   * @param v
   *          L'oggetto che implementa la View dell'applicazione
   */
  public Controller(final IView v) {
    this();
    this.addView(v);
  }

  /**
   * Viene aggiunta una View all'applicazione.
   * 
   * @param v
   *          La View da aggiungere.
   */
  @Override
  public void addView(final IView v) {
    v.attachStreetObserverObserver(observer);
    this.getViews().add(v);
  }

  /**
   * Notifica che si e' verificato un nuovo passaggio sotto ad un osservatore.
   * 
   * @param streetObserver
   *          L'osservatore che ha compiuto l'avvistamento.
   * @param sighting
   *          Oggetto di tipo {@link ISighting} contenente le informazioni rilevate
   *          dall'osservatore.
   */
  @Override
  public void newSighting(final IStreetObserver streetObserver, final ISighting sighting) {
    LOGGER.info("Car {} has been seen", sighting.getLicensePlate());

    if (!this.model.checkStreetObserverExists(streetObserver)) {
      this.addStreetObserver(streetObserver);
    }
    try {
      model.addSighting(sighting);
      this.getViews().forEach((view) -> view.newSighting(streetObserver));
    } catch (Exception e) {
      LOGGER.error("Error in signaling a new sighting ", e);
    }
  }

  /**
   * Aggiunge un nuovo {@link IStreetObserver} all'applicazione.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} da aggiungere.
   */
  private void addStreetObserver(final IStreetObserver streetObserver) {
    LOGGER.info("Add street observer", streetObserver.getCoordinates());
    model.addNewStreetObserver(streetObserver);
    this.getViews().forEach((view) -> view.addStreetObserver(streetObserver));
  }

  /**
   * Restituisce il Model dell'applicazione.
   * 
   * @return Il {@link IStreetObserverModel} dell'applicazione.
   */
  protected IStreetObserverModel getModel() {
    return this.model;
  }

  /**
   * Restituisce le View associate all'applicazione.
   * 
   * @return Le Views attive nell'applicazione
   */
  @Override
  public List<IView> getViews() {
    return views;
  }

}
