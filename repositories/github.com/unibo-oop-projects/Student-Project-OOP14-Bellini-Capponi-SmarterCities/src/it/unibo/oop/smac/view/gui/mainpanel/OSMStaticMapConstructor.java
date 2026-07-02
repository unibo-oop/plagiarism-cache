package it.unibo.oop.smac.view.gui.mainpanel;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Questa è una classe di utilita', e svolge il compito di costruire e restituire un'immagine
 * contenente una mappa del luogo di cui sono date le coordinate. Le Static Maps restituite vengono
 * prelevate dal servizio di "Open Street Map". La classe e' costruita secondo un pattern Singleton.
 * 
 * @author Federico Bellini
 */
public final class OSMStaticMapConstructor {
  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(OSMStaticMapConstructor.class);

  private static final String DEFAULT_ICON_PATH = "/images/somethingWrongHappened.png";
  private static OSMStaticMapConstructor staticMapConstructor;

  /**
   * Costruttore privato della classe.
   */
  private OSMStaticMapConstructor() {
  }

  /**
   * Metodo getInstace per restituire un'istanza di questo oggetto.
   * 
   * @return L'istanza di questo oggetto.
   */

  public static OSMStaticMapConstructor getInstance() {
    synchronized (OSMStaticMapConstructor.class) {
      if (staticMapConstructor == null) {
        staticMapConstructor = new OSMStaticMapConstructor();
      }
      return staticMapConstructor;
    }
  }

  /**
   * Metodo che restituisce un'immagine contenente una mappa del luogo di cui sono date le
   * coordinate. Le Static Maps restituite vengono prelevate dal servizio di "Open Street Map".
   * 
   * @param lat
   *          Latitudine del luogo da visualizzare.
   * @param lng
   *          Longitudine del luogo da visualizzare.
   * @return Un'{@link ImageIcon} contenente la mappa del luogo scelto.
   */
  public ImageIcon getStaticMap(final float lat, final float lng) {
    final URL url = OSMURLBuilder.getInstance().buildURL(lat, lng);
    try {
      return new ImageIcon(ImageIO.read(url));
    } catch (IOException e) {
      return new ImageIcon(OSMStaticMapConstructor.class.getResource(DEFAULT_ICON_PATH));
    }
  }

  /**
   * Classe di utilita': costruisce l'URL necessario per la richiesta della static map ad
   * "Open Street Map". La classe e' costruita secondo un pattern Singleton.
   * 
   * @author Federico Bellini
   */
  private static final class OSMURLBuilder {
    private static final int DEFAULT_ZOOM = 14;
    private static final String OSM_REQUEST_PREFIX = "http://staticmap.openstreetmap.de/staticmap.php?";
    private static final int DEFAULT_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / 4;
    private static final int DEFAULT_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height / 4;

    private static OSMURLBuilder instance;

    private OSMURLBuilder() {
    }

    public static OSMURLBuilder getInstance() {
      synchronized (OSMURLBuilder.class) {
        if (instance == null) {
          instance = new OSMURLBuilder();
        }
        return instance;
      }
    }

    /**
     * Questo metodo crea il corretto URL per la richiesta.
     * 
     * @param lat
     *          La latitudine del centro della mappa.
     * @param lng
     *          La longitudine del centro della mappa.
     * @return L'URL cercato.
     */
    public URL buildURL(final Float lat, final Float lng) {
      URL url = null;
      try {
        url = new URL(new StringBuilder().append(OSM_REQUEST_PREFIX)
            .append("center=" + lat + "," + lng).append("&zoom=" + DEFAULT_ZOOM)
            .append("&size=" + DEFAULT_WIDTH + "x" + DEFAULT_HEIGHT)
            .append("&markers=" + lat + "," + lng + ",red-pushpin").toString());
      } catch (Exception e) {
        LOGGER.error("Errore nella formattazione dell'URL", e);
      }
      return url;
    }

  }

}
