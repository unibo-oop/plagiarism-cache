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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import other.Main;

/**
 * classe che gestice il panel dove � possibile selezionare il tema
 * 
 * @author Alessandro
 *
 */
public class PanelTheme extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton buttonStandard = new JButton();
	private JButton buttonWhite = new JButton();
	private JButton buttonBlack = new JButton();

	/**
	 * costruttore che imposta il panel
	 * 
	 * @param xSize
	 *     la larghezza che dovr� avere il panel
	 * @param ySize
	 *     l'altezza che dovr� avere il panel
	 */
	public PanelTheme(int xSize, int ySize) {
		
		this.setPreferredSize(new Dimension(xSize,ySize));
		this.setLayout(new GridBagLayout());	
		this.setBackground(Color.decode(BoardGameView.theme.getThirdColor()));

		this.createMainView();
		
	}
	
	/**
	 * viene creata l'interfaccia grafica del panel
	 */
	private void createMainView() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.buttonStandard.setText("Standard");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(20, 20, 20, 20);
		this.buttonStandard.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonStandard.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.buttonStandard.setFocusPainted(false);
		this.buttonStandard.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonStandard.setPreferredSize(new Dimension(120, 50));
		this.buttonStandard.addActionListener(this);
		this.add(this.buttonStandard, gbc);
		
		this.buttonWhite.setText("Chiaro");
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(20, 20, 20, 20);
		this.buttonWhite.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonWhite.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.buttonWhite.setFocusPainted(false);
		this.buttonWhite.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonWhite.setPreferredSize(new Dimension(120, 50));
		this.buttonWhite.addActionListener(this);
		this.add(this.buttonWhite, gbc);
		
		this.buttonBlack.setText("Scuro");
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(20, 20, 20, 20);
		this.buttonBlack.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonBlack.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.buttonBlack.setFocusPainted(false);
		this.buttonBlack.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonBlack.setPreferredSize(new Dimension(120, 50));
		this.buttonBlack.addActionListener(this);
		this.add(this.buttonBlack, gbc);
	}

	/**
	 * in base al bottone che viene cliccato viene impostato un tema diverso, se cambiato viene aggiornata tutta la view del frame
	 * e degli altri panel (se esistenti)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonStandard) {
			if(BoardGameView.theme.getActuallyTheme() != "Standard") {
				BoardGameView.theme.setStandardTheme();
				Main.boardGameView.updateThemeView();
				this.updateThemeView();
				JOptionPane.showMessageDialog(null, "Tema standard selezionato");
			} else {
				JOptionPane.showMessageDialog(null, "Tema gia' attivo");
			}
		} else if(e.getSource() == buttonWhite) {
			if(BoardGameView.theme.getActuallyTheme() != "White") {
				BoardGameView.theme.setWhiteTheme();
				Main.boardGameView.updateThemeView();
				this.updateThemeView();
				JOptionPane.showMessageDialog(null, "Tema chiaro selezionato");
			} else {
				JOptionPane.showMessageDialog(null, "Tema gia' attivo");
			}
		} else if(e.getSource() == buttonBlack) {
			if(BoardGameView.theme.getActuallyTheme() != "Black") {
				BoardGameView.theme.setBlackTheme();
				Main.boardGameView.updateThemeView();
				this.updateThemeView();
				JOptionPane.showMessageDialog(null, "Tema scuro selezionato");
			} else {
				JOptionPane.showMessageDialog(null, "Tema gia' attivo");
			}
		}
		
	}
	
	/**
	 * se viene cambiato il tema vengono aggiornati tutti i colori del panel
	 */
	private void updateThemeView() {
		this.setBackground(Color.decode(BoardGameView.theme.getThirdColor()));
		
		this.buttonWhite.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonWhite.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		this.buttonStandard.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonStandard.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		this.buttonBlack.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonBlack.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		this.buttonStandard.setForeground(Color.DARK_GRAY);
		this.buttonWhite.setForeground(Color.DARK_GRAY);
		this.buttonBlack.setForeground(Color.DARK_GRAY);
	}

	
}
