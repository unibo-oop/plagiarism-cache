package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.ExamController;
import java.awt.Color;
import java.awt.Font;

public class InsertMasterImpl extends JFrame implements InsertMaster{

	ExamController examController;
	private static final long serialVersionUID = 2244202524311031240L;
	private final JPanel mainPanel = new JPanel();
	private final JButton insertMaster = new JButton("Inserisci");
	private final JButton undo = new JButton("Annulla");
	private final JTextField name = new JTextField(20);
	private final JTextField surname = new JTextField(20);
	private final JLabel titlePanel = new JLabel("Inserimento Maestro");
	private final JLabel displayName = new JLabel("Nome :");
	private final JLabel displaySurname = new JLabel("Cognome :");
	private final JLabel displayDan= new JLabel("Dan :");
	Integer[] dan = new Integer[] {1,2,3,4,5,6,7,8,9};

	private final JComboBox<Integer> danList = new JComboBox<>(dan);
	
	public InsertMasterImpl(){
		
		//super();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setBounds(100, 100, 500, 600);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(mainPanel);
		mainPanel.setLayout(null);
	
		titlePanel.setForeground(new Color(0, 0, 0));
		titlePanel.setBounds(150, 50, 300, 40);
		titlePanel.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(titlePanel);
		
		displayName.setForeground(new Color(0, 0, 0));
		displayName.setBounds(40, 150, 100, 40);
		displayName.setFont(new Font("Arial", Font.BOLD, 15));
		displayName.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(displayName);
		
		name.setForeground(new Color(0, 0, 0));
		name.setBounds(150, 150, 300, 40);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(name);
		
		displaySurname.setForeground(new Color(0, 0, 0));
		displaySurname.setBounds(40, 230, 100, 40);
		displaySurname.setFont(new Font("Arial", Font.BOLD, 15));
		displaySurname.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(displaySurname);
		
		surname.setForeground(new Color(0, 0, 0));
		surname.setBounds(150, 230, 300, 40);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(surname);
		
		displayDan.setForeground(new Color(0, 0, 0));
		displayDan.setBounds(40, 310, 100, 40);
		displayDan.setFont(new Font("Arial", Font.BOLD, 15));
		displayDan.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(displayDan);
		
		danList.setForeground(new Color(0, 0, 0));
		danList.setBounds(150, 310, 300, 40);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(danList);
		
		undo.setForeground(new Color(0, 0, 0));
		undo.setFont(new Font("Arial", Font.BOLD, 13));
		undo.setBounds(100, 440 , 120, 50);
		mainPanel.add(undo);
		
		insertMaster.setForeground(new Color(0, 0, 0));
		insertMaster.setFont(new Font("Arial", Font.BOLD, 13));
		insertMaster.setBounds(280, 440 , 120, 50);
		mainPanel.add(insertMaster);
		
		insertMaster.addActionListener(e -> {
			
			if(name.getText().isEmpty()
					|| surname.getText().isEmpty()){
				JFrame frame = new JFrame("Errore");			   
			    JOptionPane.showMessageDialog(frame,
			      "Dati non inseriti!");				
			}else if(!name.getText().matches("[A-Za-z]+")
					|| !surname.getText().matches("[A-Za-z]+")){
				JFrame frame = new JFrame("Errore");			   
			    JOptionPane.showMessageDialog(frame,
			      "Valori non consentiti!");	
			}else{
				examController.insertMaster(
							name.getText(),
							surname.getText(),
							(Integer) danList.getSelectedItem());
				 examController.updateMaster();
				name.setText("");
				surname.setText("");
			}
		});	
		
		undo.addActionListener(e->{
			
			dispose();
			
		});
		this.setLocationRelativeTo(null);
	}
	
	
	public void addObserver(ExamController controller) {
		
		this.examController = controller;
	}
}
