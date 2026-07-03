package view;

import java.awt.Color;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import controller.Turn;

public class Game {

	static JFrame framePlancia;
	private String nome1;
	private String nome2;
	private String nome3;
	private String nome4;
	private static Integer punti;
	private Integer soldi;
	private Integer vita;
	private static int numero;
	private String icona1;
	private String icona2;
	private String icona3;
	private String icona4;
	private static JButton info;
	private static JButton btnK4;
	private static JButton btnK3;
	private static JButton btnK2;
	private static JButton btnK1;
	private static JTextPane textLog;
	private static JButton btnDado1;
	private static JButton btnDado2;
	private static JButton btnDado3;
	private static JButton btnDado4;
	private static JButton btnDado5;
	private static JButton btnDado6;
	private JButton btnLanciaDadi;
	private JButton btnConfermaLancio;
	private static JButton btnFineTurno1;
	private static JButton btnFineTurno2;
	private static JButton btnFineTurno3;
	private static JButton btnFineTurno4;
	private static JLabel lblSoldi1;
	private static JLabel lblSoldi2;
	private static JLabel lblPunti1;
	private static JLabel lblPunti2;
	private static JLabel lblPunti3;
	private static JLabel lblPunti4;
	private static JLabel lblVita1;
	private static JLabel lblVita2;
	private static JLabel lblVita3;
	private static JLabel lblVita4;
	private static JLabel lblSoldi3;
	private static JLabel lblSoldi4;
	private static JButton btnCarta1;
	private static JButton btnCarta2;
	private static JButton btnCarta3;
	private static JButton btnCompraCarta1;
	private static JButton btnCompraCarta2;
	private static JButton btnCompraCarta3;
	private static JButton btnCompraCarta4;
	private static JLabel lblMostro1;
	private static JLabel lblMostro2;
	private static JLabel lblMostro3;
	private static JLabel lblMostro4;
	private static JComboBox<String> comboMano1;
	private static JComboBox<String> comboMano2;
	private static JComboBox<String> comboMano3;
	private static JComboBox<String> comboMano4;
	
	/**
	 * Create the application.
	 */
	public Game() {
		initialize();
	}
	
