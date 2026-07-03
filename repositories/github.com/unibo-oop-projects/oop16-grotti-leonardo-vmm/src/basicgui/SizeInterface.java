package basicgui;

import java.awt.Dimension;

public interface SizeInterface {

  /**
   * Ricevere la dimensione della % specificata dello schermo in larghezza.
   * @param perc è la percentuale voluta.
   * @return restituisce la dimensione.
   */
  int getSizeWidth(int perc);

  /**
   * Ricevere la dimensione della % specificata dello schermo in altezza.
   * @param perc è la percentuale voluta.
   * @return restituisce la dimensione.
   */
  int getSizeHeight(int perc);

  /**
   * Ricevere la dimensione della % specificata.
   * @param perc è la percentuale voluta.
   * @return restituisce la dimensione.
   */
  Dimension getDimension(int perc);

  /**
   * Ricevere la dimensione di un componente sapendo la dimensione del pannello.
   * 
   * @param component è il componente da dover ridimensionare.
   * @param windowPerc è la percentuale usata dal pannello.
   * @param dim è la dimensione desiderata dell'oggetto.
   * @return restituisce la dimensione dell'oggetto
   */
  Dimension getComponentDimension(int component, int windowPerc, Dimension dim);

}
