package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.FightController;

public class FightPanelImpl extends JFrame implements FightPanel{
	
	private static final long serialVersionUID = -4624979094418664594L;
	FightController fightController;
	private final JButton start = new JButton("Start");
	private JButton stampaStorico = new JButton("Storico");
	private JPanel mainPanel = new JPanel();
	private JLabel titlePanel = new JLabel("Inserimento Atleti");
	private JLabel a1 = new JLabel("Atleta 1:");
	private JLabel a2 = new JLabel("Atleta 2:");
	private JTextField cognome1 = new JTextField("");
	private JTextField cognome2 = new JTextField("");
	
	public FightPanelImpl(){
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setBounds(100, 100, 500, 450);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(mainPanel);
		mainPanel.setLayout(null);
				
		titlePanel.setForeground(new Color(0, 0, 0));
		titlePanel.setBounds(150, 30, 300, 40);
		titlePanel.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(titlePanel);
		
		a1.setForeground(new Color(0, 0, 0));
		a1.setBounds(40, 100, 100, 40);
		a1.setFont(new Font("Arial", Font.BOLD, 15));
		a1.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(a1);
		
		cognome1.setForeground(new Color(0, 0, 0));
		cognome1.setBounds(150, 100, 300, 40);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(cognome1);
		
		a2.setForeground(new Color(0, 0, 0));
		a2.setBounds(40, 180, 100, 40);
		a2.setFont(new Font("Arial", Font.BOLD, 15));
		a2.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(a2);
		
		cognome2.setForeground(new Color(0, 0, 0));
		cognome2.setBounds(150, 180, 300, 40);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(cognome2);
		
		stampaStorico.setForeground(new Color(0, 0, 0));
		stampaStorico.setFont(new Font("Arial", Font.BOLD, 13));
		stampaStorico.setBounds(100, 300 , 120, 50);
		mainPanel.add(stampaStorico);
		
		start.setForeground(new Color(0, 0, 0));
		start.setFont(new Font("Arial", Font.BOLD, 13));
		start.setBounds(280, 300 , 120, 50);
		mainPanel.add(start);
		
		this.setLocationRelativeTo(null);
		start.addActionListener(e -> {
			
			if(cognome1.getText().isEmpty() 
					|| cognome2.getText().isEmpty()){
			    JFrame frame = new JFrame("Errore");			   
			    JOptionPane.showMessageDialog(frame,
			      "Atleta/i non inserito/i.");
			}else if(!cognome1.getText().matches("[A-Za-z]+")
				|| !cognome2.getText().matches("[A-Za-z]+")){
				JFrame frame = new JFrame("Errore");			   
			    JOptionPane.showMessageDialog(frame,
			      "Inserire solo lettere.");
			}else{
				fightController.startFight(cognome1.getText(), cognome2.getText());
				setFocusable(true);
			}
		});
		
		stampaStorico.addActionListener(e->{
			
			fightController.stampaAtleti();
		});
				
	}
	
	@Override
	public void addObserver(FightController controller) {
		// TODO Auto-generated method stub
		this.fightController = controller;
		
	}
}
