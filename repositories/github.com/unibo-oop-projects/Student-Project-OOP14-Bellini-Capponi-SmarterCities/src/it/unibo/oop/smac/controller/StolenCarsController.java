package it.unibo.oop.smac.controller;

import it.unibo.oop.smac.database.model.StolenCarModelDatabase;
import it.unibo.oop.smac.datatypes.ISighting;
import it.unibo.oop.smac.datatypes.IStolenCar;
import it.unibo.oop.smac.datatypes.IStreetObserver;
import it.unibo.oop.smac.datatypes.StolenCar;
import it.unibo.oop.smac.model.IStolenCarModel;
import it.unibo.oop.smac.view.stolencars.IViewStolenCars;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione del Controller dell'applicazione, con l'aggiunta dei metodi necessari per la
 * gestione delle auto rubate. Questo controller funge anche da Observer per la parte della View.
 * 
 * @author Francesco Capponi
 */
public class StolenCarsController extends AbstractControllerDecorator implements
    IStolenCarsController {

  /**
   * Model dell'applicazione.
   */
  private final IStolenCarModel modelStolenCars;

  /**
   * Observer che si metterà in ascolto degli eventi passati dalla GUI
   */
  private final IStolenCarsObserver observer = new IStolenCarsObserver() {
    /**
     * Restituisce la lista dele stolen cars.
     * 
     * @return lista delle macchine rubate
     */
    @Override
    public List<IStolenCar> getStolenCarsInfoList() {
      return modelStolenCars.getStolenCarsInfoList();
    }

    /**
     * Aggiunge una nuova stolen car al model.
     * 
     * @param stolenCar
     *          L'auto rubata
     */
    @Override
    public void addNewStolenCar(final StolenCar stolenCar) {
      modelStolenCars.addNewStolenCar(stolenCar);
    }

    /**
     * Avvisa il controller che c'è stato un nuovo sighting
     * 
     * @param streetObserver
     *          streetObserver che effettua l'avvistamento
     * @param sighting
     *          sighting da aggiungere
     */
    @Override
    public void newSighting(final IStreetObserver streetObserver, final ISighting sighting) {
      StolenCarsController.this.newSighting(streetObserver, sighting);
    }
  };

  /**
   * Costruttore pubblico della classe. Presume che le View saranno aggiunte successivamente.
   * 
   * @param controller
   *          Il Controller di base dell'applicazione.
   */
  public StolenCarsController(final IController controller) {
    super(controller);
    this.modelStolenCars = StolenCarModelDatabase.getInstance();
  }

  /**
   * Viene aggiunta una View all'applicazione.
   * 
   * @param v
   *          La View da aggiungere
   */
  @Override
  public void addView(final IViewStolenCars v) {
    super.addView(v);
    v.attachStolenCarsObserver(observer);
  }

  /**
   * Aggiunge un pasaggio al model, e notifica la view. Controlla inoltre se la macchina è stata
   * rubata e agisce di conseguenza.
   * 
   * @param streetObserver
   *          streetObserver che effettua l'avvistamento
   * @param sighting
   *          sighting da aggiungere
   */
  @Override
  public void newSighting(final IStreetObserver streetObserver, final ISighting sighting) {
    super.newSighting(streetObserver, sighting);

    this.getViewsStolenCars().forEach((view) -> view.showLastSighting(sighting));
    // controllo che non si tratti di un'auto rubata
    if (modelStolenCars.checkStolenPlate(sighting.getLicensePlate())) {
      this.getViewsStolenCars().forEach((view) -> view.newSightingStolenCar(sighting));

    }
  }

  /**
   * Restituisce il Model dell'applicazione.
   * 
   * @return Il {@link IStolenCarModel} dell'applicazione.
   */
  protected IStolenCarModel getModel() {
    return this.modelStolenCars;
  }

  /**
   * Restituisce le view associate all'applicazione.
   * 
   * @return Le Views attive nell'applicazione
   */
  @Override
  public List<IViewStolenCars> getViewsStolenCars() {
    final List<IViewStolenCars> list = new ArrayList<IViewStolenCars>();
    super.getViews().forEach((view) -> {
      if (view instanceof IViewStolenCars) {
        list.add((IViewStolenCars) view);
      }
    });
    return list;
  }
}
