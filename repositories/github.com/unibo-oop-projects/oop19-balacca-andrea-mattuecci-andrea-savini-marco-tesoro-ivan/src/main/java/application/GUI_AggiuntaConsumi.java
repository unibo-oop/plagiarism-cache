package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import application.Consumi.Cliente;
import application.Consumi.Pasto;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class GUI_AggiuntaConsumi extends GUI {

    private JPanel contentPane;
    private JPanel panel_1;
    private JPanel panel_1_1;
    private JButton btnSalva;
    private JPanel panel;
    private JPanel panel_2;
    private JPanel panel_1_2;
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
    private JPanel panel_17;
    private JPanel panel_18;
    private JPanel panel_19;
    private JPanel panel_20;
    private JPanel panel_21;
    private JLabel lblContenuto_1;
    private JPanel panel_1_3;
    private JButton btnCarico;
    private JButton btnSalva_1;
    private JPanel panel_22;
    private JPanel panel_23;
    private JScrollPane scrollPane_1;
    private JPanel panel_25;
    private JPanel panel_26;
    private JPanel panel_27;
    private JPanel panel_28;
    private JPanel panel_29;
    private JTextField textField_1;
    
    //--------> da spostare in GUI
    HashMap<String, Float> mappa = new  HashMap<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_AggiuntaConsumi frame = new GUI_AggiuntaConsumi();
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
    public GUI_AggiuntaConsumi() {
    	
    	
    	
        setMinimumSize(new Dimension(780, 960));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 773, 971);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(176, 224, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JLabel lblDispensa = new JLabel("Aggiunta consumi");
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
        
        JButton btnSalva_1_1 = new JButton("Rimuovi tutto");
        btnSalva_1_1.setPreferredSize(new Dimension(117, 50));
        panel_1_2.add(btnSalva_1_1);
        
        panel_3 = new JPanel();
        panel_2.add(panel_3, BorderLayout.CENTER);
        panel_3.setLayout(new GridLayout(0, 2, 0, 0));
        
        panel_4 = new JPanel();
        panel_3.add(panel_4);
        
        JLabel lblTipologiePresenti = new JLabel("Data:");
        lblTipologiePresenti.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipologiePresenti.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        GroupLayout gl_panel_4 = new GroupLayout(panel_4);
        gl_panel_4.setHorizontalGroup(
        	gl_panel_4.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel_4.createSequentialGroup()
        			.addContainerGap(256, Short.MAX_VALUE)
        			.addComponent(lblTipologiePresenti, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        gl_panel_4.setVerticalGroup(
        	gl_panel_4.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_4.createSequentialGroup()
        			.addGap(27)
        			.addComponent(lblTipologiePresenti)
        			.addContainerGap(28, Short.MAX_VALUE))
        );
        panel_4.setLayout(gl_panel_4);
        
        panel_7 = new JPanel();
        panel_3.add(panel_7);
        
        JDateChooser dateChooser = new JDateChooser();
    
        GroupLayout gl_panel_7 = new GroupLayout(panel_7);
        gl_panel_7.setHorizontalGroup(
        	gl_panel_7.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_7.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(194, Short.MAX_VALUE))
        );
        gl_panel_7.setVerticalGroup(
        	gl_panel_7.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_panel_7.createSequentialGroup()
        			.addContainerGap(24, Short.MAX_VALUE)
        			.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        			.addGap(22))
        );
        panel_7.setLayout(gl_panel_7);
        
        panel_5 = new JPanel();
        panel_3.add(panel_5);
        
        JLabel lblPasto = new JLabel("Pasto");
        lblPasto.setHorizontalTextPosition(SwingConstants.CENTER);
        lblPasto.setHorizontalAlignment(SwingConstants.CENTER);
        
        JComboBox comboBox = new JComboBox(Pasto.values());
        GroupLayout gl_panel_5 = new GroupLayout(panel_5);
        gl_panel_5.setHorizontalGroup(
        	gl_panel_5.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel_5.createSequentialGroup()
        			.addContainerGap(158, Short.MAX_VALUE)
        			.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblPasto, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
        				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
        			.addGap(42))
        );
        gl_panel_5.setVerticalGroup(
        	gl_panel_5.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel_5.createSequentialGroup()
        			.addContainerGap(17, Short.MAX_VALUE)
        			.addComponent(lblPasto)
        			.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        			.addGap(13))
        );
        panel_5.setLayout(gl_panel_5);
        
        panel_8 = new JPanel();
        panel_3.add(panel_8);
        
        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setHorizontalTextPosition(SwingConstants.CENTER);
        lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
        
        JComboBox comboBox_1 = new JComboBox(Cliente.values());
        GroupLayout gl_panel_8 = new GroupLayout(panel_8);
        gl_panel_8.setHorizontalGroup(
        	gl_panel_8.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_8.createSequentialGroup()
        			.addGap(46)
        			.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
        				.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(154, Short.MAX_VALUE))
        );
        gl_panel_8.setVerticalGroup(
        	gl_panel_8.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel_8.createSequentialGroup()
        			.addContainerGap(18, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lblCliente)
        			.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        			.addGap(11))
        );
        panel_8.setLayout(gl_panel_8);
        
        panel_6 = new JPanel();
        panel_3.add(panel_6);
        GroupLayout gl_panel_6 = new GroupLayout(panel_6);
        gl_panel_6.setHorizontalGroup(
        	gl_panel_6.createParallelGroup(Alignment.TRAILING)
        		.addGap(0, 385, Short.MAX_VALUE)
        );
        gl_panel_6.setVerticalGroup(
        	gl_panel_6.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 80, Short.MAX_VALUE)
        );
        panel_6.setLayout(gl_panel_6);
        
        panel_9 = new JPanel();
        panel_3.add(panel_9);
        GroupLayout gl_panel_9 = new GroupLayout(panel_9);
        gl_panel_9.setHorizontalGroup(
        	gl_panel_9.createParallelGroup(Alignment.TRAILING)
        		.addGap(0, 385, Short.MAX_VALUE)
        );
        gl_panel_9.setVerticalGroup(
        	gl_panel_9.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 80, Short.MAX_VALUE)
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
        
        panel_17 = new JPanel();
        scrollPane.setViewportView(panel_17);
        
        panel_18 = new JPanel();
        
        panel_19 = new JPanel();
        
        panel_21 = new JPanel();
        panel_21.setBackground(Color.RED);
        GroupLayout gl_panel_19 = new GroupLayout(panel_19);
        gl_panel_19.setHorizontalGroup(
            gl_panel_19.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_19.createSequentialGroup()
                    .addGap(55)
                    .addComponent(panel_21, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addGap(48))
        );
        gl_panel_19.setVerticalGroup(
            gl_panel_19.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_19.createSequentialGroup()
                    .addGap(39)
                    .addComponent(panel_21, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                    .addGap(31))
        );
        panel_19.setLayout(gl_panel_19);
        panel_17.setLayout(new GridLayout(0, 2, 0, 0));
        
        panel_20 = new JPanel();
        panel_20.setBackground(Color.RED);
        panel_20.setForeground(Color.RED);
        GroupLayout gl_panel_18 = new GroupLayout(panel_18);
        gl_panel_18.setHorizontalGroup(
            gl_panel_18.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_18.createSequentialGroup()
                    .addGap(46)
                    .addComponent(panel_20, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addGap(47))
        );
        gl_panel_18.setVerticalGroup(
            gl_panel_18.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_18.createSequentialGroup()
                    .addGap(38)
                    .addComponent(panel_20, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                    .addContainerGap())
        );
        panel_18.setLayout(gl_panel_18);
        panel_17.add(panel_18);
        panel_17.add(panel_19);
        
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
        
        btnCarico = new JButton("Aggiungi ai consumi");
        btnCarico.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		
        		Date data = dateChooser.getDate();
        		Calendar cal = Calendar.getInstance();
        		cal.setTime(data);
        		cal.set(Calendar.HOUR_OF_DAY, 0);
        		cal.set(Calendar.MINUTE, 0);
        		cal.set(Calendar.SECOND, 0);
        		cal.set(Calendar.MILLISECOND, 0);
        		data = cal.getTime();
        		System.out.println("Data: "+data);
        		
        		
        		Pasto pasto = (Pasto)comboBox.getSelectedItem();
        		Cliente persona = (Cliente)comboBox_1.getSelectedItem();
        		
        		HashMap<String, Float> tmp = (HashMap<String, Float>) mappa.clone();
        		switch(pasto) {
	        		case COLAZIONE:
	        			GUI.hotelAccesso.getConsumiColazione().put(data, tmp);
	        			System.out.println("Aggiunto ai consumi: "+ GUI.hotelAccesso.getConsumiColazione());
	        		break;
	        		case PRANZO:	        			
	        			switch(persona) {
	        				case  BAMBINO:
	        					GUI.hotelAccesso.getConsumiBimbiPranzo().put(data, tmp);	
	        					System.out.println("Aggiunto ai consumi: "+ GUI.hotelAccesso.getConsumiBimbiPranzo());
	        				break;
	        				case ADULTO:	        					
	        					GUI.hotelAccesso.getConsumiAdultiPranzo().put(data, tmp);
	        					System.out.println("Aggiunto ai consumi: "+ GUI.hotelAccesso.getConsumiAdultiPranzo());
	        				break;
	        			}
	        		break;
	        		case CENA:
	        			switch(persona){
		        			case BAMBINO:
		        				GUI.hotelAccesso.getConsumiBimbiCena().put(data, tmp);
		        				System.out.println("Aggiunto ai consumi: "+ GUI.hotelAccesso.getConsumiBimbiCena());
		        			break;
		        			case ADULTO:
		        				GUI.hotelAccesso.getConsumiAdultiCena().put(data, tmp);
		        				System.out.println("Aggiunto ai consumi: "+ GUI.hotelAccesso.getConsumiAdultiCena());
		        			break;
	        			}
	        		default:
        		}
        		mappa.clear();
        	}
        });
        btnCarico.setPreferredSize(new Dimension(150, 50));
        panel_1_3.add(btnCarico);
        
        btnSalva_1 = new JButton("Rimuovi dai consumi");
        btnSalva_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Date data = dateChooser.getDate();
        		Pasto pasto = (Pasto)comboBox.getSelectedItem();
        		Cliente cliente = (Cliente)comboBox_1.getSelectedItem();
        		
        		HashMap<String, Float> tmp = (HashMap<String, Float>) mappa.clone();
        		switch(pasto) {
	        		case COLAZIONE:
	        			//GUI.hotelAccesso.getConsumiColazione().get(data).removeAll(mappa);
	        			for(var x : mappa.keySet()) {
	        				System.out.println(GUI.hotelAccesso.getConsumiColazione().get(data));
	        				GUI.hotelAccesso.getConsumiColazione().get(data).remove(x);
	        				System.out.println("Rimosso dai consumi: "+ GUI.hotelAccesso.getConsumiColazione().get(data));
	        			}
	        		break;
	        		case PRANZO:
	        			switch (cliente) {
	        				case BAMBINO: 
	        					for(var x : tmp.keySet()) {
	        						System.out.println(GUI.hotelAccesso.getConsumiBimbiPranzo().get(data));
	        						GUI.hotelAccesso.getConsumiBimbiPranzo().get(data).remove(x);
	        						System.out.println("Rimosso dai consumi: "+ GUI.hotelAccesso.getConsumiBimbiPranzo().get(data));
	        					}
	        					break;
	        				case ADULTO:
	        					for(var x : tmp.keySet()) {
	        						GUI.hotelAccesso.getConsumiAdultiPranzo().get(data).remove(x);
	        						System.out.println("Rimosso dai consumi: "+ GUI.hotelAccesso.getConsumiAdultiPranzo().get(data));
	        					}
	        					break;
	        			}
	        		break;
	        		case CENA:
	        			switch (cliente) {
        				case BAMBINO: 
        					for(var x : tmp.keySet()) {
        						GUI.hotelAccesso.getConsumiBimbiCena().get(data).remove(x);
        						System.out.println("Rimosso dai consumi: "+ GUI.hotelAccesso.getConsumiBimbiCena().get(data));
        					}
        					break;
        				case ADULTO:
        					for(var x : tmp.keySet()) {
        						GUI.hotelAccesso.getConsumiAdultiCena().get(data).remove(x);
        						System.out.println("Rimosso dai consumi: "+ GUI.hotelAccesso.getConsumiAdultiCena().get(data).get(data));
        					}
        					break;
        			}
	        		default:
        		}
        	}
        });
        btnSalva_1.setPreferredSize(new Dimension(150, 50));
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
        
        
        ////-----------------------------
        
        Tipologia pesce = new Tipologia("Pesce");
        ProdFornito branzino150gpescedelivery = 
        		new ProdFornito("Branzino150gpescedelivery", 
        				new ProdConcreto("Branzino150g", 
        						new Prodotto("Branzino", pesce)));
        branzino150gpescedelivery.setValoreAssoluto((float) 0.15);
        
        ProdFornito branzino200gGeloService = 
                new ProdFornito("Branzino200gGeloService",
                        new ProdConcreto("Branzino200g", 
                                new Prodotto("Branzino", pesce)));
        branzino200gGeloService.setValoreAssoluto((float) 0.2);
        
        catenaAccesso.getInventario().add(pesce);
        catenaAccesso.getInventario().add(branzino150gpescedelivery);
        catenaAccesso.getInventario().add(branzino200gGeloService);
        
        
        //
        
        JComboBox comboBoxInventario = new JComboBox();
        
        comboBoxInventario.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		DefaultComboBoxModel dml = new DefaultComboBoxModel();
        		for (Typology t: (ArrayList<Typology>)catenaAccesso.getInventario()) {
        			if(t instanceof ProdFornito) {
        				dml.addElement(t.getID());
        			}
        			comboBoxInventario.setModel(dml);
        		}
        	}
        });
        
        
        
        
       
     
        GroupLayout gl_panel_30 = new GroupLayout(panel_30);
        gl_panel_30.setHorizontalGroup(
        	gl_panel_30.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel_30.createSequentialGroup()
        			.addGap(4)
        			.addComponent(comboBoxInventario, 0, 160, Short.MAX_VALUE)
        			.addGap(2))
        );
        gl_panel_30.setVerticalGroup(
        	gl_panel_30.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_30.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(comboBoxInventario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(16, Short.MAX_VALUE))
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
        textField_1.setHorizontalAlignment(SwingConstants.CENTER);
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
        
        JButton btnAggiungi_1 = new JButton("Aggiungi nel buffer");
        btnAggiungi_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Float q = Float.parseFloat(textField_1.getText());
        		String tip = (String) comboBoxInventario.getSelectedItem();
        		try {
					mappa.put(tip, q);
					System.out.println("Aggiunto al buffer, buffer ora = "+ mappa);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Controllare i valori inseriti", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        		//codice vecchio
        		/*
        		String id = textField.getText();
        		Float quantita = Float.valueOf(textField_1.getText());
        		
        		
        		//Se non è un istanza di float stamp errore, e fai reinserire 
        		try {
					mappa.put(id, quantita);
					System.out.println("Aggiunto al buffer, buffer ora = "+ mappa);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Controllare i valori inseriti", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        		*/
        		
        	}
        });
        btnAggiungi_1.setPreferredSize(new Dimension(117, 50));
        GroupLayout gl_panel_27 = new GroupLayout(panel_27);
        gl_panel_27.setHorizontalGroup(
        	gl_panel_27.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_panel_27.createSequentialGroup()
        			.addGap(15)
        			.addComponent(btnAggiungi_1, GroupLayout.PREFERRED_SIZE, 126, Short.MAX_VALUE)
        			.addGap(20))
        );
        gl_panel_27.setVerticalGroup(
        	gl_panel_27.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_panel_27.createSequentialGroup()
        			.addGap(21)
        			.addComponent(btnAggiungi_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(21, Short.MAX_VALUE))
        );
        panel_27.setLayout(gl_panel_27);
        
        panel_29 = new JPanel();
        panel_23.add(panel_29);
        
        JButton btnRimuovi = new JButton("Rimuovi dal buffer");
        btnRimuovi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String tip = (String) comboBoxInventario.getSelectedItem();
        		//Float quantita = Float.valueOf(textField.getText());
        		
        		
        		//Se non è un istanza di float stamp errore, e fai reinserire 
        		try {
					//mappa.remove(id, quantita);
					mappa.remove(tip);
					System.out.println("Rimosso dal buffer, buffer ora = "+mappa );
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Controllare i valori inseriti", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
        	}
        });
        btnRimuovi.setPreferredSize(new Dimension(117, 50));
        
        JButton btnResetBuffer = new JButton("Reset buffer");
        btnResetBuffer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		mappa.clear();
        	}
        });
        btnResetBuffer.setPreferredSize(new Dimension(117, 50));
        GroupLayout gl_panel_29 = new GroupLayout(panel_29);
        gl_panel_29.setHorizontalGroup(
        	gl_panel_29.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_29.createSequentialGroup()
        			.addGap(37)
        			.addGroup(gl_panel_29.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnRimuovi, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnResetBuffer, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(20, Short.MAX_VALUE))
        );
        gl_panel_29.setVerticalGroup(
        	gl_panel_29.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_panel_29.createSequentialGroup()
        			.addGap(18)
        			.addComponent(btnRimuovi, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnResetBuffer, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(17, Short.MAX_VALUE))
        );
        panel_29.setLayout(gl_panel_29);
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel_24.add(scrollPane_1);
        
        panel_25 = new JPanel();
        scrollPane_1.setViewportView(panel_25);
        
        JPanel panel_20_1 = new JPanel();
        panel_20_1.setForeground(Color.RED);
        panel_20_1.setBackground(Color.RED);
        GroupLayout gl_panel_25 = new GroupLayout(panel_25);
        gl_panel_25.setHorizontalGroup(
            gl_panel_25.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_25.createSequentialGroup()
                    .addGap(136)
                    .addComponent(panel_20_1, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .addGap(129))
        );
        gl_panel_25.setVerticalGroup(
            gl_panel_25.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_25.createSequentialGroup()
                    .addGap(17)
                    .addComponent(panel_20_1, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                    .addGap(247))
        );
        panel_25.setLayout(gl_panel_25);
    }
}
