package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JTextPane;


public class GUI_GestioneUtenti extends GUI {
	

	private JPanel contentPane;
	private GUI_Registrazione_LogicsImpl r = new  GUI_Registrazione_LogicsImpl();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_GestioneUtenti frame = new GUI_GestioneUtenti();
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_GestioneUtenti() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 630);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblGestioneUtenti = new JLabel("Gestione utenti");
		lblGestioneUtenti.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestioneUtenti.setFont(new Font("Lucida Grande", Font.BOLD, 45));
		contentPane.add(lblGestioneUtenti, BorderLayout.NORTH);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBackground(new Color(255, 222, 173));
		contentPane.add(panel_1_2, BorderLayout.SOUTH);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//save(getX(), getY(), getWidth(), getHeight());
                
                frame = new GUI_Login();              
                
                frame.setBounds(getX(), getY(), getWidth(), getHeight());
               
                frame.setVisible(true);
                setVisible(false);
				
			}
		});
		btnIndietro.setPreferredSize(new Dimension(117, 50));
		panel_1_2.add(btnIndietro);
		
		JButton btnElimina = new JButton("Elimina");
		
		JPanel panel_1 = new JPanel();
		
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//r.removeSelectedCheckBox();
				try {
					r.removeSelectedCheckboxConMAPPA();
					
					//Leggo l'elenco degli utenti memorizzati nel file	
					//r.leggiElencoUtenti();	
					r.leggiElencoConMAPPA();
					
					
					//Creo una checkbox per utente, salvandomele in un arraylist
					//r.writeOnGestioneUtenti(panel_1); 
					r.writePannelloConMAPPA(panel_1);
					
					JOptionPane.showMessageDialog(null, "Eliminazione avvenuta", "ERRORE", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore eliminazione, ripovare", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnElimina.setPreferredSize(new Dimension(117, 50));
		panel_1_2.add(btnElimina);
		
		JButton btnPromuoviAViceadmin = new JButton("Promuovi");
		btnPromuoviAViceadmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					r.promozioneConMAPPA();
					JOptionPane.showMessageDialog(null, "Promozione avvenuta", "ERRORE", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore promozione, ripovare", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnPromuoviAViceadmin.setPreferredSize(new Dimension(117, 50));
		panel_1_2.add(btnPromuoviAViceadmin);
		
		JButton btnDeclassa = new JButton("Declassa");
		btnDeclassa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					r.declassamentoConMAPPA();
					JOptionPane.showMessageDialog(null, "Declassamento avvenuto", "ERRORE", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore declassamento, riprovare", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnDeclassa.setPreferredSize(new Dimension(117, 50));
		panel_1_2.add(btnDeclassa);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane();
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(103)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
					.addGap(54)
					.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(65, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(26, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE))
					.addGap(22))
		);
		panel.setLayout(gl_panel);
		
		
		
		
		JButton btnDeclassa_1 = new JButton("Mostra");
		btnDeclassa_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Leggo l'elenco degli utenti memorizzati nel file	
				//r.leggiElencoUtenti();	
				try {
					r.leggiElencoConMAPPA();
					
					
					//Creo una checkbox per utente, salvandomele in un arraylist
					//r.writeOnGestioneUtenti(panel_1); 
					r.writePannelloConMAPPA(panel_1);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore mostra utenti", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
				

			}
		});
		btnDeclassa_1.setPreferredSize(new Dimension(117, 50));
		panel_1_2.add(btnDeclassa_1);
		
		JButton btnDeclassa_1_1 = new JButton("Log");
		btnDeclassa_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String utente;
					
					if(r.soloUnoSelezionato()) {
						
						utente = r.getFirstSelected();
						stampaLog(textPane, utente);
						
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore log utente, riprovare", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		btnDeclassa_1_1.setPreferredSize(new Dimension(117, 50));
		panel_1_2.add(btnDeclassa_1_1);
	}
}
