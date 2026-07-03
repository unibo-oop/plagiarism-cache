package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

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

/** This class create the page that shows the game board for 4 players
 *
 */
public class Start4Impl extends JFrame implements Serializable, Utility {
	
	private static final long serialVersionUID = 1L;
	
	private static final int N_PLAYERS = 4;
	private static final int N_ARRIVED = 43;
	private ApplicationImpl appC = new ApplicationImpl();
	private GameActionImpl gameC = new GameActionImpl(appC);
	private FileSerializableImpl serC = new FileSerializableImpl();
	private JLabel result = new JLabel("");
	private JButton dice = new JButton("");
	private AllListImpl allList = new AllListImpl();
	private static boolean load;
	JFrame back = new JFrame("Non t'arrabbiare");

	public Start4Impl(){
		
	}
	
	public Start4Impl(String playerBlue, String playerOrange, String playerPurple, String playerRed, boolean load){
		Start4Impl.load = load;
		appC.initializeBooleanVariables(N_PLAYERS);
		back.setSize(1000, 788);
		back.setLocationRelativeTo(null);
		back.getContentPane().setLayout(new BorderLayout(0, 0));
		back.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		back.setResizable(false);
		back.addWindowListener(new WindowAdapter() {		
	    	public void windowClosing (WindowEvent e)
	    	{
	    		int choise = 0;
				if(!Start4Impl.load){
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
		ArrayList<Integer> route = new ArrayList<>(Arrays.asList(30, 260, 90, 260, 150, 260, 210, 260, 270, 260, 270, 200, 270, 140, 270, 80, 270, 20, 330, 20, 390, 20, 390, 80, 390, 140, 390, 200, 390, 260, 450, 260, 510, 260, 570, 260, 630, 260, 630, 320, 630, 380, 570, 380, 510, 380, 450, 380, 390, 380, 390, 440, 390, 500, 390, 560, 390, 620, 330, 620, 270, 620, 270, 560, 270, 500, 270, 440, 270, 380, 210, 380, 150, 380, 90, 380, 30, 380, 30, 320));
		for (int i = 1; i <= 40; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button-white.png")));
			b.setName(String.valueOf(i));
			setButton(b,route);
			b.addActionListener(l -> {
				JButton button = (JButton) l.getSource();
				gameC.routeAction(b, N_PLAYERS, button, this, null, 40);
			});
			allList.getRouteButton().add(new Pair<JButton,Integer>(b,0));
			back.add(b);
		}
		
		//creation of bases
		ArrayList<Integer> baseBlue = new ArrayList<>(Arrays.asList(0, 0, 72, 0, 0, 72, 72, 72));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("",new ImageIcon(getClass().getClassLoader().getResource("image/button_blue_cross.png")));
			setButton(b, baseBlue);
			allList.getListBasePlayerBlue().add(new Pair<JButton,Boolean>(b,true));
			back.add(b);
		}

		ArrayList<Integer> baseOrange = new ArrayList<>(Arrays.asList(700, 0, 628, 0, 700, 72,628, 72));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_orange_cross.png")));
			setButton(b, baseOrange);
			allList.getListBasePlayerOrange().add(new Pair<JButton,Boolean>(b,true));
			back.add(b);
		}
		
		ArrayList<Integer> basePurple = new ArrayList<>(Arrays.asList(700, 660, 700, 588, 628, 588, 628, 660));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_purple_cross.png")));
			setButton(b, basePurple);
			allList.getListBasePlayerPurple().add(new Pair<JButton,Boolean>(b,true));
			back.add(b);
		}

		ArrayList<Integer> baseRed = new ArrayList<>(Arrays.asList(0, 660, 72, 660, 72, 588, 0, 588));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_red_cross.png")));
			setButton(b, baseRed);
			allList.getListBasePlayerRed().add(new Pair<JButton,Boolean>(b,true));
			back.add(b);
		}

		//creation of arrives
		ArrayList<Integer> arriveBlue = new ArrayList<>(Arrays.asList(90, 320, 150, 320, 210, 320, 270, 320));
		for (int i = 0; i < 4; i++) { 
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_blue_baby.png")));
			b.setName(String.valueOf(40+i));
			setButton(b, arriveBlue);
			b.addActionListener(l->{
				if(appC.isArrivingBlue()){
					gameC.arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_blue_baby_cross.png")), b, allList.getListArrivedPlayerBlue(), N_PLAYERS, this, null, N_ARRIVED);
				}
			});
			allList.getListArrivedPlayerBlue().add(new Pair<JButton,Boolean>(b,false));
			back.add(b);
		}

		ArrayList<Integer> arriveOrange = new ArrayList<>(Arrays.asList(330, 80, 330, 140, 330, 200, 330, 260));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_orange_baby.png")));			
			b.setName(String.valueOf(40+i));
			setButton(b, arriveOrange);
			b.addActionListener(l->{
				if(appC.isArrivingOrange()){
					gameC.arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_orange_baby_cross.png")), b, allList.getListArrivedPlayerOrange(), N_PLAYERS, this, null, N_ARRIVED);
				}
			});
			allList.getListArrivedPlayerOrange().add(new Pair<JButton,Boolean>(b,false));
			back.add(b);
		}

