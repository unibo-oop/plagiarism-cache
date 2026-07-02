package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JPasswordField;
import java.awt.Label;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class GUI_Login extends GUI{

    private JPanel contentPane;
    private JPasswordField passwordField;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        frame = new GUI_Login();
        frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public GUI_Login() {
    	
    	try {
			//UtilityReadWriteCatena.setCatena(GUI.catenaAccesso);
			GUI.catenaAccesso = UtilityReadWriteCatena.getCatena();
			//GUI.catenaAccesso.aggiungiUnAlbergo(new Hotel("prova"));
		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
    	
    	
    	System.out.println("Login:"+GUI.catenaAccesso);
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 706, 499);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(176, 224, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        passwordField = new JPasswordField();       
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        textField = new JTextField();
        
        //Chiamata al metodo di Ivan per creare un file log per ogni utente
        if(!GUI.control) {
        	super.logATutti(); 
        	GUI.control=true;
        }
        JPanel panel = new JPanel();
        panel.setBackground(UIManager.getColor("Button.select"));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(83)
                    .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(83))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                    .addGap(14))
        );
        panel.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(224, 255, 255));
        panel.add(panel_1, BorderLayout.NORTH);
        
        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
        panel_1.add(lblNewLabel);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(224, 255, 255));
        panel.add(panel_2, BorderLayout.SOUTH);
        
        JButton btnNewButton = new JButton("Accedi");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	//Meglio creare un oggetto gui dentro questa classe?
	
            	if(controlloCredenziali(textField.getText(), passwordField.getPassword())) {
	               
            		
            		save(getX(), getY(), getWidth(), getHeight());
	                
	                frame = new GUI_SelezioneHotel();
	                frame.setBounds(getX(), getY(), getWidth(), getHeight());
	               
	                frame.setVisible(true);
	                setVisible(false);
	                
            	}
                
            }
        });
        
        JButton btnRegistrati = new JButton("Registrati");
        btnRegistrati.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                //save(getX(), getY(), getWidth(), getHeight());
                
                frame = new GUI_Registrazione();              
                
                //Qui lancia un JOPtionPane che dice ok  ti sei registrato
                
                frame.setBounds(getX(), getY(), getWidth(), getHeight());
               
                frame.setVisible(true);
                setVisible(false);
            }
        });
        btnRegistrati.setPreferredSize(new Dimension(117, 50));
        panel_2.add(btnRegistrati);
        btnNewButton.setPreferredSize(new Dimension(117, 50));
        panel_2.add(btnNewButton);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(245, 255, 250));
        panel.add(panel_3, BorderLayout.CENTER);
        
        JPanel panel_2_1 = new JPanel();
        panel_2_1.setOpaque(false);
        
        
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setColumns(10);
        
        JLabel lblDispensa_1 = new JLabel("Password");
        lblDispensa_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblDispensa_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        
        JLabel lblNewLabel_1 = new JLabel("Username");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        GroupLayout gl_panel_2_1 = new GroupLayout(panel_2_1);
        gl_panel_2_1.setHorizontalGroup(
            gl_panel_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel_2_1.createSequentialGroup()
                    .addGap(100)
                    .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addGap(93))
                .addGroup(gl_panel_2_1.createSequentialGroup()
                    .addGap(99)
                    .addComponent(textField, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addGap(99))
                .addGroup(gl_panel_2_1.createSequentialGroup()
                    .addGap(73)
                    .addGroup(gl_panel_2_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_2_1.createSequentialGroup()
                            .addComponent(lblDispensa_1, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addGap(9))
                        .addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                    .addGap(68))
        );
        gl_panel_2_1.setVerticalGroup(
            gl_panel_2_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2_1.createSequentialGroup()
                    .addGap(53)
                    .addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(42)
                    .addComponent(lblDispensa_1, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(58))
        );
        panel_2_1.setLayout(gl_panel_2_1);
        GroupLayout gl_panel_3 = new GroupLayout(panel_3);
        gl_panel_3.setHorizontalGroup(
            gl_panel_3.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, gl_panel_3.createSequentialGroup()
                    .addGap(69)
                    .addComponent(panel_2_1, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                    .addGap(74))
        );
        gl_panel_3.setVerticalGroup(
            gl_panel_3.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_3.createSequentialGroup()
                    .addGap(28)
                    .addComponent(panel_2_1, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addGap(28))
        );
        panel_3.setLayout(gl_panel_3);
        contentPane.setLayout(gl_contentPane);
    }
}
