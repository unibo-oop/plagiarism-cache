package view.umbrella;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import utils.Position;
import utils.constants.UmbrellaConstants;

/**
 * Bottone ombrellone mostra una dialog con le informazioni
 * @author Samuele Medici, samuele.medici2@studio.unibo.it ( Mat. 0000718877 )
 *
 */
public class UmbrellaButton extends JButton {

	private static final long serialVersionUID = -1201812958333291839L;

	private ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(UmbrellaConstants.UMBRELLA_ICON));

	// Rappresenta la posizione dell'ombrello ( univoca )
	private Position position;

	/**
	 * Costruttore per il bottone
	 * 
	 * @param row: fila a cui appartiene
	 * @param column: colonna a cui appartiene
	 * @param size: dimensione del ottone
	 */
	public UmbrellaButton(int row, int column, int size) {
		super();
		// Processo che permette il resize dell'immagine troppo grande
		Image img = this.imageIcon.getImage();

		// TODO: trovare dimensione massima
		Image newImage = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
		this.imageIcon = new ImageIcon(newImage);

		this.setIcon(this.imageIcon);

		this.position = new Position(row, column);

		this.addActionListener(e -> {
			this.openBookingPanel(e);
		});

	}

	/**
	 * Apre info dialog legato all'ombrellone
	 * @param e ActionEvent del listener
	 */
	private void openBookingPanel(ActionEvent e) {
		Component component = (Component) e.getSource();
		JFrame frame = (JFrame) SwingUtilities.getRoot(component);

		UmbrellaInfoDialog uid = new UmbrellaInfoDialog(frame, this.position);

		uid.setVisible(true);
	}

}
