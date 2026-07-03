package graphics.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import knight.Game;

public class Rank implements Serializable {
	
	private static final long serialVersionUID = 7526472295622776147L;

	public static final String FILE_NAME = "rank.txt";

	public Button button;
	private BufferedImage image;

	public TreeMap<Integer, String> m = new TreeMap();

	public Rank() throws IOException {
		button = new Button(700, 600, 300, 100, "Back");
		read();

		try {
			image = ImageIO.read(getClass().getResource("/launcher.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Disegno la schermata classifica per visualizzare i punteggi dei giocatori
	 *
	 * @param Graphics g
	 */
	public void render(Graphics g) {
		g.drawImage(image, 0, 0, Game.getFrameWidth(), Game.getFrameHEIGHT(), null);
		g.setColor(Color.white);
		g.setFont(new Font("courier", Font.BOLD, 25));
		g.drawString(m.values().toString(), 100, 100);
		g.drawString(m.keySet().toString(), 100, 200);

		button.render(g);
	}

	/**
	 * Scrivo sul file per salvare i punteggi dei vari giocatori
	 *
	 * @param TreeMap<Integer, String> map
	 */
	public void write(TreeMap<Integer, String> map) {
		// scrivo su file
		try {
			File f = new File(FILE_NAME);
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(map);
			oos.flush();
			oos.close();
			fos.close();
		} catch (Exception e) {
		}
	}

	/**
	 * Leggo il file in cui sono salvati i punteggi dei giocatori
	 *
	 */
	@SuppressWarnings("unchecked")
	public void read() {
		// read from file
		TreeMap<Integer, String> mapInFile = new TreeMap<Integer, String>();

		try {
			File f = new File(FILE_NAME);
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);

			mapInFile = (TreeMap<Integer, String>) ois.readObject();

			ois.close();
			fis.close();

		} catch (Exception e) {
		}
		m.putAll(mapInFile);
	}

	public void add(Integer i, String s) {
		m.put(i, s);
	}

}