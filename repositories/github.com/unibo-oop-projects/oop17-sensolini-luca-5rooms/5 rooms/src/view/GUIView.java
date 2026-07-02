package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.Controller;

public class GUIView extends JFrame implements GuiViewInterface{
	


	private static final long serialVersionUID = 1L;
	private static final String BGPATH = "img/base.png";

	private final JFrame frame;
	private final JButton startButton = new MyButton ("Start");
	private final JButton scoreButton = new MyButton ("Score");
	private final JButton quitButton = new MyButton ("Quit");
	
	public GUIView(){
		frame = new JFrame();
		this.go();
	}
	
	public void go() {
		
		frame.setTitle("5 Rooms");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight();
		int width = (int) screenSize.getWidth();
		
		frame.setSize(height, width/2);
		frame.setLocationRelativeTo(null);	
				
		BufferedImage img = null;
		try {
			URL url = GUIView.class.getResource("/base.png");
			img = ImageIO.read(url);
		} catch (IOException e) {
		}
		
		final JPanel menuPanel = new BackgroundPanel(img);
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
		frame.getContentPane().add(menuPanel);
		
		Dimension minSize = new Dimension(5, (int) (height*0.5));
		Dimension prefSize = new Dimension(5, (int) (height*0.5));
		Dimension maxSize = new Dimension(Short.MAX_VALUE, (int) (height*0.5));
		menuPanel.add(new Box.Filler(minSize, prefSize, maxSize));
		menuPanel.add(startButton);
		menuPanel.add(scoreButton);
		menuPanel.add(quitButton);
		
		startButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) { 
				Controller.getInstance().playNext();
			}
		});
		
		scoreButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().FailState();
				Controller.getInstance().SuccessState();
			}
	});
		
		quitButton.addActionListener(new ActionListener(){
					
					public void actionPerformed(ActionEvent e) { 
						System.exit(0);
					}
			});
		
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		startButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		scoreButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		quitButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		startButton.setBackground(Color.RED);
		scoreButton.setBackground(Color.RED);
		quitButton.setBackground(Color.RED);
		
		startButton.setForeground(Color.WHITE);
		scoreButton.setForeground(Color.WHITE);
		quitButton.setForeground(Color.WHITE);
		
		startButton.setFont(new Font("Arial", Font.BOLD, 16));
		scoreButton.setFont(new Font("Arial", Font.BOLD, 16));
		quitButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		scoreButton.setEnabled(false);
		
		JPanel subPanel = new JPanel(new GridBagLayout());
		//subPanel.add(menuPanel, new GridBagConstraints());
		frame.getContentPane().add(menuPanel, BorderLayout.CENTER);
		subPanel.setOpaque(true);

		frame.setVisible(true);
		 
	}
	
	private class MyButton extends JButton {
		
		private static final long serialVersionUID = 1L;
		
		public MyButton(String s) {
			super(s);
		}

		public Dimension getPreferredSize(){
			return(new Dimension(160,40));
		}

		public Dimension getMinimumSize(){
			return(new Dimension(160,40));
		}

		public Dimension getMaximumSize(){
			return(new Dimension(160,40));
		}

	}
	
	public void show(){
		frame.setVisible(true);;
	}
	
	public void hide(){
		frame.setVisible(false);;
	}
	
}

