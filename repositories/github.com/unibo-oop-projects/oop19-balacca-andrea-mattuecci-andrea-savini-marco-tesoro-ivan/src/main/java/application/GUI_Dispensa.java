package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GUI_Dispensa extends GUI {

    private JPanel contentPane;
    private JPanel panel_1;
    private JPanel panel_1_1;
    private JButton btnSalva;
    private JPanel panel;
    private JPanel panel_2;
    private JPanel panel_1_2;
    private JButton btnAggiungi;
    private JPanel panel_3;
    private JPanel panel_4;
    private JPanel panel_5;
    private JPanel panel_6;
    private JPanel panel_7;
    private JPanel panel_8;
    private JPanel panel_9;
    private JPanel panel_11;
    private JPanel panel_12;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_1_2;
    private JScrollPane scrollPane;
    private JComboBox comboBox_1;
    private JLabel lblNewLabel_2;
    private JLabel lblContenuto_1;
    private JPanel panel_1_3;
    private JButton btnCarico;
    private JButton btnSalva_1;
    private JPanel panel_22;
    private JPanel panel_23;
    private JScrollPane scrollPane_1;
    private JPanel panel_26;
    private JPanel panel_27;
    private JPanel panel_28;
    private JPanel panel_29;
    private JTextField textField_1;
    private JTextField textField_2;
    
    HashMap<String,Float> mappaProdotti = new HashMap<>();
    private JTable table;
    private JTable table_1;
    private JTextField textField_3;
    private JButton btnSalva_1_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_Dispensa frame = new GUI_Dispensa();
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
    public GUI_Dispensa() {
    	
	 
         
    	
    	table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID Prodotto Fornito", "Quantità"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Float.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
    	
        table = new JTable();
        table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID Prodotto Fornito", "Quantità"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Float.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
        
        DefaultTableModel model1 = (DefaultTableModel)table_1.getModel();
        DefaultTableModel model = (DefaultTableModel)table.getModel();
 
    	
        setMinimumSize(new Dimension(780, 960));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 773, 971);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(176, 224, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JLabel lblDispensa = new JLabel("Dispensa");
        lblDispensa.setHorizontalAlignment(SwingConstants.CENTER);
        lblDispensa.setFont(new Font("Lucida Grande", Font.BOLD, 45));
        contentPane.add(lblDispensa, BorderLayout.NORTH);
        
        panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        panel_1_1 = new JPanel();
        panel_1_1.setBackground(new Color(255, 222, 173));
        panel_1.add(panel_1_1, BorderLayout.SOUTH);
        
        btnSalva = new JButton("Indietro");
        btnSalva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                     
                save(getX(), getY(), getWidth(), getHeight());
                
                frame = new GUI_GeneraleHotel();
                frame.setBounds(getX(), getY(), getWidth(), getHeight());
               
                frame.setVisible(true);
                setVisible(false);
                
            }
        });
        btnSalva.setPreferredSize(new Dimension(117, 50));
        panel_1_1.add(btnSalva);
        
        panel = new JPanel();
        panel_1.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));
        
        panel_2 = new JPanel();
        panel_2.setPreferredSize(new Dimension(10, 300));
        panel.add(panel_2, BorderLayout.NORTH);
        panel_2.setLayout(new BorderLayout(0, 0));
        
        panel_1_2 = new JPanel();
        panel_1_2.setBackground(new Color(255, 222, 173));
        panel_2.add(panel_1_2, BorderLayout.SOUTH);
        
        textField_2 = new JTextField();
        panel_1_2.add(textField_2);
        textField_2.setColumns(10);
        
        btnAggiungi = new JButton("Salva");
        
        JComboBox comboBox = new JComboBox();
        comboBox.addFocusListener(new FocusAdapter() {
        	
        	public void focusGained(FocusEvent e) {
        		
        		comboBox.removeAllItems();
        		
        		for(Dispensa x : GUI.hotelAccesso.getDispense()) {
        			comboBox.addItem(x.getNome());
        		}
        		
        	}
        });
        
        btnAggiungi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(!textField_2.getText().isBlank()) {
        			
        			String nomeDispensa = textField_2.getText();
            		
            		//w.addNewDispensa(GUI.catenaAccesso, GUI.hotelAccesso.getNome(), nomeDispensa);
        			
            		GUI.hotelAccesso.aggiungiUnaDispensa(new Dispensa(nomeDispensa, GUI.catenaAccesso));
        			
            		System.out.println(GUI.catenaAccesso.getAlberghi());
            		System.out.println(GUI.hotelAccesso.getDispense());
            		
            		JOptionPane.showMessageDialog(null, "Aggiunta!", "OK", JOptionPane.ERROR_MESSAGE); 
        		}else {
        			JOptionPane.showMessageDialog(null, "Controllare i valori inseriti!", "OK", JOptionPane.ERROR_MESSAGE); 
        		}
        		
        		
        	}
        });
        btnAggiungi.setPreferredSize(new Dimension(117, 50));
        panel_1_2.add(btnAggiungi);
        
        JButton btnSalva_1_1 = new JButton("Rimuovi");
        btnSalva_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		
        		if(!textField_2.getText().isBlank()) {
        			
        			String nomeDispensa = textField_2.getText();
            		
        			wd.deleteDispensa(GUI.catenaAccesso, comboBox.getSelectedItem().toString(), GUI.hotelAccesso.getNome());
        			
            		JOptionPane.showMessageDialog(null, "Rimossa!", "OK", JOptionPane.ERROR_MESSAGE); 
        		}else {
        			JOptionPane.showMessageDialog(null, "Controllare i valori inseriti!", "OK", JOptionPane.ERROR_MESSAGE); 
        		}
        	}
        });
        btnSalva_1_1.setPreferredSize(new Dimension(117, 50));
        panel_1_2.add(btnSalva_1_1);
        
        btnSalva_1_2 = new JButton("Seleziona dispensa");
        btnSalva_1_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		GUI.dispensaAccesso = GUI.hotelAccesso.ottieniUnaDispensa(comboBox.getSelectedItem().toString()).get();
        		
        		JOptionPane.showMessageDialog(null, "Dispensa selezionata", "OK", JOptionPane.ERROR_MESSAGE); 
        	}
        });
        btnSalva_1_2.setPreferredSize(new Dimension(117, 50));
        panel_1_2.add(btnSalva_1_2);
        
        panel_3 = new JPanel();
        panel_2.add(panel_3, BorderLayout.CENTER);
        panel_3.setLayout(new GridLayout(0, 2, 0, 0));
        
        panel_4 = new JPanel();
        panel_3.add(panel_4);
        
        JLabel lblNewLabel = new JLabel("Nome dispensa:");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GroupLayout gl_panel_4 = new GroupLayout(panel_4);
        gl_panel_4.setHorizontalGroup(
            gl_panel_4.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
                    .addGap(185)
                    .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(27))
        );
        gl_panel_4.setVerticalGroup(
            gl_panel_4.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4.createSequentialGroup()
                    .addGap(28)
                    .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(27))
        );
        panel_4.setLayout(gl_panel_4);
        
        panel_7 = new JPanel();
        panel_3.add(panel_7);
        
        
        GroupLayout gl_panel_7 = new GroupLayout(panel_7);
        gl_panel_7.setHorizontalGroup(
            gl_panel_7.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_7.createSequentialGroup()
                    .addGap(49)
                    .addComponent(comboBox, 0, 267, Short.MAX_VALUE)
                    .addGap(51))
        );
        gl_panel_7.setVerticalGroup(
            gl_panel_7.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_7.createSequentialGroup()
                    .addGap(30)
                    .addComponent(comboBox)
                    .addGap(23))
        );
        panel_7.setLayout(gl_panel_7);
        
        panel_5 = new JPanel();
        panel_3.add(panel_5);
        
        JLabel lblTipologiePresenti = new JLabel("Tipologie presenti:");
        lblTipologiePresenti.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipologiePresenti.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        GroupLayout gl_panel_5 = new GroupLayout(panel_5);
        gl_panel_5.setHorizontalGroup(
            gl_panel_5.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
                    .addGap(158)
                    .addComponent(lblTipologiePresenti, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(28))
        );
        gl_panel_5.setVerticalGroup(
            gl_panel_5.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_5.createSequentialGroup()
                    .addGap(29)
                    .addComponent(lblTipologiePresenti, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addGap(26))
        );
        panel_5.setLayout(gl_panel_5);
        
        panel_8 = new JPanel();
        panel_3.add(panel_8);
        
        comboBox_1 = new JComboBox();
        comboBox_1.addFocusListener(new FocusAdapter() {
        	
        	public void focusGained(FocusEvent e) {
        		
        		comboBox_1.removeAllItems();

        		for(Dispensa x : GUI.hotelAccesso.getDispense()) {
        			if(x.getNome().equals(comboBox.getSelectedItem().toString())) {
        				for(var t : x.getTipologia()) {
        					comboBox_1.addItem(t);
        				}
        			}
        		}
        		
        	}
        });
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        
        JButton btnSalva_2 = new JButton("Salva");
        btnSalva_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(!textField_3.getText().isBlank()) {
	        		for(var x : GUI.hotelAccesso.getDispense()) {
	        			if(x.getNome().equals(comboBox.getSelectedItem().toString())) {
	        				x.aggiungiUnTipo(textField_3.getText());
	        			}
	        		}
	        		JOptionPane.showMessageDialog(null, "Aggiunta", "OK", JOptionPane.ERROR_MESSAGE); 
        		}else {
        			JOptionPane.showMessageDialog(null, "Controllare i valori inseriti", "OK", JOptionPane.ERROR_MESSAGE); 
        		}
        	}
        });
        
        JButton btnSalva_2_1 = new JButton("Rimuovi");
        btnSalva_2_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(!textField_3.getText().isBlank()) {
	        		for(var x : GUI.hotelAccesso.getDispense()) {
	        			if(x.getNome().equals(comboBox.getSelectedItem().toString())) {
	        				x.rimuoviUnTipo(textField_3.getText());
	        			}
	        		}
	        		JOptionPane.showMessageDialog(null, "Rimossa", "OK", JOptionPane.ERROR_MESSAGE); 
        		}else {
        			JOptionPane.showMessageDialog(null, "Controllare i valori inseriti", "OK", JOptionPane.ERROR_MESSAGE); 
        		}
        		
        	}
        });
        GroupLayout gl_panel_8 = new GroupLayout(panel_8);
        gl_panel_8.setHorizontalGroup(
        	gl_panel_8.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_8.createSequentialGroup()
        			.addGap(51)
        			.addComponent(comboBox_1, 0, 285, Short.MAX_VALUE)
        			.addGap(49))
        		.addGroup(gl_panel_8.createSequentialGroup()
        			.addGap(48)
        			.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnSalva_2, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnSalva_2_1, GroupLayout.PREFERRED_SIZE, 76, Short.MAX_VALUE)
        			.addGap(37))
        );
        gl_panel_8.setVerticalGroup(
        	gl_panel_8.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_8.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnSalva_2_1)
        				.addComponent(btnSalva_2)
        				.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(12, Short.MAX_VALUE))
        );
        panel_8.setLayout(gl_panel_8);
        
        panel_6 = new JPanel();
        panel_3.add(panel_6);
        
        JLabel lblNewLabel_1_1 = new JLabel("Tipi accettati:");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        GroupLayout gl_panel_6 = new GroupLayout(panel_6);
        gl_panel_6.setHorizontalGroup(
            gl_panel_6.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_6.createSequentialGroup()
                    .addGap(196)
                    .addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addGap(16))
        );
        gl_panel_6.setVerticalGroup(
            gl_panel_6.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_6.createSequentialGroup()
                    .addGap(27)
                    .addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addGap(28))
        );
        panel_6.setLayout(gl_panel_6);
        
        panel_9 = new JPanel();
        panel_3.add(panel_9);
        
        lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        GroupLayout gl_panel_9 = new GroupLayout(panel_9);
        gl_panel_9.setHorizontalGroup(
            gl_panel_9.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_9.createSequentialGroup()
                    .addGap(55)
                    .addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addGap(54))
        );
        gl_panel_9.setVerticalGroup(
            gl_panel_9.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_9.createSequentialGroup()
                    .addGap(31)
                    .addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(33))
        );
        panel_9.setLayout(gl_panel_9);
        
        JPanel panel_10 = new JPanel();
        panel.add(panel_10, BorderLayout.CENTER);
        panel_10.setLayout(new GridLayout(0, 2, 0, 0));
        
        panel_11 = new JPanel();
        panel_10.add(panel_11);
        panel_11.setLayout(new BorderLayout(0, 0));
        
        JLabel lblContenuto = new JLabel("Contenuto");
        lblContenuto.setHorizontalAlignment(SwingConstants.CENTER);
        lblContenuto.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        panel_11.add(lblContenuto, BorderLayout.NORTH);
        
        JPanel panel_13 = new JPanel();
        panel_11.add(panel_13, BorderLayout.CENTER);
        panel_13.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_14 = new JPanel();
        panel_14.setPreferredSize(new Dimension(10, 40));
        panel_13.add(panel_14, BorderLayout.NORTH);
        panel_14.setLayout(new GridLayout(0, 2, 0, 0));
        
        JPanel panel_15 = new JPanel();
        panel_14.add(panel_15);
        
        lblNewLabel_1 = new JLabel("ID P.Fornito");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        GroupLayout gl_panel_15 = new GroupLayout(panel_15);
        gl_panel_15.setHorizontalGroup(
            gl_panel_15.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_15.createSequentialGroup()
                    .addGap(13)
                    .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE))
        );
        gl_panel_15.setVerticalGroup(
            gl_panel_15.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_15.createSequentialGroup()
                    .addGap(8)
                    .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(7, Short.MAX_VALUE))
        );
        panel_15.setLayout(gl_panel_15);
        
        JPanel panel_16 = new JPanel();
        panel_14.add(panel_16);
        
        lblNewLabel_1_2 = new JLabel("Quantità:");
        lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        GroupLayout gl_panel_16 = new GroupLayout(panel_16);
        gl_panel_16.setHorizontalGroup(
            gl_panel_16.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_16.createSequentialGroup()
                    .addContainerGap(15, Short.MAX_VALUE)
                    .addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                    .addGap(13))
        );
        gl_panel_16.setVerticalGroup(
            gl_panel_16.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_16.createSequentialGroup()
                    .addGap(8)
                    .addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(7, Short.MAX_VALUE))
        );
        panel_16.setLayout(gl_panel_16);
        
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel_13.add(scrollPane, BorderLayout.CENTER);
        
       
        
        
        scrollPane.setViewportView(table);
        
        panel_12 = new JPanel();
        panel_10.add(panel_12);
        panel_12.setLayout(new BorderLayout(0, 0));
        
        lblContenuto_1 = new JLabel("Aggiungi/Rimuovi");
        lblContenuto_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblContenuto_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        panel_12.add(lblContenuto_1, BorderLayout.NORTH);
        
        panel_1_3 = new JPanel();
        panel_1_3.setBackground(new Color(224, 255, 255));
        panel_12.add(panel_1_3, BorderLayout.SOUTH);
        
        btnCarico = new JButton("Carico");
        btnCarico.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			for(var x : mappaProdotti.keySet()) {
            			
            			System.out.println(x);
            			System.out.println(mappaProdotti.get(x));
            			String stringa = String.valueOf(mappaProdotti.get(x));
            			
            			System.out.println(GUI.catenaAccesso);
            			System.out.println(GUI.hotelAccesso.getNome());
            			System.out.println(GUI.dispensaAccesso.getNome());
            			System.out.println(x);
            			System.out.println(stringa);
            			
            			wm.load(GUI.catenaAccesso, GUI.hotelAccesso.getNome(), GUI.dispensaAccesso.getNome(), x, stringa);
            			System.out.println("Oggetto aggiunto alla dispensa");
            		}
            		/*
            		for(var x : GUI.catenaAccesso.getInventario()) {
            			if(x instanceof ProdFornito) {
            				model.addRow(new Object[] {((ProdFornito) x).getID(), ((ProdFornito) x).getPrezzo()});
            			}
            		}
            		*/
            		
            		JOptionPane.showMessageDialog(null, "Carico inserito!", "OK", JOptionPane.ERROR_MESSAGE); 
				} catch (Exception e2) {
					e2.printStackTrace();
					//JOptionPane.showMessageDialog(null, "Selezionare una dispensa!", "OK", JOptionPane.ERROR_MESSAGE); 
				}
        		
        	}
        });
        btnCarico.setPreferredSize(new Dimension(117, 50));
        panel_1_3.add(btnCarico);
        
        btnSalva_1 = new JButton("Scarico");
        btnSalva_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		for(var x : mappaProdotti.keySet()) {
        			
        			String stringa = String.valueOf(mappaProdotti.get(x));
        			wm.dump(GUI.catenaAccesso, GUI.hotelAccesso.getNome(), GUI.dispensaAccesso.getNome(), x, stringa);
        			
		
        		}
        		
        		JOptionPane.showMessageDialog(null, "Scarico eseguito!", "OK", JOptionPane.ERROR_MESSAGE); 
        	}
        });
        btnSalva_1.setPreferredSize(new Dimension(117, 50));
        panel_1_3.add(btnSalva_1);
        
        panel_22 = new JPanel();
        panel_12.add(panel_22, BorderLayout.CENTER);
        panel_22.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_24 = new JPanel();
        panel_22.add(panel_24);
        panel_24.setLayout(new GridLayout(0, 1, 0, 0));
        
        panel_23 = new JPanel();
        panel_24.add(panel_23);
        panel_23.setLayout(new GridLayout(0, 2, 0, 0));
        
        panel_26 = new JPanel();
        panel_23.add(panel_26);
        panel_26.setLayout(new BorderLayout(0, 0));
        
        JLabel lblId = new JLabel("ID");
        lblId.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        panel_26.add(lblId, BorderLayout.NORTH);
        
        JPanel panel_30 = new JPanel();
        panel_26.add(panel_30, BorderLayout.CENTER);
        
        JComboBox comboBox_2 = new JComboBox();
        comboBox_2.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		
        		for(var x : GUI.catenaAccesso.getInventario()) {
        			if(x instanceof ProdFornito) {
        				comboBox_2.addItem(((ProdFornito) x).getID());
        			}
        		}
        		
        	}
        });
        GroupLayout gl_panel_30 = new GroupLayout(panel_30);
        gl_panel_30.setHorizontalGroup(
        	gl_panel_30.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_panel_30.createSequentialGroup()
        			.addGap(26)
        			.addComponent(comboBox_2, 0, 139, Short.MAX_VALUE)
        			.addGap(27))
        );
        gl_panel_30.setVerticalGroup(
        	gl_panel_30.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_panel_30.createSequentialGroup()
        			.addGap(27)
        			.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(28, Short.MAX_VALUE))
        );
        panel_30.setLayout(gl_panel_30);
        
        panel_28 = new JPanel();
        panel_23.add(panel_28);
        panel_28.setLayout(new BorderLayout(0, 0));
        
        JLabel lblQuantit = new JLabel("Quantità");
        lblQuantit.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuantit.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        panel_28.add(lblQuantit, BorderLayout.NORTH);
        
        JPanel panel_31 = new JPanel();
        panel_28.add(panel_31, BorderLayout.CENTER);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        GroupLayout gl_panel_31 = new GroupLayout(panel_31);
        gl_panel_31.setHorizontalGroup(
            gl_panel_31.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_31.createSequentialGroup()
                    .addGap(26)
                    .addComponent(textField_1)
                    .addGap(27))
        );
        gl_panel_31.setVerticalGroup(
            gl_panel_31.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_31.createSequentialGroup()
                    .addGap(26)
                    .addComponent(textField_1)
                    .addGap(23))
        );
        panel_31.setLayout(gl_panel_31);
        
        panel_27 = new JPanel();
        panel_23.add(panel_27);

        
        JButton btnAggiungi_1 = new JButton("Aggiungi");
        btnAggiungi_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String prodFornito = comboBox_2.getSelectedItem().toString();
        		String value = textField_1.getText();
        		float quantita;
        		
        		
        		if(prodFornito != null && value != null) {
        			try {
        				quantita = Float.parseFloat(value);           			
            			mappaProdotti.put(prodFornito, quantita);
            			System.out.println("Aggiunto: "+mappaProdotti);

    					model1.addRow(new Object[] {prodFornito, quantita});
			
            			//JOptionPane.showMessageDialog(null, "Aggiunto!", "OK", JOptionPane.ERROR_MESSAGE); 
    					System.out.println("Aggiunto");
            		}catch (Exception e2) {
						// TODO: handle exception
            			JOptionPane.showMessageDialog(null, "Immettere i campi correttamente", "ERRORE", JOptionPane.ERROR_MESSAGE);   		 
					}
        		}else {
        			JOptionPane.showMessageDialog(null, "Immettere i campi correttamente", "ERRORE", JOptionPane.ERROR_MESSAGE); 
        		}
        	}
        });
        btnAggiungi_1.setPreferredSize(new Dimension(117, 50));
        GroupLayout gl_panel_27 = new GroupLayout(panel_27);
        gl_panel_27.setHorizontalGroup(
            gl_panel_27.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_27.createSequentialGroup()
                    .addGap(35)
                    .addComponent(btnAggiungi_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(31))
        );
        gl_panel_27.setVerticalGroup(
            gl_panel_27.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_27.createSequentialGroup()
                    .addGap(27)
                    .addComponent(btnAggiungi_1, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addGap(23))
        );
        panel_27.setLayout(gl_panel_27);
        
        panel_29 = new JPanel();
        panel_23.add(panel_29);
        
        JButton btnRimuovi = new JButton("Rimuovi");
        btnRimuovi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String prodFornito = comboBox_2.getSelectedItem().toString();
        		
        		if(!prodFornito.isBlank()) {
        			try {
        				mappaProdotti.remove(prodFornito);
            			
            			
            			System.out.println("Rimosso: "+mappaProdotti);
            			       			
            			model1.removeRow(table_1.getSelectedRow());
            			
                		JOptionPane.showMessageDialog(null, "Rimosso!", "OK", JOptionPane.ERROR_MESSAGE);  
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Selezionare la riga da rimuovere!", "ERRORE", JOptionPane.ERROR_MESSAGE);  
					}
        			
        		}else {

        			JOptionPane.showMessageDialog(null, "Immettere i campi correttamente", "ERRORE", JOptionPane.ERROR_MESSAGE);   	
        		}
        		
        	}
        });
        btnRimuovi.setPreferredSize(new Dimension(117, 50));
        GroupLayout gl_panel_29 = new GroupLayout(panel_29);
        gl_panel_29.setHorizontalGroup(
            gl_panel_29.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_29.createSequentialGroup()
                    .addGap(34)
                    .addComponent(btnRimuovi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(32))
        );
        gl_panel_29.setVerticalGroup(
            gl_panel_29.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_29.createSequentialGroup()
                    .addGap(28)
                    .addComponent(btnRimuovi, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addGap(22))
        );
        panel_29.setLayout(gl_panel_29);
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel_24.add(scrollPane_1);
        
       
        
        
        
        scrollPane_1.setViewportView(table_1);
    }
}
