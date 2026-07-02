package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GUI_GeneraleHotel extends GUI {

    private JPanel contentPane;

    public GUI_GeneraleHotel() {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 670, 511);
        contentPane = new JPanel();
        contentPane.setMaximumSize(new Dimension(670, 510));
        contentPane.setBackground(new Color(176, 224, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 255, 255));
        contentPane.add(panel, BorderLayout.SOUTH);
        
        JButton btnIndietro = new JButton("Indietro");
        btnIndietro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		save(getX(), getY(), getWidth(), getHeight());
                MyLogger.OttieniUnLogger(GUI.utenteAccesso).info("Utilizza inventario");

                frame = new GUI_SelezioneHotel();
                frame.setBounds(getX(), getY(), getWidth(), getHeight());

                frame.setVisible(true);
                setVisible(false);
        	}
        });
        btnIndietro.setPreferredSize(new Dimension(117, 50));
        panel.add(btnIndietro);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(224, 255, 255));
        contentPane.add(panel_1, BorderLayout.NORTH);
        
        JLabel lblGeneraleHotel = new JLabel("Generale hotel");
        lblGeneraleHotel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
        panel_1.add(lblGeneraleHotel);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(240, 248, 255));
        contentPane.add(panel_2, BorderLayout.CENTER);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(224, 255, 255));
        
        JButton btnInventario = new JButton("Inventario");
        btnInventario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			save(getX(), getY(), getWidth(), getHeight());
                    MyLogger.OttieniUnLogger(GUI.utenteAccesso).info("Utilizza inventario");

                    frame = new GUI_Inventario();
                    frame.setBounds(getX(), getY(), getWidth(), getHeight());

                    frame.setVisible(true);
                    setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore apertura inventario", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        		
        		
        	}
        });
        btnInventario.setPreferredSize(new Dimension(117, 50));
        panel_3.add(btnInventario);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(new Color(224, 255, 255));
        
        JPanel panel_5 = new JPanel();
        panel_5.setBackground(new Color(224, 255, 255));
        
        JButton btnGestioneUtenti = new JButton("Gestione utenti");
        btnGestioneUtenti.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			if(soloAdmin()) {
                      	MyLogger.OttieniUnLogger(GUI.utenteAccesso).info("accesso alla gestione degli utenti");
      	                frame = new GUI_GestioneUtenti();

      	                frame.setBounds(getX(), getY(), getWidth(), getHeight());

      	                frame.setVisible(true);
      	                setVisible(false);
                      }else{
                      	MyLogger.OttieniUnLogger(GUI.utenteAccesso).warning("tentato accesso alla gestione degli utenti");
                      }
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore apertura gestione utenti", "ERRORE", JOptionPane.ERROR_MESSAGE);
				} 
        		  
        		
        	}
        });
        btnGestioneUtenti.setPreferredSize(new Dimension(117, 50));
        panel_5.add(btnGestioneUtenti);
        
        JPanel panel_5_1 = new JPanel();
        panel_5_1.setBackground(new Color(224, 255, 255));
        
        JButton btnFornitori = new JButton("Fornitori");
        btnFornitori.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			MyLogger.OttieniUnLogger(GUI.utenteAccesso).info("accesso ai fornitori");
	                frame = new GUI_Fornitore();

	                frame.setBounds(getX(), getY(), getWidth(), getHeight());

	                frame.setVisible(true);
	            setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore apertura fornitori", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        		
        		
        	}
        });
        btnFornitori.setPreferredSize(new Dimension(117, 50));
        panel_5_1.add(btnFornitori);
        
        JButton btnSalvaTutto = new JButton("SALVA TUTTO");
        btnSalvaTutto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			WarehouseAddUtilityImpl w = new WarehouseAddUtilityImpl();       		
            		UtilityReadWriteCatena.setCatena(GUI.catenaAccesso);
            		
            		JOptionPane.showMessageDialog(null, "SALVATO TUTTO", "OK", JOptionPane.OK_OPTION);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore salvataggio catena", "OK", JOptionPane.OK_OPTION);
				}
        		
        	}
        });
        btnSalvaTutto.setPreferredSize(new Dimension(117, 50));
        panel_5_1.add(btnSalvaTutto);
        
        JButton btnIndietro_3_1_1 = new JButton("Persone in hotel");
        btnIndietro_3_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			save(getX(), getY(), getWidth(), getHeight());
                    MyLogger.OttieniUnLogger(GUI.utenteAccesso).info("Utilizza persone in hotel");

                    frame = new GUI_PersoneInHotel();
                    frame.setBounds(getX(), getY(), getWidth(), getHeight());

                    frame.setVisible(true);
                    setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore apertura persone in Hotel", "OK", JOptionPane.OK_OPTION);
				}
        		
        		
        	}
        });
        btnIndietro_3_1_1.setPreferredSize(new Dimension(117, 50));
        panel_5_1.add(btnIndietro_3_1_1);
        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(
        	gl_panel_2.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel_2.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel_2.createSequentialGroup()
        					.addComponent(panel_5_1, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        					.addContainerGap())
        				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
        					.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        					.addContainerGap())
        				.addGroup(gl_panel_2.createSequentialGroup()
        					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        					.addContainerGap())
        				.addGroup(gl_panel_2.createSequentialGroup()
        					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        					.addContainerGap())))
        );
        gl_panel_2.setVerticalGroup(
        	gl_panel_2.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_2.createSequentialGroup()
        			.addGap(26)
        			.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        			.addGap(18)
        			.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        			.addGap(18)
        			.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        			.addGap(18)
        			.addComponent(panel_5_1, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        			.addGap(36))
        );
        
        JButton btnIndietro_3_1 = new JButton("I tuoi log");
        btnIndietro_3_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			save(getX(), getY(), getWidth(), getHeight());
                    MyLogger.OttieniUnLogger(GUI.utenteAccesso).info("Utilizza i suoi log");

                    frame = new GUI_LogUtente();
                    frame.setBounds(getX(), getY(), getWidth(), getHeight());

                    frame.setVisible(true);
                    setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore apertura i tuoi log", "OK", JOptionPane.OK_OPTION);
				}
        		
        		
        	}
        });
        btnIndietro_3_1.setPreferredSize(new Dimension(117, 50));
        panel_5.add(btnIndietro_3_1);
        
        JButton btnStorico = new JButton("Storico");
        panel_5.add(btnStorico);
        btnStorico.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			save(getX(), getY(), getWidth(), getHeight());
                    MyLogger.OttieniUnLogger(GUI.utenteAccesso).info(GUI.utenteAccesso+"- Utilizza storico ");

                    frame = new GUI_Storico();
                    frame.setBounds(getX(), getY(), getWidth(), getHeight());

                    frame.setVisible(true);
                    setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore apertura storico", "OK", JOptionPane.OK_OPTION);
				}
        	    
        		
        	}
        });
        btnStorico.setPreferredSize(new Dimension(117, 50));
        
        JButton btnIndietro_2_1 = new JButton("Consumi");
        btnIndietro_2_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			save(getX(), getY(), getWidth(), getHeight());
                    MyLogger.OttieniUnLogger(GUI.utenteAccesso).info("Utilizza consumi");

                    frame = new GUI_Consumi();
                    frame.setBounds(getX(), getY(), getWidth(), getHeight());

                    frame.setVisible(true);
                    setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore apertura consumi", "OK", JOptionPane.OK_OPTION);
				}
        		
        		
        	}
        });
        
        JButton btnIndietro_2_1_2 = new JButton("Previsioni");
        btnIndietro_2_1_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			save(getX(), getY(), getWidth(), getHeight());
                    MyLogger.OttieniUnLogger(GUI.utenteAccesso).info("Utilizza consumi");

                    frame = new GUI_Previsioni();
                    frame.setBounds(getX(), getY(), getWidth(), getHeight());

                    frame.setVisible(true);
                    setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore apertura previsioni", "OK", JOptionPane.OK_OPTION);
				}
        		
        		
        	}
        });
        btnIndietro_2_1_2.setPreferredSize(new Dimension(117, 50));
        panel_4.add(btnIndietro_2_1_2);
        btnIndietro_2_1.setPreferredSize(new Dimension(117, 50));
        panel_4.add(btnIndietro_2_1);
        
        JButton btnIndietro_2_1_1 = new JButton("Aggiunta cons.");
        btnIndietro_2_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			save(getX(), getY(), getWidth(), getHeight());
                    MyLogger.OttieniUnLogger(GUI.utenteAccesso).info("Utilizza dispensa");

                    frame = new GUI_AggiuntaConsumi();
                    frame.setBounds(getX(), getY(), getWidth(), getHeight());

                    frame.setVisible(true);
                    setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore apertura aggiunta consumi", "OK", JOptionPane.OK_OPTION);
				}
        		 
        	}
        });
        btnIndietro_2_1_1.setPreferredSize(new Dimension(117, 50));
        panel_4.add(btnIndietro_2_1_1);
        
        JButton btnIndietro_1_1 = new JButton("Dispensa");
        btnIndietro_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			save(getX(), getY(), getWidth(), getHeight());
                    MyLogger.OttieniUnLogger(GUI.utenteAccesso).info("Utilizza dispensa");

                    frame = new GUI_Dispensa();
                    frame.setBounds(getX(), getY(), getWidth(), getHeight());

                    frame.setVisible(true);
                    setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore apertura dispensa", "OK", JOptionPane.OK_OPTION);
				}
        		  
        		
        	}
        });
        btnIndietro_1_1.setPreferredSize(new Dimension(117, 50));
        panel_3.add(btnIndietro_1_1);
        panel_2.setLayout(gl_panel_2);
    }
}
