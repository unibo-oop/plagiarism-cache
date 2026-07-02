package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import board.Cell;
import board.GameEngine;
import dialog.DialogEndGame;
import enumeration.Action;
import enumeration.GameState;
import interfaces.BoardGameViewInterface;
import other.Chronometer;
import other.Difficult;
import other.Theme;
import record.TableRecord;

/**
 * classe che gestice tutta la parte destra del fram
 * 
 * @author Alessandro
 *
 */
public class BoardGameView extends JFrame implements BoardGameViewInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int bombs;	
	private int remainingBombs;
	
	private int xSize;
	private int ySize;
	
	private JButton buttons[][];
	
	private JButton buttonPlay;
	private JButton buttonDifficult;
	private JButton buttonRecord;
	private JButton buttonClose;
	private JButton buttonRestart;
	private JButton buttonTheme;
	
	private JLabel labelChronometer;
	private JLabel labelBomb;
	
	private Container container;
	
	private JPanel panelLeft = new JPanel();
	private JPanel panelGame = new JPanel();
	private JPanel panelInfo = new JPanel();
	private JPanel panelBoard = new JPanel();
	private PanelRecord panelRecord;
	private PanelDifficult panelDifficult;
	private PanelTheme panelTheme;
	
	private PanelEasyGame panelEasyGame;
	
	private Cell[][] cells;
	
	public GameEngine gameEngine = new GameEngine();
	
	private Chronometer chronometer;
	private Timer timer;
	
	public static TableRecord tableRecordEasy = new TableRecord("Easy");
	public static TableRecord tableRecordMedium = new TableRecord("Medium");
	public static TableRecord tableRecordHard = new TableRecord("Hard");
	
	private static Difficult difficult = new Difficult();
	
	public static Theme theme = new Theme();
	
	/**
	 * Frame principale in cui verrano creati vari panel e resi visibili e invisibili
	 * Avremo un panel fisso(panelLeft) e un panel che invece sar� dinamico (panelGame)
	 * Viene inoltre presa la dimensione dello schermo per gestire tutta la schermata in modo proporzionale
	 * Tutti i colori verrano presi dalla classe statica theme che gestisce i colori in base
	 * al tema selezionato
	 */
	public BoardGameView() {
		super("Campo Minato");
		
		this.bombs = difficult.getBombs();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.xSize = (int) screenSize.getWidth();
		this.ySize = (int) screenSize.getHeight();

		this.startNewGame();
		
		this.container = getContentPane();
		this.container.setLayout(new BorderLayout());
		
		this.createPanelLeft();
		this.createPanelGame();

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * Viene creata la barra laterale, inoltre viene gestito il click sui vari bottoni.
	 * Inoltre sul bottone Gioca viene fatto un controllo, nel caso la difficolta e/o il tema sia cambiato
	 * la partita viene riavviata automaticamente
	 * Come ultima cosa viene controllato il tema per gestire se la scritta dovr� essere chiara o scura
	 */
	private void createPanelLeft() {
	  this.panelLeft.setLayout(new GridBagLayout());
	  this.panelLeft.setPreferredSize(new Dimension(xSize/7,ySize));
		
	  this.panelLeft.setBackground(Color.decode(theme.getFirstColor()));
	  this.panelLeft.setBorder(BorderFactory.createMatteBorder(0,0,0,3, Color.decode("#2f3131")));		
		
		GridBagConstraints c = new GridBagConstraints();
		
		this.buttonPlay = new JButton("Gioca");
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 20, 0);
		this.buttonPlay.setBorder(new LineBorder(Color.decode(theme.getSecondColor()), 3));
		this.buttonPlay.setBackground(Color.decode(theme.getThirdColor()));
		this.buttonPlay.setFocusPainted(false);
		this.buttonPlay.setPreferredSize(new Dimension(120,50));
		this.buttonPlay.setFont(new Font("Arial", Font.BOLD, 18));	
		this.buttonPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!panelBoard.isVisible() && !panelGame.isVisible() && !panelInfo.isVisible()) {
					if(panelRecord != null) {
						panelRecord.setVisible(false);
					}
					if(panelDifficult != null) {
						panelDifficult.setVisible(false);
					}
					if(panelTheme != null) {
						panelTheme.setVisible(false);
					}
					panelBoard.setVisible(true);
					panelGame.setVisible(true);
					panelInfo.setVisible(true);
					if(difficult.isChange() || theme.isThemeChanged()) {
						startNewGame();
					}
				}
			}
		});
		this.panelLeft.add(this.buttonPlay, c);
		
		this.buttonDifficult = new JButton("Difficolta");
		c.gridx = 0;
		c.gridy = 1;
		this.buttonDifficult.setBorder(new LineBorder(Color.decode(theme.getSecondColor()), 3));
		this.buttonDifficult.setBackground(Color.decode(theme.getThirdColor()));
		this.buttonDifficult.setFocusPainted(false);
		this.buttonDifficult.setPreferredSize(new Dimension(120,50));
		this.buttonDifficult.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonDifficult.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				if(panelTheme == null && panelRecord == null) {
					panelBoard.setVisible(false);
					panelGame.setVisible(false);
					panelInfo.setVisible(false);
				} else {
					if(panelTheme == null) {
						if(panelBoard.isVisible() && panelGame.isVisible() && panelInfo.isVisible()
							|| panelRecord.isVisible()) {
							panelBoard.setVisible(false);
							panelGame.setVisible(false);
							panelInfo.setVisible(false);
							panelRecord.setVisible(false);
						}
					} else if(panelRecord == null) {
						if(panelBoard.isVisible() && panelGame.isVisible() && panelInfo.isVisible()
								|| panelTheme.isVisible()) {
								panelBoard.setVisible(false);
								panelGame.setVisible(false);
								panelInfo.setVisible(false);
								panelTheme.setVisible(false);
						}
					} else {
						if(panelBoard.isVisible() && panelGame.isVisible() && panelInfo.isVisible()
								|| panelTheme.isVisible() || panelRecord.isVisible()) {
								panelBoard.setVisible(false);
								panelGame.setVisible(false);
								panelInfo.setVisible(false);
								panelTheme.setVisible(false);
								panelRecord.setVisible(false);
						}
					}
				}
				if(panelDifficult == null) {
					panelDifficult = new PanelDifficult(difficult, xSize-(xSize/7), ySize);
					container.add(panelDifficult, BorderLayout.CENTER);
				}
				panelDifficult.setVisible(true);
			}
		});
		this.panelLeft.add(this.buttonDifficult, c);
		
		this.buttonRecord = new JButton("Record");
		c.gridx = 0;
		c.gridy = 2;
		this.buttonRecord.setBorder(new LineBorder(Color.decode(theme.getSecondColor()), 3));
		this.buttonRecord.setBackground(Color.decode(theme.getThirdColor()));
		this.buttonRecord.setFocusPainted(false);
		this.buttonRecord.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonRecord.setPreferredSize(new Dimension(120,50));
		this.buttonRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				if(panelDifficult == null && panelTheme == null) {
					panelBoard.setVisible(false);
					panelGame.setVisible(false);
					panelInfo.setVisible(false);
				} else {
					if(panelDifficult == null) {
						if(panelBoard.isVisible() && panelGame.isVisible() && panelInfo.isVisible()
							|| panelTheme.isVisible()) {
							panelBoard.setVisible(false);
							panelGame.setVisible(false);
							panelInfo.setVisible(false);
							panelTheme.setVisible(false);
						}
					} else if(panelTheme == null) {
						if(panelBoard.isVisible() && panelGame.isVisible() && panelInfo.isVisible()
								|| panelDifficult.isVisible()) {
								panelBoard.setVisible(false);
								panelGame.setVisible(false);
								panelInfo.setVisible(false);
								panelDifficult.setVisible(false);
						}
					} else {
						if(panelBoard.isVisible() && panelGame.isVisible() && panelInfo.isVisible()
								|| panelDifficult.isVisible() || panelTheme.isVisible()) {
								panelBoard.setVisible(false);
								panelGame.setVisible(false);
								panelInfo.setVisible(false);
								panelDifficult.setVisible(false);
								panelTheme.setVisible(false);
						}
					}
				}
				if(panelRecord == null) {
					panelRecord = new PanelRecord(xSize-(xSize/7), ySize);
					container.add(panelRecord, BorderLayout.CENTER);
				}
				panelRecord.setVisible(true);
			}
		});
		this.panelLeft.add(this.buttonRecord, c);
		
		this.buttonTheme = new JButton("Temi");
		c.gridx = 0;
		c.gridy = 3;
		this.buttonTheme.setBorder(new LineBorder(Color.decode(theme.getSecondColor()), 3));
		this.buttonTheme.setBackground(Color.decode(theme.getThirdColor()));
		this.buttonTheme.setFocusPainted(false);
		this.buttonTheme.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonTheme.setPreferredSize(new Dimension(120,50));
		this.buttonTheme.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(panelDifficult == null && panelRecord == null) {
					panelBoard.setVisible(false);
					panelGame.setVisible(false);
					panelInfo.setVisible(false);
				} else {
					if(panelDifficult == null) {
						if(panelBoard.isVisible() && panelGame.isVisible() && panelInfo.isVisible()
							|| panelRecord.isVisible()) {
							panelBoard.setVisible(false);
							panelGame.setVisible(false);
							panelInfo.setVisible(false);
							panelRecord.setVisible(false);
						}
					} else if(panelRecord == null) {
						if(panelBoard.isVisible() && panelGame.isVisible() && panelInfo.isVisible()
								|| panelDifficult.isVisible()) {
								panelBoard.setVisible(false);
								panelGame.setVisible(false);
								panelInfo.setVisible(false);
								panelDifficult.setVisible(false);
						}
					} else {
						if(panelBoard.isVisible() && panelGame.isVisible() && panelInfo.isVisible()
								|| panelDifficult.isVisible() || panelRecord.isVisible()) {
								panelBoard.setVisible(false);
								panelGame.setVisible(false);
								panelInfo.setVisible(false);
								panelDifficult.setVisible(false);
								panelRecord.setVisible(false);
						}
					}
				}
				if(panelTheme == null) {
					panelTheme = new PanelTheme(xSize-(xSize/7), ySize);
					container.add(panelTheme, BorderLayout.CENTER);
				}
				panelTheme.setVisible(true);
			}
			
		});
		this.panelLeft.add(this.buttonTheme, c);
		
		this.buttonClose = new JButton("Esci");
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(50, 0, 0, 0);
		this.buttonClose.setBorder(new LineBorder(Color.decode(theme.getSecondColor()), 3));
		this.buttonClose.setBackground(Color.decode(theme.getThirdColor()));
		this.buttonClose.setFocusPainted(false);
		this.buttonClose.setPreferredSize(new Dimension(120,50));
		this.buttonClose.setFont(new Font("Arial", Font.BOLD, 18));
		this.buttonClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	
			}
		});
		this.panelLeft.add(this.buttonClose, c);
		
		if(theme.getActuallyTheme() == "Black") {
		  this.buttonPlay.setForeground(Color.WHITE);
		  this.buttonDifficult.setForeground(Color.WHITE);
		  this.buttonRecord.setForeground(Color.WHITE);
		  this.buttonTheme.setForeground(Color.WHITE);
		  this.buttonClose.setForeground(Color.WHITE);
		} else {
		  this.buttonPlay.setForeground(Color.DARK_GRAY);
		  this.buttonDifficult.setForeground(Color.DARK_GRAY);
		  this.buttonRecord.setForeground(Color.DARK_GRAY);
		  this.buttonTheme.setForeground(Color.DARK_GRAY);
		  this.buttonClose.setForeground(Color.DARK_GRAY);
		}
		
		this.container.add(this.panelLeft, BorderLayout.WEST);
	}
	
	/**
	 * panel dinamico che cambia in base alle azioni sul panel statico
	 * all'avvio dell'applicazione parte il panel di gioco con il panelInfo che contiene cronometro, bombe rimaste
	 * e bottone riavvia.
	 * Anche il panel che contiene la Board.
	 */
	private void createPanelGame() {
	  this.panelGame.setLayout(new BorderLayout());
	  this.panelGame.setPreferredSize(new Dimension(xSize-(xSize/7),ySize));
		
	  this.createButtonsInfo();
	  this.createButtonsBoard();
		
	  this.container.add(this.panelGame, BorderLayout.CENTER);
	}
	
	/**
	 * Viene creato l'oggetto panelEasyGame e viene restituito il riferimento ai bottoni e inserito in una variabile
	 * della classe BoardGameView
	 */
	private void createButtonsBoard() {
	  this.panelEasyGame = new PanelEasyGame(difficult, xSize-(xSize/7), ySize-(ySize/14));
		this.buttons = this.panelEasyGame.getButtons();

		this.panelGame.add(this.panelEasyGame, BorderLayout.CENTER);
	}
	
	/**
	 * Panel che contiene cronometro, bottone riavvia partita e bombe rimaste
	 */
	private void createButtonsInfo() {
	  this.panelInfo.setLayout(new GridBagLayout());
	  this.panelInfo.setBackground(Color.decode(theme.getThirdColor()));
	  this.panelInfo.setPreferredSize(new Dimension(xSize-(xSize/7),ySize/14));
		
		GridBagConstraints gb = new GridBagConstraints();
		
		this.buttonRestart = new JButton();
		gb.gridx = 1;
		gb.gridy = 0;
		gb.insets = new Insets(0,15,0,15);
		this.buttonRestart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				startNewGame();
			}
		});
		try {
			Image img = ImageIO.read(this.getClass().getResourceAsStream("/images/restart.png"));
			Dimension size = buttonRestart.getSize();
			Insets insets = buttonRestart.getInsets();
			size.width -= insets.left + insets.right;
      size.height -= insets.top + insets.bottom;
      Image scaled = img.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
      this.buttonRestart.setIcon(new ImageIcon(scaled));
		} catch(Exception e) {
			System.out.println(e);
		}
		this.buttonRestart.setPreferredSize(new Dimension(40,40));
		this.buttonRestart.setFocusPainted(false);
		this.panelInfo.add(this.buttonRestart, gb);
		
		this.labelBomb = new JLabel("Bombe rimaste : " + Integer.toString(this.remainingBombs));
		gb.gridx = 0;
		gb.gridy = 0;
		this.labelBomb.setFont(new Font("Arial", Font.PLAIN, 20));
		this.panelInfo.add(this.labelBomb, gb);

		gb.gridx = 2;
		gb.gridy = 0;
		this.labelChronometer.setFont(new Font("Arial", Font.PLAIN, 20));
		this.panelInfo.add(this.labelChronometer, gb);
		
		if(theme.getActuallyTheme() == "Black") {
		  this.labelBomb.setForeground(Color.WHITE);
		  this.labelChronometer.setForeground(Color.WHITE);
		} else {
		  this.labelBomb.setForeground(Color.DARK_GRAY);
		  this.labelChronometer.setForeground(Color.DARK_GRAY);
		}
		
		this.panelGame.add(this.panelInfo, BorderLayout.NORTH);
	}
	
	/**
	 * funzione che gestisce l'avvio del gioco, se non � ancora partito viene creato l'oggetto gameEngine
	 * Se la partita sta andando viene controllato se viene cambiata difficolta o tema e nel caso riavviata
	 * la partita e aggiornata la View
	 */
	private void startNewGame() {
		if(this.gameEngine.gameState == GameState.STOP) {
		  this.gameEngine.newGame(difficult.getRows(), difficult.getColumns(), difficult.getBombs());
		  this.cells = this.gameEngine.getCells();
		  this.remainingBombs = this.bombs;
		} else {
			if(difficult.isChange() || theme.isThemeChanged()) {
				difficult.setNotChange();
				this.panelEasyGame.removeAll();
				this.panelEasyGame.updateUI();
				this.panelGame.remove(panelEasyGame);
				this.panelEasyGame = null;
				this.panelEasyGame = new PanelEasyGame(difficult, xSize-(xSize/7), ySize-(ySize/14));
				this.buttons = panelEasyGame.getButtons();
				this.panelGame.add(panelEasyGame);
				if(theme.isThemeChanged()) {
					theme.themeNotChanged();
				}
			}
			this.gameEngine = null;
			this.gameEngine = new GameEngine();
			this.gameEngine.newGame(difficult.getRows(), difficult.getColumns(), difficult.getBombs());
			this.cells = gameEngine.getCells();
			this.remainingBombs = difficult.getBombs();
			this.clearView();
		}
		this.startChronometer();
	}
	
	/**
	 * funzione che gestisce il gioco e la view.
	 * ad ogni click viene controllato da che bottono proviene
	 * al click su una cella viene chiamato il GameEngine che ci restituir� uno stato, nel caso il gioco proceda verranno
	 * svelato tutte le celle rivelate
	 * In caso di sconfitta viene richiamato un Dialog con i valori di partita persa e no record (false, false)
	 * In caso di vittoria verr� chiamato un Dialog con valori di vittoria (true) e il secondo valore in base
	 * a se il tempo � un record o no
	 * 
	 * @param object
	 *     l'oggetto su cui � avvenuto l'evento
	 * @param action
	 *     l'azione da compiere sull'oggetto
	 */
	public void clickView(Object object, Action action) {
		for(int x = 0; x < difficult.getRows(); x++) {
			for(int y = 0; y < difficult.getColumns(); y++) {
				if(this.buttons[x][y] == object) {
					if(action == Action.PLAY) {
						if(!this.cells[x][y].isFlag()) {
						  this.gameEngine.click(this.cells[x][y], action);
							switch(this.gameEngine.gameState) {
							case ONGOING :
								for(int i = 0; i < difficult.getRows(); i++) {
									for(int j = 0; j < difficult.getColumns(); j++) {
										if(this.cells[i][j].isRevealed()) {
										  this.revealButton(i, j);
										}
									}
								}
								break;
							case STOP :
								break;
							case WON : 
							  try {
							    InputStream is = getClass().getResourceAsStream("/sounds/Win.wav");
							    AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
							    Clip clip = AudioSystem.getClip();
							    clip.open(ais);
							    clip.start();
			          
							    ais.close();
							    is.close();
							  } catch(LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
							    e1.printStackTrace();
							  }
								for(int i = 0; i < difficult.getRows(); i++) {
									for(int j = 0; j < difficult.getColumns(); j++) {
										if(this.cells[i][j].isBomb()) {
										  this.revealButton(x, y);
										  this.buttons[i][j].setBackground(Color.GREEN);
										}
										this.buttons[i][j].setEnabled(false);
									}
								}
								this.timer.cancel();
								
								switch(difficult.getDifficult()) {
								case "Easy":
									if(tableRecordEasy.isRecord(this.chronometer.getTime())) {
										DialogEndGame dialogEndGame = new DialogEndGame(this, this.chronometer.getTime(), true, true);
										dialogEndGame.setVisible(true);
										tableRecordEasy.addRecord(dialogEndGame.getStringName(), this.chronometer.getTime());
									} else {
										DialogEndGame dialogEndGame = new DialogEndGame(this, this.chronometer.getTime(), true, false);
										dialogEndGame.setVisible(true);
									}
									break;
								case "Medium":
									if(tableRecordMedium.isRecord(this.chronometer.getTime())) {
										DialogEndGame dialogEndGame = new DialogEndGame(this, this.chronometer.getTime(), true, true);
										dialogEndGame.setVisible(true);
										tableRecordMedium.addRecord(dialogEndGame.getStringName(), this.chronometer.getTime());
										
									} else {
										DialogEndGame dialogEndGame = new DialogEndGame(this, this.chronometer.getTime(), true, false);
										dialogEndGame.setVisible(true);
									}
									break;
								case "Hard":
									if(tableRecordHard.isRecord(this.chronometer.getTime())) {
										DialogEndGame dialogEndGame = new DialogEndGame(this, this.chronometer.getTime(), true, true);
										dialogEndGame.setVisible(true);
										tableRecordHard.addRecord(dialogEndGame.getStringName(), this.chronometer.getTime());
									} else {
										DialogEndGame dialogEndGame = new DialogEndGame(this, this.chronometer.getTime(), true, false);
										dialogEndGame.setVisible(true);
									}
									break;
								default:
									DialogEndGame dialogEndGame = new DialogEndGame(this, this.chronometer.getTime(), true, false);
									dialogEndGame.setVisible(true);
									break;
								}
								if(this.panelRecord != null) {
								  this.panelRecord.updateView();
								}
								break;
							case LOST :
							  try {
                  InputStream is = getClass().getResourceAsStream("/sounds/Bomba.wav");
                  AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
                  Clip clip = AudioSystem.getClip();
                  clip.open(ais);
                  clip.start();
                
                  ais.close();
                  is.close();
                } catch(LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
                  e1.printStackTrace();
                }
								for(int i = 0; i < difficult.getRows(); i++) {
									for(int j = 0; j < difficult.getColumns(); j++) {
										if(this.cells[i][j].isBomb()) {
										  this.buttons[i][j].setBackground(Color.red);
										}
										this.buttons[i][j].setEnabled(false);
									}
								}
								this.timer.cancel();
								DialogEndGame dialogEndGame = new DialogEndGame(this, this.chronometer.getTime(), false, false);
								dialogEndGame.setVisible(true);
								break;
							default:
								break;
							}
						}
					} else {
						if(!this.cells[x][y].isFlag()) {
							if(this.remainingBombs > 0) {
								if(!this.cells[x][y].isRevealed()) {
								  this.gameEngine.click(this.cells[x][y], Action.SET_FLAG);
									try {
										Image img = ImageIO.read(this.getClass().getResourceAsStream("/images/flag.png"));
										Dimension size = this.buttons[x][y].getSize();
										Insets insets = this.buttons[x][y].getInsets();
										size.width -= insets.left + insets.right;
			              size.height -= insets.top + insets.bottom;
			              Image scaled = img.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
			              this.buttons[x][y].setIcon(new ImageIcon(scaled));
									} catch(Exception e) {
										System.out.println(e);
									}
									this.labelBomb.setText("Bombe rimaste : " + Integer.toString(--this.remainingBombs));
								}
							}
						} else {
						  this.gameEngine.click(cells[x][y], Action.REMOVE_FLAG);
						  this.buttons[x][y].setText("");
						  this.buttons[x][y].setIcon(null);
						  this.labelBomb.setText("Bombe rimaste : " + Integer.toString(++this.remainingBombs));
						}
					}
				}
			}
		}
	}
	
	/**
	 * funzione che rivela le celle a livello grafico
	 * 
	 * @param i
	 *     riga della cella
	 * @param j
	 *     colonna della cella
	 */
	private void revealButton(Integer i, Integer j) {
		switch(this.cells[i][j].getNumberNeighborBombs()) {
			case 1:
			  this.buttons[i][j].setText("1");
			  this.buttons[i][j].setBackground(Color.decode("#f8f1e5"));
			break;
			case 2:
			  this.buttons[i][j].setText("2");
			  this.buttons[i][j].setBackground(Color.decode("#f8f1e5"));
			break;
			case 3:
			  this.buttons[i][j].setText("3");
			  this.buttons[i][j].setBackground(Color.decode("#f8f1e5"));
			break;
			case 4:
			  this.buttons[i][j].setText("4");
			  this.buttons[i][j].setBackground(Color.decode("#f8f1e5"));
			break;
			case 5:
			  this.buttons[i][j].setText("5");
			  this.buttons[i][j].setBackground(Color.decode("#f8f1e5"));
			break;
			case 6:
			  this.buttons[i][j].setText("6");
			  this.buttons[i][j].setBackground(Color.decode("#f8f1e5"));
			break;
			case 7:
			  this.buttons[i][j].setText("7");
			  this.buttons[i][j].setBackground(Color.decode("#f8f1e5"));
			break;
			case 8:
			  this.buttons[i][j].setText("8");
			  this.buttons[i][j].setBackground(Color.decode("#f8f1e5"));
			break;
			default:
			  this.buttons[i][j].setBackground(Color.decode("#f8f1e5"));
			break;
			}
	}
	
	/**
	 * funzione che pulisce la view in caso di riavvio o cambio difficolta
	 */
	private void clearView() {
		for(int i = 0; i < this.buttons.length; i++) {
			for(int j = 0; j < this.buttons[i].length; j++) {
			  this.buttons[i][j].setBorder(new LineBorder(Color.decode(theme.getSecondColor()), 3));
			  this.buttons[i][j].setBackground(Color.decode(theme.getFirstColor()));
			  this.buttons[i][j].setIcon(null);
			  this.buttons[i][j].setText("");
			  this.buttons[i][j].setEnabled(true);
			}
		}
		labelBomb.setText("Bombe rimaste : " + Integer.toString(remainingBombs));
	}
	
	/**
	 * funzione che gestisce un cronometro che aggiorna una label ogni secondo
	 */
	private void startChronometer() {
		if(this.chronometer == null) {
		  this.labelChronometer = new JLabel("Timer : 0 : 0 : 0");
		  this.chronometer = new Chronometer(this.labelChronometer);
		  this.timer = new Timer();
		  this.timer.schedule(chronometer, 1000, 1000);
		} else {
		  this.labelChronometer.setText("Timer : 0 : 0 : 0");
		  this.chronometer = null;
		  this.timer.cancel();
		  this.chronometer = new Chronometer(this.labelChronometer);
		  this.timer = new Timer();
		  this.timer.schedule(this.chronometer, 1000, 1000);
		}	
	}
	
	/**
	 * Funzione che aggionra tutto il frame nel caso in cui venga cambiato il tema
	 */
	public void updateThemeView() {
	  this.panelLeft.setBackground(Color.decode(theme.getFirstColor()));
		
	  this.buttonPlay.setBorder(new LineBorder(Color.decode(theme.getSecondColor()), 3));
	  this.buttonPlay.setBackground(Color.decode(theme.getThirdColor()));
		
	  this.buttonDifficult.setBorder(new LineBorder(Color.decode(theme.getSecondColor()), 3));
	  this.buttonDifficult.setBackground(Color.decode(theme.getThirdColor()));
		
	  this.buttonRecord.setBorder(new LineBorder(Color.decode(theme.getSecondColor()), 3));
	  this.buttonRecord.setBackground(Color.decode(theme.getThirdColor()));
		
	  this.buttonTheme.setBorder(new LineBorder(Color.decode(theme.getSecondColor()), 3));
	  this.buttonTheme.setBackground(Color.decode(theme.getThirdColor()));
		
	  this.buttonClose.setBorder(new LineBorder(Color.decode(theme.getSecondColor()), 3));
	  this.buttonClose.setBackground(Color.decode(theme.getThirdColor()));
		
	  this.panelInfo.setBackground(Color.decode(theme.getThirdColor()));
		
		if(theme.getActuallyTheme() == "Black") {
		  this.buttonPlay.setForeground(Color.WHITE);
		  this.buttonDifficult.setForeground(Color.WHITE);
		  this.buttonRecord.setForeground(Color.WHITE);
		  this.buttonTheme.setForeground(Color.WHITE);
		  this.buttonClose.setForeground(Color.WHITE);
		  this.labelChronometer.setForeground(Color.WHITE);
		  this.labelBomb.setForeground(Color.WHITE);
		} else {
		  this.buttonPlay.setForeground(Color.DARK_GRAY);
		  this.buttonDifficult.setForeground(Color.DARK_GRAY);
		  this.buttonRecord.setForeground(Color.DARK_GRAY);
		  this.buttonTheme.setForeground(Color.DARK_GRAY);
		  this.buttonClose.setForeground(Color.DARK_GRAY);
		  this.labelChronometer.setForeground(Color.DARK_GRAY);
		  this.labelBomb.setForeground(Color.DARK_GRAY);
		}
		if(this.panelDifficult != null) {
		  this.panelDifficult.updateThemeView();
		}
		if(this.panelRecord != null) {
		  this.panelRecord.updateThemeView();
		}
	}
}