	/**
	 * Constructor of the class Game
	 * @param nome1
	 * @param nome2
	 * @param nome3
	 * @param nome4
	 * @param vita
	 * @param punti
	 * @param soldi
	 * @param numero
	 * @param icona1
	 * @param icona2
	 * @param icona3
	 * @param icona4
	 */
	public Game(String nome1, String nome2, String nome3, String nome4, int vita, int punti, int soldi, int numero, String icona1, String icona2, String icona3, String icona4) {
		this.nome1 = nome1;
		this.nome2 = nome2;
		this.nome3 = nome3;
		this.nome4 = nome4;
		this.vita = vita;
		this.soldi = soldi;
		Game.punti = punti;
		Game.numero = numero;
		this.icona1 = icona1;
		this.icona2 = icona2;
		this.icona3 = icona3;
		this.icona4 = icona4;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Turn.setMaxLife(vita); //setto nel turno il valore massimo della vita
		
		framePlancia = new JFrame();
		framePlancia.setBounds(0,0,1366,728);
		framePlancia.getContentPane().setBackground(new Color (176,224,230));
		framePlancia.setResizable(false);
		framePlancia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePlancia.getContentPane().setLayout(null);
		
		info = new JButton("Info");
		info.setBounds(830, 650, 89, 23);
		framePlancia.getContentPane().add(info);
		
		//pannello log
		textLog = new JTextPane();
		textLog.setBounds(977, 185, 327, 474);
		textLog.setText("Che la battaglia per Forlì abbia inizio!" + "\n");
		framePlancia.getContentPane().add(textLog);
		//pannello log
		
		//bottoni dadi
		btnDado1 = new JButton("1");
		btnDado1.setBounds(905, 96, 46, 23);
		framePlancia.getContentPane().add(btnDado1);
		
		btnDado2 = new JButton("2");
		btnDado2.setBounds(977, 96, 46, 23);
		framePlancia.getContentPane().add(btnDado2);
		
		btnDado3 = new JButton("3");
		btnDado3.setBounds(1045, 96, 46, 23);
		framePlancia.getContentPane().add(btnDado3);
		
		btnDado4 = new JButton("4");
		btnDado4.setBounds(1116, 96, 46, 23);
		framePlancia.getContentPane().add(btnDado4);
		
		btnDado5 = new JButton("5");
		btnDado5.setBounds(1190, 96, 46, 23);
		framePlancia.getContentPane().add(btnDado5);
		
		btnDado6 = new JButton("6");
		btnDado6.setBounds(1258, 96, 46, 23);
		framePlancia.getContentPane().add(btnDado6);
		
		btnLanciaDadi = new JButton("Lancia");
		btnLanciaDadi.setBounds(977, 140, 114, 23);
		framePlancia.getContentPane().add(btnLanciaDadi);
		
		btnConfermaLancio = new JButton("Conferma");
		btnConfermaLancio.setBounds(1116, 140, 120, 23);
		btnConfermaLancio.addActionListener(e->{
			if(Turn.getRoll()) {
				Turn.solveDice();
			}
		});
		framePlancia.getContentPane().add(btnConfermaLancio);
		
		JLabel lblDadi = new JLabel("Dadi");
		lblDadi.setBounds(1088, 11, 46, 14);
		framePlancia.getContentPane().add(lblDadi);
				//immagini dadi
				JLabel lblDado1 = new JLabel("");
				lblDado1.setIcon(new ImageIcon(Game.class.getResource("/res/1.png")));
				lblDado1.setBounds(905, 45, 46, 40);
				framePlancia.getContentPane().add(lblDado1);
				
				JLabel lblDado2 = new JLabel("");
				lblDado2.setIcon(new ImageIcon(Game.class.getResource("/res/2.png")));
				lblDado2.setBounds(977, 45, 46, 40);
				framePlancia.getContentPane().add(lblDado2);
				
				JLabel lblDado3 = new JLabel("");
				lblDado3.setIcon(new ImageIcon(Game.class.getResource("/res/3.png")));
				lblDado3.setBounds(1045, 45, 46, 40);
				framePlancia.getContentPane().add(lblDado3);
				
				JLabel lblDado4 = new JLabel("");
				lblDado4.setIcon(new ImageIcon(Game.class.getResource("/res/4.png")));
				lblDado4.setBounds(1116, 45, 46, 40);
				framePlancia.getContentPane().add(lblDado4);
				
				JLabel lblDado5 = new JLabel("");
				lblDado5.setIcon(new ImageIcon(Game.class.getResource("/res/5.png")));
				lblDado5.setBounds(1190, 45, 46, 40);
				framePlancia.getContentPane().add(lblDado5);
				
				JLabel lblDado6 = new JLabel("");
				lblDado6.setIcon(new ImageIcon(Game.class.getResource("/res/6.png")));
				lblDado6.setBounds(1258, 45, 46, 40);
				framePlancia.getContentPane().add(lblDado6);
				//immagini dadi
				Turn.setDices(lblDado1, lblDado2, lblDado3, lblDado4, lblDado5, lblDado6);
				Turn.setBtnDice(btnDado1, btnDado2, btnDado3, btnDado4, btnDado5, btnDado6);
		//bottoni dadi
		
		
		
		//random scelta del king
		Random k = new Random();
		int vec = k.nextInt(numero);
		//random scelta del king
		
		//giocatore 1
		JLabel lblNome1 = new JLabel(nome1);
		lblNome1.setBounds(32, 37, 60, 14);
		framePlancia.getContentPane().add(lblNome1);
		textLog.setText(textLog.getText() + nome1 + " Pronto!\n");
		
		lblSoldi1 = new JLabel(soldi.toString());
		lblSoldi1.setBounds(150, 37, 50, 14);
		framePlancia.getContentPane().add(lblSoldi1);
		
		lblVita1 = new JLabel(vita.toString());
		lblVita1.setBounds(263, 37, 50, 14);
		framePlancia.getContentPane().add(lblVita1);
		
		lblPunti1 = new JLabel("0");
		lblPunti1.setBounds(87, 78, 25, 14);
		framePlancia.getContentPane().add(lblPunti1);
		
		btnK1 = new JButton("");
		btnK1.setBounds(23, 107, 89, 89);
		framePlancia.getContentPane().add(btnK1);
		
		lblMostro1 = new JLabel("Mostro 1");
		lblMostro1.setIcon(new ImageIcon(Game.class.getResource("/res/"+icona1+".png")));
		lblMostro1.setBounds(141, 78, 118, 118);
		framePlancia.getContentPane().add(lblMostro1);
		
		btnCompraCarta1 = new JButton("Compra Carta");
		btnCompraCarta1.setBounds(23, 234, 157, 23);
		btnCompraCarta1.addActionListener(e->{
			if(Turn.getCurrentInt() == 0) {
				Turn.canBuyCard();
			}
		});
		framePlancia.getContentPane().add(btnCompraCarta1);
		
		btnFineTurno1 = new JButton("Fine Turno");
		btnFineTurno1.setBounds(190, 234, 154, 23);
		btnFineTurno1.addActionListener(e->{
			if(Turn.getCurrentInt() == 0) {
				Turn.fineTurno();
			}
		});
		framePlancia.getContentPane().add(btnFineTurno1);
		
		comboMano1 = new JComboBox<String>();
		comboMano1.setToolTipText("Mano");
		comboMano1.setBounds(23, 268, 320, 20);
		framePlancia.getContentPane().add(comboMano1);
		
		JLabel lblNewLabel = new JLabel("Soldi:");
		lblNewLabel.setBounds(109, 37, 32, 14);
		framePlancia.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Vita:");
		lblNewLabel_1.setBounds(234, 37, 32, 14);
		framePlancia.getContentPane().add(lblNewLabel_1);
		
		JLabel lblDenaro = new JLabel("Punti:");
		lblDenaro.setBounds(32, 78, 46, 14);
		framePlancia.getContentPane().add(lblDenaro);
		//giocatore 1
		
		//giocatore 2
		JLabel lblNome2 = new JLabel(nome2);
		lblNome2.setBounds(469, 37, 60, 14);
		framePlancia.getContentPane().add(lblNome2);
		textLog.setText(textLog.getText() + nome2 + " Pronto!\n");
		
		lblSoldi2 = new JLabel(soldi.toString());
		lblSoldi2.setBounds(585, 37, 50, 14);
		framePlancia.getContentPane().add(lblSoldi2);
		
		lblVita2 = new JLabel(vita.toString());
		lblVita2.setBounds(714, 37, 50, 14);
		framePlancia.getContentPane().add(lblVita2);
		
		lblPunti2 = new JLabel("0");
		lblPunti2.setBounds(533, 78, 25, 14);
		framePlancia.getContentPane().add(lblPunti2);
		
		btnK2 = new JButton("");
		btnK2.setBounds(469, 107, 89, 89);
		framePlancia.getContentPane().add(btnK2);
		
		lblMostro2 = new JLabel("Mostro 2");
		lblMostro2.setIcon(new ImageIcon(Game.class.getResource("/res/"+icona2+".png")));
		lblMostro2.setBounds(586, 78, 118, 118);
		framePlancia.getContentPane().add(lblMostro2);
		
		comboMano2 = new JComboBox<String>();
		comboMano2.setToolTipText("Mano");
		comboMano2.setBounds(469, 268, 322, 20);
		framePlancia.getContentPane().add(comboMano2);
		
		btnCompraCarta2 = new JButton("Compra Carta");
		btnCompraCarta2.setBounds(469, 234, 157, 23);
		btnCompraCarta2.addActionListener(e->{
			if(Turn.getCurrentInt() == 1) {
				Turn.canBuyCard();
			}
		});
		framePlancia.getContentPane().add(btnCompraCarta2);
		
		btnFineTurno2 = new JButton("Fine Turno");
		btnFineTurno2.setBounds(636, 234, 155, 23);
		btnFineTurno2.addActionListener(e->{
			if(Turn.getCurrentInt() == 1) {
				Turn.fineTurno();
			}
		});
		framePlancia.getContentPane().add(btnFineTurno2);
		
		JLabel label_6 = new JLabel("Punti:");
		label_6.setBounds(469, 78, 46, 14);
		framePlancia.getContentPane().add(label_6);
		
		JLabel label_1 = new JLabel("Soldi:");
		label_1.setBounds(552, 37, 32, 14);
		framePlancia.getContentPane().add(label_1);
		
		JLabel label_5 = new JLabel("Vita:");
		label_5.setBounds(673, 37, 32, 14);
		framePlancia.getContentPane().add(label_5);
		//giocatore 2
		
		if(numero>2) {
		// giocatore 3
		JLabel lblNome3 = new JLabel(nome3);
		lblNome3.setBounds(32, 371, 60, 14);
		framePlancia.getContentPane().add(lblNome3);
		textLog.setText(textLog.getText() + nome3 + " Pronto!\n");
		
		lblSoldi3 = new JLabel(soldi.toString());
		lblSoldi3.setBounds(150, 371, 50, 14);
		framePlancia.getContentPane().add(lblSoldi3);
		
		lblVita3 = new JLabel(vita.toString());
		lblVita3.setBounds(243, 371, 50, 14);
		framePlancia.getContentPane().add(lblVita3);
		
		lblPunti3 = new JLabel("0");
		lblPunti3.setBounds(96, 411, 25, 14);
		framePlancia.getContentPane().add(lblPunti3);
		
		btnK3 = new JButton("");
		btnK3.setBounds(32, 440, 89, 89);
		framePlancia.getContentPane().add(btnK3);
		
		lblMostro3 = new JLabel("Mostro 3");
		lblMostro3.setIcon(new ImageIcon(Game.class.getResource("/res/"+icona3+".png")));
		lblMostro3.setBounds(151, 411, 118, 118);
		framePlancia.getContentPane().add(lblMostro3);
		
		btnCompraCarta3 = new JButton("Compra Carta");
		btnCompraCarta3.setBounds(32, 557, 148, 23);
		btnCompraCarta3.addActionListener(e->{
			if(Turn.getCurrentInt() == 2) {
				Turn.canBuyCard();
			}
		});
		framePlancia.getContentPane().add(btnCompraCarta3);
		
		btnFineTurno3 = new JButton("Fine Turno");
		btnFineTurno3.setBounds(190, 557, 154, 23);
		btnFineTurno3.addActionListener(e->{
			if(Turn.getCurrentInt() == 2) {
				Turn.fineTurno();
			}
		});
		framePlancia.getContentPane().add(btnFineTurno3);
		
		comboMano3 = new JComboBox<String>();
		comboMano3.setToolTipText("Mano");
		comboMano3.setBounds(32, 591, 312, 20);
		framePlancia.getContentPane().add(comboMano3);
		
		JLabel label = new JLabel("Soldi:");
		label.setBounds(109, 371, 32, 14);
		framePlancia.getContentPane().add(label);
		
		JLabel label_3 = new JLabel("Vita:");
		label_3.setBounds(207, 371, 32, 14);
		framePlancia.getContentPane().add(label_3);
		
		JLabel label_7 = new JLabel("Punti:");
		label_7.setBounds(32, 411, 46, 14);
		framePlancia.getContentPane().add(label_7);
		//giocatore 3
		}
		
		if(numero>3) {
		//giocatore 4
		JLabel lblNome4 = new JLabel(nome4);
		lblNome4.setBounds(469, 371, 60, 14);
		framePlancia.getContentPane().add(lblNome4);
		textLog.setText(textLog.getText() + nome4 + " Pronto!\n");
		
		lblSoldi4 = new JLabel(soldi.toString());
		lblSoldi4.setBounds(585, 371, 50, 14);
		framePlancia.getContentPane().add(lblSoldi4);
		
		lblVita4 = new JLabel(vita.toString());
		lblVita4.setBounds(713, 371, 50, 14);
		framePlancia.getContentPane().add(lblVita4);
		
		lblPunti4 = new JLabel("0");
		lblPunti4.setBounds(533, 411, 25, 14);
		framePlancia.getContentPane().add(lblPunti4);
		
		btnK4 = new JButton("");
		btnK4.setBounds(469, 440, 89, 89);
		framePlancia.getContentPane().add(btnK4);
		
		lblMostro4 = new JLabel("Mostro 4");
		lblMostro4.setIcon(new ImageIcon(Game.class.getResource("/res/"+icona4+".png")));
		lblMostro4.setBounds(586, 411, 118, 118);
		framePlancia.getContentPane().add(lblMostro4);	
		
		btnCompraCarta4 = new JButton("Compra Carta");
		btnCompraCarta4.setBounds(469, 557, 157, 23);
		btnCompraCarta4.addActionListener(e->{
			if(Turn.getCurrentInt() == 3) {
				Turn.canBuyCard();
			}
		});
		framePlancia.getContentPane().add(btnCompraCarta4);
		
		btnFineTurno4 = new JButton("Fine Turno");
		btnFineTurno4.setBounds(636, 557, 159, 23);
		btnFineTurno4.addActionListener(e->{
			if(Turn.getCurrentInt() == 3) {
				Turn.fineTurno();
			}
		});
		framePlancia.getContentPane().add(btnFineTurno4);
		
		comboMano4 = new JComboBox<String>();
		comboMano4.setToolTipText("Mano");
		comboMano4.setBounds(469, 591, 325, 20);
		framePlancia.getContentPane().add(comboMano4);
		
		JLabel label_2 = new JLabel("Soldi:");
		label_2.setBounds(544, 371, 32, 14);
		framePlancia.getContentPane().add(label_2);
		
		JLabel label_4 = new JLabel("Vita:");
		label_4.setBounds(673, 371, 32, 14);
		framePlancia.getContentPane().add(label_4);
		
		JLabel label_8 = new JLabel("Punti:");
		label_8.setBounds(469, 411, 46, 14);
		framePlancia.getContentPane().add(label_8);
		//giocatore 4
		}
		
		//passo le label dei vari valori
		
		
		
		if(numero == 4) {
			Turn.setCrown(btnK1, btnK2, btnK3, btnK4);
			Turn.setPoints(lblPunti1, lblPunti2, lblPunti3, lblPunti4);
			Turn.setLifeLbl(lblVita1, lblVita2, lblVita3, lblVita4);
			Turn.setMoneyLbl(lblSoldi1, lblSoldi2, lblSoldi3, lblSoldi4);
			Turn.setComboEquip(comboMano1,comboMano2,comboMano3,comboMano4);
		}
		if(numero == 3) {
			Turn.setCrown(btnK1, btnK2, btnK3, null);
			Turn.setPoints(lblPunti1, lblPunti2, lblPunti3, null);
			Turn.setLifeLbl(lblVita1, lblVita2, lblVita3, null);
			Turn.setMoneyLbl(lblSoldi1, lblSoldi2, lblSoldi3, null);
			Turn.setComboEquip(comboMano1,comboMano2,comboMano3,null);
		}
		if(numero == 2) {
			Turn.setCrown(btnK1, btnK2, null, null);
			Turn.setPoints(lblPunti1, lblPunti2, null, null);
			Turn.setLifeLbl(lblVita1, lblVita2, null, null);
			Turn.setMoneyLbl(lblSoldi1, lblSoldi2, null, null);
			Turn.setComboEquip(comboMano1,comboMano2,null,null);
		}
		
		//imposto l'immagine del king
		if(vec == 0) {
			btnK1.setIcon(new ImageIcon(Game.class.getResource("/res/corona-png.png")));
		}else if(vec == 1) {
			btnK2.setIcon(new ImageIcon(Game.class.getResource("/res/corona-png.png")));
		}else if(vec == 2) {
			btnK3.setIcon(new ImageIcon(Game.class.getResource("/res/corona-png.png")));
		}else if(vec == 3) {
			btnK4.setIcon(new ImageIcon(Game.class.getResource("/res/corona-png.png")));
		}
		Turn.setKing(vec);
		//imposto l'immagine del king
		
		//carte
		btnCarta1 = new JButton("");
		btnCarta1.setBounds(830, 182, 89, 128);
		btnCarta1.setEnabled(false);
		btnCarta1.setIcon(new ImageIcon(Game.class.getResource("/res/31.png")));
		framePlancia.getContentPane().add(btnCarta1);
		
		btnCarta2 = new JButton("");
		btnCarta2.setBounds(830, 337, 89, 128);
		btnCarta2.setEnabled(false);
		btnCarta2.setIcon(new ImageIcon(Game.class.getResource("/res/32.png")));
		framePlancia.getContentPane().add(btnCarta2);
		
		btnCarta3 = new JButton("");
		btnCarta3.setBounds(830, 493, 89, 128);
		btnCarta3.setEnabled(false);
		btnCarta3.setIcon(new ImageIcon(Game.class.getResource("/res/33.png")));
		btnCarta3.setToolTipText("Carta 3");
		framePlancia.getContentPane().add(btnCarta3);	
		
		Turn.setFieldCards(btnCarta1, btnCarta2, btnCarta3);
		//carte
			
		
		Turn.setInfo(info);
		
		Turn.setReachPoint(punti);
		Turn.setnPlayer(numero);
		
		btnLanciaDadi.addActionListener(e->{
			Turn.rollDice();
		});
		}
	
