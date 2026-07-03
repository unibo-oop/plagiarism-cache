package hud;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.Controller;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;

public class HUD extends JPanel {
	
	
	JLabel label_2 = new JLabel("Player");                   //casella contenente il nome del giocatore corrente
	
	JLabel label_3 = new JLabel("");						 //casella contenente il colore del giocatore corrente
	
	public static JButton button = new JButton("Spostamento"); //bottone spostamento
	
	public static JButton button_1 = new JButton("Fine turno");  //botttone fineturno
	
	JPanel panel = new JPanel(); 						//pannello contenente le seguenti 8 jlabel per la rappresentazione dei dadi
	
	JLabel label_4 = new JLabel("ATT");							
	
	JLabel label_5 = new JLabel("A1");									
	
	JLabel label_6 = new JLabel("A2");
	
	JLabel label_7 = new JLabel("A3");
	
	JLabel label_8 = new JLabel("DIF");
	
	JLabel label_9 = new JLabel("D1");
	
	JLabel label_10 = new JLabel("D2");
	
	JLabel label_11 = new JLabel("D3");
	
	JPanel panel_comandi = new JPanel();			//pannello contenente le informazioni necessarie a giocare
	
	JLabel lblObbiettivi = new JLabel("obbiettivo");
	
	JLabel lblNarmate = new JLabel("n\u00B0armate");
	
	JLabel lblComando = new JLabel("comando");
	
	static JLabel obbiettivilbl = new JLabel("");
	
	static JLabel numarmatelbl = new JLabel("");
	
	static JLabel comandilbl = new JLabel("");
	
	public HUD() {
		panel_comandi.setPreferredSize(new Dimension(1600, 10));
		panel_comandi.setBackground(Color.GREEN);
		setBackground(SystemColor.textHighlight);
		
		JLabel label = new JLabel("Giocatore:");
		label.setOpaque(true);
		label.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		label.setBackground(SystemColor.activeCaption);
		
		JLabel label_1 = new JLabel("Colore:");
		label_1.setOpaque(true);
		label_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		label_1.setBackground(SystemColor.activeCaption);
		
		
		
		
		
		label_3.setOpaque(true);
		
		
		button.setFont(new Font("Georgia", Font.PLAIN, 16));
		button.setBackground(new Color(0, 250, 154));
		button.addActionListener(a->Controller.moveArmies());
		
		
		button_1.setBackground(new Color(0, 250, 154));
		button_1.setFont(new Font("Georgia", Font.PLAIN, 17));
		button_1.addActionListener(b->Controller.nextPlayer());
		
		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel label_4 = new JLabel("ATT");
		label_4.setOpaque(true);
		label_4.setFont(new Font("Viner Hand ITC", Font.PLAIN, 16));
		label_4.setBackground(new Color(255, 69, 0));
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 0;
		panel.add(label_4, gbc_label_4);
		
		
		label_5.setOpaque(true);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_5.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 1;
		gbc_label_5.gridy = 0;
		panel.add(label_5, gbc_label_5);
		
		
		label_6.setOpaque(true);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_6.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 2;
		gbc_label_6.gridy = 0;
		panel.add(label_6, gbc_label_6);
		
		
		label_7.setOpaque(true);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_7.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.insets = new Insets(0, 0, 5, 0);
		gbc_label_7.gridx = 3;
		gbc_label_7.gridy = 0;
		panel.add(label_7, gbc_label_7);
		
		
		label_8.setOpaque(true);
		label_8.setFont(new Font("Viner Hand ITC", Font.PLAIN, 16));
		label_8.setBackground(Color.YELLOW);
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.insets = new Insets(0, 0, 0, 5);
		gbc_label_8.gridx = 0;
		gbc_label_8.gridy = 1;
		panel.add(label_8, gbc_label_8);
		
		
		label_9.setOpaque(true);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_9.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.insets = new Insets(0, 0, 0, 5);
		gbc_label_9.gridx = 1;
		gbc_label_9.gridy = 1;
		panel.add(label_9, gbc_label_9);
		
		
		label_10.setOpaque(true);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_10.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.insets = new Insets(0, 0, 0, 5);
		gbc_label_10.gridx = 2;
		gbc_label_10.gridy = 1;
		panel.add(label_10, gbc_label_10);
		
		
		label_11.setOpaque(true);
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_11.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.gridx = 3;
		gbc_label_11.gridy = 1;
		panel.add(label_11, gbc_label_11);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addGap(17))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(button_1)
							.addGap(33)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_comandi, GroupLayout.PREFERRED_SIZE, 650, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_comandi, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
											.addComponent(button)
											.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(button_1)
										.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		GroupLayout gl_panel_comandi = new GroupLayout(panel_comandi);
		gl_panel_comandi.setHorizontalGroup(
			gl_panel_comandi.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_comandi.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_comandi.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNarmate)
						.addComponent(lblComando)
						.addComponent(lblObbiettivi))
					.addGap(25)
					.addGroup(gl_panel_comandi.createParallelGroup(Alignment.LEADING)
						.addComponent(obbiettivilbl)
						.addComponent(numarmatelbl)
						.addComponent(comandilbl))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_panel_comandi.setVerticalGroup(
			gl_panel_comandi.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_comandi.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_comandi.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblObbiettivi)
						.addComponent(obbiettivilbl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_comandi.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNarmate)
						.addComponent(numarmatelbl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_comandi.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblComando)
						.addComponent(comandilbl))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_comandi.setLayout(gl_panel_comandi);
		setLayout(groupLayout);

	}
	
	public static JLabel getObbiettivilbl() {
		
		return HUD.obbiettivilbl;
		
	}
	
	public static JLabel getNumarmatelbl(){
		
		return HUD.numarmatelbl;
	}
	
	public static JLabel getComandilbl() {
		
		return HUD.comandilbl;
	}
	
	public static JButton getSpostamento() {
		return HUD.button;
		
	}
	
	public static JButton getFineturno() {
		return HUD.button_1;
		
	}
} 
