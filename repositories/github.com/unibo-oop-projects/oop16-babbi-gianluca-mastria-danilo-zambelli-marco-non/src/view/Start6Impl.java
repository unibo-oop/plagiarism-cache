package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controller.ApplicationImpl;
import controller.FileSerializableImpl;
import controller.GameActionImpl;

/**this class create the page that shows the gameboard for 6 players
 *
 */
public class Start6Impl extends JFrame implements Serializable, Utility {

	private static final long serialVersionUID = 1L;
	
	private static final int N_PLAYERS = 6;
	private static final int N_ARRIVED = 51;
	private ApplicationImpl appC = new ApplicationImpl();
	private GameActionImpl gameC = new GameActionImpl(appC);
	private FileSerializableImpl serC = new FileSerializableImpl();
	private JLabel result = new JLabel("");
	private JButton dice = new JButton("");
	public List<JButton> routeButton = new ArrayList<>();
	private AllListImpl allList = new AllListImpl();
	private static boolean load;
	JFrame back = new JFrame("Non t'arrabbiare");
	
	public Start6Impl(){
		
	}
	
	public Start6Impl(String playerBlue, String playerOrange, String playerPurple, String playerRed, String playerYellow, String playerGreen, boolean load) {
		Start6Impl.load = load;
		appC.initializeBooleanVariables(N_PLAYERS);
		back.setSize(1200, 950);back.setResizable(false);
		back.setLocationRelativeTo(null);
		back.getContentPane().setLayout(new BorderLayout(0, 0));
		back.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		back.setResizable(false);
		back.setLayout(null);
		back.setVisible(true);
		back.addWindowListener(new WindowAdapter() {		
	    	public void windowClosing (WindowEvent e)
	    	{
	    		int choise = 0;
	    		if(!Start6Impl.load){
					choise = JOptionPane.showOptionDialog(null, 
							"Do you want exit without saving?", 
							"", 
							JOptionPane.YES_NO_OPTION, 
							JOptionPane.QUESTION_MESSAGE, 
							null, null, null);
				}
				else{
					choise = JOptionPane.showOptionDialog(null, 
							"Do you want exit?", 
							"", 
							JOptionPane.YES_NO_OPTION, 
							JOptionPane.QUESTION_MESSAGE, 
							null, null, null);
				}
	    		
	    		if (choise == JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
	    	}
	    });

		//creation of the route
		ArrayList<Integer> route = new ArrayList<>(Arrays.asList(530, 0, 530, 65, 530, 130, 580, 175, 630, 220, 685, 180, 740, 140, 785, 195, 830, 250, 775, 290, 720, 330, 720, 395, 720, 460, 775, 500, 830, 540, 785, 595, 740, 650, 685, 610, 630, 570, 580, 615, 530, 660, 530, 725, 530, 790, 465, 790, 400, 790, 400, 725, 400, 660, 350, 615, 300, 570, 245, 610, 190, 650, 145, 595, 100, 540, 155, 500, 210, 460, 210, 395, 210, 330, 155, 290, 100, 250, 145, 195, 190, 140, 245, 180, 300, 220, 350, 175, 400, 130, 400, 65, 400, 0, 465, 0));
		for (int i = 1; i <= 48; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button-white.png")));
			b.setName(String.valueOf(i));
			setButton(b, route);
			b.addActionListener(l -> {
				JButton button = (JButton) l.getSource();
				gameC.routeAction(b, N_PLAYERS, button, null, this, 48);
			});
			allList.getRouteButton().add(new Pair<JButton,Integer>(b,0));
			back.add(b);
		}
		
		//creation of bases
		ArrayList<Integer> baseBlu = new ArrayList<>(Arrays.asList(0, 0, 72, 0, 0, 72, 72, 72));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("",new ImageIcon(getClass().getClassLoader().getResource("image/button_blue_cross.png")));
			setButton(b, baseBlu);
			allList.getListBasePlayerBlue().add(new Pair<JButton,Boolean>(b,true));
			back.add(b);
		}
		
		ArrayList<Integer> baseOrange = new ArrayList<>(Arrays.asList(930, 0, 858, 0, 930, 72, 858, 72));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_orange_cross.png")));
			setButton(b, baseOrange);
			allList.getListBasePlayerOrange().add(new Pair<JButton,Boolean>(b,true));
			back.add(b);
		}
		
