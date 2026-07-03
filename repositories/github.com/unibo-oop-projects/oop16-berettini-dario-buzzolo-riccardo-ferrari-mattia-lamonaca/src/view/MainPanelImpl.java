package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.MainController;

public class MainPanelImpl extends JFrame implements MainPanel{

	private static final long serialVersionUID = 8607610145685675869L;
	private JPanel mainPanel = new JPanel();
	private JPanel logInPanel = new JPanel(); 
	private final JLabel titleLogIn = new JLabel("Benvenuto nella piattaforma Taekwondo");
	private final JLabel lLogIn = new JLabel("Inserire dati");
	private final JLabel lUserName = new JLabel("Nome utente:");
	private final JTextField tfUserName = new JTextField(20);
	private final JLabel lPassword = new JLabel("Password:");
	private final JPasswordField tfPassword = new JPasswordField(20);
	private final JButton submit = new JButton("Login");
	MainController mainController;
	private final JButton fight = new JButton("Combattimento");
	private final JButton form = new JButton("Forma");
	private final JButton exam = new JButton("Esame");
    URL url = MainPanelImpl.class.getResource("/image.jpg");
	private final JLabel background=new JLabel(new ImageIcon(url));
	Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	Double width = screensize.getWidth();
	Double height = screensize.getHeight();

	public MainPanelImpl(){
		
		super("Taekwondo Platform");
	
	 // LOGIN
		this.setVisible(true);
		this.setResizable(false);
		this.setBounds(100, 100, 800, 480);
		logInPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(logInPanel);
		logInPanel.setLayout(null);
		
		titleLogIn.setForeground(new Color(0, 0, 0));
		titleLogIn.setBounds(100, 30, 600, 40);
		titleLogIn.setFont(new Font("Arial", Font.BOLD, 30));
		titleLogIn.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		logInPanel.add(titleLogIn);
		
		lLogIn.setForeground(new Color(0, 0, 0));
		lLogIn.setBounds(340, 80, 100, 40);
		lLogIn.setFont(new Font("Arial", Font.BOLD, 16));
		lLogIn.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		logInPanel.add(lLogIn);
		
		lUserName.setForeground(new Color(0, 0, 0));
		lUserName.setBounds(100, 150, 100, 40);
		lUserName.setFont(new Font("Arial", Font.BOLD, 15));
		// name.setBackground(new Color(255, 255, 255));
		logInPanel.add(lUserName);
		
		tfUserName.setForeground(new Color(0, 0, 0));
		tfUserName.setBounds(250, 150, 450, 40);
		tfUserName.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		logInPanel.add(tfUserName);
		
		lPassword.setForeground(new Color(0, 0, 0));
		lPassword.setBounds(100, 200, 100, 40);
		lPassword.setFont(new Font("Arial", Font.BOLD, 15));
		// name.setBackground(new Color(255, 255, 255));
		logInPanel.add(lPassword);
		
		tfPassword.setForeground(new Color(0, 0, 0));
		tfPassword.setBounds(250, 200, 450, 40);
		tfPassword.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		logInPanel.add(tfPassword);
		
		submit.setForeground(new Color(0, 0, 0));
		submit.setFont(new Font("Arial", Font.BOLD, 13));
		submit.setBounds(350, 350 , 120, 50);
		logInPanel.add(submit);
		
	 // MAIN
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	    this.setResizable(false);
	    this.setBounds(100, 100, 800, 480);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(null);
		
		mainPanel.setBackground(new Color(250, 250, 250));
		
		background.setBounds(200, 0, 420, 150);
		background.setOpaque(true);
		// name.setBackground(new Color(255, 255, 255));
		mainPanel.add(background);
		
		fight.setForeground(new Color(0, 0, 0));
		fight.setOpaque(true);
		fight.setFont(new Font("Arial", Font.BOLD, 25));
		fight.setBounds(0, 150 , 799, 100);
		mainPanel.add(fight);
		
		form.setForeground(new Color(0, 0, 0));
		form.setOpaque(true);
		form.setFont(new Font("Arial", Font.BOLD, 25));
		form.setBounds(0, 250 , 799, 100);
		mainPanel.add(form);
		
		exam.setForeground(new Color(0, 0, 0));
		exam.setOpaque(true);
		exam.setFont(new Font("Arial", Font.BOLD, 25));
		exam.setBounds(0, 350 , 799, 100);
		mainPanel.add(exam);
		
		fight.addActionListener(e -> {
			
			mainController.fightView();
			this.toBack();
		});
		
		form.addActionListener(e -> {

			mainController.formView();
			this.toBack();
		});
		
		exam.addActionListener(e -> {
			
			mainController.examView();
			this.toBack();
		});
		
		//ActionListener dell'OK per il login
		submit.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(mainController.logIn(tfUserName.getText(),
						tfPassword.getText()) == true) {
					
					setContentPane(mainPanel);
					logInPanel.setVisible(false);
					validate();
				} else {
					
					JOptionPane.showMessageDialog(MainPanelImpl.this, "Dati inseriti non corretti.");
					//tfUserName.setText("");
					tfPassword.setText("");
				}
			}
		});
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			  @Override
			  public void windowClosing(WindowEvent we)
			  { 
			    String ObjButtons[] = {"Si","No"};
			    int PromptResult = JOptionPane.showOptionDialog(null, 
			        "Sei sicuro di vole uscire?", "Attenzione", 
			        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
			        ObjButtons,ObjButtons[1]);
			    if(PromptResult==0)
			    {
			      System.exit(0);          
			    }
			  }
		});
		this.setLocationRelativeTo(null);	
	}
	
	public void addObserver(MainController controller) {

		this.mainController = controller;
	}
}
