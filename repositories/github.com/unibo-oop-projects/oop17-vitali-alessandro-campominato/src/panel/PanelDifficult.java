package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import interfaces.PanelDifficultInterface;
import other.Difficult;

/**
 * classe che gestice la il panel per impostare la difficoltà
 * 
 * @author Alessandro
 *
 */
public class PanelDifficult extends JPanel implements ActionListener, PanelDifficultInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Difficult difficult;
	
	private JButton buttonEasy = new JButton();
	private JButton buttonMedium = new JButton();
	private JButton buttonHard = new JButton();
	private JButton buttonCustom = new JButton();
	private JButton buttonConfirm = new JButton();
	private JButton buttonBack = new JButton();
	
	private JTextField rows = new JTextField();
	private JTextField columns = new JTextField();
	private JTextField bombs = new JTextField();
	
	private JLabel labelRows = new JLabel();
	private JLabel labelColumns = new JLabel();
	private JLabel labelBombs = new JLabel();
	
	private JLabel labelMessage = new JLabel();	
	
	/**
	 * costruttore che imposta le dimensioni del panel e riceve il riferimento difficult per cambiare la difficoltà
	 * 
	 * @param difficult
	 *     riferimento per cambiare la difficoltà
	 * @param xSize
	 *     larghezza del panel
	 * @param ySize
	 *     altezza del panel
	 */
	public PanelDifficult(Difficult difficult, int xSize, int ySize) {
		
		this.difficult = difficult;
		
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(xSize,ySize));
		this.setBackground(Color.decode(BoardGameView.theme.getThirdColor()));
		
		this.createView();
	}
	
	/**
	 * crea la view del panel
	 */
	private void createView() {
		GridBagConstraints gb = new GridBagConstraints();
		
		this.buttonEasy.setText("Facile");
		gb.gridx = 0;
		gb.gridy = 0;
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.insets = new Insets(20, 20, 20, 20);
		this.buttonEasy.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.buttonEasy.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonEasy.setFocusPainted(false);
		this.buttonEasy.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonEasy.setPreferredSize(new Dimension(120, 50));
		this.buttonEasy.addActionListener(this);
		this.add(this.buttonEasy, gb);
		
		this.buttonMedium.setText("Intermedio");
		gb.gridx = 1;
		gb.gridy = 0;
		this.buttonMedium.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.buttonMedium.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonMedium.setFocusPainted(false);
		this.buttonMedium.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonMedium.setPreferredSize(new Dimension(120, 50));
		this.buttonMedium.addActionListener(this);
		this.add(this.buttonMedium, gb);
		
		this.buttonHard.setText("Diffcile");
		gb.gridx = 0;
		gb.gridy = 1;
		this.buttonHard.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.buttonHard.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonHard.setFocusPainted(false);
		this.buttonHard.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonHard.setPreferredSize(new Dimension(120, 50));
		this.buttonHard.addActionListener(this);
		this.add(this.buttonHard, gb);
		
		this.buttonCustom.setText("Custom");
		gb.gridx = 1;
		gb.gridy = 1;
		this.buttonCustom.setPreferredSize(new Dimension(120,50));
		this.buttonCustom.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.buttonCustom.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonCustom.setFocusPainted(false);
		this.buttonCustom.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonCustom.addActionListener(this);
		this.add(this.buttonCustom, gb);
		
	}

	/**
	 * gestione del click sui vari bottoni, se viene cliccato buttonCustom la view viene aggiornata
	 * viene fatto un controllo anche sulle righe max, colonne max e bombe max
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.buttonEasy) {
		  this.difficult.setDifficultEasy();
			JOptionPane.showMessageDialog(null, "Difficolta facile selezionata");
		} else if(e.getSource() == this.buttonMedium) {
		  this.difficult.setDifficultMedium();
			JOptionPane.showMessageDialog(null, "Difficolta media selezionata");
		} else if(e.getSource() == this.buttonHard){
		  this.difficult.setDifficultHard();
			JOptionPane.showMessageDialog(null, "Difficolta difficile selezionata");
		} else if(e.getSource() == this.buttonCustom) {
		  this.createTextFieldCustom();
		} else if(e.getSource() == this.buttonConfirm) {
			try{
				int r = Integer.parseInt(rows.getText());
				int c = Integer.parseInt(columns.getText());
				int b = Integer.parseInt(bombs.getText());
				if(r < 1 || c < 1 || r > 25 || c > 25) {
					this.labelMessage.setText("Min-1 Max-25");
				} else {
					if(b < 1 || b >= r*c) {
						this.labelMessage.setText("Bombe non valide");
					} else {
					  this.difficult.setDifficultCustom(r, c, b);
						this.removeAll();
						this.updateUI();
						this.createView();
					}
				}
			} catch(NumberFormatException ex) {
				this.labelMessage.setText("Valore non valido");
			}
		} else if(e.getSource() == this.buttonBack) {
			this.removeAll();
			this.updateUI();
			this.createView();
		}
	}
	
	/**
	 * viene creata la view per permettere di inserire i valori per creare una board personalizzata
	 */
	private void createTextFieldCustom() {
		this.removeAll();
		this.updateUI(); 
		
		this.rows.setText("0");
		this.columns.setText("0");
		this.bombs.setText("0");

		GridBagConstraints gb = new GridBagConstraints();
		
		this.labelRows.setText("Righe");
		gb.gridx = 0;
		gb.gridy = 0;
		gb.insets = new Insets(10, 10, 10, 10);
		this.labelRows.setFont(new Font("Arial", Font.BOLD, 18));
		this.add(this.labelRows, gb);
		
		this.labelColumns.setText("Colonne");
		gb.gridx = 1;
		gb.gridy = 0;
		this.labelColumns.setFont(new Font("Arial", Font.BOLD, 18));
		this.add(this.labelColumns, gb);
		
		this.labelBombs.setText("Bombe");
		gb.gridx = 2;
		gb.gridy = 0;
		this.labelBombs.setFont(new Font("Arial", Font.BOLD, 18));
		this.add(this.labelBombs, gb);
		
		this.rows.setText("1-25");
		gb.gridx = 0;
		gb.gridy = 1;
		this.rows.setPreferredSize(new Dimension(120,50));
		this.rows.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.rows.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.rows.setPreferredSize(new Dimension(50,50));
		this.rows.setFont(new Font("Arial", Font.BOLD, 18));
		this.add(this.rows, gb);
		
		this.columns.setText("1-25");
		gb.gridx = 1;
		gb.gridy = 1;
		this.columns.setPreferredSize(new Dimension(120,50));
		this.columns.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.columns.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.columns.setPreferredSize(new Dimension(50,50));
		this.columns.setFont(new Font("Arial", Font.BOLD, 18));
		this.add(this.columns, gb);
		
		gb.gridx = 2;
		gb.gridy = 1;
		this.bombs.setPreferredSize(new Dimension(120,50));
		this.bombs.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.bombs.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.bombs.setPreferredSize(new Dimension(50,50));
		this.bombs.setFont(new Font("Arial", Font.BOLD, 18));
		this.add(this.bombs, gb);
		
		this.labelMessage.setForeground(Color.RED);
		gb.gridx = 1;
		gb.gridy = 2;
		this.labelMessage.setPreferredSize(new Dimension(100,50));
		this.add(this.labelMessage, gb);
		
		this.buttonConfirm.setText("Conferma");
		gb.gridx = 1;
		gb.gridy = 3;
		gb.gridwidth = 1;
		gb.insets = new Insets(10, 0, 10, 0);
		this.buttonConfirm.setPreferredSize(new Dimension(120,50));
		this.buttonConfirm.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.buttonConfirm.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonConfirm.setFocusPainted(false);
		this.buttonConfirm.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonConfirm.addActionListener(this);
		this.add(this.buttonConfirm, gb);
		
		this.buttonBack.setText("Indietro");
		gb.gridx = 1;
		gb.gridy = 4;
		gb.gridwidth = 1;
		this.buttonBack.setPreferredSize(new Dimension(120,50));
		this.buttonBack.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.buttonBack.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonBack.setFocusPainted(false);
		this.buttonBack.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonBack.addActionListener(this);
		this.add(this.buttonBack, gb);
		
		if(BoardGameView.theme.getActuallyTheme() == "Black") {
		  this.labelRows.setForeground(Color.WHITE);
		  this.labelColumns.setForeground(Color.WHITE);
		  this.labelBombs.setForeground(Color.WHITE);
		} else {
		  this.labelRows.setForeground(Color.DARK_GRAY);
		  this.labelColumns.setForeground(Color.DARK_GRAY);
		  this.labelBombs.setForeground(Color.DARK_GRAY);
		}
	}
	
	/**
	 * funzione che aggiorna la view in caso di modifica del tema
	 */
	public void updateThemeView() {
		this.setBackground(Color.decode(BoardGameView.theme.getThirdColor()));
		
		this.buttonEasy.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonEasy.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		this.buttonMedium.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonMedium.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		this.buttonHard.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonHard.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		this.buttonCustom.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonCustom.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		this.buttonConfirm.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonConfirm.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		this.buttonBack.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonBack.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		this.rows.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.rows.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		this.columns.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.columns.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		this.bombs.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.bombs.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		if(BoardGameView.theme.getActuallyTheme() == "Black") {
		  this.labelRows.setForeground(Color.WHITE);
		  this.labelColumns.setForeground(Color.WHITE);
		  this.labelBombs.setForeground(Color.WHITE);
		} else {
		  this.labelRows.setForeground(Color.DARK_GRAY);
		  this.labelColumns.setForeground(Color.DARK_GRAY);
		  this.labelBombs.setForeground(Color.DARK_GRAY);
		}


		
	}


	
}
