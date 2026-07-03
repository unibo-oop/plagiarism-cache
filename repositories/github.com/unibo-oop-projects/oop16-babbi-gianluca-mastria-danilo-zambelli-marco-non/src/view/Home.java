package view;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import controller.FileSerializableImpl;


public class Home {
	
	private FileSerializableImpl serC = new FileSerializableImpl();
	
	public Home() { 
		
			JFrame frame = new JFrame("Non t'arrabbiare");
		
		    frame.setSize(800, 800);
		    frame.setLocationRelativeTo(null);
		    frame.getContentPane().setLayout(new BorderLayout(0, 0));
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    BackgroundHomeImpl back = new BackgroundHomeImpl();
		    back.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		 
		    JButton newGame = new JButton("");
			JButton loadGame = new JButton("");
			JButton info = new JButton("");
		    
		    Font f=new Font("Helvetica", Font.BOLD, 24);
		    
		    newGame.setLocation(80, 554);
		    newGame.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/newGame.png")));
		    newGame.setFont(f);
		    newGame.setRolloverEnabled(true);
		    newGame.setFocusPainted(false);
		    newGame.setBorderPainted(false);
		    newGame.setContentAreaFilled(false);
		    newGame.setSize(320, 64);
		    newGame.addActionListener(l-> new Modality(frame));
		    
		    loadGame.setLocation(289, 73);
		    loadGame.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/loadGame.png")));
		    loadGame.setRolloverEnabled(true);
		    loadGame.setFocusPainted(false);
		    loadGame.setBorderPainted(false);
		    loadGame.setContentAreaFilled(false);
		    loadGame.setLocation(430, 554);
		    loadGame.setFont(f);
		    loadGame.setSize(320, 64);
		    loadGame.addActionListener(l -> {
		    	int nPlayers = serC.loadNPlayers();
				if(nPlayers == 4){
					new Start4Impl("", "", "", "", true);
					frame.dispose();
				}
				else if(nPlayers == 6){
					new Start6Impl("", "", "", "", "", "", true);
					frame.dispose();
				}
		    });
		   
		    info.setLocation(700, 680);
		    info.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/info.png")));
		    info.setFont(f);
		    info.setSize(80, 80);
		    info.setRolloverEnabled(true);
		    info.setFocusPainted(false);
		    info.setBorderPainted(false);
		    info.setContentAreaFilled(false);
		    info.addActionListener(l -> {
		    	new Info(frame);
		    	frame.setEnabled(false);
		    });
		    
		    JButton turnOff = new JButton("");
		    turnOff.setLocation(700, 20);
		    turnOff.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/turnOff.png")));
		    turnOff.setFont(f);
		    turnOff.setSize(80, 80);
		    turnOff.setRolloverEnabled(true);
		    turnOff.setFocusPainted(false);
		    turnOff.setBorderPainted(false);
		    turnOff.setContentAreaFilled(false);
		    turnOff.addActionListener(l -> System.exit(0));
		    
		    back.setLayout(null);
		    back.add(newGame, new BorderLayout(400, 400));
		    back.add(loadGame, BorderLayout.SOUTH );
		    back.add(turnOff, BorderLayout.SOUTH);
		    back.add(info, BorderLayout.SOUTH );
		    frame.getContentPane().add(back, BorderLayout.CENTER);
		    frame.setResizable(false);
		    frame.setVisible(true);
	}
}

