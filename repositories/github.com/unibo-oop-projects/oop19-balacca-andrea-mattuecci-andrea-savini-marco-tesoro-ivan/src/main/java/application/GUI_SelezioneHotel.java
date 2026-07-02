package application;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

public class GUI_SelezioneHotel extends GUI {

    private JPanel contentPane;
    private JTextField textField;
   
    public GUI_SelezioneHotel() {
        
    	
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 575, 438);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(176, 224, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBackground(new Color(255, 222, 173));
        contentPane.add(panel_1_1, BorderLayout.SOUTH);
        
        JButton btnSalva_1_1 = new JButton("Indietro");
        btnSalva_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
       
                
                save(getX(), getY(), getWidth(), getHeight());
                
                frame = new GUI_Login();
                frame.setBounds(getX(), getY(), getWidth(), getHeight());
               
                frame.setVisible(true);
                setVisible(false);
                
            }
        });
        btnSalva_1_1.setPreferredSize(new Dimension(117, 50));
        panel_1_1.add(btnSalva_1_1);
        
        JComboBox comboBox = new JComboBox();
        comboBox.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		
        		comboBox.removeAllItems();
        		
        		for(Hotel x : (ArrayList<Hotel>)GUI.catenaAccesso.getAlberghi()) {
        			comboBox.addItem(x.getNome());
        		}
        		
        	}
        });
       
        textField = new JTextField();
        
        JButton btnSalva_1_1_1 = new JButton("Conferma");
        btnSalva_1_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	//Setto l'hotel in modo che lo vedano tutti
            	GUI.hotelAccesso = GUI.catenaAccesso.ottieniUnAlbergo((String)comboBox.getSelectedItem()).get();
            	try {
            		UtilityReadWriteCatena.setCatena(GUI.catenaAccesso);
            		System.out.println("Hotel settato");
                    
                    save(getX(), getY(), getWidth(), getHeight());
                    
                    //System.out.println(GUI.gradoAccesso);
                    MyLogger.OttieniUnLogger(GUI.utenteAccesso).info(GUI.utenteAccesso+"- Utilizza l'hotel "+comboBox.getSelectedItem());
                    
                    frame = new GUI_GeneraleHotel();
                    frame.setBounds(getX(), getY(), getWidth(), getHeight());
                   
                    frame.setVisible(true);
                    setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore lettura catena, riprovare", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
 
            }
        });
        
        btnSalva_1_1_1.setPreferredSize(new Dimension(117, 50));
        panel_1_1.add(btnSalva_1_1_1);
        
        JButton btnSalva_1_1_2 = new JButton("Aggiungi");
        btnSalva_1_1_2.setPreferredSize(new Dimension(117, 50));
        panel_1_1.add(btnSalva_1_1_2);
        btnSalva_1_1_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	//Setto l'hotel in modo che lo vedano tutti
            	
            	try {
            		
            		String nome = textField.getText();
                	
                	if(!nome.isBlank()) {
                		
                		if(!nomeHotelPresente(nome)) {
                			
                			if(soloAdmin()) {
                				Hotel h = new Hotel(textField.getText());
                        		GUI.catenaAccesso.aggiungiUnAlbergo(h);
                        		
                            	System.out.println("Hotel aggiunto");
                            	JOptionPane.showMessageDialog(null, "Hotel aggiunto!", "OK", JOptionPane.ERROR_MESSAGE);   	
                			}else {
                				JOptionPane.showMessageDialog(null, "Non ti è permesso eseguire questa operazione!", "OK", JOptionPane.ERROR_MESSAGE);   	
                			}
                				
                		}else {
                			JOptionPane.showMessageDialog(null, "Hotel già presente!", "ERRORE", JOptionPane.ERROR_MESSAGE);
                		}       		
                	}else {
                		JOptionPane.showMessageDialog(null, "Controllare riempimento campi", "OK", JOptionPane.ERROR_MESSAGE);  
                	}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Errore salvataggio hotel", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
            	
            	
            }
        });
        
        JButton btnSalva_1_1_3 = new JButton("Rimuovi");
        btnSalva_1_1_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			if(soloAdmin()) {
        				GUI.catenaAccesso.rimuoviUnAlbergo(""+comboBox.getSelectedItem());
                		comboBox.removeItem(comboBox.getSelectedItem());
        			}else {
        				JOptionPane.showMessageDialog(null, "Non ti è permesso eseguire questa operazione!", "OK", JOptionPane.ERROR_MESSAGE);  
        			}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Rimozione impossibile riprovare", "ERRORE", JOptionPane.ERROR_MESSAGE);
				} 
        	}
        });
        btnSalva_1_1_3.setPreferredSize(new Dimension(117, 50));
        panel_1_1.add(btnSalva_1_1_3);
        
        JLabel lblSelezionaHotel = new JLabel("Seleziona Hotel");
        lblSelezionaHotel.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelezionaHotel.setFont(new Font("Lucida Grande", Font.BOLD, 45));
        contentPane.add(lblSelezionaHotel, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        
        JPanel panel_1 = new JPanel();
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
        			.addGap(156)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        					.addGap(153))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(comboBox, 0, 261, Short.MAX_VALUE)
        					.addGap(148))))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(75)
        			.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
        			.addGap(28))
        );
        panel_1.setLayout(new BorderLayout(0, 0));
        
        JLabel lblAggiungiUnHotel = new JLabel("Aggiungi un hotel:");
        lblAggiungiUnHotel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblAggiungiUnHotel.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1.add(lblAggiungiUnHotel, BorderLayout.NORTH);
        
        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2, BorderLayout.CENTER);
        
      
        textField.setColumns(10);
        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(
        	gl_panel_2.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_2.createSequentialGroup()
        			.addGap(61)
        			.addComponent(textField)
        			.addGap(65))
        );
        gl_panel_2.setVerticalGroup(
        	gl_panel_2.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_2.createSequentialGroup()
        			.addGap(49)
        			.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(53, Short.MAX_VALUE))
        );
        panel_2.setLayout(gl_panel_2);
        panel.setLayout(gl_panel);
        
       
    }
}