	/**
	 * Method that log events of the game
	 * @param log
	 */
	public static void logger(String log) {
		textLog.setText(textLog.getText() + log + "\n");
	}
	
	/**
	 * Method the clear the log textBox
	 */
	public static void clearLog() {
		textLog.setText("");
	}
	
	/**
	 * Method that disable the play tha lose all life points
	 * @param i
	 */
	public static void dead(int i) {
		if(i == 0) {
			lblMostro1.setIcon(new ImageIcon(Game.class.getResource("/res/dead.png")));
			btnCompraCarta1.setEnabled(false);
			btnK1.setEnabled(false);
			btnFineTurno1.setEnabled(false);
		} 
		if(i == 1) {
			lblMostro2.setIcon(new ImageIcon(Game.class.getResource("/res/dead.png")));
			btnCompraCarta2.setEnabled(false);
			btnK2.setEnabled(false);
			btnFineTurno2.setEnabled(false);
		}
		if(i == 2) {
			lblMostro3.setIcon(new ImageIcon(Game.class.getResource("/res/dead.png")));
			btnCompraCarta3.setEnabled(false);
			btnK3.setEnabled(false);
			btnFineTurno3.setEnabled(false);
		} 
		if(i == 3) {
			lblMostro4.setIcon(new ImageIcon(Game.class.getResource("/res/dead.png")));
			btnCompraCarta4.setEnabled(false);
			btnK4.setEnabled(false);
			btnFineTurno4.setEnabled(false);
		}
	}
	
	/**
	 * Method that return the frame framePlancia
	 * @return
	 */
	public static JFrame getFrame() {
		return framePlancia;
	}
	
}
