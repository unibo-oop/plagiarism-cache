package view;

import java.awt.Color;
import java.awt.EventQueue;
import controller.Turn;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class NameForm extends Home{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method that return into Home if the form is closed
	 */
	class WindowEventHandler extends WindowAdapter {
		  public void windowClosing(WindowEvent evt) {
			  EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Home window = new Home();
							window.frameHome.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
		  }
	}

	private static int nPlayer;
	private static int Life;
	private static int Cash;
	private static int Point;
	JFrame frameName;
	private JTextField textName1;
	private JTextField textName2;
	private JTextField textName3;
	private JTextField textName4;
	private JComboBox<String> comboMostro1;
	private JComboBox<String> comboMostro2;
	private JComboBox<String> comboMostro3;
	private JComboBox<String> comboMostro4;
	private ArrayList<String> names = new ArrayList<>();

	public static void setNPlayer(int n) {
		nPlayer = n;
	}

	/**
	 * Create the application.
	 */
	public NameForm(int n, int l, int c, int p) {
		nPlayer = n;
		Life = l;
		Cash = c;
		Point = p;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameName = new JFrame();
		frameName.setBounds(100, 100, 450, 300);
		frameName.addWindowListener(new WindowEventHandler());
		frameName.setResizable(false);
		frameName.getContentPane().setBackground(new Color (176,224,230));
		frameName.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frameName.getContentPane().setLayout(null);
		
		JLabel lblInserisciINomi = new JLabel("Inserisci i nomi dei giocatori e seleziona il personaggio");
		lblInserisciINomi.setBounds(20, 20, 387, 15);
		frameName.getContentPane().add(lblInserisciINomi);
		
		textName1 = new JTextField();
		textName1.setBounds(126, 61, 204, 19);
		frameName.getContentPane().add(textName1);
		textName1.setColumns(10);
		comboMostro1 = new JComboBox<>();
		comboMostro1.setBounds(330, 61, 100, 19);
		comboMostro1.addItem("CyberKitty");
		comboMostro1.addItem("TheKing");
		comboMostro1.addItem("MecaDragon");
		comboMostro1.addItem("SpacePenguin");
		comboMostro1.addItem("IronRook");
		comboMostro1.addItem("Alienoid");
		comboMostro1.addItem("Gigazaur");
		frameName.getContentPane().add(comboMostro1);
		
		
		textName2 = new JTextField();
		textName2.setBounds(126, 92, 204, 19);
		frameName.getContentPane().add(textName2);
		textName2.setColumns(10);
		comboMostro2 = new JComboBox<>();
		comboMostro2.setBounds(330, 92, 100, 19);
		comboMostro2.addItem("CyberKitty");
		comboMostro2.addItem("TheKing");
		comboMostro2.addItem("MecaDragon");
		comboMostro2.addItem("SpacePenguin");
		comboMostro2.addItem("IronRook");
		comboMostro2.addItem("Alienoid");
		comboMostro2.addItem("Gigazaur");
		frameName.getContentPane().add(comboMostro2);
		
		if(nPlayer > 2) {
			textName3 = new JTextField();
			textName3.setBounds(126, 123, 204, 19);
			frameName.getContentPane().add(textName3);
			textName3.setColumns(10);
			comboMostro3 = new JComboBox<>();
			comboMostro3.setBounds(330, 123, 100, 19);
			comboMostro3.addItem("CyberKitty");
			comboMostro3.addItem("TheKing");
			comboMostro3.addItem("MecaDragon");
			comboMostro3.addItem("SpacePenguin");
			comboMostro3.addItem("IronRook");
			comboMostro3.addItem("Alienoid");
			comboMostro3.addItem("Gigazaur");
			frameName.getContentPane().add(comboMostro3);
			
			JLabel lblName3 = new JLabel("Giocatore 3:");
			lblName3.setBounds(12, 125, 96, 15);
			frameName.getContentPane().add(lblName3);
		}
		if(nPlayer > 3) {
			textName4 = new JTextField();
			textName4.setBounds(126, 154, 204, 19);
			frameName.getContentPane().add(textName4);
			textName4.setColumns(10);
			comboMostro4 = new JComboBox<>();
			comboMostro4.setBounds(330, 154, 100, 19);
			comboMostro4.addItem("CyberKitty");
			comboMostro4.addItem("TheKing");
			comboMostro4.addItem("MecaDragon");
			comboMostro4.addItem("SpacePenguin");
			comboMostro4.addItem("IronRook");
			comboMostro4.addItem("Alienoid");
			comboMostro4.addItem("Gigazaur");
			frameName.getContentPane().add(comboMostro4);
			
			JLabel lblName4 = new JLabel("Giocatore 4:");
			lblName4.setBounds(12, 156, 96, 15);
			frameName.getContentPane().add(lblName4);
			
		}
		JLabel lblName1 = new JLabel("Giocatore 1:");
		lblName1.setBounds(12, 63, 96, 15);
		frameName.getContentPane().add(lblName1);
		
		JLabel lblName2 = new JLabel("Giocatore 2:");
		lblName2.setBounds(12, 94, 96, 15);
		frameName.getContentPane().add(lblName2);
		
		JButton btnStart = new JButton("Avvia");
		btnStart.setBounds(255, 206, 117, 25);
		frameName.getContentPane().add(btnStart);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(85,206,117,25);
		frameName.getContentPane().add(btnIndietro);
		
		btnStart.addActionListener(e ->{
			//qui il controller deve creare i giocatori prima di avviare la plancia
			//ArrayList<String> names = Arrays.asList(textName1.getText(),textName2.getText(),textName3.getText(),textName4.getText());
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						if(nPlayer == 4) {
							names.addAll(Arrays.asList(textName1.getText(),textName2.getText(),textName3.getText(),textName4.getText()));
							Turn.createPlayers(names,Life,Cash);
							Game window = new Game(textName1.getText(),textName2.getText(),textName3.getText(),textName4.getText(), Life, Point, Cash, nPlayer, (String)comboMostro1.getSelectedItem(),(String)comboMostro2.getSelectedItem(),(String)comboMostro3.getSelectedItem(),(String)comboMostro4.getSelectedItem());
							Game.framePlancia.setVisible(true);
						} 
						if(nPlayer == 3) {
							names.addAll(Arrays.asList(textName1.getText(),textName2.getText(),textName3.getText()));
							Turn.createPlayers(names,Life,Cash);
							Game window = new Game(textName1.getText(),textName2.getText(),textName3.getText(),"" , Life, Point, Cash, nPlayer,(String)comboMostro1.getSelectedItem(),(String)comboMostro2.getSelectedItem(),(String)comboMostro3.getSelectedItem(),"");
							Game.framePlancia.setVisible(true);
						}
						if(nPlayer == 2) {
							names.addAll(Arrays.asList(textName1.getText(),textName2.getText()));
							Turn.createPlayers(names,Life,Cash);
							Game window = new Game(textName1.getText(),textName2.getText(),"","", Life, Point, Cash, nPlayer,(String)comboMostro1.getSelectedItem(),(String)comboMostro2.getSelectedItem(),"","");
							Game.framePlancia.setVisible(true);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			frameName.dispose();
		});
		
		btnIndietro.addActionListener( f-> {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TestForm window = new TestForm(nPlayer, Life, Cash, Point);
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			frameName.dispose();
		});
	}
}
