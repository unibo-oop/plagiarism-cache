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
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class GUI_PersoneInHotel extends GUI {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	Calendar cal = Calendar.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_PersoneInHotel frame = new GUI_PersoneInHotel();
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
	public GUI_PersoneInHotel() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 546);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblSceltaConsumi = new JLabel("Persone in hotel");
		lblSceltaConsumi.setHorizontalAlignment(SwingConstants.CENTER);
		lblSceltaConsumi.setFont(new Font("Lucida Grande", Font.BOLD, 45));
		contentPane.add(lblSceltaConsumi, BorderLayout.NORTH);
		
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
		
		JButton btnSalva_1_1_1 = new JButton("Conferma");
		btnSalva_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					UtilityReadWriteCatena.setCatena(catenaAccesso);
					JOptionPane.showMessageDialog(null, "Catena scritta su file!", "ERRORE", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Errore scrittura catena", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnSalva_1_1_1.setPreferredSize(new Dimension(117, 50));
		panel_1_1.add(btnSalva_1_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("TOTALE");
		comboBox.addItem("PRANZO");
		comboBox.addItem("CENA");
		
		JDateChooser dateChooser = new JDateChooser();

		JButton btnSalva_1_1_2 = new JButton("Aggiungi");
		btnSalva_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Date data = dateChooser.getDate();
					cal.setTime(data);
					cal.set(Calendar.HOUR_OF_DAY, 0);
	        		cal.set(Calendar.MINUTE, 0);
	        		cal.set(Calendar.SECOND, 0);
	        		cal.set(Calendar.MILLISECOND, 0);
	        		data = cal.getTime();
	        		
	        		int numeroAdulti = Integer.parseInt(textField.getText());
	        		int numeroBambini = Integer.parseInt(textField_1.getText());
	        		String selected = (String) comboBox.getSelectedItem();
	        		
	        		//System.out.println(data);
	        		
					switch (selected) {
						case "TOTALE": 		
							
							GUI.hotelAccesso.aggiungiUnNTot(data, numeroAdulti, numeroBambini);			
							System.out.println("aggiunto: "+ data + numeroAdulti + numeroBambini);
							
							break;
						
						case "PRANZO": 
							
							GUI.hotelAccesso.aggiungiUnNPranzo(data, numeroAdulti, numeroBambini);
							System.out.println("aggiunto: "+ data + numeroAdulti + numeroBambini);
							
							break;
							
						case "CENA": 
							
							GUI.hotelAccesso.aggiungiUnNCena(data, numeroAdulti, numeroBambini);
							System.out.println("aggiunto: "+ data + numeroAdulti + numeroBambini);
							
							break;
						 
					}
					JOptionPane.showMessageDialog(null, "Aggiunto!", "ERRORE", JOptionPane.ERROR_MESSAGE);
					try {
						UtilityReadWriteCatena.setCatena(catenaAccesso);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Errore lettura catena", "ERRORE", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore aggiunta", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnSalva_1_1_2.setPreferredSize(new Dimension(117, 50));
		panel_1_1.add(btnSalva_1_1_2);
		
		JButton btnSalva_1_1_3 = new JButton("Rimuovi");
		btnSalva_1_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
				
					Date data = dateChooser.getDate();
					cal.setTime(data);
					cal.set(Calendar.HOUR_OF_DAY, 0);
	        		cal.set(Calendar.MINUTE, 0);
	        		cal.set(Calendar.SECOND, 0);
	        		cal.set(Calendar.MILLISECOND, 0);
	        		data = cal.getTime();
	        		
	        		String selected = (String) comboBox.getSelectedItem();
	        		
	        		switch (selected) {
					case "TOTALE": 
						GUI.hotelAccesso.getnTotClientiGiornaliero().remove(data);
						textField.setText("");
						textField_1.setText("");
						System.out.println("Rimosso");
						break;
					
					case "PRANZO": 
						GUI.hotelAccesso.getnPranzoClientiGiornaliero().remove(data);
						textField.setText("");
						textField_1.setText("");
						System.out.println("Rimosso");
						break;
						
					case "CENA": 
						GUI.hotelAccesso.getnCenaClientiGiornaliero().remove(data);
						textField.setText("");
						textField_1.setText("");
						System.out.println("Rimosso");
						break;
	
	        		}
	        		JOptionPane.showMessageDialog(null, "Rimosso", "ERRORE", JOptionPane.ERROR_MESSAGE);
	        		try {
	    				UtilityReadWriteCatena.setCatena(catenaAccesso);
	    			} catch (Exception e1) {
	    				JOptionPane.showMessageDialog(null, "Errore lettura catena", "ERRORE", JOptionPane.ERROR_MESSAGE);
	    			}
        		} catch (Exception e2) {
        			JOptionPane.showMessageDialog(null, "Errore rimozione", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
			}
				
		});
		btnSalva_1_1_3.setPreferredSize(new Dimension(117, 50));
		panel_1_1.add(btnSalva_1_1_3);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Data");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		JButton btnMostraSalvati = new JButton("mostra salvati");
		btnMostraSalvati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Date data = dateChooser.getDate();
				System.out.println("data selezionata = "+data);
				
					
				if(data != null) {
					cal.setTime(data);
					cal.set(Calendar.HOUR_OF_DAY, 0);
	        		cal.set(Calendar.MINUTE, 0);
	        		cal.set(Calendar.SECOND, 0);
	        		cal.set(Calendar.MILLISECOND, 0);
	        		data = cal.getTime();
	        		
					String selected = (String) comboBox.getSelectedItem();
					Integer[] n;
					String numeroAdulti = "";
					String numeroBambini = "";
					
					n = GUI.hotelAccesso.getnTotClientiGiornaliero().get(data);

					if(n != null) {
						switch (selected) {																																				
							case "TOTALE": 
								
								n = GUI.hotelAccesso.getnTotClientiGiornaliero().get(data);		
								//numero adulti
								numeroAdulti = String.valueOf(Array.get(n, 0));
								//numero bambini
								numeroBambini = String.valueOf(Array.get(n, 1));
								
								break;
							
							case "PRANZO": 
								n = GUI.hotelAccesso.getnPranzoClientiGiornaliero().get(data);
								//numero adulti
								numeroAdulti = String.valueOf(Array.get(n, 0));
								//numero bambini
								numeroBambini = String.valueOf(Array.get(n, 1));
								
								break;
								
							case "CENA": 
								 n = GUI.hotelAccesso.getnCenaClientiGiornaliero().get(data);
								//numero adulti
								 numeroAdulti = String.valueOf(Array.get(n, 0));
								//numero bambini
								 numeroBambini = String.valueOf(Array.get(n, 1));

								break;
			
						}
						
						System.out.println(numeroAdulti);
						System.out.println(numeroBambini);
						textField.setText(numeroAdulti);
						textField_1.setText(numeroBambini);
						
					}

				}else {
					JOptionPane.showMessageDialog(null, "Inserire data di ricerca", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
				
			
			}
		});
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(260)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnMostraSalvati)
					.addGap(95))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnMostraSalvati)
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblNumeroAdulti = new JLabel("Numero adulti");
		lblNumeroAdulti.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroAdulti.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(125)
					.addComponent(lblNumeroAdulti, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
					.addGap(267))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(37)
					.addComponent(lblNumeroAdulti, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addGap(40)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JLabel lblNumeroBambini = new JLabel("Numero bambini");
		lblNumeroBambini.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroBambini.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setColumns(10);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(101)
					.addComponent(lblNumeroBambini, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
					.addGap(19)
					.addComponent(textField_1)
					.addGap(269))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(37)
					.addComponent(lblNumeroBambini, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addGap(41)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(275)
					.addComponent(comboBox, 0, 211, Short.MAX_VALUE)
					.addGap(251))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_4.createSequentialGroup()
					.addGap(34)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(39, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
	}
}
