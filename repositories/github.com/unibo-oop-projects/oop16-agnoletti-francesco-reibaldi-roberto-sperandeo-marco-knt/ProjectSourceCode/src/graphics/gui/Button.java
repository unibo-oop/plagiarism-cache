package graphics.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import knight.Game;

public class Button {

	public int x, y;
	public int width, height;
	public String label;

	public Button(int x, int y, int width, int height, String label) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.label = label;
	}

	/**
	 * Disegno il bottone e scrivo al suo interno in
	 * posizione centrale
	 *
	 * @param Graphics g
	 */
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Phosphate", Font.BOLD, 50));
		g.drawRect(getX(), getY(), getWidth(), getHeight());

		// scrivo nel centro del bottone
		FontMetrics fm = g.getFontMetrics();
		int stringX = (getWidth() - fm.stringWidth(getLabel())) / 2;
		int stringY = (fm.getAscent() + (getHeight() - (fm.getAscent() + fm
				.getDescent())) / 2);
		g.drawString(getLabel(), getX() + stringX, getY() + stringY);
	}

	/**
	 * A seconda del contenuto del bottone che clicco, esco o faccio partire il
	 * gioco.
	 *
	 */
	public void TriggerEvent() {
		if (getLabel().toLowerCase().contains("start")) {
			Game.nome = JOptionPane.showInputDialog("Inserisci il nome che verrà salvato nella classifica:");
			Game.playing = true;
		} else if (getLabel().toLowerCase().contains("ranking")) {
			Game.rankb = true;
		} else if (getLabel().toLowerCase().contains("info")) {
			Game.infob = true;
		} else if (getLabel().toLowerCase().contains("exit")) {
			Game.rank.write(Game.rank.m);
			System.exit(0);
		}
		if (getLabel().toLowerCase().contains("back")){
			Game.rankb = false;
			Game.infob = false;
		}
	}

	/**
	 * setta il valore di X
	 *
	 * @param X
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Setta il valore di Y
	 *
	 * @param Y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Setta la larghezza
	 *
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Setta l'altezza
	 *
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Setta la label
	 *
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * Restituisce il valore di x
	 *
	 * @return this.x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Restituisce il valore di Y
	 *
	 * @return this.y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Restituisce il valore di width
	 *
	 * @return this.width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Restituisce il valore di height
	 *
	 * @return this.height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Restituisce il valore di label
	 *
	 * @return this.label
	 */
	public String getLabel() {
		return label;
	}
}
