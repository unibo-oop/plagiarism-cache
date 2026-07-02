package it.unibo.oop.smac.view.gui.locationpanel;

import it.unibo.oop.smac.datatypes.ICoordinates;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapObjectImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

/**
 * Questa classe astratta implementa la logica interna di un MapMarker personalizzato da utilizzare
 * in una {@link org.openstreetmap.gui.jmapviewer.JMapViewer}. Questa classe e le sue
 * implementazioni sono realizzate secondo il pattern Template Method.
 * 
 * @author Federico Bellini
 */
public abstract class AbstractMapMarker extends MapObjectImpl implements MapMarker {

  /**
   * Raggio del cerchio in cui il marker viene collocato.
   */
  private static final int RADIUS = 1;

  /**
   * Le coordinate di posizione del marker.
   */
  private final Coordinate coordinate;

  /**
   * Immagine sottostante al marker.
   */
  private final Image locationImage;

  /**
   * Costruttore pubblico.
   * 
   * @param id
   *          ID che si vuole impostare per il MapMarker.
   * @param coordinates
   *          Coordinate di posizione del MapMarker.
   */
  public AbstractMapMarker(final String id, final ICoordinates coordinates) {
    super(id);
    this.coordinate = new Coordinate(coordinates.getLatitude(), coordinates.getLongitude());
    this.locationImage = this.getImageForMarker();
  }

  /**
   * Questo metodo astratto deve restituire l'immagine che si vuole utilizzare come MapMarker.
   * L'implementazione di esso è lasciato alle sottoclassi, come definito dal pattern Template
   * Method.
   * 
   * @return L'Image che si vuole utilizzare come MapMarker.
   */
  protected abstract Image getImageForMarker();

  /**
   * Questa operazione non è supportata.
   * 
   * @throws UnsupportedOperationException
   *           Questa eccezione viene lanciata quando viene utilizzato questo metodo.
   */
  @Override
  public void setLat(final double arg0) {
    throw new UnsupportedOperationException("Method setLat is unsupported.");
  }

  /**
   * Questa operazione non è supportata.
   * 
   * @throws UnsupportedOperationException
   *           Questa eccezione viene lanciata quando viene utilizzato questo metodo.
   */
  @Override
  public void setLon(final double arg0) {
    throw new UnsupportedOperationException("Method setLon is unsupported.");
  }

  /**
   * Restituisce le coordinate del punto.
   * 
   * @return Le coordinate del punto.
   */
  @Override
  public Coordinate getCoordinate() {
    // safety copy
    return new Coordinate(this.coordinate.getLat(), this.coordinate.getLon());
  }

  /**
   * Restituisce la latitudine del punto.
   * 
   * @return La latitudine del punto.
   */
  @Override
  public double getLat() {
    return this.coordinate.getLat();
  }

  /**
   * Restituisce la longitudine del punto.
   * 
   * @return La longitudine del punto.
   */
  @Override
  public double getLon() {
    return this.coordinate.getLon();
  }

  /**
   * Restituisce lo stile del mapMarker.
   * 
   * @return Lo stile del mapMarker.
   */
  @Override
  public STYLE getMarkerStyle() {
    return STYLE.VARIABLE;
  }

  /**
   * Restituisce il raggio del marker.
   * 
   * @return Il raggio del marker.
   */
  @Override
  public double getRadius() {
    return RADIUS;
  }

  /**
   * Disegna sul {@link Graphics} l'immagine alla posizione p.
   * 
   * @param graphics
   *          Il {@link Graphics}
   * @param point
   *          Il {@link Point} dove disegnare l'immagine
   * @param radio
   *          Inutilizzato
   */
  @Override
  public void paint(final Graphics graphics, final Point point, final int radio) {
    graphics.drawImage(locationImage, (int) point.getX(), (int) point.getY(), null);
  }

  /**
   * Override della funzione hashCode della classe Object.
   */
  @Override
  public int hashCode() {
    final int prime = (1 << 3) - 1;
    int result = 1;
    result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
    return result;
  }

  /**
   * Override della funzione equals della classe Object. In questo caso due MyMapMarker vengono
   * ritenuti uguali se hanno stesse coordinate.
   */
  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof AbstractMapMarker) {
      return this.coordinate.getLat() == ((AbstractMapMarker) obj).getLat()
          && this.coordinate.getLon() == ((AbstractMapMarker) obj).getLon();
    }
    return false;
  }

}
