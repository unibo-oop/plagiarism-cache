package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import application.Consumi.Cliente;
import application.Consumi.Pasto;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTable;

public class GUI_Consumi extends GUI {
    

    private JPanel contentPane;
    private JTable table;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_Consumi frame = new GUI_Consumi();
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
    public GUI_Consumi() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 752, 575);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(175, 238, 238));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JLabel lblConsumi = new JLabel("Consumi");
        lblConsumi.setHorizontalAlignment(SwingConstants.CENTER);
        lblConsumi.setFont(new Font("Lucida Grande", Font.BOLD, 45));
        contentPane.add(lblConsumi, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_1 = new JPanel();
        panel.add(panel_1, BorderLayout.NORTH);
        panel_1.setLayout(new GridLayout(0, 2, 0, 0));
        
        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2);
        
        JRadioButton chckbxDataSingola = new JRadioButton("Data Singola");
        chckbxDataSingola.setSelected(true);
        chckbxDataSingola.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(
            gl_panel_2.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_2.createSequentialGroup()
                    .addContainerGap(106, Short.MAX_VALUE)
                    .addComponent(chckbxDataSingola, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addGap(75))
        );
        gl_panel_2.setVerticalGroup(
            gl_panel_2.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2.createSequentialGroup()
                    .addGap(9)
                    .addComponent(chckbxDataSingola)
                    .addContainerGap(11, Short.MAX_VALUE))
        );
        panel_2.setLayout(gl_panel_2);
        
        JPanel panel_3 = new JPanel();
        panel_1.add(panel_3);
        
        JRadioButton chckbxRangeDate = new JRadioButton("Range Date");
        chckbxRangeDate.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
        GroupLayout gl_panel_3 = new GroupLayout(panel_3);
        gl_panel_3.setHorizontalGroup(
            gl_panel_3.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_3.createSequentialGroup()
                    .addGap(92)
                    .addComponent(chckbxRangeDate, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(89, Short.MAX_VALUE))
        );
        gl_panel_3.setVerticalGroup(
            gl_panel_3.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_3.createSequentialGroup()
                    .addGap(10)
                    .addComponent(chckbxRangeDate, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(10, Short.MAX_VALUE))
        );
        panel_3.setLayout(gl_panel_3);
                
        ButtonGroup g = new ButtonGroup();
        g.add(chckbxDataSingola);
        g.add(chckbxRangeDate);
        
        
        JPanel panel_4 = new JPanel();
        panel.add(panel_4, BorderLayout.CENTER);
        panel_4.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_5 = new JPanel();
        panel_5.setPreferredSize(new Dimension(10, 50));
        panel_5.setMinimumSize(new Dimension(10, 30));
        panel_4.add(panel_5, BorderLayout.NORTH);
        panel_5.setLayout(new GridLayout(0, 4, 0, 0));
        
        JPanel panel_6 = new JPanel();
        panel_5.add(panel_6);
        panel_6.setLayout(new BorderLayout(0, 0));
        
        JLabel lblData = new JLabel("Data 1");
        lblData.setHorizontalAlignment(SwingConstants.CENTER);
        lblData.setHorizontalTextPosition(SwingConstants.CENTER);
        panel_6.add(lblData, BorderLayout.NORTH);
        
        JPanel panel_10 = new JPanel();
        panel_6.add(panel_10, BorderLayout.CENTER);
        panel_10.setLayout(new GridLayout(0, 1, 0, 0));
        
        JDateChooser dateChooser = new JDateChooser();
        panel_10.add(dateChooser);
        
        JPanel panel_7 = new JPanel();
        panel_5.add(panel_7);
        panel_7.setLayout(new BorderLayout(0, 0));
        
        JLabel lblData_4 = new JLabel("Data 2");
        lblData_4.setHorizontalTextPosition(SwingConstants.CENTER);
        lblData_4.setHorizontalAlignment(SwingConstants.CENTER);
        panel_7.add(lblData_4, BorderLayout.NORTH);
        
        JPanel panel_11 = new JPanel();
        panel_7.add(panel_11, BorderLayout.CENTER);
        panel_11.setLayout(new GridLayout(0, 1, 0, 0));
        
        JDateChooser dateChooser_1 = new JDateChooser();
        panel_11.add(dateChooser_1);
        dateChooser_1.setEnabled(false);
        
        chckbxDataSingola.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dateChooser_1.setEnabled(false);
        	}
        });
        
        chckbxRangeDate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dateChooser_1.setEnabled(true);
        	}
        });
        
        JPanel panel_8 = new JPanel();
        panel_5.add(panel_8);
        panel_8.setLayout(new BorderLayout(0, 0));
        
        JLabel lblPasto = new JLabel("Pasto");
        lblPasto.setHorizontalTextPosition(SwingConstants.CENTER);
        lblPasto.setHorizontalAlignment(SwingConstants.CENTER);
        panel_8.add(lblPasto, BorderLayout.NORTH);
        
        JPanel panel_11_1 = new JPanel();
        panel_8.add(panel_11_1, BorderLayout.CENTER);
        panel_11_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JComboBox comboBox = new JComboBox(Pasto.values());      
        panel_11_1.add(comboBox);
        
        JPanel panel_9 = new JPanel();
        panel_5.add(panel_9);
        panel_9.setLayout(new BorderLayout(0, 0));
        
        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setHorizontalTextPosition(SwingConstants.CENTER);
        lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
        panel_9.add(lblCliente, BorderLayout.NORTH);
        
        JPanel panel_11_2 = new JPanel();
        panel_9.add(panel_11_2, BorderLayout.CENTER);
        panel_11_2.setLayout(new GridLayout(0, 1, 0, 0));
        
        JComboBox comboBox_1 = new JComboBox(Cliente.values());
        panel_11_2.add(comboBox_1);
        
        JPanel panel_12 = new JPanel();
        panel_12.setBackground(new Color(255, 255, 224));
        panel_4.add(panel_12, BorderLayout.CENTER);
        panel_12.setLayout(new BorderLayout(0, 0));
        
        table = new JTable();
        panel_12.add(table);
        
        JPanel panel_1_2_1 = new JPanel();
        panel_1_2_1.setBackground(new Color(255, 222, 173));
        panel_12.add(panel_1_2_1, BorderLayout.SOUTH);
        
        JButton btnGrafico_1 = new JButton("Grafico");
        btnGrafico_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		try {
        			Pasto pasto = (Pasto) comboBox.getSelectedItem();
    				Cliente cliente = (Cliente) comboBox_1.getSelectedItem();
    				String legenda = textField.getText();

        	        Consumi cons = new ConsumiImpl(GUI.hotelAccesso);
        	        System.out.println(GUI.hotelAccesso.getNome());
        	        
        	        
        	        
        	        Date data1 = dateChooser.getDate();
    				Calendar cal = Calendar.getInstance();
    				Date data2 = dateChooser_1.getDate();
    				System.out.println("inventario" + GUI.catenaAccesso.getInventario());
        	        
    				if(data1 != null && data2 != null) {
    					
    						cal.setTime(data1);
    		        		cal.set(Calendar.HOUR_OF_DAY, 0);
    		        		cal.set(Calendar.MINUTE, 0);
    		        		cal.set(Calendar.SECOND, 0);
    		        		cal.set(Calendar.MILLISECOND, 0);
    		        		data1 = cal.getTime();
    		        		
    		        		cal.setTime(data2);
    		        		cal.set(Calendar.HOUR_OF_DAY, 0);
    		        		cal.set(Calendar.MINUTE, 0);
    		        		cal.set(Calendar.SECOND, 0);
    		        		cal.set(Calendar.MILLISECOND, 0);       		
    						data2 = cal.getTime();
    					
    					try {
    						DrawGraphImpl draw = new DrawGraphImpl(cons, GUI.catenaAccesso);
    						System.out.println("grafico: " + draw.getGraphConsumi(data1, data2, legenda));
    						DisplayGraph dig = new DisplayGraph(cons, GUI.catenaAccesso, data1, data2, legenda);
    						dig.setVisible(true);
    					} catch (DateNotFound e1) {
    						// TODO Auto-generated catch block
    						//e1.printStackTrace();
    						JOptionPane.showMessageDialog(null, "Errore disegno grafico", "ERRORE", JOptionPane.ERROR_MESSAGE);
    					}
    				}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore creazione grafico", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        		
				
        	}
        });
        btnGrafico_1.setPreferredSize(new Dimension(117, 50));
        panel_1_2_1.add(btnGrafico_1);
        
        textField = new JTextField();
        panel_1_2_1.add(textField);
        textField.setColumns(10);
        
        JPanel panel_1_2 = new JPanel();
        panel_1_2.setBackground(new Color(255, 222, 173));
        contentPane.add(panel_1_2, BorderLayout.SOUTH);
        
        JButton btnIndietro = new JButton("Indietro");
        btnIndietro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		save(getX(), getY(), getWidth(), getHeight());
                
                frame = new GUI_GeneraleHotel();
                frame.setBounds(getX(), getY(), getWidth(), getHeight());
               
                frame.setVisible(true);
                setVisible(false);
        		
        	}
        });
        btnIndietro.setPreferredSize(new Dimension(117, 50));
        panel_1_2.add(btnIndietro);
        
        table = new JTable();
		
        
        JButton btnTabella = new JButton("Consumi");
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        btnTabella.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
        		try {
					
				
        		Pasto pasto = (Pasto) comboBox.getSelectedItem();
				Cliente cliente = (Cliente) comboBox_1.getSelectedItem();
        		Date data1 = dateChooser.getDate();
				Calendar cal = Calendar.getInstance();
				Date data2 = dateChooser_1.getDate();
		        Consumi c1 = new ConsumiImpl(GUI.hotelAccesso);
				if(data1 != null && data2 != null) {
					
					cal.setTime(data1);
	        		cal.set(Calendar.HOUR_OF_DAY, 0);
	        		cal.set(Calendar.MINUTE, 0);
	        		cal.set(Calendar.SECOND, 0);
	        		cal.set(Calendar.MILLISECOND, 0);
	        		data1 = cal.getTime();
	        		
	        		cal.setTime(data2);
	        		cal.set(Calendar.HOUR_OF_DAY, 0);
	        		cal.set(Calendar.MINUTE, 0);
	        		cal.set(Calendar.SECOND, 0);
	        		cal.set(Calendar.MILLISECOND, 0);       		
					data2 = cal.getTime();
	        		
					try {
						NavigableMap<Date, HashMap<String, Float>> map = c1.getConsumi(pasto, cliente, data1, data2);
						System.out.println(map);
						
						/*
						for(Entry<Date, HashMap<String, Float>> mappa : map.entrySet()) {
							for(Entry<String, Float> h1 : mappa.getValue().entrySet()) {	
								model.addRow(new Object[] {(mappa.getValue()), (h1.getKey()), (h1.getValue())});
							}
						}
						*/
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Errore creazione mappa range data", "ERRORE", JOptionPane.ERROR_MESSAGE);
					}
				}else if (data1 != null){
					cal.setTime(data1);
	        		cal.set(Calendar.HOUR_OF_DAY, 0);
	        		cal.set(Calendar.MINUTE, 0);
	        		cal.set(Calendar.SECOND, 0);
	        		cal.set(Calendar.MILLISECOND, 0);
	        		data1 = cal.getTime();
					try {
						NavigableMap<Date, HashMap<String, Float>> map = c1.getConsumi(pasto, cliente, data1);
						System.out.println(map);
						
						/*
						for(Entry<Date, HashMap<String, Float>> mappa : map.entrySet()) {
							for(Entry<String, Float> h1 : mappa.getValue().entrySet()) {	
								model.addRow(new Object[] {(mappa.getValue()), (h1.getKey()), (h1.getValue())});
							}
						}
						*/
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Errore creazione mappa range data", "ERRORE", JOptionPane.ERROR_MESSAGE);
					}	
				}
        		} catch (Exception e2) {
        			JOptionPane.showMessageDialog(null, "Errore consumi", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        	}
        });
        btnTabella.setPreferredSize(new Dimension(117, 50));
        panel_1_2.add(btnTabella);
        
        JButton btnConsumoProCapite = new JButton("Consumo pro capite");
        btnConsumoProCapite.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {

	        		Pasto pasto = (Pasto) comboBox.getSelectedItem();
					Cliente cliente = (Cliente) comboBox_1.getSelectedItem();
	        		Date data1 = dateChooser.getDate();
					Calendar cal = Calendar.getInstance();
					Date data2 = dateChooser_1.getDate();
			        Consumi c1 = new ConsumiImpl(GUI.hotelAccesso);
					if(data1 != null && data2 != null) {
						
						cal.setTime(data1);
		        		cal.set(Calendar.HOUR_OF_DAY, 0);
		        		cal.set(Calendar.MINUTE, 0);
		        		cal.set(Calendar.SECOND, 0);
		        		cal.set(Calendar.MILLISECOND, 0);
		        		data1 = cal.getTime();
		        		
		        		cal.setTime(data2);
		        		cal.set(Calendar.HOUR_OF_DAY, 0);
		        		cal.set(Calendar.MINUTE, 0);
		        		cal.set(Calendar.SECOND, 0);
		        		cal.set(Calendar.MILLISECOND, 0);       		
						data2 = cal.getTime();
		        		
						try {
							NavigableMap<Date, HashMap<String, Float>> map = c1.getConsumoProCapite(pasto, cliente, data1, data2);
							System.out.println(map);
							
							/*
							for(Entry<Date, HashMap<String, Float>> mappa : map.entrySet()) {
								for(Entry<String, Float> h1 : mappa.getValue().entrySet()) {	
									model.addRow(new Object[] {(mappa.getValue()), (h1.getKey()), (h1.getValue())});
								}
							}
							*/
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Errore creazione mappa range data", "ERRORE", JOptionPane.ERROR_MESSAGE);
						}
					}else if (data1 != null){
						cal.setTime(data1);
		        		cal.set(Calendar.HOUR_OF_DAY, 0);
		        		cal.set(Calendar.MINUTE, 0);
		        		cal.set(Calendar.SECOND, 0);
		        		cal.set(Calendar.MILLISECOND, 0);
		        		data1 = cal.getTime();
						try {
							NavigableMap<Date, HashMap<String, Float>> map = c1.getConsumoProCapite(pasto, cliente, data1);
							System.out.println(map);
							
							/*
							for(Entry<Date, HashMap<String, Float>> mappa : map.entrySet()) {
								for(Entry<String, Float> h1 : mappa.getValue().entrySet()) {	
									model.addRow(new Object[] {(mappa.getValue()), (h1.getKey()), (h1.getValue())});
								}
							}
							*/
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Errore creazione mappa range data", "ERRORE", JOptionPane.ERROR_MESSAGE);
						}	
					}
        		} catch (Exception e2) {
        			JOptionPane.showMessageDialog(null, "Errore consumi pro capite", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        	}
        });
        btnConsumoProCapite.setPreferredSize(new Dimension(150, 50));
        panel_1_2.add(btnConsumoProCapite);
        
        JButton btnConsumoMedio = new JButton("Consumo medio");
        btnConsumoMedio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
					
				
	        		Pasto pasto = (Pasto) comboBox.getSelectedItem();
					Cliente cliente = (Cliente) comboBox_1.getSelectedItem();
	        		Date data1 = dateChooser.getDate();
					Calendar cal = Calendar.getInstance();
					Date data2 = dateChooser_1.getDate();
			        Consumi c1 = new ConsumiImpl(GUI.hotelAccesso);
					if(data1 != null && data2 != null) {
						
						cal.setTime(data1);
		        		cal.set(Calendar.HOUR_OF_DAY, 0);
		        		cal.set(Calendar.MINUTE, 0);
		        		cal.set(Calendar.SECOND, 0);
		        		cal.set(Calendar.MILLISECOND, 0);
		        		data1 = cal.getTime();
		        		
		        		cal.setTime(data2);
		        		cal.set(Calendar.HOUR_OF_DAY, 0);
		        		cal.set(Calendar.MINUTE, 0);
		        		cal.set(Calendar.SECOND, 0);
		        		cal.set(Calendar.MILLISECOND, 0);       		
						data2 = cal.getTime();
		        		
						try {
							HashMap<String, Float> map = c1.getConsumoMedioRangeDate(pasto, cliente, data1, data2);
							System.out.println(map);
							
							/*
							for(Entry<Date, HashMap<String, Float>> mappa : map.entrySet()) {
								for(Entry<String, Float> h1 : mappa.getValue().entrySet()) {	
									model.addRow(new Object[] {(mappa.getValue()), (h1.getKey()), (h1.getValue())});
								}
							}
							*/
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Errore creazione mappa range data", "ERRORE", JOptionPane.ERROR_MESSAGE);
						}
					}
        		} catch (Exception e2) {
        			JOptionPane.showMessageDialog(null, "Errore consumo medio", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        	}
        });
        btnConsumoMedio.setPreferredSize(new Dimension(117, 50));
        panel_1_2.add(btnConsumoMedio);
    }
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
