package application;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.scene.control.ComboBox;

import javax.swing.JScrollBar;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Cursor;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI_Inventario extends GUI {

    private JPanel contentPane;
    
    
    private JTextField textField_1_2_1_1_1;
    private JTextField textField_1_2_1_2_1_1_1_1;
    private JTextField textField_1_2_1_2_1_1_2_1;
    private JTextField textField_2_2_1_1_1;
    private JTextField textField_2_2_1_2_1_1_1;
    private JTextField textField_2_2_1_2_1_1_2_1;
    private JTextField textField_2_2_1_3_1_1_1;
    private JTextField textField_2_2_1_3_1_1_2_1;
    private JTextField textField_2_2_1_4_1;    
    private JTextField textField_3_2_1_1_1_1;
    private JTextField textField_3_2_1_1_2;
    private JTextField textField_3_2_1_1_3_1;
    private JTextField textField_3_2_1_1_4_1;
    private JTextField textField_3_2_1_2_2_1;
    private JTextField textField_3_2_1_2_1_1_1_1;
    private JTextField textField_3_2_1_2_1_1_1_2_1;
    private JTextField textField_3_2_1_2_3_1_1_1_1;
    private JTextField textField_3_2_1_2_3_1_1_2_1;
    private JTextField textField_4_2_1_1_1_1_1;
    private JTextField textField_4_2_1_1_1_2_1;
    private JTextField textField_4_2_1_1_1_3_1;
    private JTextField textField_4_2_1_1_2_2_1;
    private JTextField textField_4_2_1_1_3_1_1;
    private JTextField textField_4_2_1_1_3_2_1;
    private JTextField textField_4_2_1_1_3_3_1;
    private JTextField textField_4_2_1_1_3_4_1;
    private JTextField textField_4_2_1_1_2_1_1_1_1_1;
    private JTextField textField_4_2_1_1_2_1_1_1_2_1;
    private JTextField textField_4_2_1_1_2_3_1_1_1_1;
    private JTextField textField_4_2_1_1_2_3_1_1_2_1;
    
   /*
    * 
    * 
    * 
    * Text Field di sola stampa
		Tipologia: 
			niente
		
		prodotto:
			padre --> textField_2_2_1_4_1
			info -->
			
		
		prodConcreto:
			padre --> textField_3_2_1_2_2_1
			ID best forn --> textField_3_2_1_1_2
			prezzo più basso --> textField_3_2_1_1_3_1
			prezzo eff. migliore --> textField_3_2_1_1_4_1
		
		prod.fornito:
			padre —> textField_4_2_1_1_3_4_1
			val. netto —> textField_4_2_1_1_3_2_1
			prezzo eff —> textField_4_2_1_1_2_2_1
			perc. netto —>  textField_4_2_1_1_3_3_1
    * 
    * 
    * 
    * */
    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_Inventario frame = new GUI_Inventario();
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
    public GUI_Inventario() {
        setMinimumSize(new Dimension(670, 660));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 615, 659);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(176, 224, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_bottoni = new JPanel();
        panel_bottoni.setPreferredSize(new Dimension(10, 70));
        panel_bottoni.setOpaque(false);
        contentPane.add(panel_bottoni, BorderLayout.SOUTH);
        panel_bottoni.setLayout(new GridLayout(1, 0, 0, 0));
        
        JButton btnNewButton = new JButton("Indietro");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                save(getX(), getY(), getWidth(), getHeight());
                
                frame = new GUI_GeneraleHotel();
                frame.setBounds(getX(), getY(), getWidth(), getHeight());
               
                frame.setVisible(true);
                setVisible(false);
                
            }
        });
        panel_bottoni.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Conferma");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                save(getX(), getY(), getWidth(), getHeight());
                
                frame = new GUI_GeneraleHotel();
                frame.setBounds(getX(), getY(), getWidth(), getHeight());
               
                frame.setVisible(true);
                setVisible(false);
                
            }
        });
        panel_bottoni.add(btnNewButton_1);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        JPanel panel = new JPanel();
        scrollPane.setViewportView(panel);
        
        
        
        //--------------------- 1 -----------------------
        
        JPanel p1_panel_TIPOLOGIA = new JPanel();
        p1_panel_TIPOLOGIA.setBackground(new Color(240, 255, 255));
        
        p1_panel_TIPOLOGIA.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_1_TIPOLOGIA = new JLabel("Tipologia");
        lbl_1_TIPOLOGIA.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_1_TIPOLOGIA.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        p1_panel_TIPOLOGIA.add(lbl_1_TIPOLOGIA, BorderLayout.NORTH);
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBackground(new Color(255, 222, 173));
        p1_panel_TIPOLOGIA.add(panel_1_1, BorderLayout.SOUTH);
        
        
        
        JButton btnSalva_1_1 = new JButton("Salva");
        
        JComboBox comboBox_1_2 = new JComboBox();
        JComboBox comboBox_1_2_1_2_1 = new JComboBox();
        
        
        comboBox_1_2.addFocusListener(new FocusAdapter() {
        	@Override
        	//Mostrare tendina con elenco delle tipologie
        	public void focusGained(FocusEvent e) {
        		try {
        			comboBox_1_2.removeAllItems();
            		
            		for(Typology x : (ArrayList<Typology>)GUI.catenaAccesso.getInventario()) {
            			if(x instanceof Tipologia) {
            				comboBox_1_2.addItem(x.getID());
            			}
            		}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore tendina tipologie", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        		
	
        	}
        });
        
        
        comboBox_1_2_1_2_1.addFocusListener(new FocusAdapter() {
        	
        	
        	//Mostrare tendina con elenco delle informazioni associate alla tipologia scelta nella tendina sopra
        	
        	public void focusGained(FocusEvent e) {
        		
        		try {
        			comboBox_1_2_1_2_1.removeAllItems();
            		
            		Tipologia tmp = (Tipologia)GUI.catenaAccesso.ottieniDallInventario(comboBox_1_2.getSelectedItem().toString()).get();
            		
            		for(String x : tmp.getInfo().keySet()) {
            			
            			comboBox_1_2_1_2_1.addItem(x.toString());
            			
            		}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore tendina informazioni della tipologia", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}

        	}
        	
        });
        
        
        
        btnSalva_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
               try {
                String id_tipologia = textField_1_2_1_1_1.getText();              
                String id_info = textField_1_2_1_2_1_1_1_1.getText();               
                String val_info = textField_1_2_1_2_1_1_2_1.getText();                
                JComboBox tipologia = comboBox_1_2;               
                JComboBox informazioni = comboBox_1_2_1_2_1;
                
               
                setTipologia(id_tipologia, id_info, val_info, tipologia, informazioni);
                //reloadComboTipologia(tipologia);
               }catch (Exception e1) {
            	   e1.printStackTrace();
            	   JOptionPane.showMessageDialog(null, "Controllare i campi inseriti", "ERRORE", JOptionPane.ERROR_MESSAGE);   
               }
                
                
            }
        });
        btnSalva_1_1.setPreferredSize(new Dimension(117, 50));
        panel_1_1.add(btnSalva_1_1);
        
        JButton btnModifica_1_1 = new JButton("Modifica");
        btnModifica_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 try {
	        		 String id_tipologia = textField_1_2_1_1_1.getText();              
	                 String id_info = textField_1_2_1_2_1_1_1_1.getText();               
	                 String val_info = textField_1_2_1_2_1_1_2_1.getText();                
	                 JComboBox tipologia = comboBox_1_2;               
	                 JComboBox informazioni = comboBox_1_2_1_2_1;
                 
                 
	                 modificaTipologia(id_tipologia, id_info, val_info);
	               
	                 comboBox_1_2.removeAllItems();
	         		
	         		 for(Typology x : (ArrayList<Typology>)GUI.catenaAccesso.getInventario()) {
	         			if(x instanceof Tipologia) {
	         				comboBox_1_2.addItem(x.getID());
	         			}
	         		 }
				  }catch (Exception e1) {
					  e1.printStackTrace();
		        	  JOptionPane.showMessageDialog(null, "Controllare i campi inseriti", "ERRORE", JOptionPane.ERROR_MESSAGE);   
		          }
        		 
        		 
		    	}
        });
        btnModifica_1_1.setPreferredSize(new Dimension(117, 50));
        panel_1_1.add(btnModifica_1_1);
        
        JButton btnRimuovi_1_1 = new JButton("Rimuovi");
        btnRimuovi_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
	        		String id_tipologia = textField_1_2_1_1_1.getText();              
	                String id_info = textField_1_2_1_2_1_1_1_1.getText();               
	                String val_info = textField_1_2_1_2_1_1_2_1.getText();                
	                JComboBox tipologia = comboBox_1_2;               
	                JComboBox informazioni = comboBox_1_2_1_2_1;
                
                
                rimuoviTipologia(id_tipologia, id_info);
        		 }catch (Exception e1) {
        			 e1.printStackTrace();
		        	  JOptionPane.showMessageDialog(null, "Controllare i campi inseriti", "ERRORE", JOptionPane.ERROR_MESSAGE);   
		         }
        	}
        });
        btnRimuovi_1_1.setPreferredSize(new Dimension(117, 50));
        panel_1_1.add(btnRimuovi_1_1);
        
        JButton btnRimuovi_1_1_1 = new JButton("Rimuovi");
        btnRimuovi_1_1_1.setPreferredSize(new Dimension(117, 50));
        panel_1_1.add(btnRimuovi_1_1_1);
        
        JPanel panel_1_2 = new JPanel();
        panel_1_2.setBackground(new Color(144, 238, 144));
        p1_panel_TIPOLOGIA.add(panel_1_2, BorderLayout.CENTER);
        panel_1_2.setLayout(new BorderLayout(0, 0));
        
        
        panel_1_2.add(comboBox_1_2, BorderLayout.NORTH);
        
        JPanel panel_1_2_1 = new JPanel();
        panel_1_2_1.setBackground(new Color(250, 250, 210));
        panel_1_2.add(panel_1_2_1, BorderLayout.CENTER);
        panel_1_2_1.setLayout(new GridLayout(1, 0, 0, 0));
        
        JPanel panel_1_2_1_1 = new JPanel();
        panel_1_2_1.add(panel_1_2_1_1);
        panel_1_2_1_1.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_ID_1_2_1_1 = new JLabel("ID");
        lbl_ID_1_2_1_1.setOpaque(true);
        lbl_ID_1_2_1_1.setBackground(new Color(255, 228, 225));
        lbl_ID_1_2_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_ID_1_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1_2_1_1.add(lbl_ID_1_2_1_1, BorderLayout.NORTH);
        
        JPanel panel_1_2_1_1_1 = new JPanel();
        panel_1_2_1_1.add(panel_1_2_1_1_1, BorderLayout.CENTER);
        
        textField_1_2_1_1_1 = new JTextField();
        textField_1_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_1_2_1_1_1.setColumns(10);
        GroupLayout gl_panel_1_2_1_1_1 = new GroupLayout(panel_1_2_1_1_1);
        gl_panel_1_2_1_1_1.setHorizontalGroup(
            gl_panel_1_2_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_1_2_1_1_1.createSequentialGroup()
                    .addGap(53)
                    .addComponent(textField_1_2_1_1_1, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addGap(51))
        );
        gl_panel_1_2_1_1_1.setVerticalGroup(
            gl_panel_1_2_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1_2_1_1_1.createSequentialGroup()
                    .addGap(98)
                    .addComponent(textField_1_2_1_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(114, Short.MAX_VALUE))
        );
        panel_1_2_1_1_1.setLayout(gl_panel_1_2_1_1_1);
        
        JPanel panel_1_2_1_2 = new JPanel();
        panel_1_2_1.add(panel_1_2_1_2);
        panel_1_2_1_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_ID_1_2_1_2 = new JLabel("Informazioni");
        lbl_ID_1_2_1_2.setBackground(new Color(255, 228, 225));
        lbl_ID_1_2_1_2.setOpaque(true);
        lbl_ID_1_2_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_ID_1_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1_2_1_2.add(lbl_ID_1_2_1_2, BorderLayout.NORTH);
        
        JPanel panel_1_2_1_2_1 = new JPanel();
        panel_1_2_1_2.add(panel_1_2_1_2_1, BorderLayout.CENTER);
        panel_1_2_1_2_1.setLayout(new BorderLayout(0, 0));
        
        
        panel_1_2_1_2_1.add(comboBox_1_2_1_2_1, BorderLayout.NORTH);
        
        JPanel panel_1_2_1_2_1_1 = new JPanel();
        panel_1_2_1_2_1.add(panel_1_2_1_2_1_1, BorderLayout.CENTER);
        panel_1_2_1_2_1_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_1_2_1_2_1_1_1 = new JPanel();
        panel_1_2_1_2_1_1.add(panel_1_2_1_2_1_1_1);
        panel_1_2_1_2_1_1_1.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId_1_2_1_2_1_1_1 = new JLabel("ID");
        lblId_1_2_1_2_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblId_1_2_1_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1_2_1_2_1_1_1.add(lblId_1_2_1_2_1_1_1, BorderLayout.NORTH);
        
        JPanel panel_1_2_1_2_1_1_1_1 = new JPanel();
        panel_1_2_1_2_1_1_1.add(panel_1_2_1_2_1_1_1_1, BorderLayout.CENTER);
        
        textField_1_2_1_2_1_1_1_1 = new JTextField();
        textField_1_2_1_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_1_2_1_2_1_1_1_1.setColumns(10);
        GroupLayout gl_panel_1_2_1_2_1_1_1_1 = new GroupLayout(panel_1_2_1_2_1_1_1_1);
        gl_panel_1_2_1_2_1_1_1_1.setHorizontalGroup(
            gl_panel_1_2_1_2_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1_2_1_2_1_1_1_1.createSequentialGroup()
                    .addGap(60)
                    .addComponent(textField_1_2_1_2_1_1_1_1, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addGap(61))
        );
        gl_panel_1_2_1_2_1_1_1_1.setVerticalGroup(
            gl_panel_1_2_1_2_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1_2_1_2_1_1_1_1.createSequentialGroup()
                    .addGap(31)
                    .addComponent(textField_1_2_1_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(29, Short.MAX_VALUE))
        );
        panel_1_2_1_2_1_1_1_1.setLayout(gl_panel_1_2_1_2_1_1_1_1);
        
        JPanel panel_1_2_1_2_1_1_2 = new JPanel();
        panel_1_2_1_2_1_1.add(panel_1_2_1_2_1_1_2);
        panel_1_2_1_2_1_1_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lblChiave = new JLabel("Valore");
        lblChiave.setHorizontalAlignment(SwingConstants.CENTER);
        lblChiave.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_1_2_1_2_1_1_2.add(lblChiave, BorderLayout.NORTH);
        
        JPanel panel_1_2_1_2_1_1_2_1 = new JPanel();
        panel_1_2_1_2_1_1_2.add(panel_1_2_1_2_1_1_2_1, BorderLayout.CENTER);
        
        textField_1_2_1_2_1_1_2_1 = new JTextField();
        textField_1_2_1_2_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_1_2_1_2_1_1_2_1.setColumns(10);
        GroupLayout gl_panel_1_2_1_2_1_1_2_1 = new GroupLayout(panel_1_2_1_2_1_1_2_1);
        gl_panel_1_2_1_2_1_1_2_1.setHorizontalGroup(
            gl_panel_1_2_1_2_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1_2_1_2_1_1_2_1.createSequentialGroup()
                    .addGap(61)
                    .addComponent(textField_1_2_1_2_1_1_2_1, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addGap(60))
        );
        gl_panel_1_2_1_2_1_1_2_1.setVerticalGroup(
            gl_panel_1_2_1_2_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_1_2_1_2_1_1_2_1.createSequentialGroup()
                    .addGap(31)
                    .addComponent(textField_1_2_1_2_1_1_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(29, Short.MAX_VALUE))
        );
        panel_1_2_1_2_1_1_2_1.setLayout(gl_panel_1_2_1_2_1_1_2_1);
        
        
        
        //-----------------------------------------------
        
        
        
        //-------------------- 2 ------------------------
        
        JPanel p2_panel_PRODOTTO = new JPanel();
        p2_panel_PRODOTTO.setBackground(new Color(240, 255, 255));
        p2_panel_PRODOTTO.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_PRODOTTO_2 = new JLabel("Prodotto");
        lbl_PRODOTTO_2.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_PRODOTTO_2.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        p2_panel_PRODOTTO.add(lbl_PRODOTTO_2, BorderLayout.NORTH);
        
        JPanel panel_2_1 = new JPanel();
        panel_2_1.setBackground(new Color(255, 222, 173));
        p2_panel_PRODOTTO.add(panel_2_1, BorderLayout.SOUTH);
        
        JCheckBox chckbx_2_2_1_3_1_1_3_1 = new JCheckBox("");
        
        JButton btnSalva_2_1 = new JButton("Salva");
        btnSalva_2_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        		String id_prodotto = textField_2_2_1_1_1.getText();
        		String padre = textField_2_2_1_4_1.getText();
        		String id_scarto = textField_2_2_1_3_1_1_1.getText();
        		String val_scarto = textField_2_2_1_3_1_1_2_1.getText();
        		String id_info = textField_2_2_1_2_1_1_1.getText();
        		String val_info = textField_2_2_1_2_1_1_2_1.getText();
        		JCheckBox cb= chckbx_2_2_1_3_1_1_3_1;
        		
        		if(cb.isSelected()) {
        			float n = Float.parseFloat(val_scarto)/100;
        			val_scarto= String.valueOf(n);
        		}
        		
        		setProdotto(id_prodotto, padre, id_scarto, val_scarto, id_info, val_info);
        		 }catch (Exception e1) {
        			 e1.printStackTrace();
		        	  JOptionPane.showMessageDialog(null, "Controllare i campi inseriti", "ERRORE", JOptionPane.ERROR_MESSAGE);   
		         }
        		
        	}
        });
        btnSalva_2_1.setPreferredSize(new Dimension(117, 50));
        panel_2_1.add(btnSalva_2_1);
        
        JComboBox comboBox_2_2 = new JComboBox();
        
        JButton btnModifica_2_1 = new JButton("Modifica");
        btnModifica_2_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        		String id_prodotto = textField_2_2_1_1_1.getText();
        		String padre = textField_2_2_1_4_1.getText();
        		String id_scarto = textField_2_2_1_3_1_1_1.getText();
        		String val_scarto = textField_2_2_1_3_1_1_2_1.getText();
        		String id_info = textField_2_2_1_2_1_1_1.getText();
        		String val_info = textField_2_2_1_2_1_1_2_1.getText();
        		JCheckBox cb= chckbx_2_2_1_3_1_1_3_1;
        		
        		if(cb.isSelected()) {
        			float n = Float.parseFloat(val_scarto)/100;
        			val_scarto= String.valueOf(n);
        		}
        		
        		modificaProdotto(id_prodotto, id_scarto, val_scarto, id_info, val_info);
        		
        		comboBox_2_2.removeAllItems();
        		
        		for(Typology x : (ArrayList<Typology>)GUI.catenaAccesso.getInventario()) {
        			if(x instanceof Prodotto) {
        				if(((Prodotto) x).getPadre().getID().equals(comboBox_1_2.getSelectedItem().toString()))
        				comboBox_2_2.addItem(x.getID());
        			}
        		}
        		
        		textField_2_2_1_4_1.setText(comboBox_1_2.getSelectedItem().toString());
        		 }catch (Exception e1) {
        			 e1.printStackTrace();
		        	  JOptionPane.showMessageDialog(null, "Controllare i campi inseriti", "ERRORE", JOptionPane.ERROR_MESSAGE);   
		         }
        	}
        });
        btnModifica_2_1.setPreferredSize(new Dimension(117, 50));
        panel_2_1.add(btnModifica_2_1);
        
        JButton btnRimuovi_2_1 = new JButton("Rimuovi");
        btnRimuovi_2_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        		String id_prodotto = textField_2_2_1_1_1.getText();
        		String padre = textField_2_2_1_4_1.getText();
        		String id_scarto = textField_2_2_1_3_1_1_1.getText();
        		String val_scarto = textField_2_2_1_3_1_1_2_1.getText();
        		String id_info = textField_2_2_1_2_1_1_1.getText();
        		String val_info = textField_2_2_1_2_1_1_2_1.getText();
        		JCheckBox cb= chckbx_2_2_1_3_1_1_3_1;
        		
        		if(cb.isSelected()) {
        			float n = Float.parseFloat(val_scarto)/100;
        			val_scarto= String.valueOf(n);
        		}
        		
        		rimuoviProdotto(id_prodotto,  id_info,  id_scarto);
        		}catch (Exception e1) {
        			e1.printStackTrace();
		        	  JOptionPane.showMessageDialog(null, "Controllare i campi inseriti", "ERRORE", JOptionPane.ERROR_MESSAGE);   
		         }
        	}
        });
        btnRimuovi_2_1.setPreferredSize(new Dimension(117, 50));
        panel_2_1.add(btnRimuovi_2_1);
        
        JPanel panel_2_2 = new JPanel();
        panel_2_2.setBackground(new Color(144, 238, 144));
        p2_panel_PRODOTTO.add(panel_2_2, BorderLayout.CENTER);
        panel_2_2.setLayout(new BorderLayout(0, 0));
        
        
        comboBox_2_2.addFocusListener(new FocusAdapter() {
        	@Override
        	
        	//Tendina con l'elenco dei prodotti della tipologia scelta nella tendina delle tipologie
        	public void focusGained(FocusEvent e) {
        		
        		try {
        			comboBox_2_2.removeAllItems();
            		
            		for(Typology x : (ArrayList<Typology>)GUI.catenaAccesso.getInventario()) {
            			if(x instanceof Prodotto) {
            				if(((Prodotto) x).getPadre().getID().equals(comboBox_1_2.getSelectedItem().toString()))
            				comboBox_2_2.addItem(x.getID());
            			}
            		}
            		
            		textField_2_2_1_4_1.setText(comboBox_1_2.getSelectedItem().toString());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore tendina prodotti", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}

        	}
        });
        panel_2_2.add(comboBox_2_2, BorderLayout.NORTH);
        
        JPanel panel_2_2_1 = new JPanel();
        panel_2_2_1.setBackground(new Color(250, 250, 210));
        panel_2_2.add(panel_2_2_1, BorderLayout.CENTER);
        panel_2_2_1.setLayout(new GridLayout(1, 0, 0, 0));
        
        JPanel panel_2_2_1_1 = new JPanel();
        panel_2_2_1.add(panel_2_2_1_1);
        panel_2_2_1_1.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_ID_2_2_1_1 = new JLabel("ID");
        lbl_ID_2_2_1_1.setOpaque(true);
        lbl_ID_2_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_ID_2_2_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_ID_2_2_1_1.setBackground(new Color(255, 228, 225));
        panel_2_2_1_1.add(lbl_ID_2_2_1_1, BorderLayout.NORTH);
        
        JPanel panel_2_2_1_1_1 = new JPanel();
        panel_2_2_1_1.add(panel_2_2_1_1_1, BorderLayout.CENTER);
        
        textField_2_2_1_1_1 = new JTextField();
        textField_2_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_2_2_1_1_1.setColumns(10);
        GroupLayout gl_panel_2_2_1_1_1 = new GroupLayout(panel_2_2_1_1_1);
        gl_panel_2_2_1_1_1.setHorizontalGroup(
            gl_panel_2_2_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_2_2_1_1_1.createSequentialGroup()
                    .addGap(16)
                    .addComponent(textField_2_2_1_1_1)
                    .addGap(14))
        );
        gl_panel_2_2_1_1_1.setVerticalGroup(
            gl_panel_2_2_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2_2_1_1_1.createSequentialGroup()
                    .addGap(137)
                    .addComponent(textField_2_2_1_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(157, Short.MAX_VALUE))
        );
        panel_2_2_1_1_1.setLayout(gl_panel_2_2_1_1_1);
        
        JPanel panel_2_2_1_2 = new JPanel();
        panel_2_2_1.add(panel_2_2_1_2);
        panel_2_2_1_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_INFO_2_2_1_2 = new JLabel("Informazioni");
        lbl_INFO_2_2_1_2.setOpaque(true);
        lbl_INFO_2_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_INFO_2_2_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_INFO_2_2_1_2.setBackground(new Color(255, 228, 225));
        panel_2_2_1_2.add(lbl_INFO_2_2_1_2, BorderLayout.NORTH);
        
        JPanel panel_2_2_1_2_1 = new JPanel();
        panel_2_2_1_2.add(panel_2_2_1_2_1, BorderLayout.CENTER);
        panel_2_2_1_2_1.setLayout(new BorderLayout(0, 0));
        
        JComboBox comboBox_2_2_1_2_1 = new JComboBox();
        comboBox_2_2_1_2_1.addFocusListener(new FocusAdapter() {
        	@Override
        	
        	//Tendina che mostra l'elenco delle informazioni che ha il prodotto selezionato
        	public void focusGained(FocusEvent e) {
        		
        		try {
        			comboBox_2_2_1_2_1.removeAllItems();
            		
            		Prodotto tmp = (Prodotto)GUI.catenaAccesso.ottieniDallInventario(comboBox_2_2.getSelectedItem().toString()).get();
            		
            		for(String x : tmp.ottieniInfoTotali().keySet()) {
            			
            			comboBox_2_2_1_2_1.addItem(x.toString());
            			
            		}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore tendina informazioni del prodotto", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        		
        	}
        });
        
        panel_2_2_1_2_1.add(comboBox_2_2_1_2_1, BorderLayout.NORTH);
        
        JPanel panel_2_2_1_2_1_1 = new JPanel();
        panel_2_2_1_2_1.add(panel_2_2_1_2_1_1, BorderLayout.CENTER);
        panel_2_2_1_2_1_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_2_2_1_2_1_1_1 = new JPanel();
        panel_2_2_1_2_1_1.add(panel_2_2_1_2_1_1_1);
        panel_2_2_1_2_1_1_1.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId_2_2_1_2_1_1_1 = new JLabel("ID");
        lblId_2_2_1_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblId_2_2_1_2_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_2_2_1_2_1_1_1.add(lblId_2_2_1_2_1_1_1, BorderLayout.NORTH);
        
        JPanel panel_2_2_1_2_1_1_1_1 = new JPanel();
        panel_2_2_1_2_1_1_1.add(panel_2_2_1_2_1_1_1_1, BorderLayout.CENTER);
        
        textField_2_2_1_2_1_1_1 = new JTextField();
        textField_2_2_1_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_2_2_1_2_1_1_1.setColumns(10);
        GroupLayout gl_panel_2_2_1_2_1_1_1_1 = new GroupLayout(panel_2_2_1_2_1_1_1_1);
        gl_panel_2_2_1_2_1_1_1_1.setHorizontalGroup(
            gl_panel_2_2_1_2_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2_2_1_2_1_1_1_1.createSequentialGroup()
                    .addGap(15)
                    .addComponent(textField_2_2_1_2_1_1_1)
                    .addGap(15))
        );
        gl_panel_2_2_1_2_1_1_1_1.setVerticalGroup(
            gl_panel_2_2_1_2_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2_2_1_2_1_1_1_1.createSequentialGroup()
                    .addGap(49)
                    .addComponent(textField_2_2_1_2_1_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(52, Short.MAX_VALUE))
        );
        panel_2_2_1_2_1_1_1_1.setLayout(gl_panel_2_2_1_2_1_1_1_1);
        
        JPanel panel_2_2_1_2_1_1_2 = new JPanel();
        panel_2_2_1_2_1_1.add(panel_2_2_1_2_1_1_2);
        panel_2_2_1_2_1_1_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lblChiave_2_2_1_2_1_1_2 = new JLabel("Valore");
        lblChiave_2_2_1_2_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblChiave_2_2_1_2_1_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_2_2_1_2_1_1_2.add(lblChiave_2_2_1_2_1_1_2, BorderLayout.NORTH);
        
        JPanel panel_2_2_1_2_1_1_2_1 = new JPanel();
        panel_2_2_1_2_1_1_2.add(panel_2_2_1_2_1_1_2_1, BorderLayout.CENTER);
        
        textField_2_2_1_2_1_1_2_1 = new JTextField();
        textField_2_2_1_2_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_2_2_1_2_1_1_2_1.setColumns(10);
        GroupLayout gl_panel_2_2_1_2_1_1_2_1 = new GroupLayout(panel_2_2_1_2_1_1_2_1);
        gl_panel_2_2_1_2_1_1_2_1.setHorizontalGroup(
            gl_panel_2_2_1_2_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2_2_1_2_1_1_2_1.createSequentialGroup()
                    .addGap(15)
                    .addComponent(textField_2_2_1_2_1_1_2_1)
                    .addGap(15))
        );
        gl_panel_2_2_1_2_1_1_2_1.setVerticalGroup(
            gl_panel_2_2_1_2_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2_2_1_2_1_1_2_1.createSequentialGroup()
                    .addGap(46)
                    .addComponent(textField_2_2_1_2_1_1_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(55, Short.MAX_VALUE))
        );
        panel_2_2_1_2_1_1_2_1.setLayout(gl_panel_2_2_1_2_1_1_2_1);
        
        JPanel panel_2_2_1_3 = new JPanel();
        panel_2_2_1.add(panel_2_2_1_3);
        panel_2_2_1_3.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_SCARTO_2_2_1_3 = new JLabel("Scarto");
        lbl_SCARTO_2_2_1_3.setOpaque(true);
        lbl_SCARTO_2_2_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_SCARTO_2_2_1_3.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_SCARTO_2_2_1_3.setBackground(new Color(255, 228, 225));
        panel_2_2_1_3.add(lbl_SCARTO_2_2_1_3, BorderLayout.NORTH);
        
        JPanel panel_2_2_1_3_1 = new JPanel();
        panel_2_2_1_3.add(panel_2_2_1_3_1, BorderLayout.CENTER);
        panel_2_2_1_3_1.setLayout(new BorderLayout(0, 0));
        
        JComboBox comboBox_2_2_1_3_1 = new JComboBox();
        comboBox_2_2_1_3_1.addFocusListener(new FocusAdapter() {
        	@Override
        	
        	//Tendina con l'elenco degli scarti del prodotto selezionato
        	public void focusGained(FocusEvent e) {
        		
        		try {
        			comboBox_2_2_1_3_1.removeAllItems();
            		
            		Prodotto tmp = (Prodotto)GUI.catenaAccesso.ottieniDallInventario(comboBox_2_2.getSelectedItem().toString()).get();
            		
            		for(Scarto x : tmp.getScarti()) {
            			
            			comboBox_2_2_1_3_1.addItem(x.getID());       			
            		}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore tendina scarti del prodotto", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        		
        	}
        });
        panel_2_2_1_3_1.add(comboBox_2_2_1_3_1, BorderLayout.NORTH);
        
        JPanel panel_2_2_1_3_1_1 = new JPanel();
        panel_2_2_1_3_1.add(panel_2_2_1_3_1_1, BorderLayout.CENTER);
        panel_2_2_1_3_1_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_2_2_1_3_1_1_1 = new JPanel();
        panel_2_2_1_3_1_1.add(panel_2_2_1_3_1_1_1);
        panel_2_2_1_3_1_1_1.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId_2_2_1_3_1_1_1 = new JLabel("ID");
        lblId_2_2_1_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblId_2_2_1_3_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_2_2_1_3_1_1_1.add(lblId_2_2_1_3_1_1_1, BorderLayout.NORTH);
        
        JPanel panel_2_2_1_3_1_1_1_1 = new JPanel();
        panel_2_2_1_3_1_1_1.add(panel_2_2_1_3_1_1_1_1, BorderLayout.CENTER);
        
        textField_2_2_1_3_1_1_1 = new JTextField();
        textField_2_2_1_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_2_2_1_3_1_1_1.setColumns(10);
        GroupLayout gl_panel_2_2_1_3_1_1_1_1 = new GroupLayout(panel_2_2_1_3_1_1_1_1);
        gl_panel_2_2_1_3_1_1_1_1.setHorizontalGroup(
            gl_panel_2_2_1_3_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2_2_1_3_1_1_1_1.createSequentialGroup()
                    .addGap(15)
                    .addComponent(textField_2_2_1_3_1_1_1)
                    .addGap(15))
        );
        gl_panel_2_2_1_3_1_1_1_1.setVerticalGroup(
            gl_panel_2_2_1_3_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2_2_1_3_1_1_1_1.createSequentialGroup()
                    .addGap(24)
                    .addComponent(textField_2_2_1_3_1_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(28, Short.MAX_VALUE))
        );
        panel_2_2_1_3_1_1_1_1.setLayout(gl_panel_2_2_1_3_1_1_1_1);
        
        JPanel panel_2_2_1_3_1_1_2 = new JPanel();
        panel_2_2_1_3_1_1.add(panel_2_2_1_3_1_1_2);
        panel_2_2_1_3_1_1_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId_2_2_1_3_1_1_2 = new JLabel("Valore");
        lblId_2_2_1_3_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblId_2_2_1_3_1_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_2_2_1_3_1_1_2.add(lblId_2_2_1_3_1_1_2, BorderLayout.NORTH);
        
        JPanel panel_2_2_1_3_1_1_2_1 = new JPanel();
        panel_2_2_1_3_1_1_2.add(panel_2_2_1_3_1_1_2_1, BorderLayout.CENTER);
        
        textField_2_2_1_3_1_1_2_1 = new JTextField();
        textField_2_2_1_3_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_2_2_1_3_1_1_2_1.setColumns(10);
        GroupLayout gl_panel_2_2_1_3_1_1_2_1 = new GroupLayout(panel_2_2_1_3_1_1_2_1);
        gl_panel_2_2_1_3_1_1_2_1.setHorizontalGroup(
            gl_panel_2_2_1_3_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_2_2_1_3_1_1_2_1.createSequentialGroup()
                    .addGap(16)
                    .addComponent(textField_2_2_1_3_1_1_2_1)
                    .addGap(14))
        );
        gl_panel_2_2_1_3_1_1_2_1.setVerticalGroup(
            gl_panel_2_2_1_3_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2_2_1_3_1_1_2_1.createSequentialGroup()
                    .addGap(26)
                    .addComponent(textField_2_2_1_3_1_1_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(26, Short.MAX_VALUE))
        );
        panel_2_2_1_3_1_1_2_1.setLayout(gl_panel_2_2_1_3_1_1_2_1);
        
        JPanel panel_2_2_1_3_1_1_3 = new JPanel();
        panel_2_2_1_3_1_1.add(panel_2_2_1_3_1_1_3);
        panel_2_2_1_3_1_1_3.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId_2_2_1_3_1_1_3 = new JLabel("Percentuale");
        lblId_2_2_1_3_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblId_2_2_1_3_1_1_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_2_2_1_3_1_1_3.add(lblId_2_2_1_3_1_1_3, BorderLayout.NORTH);
        
        JPanel panel_2_2_1_3_1_1_3_1 = new JPanel();
        panel_2_2_1_3_1_1_3.add(panel_2_2_1_3_1_1_3_1, BorderLayout.CENTER);
        
        
        chckbx_2_2_1_3_1_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        GroupLayout gl_panel_2_2_1_3_1_1_3_1 = new GroupLayout(panel_2_2_1_3_1_1_3_1);
        gl_panel_2_2_1_3_1_1_3_1.setHorizontalGroup(
            gl_panel_2_2_1_3_1_1_3_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_2_2_1_3_1_1_3_1.createSequentialGroup()
                    .addGap(59)
                    .addComponent(chckbx_2_2_1_3_1_1_3_1, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addGap(50))
        );
        gl_panel_2_2_1_3_1_1_3_1.setVerticalGroup(
            gl_panel_2_2_1_3_1_1_3_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2_2_1_3_1_1_3_1.createSequentialGroup()
                    .addGap(22)
                    .addComponent(chckbx_2_2_1_3_1_1_3_1)
                    .addContainerGap(33, Short.MAX_VALUE))
        );
        panel_2_2_1_3_1_1_3_1.setLayout(gl_panel_2_2_1_3_1_1_3_1);
        
        JPanel panel_2_2_1_4 = new JPanel();
        panel_2_2_1.add(panel_2_2_1_4);
        panel_2_2_1_4.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_PADRE_2_2_1_4 = new JLabel("Padre");
        lbl_PADRE_2_2_1_4.setOpaque(true);
        lbl_PADRE_2_2_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_PADRE_2_2_1_4.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_PADRE_2_2_1_4.setBackground(new Color(255, 228, 225));
        panel_2_2_1_4.add(lbl_PADRE_2_2_1_4, BorderLayout.NORTH);
        
        JPanel panel_2_2_1_4_1 = new JPanel();
        panel_2_2_1_4.add(panel_2_2_1_4_1, BorderLayout.CENTER);
        
        textField_2_2_1_4_1 = new JTextField();
        textField_2_2_1_4_1.setEditable(false);
        textField_2_2_1_4_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_2_2_1_4_1.setColumns(10);
        GroupLayout gl_panel_2_2_1_4_1 = new GroupLayout(panel_2_2_1_4_1);
        gl_panel_2_2_1_4_1.setHorizontalGroup(
            gl_panel_2_2_1_4_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2_2_1_4_1.createSequentialGroup()
                    .addGap(14)
                    .addComponent(textField_2_2_1_4_1)
                    .addGap(16))
        );
        gl_panel_2_2_1_4_1.setVerticalGroup(
            gl_panel_2_2_1_4_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2_2_1_4_1.createSequentialGroup()
                    .addGap(141)
                    .addComponent(textField_2_2_1_4_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(153, Short.MAX_VALUE))
        );
        panel_2_2_1_4_1.setLayout(gl_panel_2_2_1_4_1);
        
        
        
        
        //-----------------------------------------------
        
        
        //--------------------- 3 -----------------------
        
        JPanel p3_panel_PCONCRETO = new JPanel();
        p3_panel_PCONCRETO.setBackground(new Color(240, 255, 255));
        
        p3_panel_PCONCRETO.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_PCONCRETO_3 = new JLabel("Prodotto concreto");
        lbl_PCONCRETO_3.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_PCONCRETO_3.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        p3_panel_PCONCRETO.add(lbl_PCONCRETO_3, BorderLayout.NORTH);
        
        JPanel panel_3_1 = new JPanel();
        panel_3_1.setBackground(new Color(255, 222, 173));
        p3_panel_PCONCRETO.add(panel_3_1, BorderLayout.SOUTH);
        
        JCheckBox chckbx_3_2_1_2_1_1_1_3_1 = new JCheckBox("");
        
        JButton btnSalva_3_1 = new JButton("Salva");
        btnSalva_3_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        		
	        		String idProdConcreto = textField_3_2_1_1_1_1.getText();
	        		String idBestForn = textField_3_2_1_1_2.getText();
	        		String pPiuBasso = textField_3_2_1_1_3_1.getText();
	        		String pMigliore = textField_3_2_1_1_4_1.getText();
	        		String idScarto = textField_3_2_1_2_1_1_1_1.getText();
	        		String valScarto = textField_3_2_1_2_1_1_1_2_1.getText();
	        		JCheckBox cb = chckbx_3_2_1_2_1_1_1_3_1;
	        		String padre = textField_3_2_1_2_2_1.getText();
	        		String idInfo = textField_3_2_1_2_3_1_1_1_1.getText();
	        		String valInfo = textField_3_2_1_2_3_1_1_2_1.getText();
	        		
	        		if(cb.isSelected()) {
	        			float n = Float.parseFloat(valScarto)/100;
	        			valScarto= String.valueOf(n);
	        		}
	        		
	        		setProdottoConcreto(idProdConcreto, padre, idScarto, valScarto, idInfo, valInfo);
        		}catch (Exception e1) {
        			e1.printStackTrace();
		        	  JOptionPane.showMessageDialog(null, "Controllare i campi inseriti", "ERRORE", JOptionPane.ERROR_MESSAGE);   
		         }

        	}
        });
        btnSalva_3_1.setPreferredSize(new Dimension(117, 50));
        panel_3_1.add(btnSalva_3_1);
        
        JComboBox comboBox_3_2 = new JComboBox();
        
        JButton btnModifica_3_1 = new JButton("Modifica");
        btnModifica_3_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        		String idProdConcreto = textField_3_2_1_1_1_1.getText();
        		String idBestForn = textField_3_2_1_1_2.getText();
        		String pPiuBasso = textField_3_2_1_1_3_1.getText();
        		String pMigliore = textField_3_2_1_1_4_1.getText();
        		String idScarto = textField_3_2_1_2_1_1_1_1.getText();
        		String valScarto = textField_3_2_1_2_1_1_1_2_1.getText();
        		JCheckBox cb = chckbx_3_2_1_2_1_1_1_3_1;
        		String padre = textField_3_2_1_2_2_1.getText();
        		String idInfo = textField_3_2_1_2_3_1_1_1_1.getText();
        		String valInfo = textField_3_2_1_2_3_1_1_2_1.getText();
        		
        		if(cb.isSelected()) {
        			float n = Float.parseFloat(valScarto)/100;
        			valScarto= String.valueOf(n);
        		}
        		
        		modificaProdottoConcreto(idProdConcreto, idScarto, valScarto, idInfo, valInfo);
        		
        		comboBox_3_2.removeAllItems();
        		
        		for(Typology x : (ArrayList<Typology>)GUI.catenaAccesso.getInventario()) {
        			if(x instanceof ProdConcreto) {
        				if(((ProdConcreto) x).getPadre().getID().equals(comboBox_2_2.getSelectedItem().toString()))
        					comboBox_3_2.addItem(x.getID());
        			}
        		}
        		
        		
        		}catch (Exception e1) {
		        	  JOptionPane.showMessageDialog(null, "Controllare i campi inseriti", "ERRORE", JOptionPane.ERROR_MESSAGE);   
		        }
        	}
        });
        btnModifica_3_1.setPreferredSize(new Dimension(117, 50));
        panel_3_1.add(btnModifica_3_1);
        
        JButton btnRimuovi_3_1 = new JButton("Rimuovi");
        btnRimuovi_3_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
	        		String idProdConcreto = textField_3_2_1_1_1_1.getText();
	        		String idBestForn = textField_3_2_1_1_2.getText();
	        		String pPiuBasso = textField_3_2_1_1_3_1.getText();
	        		String pMigliore = textField_3_2_1_1_4_1.getText();
	        		String idScarto = textField_3_2_1_2_1_1_1_1.getText();
	        		String valScarto = textField_3_2_1_2_1_1_1_2_1.getText();
	        		JCheckBox cb = chckbx_3_2_1_2_1_1_1_3_1;
	        		String padre = textField_3_2_1_2_2_1.getText();
	        		String idInfo = textField_3_2_1_2_3_1_1_1_1.getText();
	        		String valInfo = textField_3_2_1_2_3_1_1_2_1.getText();
	        		
	        		if(cb.isSelected()) {
	        			float n = Float.parseFloat(valScarto)/100;
	        			valScarto= String.valueOf(n);
	        		}
	        		
	        		rimuoviProdottoConcreto(idProdConcreto, idInfo, idScarto);
        		}catch (Exception e1) {
        			e1.printStackTrace();
		        	  JOptionPane.showMessageDialog(null, "Controllare i campi inseriti", "ERRORE", JOptionPane.ERROR_MESSAGE);   
		         }
        	}
        });
        btnRimuovi_3_1.setPreferredSize(new Dimension(117, 50));
        panel_3_1.add(btnRimuovi_3_1);
        
        JPanel panel_3_2 = new JPanel();
        panel_3_2.setBackground(new Color(144, 238, 144));
        p3_panel_PCONCRETO.add(panel_3_2, BorderLayout.CENTER);
        panel_3_2.setLayout(new BorderLayout(0, 0));
        
        
        comboBox_3_2.addFocusListener(new FocusAdapter() {
        	@Override
        	
        	//Tendina con l'elenco dei prodotti concreti relativi al prodotto selezionato nella tendina dei prodotti
        	public void focusGained(FocusEvent e) {
        		try {

	        		comboBox_3_2.removeAllItems();
	        		
	        		for(Typology x : (ArrayList<Typology>)GUI.catenaAccesso.getInventario()) {
	        			if(x instanceof ProdConcreto) {
	        				if(((ProdConcreto) x).getPadre().getID().equals(comboBox_2_2.getSelectedItem().toString()))
	        					comboBox_3_2.addItem(x.getID());
	        			}
	        		}
	        		
	        		//padre
	        		textField_3_2_1_2_2_1.setText(comboBox_2_2.getSelectedItem().toString());
	        		
	        		//Id Best Forn
	        		try {
	        			ProdConcreto p = (ProdConcreto)GUI.catenaAccesso.ottieniDallInventario(comboBox_3_2.getSelectedItem().toString()).get();
	        			if(p != null) {
			        		textField_3_2_1_1_2.setText(p.getIDMigliorFornitore(GUI.catenaAccesso));
			        		
			        		//Prezzo piu basso
			        		textField_3_2_1_1_3_1.setText(p.getPrezzoPiuBasso(GUI.catenaAccesso).toString());
			        		
			        		//Prezzo effettivo migliore
			        		textField_3_2_1_1_4_1.setText(p.getPrezzoEffettivoMigliore(GUI.catenaAccesso).toString());
	        			}
					} catch (Exception e2) {
						
					}
        		} catch (Exception e2) {
        			JOptionPane.showMessageDialog(null, "Errore tendina prodotti concreti", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        		
        	}
        });
        panel_3_2.add(comboBox_3_2, BorderLayout.NORTH);
        
        JPanel panel_3_2_1 = new JPanel();
        panel_3_2_1.setBackground(new Color(250, 250, 210));
        panel_3_2.add(panel_3_2_1, BorderLayout.CENTER);
        panel_3_2_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_3_2_1_1 = new JPanel();
        panel_3_2_1.add(panel_3_2_1_1);
        panel_3_2_1_1.setLayout(new GridLayout(0, 4, 0, 0));
        
        JPanel panel_3_2_1_1_1 = new JPanel();
        panel_3_2_1_1.add(panel_3_2_1_1_1);
        panel_3_2_1_1_1.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_ID_3_2_1_1_1 = new JLabel("ID");
        lbl_ID_3_2_1_1_1.setOpaque(true);
        lbl_ID_3_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_ID_3_2_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_ID_3_2_1_1_1.setBackground(new Color(255, 228, 225));
        panel_3_2_1_1_1.add(lbl_ID_3_2_1_1_1, BorderLayout.NORTH);
        
        JPanel panel_3_2_1_1_1_1 = new JPanel();
        panel_3_2_1_1_1.add(panel_3_2_1_1_1_1, BorderLayout.CENTER);
        
        textField_3_2_1_1_1_1 = new JTextField();
        textField_3_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_3_2_1_1_1_1.setColumns(10);
        GroupLayout gl_panel_3_2_1_1_1_1 = new GroupLayout(panel_3_2_1_1_1_1);
        gl_panel_3_2_1_1_1_1.setHorizontalGroup(
            gl_panel_3_2_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_3_2_1_1_1_1.createSequentialGroup()
                    .addGap(15)
                    .addComponent(textField_3_2_1_1_1_1)
                    .addGap(15))
        );
        gl_panel_3_2_1_1_1_1.setVerticalGroup(
            gl_panel_3_2_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_3_2_1_1_1_1.createSequentialGroup()
                    .addGap(140)
                    .addComponent(textField_3_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(137, Short.MAX_VALUE))
        );
        panel_3_2_1_1_1_1.setLayout(gl_panel_3_2_1_1_1_1);
        
        JPanel panel_3_2_1_1_2 = new JPanel();
        panel_3_2_1_1.add(panel_3_2_1_1_2);
        panel_3_2_1_1_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_ID_3_2_1_1_2 = new JLabel("ID Best Forn.");
        lbl_ID_3_2_1_1_2.setOpaque(true);
        lbl_ID_3_2_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_ID_3_2_1_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_ID_3_2_1_1_2.setBackground(new Color(255, 228, 225));
        panel_3_2_1_1_2.add(lbl_ID_3_2_1_1_2, BorderLayout.NORTH);
        
        JPanel panel_3_2_1_1_2_1 = new JPanel();
        panel_3_2_1_1_2.add(panel_3_2_1_1_2_1, BorderLayout.CENTER);
        
        textField_3_2_1_1_2 = new JTextField();
        textField_3_2_1_1_2.setEditable(false);
        textField_3_2_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        textField_3_2_1_1_2.setColumns(10);
        GroupLayout gl_panel_3_2_1_1_2_1 = new GroupLayout(panel_3_2_1_1_2_1);
        gl_panel_3_2_1_1_2_1.setHorizontalGroup(
            gl_panel_3_2_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_3_2_1_1_2_1.createSequentialGroup()
                    .addGap(15)
                    .addComponent(textField_3_2_1_1_2)
                    .addGap(15))
        );
        gl_panel_3_2_1_1_2_1.setVerticalGroup(
            gl_panel_3_2_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_3_2_1_1_2_1.createSequentialGroup()
                    .addGap(140)
                    .addComponent(textField_3_2_1_1_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(137, Short.MAX_VALUE))
        );
        panel_3_2_1_1_2_1.setLayout(gl_panel_3_2_1_1_2_1);
        
        JPanel panel_3_2_1_1_3 = new JPanel();
        panel_3_2_1_1.add(panel_3_2_1_1_3);
        panel_3_2_1_1_3.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_ID_3_2_1_1_3 = new JLabel("P. più basso");
        lbl_ID_3_2_1_1_3.setOpaque(true);
        lbl_ID_3_2_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_ID_3_2_1_1_3.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_ID_3_2_1_1_3.setBackground(new Color(255, 228, 225));
        panel_3_2_1_1_3.add(lbl_ID_3_2_1_1_3, BorderLayout.NORTH);
        
        JPanel panel_3_2_1_1_3_1 = new JPanel();
        panel_3_2_1_1_3.add(panel_3_2_1_1_3_1, BorderLayout.CENTER);
        
        textField_3_2_1_1_3_1 = new JTextField();
        textField_3_2_1_1_3_1.setEditable(false);
        textField_3_2_1_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_3_2_1_1_3_1.setColumns(10);
        GroupLayout gl_panel_3_2_1_1_3_1 = new GroupLayout(panel_3_2_1_1_3_1);
        gl_panel_3_2_1_1_3_1.setHorizontalGroup(
            gl_panel_3_2_1_1_3_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_3_2_1_1_3_1.createSequentialGroup()
                    .addGap(15)
                    .addComponent(textField_3_2_1_1_3_1)
                    .addGap(15))
        );
        gl_panel_3_2_1_1_3_1.setVerticalGroup(
            gl_panel_3_2_1_1_3_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_3_2_1_1_3_1.createSequentialGroup()
                    .addGap(140)
                    .addComponent(textField_3_2_1_1_3_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(137, Short.MAX_VALUE))
        );
        panel_3_2_1_1_3_1.setLayout(gl_panel_3_2_1_1_3_1);
        
        JPanel panel_3_2_1_1_4 = new JPanel();
        panel_3_2_1_1.add(panel_3_2_1_1_4);
        panel_3_2_1_1_4.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_ID_3_2_1_1_4 = new JLabel("P. migliore");
        lbl_ID_3_2_1_1_4.setOpaque(true);
        lbl_ID_3_2_1_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_ID_3_2_1_1_4.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_ID_3_2_1_1_4.setBackground(new Color(255, 228, 225));
        panel_3_2_1_1_4.add(lbl_ID_3_2_1_1_4, BorderLayout.NORTH);
        
        JPanel panel_3_2_1_1_4_1 = new JPanel();
        panel_3_2_1_1_4.add(panel_3_2_1_1_4_1, BorderLayout.CENTER);
        
        textField_3_2_1_1_4_1 = new JTextField();
        textField_3_2_1_1_4_1.setEditable(false);
        textField_3_2_1_1_4_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_3_2_1_1_4_1.setColumns(10);
        GroupLayout gl_panel_3_2_1_1_4_1 = new GroupLayout(panel_3_2_1_1_4_1);
        gl_panel_3_2_1_1_4_1.setHorizontalGroup(
            gl_panel_3_2_1_1_4_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_3_2_1_1_4_1.createSequentialGroup()
                    .addGap(14)
                    .addComponent(textField_3_2_1_1_4_1)
                    .addGap(16))
        );
        gl_panel_3_2_1_1_4_1.setVerticalGroup(
            gl_panel_3_2_1_1_4_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_3_2_1_1_4_1.createSequentialGroup()
                    .addGap(140)
                    .addComponent(textField_3_2_1_1_4_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(137, Short.MAX_VALUE))
        );
        panel_3_2_1_1_4_1.setLayout(gl_panel_3_2_1_1_4_1);
        
        final JPanel panel_3_2_1_2 = new JPanel();
        panel_3_2_1.add(panel_3_2_1_2);
        panel_3_2_1_2.setLayout(new GridLayout(0, 3, 0, 0));
        
        JPanel panel_3_2_1_2_1 = new JPanel();
        panel_3_2_1_2.add(panel_3_2_1_2_1);
        panel_3_2_1_2_1.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_ID_3_2_1_2_1 = new JLabel("Scarti");
        lbl_ID_3_2_1_2_1.setOpaque(true);
        lbl_ID_3_2_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_ID_3_2_1_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_ID_3_2_1_2_1.setBackground(new Color(255, 228, 225));
        panel_3_2_1_2_1.add(lbl_ID_3_2_1_2_1, BorderLayout.NORTH);
        
        JPanel panel_3_2_1_2_1_1 = new JPanel();
        panel_3_2_1_2_1.add(panel_3_2_1_2_1_1, BorderLayout.CENTER);
        panel_3_2_1_2_1_1.setLayout(new BorderLayout(0, 0));
        
        JComboBox comboBox_3_2_1_2_1_1 = new JComboBox();
        comboBox_3_2_1_2_1_1.addFocusListener(new FocusAdapter() {
        	@Override
        	
        	//Tendina con elenco degli scarti del prodotto concreto selezionato
        	public void focusGained(FocusEvent e) {
        		
        		try {
        			comboBox_3_2_1_2_1_1.removeAllItems();
            		
            		ProdConcreto tmp = (ProdConcreto)GUI.catenaAccesso.ottieniDallInventario(comboBox_3_2.getSelectedItem().toString()).get();
            		
            		for(Scarto x : tmp.ottieniScartiTotali()) {
            			
            			comboBox_3_2_1_2_1_1.addItem(x.getID());
            			
            		}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore tendina scarti prod.concreto", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        		
        		
        	}
        });
        
        panel_3_2_1_2_1_1.add(comboBox_3_2_1_2_1_1, BorderLayout.NORTH);
        
        JPanel panel_3_2_1_2_1_1_1 = new JPanel();
        panel_3_2_1_2_1_1.add(panel_3_2_1_2_1_1_1, BorderLayout.CENTER);
        panel_3_2_1_2_1_1_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_3_2_1_2_1_1_1_1 = new JPanel();
        panel_3_2_1_2_1_1_1.add(panel_3_2_1_2_1_1_1_1);
        panel_3_2_1_2_1_1_1_1.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId_3_2_1_2_1_1_1_1 = new JLabel("ID");
        lblId_3_2_1_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblId_3_2_1_2_1_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_3_2_1_2_1_1_1_1.add(lblId_3_2_1_2_1_1_1_1, BorderLayout.NORTH);
        
        JPanel panel_3_2_1_2_1_1_1_1_1 = new JPanel();
        panel_3_2_1_2_1_1_1_1.add(panel_3_2_1_2_1_1_1_1_1, BorderLayout.CENTER);
        
        textField_3_2_1_2_1_1_1_1 = new JTextField();
        textField_3_2_1_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_3_2_1_2_1_1_1_1.setColumns(10);
        GroupLayout gl_panel_3_2_1_2_1_1_1_1_1 = new GroupLayout(panel_3_2_1_2_1_1_1_1_1);
        gl_panel_3_2_1_2_1_1_1_1_1.setHorizontalGroup(
            gl_panel_3_2_1_2_1_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_3_2_1_2_1_1_1_1_1.createSequentialGroup()
                    .addGap(39)
                    .addComponent(textField_3_2_1_2_1_1_1_1)
                    .addGap(44))
        );
        gl_panel_3_2_1_2_1_1_1_1_1.setVerticalGroup(
            gl_panel_3_2_1_2_1_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_3_2_1_2_1_1_1_1_1.createSequentialGroup()
                    .addGap(24)
                    .addComponent(textField_3_2_1_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(23, Short.MAX_VALUE))
        );
        panel_3_2_1_2_1_1_1_1_1.setLayout(gl_panel_3_2_1_2_1_1_1_1_1);
        
        JPanel panel_3_2_1_2_1_1_1_2 = new JPanel();
        panel_3_2_1_2_1_1_1.add(panel_3_2_1_2_1_1_1_2);
        panel_3_2_1_2_1_1_1_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId_3_2_1_2_1_1_1_2 = new JLabel("Valore");
        lblId_3_2_1_2_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblId_3_2_1_2_1_1_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_3_2_1_2_1_1_1_2.add(lblId_3_2_1_2_1_1_1_2, BorderLayout.NORTH);
        
        JPanel panel_3_2_1_2_1_1_1_2_1 = new JPanel();
        panel_3_2_1_2_1_1_1_2.add(panel_3_2_1_2_1_1_1_2_1, BorderLayout.CENTER);
        
        textField_3_2_1_2_1_1_1_2_1 = new JTextField();
        textField_3_2_1_2_1_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_3_2_1_2_1_1_1_2_1.setColumns(10);
        GroupLayout gl_panel_3_2_1_2_1_1_1_2_1 = new GroupLayout(panel_3_2_1_2_1_1_1_2_1);
        gl_panel_3_2_1_2_1_1_1_2_1.setHorizontalGroup(
            gl_panel_3_2_1_2_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_3_2_1_2_1_1_1_2_1.createSequentialGroup()
                    .addGap(41)
                    .addComponent(textField_3_2_1_2_1_1_1_2_1)
                    .addGap(42))
        );
        gl_panel_3_2_1_2_1_1_1_2_1.setVerticalGroup(
            gl_panel_3_2_1_2_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_3_2_1_2_1_1_1_2_1.createSequentialGroup()
                    .addGap(24)
                    .addComponent(textField_3_2_1_2_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(23, Short.MAX_VALUE))
        );
        panel_3_2_1_2_1_1_1_2_1.setLayout(gl_panel_3_2_1_2_1_1_1_2_1);
        
        JPanel panel_3_2_1_2_1_1_1_3 = new JPanel();
        panel_3_2_1_2_1_1_1.add(panel_3_2_1_2_1_1_1_3);
        panel_3_2_1_2_1_1_1_3.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId_3_2_1_2_1_1_1_3 = new JLabel("Percentuale");
        lblId_3_2_1_2_1_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblId_3_2_1_2_1_1_1_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_3_2_1_2_1_1_1_3.add(lblId_3_2_1_2_1_1_1_3, BorderLayout.NORTH);
        
        JPanel panel_3_2_1_2_1_1_1_3_1 = new JPanel();
        panel_3_2_1_2_1_1_1_3.add(panel_3_2_1_2_1_1_1_3_1, BorderLayout.CENTER);
        
      
        chckbx_3_2_1_2_1_1_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        GroupLayout gl_panel_3_2_1_2_1_1_1_3_1 = new GroupLayout(panel_3_2_1_2_1_1_1_3_1);
        gl_panel_3_2_1_2_1_1_1_3_1.setHorizontalGroup(
            gl_panel_3_2_1_2_1_1_1_3_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_3_2_1_2_1_1_1_3_1.createSequentialGroup()
                    .addGap(81)
                    .addComponent(chckbx_3_2_1_2_1_1_1_3_1, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addGap(81))
        );
        gl_panel_3_2_1_2_1_1_1_3_1.setVerticalGroup(
            gl_panel_3_2_1_2_1_1_1_3_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_3_2_1_2_1_1_1_3_1.createSequentialGroup()
                    .addGap(22)
                    .addComponent(chckbx_3_2_1_2_1_1_1_3_1)
                    .addContainerGap(28, Short.MAX_VALUE))
        );
        panel_3_2_1_2_1_1_1_3_1.setLayout(gl_panel_3_2_1_2_1_1_1_3_1);
        
        JPanel panel_3_2_1_2_2 = new JPanel();
        panel_3_2_1_2.add(panel_3_2_1_2_2);
        panel_3_2_1_2_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_3_2_1_2_2 = new JLabel("Padre");
        lbl_3_2_1_2_2.setOpaque(true);
        lbl_3_2_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_3_2_1_2_2.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_3_2_1_2_2.setBackground(new Color(255, 228, 225));
        panel_3_2_1_2_2.add(lbl_3_2_1_2_2, BorderLayout.NORTH);
        
        JPanel panel_3_2_1_2_2_1 = new JPanel();
        panel_3_2_1_2_2.add(panel_3_2_1_2_2_1, BorderLayout.CENTER);
        
        textField_3_2_1_2_2_1 = new JTextField();
        textField_3_2_1_2_2_1.setEditable(false);
        textField_3_2_1_2_2_1.setColumns(10);
        GroupLayout gl_panel_3_2_1_2_2_1 = new GroupLayout(panel_3_2_1_2_2_1);
        gl_panel_3_2_1_2_2_1.setHorizontalGroup(
            gl_panel_3_2_1_2_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_3_2_1_2_2_1.createSequentialGroup()
                    .addGap(26)
                    .addComponent(textField_3_2_1_2_2_1, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addGap(21))
        );
        gl_panel_3_2_1_2_2_1.setVerticalGroup(
            gl_panel_3_2_1_2_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_3_2_1_2_2_1.createSequentialGroup()
                    .addGap(140)
                    .addComponent(textField_3_2_1_2_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(137, Short.MAX_VALUE))
        );
        panel_3_2_1_2_2_1.setLayout(gl_panel_3_2_1_2_2_1);
        
        JPanel panel_3_2_1_2_3 = new JPanel();
        panel_3_2_1_2.add(panel_3_2_1_2_3);
        panel_3_2_1_2_3.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_3_2_1_2_3 = new JLabel("Informazioni");
        lbl_3_2_1_2_3.setOpaque(true);
        lbl_3_2_1_2_3.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_3_2_1_2_3.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_3_2_1_2_3.setBackground(new Color(255, 228, 225));
        panel_3_2_1_2_3.add(lbl_3_2_1_2_3, BorderLayout.NORTH);
        
        JPanel panel_3_2_1_2_3_1 = new JPanel();
        panel_3_2_1_2_3.add(panel_3_2_1_2_3_1, BorderLayout.CENTER);
        panel_3_2_1_2_3_1.setLayout(new BorderLayout(0, 0));
        
        JComboBox comboBox_3_2_1_2_3_1 = new JComboBox();
        comboBox_3_2_1_2_3_1.addFocusListener(new FocusAdapter() {
        	@Override
        	
        	//Tendina con elenco delle informazioni del prodotto concreto selezionato
        	public void focusGained(FocusEvent e) {
        		
        		try {
        			comboBox_3_2_1_2_3_1.removeAllItems();
            		
            		ProdConcreto tmp = (ProdConcreto)GUI.catenaAccesso.ottieniDallInventario(comboBox_3_2.getSelectedItem().toString()).get();
            		
            		for(String x : tmp.ottieniInfoTotali().keySet()) {
            			
            			comboBox_3_2_1_2_3_1.addItem(x);
            			
            		}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore tendina informazioni prod.concreto", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        		
        	}
        });
        panel_3_2_1_2_3_1.add(comboBox_3_2_1_2_3_1, BorderLayout.NORTH);
        
        JPanel panel_3_2_1_2_3_1_1 = new JPanel();
        panel_3_2_1_2_3_1.add(panel_3_2_1_2_3_1_1, BorderLayout.CENTER);
        panel_3_2_1_2_3_1_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_3_2_1_2_3_1_1_1 = new JPanel();
        panel_3_2_1_2_3_1_1.add(panel_3_2_1_2_3_1_1_1);
        panel_3_2_1_2_3_1_1_1.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId_3_2_1_2_3_1_1_1 = new JLabel("ID");
        lblId_3_2_1_2_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblId_3_2_1_2_3_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_3_2_1_2_3_1_1_1.add(lblId_3_2_1_2_3_1_1_1, BorderLayout.NORTH);
        
        JPanel panel_3_2_1_2_3_1_1_1_1 = new JPanel();
        panel_3_2_1_2_3_1_1_1.add(panel_3_2_1_2_3_1_1_1_1, BorderLayout.CENTER);
        
        textField_3_2_1_2_3_1_1_1_1 = new JTextField();
        textField_3_2_1_2_3_1_1_1_1.setColumns(10);
        GroupLayout gl_panel_3_2_1_2_3_1_1_1_1 = new GroupLayout(panel_3_2_1_2_3_1_1_1_1);
        gl_panel_3_2_1_2_3_1_1_1_1.setHorizontalGroup(
            gl_panel_3_2_1_2_3_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_3_2_1_2_3_1_1_1_1.createSequentialGroup()
                    .addGap(43)
                    .addComponent(textField_3_2_1_2_3_1_1_1_1)
                    .addGap(40))
        );
        gl_panel_3_2_1_2_3_1_1_1_1.setVerticalGroup(
            gl_panel_3_2_1_2_3_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_3_2_1_2_3_1_1_1_1.createSequentialGroup()
                    .addGap(47)
                    .addComponent(textField_3_2_1_2_3_1_1_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(46, Short.MAX_VALUE))
        );
        panel_3_2_1_2_3_1_1_1_1.setLayout(gl_panel_3_2_1_2_3_1_1_1_1);
        
        JPanel panel_3_2_1_2_3_1_1_2 = new JPanel();
        panel_3_2_1_2_3_1_1.add(panel_3_2_1_2_3_1_1_2);
        panel_3_2_1_2_3_1_1_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId_3_2_1_2_3_1_1_2 = new JLabel("Valore");
        lblId_3_2_1_2_3_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblId_3_2_1_2_3_1_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_3_2_1_2_3_1_1_2.add(lblId_3_2_1_2_3_1_1_2, BorderLayout.NORTH);
        
        JPanel panel_3_2_1_2_3_1_1_2_1 = new JPanel();
        panel_3_2_1_2_3_1_1_2.add(panel_3_2_1_2_3_1_1_2_1, BorderLayout.CENTER);
        
        textField_3_2_1_2_3_1_1_2_1 = new JTextField();
        textField_3_2_1_2_3_1_1_2_1.setColumns(10);
        GroupLayout gl_panel_3_2_1_2_3_1_1_2_1 = new GroupLayout(panel_3_2_1_2_3_1_1_2_1);
        gl_panel_3_2_1_2_3_1_1_2_1.setHorizontalGroup(
            gl_panel_3_2_1_2_3_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_3_2_1_2_3_1_1_2_1.createSequentialGroup()
                    .addGap(45)
                    .addComponent(textField_3_2_1_2_3_1_1_2_1)
                    .addGap(38))
        );
        gl_panel_3_2_1_2_3_1_1_2_1.setVerticalGroup(
            gl_panel_3_2_1_2_3_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_3_2_1_2_3_1_1_2_1.createSequentialGroup()
                    .addGap(48)
                    .addComponent(textField_3_2_1_2_3_1_1_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE))
        );
        panel_3_2_1_2_3_1_1_2_1.setLayout(gl_panel_3_2_1_2_3_1_1_2_1);
   
        //-----------------------------------------------
        
        
        //--------------------- 4 -----------------------  
        
        JPanel p4_panel_PFORNITO = new JPanel();
        p4_panel_PFORNITO.setBackground(new Color(240, 255, 255));
        p4_panel_PFORNITO.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_PFORNITE_2 = new JLabel("Prodotto fornito");
        lbl_PFORNITE_2.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_PFORNITE_2.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        p4_panel_PFORNITO.add(lbl_PFORNITE_2, BorderLayout.NORTH);
        
        JPanel panel_4_1 = new JPanel();
        panel_4_1.setBackground(new Color(255, 222, 173));
        p4_panel_PFORNITO.add(panel_4_1, BorderLayout.SOUTH);
        
        JCheckBox chckbx_4_2_1_1_2_3_1_1_3_1 = new JCheckBox("");
        
        JButton btnSalva_4_1 = new JButton("Salva");
        btnSalva_4_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
	        		String id = textField_4_2_1_1_1_1_1.getText();
	        		String idForn = textField_4_2_1_1_1_2_1.getText();
	        		String prezzo = textField_4_2_1_1_1_3_1.getText();
	        		String idInfo = textField_4_2_1_1_2_1_1_1_1_1.getText();
	        		String valInfo = textField_4_2_1_1_2_1_1_1_2_1.getText();
	        		String pEff = textField_4_2_1_1_2_2_1.getText();
	        		String idScarti = textField_4_2_1_1_2_3_1_1_1_1.getText();
	        		String valScarti = textField_4_2_1_1_2_3_1_1_2_1.getText();
	        		JCheckBox cb = chckbx_4_2_1_1_2_3_1_1_3_1;
	        		String valAssoluto = textField_4_2_1_1_3_1_1.getText();
	        		String valNetto = textField_4_2_1_1_3_2_1.getText();
	        		String percNetto = textField_4_2_1_1_3_3_1.getText();
	        		String padre = textField_4_2_1_1_3_4_1.getText();
	        		
	        		if(cb.isSelected()) {
	        			float n = Float.parseFloat(valScarti)/100;
	        			valScarti= String.valueOf(n);
	        		}
	        		
	        		
	        		
	        		setProdottoFornito (id, padre, idScarti,
	        				valScarti, idInfo, valInfo, idForn, prezzo,
	        				valAssoluto);
        		}catch (Exception e1) {
        			//e1.printStackTrace();
		        	  JOptionPane.showMessageDialog(null, "Controllare i campi inseriti", "ERRORE", JOptionPane.ERROR_MESSAGE);   
		        }
        		
        	}
        });
        btnSalva_4_1.setPreferredSize(new Dimension(117, 50));
        panel_4_1.add(btnSalva_4_1);
        
        JComboBox comboBox_4_2 = new JComboBox();
        
        JButton btnModifica_4_1 = new JButton("Modifica");
        btnModifica_4_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		

        		try {
	        		String id = textField_4_2_1_1_1_1_1.getText();
	        		String idForn = textField_4_2_1_1_1_2_1.getText();
	        		String prezzo = textField_4_2_1_1_1_3_1.getText();
	        		String idInfo = textField_4_2_1_1_2_1_1_1_1_1.getText();
	        		String valInfo = textField_4_2_1_1_2_1_1_1_2_1.getText();
	        		String pEff = textField_4_2_1_1_2_2_1.getText();
	        		String idScarti = textField_4_2_1_1_2_3_1_1_1_1.getText();
	        		String valScarti = textField_4_2_1_1_2_3_1_1_2_1.getText();
	        		JCheckBox cb = chckbx_4_2_1_1_2_3_1_1_3_1;
	        		String valAssoluto = textField_4_2_1_1_3_1_1.getText();
	        		String valNetto = textField_4_2_1_1_3_2_1.getText();
	        		String percNetto = textField_4_2_1_1_3_3_1.getText();
	        		String padre = textField_4_2_1_1_3_4_1.getText();
	        		
	        		if(cb.isSelected()) {
	        			float n = Float.parseFloat(valScarti)/100;
	        			valScarti= String.valueOf(n);
	        		}
	        		
	        		modificaProdottoFornito (id, idScarti,valScarti, idInfo, valInfo, idForn, prezzo);
	        		
	        		comboBox_4_2.removeAllItems();
	
	        		for(Typology x : (ArrayList<Typology>)GUI.catenaAccesso.getInventario()) {
	        			if(x instanceof ProdFornito) {
	        				if(((ProdFornito) x).getPadre().getID().equals(comboBox_3_2.getSelectedItem().toString()))
	        					comboBox_4_2.addItem(x.getID());
	        			}
	        		}
        		
        		}catch (Exception e1) {
        			e1.printStackTrace();
		        	  JOptionPane.showMessageDialog(null, "Controllare i campi inseriti", "ERRORE", JOptionPane.ERROR_MESSAGE);   
		         }
        		
        	}
        });
        btnModifica_4_1.setPreferredSize(new Dimension(117, 50));
        panel_4_1.add(btnModifica_4_1);
        
        JButton btnRimuovi_4_1 = new JButton("Rimuovi");
        btnRimuovi_4_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
	        		String id = textField_4_2_1_1_1_1_1.getText();
	        		String idForn = textField_4_2_1_1_1_2_1.getText();
	        		String prezzo = textField_4_2_1_1_1_3_1.getText();
	        		String idInfo = textField_4_2_1_1_2_1_1_1_1_1.getText();
	        		String valInfo = textField_4_2_1_1_2_1_1_1_2_1.getText();
	        		String pEff = textField_4_2_1_1_2_2_1.getText();
	        		String idScarti = textField_4_2_1_1_2_3_1_1_1_1.getText();
	        		String valScarti = textField_4_2_1_1_2_3_1_1_2_1.getText();
	        		JCheckBox cb = chckbx_4_2_1_1_2_3_1_1_3_1;
	        		String valAssoluto = textField_4_2_1_1_3_1_1.getText();
	        		String valNetto = textField_4_2_1_1_3_2_1.getText();
	        		String percNetto = textField_4_2_1_1_3_3_1.getText();
	        		String padre = textField_4_2_1_1_3_4_1.getText();
	        		
	        		if(cb.isSelected()) {
	        			float n = Float.parseFloat(valScarti)/100;
	        			valScarti= String.valueOf(n);
	        		}
	        		
	        		rimuoviProdottoFornito (id, idInfo, idScarti);
        		}catch (Exception e1) {
        			e1.printStackTrace();
		        	  JOptionPane.showMessageDialog(null, "Controllare i campi inseriti", "ERRORE", JOptionPane.ERROR_MESSAGE);   
		         }
        		
        	}
        });
        btnRimuovi_4_1.setPreferredSize(new Dimension(117, 50));
        panel_4_1.add(btnRimuovi_4_1);
        
        JPanel panel_4_2 = new JPanel();
        panel_4_2.setBackground(new Color(144, 238, 144));
        p4_panel_PFORNITO.add(panel_4_2, BorderLayout.CENTER);
        panel_4_2.setLayout(new BorderLayout(0, 0));
        
        
        comboBox_4_2.addFocusListener(new FocusAdapter() {
        	@Override
        	
        	//Tendina con'lelenco dei prodotti forniti relativi al prodotto concreto scelto sopra
        	public void focusGained(FocusEvent e) {
        		
        		try {
        			comboBox_4_2.removeAllItems();
            		
            		for(Typology x : (ArrayList<Typology>)GUI.catenaAccesso.getInventario()) {
            			if(x instanceof ProdFornito) {
            				if(((ProdFornito) x).getPadre().getID().equals(comboBox_3_2.getSelectedItem().toString()))
            					comboBox_4_2.addItem(x.getID());
            			}
            		}
            		
            		//Padre
            		textField_4_2_1_1_3_4_1.setText(comboBox_3_2.getSelectedItem().toString());
            		
            		try {
            			//Val netto
                		ProdFornito p = (ProdFornito)GUI.catenaAccesso.ottieniDallInventario(comboBox_3_2.getSelectedItem().toString()).get();
                		
                		textField_4_2_1_1_3_2_1.setText(p.getValoreNetto().toString());
                		
                		//Val effettivo
                		textField_4_2_1_1_2_2_1.setText(p.getPrezzoEffettivo().toString());
                		
                		//Percentuale netto
                		textField_4_2_1_1_3_3_1.setText(p.getPercentualeNetto().toString());
    				} catch (Exception e2) {
    					
    				}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore tendina elenco prod.forniti", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        		
        		
        		
        	}
        });
        panel_4_2.add(comboBox_4_2, BorderLayout.NORTH);
        
        JPanel panel_4_2_1 = new JPanel();
        panel_4_2_1.setBackground(new Color(250, 250, 210));
        panel_4_2.add(panel_4_2_1, BorderLayout.CENTER);
        panel_4_2_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_4_2_1_1 = new JPanel();
        panel_4_2_1.add(panel_4_2_1_1);
        panel_4_2_1_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_4_2_1_1_1 = new JPanel();
        panel_4_2_1_1.add(panel_4_2_1_1_1);
        panel_4_2_1_1_1.setLayout(new GridLayout(0, 3, 0, 0));
        
        JPanel panel_4_2_1_1_1_1 = new JPanel();
        panel_4_2_1_1_1.add(panel_4_2_1_1_1_1);
        panel_4_2_1_1_1_1.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_ID_4_2_1_1_1_1 = new JLabel("ID");
        lbl_ID_4_2_1_1_1_1.setOpaque(true);
        lbl_ID_4_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_ID_4_2_1_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_ID_4_2_1_1_1_1.setBackground(new Color(255, 228, 225));
        panel_4_2_1_1_1_1.add(lbl_ID_4_2_1_1_1_1, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_1_1_1 = new JPanel();
        panel_4_2_1_1_1_1.add(panel_4_2_1_1_1_1_1, BorderLayout.CENTER);
        
        textField_4_2_1_1_1_1_1 = new JTextField();
        textField_4_2_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4_2_1_1_1_1_1.setColumns(10);
        GroupLayout gl_panel_4_2_1_1_1_1_1 = new GroupLayout(panel_4_2_1_1_1_1_1);
        gl_panel_4_2_1_1_1_1_1.setHorizontalGroup(
            gl_panel_4_2_1_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_1_1_1.createSequentialGroup()
                    .addGap(23)
                    .addComponent(textField_4_2_1_1_1_1_1, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addGap(25))
        );
        gl_panel_4_2_1_1_1_1_1.setVerticalGroup(
            gl_panel_4_2_1_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_1_1_1.createSequentialGroup()
                    .addGap(79)
                    .addComponent(textField_4_2_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(87, Short.MAX_VALUE))
        );
        panel_4_2_1_1_1_1_1.setLayout(gl_panel_4_2_1_1_1_1_1);
        
        JPanel panel_4_2_1_1_1_2 = new JPanel();
        panel_4_2_1_1_1.add(panel_4_2_1_1_1_2);
        panel_4_2_1_1_1_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_ID_4_2_1_1_1_2 = new JLabel("ID Forn.");
        lbl_ID_4_2_1_1_1_2.setOpaque(true);
        lbl_ID_4_2_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_ID_4_2_1_1_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_ID_4_2_1_1_1_2.setBackground(new Color(255, 228, 225));
        panel_4_2_1_1_1_2.add(lbl_ID_4_2_1_1_1_2, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_1_2_1 = new JPanel();
        panel_4_2_1_1_1_2.add(panel_4_2_1_1_1_2_1, BorderLayout.CENTER);
        
        textField_4_2_1_1_1_2_1 = new JTextField();
        textField_4_2_1_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4_2_1_1_1_2_1.setColumns(10);
        GroupLayout gl_panel_4_2_1_1_1_2_1 = new GroupLayout(panel_4_2_1_1_1_2_1);
        gl_panel_4_2_1_1_1_2_1.setHorizontalGroup(
            gl_panel_4_2_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_1_2_1.createSequentialGroup()
                    .addGap(21)
                    .addComponent(textField_4_2_1_1_1_2_1, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addGap(27))
        );
        gl_panel_4_2_1_1_1_2_1.setVerticalGroup(
            gl_panel_4_2_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_1_2_1.createSequentialGroup()
                    .addGap(79)
                    .addComponent(textField_4_2_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(87, Short.MAX_VALUE))
        );
        panel_4_2_1_1_1_2_1.setLayout(gl_panel_4_2_1_1_1_2_1);
        
        JPanel panel_4_2_1_1_1_3 = new JPanel();
        panel_4_2_1_1_1.add(panel_4_2_1_1_1_3);
        panel_4_2_1_1_1_3.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_ID_4_2_1_1_1_3 = new JLabel("Prezzo");
        lbl_ID_4_2_1_1_1_3.setOpaque(true);
        lbl_ID_4_2_1_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_ID_4_2_1_1_1_3.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_ID_4_2_1_1_1_3.setBackground(new Color(255, 228, 225));
        panel_4_2_1_1_1_3.add(lbl_ID_4_2_1_1_1_3, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_1_3_1 = new JPanel();
        panel_4_2_1_1_1_3.add(panel_4_2_1_1_1_3_1, BorderLayout.CENTER);
        
        textField_4_2_1_1_1_3_1 = new JTextField();
        textField_4_2_1_1_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4_2_1_1_1_3_1.setColumns(10);
        GroupLayout gl_panel_4_2_1_1_1_3_1 = new GroupLayout(panel_4_2_1_1_1_3_1);
        gl_panel_4_2_1_1_1_3_1.setHorizontalGroup(
            gl_panel_4_2_1_1_1_3_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_4_2_1_1_1_3_1.createSequentialGroup()
                    .addGap(28)
                    .addComponent(textField_4_2_1_1_1_3_1, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addGap(20))
        );
        gl_panel_4_2_1_1_1_3_1.setVerticalGroup(
            gl_panel_4_2_1_1_1_3_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_1_3_1.createSequentialGroup()
                    .addGap(80)
                    .addComponent(textField_4_2_1_1_1_3_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(86, Short.MAX_VALUE))
        );
        panel_4_2_1_1_1_3_1.setLayout(gl_panel_4_2_1_1_1_3_1);
        
        JPanel panel_4_2_1_1_2 = new JPanel();
        panel_4_2_1_1.add(panel_4_2_1_1_2);
        panel_4_2_1_1_2.setLayout(new GridLayout(0, 3, 0, 0));
        
        JPanel panel_4_2_1_1_2_1 = new JPanel();
        panel_4_2_1_1_2.add(panel_4_2_1_1_2_1);
        panel_4_2_1_1_2_1.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_ID_4_2_1_1_2_1 = new JLabel("Informazioni");
        lbl_ID_4_2_1_1_2_1.setOpaque(true);
        lbl_ID_4_2_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_ID_4_2_1_1_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_ID_4_2_1_1_2_1.setBackground(new Color(255, 228, 225));
        panel_4_2_1_1_2_1.add(lbl_ID_4_2_1_1_2_1, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_2_1_1 = new JPanel();
        panel_4_2_1_1_2_1.add(panel_4_2_1_1_2_1_1, BorderLayout.CENTER);
        panel_4_2_1_1_2_1_1.setLayout(new BorderLayout(0, 0));
        
        JComboBox comboBox_4_2_1_1_2_1_1 = new JComboBox();
        comboBox_4_2_1_1_2_1_1.addFocusListener(new FocusAdapter() {
        	@Override
        	
        	//Tendina con elenco delle informazioni relative al prodotto fornito scelto
        	public void focusGained(FocusEvent e) {
        		
        		try {
        			comboBox_4_2_1_1_2_1_1.removeAllItems();
            		
            		ProdFornito tmp = (ProdFornito)GUI.catenaAccesso.ottieniDallInventario(comboBox_4_2.getSelectedItem().toString()).get();
            		
            		for(String x : tmp.ottieniInfoTotali().keySet()) {
            			
            			comboBox_4_2_1_1_2_1_1.addItem(x);
            			
            		}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore tendina elenco informazioni prod.fornito", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        		
        	}
        });
        panel_4_2_1_1_2_1_1.add(comboBox_4_2_1_1_2_1_1, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_2_1_1_1 = new JPanel();
        panel_4_2_1_1_2_1_1.add(panel_4_2_1_1_2_1_1_1, BorderLayout.CENTER);
        panel_4_2_1_1_2_1_1_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_4_2_1_1_2_1_1_1_1 = new JPanel();
        panel_4_2_1_1_2_1_1_1.add(panel_4_2_1_1_2_1_1_1_1);
        panel_4_2_1_1_2_1_1_1_1.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId_4_2_1_1_2_1_1_1_1 = new JLabel("ID");
        lblId_4_2_1_1_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblId_4_2_1_1_2_1_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_4_2_1_1_2_1_1_1_1.add(lblId_4_2_1_1_2_1_1_1_1, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_2_1_1_1_1_1 = new JPanel();
        panel_4_2_1_1_2_1_1_1_1.add(panel_4_2_1_1_2_1_1_1_1_1, BorderLayout.CENTER);
        
        textField_4_2_1_1_2_1_1_1_1_1 = new JTextField();
        textField_4_2_1_1_2_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4_2_1_1_2_1_1_1_1_1.setColumns(10);
        GroupLayout gl_panel_4_2_1_1_2_1_1_1_1_1 = new GroupLayout(panel_4_2_1_1_2_1_1_1_1_1);
        gl_panel_4_2_1_1_2_1_1_1_1_1.setHorizontalGroup(
            gl_panel_4_2_1_1_2_1_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_2_1_1_1_1_1.createSequentialGroup()
                    .addGap(24)
                    .addComponent(textField_4_2_1_1_2_1_1_1_1_1, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addGap(24))
        );
        gl_panel_4_2_1_1_2_1_1_1_1_1.setVerticalGroup(
            gl_panel_4_2_1_1_2_1_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_4_2_1_1_2_1_1_1_1_1.createSequentialGroup()
                    .addGap(21)
                    .addComponent(textField_4_2_1_1_2_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE))
        );
        panel_4_2_1_1_2_1_1_1_1_1.setLayout(gl_panel_4_2_1_1_2_1_1_1_1_1);
        
        JPanel panel_4_2_1_1_2_1_1_1_2 = new JPanel();
        panel_4_2_1_1_2_1_1_1.add(panel_4_2_1_1_2_1_1_1_2);
        panel_4_2_1_1_2_1_1_1_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId_4_2_1_1_2_1_1_1_2 = new JLabel("Valore");
        lblId_4_2_1_1_2_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblId_4_2_1_1_2_1_1_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_4_2_1_1_2_1_1_1_2.add(lblId_4_2_1_1_2_1_1_1_2, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_2_1_1_1_2_1 = new JPanel();
        panel_4_2_1_1_2_1_1_1_2.add(panel_4_2_1_1_2_1_1_1_2_1, BorderLayout.CENTER);
        
        textField_4_2_1_1_2_1_1_1_2_1 = new JTextField();
        textField_4_2_1_1_2_1_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4_2_1_1_2_1_1_1_2_1.setColumns(10);
        GroupLayout gl_panel_4_2_1_1_2_1_1_1_2_1 = new GroupLayout(panel_4_2_1_1_2_1_1_1_2_1);
        gl_panel_4_2_1_1_2_1_1_1_2_1.setHorizontalGroup(
            gl_panel_4_2_1_1_2_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_2_1_1_1_2_1.createSequentialGroup()
                    .addGap(22)
                    .addComponent(textField_4_2_1_1_2_1_1_1_2_1, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addGap(26))
        );
        gl_panel_4_2_1_1_2_1_1_1_2_1.setVerticalGroup(
            gl_panel_4_2_1_1_2_1_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_2_1_1_1_2_1.createSequentialGroup()
                    .addGap(18)
                    .addComponent(textField_4_2_1_1_2_1_1_1_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE))
        );
        panel_4_2_1_1_2_1_1_1_2_1.setLayout(gl_panel_4_2_1_1_2_1_1_1_2_1);
        
        JPanel panel_4_2_1_1_2_2 = new JPanel();
        panel_4_2_1_1_2.add(panel_4_2_1_1_2_2);
        panel_4_2_1_1_2_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_ID_4_2_1_1_2_2 = new JLabel("P. Effettivo");
        lbl_ID_4_2_1_1_2_2.setOpaque(true);
        lbl_ID_4_2_1_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_ID_4_2_1_1_2_2.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_ID_4_2_1_1_2_2.setBackground(new Color(255, 228, 225));
        panel_4_2_1_1_2_2.add(lbl_ID_4_2_1_1_2_2, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_2_2_1 = new JPanel();
        panel_4_2_1_1_2_2.add(panel_4_2_1_1_2_2_1, BorderLayout.CENTER);
        
        textField_4_2_1_1_2_2_1 = new JTextField();
        textField_4_2_1_1_2_2_1.setEditable(false);
        textField_4_2_1_1_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4_2_1_1_2_2_1.setColumns(10);
        GroupLayout gl_panel_4_2_1_1_2_2_1 = new GroupLayout(panel_4_2_1_1_2_2_1);
        gl_panel_4_2_1_1_2_2_1.setHorizontalGroup(
            gl_panel_4_2_1_1_2_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_4_2_1_1_2_2_1.createSequentialGroup()
                    .addGap(26)
                    .addComponent(textField_4_2_1_1_2_2_1, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addGap(22))
        );
        gl_panel_4_2_1_1_2_2_1.setVerticalGroup(
            gl_panel_4_2_1_1_2_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_4_2_1_1_2_2_1.createSequentialGroup()
                    .addGap(86)
                    .addComponent(textField_4_2_1_1_2_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(80, Short.MAX_VALUE))
        );
        panel_4_2_1_1_2_2_1.setLayout(gl_panel_4_2_1_1_2_2_1);
        
        JPanel panel_4_2_1_1_2_3 = new JPanel();
        panel_4_2_1_1_2.add(panel_4_2_1_1_2_3);
        panel_4_2_1_1_2_3.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_ID_4_2_1_1_2_3 = new JLabel("Scarti");
        lbl_ID_4_2_1_1_2_3.setOpaque(true);
        lbl_ID_4_2_1_1_2_3.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_ID_4_2_1_1_2_3.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_ID_4_2_1_1_2_3.setBackground(new Color(255, 228, 225));
        panel_4_2_1_1_2_3.add(lbl_ID_4_2_1_1_2_3, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_2_3_1 = new JPanel();
        panel_4_2_1_1_2_3.add(panel_4_2_1_1_2_3_1, BorderLayout.CENTER);
        panel_4_2_1_1_2_3_1.setLayout(new BorderLayout(0, 0));
        
        JComboBox comboBox_4_2_1_1_2_3_1 = new JComboBox();
        comboBox_4_2_1_1_2_3_1.addFocusListener(new FocusAdapter() {
        	@Override
        	
        	//Tendina con elenco degli scarti relativi al prodotto fornito scelto
        	public void focusGained(FocusEvent e) {
        		
        		try {
        			comboBox_4_2_1_1_2_3_1.removeAllItems();
            		
            		ProdFornito tmp = (ProdFornito)GUI.catenaAccesso.ottieniDallInventario(comboBox_4_2.getSelectedItem().toString()).get();
            		
            		for(Scarto x : tmp.ottieniScartiTotali()) {
            			
            			comboBox_4_2_1_1_2_3_1.addItem(x);
            			
            		}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore tendina elenco scarti prod.fornito", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        		
        	}
        });
        panel_4_2_1_1_2_3_1.add(comboBox_4_2_1_1_2_3_1, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_2_3_1_1 = new JPanel();
        panel_4_2_1_1_2_3_1.add(panel_4_2_1_1_2_3_1_1, BorderLayout.CENTER);
        panel_4_2_1_1_2_3_1_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_4_2_1_1_2_3_1_1_1 = new JPanel();
        panel_4_2_1_1_2_3_1_1.add(panel_4_2_1_1_2_3_1_1_1);
        panel_4_2_1_1_2_3_1_1_1.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId_4_2_1_1_2_3_1_1_1 = new JLabel("ID");
        lblId_4_2_1_1_2_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblId_4_2_1_1_2_3_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_4_2_1_1_2_3_1_1_1.add(lblId_4_2_1_1_2_3_1_1_1, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_2_3_1_1_1_1 = new JPanel();
        panel_4_2_1_1_2_3_1_1_1.add(panel_4_2_1_1_2_3_1_1_1_1, BorderLayout.CENTER);
        
        textField_4_2_1_1_2_3_1_1_1_1 = new JTextField();
        textField_4_2_1_1_2_3_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4_2_1_1_2_3_1_1_1_1.setColumns(10);
        GroupLayout gl_panel_4_2_1_1_2_3_1_1_1_1 = new GroupLayout(panel_4_2_1_1_2_3_1_1_1_1);
        gl_panel_4_2_1_1_2_3_1_1_1_1.setHorizontalGroup(
            gl_panel_4_2_1_1_2_3_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_4_2_1_1_2_3_1_1_1_1.createSequentialGroup()
                    .addGap(25)
                    .addComponent(textField_4_2_1_1_2_3_1_1_1_1, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addGap(23))
        );
        gl_panel_4_2_1_1_2_3_1_1_1_1.setVerticalGroup(
            gl_panel_4_2_1_1_2_3_1_1_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_2_3_1_1_1_1.createSequentialGroup()
                    .addGap(5)
                    .addComponent(textField_4_2_1_1_2_3_1_1_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_4_2_1_1_2_3_1_1_1_1.setLayout(gl_panel_4_2_1_1_2_3_1_1_1_1);
        
        JPanel panel_4_2_1_1_2_3_1_1_2 = new JPanel();
        panel_4_2_1_1_2_3_1_1.add(panel_4_2_1_1_2_3_1_1_2);
        panel_4_2_1_1_2_3_1_1_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId_4_2_1_1_2_3_1_1_2 = new JLabel("Valore");
        lblId_4_2_1_1_2_3_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblId_4_2_1_1_2_3_1_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_4_2_1_1_2_3_1_1_2.add(lblId_4_2_1_1_2_3_1_1_2, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_2_3_1_1_2_1 = new JPanel();
        panel_4_2_1_1_2_3_1_1_2.add(panel_4_2_1_1_2_3_1_1_2_1, BorderLayout.CENTER);
        
        textField_4_2_1_1_2_3_1_1_2_1 = new JTextField();
        textField_4_2_1_1_2_3_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4_2_1_1_2_3_1_1_2_1.setColumns(10);
        GroupLayout gl_panel_4_2_1_1_2_3_1_1_2_1 = new GroupLayout(panel_4_2_1_1_2_3_1_1_2_1);
        gl_panel_4_2_1_1_2_3_1_1_2_1.setHorizontalGroup(
            gl_panel_4_2_1_1_2_3_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_4_2_1_1_2_3_1_1_2_1.createSequentialGroup()
                    .addGap(25)
                    .addComponent(textField_4_2_1_1_2_3_1_1_2_1, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addGap(23))
        );
        gl_panel_4_2_1_1_2_3_1_1_2_1.setVerticalGroup(
            gl_panel_4_2_1_1_2_3_1_1_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_2_3_1_1_2_1.createSequentialGroup()
                    .addGap(5)
                    .addComponent(textField_4_2_1_1_2_3_1_1_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_4_2_1_1_2_3_1_1_2_1.setLayout(gl_panel_4_2_1_1_2_3_1_1_2_1);
        
        JPanel panel_4_2_1_1_2_3_1_1_3 = new JPanel();
        panel_4_2_1_1_2_3_1_1.add(panel_4_2_1_1_2_3_1_1_3);
        panel_4_2_1_1_2_3_1_1_3.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId_4_2_1_1_2_3_1_1_3 = new JLabel("Percentuale");
        lblId_4_2_1_1_2_3_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblId_4_2_1_1_2_3_1_1_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_4_2_1_1_2_3_1_1_3.add(lblId_4_2_1_1_2_3_1_1_3, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_2_3_1_1_3_1 = new JPanel();
        panel_4_2_1_1_2_3_1_1_3.add(panel_4_2_1_1_2_3_1_1_3_1, BorderLayout.CENTER);
        
        
        chckbx_4_2_1_1_2_3_1_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        GroupLayout gl_panel_4_2_1_1_2_3_1_1_3_1 = new GroupLayout(panel_4_2_1_1_2_3_1_1_3_1);
        gl_panel_4_2_1_1_2_3_1_1_3_1.setHorizontalGroup(
            gl_panel_4_2_1_1_2_3_1_1_3_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_2_3_1_1_3_1.createSequentialGroup()
                    .addGap(81)
                    .addComponent(chckbx_4_2_1_1_2_3_1_1_3_1, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addGap(81))
        );
        gl_panel_4_2_1_1_2_3_1_1_3_1.setVerticalGroup(
            gl_panel_4_2_1_1_2_3_1_1_3_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_2_3_1_1_3_1.createSequentialGroup()
                    .addGap(8)
                    .addComponent(chckbx_4_2_1_1_2_3_1_1_3_1)
                    .addContainerGap(7, Short.MAX_VALUE))
        );
        panel_4_2_1_1_2_3_1_1_3_1.setLayout(gl_panel_4_2_1_1_2_3_1_1_3_1);
        
        JPanel panel_4_2_1_1_3 = new JPanel();
        panel_4_2_1_1.add(panel_4_2_1_1_3);
        panel_4_2_1_1_3.setLayout(new GridLayout(0, 4, 0, 0));
        
        JPanel panel_4_2_1_1_3_1 = new JPanel();
        panel_4_2_1_1_3.add(panel_4_2_1_1_3_1);
        panel_4_2_1_1_3_1.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_4_2_1_1_3_1 = new JLabel("Val. assoluto");
        lbl_4_2_1_1_3_1.setOpaque(true);
        lbl_4_2_1_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_4_2_1_1_3_1.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_4_2_1_1_3_1.setBackground(new Color(255, 228, 225));
        panel_4_2_1_1_3_1.add(lbl_4_2_1_1_3_1, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_3_1_1 = new JPanel();
        panel_4_2_1_1_3_1.add(panel_4_2_1_1_3_1_1, BorderLayout.CENTER);
        
        textField_4_2_1_1_3_1_1 = new JTextField();
        textField_4_2_1_1_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4_2_1_1_3_1_1.setColumns(10);
        GroupLayout gl_panel_4_2_1_1_3_1_1 = new GroupLayout(panel_4_2_1_1_3_1_1);
        gl_panel_4_2_1_1_3_1_1.setHorizontalGroup(
            gl_panel_4_2_1_1_3_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_3_1_1.createSequentialGroup()
                    .addGap(14)
                    .addComponent(textField_4_2_1_1_3_1_1)
                    .addGap(16))
        );
        gl_panel_4_2_1_1_3_1_1.setVerticalGroup(
            gl_panel_4_2_1_1_3_1_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_3_1_1.createSequentialGroup()
                    .addGap(80)
                    .addComponent(textField_4_2_1_1_3_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(86, Short.MAX_VALUE))
        );
        panel_4_2_1_1_3_1_1.setLayout(gl_panel_4_2_1_1_3_1_1);
        
        JPanel panel_4_2_1_1_3_2 = new JPanel();
        panel_4_2_1_1_3.add(panel_4_2_1_1_3_2);
        panel_4_2_1_1_3_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_4_2_1_1_3_2 = new JLabel("Val. netto");
        lbl_4_2_1_1_3_2.setOpaque(true);
        lbl_4_2_1_1_3_2.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_4_2_1_1_3_2.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_4_2_1_1_3_2.setBackground(new Color(255, 228, 225));
        panel_4_2_1_1_3_2.add(lbl_4_2_1_1_3_2, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_3_2_1 = new JPanel();
        panel_4_2_1_1_3_2.add(panel_4_2_1_1_3_2_1, BorderLayout.CENTER);
        
        textField_4_2_1_1_3_2_1 = new JTextField();
        textField_4_2_1_1_3_2_1.setEditable(false);
        textField_4_2_1_1_3_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4_2_1_1_3_2_1.setColumns(10);
        GroupLayout gl_panel_4_2_1_1_3_2_1 = new GroupLayout(panel_4_2_1_1_3_2_1);
        gl_panel_4_2_1_1_3_2_1.setHorizontalGroup(
            gl_panel_4_2_1_1_3_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_3_2_1.createSequentialGroup()
                    .addGap(15)
                    .addComponent(textField_4_2_1_1_3_2_1)
                    .addGap(15))
        );
        gl_panel_4_2_1_1_3_2_1.setVerticalGroup(
            gl_panel_4_2_1_1_3_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_3_2_1.createSequentialGroup()
                    .addGap(79)
                    .addComponent(textField_4_2_1_1_3_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(87, Short.MAX_VALUE))
        );
        panel_4_2_1_1_3_2_1.setLayout(gl_panel_4_2_1_1_3_2_1);
        
        JPanel panel_4_2_1_1_3_3 = new JPanel();
        panel_4_2_1_1_3.add(panel_4_2_1_1_3_3);
        panel_4_2_1_1_3_3.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_4_2_1_1_3_3 = new JLabel("Perc. netto");
        lbl_4_2_1_1_3_3.setOpaque(true);
        lbl_4_2_1_1_3_3.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_4_2_1_1_3_3.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_4_2_1_1_3_3.setBackground(new Color(255, 228, 225));
        panel_4_2_1_1_3_3.add(lbl_4_2_1_1_3_3, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_3_3_1 = new JPanel();
        panel_4_2_1_1_3_3.add(panel_4_2_1_1_3_3_1, BorderLayout.CENTER);
        
        textField_4_2_1_1_3_3_1 = new JTextField();
        textField_4_2_1_1_3_3_1.setEditable(false);
        textField_4_2_1_1_3_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4_2_1_1_3_3_1.setColumns(10);
        GroupLayout gl_panel_4_2_1_1_3_3_1 = new GroupLayout(panel_4_2_1_1_3_3_1);
        gl_panel_4_2_1_1_3_3_1.setHorizontalGroup(
            gl_panel_4_2_1_1_3_3_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_3_3_1.createSequentialGroup()
                    .addGap(14)
                    .addComponent(textField_4_2_1_1_3_3_1)
                    .addGap(16))
        );
        gl_panel_4_2_1_1_3_3_1.setVerticalGroup(
            gl_panel_4_2_1_1_3_3_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_3_3_1.createSequentialGroup()
                    .addGap(80)
                    .addComponent(textField_4_2_1_1_3_3_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(86, Short.MAX_VALUE))
        );
        panel_4_2_1_1_3_3_1.setLayout(gl_panel_4_2_1_1_3_3_1);
        
        JPanel panel_4_2_1_1_3_4 = new JPanel();
        panel_4_2_1_1_3.add(panel_4_2_1_1_3_4);
        panel_4_2_1_1_3_4.setLayout(new BorderLayout(0, 0));
        
        JLabel lbl_4_2_1_1_3_4 = new JLabel("Padre");
        lbl_4_2_1_1_3_4.setOpaque(true);
        lbl_4_2_1_1_3_4.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_4_2_1_1_3_4.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        lbl_4_2_1_1_3_4.setBackground(new Color(255, 228, 225));
        panel_4_2_1_1_3_4.add(lbl_4_2_1_1_3_4, BorderLayout.NORTH);
        
        JPanel panel_4_2_1_1_3_4_1 = new JPanel();
        panel_4_2_1_1_3_4.add(panel_4_2_1_1_3_4_1, BorderLayout.CENTER);
        
        textField_4_2_1_1_3_4_1 = new JTextField();
        textField_4_2_1_1_3_4_1.setEditable(false);
        textField_4_2_1_1_3_4_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4_2_1_1_3_4_1.setColumns(10);
        GroupLayout gl_panel_4_2_1_1_3_4_1 = new GroupLayout(panel_4_2_1_1_3_4_1);
        gl_panel_4_2_1_1_3_4_1.setHorizontalGroup(
            gl_panel_4_2_1_1_3_4_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_3_4_1.createSequentialGroup()
                    .addGap(15)
                    .addComponent(textField_4_2_1_1_3_4_1)
                    .addGap(15))
        );
        gl_panel_4_2_1_1_3_4_1.setVerticalGroup(
            gl_panel_4_2_1_1_3_4_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4_2_1_1_3_4_1.createSequentialGroup()
                    .addGap(80)
                    .addComponent(textField_4_2_1_1_3_4_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(86, Short.MAX_VALUE))
        );
        panel_4_2_1_1_3_4_1.setLayout(gl_panel_4_2_1_1_3_4_1);
        
        
        //-----------------------------------------------
        
        
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                        .addComponent(p1_panel_TIPOLOGIA, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                        .addComponent(p2_panel_PRODOTTO, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 588, Short.MAX_VALUE)
                        .addComponent(p3_panel_PCONCRETO, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 588, Short.MAX_VALUE)
                        .addComponent(p4_panel_PFORNITO, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 588, Short.MAX_VALUE))
                    .addGap(7))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(p1_panel_TIPOLOGIA, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p2_panel_PRODOTTO, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(p3_panel_PCONCRETO, GroupLayout.PREFERRED_SIZE, 832, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(p4_panel_PFORNITO, GroupLayout.PREFERRED_SIZE, 732, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        panel.setLayout(gl_panel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setPreferredSize(new Dimension(10, 50));
        contentPane.add(panel_1, BorderLayout.NORTH);
        
        JLabel lblGuiInventario = new JLabel("GUI Inventario");
        lblGuiInventario.setHorizontalAlignment(SwingConstants.CENTER);
        lblGuiInventario.setFont(new Font("Lucida Grande", Font.BOLD, 30));
        
        JButton btnSalva_1_1_1 = new JButton("Mostra tutto");
        btnSalva_1_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		/*
        		try {
        			Tipologia t = (Tipologia)GUI.catenaAccesso.ottieniDallInventario(comboBox_1_2.getSelectedItem().toString()).get();
        			
        			//Informazioni
            		textField_1_2_1_2_1_1_1_1.setText(comboBox_1_2_1_2_1.getSelectedItem().toString());            		
            		textField_1_2_1_2_1_1_2_1.setText(t.getInfo().get(textField_1_2_1_2_1_1_1_1.getText()));
				} catch (Exception e2) {
					e2.printStackTrace();
				}
        		
        		
        		try {
					
        			Prodotto p = (Prodotto)GUI.catenaAccesso.ottieniDallInventario(comboBox_2_2.getSelectedItem().toString()).get();
        			
        			//Informazioni
        			textField_2_2_1_2_1_1_1.setText(comboBox_2_2_1_2_1.getSelectedItem().toString());
        			textField_2_2_1_2_1_1_2_1.setText(p.getInfo().get(textField_2_2_1_2_1_1_1.getText()));
        			
        			//Scarti
        			textField_2_2_1_3_1_1_1.setText(comboBox_2_2_1_3_1.getSelectedItem().toString());	
        		
        			//textField_2_2_1_3_1_1_2_1.setText(p.ottieniScarti(a).);
        			
				} catch (Exception e2) {
					e2.printStackTrace();
				}
        		
        		//Prodotto concreto
        		//Prodotto fornito
        		 
        		 */
        		
        	}
        });
        btnSalva_1_1_1.setPreferredSize(new Dimension(117, 50));
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
        	gl_panel_1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_1.createSequentialGroup()
        			.addGap(139)
        			.addComponent(lblGuiInventario, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
        			.addGap(3)
        			.addComponent(btnSalva_1_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGap(56))
        );
        gl_panel_1.setVerticalGroup(
        	gl_panel_1.createParallelGroup(Alignment.LEADING)
        		.addComponent(btnSalva_1_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        		.addGroup(gl_panel_1.createSequentialGroup()
        			.addGap(6)
        			.addComponent(lblGuiInventario, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        			.addGap(6))
        );
        panel_1.setLayout(gl_panel_1);
        
    }
}
