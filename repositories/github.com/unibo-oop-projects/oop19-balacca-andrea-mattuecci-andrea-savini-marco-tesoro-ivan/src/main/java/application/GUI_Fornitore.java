	package application;

	import java.awt.BorderLayout;
	import java.awt.EventQueue;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;
	import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
	import javax.swing.SwingConstants;
	import java.awt.Color;
	import javax.swing.JButton;
	import java.awt.Dimension;
	import javax.swing.JComboBox;
	import java.awt.GridBagLayout;
	import java.awt.GridLayout;
	import javax.swing.JTable;
	import javax.swing.JScrollPane;
	import javax.swing.table.DefaultTableModel;
	import javax.swing.GroupLayout;
	import javax.swing.GroupLayout.Alignment;
	import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

	public class GUI_Fornitore extends GUI {

		private JPanel contentPane;
		private JTable table;
		private JTable table_1;
		
		WarehouseAddUtility w = new WarehouseAddUtilityImpl();
		
		//static FileUtility file = new FileUtilityImpl();
		private ObjectInputStream in = null;
		private JTextField textField;
		

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GUI_Fornitore frame = new GUI_Fornitore();
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
		public GUI_Fornitore() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 698, 503);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			JLabel lblFornitori = new JLabel("Fornitori");
			lblFornitori.setOpaque(true);
			lblFornitori.setBackground(new Color(224, 255, 255));
			lblFornitori.setHorizontalAlignment(SwingConstants.CENTER);
			lblFornitori.setFont(new Font("Lucida Grande", Font.BOLD, 45));
			contentPane.add(lblFornitori, BorderLayout.NORTH);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(new Color(224, 255, 255));
			contentPane.add(panel_2, BorderLayout.SOUTH);
			
			JButton btnRegistrati = new JButton("Indietro");
			btnRegistrati.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						UtilityReadWriteCatena.setCatena(GUI.catenaAccesso);
						
						
						save(getX(), getY(), getWidth(), getHeight());
		                MyLogger.OttieniUnLogger(GUI.utenteAccesso).info("Utilizza inventario");

		                frame = new GUI_GeneraleHotel();
		                frame.setBounds(getX(), getY(), getWidth(), getHeight());

		                frame.setVisible(true);
		                setVisible(false);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Errore salvataggio catena", "ERRORE", JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
			});
			btnRegistrati.setPreferredSize(new Dimension(117, 50));
			panel_2.add(btnRegistrati);
			
			JComboBox comboBox = new JComboBox();
		
			JButton btnNewButton = new JButton("Cerca");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						Object o = comboBox.getSelectedItem().toString();
						
						DefaultTableModel model = (DefaultTableModel)table.getModel();
						DefaultTableModel model1 = (DefaultTableModel)table_1.getModel();
				
						model.setRowCount(0);
						model1.setRowCount(0);
						
						for(var x : GUI.catenaAccesso.getInventario()) {

							if(x instanceof ProdFornito && ((ProdFornito) x).getIDFornitore().equals(o)) {
								
								model.addRow(new Object[] {((ProdFornito) x).getID(), ((ProdFornito) x).getPrezzo()});
							}
						}

						
						for(var x : GUI.catenaAccesso.getInventario()) {

							if(x instanceof ProdConcreto && !((ProdConcreto) x).getPrezzoEffettivoMigliore(GUI.catenaAccesso).equals(0)) {
								
								model1.addRow(new Object[] {((ProdConcreto) x).getID(), ((ProdConcreto) x).getPrezzoEffettivoMigliore(GUI.catenaAccesso)});
							}
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Selezionare un fornitore", "ERRORE", JOptionPane.ERROR_MESSAGE);   	
					}
					
					
				}
			});
			btnNewButton.setPreferredSize(new Dimension(117, 50));
			panel_2.add(btnNewButton);
			
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			
			JPanel panel_1 = new JPanel();
			panel.add(panel_1, BorderLayout.NORTH);
			
			textField = new JTextField();
			textField.setColumns(10);
			
			JButton btnAggiungi = new JButton("Aggiungi");
			btnAggiungi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	
					//w.addNewFornitore(GUI.catenaAccesso, textField.getText());
					if(!textField.getText().isBlank()) {
						GUI.catenaAccesso.aggiungiUnFornitore(new Fornitore(textField.getText()));
						JOptionPane.showMessageDialog(null, "Inserito!", "ERRORE", JOptionPane.ERROR_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Inserire il campo di ricerca", "ERRORE", JOptionPane.ERROR_MESSAGE);   		 
					}
				}
			});
			
			JButton btnRimuovi = new JButton("Rimuovi");
			btnRimuovi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!textField.getText().isBlank()) {
						GUI.catenaAccesso.rimuoviUnFornitore(comboBox.getSelectedItem().toString());
						JOptionPane.showMessageDialog(null, "Rimosso!", "ERRORE", JOptionPane.ERROR_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Inserire il campo di ricerca", "ERRORE", JOptionPane.ERROR_MESSAGE);   		 
					}
				}
			});
			
			
			comboBox.addFocusListener(new FocusAdapter() {
				
				
				public void focusGained(FocusEvent e) {				
					try {
						comboBox.removeAllItems();
						
						for(Fornitore x : (ArrayList<Fornitore>)GUI.catenaAccesso.getFornitori()) {
							System.out.println(x.getID());
							comboBox.addItem(x.getID());
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Errore tendina fornitori", "ERRORE", JOptionPane.ERROR_MESSAGE);
					}

				}
				
			});
			
			
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(96)
						.addComponent(comboBox, 0, 173, Short.MAX_VALUE)
						.addGap(30)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 98, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnRimuovi, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
						.addGap(53))
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnAggiungi)
							.addComponent(btnRimuovi)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			panel_1.setLayout(gl_panel_1);
			
			JPanel panel_3 = new JPanel();
			panel.add(panel_3, BorderLayout.CENTER);
			panel_3.setLayout(new GridLayout(0, 2, 0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_3.add(scrollPane);
			
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID Prodotto Fornito", "Prezzo Prodotto"
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
			table.getColumnModel().getColumn(0).setPreferredWidth(153);
			table.getColumnModel().getColumn(1).setPreferredWidth(132);
			scrollPane.setViewportView(table);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			panel_3.add(scrollPane_1);
			
			table_1 = new JTable();
			table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID Prodotto Concreto", "Prezzo Effettivo Migliore"
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
			table_1.getColumnModel().getColumn(0).setPreferredWidth(165);
			table_1.getColumnModel().getColumn(1).setPreferredWidth(151);
			scrollPane_1.setViewportView(table_1);
		}
}