		ArrayList<Integer> basePurple = new ArrayList<>(Arrays.asList(930, 360, 930, 432, 858, 360, 858, 432));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_purple_cross.png")));
			setButton(b, basePurple);
			allList.getListBasePlayerPurple().add(new Pair<JButton,Boolean>(b,true));
			back.add(b);
		}
		
		ArrayList<Integer> baseRed = new ArrayList<>(Arrays.asList(930, 800, 858, 800, 858, 728, 930, 728));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_red_cross.png")));
			setButton(b, baseRed);
			allList.getListBasePlayerRed().add(new Pair<JButton,Boolean>(b,true));
			back.add(b);
		}
		
		ArrayList<Integer> baseYellow = new ArrayList<>(Arrays.asList(0, 800, 72, 800, 72, 728, 0, 728));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_yellow_cross.png")));
			setButton(b, baseYellow);
			allList.getListBasePlayerYellow().add(new Pair<JButton,Boolean>(b,true));
			back.add(b);
		}
		
		ArrayList<Integer> baseGreen = new ArrayList<>(Arrays.asList(0, 360, 0, 432, 72, 360, 72, 432));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_green_cross.png")));
			setButton(b, baseGreen);
			allList.getListBasePlayerGreen().add(new Pair<JButton,Boolean>(b,true));
			back.add(b);
		}
		
		//creation of arrives
		ArrayList<Integer> arriveBlue = new ArrayList<>(Arrays.asList(465, 65, 465, 130, 465, 195, 465, 260));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_blue_baby.png")));
			b.setName(String.valueOf(48+i));
			setButton(b, arriveBlue);
			b.addActionListener(l->{
				if(appC.isArrivingBlue()){
					gameC.arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_blue_baby_cross.png")), b, allList.getListArrivedPlayerBlue(), N_PLAYERS, null, this, N_ARRIVED);
				}
			});
			allList.getListArrivedPlayerBlue().add(new Pair<JButton,Boolean>(b,false));
			back.add(b);
		}	
		
		ArrayList<Integer> arriveOrange = new ArrayList<>(Arrays.asList(730, 235, 675, 275, 625, 315, 570, 355));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_orange_baby.png")));			
			b.setName(String.valueOf(48+i));
			setButton(b, arriveOrange);
			b.addActionListener(l->{
				if(appC.isArrivingOrange()){
					gameC.arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_orange_baby_cross.png")), b, allList.getListArrivedPlayerOrange(), N_PLAYERS, null, this, N_ARRIVED);
				}
			});
			allList.getListArrivedPlayerOrange().add(new Pair<JButton,Boolean>(b,false));
			back.add(b);
		}	
		
		ArrayList<Integer> arrivePurple = new ArrayList<>(Arrays.asList(730, 555, 675, 515, 625, 475, 570, 435));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_purple_baby.png")));
			b.setName(String.valueOf(48+i));
			setButton(b, arrivePurple);
			b.addActionListener(l->{
				if(appC.isArrivingPurple()){
					gameC.arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_purple_baby_cross.png")), b, allList.getListArrivedPlayerPurple(), N_PLAYERS, null, this, N_ARRIVED);
				}
			});			
			allList.getListArrivedPlayerPurple().add(new Pair<JButton,Boolean>(b,false));
			back.add(b);
		}
		
		ArrayList<Integer> arriveRed = new ArrayList<>(Arrays.asList(465, 725, 465, 660, 465, 595, 465, 530));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_red_baby.png")));
			b.setName(String.valueOf(48+i));
			setButton(b, arriveRed);
			b.addActionListener(l->{
				if(appC.isArrivingRed()){
					gameC.arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_red_baby_cross.png")), b, allList.getListArrivedPlayerRed(), N_PLAYERS, null, this, N_ARRIVED);	
				}
			});
			allList.getListArrivedPlayerRed().add(new Pair<JButton,Boolean>(b,false));
			back.add(b);
		}	
		
		ArrayList<Integer> arriveYellow = new ArrayList<>(Arrays.asList(200, 555, 255, 515, 310, 475, 365, 435));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_yellow_baby.png")));
			b.setName(String.valueOf(48+i));
			setButton(b, arriveYellow);
			b.addActionListener(l->{
				if(appC.isArrivingYellow()){
					gameC.arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_yellow_baby_cross.png")), b, allList.getListArrivedPlayerYellow(), N_PLAYERS, null, this, N_ARRIVED);
				}
			});			
			allList.getListArrivedPlayerYellow().add(new Pair<JButton,Boolean>(b,false));
			back.add(b);
		}
		
		ArrayList<Integer> arriveGreen = new ArrayList<>(Arrays.asList(200, 235, 255, 275, 310, 315, 365, 355));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_green_baby.png")));
			b.setName(String.valueOf(48+i));
			setButton(b, arriveGreen);
			b.addActionListener(l->{
				if(appC.isArrivingGreen()){
					gameC.arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_green_baby_cross.png")), b, allList.getListArrivedPlayerGreen(), N_PLAYERS, null, this, N_ARRIVED);
				}
			});			
			allList.getListArrivedPlayerGreen().add(new Pair<JButton,Boolean>(b,false));			
			back.add(b);
		}
		
		back.setLayout(null); 
		
		//creation of panel on the right
		JPanel panel = new JPanel();
		panel.setBounds(1031, 0, 163, 903);
		panel.setLayout(null);
		back.add(panel);

		Border border = BorderFactory.createLineBorder(Color.BLACK, 3);

		result.setFont(new Font("Sitka Text", Font.PLAIN, 20));
		result.setBorder(border);
		result.setHorizontalTextPosition(JLabel.CENTER);
		result.setVerticalTextPosition(JLabel.CENTER);
		result.setBounds(44, 78, 86, 33);
		panel.add(result);
		
		//button that show the statics of the match
		JButton statics = new JButton();
		statics.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/statics1.png")));
		statics.setBounds(35, 300, 120, 108);
		statics.addActionListener(l -> {
			new MatchStatic(6, appC, false, back);
			back.setEnabled(false);
		});
		panel.add(statics);
		
		if(!load){
			//button that save the match
			JButton save =  new JButton(new ImageIcon(getClass().getClassLoader().getResource("image/save.png")));
			save.setLocation(0, 776);
			save.setSize(162, 49);
			save.setRolloverEnabled(true);
			save.setFocusPainted(false);
			save.setBorderPainted(false);
			save.setContentAreaFilled(false);
			save.addActionListener(x -> {
				int choise = JOptionPane.showOptionDialog(null, 
						"Do you want exit with saving?", 
						"", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, 
						null, null, null);

				if (choise == JOptionPane.YES_OPTION)
				{
					int s = serC.save(N_PLAYERS);
					if(s == JFileChooser.APPROVE_OPTION){
						System.exit(0);
					}
				} else {
					System.exit(0);
				}

			});
			panel.add(save);
		}

		//button that close the match
		JButton exit =  new JButton(new ImageIcon(getClass().getClassLoader().getResource("image/close.png")));
		exit.setLocation(0, 829);
		exit.setSize(162, 49);
		exit.setRolloverEnabled(true);
		exit.setFocusPainted(false);
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.addActionListener(l -> {
			int choise = 0;
			if(!Start6Impl.load){
				choise = JOptionPane.showOptionDialog(null, 
						"Do you want exit without saving?", 
						"", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, 
						null, null, null);
			}
			else{
				choise = JOptionPane.showOptionDialog(null, 
						"Do you want exit?", 
						"", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, 
						null, null, null);
			}
			
			if (choise == JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		});
		panel.add(exit);
		
		//creation of labels with players name near the bases
		JLabel lblPlayerBlue = new JLabel();
		lblPlayerBlue.setBounds(12, 155, 139, 33);
		back.add(lblPlayerBlue);
		setLabel(lblPlayerBlue, playerBlue);
		
		JLabel lblPlayerOrange = new JLabel();
		lblPlayerOrange.setBounds(874, 155, 139, 33);
		back.add(lblPlayerOrange);
		setLabel(lblPlayerOrange, playerOrange);
			
		JLabel lblPlayerPurple = new JLabel();
		lblPlayerPurple.setBounds(874, 515, 139, 33);
		back.add(lblPlayerPurple);
		setLabel(lblPlayerPurple, playerPurple);
						
		JLabel lblPlayerGreen = new JLabel();
		lblPlayerGreen.setBounds(12, 515, 139, 33);
		back.add(lblPlayerGreen);
		setLabel(lblPlayerGreen, playerGreen);
		
		JLabel lblPlayerRed = new JLabel();
		lblPlayerRed.setBounds(874, 695, 139, 33);
		back.add(lblPlayerRed);
		setLabel(lblPlayerRed, playerRed);
		
		JLabel lblPlayerYellow = new JLabel();
		lblPlayerYellow.setBounds(12, 695, 139, 33);
		back.add(lblPlayerYellow);
		setLabel(lblPlayerYellow, playerYellow);
		
		dice.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/dice.png")));
		dice.setBounds(44, 13, 86, 64);
		panel.add(dice);
		dice.addActionListener(l -> {
			gameC.game6(N_PLAYERS, this);
		});
		
		//load is true if the match current match is loaded
		if(load){
			serC.load();
			lblPlayerBlue.setText(ApplicationImpl.getM().getPlayers().get(0).getName());
			lblPlayerOrange.setText(ApplicationImpl.getM().getPlayers().get(1).getName());
			lblPlayerPurple.setText(ApplicationImpl.getM().getPlayers().get(2).getName());
			lblPlayerRed.setText(ApplicationImpl.getM().getPlayers().get(3).getName());
			lblPlayerYellow.setText(ApplicationImpl.getM().getPlayers().get(4).getName());
			lblPlayerGreen.setText(ApplicationImpl.getM().getPlayers().get(5).getName());
			appC.refreshBaseButtons();
			appC.refreshArriveButtons();
			appC.refreshGameboard();
			load = false;
		}
		back.setVisible(true);
		showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn." );
	}
	
	public void setTextResult(String s) {
		result.setText(s);
	}
	
	public void setDiceEnabled(boolean bool) {
		dice.setEnabled(bool);
	}

	public void showMessage(String s){
		JOptionPane.showMessageDialog(null, s);
	}

	public void setButton(JButton b, ArrayList<Integer> arrive) {
		b.setLocation(arrive.get(0), arrive.get(1));
		b.setSize(80, 80);
		b.setRolloverEnabled(true);
		b.setFocusPainted(false);
		b.setBorderPainted(false);
		b.setContentAreaFilled(false);
		arrive.remove(1);
		arrive.remove(0);
	}
	
	public void setLabel(JLabel label, String player){
		label.setVerticalTextPosition(SwingConstants.CENTER);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setFont(new Font("Sitka Text", Font.BOLD, 20));
		label.setText(player);
	}
	
	public JFrame getFrame(){
		return this.back;
	}
}