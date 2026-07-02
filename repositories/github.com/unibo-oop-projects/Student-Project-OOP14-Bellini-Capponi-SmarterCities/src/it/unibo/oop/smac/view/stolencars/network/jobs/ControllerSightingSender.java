package it.unibo.oop.smac.view.stolencars.network.jobs;

import it.unibo.oop.smac.datatypes.ISighting;
import it.unibo.oop.smac.datatypes.LicensePlate;
import it.unibo.oop.smac.datatypes.Sighting;
import it.unibo.oop.smac.datatypes.StreetObserver;
import it.unibo.oop.smac.view.stolencars.network.Dispatcher;
import it.unibo.oop.smac.view.stolencars.network.PlainSighting;

import java.util.Observable;
import java.util.Observer;

import javax.management.InvalidAttributeValueException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe implementata con il pattern Observer che alla ricezione da parte di un client di un
 * messaggio di sighting, notifica il controller.
 * 
 * @author Francesco Capponi
 */
public class ControllerSightingSender implements Observer {

  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ControllerSightingSender.class);

  @Override
  public void update(final Observable observable, final Object arg) {
    // controllo che sia stato attachato a un dispatcher valido
    if (!(observable instanceof Dispatcher)) {
      throw new IllegalArgumentException();
    }

    // controllo che il messaggio sia un sighting
    if (arg instanceof PlainSighting) {
      final PlainSighting netSighting = (PlainSighting) arg;
      StreetObserver streetObserver = null;
      try {
        streetObserver = new StreetObserver(netSighting.getCoordinates());
      } catch (InvalidAttributeValueException e) {
        LOGGER.error("Creation failed: invalid coordinates.", e);
      }

      ISighting sighting = null;
      try {
        // provo a generare il sighting
        sighting = new Sighting.Builder().date(netSighting.getDate())
            .streetObserver(streetObserver).speed(netSighting.getSpeed())
            .licensePlate(new LicensePlate(netSighting.getLicensePlate())).build();
        final Dispatcher dispatcher = (Dispatcher) observable;

        // lo spedisco al controller che ne farà il giusto dispatch alla view
        dispatcher.getStolenCarsObserver().newSighting(streetObserver, sighting);
      } catch (InvalidAttributeValueException e) {
        // Targa non valida, interrompo la notifica
        LOGGER.error("Invalid license plate ({}) from a client", netSighting.getLicensePlate(), e);
      }

    }
  }
}
