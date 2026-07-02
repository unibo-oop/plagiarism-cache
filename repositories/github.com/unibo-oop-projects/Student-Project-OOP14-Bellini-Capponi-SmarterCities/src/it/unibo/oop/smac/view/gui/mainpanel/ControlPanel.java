package it.unibo.oop.smac.view.gui.mainpanel;

import it.unibo.oop.smac.datatypes.IStreetObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

/**
 * Questa classe implementa un JScrollPane che deve mostrare una sequenza di
 * {@link StreetObserverPanel}.
 * 
 * @author Federico Bellini
 */
public class ControlPanel extends JScrollPane {

  private static final long serialVersionUID = -6541769613294971397L;

  // velocita' di scrolling del JScrollPane personalizzata
  private static final int CUSTOM_SCROLLING = 30;

  // panel contenente tutti gli StreetObserverPanel
  private final JPanel panel = new JPanel();

  /**
   * Map che ha come chiave l'id dello streetObserver, e come valore il riferimento al relativo
   * StreetObserverPanel. Questa memorizzazione e' necessaria per richiamare il metodo
   * displaySighting della classe StreetObserverPanel sul giusto pannello.
   */
  private final Map<String, StreetObserverPanel> streetObserversMap = new HashMap<>();

  /**
   * Costruttore pubblico della classe.
   */
  public ControlPanel() {
    super();
    this.setBorder(new TitledBorder("Observers"));
    this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
    this.getVerticalScrollBar().setUnitIncrement(CUSTOM_SCROLLING);
    this.getViewport().add(panel);
  }

  /**
   * Aggiunge uno streetObserver alla sequenza.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} da aggiungere.
   * @param consumer
   *          L'observer da attaccare al panel contenente l' {@link IStreetObserver} passato come
   *          primo paramentro.
   */
  public void addStreetObserver(final IStreetObserver streetObserver,
      final Consumer<IStreetObserver> consumer) {
    final StreetObserverPanel p = new StreetObserverPanel(streetObserver, consumer);
    SwingUtilities.invokeLater(() -> this.panel.add(p));
    this.streetObserversMap.put(streetObserver.getId(), p);
  }

  /**
   * Mostra che un'{@link IStreetObserver} ha rilevato dei dati.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} che ha rilevato i dati.
   */
  public void notifySighting(final IStreetObserver streetObserver) {
    if (this.streetObserversMap.containsKey(streetObserver.getId())) {
      final StreetObserverPanel p = this.streetObserversMap.get(streetObserver.getId());
      p.displaySighting();
    }
  }

}
