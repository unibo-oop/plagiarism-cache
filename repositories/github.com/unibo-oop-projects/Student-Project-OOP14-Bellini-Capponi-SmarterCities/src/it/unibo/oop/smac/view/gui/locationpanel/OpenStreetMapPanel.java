package it.unibo.oop.smac.view.gui.locationpanel;

import it.unibo.oop.smac.datatypes.ICoordinates;
import it.unibo.oop.smac.datatypes.IStreetObserver;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementazione di una {@link JMapViewer}, che permette di visualizzare una mappa di
 * "Open Street Map".
 * 
 * @author Federico Bellini
 */
public class OpenStreetMapPanel extends JMapViewer implements ILocationPanel {

  private static final long serialVersionUID = -2763079963863167233L;

  /**
   * Logger della classe.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(OpenStreetMapPanel.class);

  /**
   * Tempo in millisecondi in cui l'icona di passaggio resta "accesa"
   */
  private static final int TIME_DELAY = 200;

  /**
   * Costruttore pubblico della classe.
   */
  public OpenStreetMapPanel() {
    this.setZoomButtonStyle(JMapViewer.ZOOM_BUTTON_STYLE.VERTICAL);
  }

  /**
   * Aggiunge un'{@link IStreetObserver} alla mappa.
   */
  @Override
  public void addStreetObserver(final IStreetObserver streetObserver) {
    this.addRedMapMarker(streetObserver.getId(), streetObserver.getCoordinates());
    SwingUtilities.invokeLater(() -> {
      this.setDisplayToFitMapMarkers();
    });
  }

  /**
   * Restituisce questo JPanel.
   * 
   * @return Il JPanel richiesto.
   */
  @Override
  public JPanel getPanel() {
    return this;
  }

  /**
   * Notifica visivamente sulla mappa l'avvenuto passaggio di un auto sotto di un
   * {@link IStreetObserver} modificando per qualche istante il colore del pin visualizzato, da
   * rosso a verde.
   */
  @Override
  public void notifySighting(final IStreetObserver streetObserver) {
    final String id = streetObserver.getId();
    final ICoordinates c = streetObserver.getCoordinates();

    removeMapMarker(id, c);
    addGreenMapMarker(id, c);
    try {
      Thread.sleep(TIME_DELAY);
    } catch (InterruptedException e) {
      LOGGER.error("Thread interrupted while sleeping. ", e);
    }
    removeMapMarker(id, c);
    addRedMapMarker(id, c);
  }

  /**
   * Aggiunge alla mappa un nuovo {@link AbstractMapMarker} di colore rosso.
   * 
   * @param id
   *          ID del {@link AbstractMapMarker}.
   * @param coordinates
   *          Le coodinate del {@link AbstractMapMarker}.
   */
  private void addRedMapMarker(final String id, final ICoordinates coordinates) {
    SwingUtilities.invokeLater(() -> this.addMapMarker(new RedMapMarker(id, coordinates)));
  }

  /**
   * Aggiunge alla mappa un nuovo {@link AbstractMapMarker} di colore verde.
   * 
   * @param id
   *          ID del {@link AbstractMapMarker}.
   * @param coordinates
   *          Le coodinate del {@link AbstractMapMarker}.
   */
  private void addGreenMapMarker(final String id, final ICoordinates coordinates) {
    SwingUtilities.invokeLater(() -> this.addMapMarker(new GreenMapMarker(id, coordinates)));
  }

  /**
   * Rimuove dalla mappa un {@link AbstractMapMarker} esistente.
   * 
   * @param id
   *          ID del {@link AbstractMapMarker}.
   * @param coordinates
   *          Le coodinate del {@link AbstractMapMarker}.
   */
  private void removeMapMarker(final String id, final ICoordinates coordinates) {
    /*
     * nota che non c'è differenza tra rimuovere un RedMapMarker o un GreenMapMarker, poichè quando
     * il metodo removeMapMarker richiama la funziona equals, e cerca il giusto MapMarker da
     * rimuovere, essa non fa differenza tra red o green, ma si basa esclusivamente sulla posizione
     * dei MapMarker per verificarne l'uguaglianza
     */
    SwingUtilities.invokeLater(() -> this.removeMapMarker(new RedMapMarker(id, coordinates)));
  }

}
