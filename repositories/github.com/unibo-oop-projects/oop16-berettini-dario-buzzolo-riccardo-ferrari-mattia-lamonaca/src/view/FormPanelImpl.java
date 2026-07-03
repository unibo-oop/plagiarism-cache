package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.FormController;
import model.Belt;

public class FormPanelImpl extends JFrame implements FormPanel{
	
	private static final long serialVersionUID = -5054917338852256007L;
	FormController formControllerImpl;
	private JPanel mainPanel = new JPanel();
	private final JButton start = new JButton("Start");
	private final JButton storico = new JButton("Storico");
	private final JTextField name = new JTextField(20);
	private final JTextField surname = new JTextField(20);
	private final JLabel titlePanel = new JLabel("Inserimento Studente");
	private final JLabel displayName = new JLabel("Nome :");
	private final JLabel displaySurname = new JLabel("Cognome :");
	private final JLabel displayDan= new JLabel("Cintura :");
	private final JLabel displayForm= new JLabel("Forma :");
	String[] forms =  new String[]{"Taegeuk Il Jang – 1° Forma","Taegeuk I Jang – 2° Forma","Taegeuk Sam Jang – 3° Forma"
			,"Taegeuk Sa Jang – 4° Forma","Taegeuk Oh Jang – 5° Forma","Taegeuk Yuk Jang – 6° Forma",
			"Taegeuk Chil Jang – 7° Forma","Taegeuk Pal Jang – 8° Forma"};
	
	private final JComboBox<String> formList = new JComboBox<>(forms);
	private final JComboBox<Belt> beltList = new JComboBox<>(Belt.values());	
	public FormPanelImpl(){
		
		super("Forma");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setBounds(100, 100, 500, 600);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(mainPanel);
		mainPanel.setLayout(null);
	
		titlePanel.setForeground(new Color(0, 0, 0));
		titlePanel.setBounds(150, 30, 300, 40);
		titlePanel.setFont(new Font("Arial", Font.BOLD, 20));
		titlePanel.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(titlePanel);
		
		displayName.setForeground(new Color(0, 0, 0));
		displayName.setBounds(40, 100, 100, 40);
		displayName.setFont(new Font("Arial", Font.BOLD, 15));
		displayName.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(displayName);
		
		name.setForeground(new Color(0, 0, 0));
		name.setBounds(150, 100, 300, 40);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(name);
		
		displaySurname.setForeground(new Color(0, 0, 0));
		displaySurname.setBounds(40, 180, 100, 40);
		displaySurname.setFont(new Font("Arial", Font.BOLD, 15));
		displaySurname.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(displaySurname);
		
		surname.setForeground(new Color(0, 0, 0));
		surname.setBounds(150, 180, 300, 40);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(surname);
		
		displayForm.setForeground(new Color(0, 0, 0));
		displayForm.setBounds(40, 260, 100, 40);
		displayForm.setFont(new Font("Arial", Font.BOLD, 15));
		displayForm.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(displayForm);
		
		formList.setForeground(new Color(0, 0, 0));
		formList.setBounds(150, 260, 300, 40);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(formList);
		
		displayDan.setForeground(new Color(0, 0, 0));
		displayDan.setBounds(40, 340, 100, 40);
		displayDan.setFont(new Font("Arial", Font.BOLD, 15));
		displayDan.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(displayDan);
		
		beltList.setForeground(new Color(0, 0, 0));
		beltList.setBounds(150, 340, 300, 40);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(beltList);
		
		storico.setForeground(new Color(0, 0, 0));
		storico.setFont(new Font("Arial", Font.BOLD, 13));
		storico.setBounds(100, 440 , 120, 50);
		mainPanel.add(storico);
		
		start.setForeground(new Color(0, 0, 0));
		start.setFont(new Font("Arial", Font.BOLD, 13));
		start.setBounds(280, 440 , 120, 50);
		mainPanel.add(start);
		
		start.addActionListener(e -> {
			
			if(name.getText().isEmpty() 
					|| surname.getText().isEmpty()){
			    JFrame frame = new JFrame("Errore");			   
			    JOptionPane.showMessageDialog(frame,
			      "Nome o Cognome non inseriti.");
			}else if(!name.getText().matches("[A-Za-z]+")
					|| !surname.getText().matches("[A-Za-z]+")){
				JFrame frame = new JFrame("Errore");			   
			    JOptionPane.showMessageDialog(frame,
			      "Inserire solo lettere.");
			}else{
				formControllerImpl.startForm(name.getText(), surname.getText(), (Belt) beltList.getSelectedItem(), formList.getSelectedItem().toString());
			}
		});
		
		storico.addActionListener(e->{
			
			formControllerImpl.stampaAtleti();			
		});
		this.setLocationRelativeTo(null);
	}
	
	public void addObserver(FormController controller) {
		
		this.formControllerImpl = controller;	
	}
}
