package it.unibo.oop.smac.view.gui.mainpanel;

import it.unibo.oop.smac.datatypes.IInfoStreetObserver;
import it.unibo.oop.smac.datatypes.InfoStreetObserver;

import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * Classe che implementa un pannello che mostra le informazioni relative ad un osservatore.
 * 
 * @author Federico Bellini
 */
public final class InformationsPanel extends JPanel {

  private static final long serialVersionUID = -3058034776743041502L;
  private static final String NEW_LINE = System.lineSeparator();
  private static final String TAB = "          ";
  private static final String NON_PRESENT_VALUE = " - ";
  private static final int DECIMAL_PRECISION = 2;

  // textArea che mostrano i dati ottenuti dall'osservatore selezionato
  private final JTextArea streetObserverInfo = new JTextArea();
  private final JTextArea carRateInfo = new JTextArea();
  private final JTextArea sightInfo = new JTextArea();
  private final JTextArea speedInfo = new JTextArea();

  // informazioni correnti da mostrare
  private transient IInfoStreetObserver currentInfo;

  /**
   * Costruttore pubblico della classe.
   */
  public InformationsPanel() {
    super();
    this.setBorder(new TitledBorder("Informations"));
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // setto le informazioni inizialmente vuote
    currentInfo = new InfoStreetObserver.Builder().build();

    this.streetObserverInfo.setEditable(false);
    this.streetObserverInfo.setBackground(this.getBackground());

    this.carRateInfo.setEditable(false);
    this.carRateInfo.setBackground(this.getBackground());

    this.sightInfo.setEditable(false);
    this.sightInfo.setBackground(this.getBackground());

    this.speedInfo.setEditable(false);
    this.speedInfo.setBackground(this.getBackground());

    this.add(streetObserverInfo);
    this.add(carRateInfo);
    this.add(sightInfo);
    this.add(speedInfo);

    this.updateInfo();
  }

  /**
   * Questo metodo mostra a video le informazioni passate come parametro.
   * 
   * @param iso
   *          Le informazioni da visualizzare.
   */
  public void showInformations(final IInfoStreetObserver iso) {
    this.currentInfo = iso;
    this.updateInfo();
  }

  /**
   * Restituisce questo JPanel.
   * 
   * @return Il JPanel richiesto.
   */
  public JPanel getPanel() {
    return this;
  }

  /**
   * Questo metodo aggiorna tutte le informazioni contenute nel pannello.
   */
  protected void updateInfo() {
    showStreetObserverInfo();
    showCarRateInfo();
    showSightingInfo();
    showSpeedInfo();
  }

  /**
   * Metodo di utilita' che restituisce il valore dell'Optional preso come parametro se e' presente,
   * altrimenti restituisce la stringa NON_PRESENT_VALUE. Nel caso in cui l'Optional fosse presente
   * e la classe dell'oggetto in esso contenuto fosse Float, restituisce il Numero Float in esso
   * contenuto, ma con un numero DECIMAL_PRECISION di cifre significative.
   */
  private String stringOutputUtility(final Optional<?> o) {
    if (o.isPresent()) {
      final String out = o.get().toString();
      if (o.get().getClass().equals(Float.class)) {
        return out.substring(0, out.indexOf('.') + DECIMAL_PRECISION);
      }
      return out;
    } else {
      return NON_PRESENT_VALUE;
    }
  }

  /**
   * Mostra le informazioni sugli StreetObserver.
   */
  private void showStreetObserverInfo() {
    final String s = new StringBuilder()
        .append("   STREET OBSERVER\t\t\t")
        .append(NEW_LINE)
        .append(
            TAB + "Location: " + stringOutputUtility(this.currentInfo.getStreetObserverLocation()))
        .append(NEW_LINE)
        .append(TAB + "ID: " + stringOutputUtility(this.currentInfo.getStreetObserverID()))
        .toString();
    this.streetObserverInfo.setText(s);

  }

  /**
   * Mostra le informazioni sul car rate.
   */
  private void showCarRateInfo() {
    final String s = new StringBuilder()
        .append("   CAR RATE")
        .append(NEW_LINE)
        .append(
            TAB + "Max car rate (today in an hour): "
                + stringOutputUtility(this.currentInfo.getMaxCarRateToday())).toString();
    this.carRateInfo.setText(s);
  }

  /**
   * Mostra le informazioni sugli avvistamenti.
   */
  private void showSightingInfo() {
    final String s = new StringBuilder()
        .append("   SIGHT DATA")
        .append(NEW_LINE)
        .append(
            TAB + "Number of sight (last hour): "
                + stringOutputUtility(this.currentInfo.getnOfSightLastHour()))
        .append(NEW_LINE)
        .append(
            TAB + "Number of sight (today): "
                + stringOutputUtility(this.currentInfo.getnOfSightToday()))
        .append(NEW_LINE)
        .append(
            TAB + "Number of sight (last week): "
                + stringOutputUtility(this.currentInfo.getnOfSightLastWeek()))
        .append(NEW_LINE)
        .append(
            TAB + "Number of sight (last month): "
                + stringOutputUtility(this.currentInfo.getnOfSightLastMonth()))
        .append(NEW_LINE)
        .append(
            TAB + "Total number of sight: "
                + stringOutputUtility(this.currentInfo.getTotalNOfSight())).toString();
    this.sightInfo.setText(s);
  }

  /**
   * Mostra le informazioni sulle velocita' rilevate.
   */
  private void showSpeedInfo() {
    final String s = new StringBuilder()
        .append("   SPEED DATA")
        .append(NEW_LINE)
        .append(
            TAB + "Average speed limit (today): "
                + stringOutputUtility(this.currentInfo.getAverageSpeedToday()))
        .append(NEW_LINE)
        .append(
            TAB + "Average speed limit (last week): "
                + stringOutputUtility(this.currentInfo.getAverageSpeedLastWeek()))
        .append(NEW_LINE)
        .append(
            TAB + "Average speed limit (last month): "
                + stringOutputUtility(this.currentInfo.getAverageSpeedLastMonth()))
        .append(NEW_LINE)
        .append(
            TAB + "Max speed registered today is: "
                + stringOutputUtility(this.currentInfo.getMaxSpeedToday())).toString();
    this.speedInfo.setText(s);
  }

}
