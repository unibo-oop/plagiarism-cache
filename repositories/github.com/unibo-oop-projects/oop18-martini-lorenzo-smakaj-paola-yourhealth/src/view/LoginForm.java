package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class LoginForm extends JFrame{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 3401252203616577808L;
	/**
	 * 
	 */
	
	private static JButton admin, doc, patient;
	static JFrame frame;
    private static JPanel mainpanel, panel1, panel2;
    private GUI factory = new GUIFactory();
	private ControllerLogin contr;


    public LoginForm() {
		try {
			contr = new ControllerLogin(this);
		} catch(Exception e) {
			e.printStackTrace();
		}
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setTitle("YourHealth");
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        mainpanel = new JPanel (new GridLayout(2,1));
        frame.add(mainpanel, BorderLayout.CENTER);
        
        panel1 = new JPanel (new GridLayout());
        
        admin = factory.createButton("Amministratore");
        admin.setBackground(Color.white);
        panel1.add(admin);
		admin.addActionListener(contr);
		doc = factory.createButton("Dottore");
		doc.setBackground(Color.lightGray);
        panel1.add(doc);
        doc.addActionListener(contr);
        patient = factory.createButton("Paziente");
		patient.setBackground(Color.lightGray);
        panel1.add(patient);
        patient.addActionListener(contr);
		
		mainpanel.add(panel1);
		frame.setVisible(true);
	
		panel2= new JPanel();
        
		setLayout(new BorderLayout());
		JLabel background = new JLabel(new ImageIcon("img/bg.png"));
		add(background);
		background.setLayout(new FlowLayout());
		background.setVisible(true);
		panel2.add(background);
		
        frame.setVisible(true);
        
        mainpanel.add(panel2);
        }
}