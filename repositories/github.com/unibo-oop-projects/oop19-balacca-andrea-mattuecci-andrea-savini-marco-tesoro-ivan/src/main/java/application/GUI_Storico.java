package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;

public class GUI_Storico extends GUI {

    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_Storico frame = new GUI_Storico();
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
    public GUI_Storico() {
    	
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
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		JDateChooser dateChooser = new JDateChooser();
        JDateChooser dateChooser_1 = new JDateChooser();
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 777, 647);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(176, 224, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JLabel lblStorico = new JLabel("Storico");
        lblStorico.setHorizontalAlignment(SwingConstants.CENTER);
        lblStorico.setFont(new Font("Lucida Grande", Font.BOLD, 45));
        contentPane.add(lblStorico, BorderLayout.NORTH);
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBackground(new Color(255, 222, 173));
        contentPane.add(panel_1_1, BorderLayout.SOUTH);
        
        JButton btnSalva_1_1 = new JButton("Indietro");
        btnSalva_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                save(getX(), getY(), getWidth(), getHeight());
                
                frame = new GUI_GeneraleHotel();
                frame.setBounds(getX(), getY(), getWidth(), getHeight());
               
                frame.setVisible(true);
                setVisible(false);
                
            }
        });
        btnSalva_1_1.setPreferredSize(new Dimension(117, 50));
        panel_1_1.add(btnSalva_1_1);
        
        
        
        JButton btnSalva_1_1_1 = new JButton("Cerca carico");
        btnSalva_1_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
					
				
	        		Date data1 = dateChooser.getDate();
	        		Date data2 = dateChooser_1.getDate();
	        		
	        		try {
	
	        			for(var x : s.searchload(GUI.catenaAccesso, GUI.hotelAccesso.getNome(), GUI.dispensaAccesso.getNome(), data1, data2).keySet()) {
	        				model.addRow(new Object[] {x, s.searchload(GUI.catenaAccesso, GUI.hotelAccesso.getNome(), GUI.dispensaAccesso.getNome(), data1, data2).get(x)});
	        			}
	        			
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Inserire entrambe le date", "ERRORE", JOptionPane.ERROR_MESSAGE);  
					}
        		} catch (Exception e2) {
        			JOptionPane.showMessageDialog(null, "Errore cerca carico", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        		
        	}
        });
        btnSalva_1_1_1.setPreferredSize(new Dimension(117, 50));
        panel_1_1.add(btnSalva_1_1_1);
        
        JButton btnSalva_1_1_1_1 = new JButton("Cerca scarico");
        btnSalva_1_1_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
					
				
	        		Date data1 = dateChooser.getDate();
	        		Date data2 = dateChooser_1.getDate();
	        		
	        		try {
	
	        			for(var x : s.searchdump(GUI.catenaAccesso, GUI.hotelAccesso.getNome(), GUI.dispensaAccesso.getNome(), data1, data2).keySet()) {
	        				model.addRow(new Object[] {x, s.searchdump(GUI.catenaAccesso, GUI.hotelAccesso.getNome(), GUI.dispensaAccesso.getNome(), data1, data2).get(x)});
	        			}
	        			
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Inserire entrambe le date", "ERRORE", JOptionPane.ERROR_MESSAGE);  
					}
        		} catch (Exception e2) {
        			JOptionPane.showMessageDialog(null, "Errore cerca carico", "ERRORE", JOptionPane.ERROR_MESSAGE);  
				}
        		
        	}
        });
        btnSalva_1_1_1_1.setPreferredSize(new Dimension(117, 50));
        panel_1_1.add(btnSalva_1_1_1_1);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_1 = new JPanel();
        panel_1.setPreferredSize(new Dimension(10, 100));
        panel.add(panel_1, BorderLayout.NORTH);
        panel_1.setLayout(new GridLayout(0, 2, 0, 0));
        
        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2);
        panel_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lblDataInizioRicerca = new JLabel("Data inizio ricerca");
        lblDataInizioRicerca.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblDataInizioRicerca.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(lblDataInizioRicerca, BorderLayout.NORTH);
        
        JPanel panel_6 = new JPanel();
        panel_2.add(panel_6, BorderLayout.CENTER);
        
        
        GroupLayout gl_panel_6 = new GroupLayout(panel_6);
        gl_panel_6.setHorizontalGroup(
        	gl_panel_6.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_panel_6.createSequentialGroup()
        			.addGap(117)
        			.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
        			.addGap(114))
        );
        gl_panel_6.setVerticalGroup(
        	gl_panel_6.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_6.createSequentialGroup()
        			.addGap(18)
        			.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(31, Short.MAX_VALUE))
        );
        panel_6.setLayout(gl_panel_6);
        
        JPanel panel_3 = new JPanel();
        panel_1.add(panel_3);
        panel_3.setLayout(new BorderLayout(0, 0));
        
        JLabel lblDataFineRicerca = new JLabel("Data fine ricerca");
        lblDataFineRicerca.setHorizontalAlignment(SwingConstants.CENTER);
        lblDataFineRicerca.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        panel_3.add(lblDataFineRicerca, BorderLayout.NORTH);
        
        JPanel panel_7 = new JPanel();
        panel_3.add(panel_7, BorderLayout.CENTER);
        
       
        GroupLayout gl_panel_7 = new GroupLayout(panel_7);
        gl_panel_7.setHorizontalGroup(
        	gl_panel_7.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_panel_7.createSequentialGroup()
        			.addGap(111)
        			.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(120, Short.MAX_VALUE))
        );
        gl_panel_7.setVerticalGroup(
        	gl_panel_7.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_7.createSequentialGroup()
        			.addGap(21)
        			.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(28, Short.MAX_VALUE))
        );
        panel_7.setLayout(gl_panel_7);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);
        
       
        scrollPane.setViewportView(table);
    }
}
