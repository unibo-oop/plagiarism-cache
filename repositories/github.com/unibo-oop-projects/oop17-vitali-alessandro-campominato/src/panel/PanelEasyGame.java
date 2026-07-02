package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import interfaces.PanelEasyGameInterface;
import other.Difficult;
import other.MouseEventClick;

/**
 * classe che gestisce la Board di gioco a livello grafico
 * 
 * @author Alessandro
 *
 */
public class PanelEasyGame extends JPanel implements PanelEasyGameInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int rows;
	private int columns;
	
	private int xSize;
	private int ySize;
	
	private JButton[][] buttons;
	
	/**
	 * costruttore che imposta la board
	 * 
	 * @param difficult
	 *     la difficoltà da cui prendere i valori per creare la tabella
	 * @param xSize
	 *     la larghezza del panel
	 * @param ySize
	 *     l'altezza del panel
	 */
	public PanelEasyGame(Difficult difficult, int xSize, int ySize) {
		
		this.xSize = xSize - 20;
		this.ySize = ySize - 20;
		
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.decode(BoardGameView.theme.getThirdColor()));
		this.setPreferredSize(new Dimension(xSize, ySize));
		this.rows = difficult.getRows();
		this.columns = difficult.getColumns();
		
		this.createBoardTheme();
	}
	
	/**
	 * crea la tabella con i bottoni, in base al rapporto xSize/colonne e ySize/righe sceglie il minore per dare la dimensione
	 * ai bottoni
	 */
	private void createBoardTheme() {
	  this.buttons = new JButton[this.rows][this.columns];
		GridBagConstraints c = new GridBagConstraints();

		int ry = this.ySize / this.rows;
		int rx = this.xSize / this.columns;
		int y = 0;
		if(ry < rx) {
			int x = this.rows * 2;
			y = (this.ySize - x) / this.rows;
		} else {
			int x = this.columns * 2;
			y = (this.xSize - x) / this.columns;
		}
		
		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.columns; j++) {
			  this.buttons[i][j] = new JButton();
			  this.buttons[i][j].setForeground(Color.DARK_GRAY);
			  this.buttons[i][j].setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
			  this.buttons[i][j].setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
			  this.buttons[i][j].setFont(new Font("Arial", Font.BOLD, 22));
			  this.buttons[i][j].setFocusPainted(false);
			  this.buttons[i][j].setPreferredSize(new Dimension(y,y));
			  this.buttons[i][j].addMouseListener(new MouseEventClick(this.buttons[i][j]));
				c.gridx = j;
				c.gridy = i;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(1,1,1,1);
				this.add(this.buttons[i][j], c);
			}
		}
	}
	
	/**
	 * @return il riferimento ai bottoni
	 */
	public JButton[][] getButtons() {
		return this.buttons;
	}

}
