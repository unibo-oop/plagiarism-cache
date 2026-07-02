package view;
import java.awt.*;
import java.io.*;
import javax.swing.*;

import controller.GameController;


public class Application {

	/**
	 * 
	 */
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2936225451510853838L;

	private JFrame mainFrame;
	private StartView startView;
	private GameView gameView;
	private StatisticsView statisticsView;
    private static final double WIDTH_PERC = 0.5;
	private static final double HEIGHT_PERC = 0.5;
	public Application() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame = new JFrame("PingPongGame");
		mainFrame.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar bar = new JMenuBar();
		bar.add(new JButton("Go back"));
		bar.setVisible(false);
		//bar.getMenu(0).add(new JMenuItem("Go back",new ImageIcon("/home/jacopo/Scaricati/go-back-left-arrow.png")));
		mainFrame.setJMenuBar(bar);
		JPanel mainPane = this.getMainPane();
		mainPane.setLayout(new CardLayout());
		startView = new StartView();
		startView.setName("STARTVIEW");
		mainPane.add(startView,startView.getName());
		gameView = new GameView();
		gameView.setName("GAMEVIEW");
		mainPane.add(gameView,gameView.getName());
		statisticsView = new StatisticsView();
		statisticsView.setName("STATISTICSVIEW");
		mainPane.add(statisticsView,statisticsView.getName());
		//mainPane.add(, "OPTIONSVIEW");
		new GameController(this);
	//	controllerGame.changePanel("STARTVIEW","STARTVIEW");
	

		
		
		/*JLabel label = new JLabel("The quick brown fox jumps over the lazy dog. 0123456789");
		JButton playButton =  new JButton("Start the match");
		JButton optionsButton = new JButton("Game Options");
		JButton creditsButton = new JButton("Credits");
		Font appFont = fontLoader("/home/jacopo/Scrivania/Campton-LightDEMO.ttf");
		if(appFont!=null) {
			label.setFont(appFont);
			playButton.setFont(appFont);
			optionsButton.setFont(appFont);
			creditsButton.setFont(appFont);
		}
		mainPane.add(label, BorderLayout.NORTH);
		mainPane.add(playButton,BorderLayout.WEST);
		mainPane.add(optionsButton,BorderLayout.EAST);
		mainPane.add(creditsButton,BorderLayout.SOUTH);
		//mainFrame.getContentPane().setBackground(new Color(105,105,105));*/
		//mainFrame.add(mainPane);
	
		mainFrame.setVisible(true);
	}
	public static void main(String[] args) {
		new Application();
		
	}
	 public JPanel getMainPane() {
		 return (JPanel) mainFrame.getContentPane();
	 }
	 public JMenuBar getMenuBar() {
		 return this.mainFrame.getJMenuBar();
	 }
    public StartView getStartView() {
        return startView;
    }
    public GameView getGameView() {
        return gameView;
    }
    public StatisticsView getStatisticsView() {
        return statisticsView;
    }
	 

}
