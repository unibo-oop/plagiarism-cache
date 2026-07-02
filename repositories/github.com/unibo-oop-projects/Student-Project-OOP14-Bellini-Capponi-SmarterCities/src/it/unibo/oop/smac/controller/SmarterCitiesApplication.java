package it.unibo.oop.smac.controller;

import it.unibo.oop.smac.simulator.client.SightingSenderClient;
import it.unibo.oop.smac.view.stolencars.gui.ViewGUIStolenCars;
import it.unibo.oop.smac.view.stolencars.network.ViewNetworkStolenCars;

/**
 * Esempio di avvio dell'applicazione.
 * 
 * @author Federico Bellini
 * @author Francesco Capponi
 */
public final class SmarterCitiesApplication {

  /**
   * Numero di auto in circolazione nel tracciato.
   */
  private static final int N_CARS = 2;

  private SmarterCitiesApplication() {
  }

  /**
   * Main dell'applicazione. Avvia i servizi e l'interfaccia grafica.
   * 
   * @param varargs
   *          Parametri passati all'applicazione. NON utilizzati.
   * @throws InterruptedException
   *           Chiusura forzata dell'applicazione
   */
  public static void main(final String... varargs) throws InterruptedException {

    // creazione del controller che gestirà tutti gli eventi tra le parti
    final IStolenCarsController controller = new StolenCarsController(new Controller());

    // gli aggiungo la view Grafica (ViewGUI) con la possibilità di vedere anche le stolenCars
    controller.addView(new ViewGUIStolenCars());

    // creazione del server e dei client ad esso connessi
    controller.addView(new ViewNetworkStolenCars());

    for (int i = 0; i < N_CARS; i++) {
      Thread.sleep(1000); // garantisce un distacco tra le due auto
      new Thread(new SightingSenderClient()).start();
    }
  }
}
