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
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import interfaces.PanelRecordInterface;
import record.PlayerTime;
import record.TableRecord;

/**
 * classe che gestice il panel dei record
 * 
 * @author Alessandro
 *
 */
public class PanelRecord extends JPanel implements ActionListener, PanelRecordInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int hour;
	private int minut;
	private int second;
	
	private JButton buttonRecordEasy;
	private JButton buttonRecordMedium;
	private JButton buttonRecordHard;
	
	private JButton buttonResetRecord;
	private JButton buttonBack;
	
	private String[] listRecord = new String[10];
	private JLabel[] labelListRecord;
	
	private PlayerTime[] listPlayerRecord;
	
	private String resetDifficult = new String();
	
	/**
	 * costruttore che imposta il panel
	 * 
	 * @param xSize
	 *     larghezza del panel
	 * @param ySize
	 *     altezza del panel
	 */
	public PanelRecord(int xSize, int ySize) {
		
		this.setPreferredSize(new Dimension(xSize,ySize));
		this.setLayout(new GridBagLayout());		
		this.setBackground(Color.decode(BoardGameView.theme.getThirdColor()));

		this.createMainView();
	}
	
	/**
	 * viene creata la view del panel
	 */
	private void createMainView() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.buttonRecordEasy = new JButton("Facile");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(20, 20, 20, 20);
		this.buttonRecordEasy.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonRecordEasy.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.buttonRecordEasy.setFocusPainted(false);
		this.buttonRecordEasy.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonRecordEasy.setPreferredSize(new Dimension(120, 50));
		this.buttonRecordEasy.addActionListener(this);
		this.add(this.buttonRecordEasy, gbc);
		
		this.buttonRecordMedium = new JButton("Medio");
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(20, 20, 20, 20);
		this.buttonRecordMedium.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonRecordMedium.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.buttonRecordMedium.setFocusPainted(false);
		this.buttonRecordMedium.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonRecordMedium.setPreferredSize(new Dimension(120, 50));
		this.buttonRecordMedium.addActionListener(this);
		this.add(this.buttonRecordMedium, gbc);
		
		this.buttonRecordHard = new JButton("Difficle");
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(20, 20, 20, 20);
		this.buttonRecordHard.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonRecordHard.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.buttonRecordHard.setFocusPainted(false);
		this.buttonRecordHard.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonRecordHard.setPreferredSize(new Dimension(120, 50));
		this.buttonRecordHard.addActionListener(this);
		this.add(this.buttonRecordHard, gbc);
	}
	
	/**
	 * in base al bottone cliccato viene creata la classifica
	 * 
	 * @param tableRecord
	 *     la difficolt� della classifica che bisogna creare
	 */
	private void createListRecord(TableRecord tableRecord) {
	  GridBagConstraints gbc = new GridBagConstraints();
		
		this.listPlayerRecord = null;
		this.labelListRecord = null;
		
		this.labelListRecord = new JLabel[10];
		
		this.listPlayerRecord = tableRecord.getListRecord();
		System.out.println(this.listPlayerRecord[0].getName() + this.listPlayerRecord[0].getTime());
		for(int x = 0; x < this.listPlayerRecord.length; x++) {
			gbc.gridx = 0;
			gbc.gridy = x;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			this.hour = this.listPlayerRecord[x].getTime() / 3600;
			this.minut = this.listPlayerRecord[x].getTime() / 60 - this.hour*60;
			this.second = this.listPlayerRecord[x].getTime() - this.hour*3600 - this.minut*60;
			String stringTime = new String(hour + "h " + minut + "'" + second + "\"");
			this.listRecord[x] = new String(x+1 + ") " + this.listPlayerRecord[x].getName() + " - "
					+ stringTime);
			this.labelListRecord[x] = new JLabel(this.listRecord[x]);
			this.labelListRecord[x].setFont(new Font("Arial", Font.BOLD, 18));
			this.add(this.labelListRecord[x], gbc);
			if(BoardGameView.theme.getActuallyTheme() == "Black") {
			  this.labelListRecord[x].setForeground(Color.WHITE);
			} else {
			  this.labelListRecord[x].setForeground(Color.DARK_GRAY);
			}
		}

		this.buttonBack = new JButton("Indietro");
		gbc.gridx = 0;
		gbc.gridy = 10;
		this.buttonBack.setPreferredSize(new Dimension(120,50));
		this.buttonBack.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonBack.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.buttonBack.setFocusPainted(false);
		this.buttonBack.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonBack.addActionListener(this);
		gbc.insets = new Insets(30, 0, 10, 0);
		this.add(this.buttonBack, gbc);
		
		this.buttonResetRecord = new JButton("Azzera");
		gbc.gridx = 0;
		gbc.gridy = 11;
		this.buttonResetRecord.setPreferredSize(new Dimension(120,50));
		this.buttonResetRecord.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonResetRecord.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		this.buttonResetRecord.setFocusPainted(false);
		this.buttonResetRecord.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonResetRecord.addActionListener(this);
		gbc.insets = new Insets(10, 0, 10, 0);
		this.add(this.buttonResetRecord, gbc);
		
	}
	
	/**
	 * aggiorna la view in tempo reale, anche se la tabella dei record � attualmente aperta
	 */
	public void updateView() {  
	  if(this.buttonResetRecord.isVisible() && this.buttonBack.isVisible()) {
	    for(int x = 0; x < this.listPlayerRecord.length; x++) {
	      this.hour = this.listPlayerRecord[x].getTime() / 3600;
	      this.minut = this.listPlayerRecord[x].getTime() / 60 - this.hour*60;
	      this.second = this.listPlayerRecord[x].getTime() - this.hour*3600 - this.minut*60;
	      String stringTime = new String(hour + "h " + minut + "'" + second + "\"");
	      this.listRecord[x] = x+1 + ") " + this.listPlayerRecord[x].getName() + " - "
	          + stringTime;
	      this.labelListRecord[x].setText(this.listRecord[x]);
	      if(BoardGameView.theme.getActuallyTheme() == "Black") {
	        this.labelListRecord[x].setForeground(Color.WHITE);
	      } else {
	        this.labelListRecord[x].setForeground(Color.DARK_GRAY);
	      }
	    }
	  }	
	}

	/**
	 * gestice il click sui vari bottoni, in ogni caso rimuove tutti gli elementi attualmente nella view per inserire i nuovi
	 * Se viene cliccato il bottono di reset vengono cancellati i record dalla view attualmente aperta e aggiornati i File dei record
	 * resetDifficult serve per capire quale record � attualmente nella view
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.removeAll();
		this.updateUI();
		if(e.getSource() == this.buttonRecordEasy) {
		  System.out.println("1");
		  this.resetDifficult = "Easy";
		  this.createListRecord(BoardGameView.tableRecordEasy);
		} else if(e.getSource() == this.buttonRecordMedium) {
		  this.resetDifficult = "Medium";
		  this.createListRecord(BoardGameView.tableRecordMedium);
		} else if(e.getSource() == this.buttonRecordHard){
		  this.resetDifficult = "Hard";
		  this.createListRecord(BoardGameView.tableRecordHard);
		} else if(e.getSource() == this.buttonBack) {

			this.createMainView();
		} else if(e.getSource() == this.buttonResetRecord) {
			switch(this.resetDifficult) {
			case "Easy":
			  BoardGameView.tableRecordEasy.writeDefaultRecord();
	       this.createListRecord(BoardGameView.tableRecordEasy);
	       break;
			case "Medium":
        BoardGameView.tableRecordMedium.writeDefaultRecord();
        this.createListRecord(BoardGameView.tableRecordMedium);
				break;
			case "Hard":
        BoardGameView.tableRecordHard.writeDefaultRecord();
        this.createListRecord(BoardGameView.tableRecordHard);
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * aggiorna la view se viene cambiato il tema
	 */
	public void updateThemeView() {
		this.setBackground(Color.decode(BoardGameView.theme.getThirdColor()));
		
		this.buttonRecordEasy.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonRecordEasy.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		this.buttonRecordMedium.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonRecordMedium.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		this.buttonRecordHard.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonRecordHard.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		this.buttonBack.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonBack.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		this.buttonResetRecord.setBackground(Color.decode(BoardGameView.theme.getFirstColor()));
		this.buttonResetRecord.setBorder(new LineBorder(Color.decode(BoardGameView.theme.getSecondColor()), 3));
		
		if(labelListRecord != null) {
			if(BoardGameView.theme.getActuallyTheme() == "Black") {
				for(int x = 0; x < this.listPlayerRecord.length; x++) {
				  this.labelListRecord[x].setForeground(Color.WHITE);
				}
			} else {
				for(int x = 0; x < this.listPlayerRecord.length; x++) {
				  this.labelListRecord[x].setForeground(Color.DARK_GRAY);
				}
			} 
		}
	}
}