		ArrayList<Integer> arrivePurple = new ArrayList<>(Arrays.asList(570, 320, 510, 320, 450, 320, 390, 320));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_purple_baby.png")));
			b.setName(String.valueOf(40+i));
			setButton(b, arrivePurple);
			b.addActionListener(l->{
				if(appC.isArrivingPurple()){
					gameC.arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_purple_baby_cross.png")), b, allList.getListArrivedPlayerPurple(), N_PLAYERS, this, null, N_ARRIVED);
				}
			});	
			allList.getListArrivedPlayerPurple().add(new Pair<JButton,Boolean>(b,false));
			back.add(b);
		}

		ArrayList<Integer> arriveRed = new ArrayList<>(Arrays.asList(330, 380, 330, 440, 330, 500, 330, 560));
		for (int i = 0; i < 4; i++) {
			JButton b =  new JButton("", new ImageIcon(getClass().getClassLoader().getResource("image/button_red_baby.png")));
			b.setName(String.valueOf(40+i));
			setButton(b, arriveRed);
			b.addActionListener(l->{
				if(appC.isArrivingRed()){
					gameC.arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_red_baby_cross.png")), b, allList.getListArrivedPlayerRed(), N_PLAYERS, this, null, N_ARRIVED);	
				}
			});
			allList.getListArrivedPlayerRed().add(new Pair<JButton,Boolean>(b,false));
			back.add(b);
		}
		
		back.setLayout(null);

		//creation of panel on the right
		JPanel panel = new JPanel();
		panel.setBounds(819, 0, 163, 741);
		panel.setLayout(null);
		back.add(panel);

		Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
		
		result.setFont(new Font("Sitka Text", Font.BOLD, 20));
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
			new MatchStatic(4, appC, false, back);
			back.setEnabled(false);
		});
		panel.add(statics);

		if(!load){
			//button that save the match
			JButton save =  new JButton(new ImageIcon(getClass().getClassLoader().getResource("image/save.png")));
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
				}
			});
			save.setLocation(0, 622);
			save.setSize(162, 50);
			save.setRolloverEnabled(true);
			save.setFocusPainted(false);
			save.setBorderPainted(false);
			save.setContentAreaFilled(false);
			panel.add(save);
		}

		//button that close the match
		JButton exit =  new JButton(new ImageIcon(getClass().getClassLoader().getResource("image/close.png")));
		exit.setLocation(0, 682);
		exit.setSize(162, 50);
		exit.setRolloverEnabled(true);
		exit.setFocusPainted(false);
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.addActionListener(l -> {
			int choise = 0;
			if(!Start4Impl.load){
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
		lblPlayerBlue.setBounds(12, 148, 139, 33);
		back.add(lblPlayerBlue);
		setLabel(lblPlayerBlue, playerBlue);

		JLabel lblPlayerOrange = new JLabel();
		lblPlayerOrange.setBounds(651, 148, 139, 33);
		back.add(lblPlayerOrange);
		setLabel(lblPlayerOrange, playerOrange);

		JLabel lblPlayerPurple = new JLabel();
		lblPlayerPurple.setBounds(651, 552, 139, 33);
		back.add(lblPlayerPurple);
		setLabel(lblPlayerPurple, playerPurple);

		JLabel lblPlayerRed = new JLabel();
		lblPlayerRed.setBounds(12, 552, 139, 33);
		back.add(lblPlayerRed);
		setLabel(lblPlayerRed, playerRed);
		
		dice.setBounds(44, 13, 86, 64);
		panel.add(dice);
		dice.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/dice.png")));		
		dice.addActionListener(l -> {		
			gameC.game4(N_PLAYERS, this);
		});
		
		//load is true if the match current match is loaded
		if(load){
			serC.load();
			lblPlayerBlue.setText(ApplicationImpl.getM().getPlayers().get(0).getName());
			lblPlayerOrange.setText(ApplicationImpl.getM().getPlayers().get(1).getName());
			lblPlayerPurple.setText(ApplicationImpl.getM().getPlayers().get(2).getName());
			lblPlayerRed.setText(ApplicationImpl.getM().getPlayers().get(3).getName());
			appC.refreshBaseButtons();
			appC.refreshArriveButtons();
			appC.refreshGameboard();
			load = false;
		}
		
		back.setVisible(true);
		showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn.");
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

