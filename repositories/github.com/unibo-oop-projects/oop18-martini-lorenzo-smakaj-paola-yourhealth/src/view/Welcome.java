package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Welcome extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8948126197209198029L;
	private JButton addPerformance, removePerformance, addPatient, addDoctor, removePatient, removeDoctor, addMach, removeMach, addAmb, removeAmb;
    private static JButton performances, patients, doctors, mach, amb;
    private static JPanel mainpanel, panel1, panel2, panel3, panel4, panel5, panel6;
    private GUI factory = new GUIFactory();
	private Controller contr;
	private JFrame frame = new JFrame();
    
	public Welcome() {
		try {
			contr = new Controller(this);
		} catch(Exception e) {
			e.printStackTrace();
		}
		

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setVisible(true);
        frame.setTitle("Bentornato, Amministratore - YourHealth");
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        
        mainpanel = new JPanel (new GridLayout(2,3));
        frame.add(mainpanel, BorderLayout.CENTER);

        panel1 = factory.createGridPanel();
        
        performances = factory.createButton("Lista prestazioni");
        performances.setBackground(Color.white);
        panel1.add(performances);
		performances.addActionListener(contr);
        addPerformance = factory.createButton("Aggiungi prestazione");
        addPerformance.setBackground(Color.white);
        panel1.add(addPerformance);
		addPerformance.addActionListener(contr); 			
        removePerformance = factory.createButton("Rimuovi prestazione");
        removePerformance.setBackground(Color.white);
        panel1.add(removePerformance);
		removePerformance.addActionListener(contr);
        
		mainpanel.add(panel1, BorderLayout.WEST);
        
		panel2 = factory.createGridPanel();
        
        patients = factory.createButton("Lista pazienti");
        patients.setBackground(Color.lightGray);
        panel2.add(patients);
		patients.addActionListener(contr);
        addPatient = factory.createButton("Aggiungi paziente");
        addPatient.setBackground(Color.lightGray);
        panel2.add(addPatient);
		addPatient.addActionListener(contr);
        removePatient = factory.createButton("Rimuovi paziente");
        removePatient.setBackground(Color.lightGray);
        panel2.add(removePatient);
		removePatient.addActionListener(contr);
		
		mainpanel.add(panel2);

		panel3 = factory.createGridPanel();
        
        doctors = factory.createButton("Lista dottori");
        doctors.setBackground(Color.white);
        panel3.add(doctors);
		doctors.addActionListener(contr);
        addDoctor = factory.createButton("Aggiungi dottore");
        addDoctor.setBackground(Color.white);
        panel3.add(addDoctor);
		addDoctor.addActionListener(contr);	
        removeDoctor = factory.createButton("Rimuovi dottore");
        removeDoctor.setBackground(Color.white);
        panel3.add(removeDoctor);
		removeDoctor.addActionListener(contr);	
		
		mainpanel.add(panel3);
        
		panel4 = factory.createGridPanel();
        
        mach = factory.createButton("Lista macchinari");
        mach.setBackground(Color.lightGray);
        panel4.add(mach);
		mach.addActionListener(contr);
		addMach = factory.createButton("Aggiungi macchinario");
		addMach.setBackground(Color.lightGray);
        panel4.add(addMach);
		addMach.addActionListener(contr);
        removeMach = factory.createButton("Rimuovi macchinario");
        removeMach.setBackground(Color.lightGray);
        panel4.add(removeMach);
		removeMach.addActionListener(contr);
        frame.setVisible(true);
        
        mainpanel.add(panel4);
       
        panel5 = factory.createGridPanel();
        
        amb = factory.createButton("Lista ambulatori");
        amb.setBackground(Color.white);
        panel5.add(amb);
		amb.addActionListener(contr);
		addAmb = factory.createButton("Aggiungi ambulatorio");
		addAmb.setBackground(Color.white);
        panel5.add(addAmb);
		addAmb.addActionListener(contr);
        removeAmb = factory.createButton("Rimuovi ambulatorio");
        removeAmb.setBackground(Color.white);
        panel5.add(removeAmb);
		removeAmb.addActionListener(contr);
        frame.setVisible(true);
        
        mainpanel.add(panel5);
        
        panel6 = new JPanel();
        
        JButton logout = new JButton("Esci");
        logout.setFont(new Font("Calibri", Font.PLAIN,18));
        logout.setBackground(Color.darkGray);
        logout.setForeground(Color.white);
        panel6.add(logout);
        logout.addActionListener(this);
        frame.setVisible(true);
        
        mainpanel.add(panel6);
        }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		new LoginForm();
	    frame.setVisible(false);
		
	}
}