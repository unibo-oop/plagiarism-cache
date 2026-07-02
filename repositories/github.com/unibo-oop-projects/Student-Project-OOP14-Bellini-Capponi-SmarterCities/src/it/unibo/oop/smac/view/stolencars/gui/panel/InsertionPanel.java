package it.unibo.oop.smac.view.stolencars.gui.panel;

import it.unibo.oop.smac.controller.IStolenCarsObserver;
import it.unibo.oop.smac.datatypes.StolenCar;

import java.awt.FlowLayout;

import javax.management.InvalidAttributeValueException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Classe che implementa un pannello che permette di inserire nuove macchine rubate nella lista gia'
 * presente. Questa classe e' implementata secondo il pattern Observer.
 * 
 * @author Francesco Capponi
 */
public class InsertionPanel extends JPanel {

  private static final long serialVersionUID = -3093237633576185609L;

  /**
   * Observer delle auto rubate.
   */
  private IStolenCarsObserver stolenCarsObserver;

  /**
   * Label per l'inserimento delle targhe
   */
  private final JLabel labelPlate = new JLabel("Insert license plate:");

  /**
   * Text field per l'inserimento delle targhe.
   */
  private final JTextField fieldPlate = new JTextField(7);

  /**
   * Button per l'inserimento dell'auto rubata.
   */
  private final JButton addButton = new JButton("Add stolen car");

  /**
   * Costruttore pubblico della classe.
   */
  public InsertionPanel() {
    super();
    this.setBorder(new TitledBorder("Insert a new stolen car into the stolen cars list"));
    this.setLayout(new FlowLayout());

    this.add(this.labelPlate);
    this.add(this.fieldPlate);
    this.add(this.addButton);

    // inserimento di una nuova auto rubata quando viene premuto il pulsante addButton.
    addButton.addActionListener((e) -> {
      final String licensePlate = fieldPlate.getText();
      try {
        final StolenCar stolenCar = new StolenCar.Builder().licensePlate(fieldPlate.getText())
            .insertionDateNow().build();

        stolenCarsObserver.addNewStolenCar(stolenCar);
      } catch (InvalidAttributeValueException exception) {
        // nel caso in cui la targa non sia corretta, informo l'utente.
        final String msg = new StringBuilder()
            .append("Error inserting a new stolen car license plate: ")
            .append("\n " + licensePlate + " is not valid.")
            .append("\n The format AA000AA is a valid one.").toString();

        JOptionPane.showOptionDialog(null, msg, "Insertion Error", JOptionPane.CLOSED_OPTION,
            JOptionPane.INFORMATION_MESSAGE, null, null, null);
      }
    });
  }

  /**
   * Attacca l'Observer degli StolenCars.
   * 
   * @param sco
   *          L'{@link IStolenCarsObserver} da attaccare.
   */
  public void attachStolenCarsObserver(final IStolenCarsObserver sco) {
    this.stolenCarsObserver = sco;
  }

}
