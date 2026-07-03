package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Modality extends JFrame{

	private static final long serialVersionUID = 1L;

	public Modality(JFrame frame)  {
		
		JFrame modality = new JFrame();
    	modality.setSize(600, 300);
    	modality.getContentPane().setLayout(null);
    	modality.setLocationRelativeTo(null);
    	modality.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    	modality.addWindowListener(new WindowAdapter() {
	    	public void windowClosing (WindowEvent e)
	    	{	
	    		modality.dispose();
	    		frame.setEnabled(true);
	    		frame.requestFocus();
	    	}
	    });
    	frame.setEnabled(false);
    	
    	JButton players4 = new JButton("4 Players");
    	players4.setLocation(66, 154);
    	players4.setForeground(Color.BLACK);
	    players4.setFont(new Font("Talking to the Moon", Font.BOLD, 28));
	    players4.setRolloverEnabled(true);
	    players4.setFocusPainted(false);
	    players4.setBorderPainted(false);
	    players4.setContentAreaFilled(false);
	    players4.setSize(208, 64);
	    players4.addActionListener(s -> {
	    	new RegistrationPlayersImpl(4, frame, modality);
	    });
    	
	    JButton players6 = new JButton("6 Players");
    	players6.setLocation(318, 154);
    	players6.setForeground(Color.BLACK);
	    players6.setFont(new Font("Talking to the Moon", Font.BOLD, 28));
	    players6.setRolloverEnabled(true);
	    players6.setFocusPainted(false);
	    players6.setBorderPainted(false);
	    players6.setContentAreaFilled(false);
	    players6.setSize(224, 64);
	    players6.addActionListener(l -> {
	    	new RegistrationPlayersImpl(6, frame, modality);
	    });
	    
	    JButton turnBack = new JButton("");
	    
	    turnBack.setLocation(400, 200);
	    turnBack.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/turn_back.png")));
	    turnBack.setRolloverEnabled(true);
	    turnBack.setFocusPainted(false);
	    turnBack.setBorderPainted(false);
	    turnBack.setContentAreaFilled(false);
	    turnBack.setSize(320, 64);
	    turnBack.addActionListener(l-> {
	    	modality.dispose();
	    	frame.setEnabled(true);
	    	frame.requestFocus();
	    });
	    
    	modality.getContentPane().add(players4, BorderLayout.WEST);
    	modality.getContentPane().add(players6);
    	modality.getContentPane().add(turnBack);
    	
    	JLabel label = new JLabel("Choose the game mode:");
    	label.setFont(new Font("Talking to the Moon", Font.BOLD, 32));
    	label.setBounds(140, 13, 400, 64);
    	
    	modality.getContentPane().add(label);
    	modality.setResizable(false);
    	modality.setVisible(true);
	}
}
