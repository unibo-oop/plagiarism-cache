package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Dimension;

public class GUI_Registrazione extends GUI{

    private JPanel contentPane;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    
   
    
    //Da mettere come prima dichiarazione l'interfaccia
    private GUI_Registrazione_LogicsImpl r = new  GUI_Registrazione_LogicsImpl();
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_Registrazione frame = new GUI_Registrazione();
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
    public GUI_Registrazione() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 521, 566);
        contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(520, 566));
        contentPane.setBackground(new Color(176, 224, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JLabel lblRegistrazione = new JLabel("Registrazione");
        lblRegistrazione.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistrazione.setFont(new Font("Lucida Grande", Font.BOLD, 45));
        contentPane.add(lblRegistrazione, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBackground(new Color(176, 224, 230));
        contentPane.add(panel, BorderLayout.CENTER);
        
        JPanel panel_2 = new JPanel();
        panel_2.setOpaque(false);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
                    .addGap(14)
                    .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(22)
                    .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addGap(19))
        );
        
        JPanel panel_2_1 = new JPanel();
        panel_2_1.setOpaque(false);
        
        JLabel lblDispensa_1 = new JLabel("Password");
        lblDispensa_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblDispensa_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        
        JLabel lblQuantit = new JLabel("Ripeti password");
        lblQuantit.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuantit.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        
        JLabel lblNewLabel = new JLabel("Username");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        
        passwordField = new JPasswordField();
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        
        passwordField_1 = new JPasswordField();
        passwordField_1.setHorizontalAlignment(SwingConstants.CENTER);
        
        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setColumns(10);
        GroupLayout gl_panel_2_1 = new GroupLayout(panel_2_1);
        gl_panel_2_1.setHorizontalGroup(
        	gl_panel_2_1.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel_2_1.createSequentialGroup()
        			.addGap(73)
        			.addGroup(gl_panel_2_1.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblQuantit, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
        				.addComponent(lblDispensa_1, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
        				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
        			.addGap(68))
        		.addGroup(gl_panel_2_1.createSequentialGroup()
        			.addGap(96)
        			.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
        			.addGap(97))
        		.addGroup(gl_panel_2_1.createSequentialGroup()
        			.addGap(95)
        			.addComponent(passwordField_1, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
        			.addGap(98))
        		.addGroup(Alignment.LEADING, gl_panel_2_1.createSequentialGroup()
        			.addGap(100)
        			.addComponent(textField, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        			.addGap(99))
        );
        gl_panel_2_1.setVerticalGroup(
        	gl_panel_2_1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_2_1.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(42)
        			.addComponent(lblDispensa_1, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(35)
        			.addComponent(lblQuantit, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(28))
        );
        panel_2_1.setLayout(gl_panel_2_1);
        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(
            gl_panel_2.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2.createSequentialGroup()
                    .addGap(46)
                    .addComponent(panel_2_1, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                    .addGap(58))
        );
        gl_panel_2.setVerticalGroup(
            gl_panel_2.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
                    .addGap(44)
                    .addComponent(panel_2_1, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addGap(52))
        );
        panel_2.setLayout(gl_panel_2);
        panel.setLayout(gl_panel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setPreferredSize(new Dimension(10, 70));
        panel_1.setOpaque(false);
        contentPane.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new GridLayout(1, 0, 0, 0));
        
        JButton btnNewButton = new JButton("Indietro");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                save(getX(), getY(), getWidth(), getHeight());
                
                frame = new GUI_Login();
                frame.setBounds(getX(), getY(), getWidth(), getHeight());
               
                frame.setVisible(true);
                setVisible(false);
                
            }
        });
        panel_1.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Conferma");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	String tx = textField.getText();
    			char[] p = passwordField.getPassword();
    			char[] p2 = passwordField_1.getPassword();
        		
    			try {
    				if(!tx.isBlank() && p.length!=0) {
    					if(Arrays.equals(p, p2)) {
    						if(!r.UtenteGiaPresente(tx)) {
		    				r.leggiElencoConMAPPA();
			    			r.scriviNuovoUtenteConMAPPA(tx, p);
			    			
			    			//Chiamata al metodo di Ivan per aggiungere un log per l'utente che si è iscritto
			            	MyLogger.AggiungiUnLogger(tx); 
			            	System.out.println("Log creato anche per: "+tx);
			    			
			                save(getX(), getY(), getWidth(), getHeight());
			                
			                
			                frame = new GUI_SelezioneHotel();
			                frame.setBounds(getX(), getY(), getWidth(), getHeight());
			               
			                frame.setVisible(true);
			                setVisible(false);
    						}else {
    							JOptionPane.showMessageDialog(null, "Nome utente già presente", "ERRORE", JOptionPane.ERROR_MESSAGE);
    						}
    					}else {
    						JOptionPane.showMessageDialog(null, "Le due password non corrispondono", "ERRORE", JOptionPane.ERROR_MESSAGE);
    					}
    				}else {
    					JOptionPane.showMessageDialog(null, "Riempire i campi prima di procedere", "ERRORE", JOptionPane.ERROR_MESSAGE);
    				}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Controllare credenziali inserite", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
                
            }
        });
        panel_1.add(btnNewButton_1);
    }
}
