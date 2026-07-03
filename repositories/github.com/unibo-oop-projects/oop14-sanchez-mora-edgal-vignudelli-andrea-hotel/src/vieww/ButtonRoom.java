/*
 * Questa GUI serve a relizzare il "Bottone" che rappresenta la singola stanza, che si compone di un'immagine,
 * che cambia a seconda dello stato della camera, e da un numero, che identifia la singola stanza.
 *  
 * 
 */
package vieww;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.Room;

// TODO: Auto-generated Javadoc
/**
 * The Class ButtonRoom.
 */
public class ButtonRoom extends JButton implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6986392103580341061L;

	/**
	 * Instantiates a new button room.
	 *
	 * @param a the room number
	 * @param b the room state
	 * @param r the room instance
	 * @param ld the selected date
	 * @param rv the roomView
	 */
	ButtonRoom(final int a,final boolean b,final Room r,final LocalDate ld,final RoomView rv) {
		super();
		this.setBorder(null);
		this.setSize(new Dimension(50, 50));
		BufferedImage large = null;
		try {
			if (b == false) {
				large = ImageIO.read(new File("res/iconaroom.gif"));
			} else {
				large = ImageIO.read(new File("res/iconaroombusy.jpg"));
			}
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		BufferedImage combined = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		Graphics g = combined.getGraphics();
		g.drawImage(large, 0, 0, null);
		g.drawString(("" + a), 20, 25);
		ImageIcon icon1 = new ImageIcon(combined);
		this.setIcon(icon1);
		this.addActionListener(e -> {
			new RoomInformation(r, ld, rv);

		});

	}

}
