package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.FightController;


public class FightStartedPanelImpl extends JFrame implements FightStartedPanel{

	private static final long serialVersionUID = 1L;
	private FightController fightController;
	public static Timer timerFight;
	static Timer timerPausa ;
	private JPanel pane = new JPanel();
	
	private final JButton start = new JButton("Start");
	private final JButton pause = new JButton("Pause");
	private final JLabel instructions = new JLabel("?");
	private final JButton undo = new JButton("Undo");
	private final JButton onePointBlue = new JButton("+1");
	private final JButton onePointRed = new JButton("+1");
	private final JButton threePointBlue = new JButton("+3");
	private final JButton threePointRed = new JButton("+3");
	private final JButton warningOneRed = new JButton("X");
	private final JLabel[] warningRed = new JLabel[10];
	private final JButton warningOneBlue = new JButton("X");
	private final JButton koBlue = new JButton("KO");
	private final JButton koRed = new JButton("KO");
	private final JLabel[] warningBlue = new JLabel[10];
	private final JLabel secondsFightText = new JLabel("01:00");
	private final JLabel secondsPauseText = new JLabel("Break: 00:15");
	private final JLabel round = new JLabel("    R - ");
	private final JLabel puntiBluImage = new JLabel(new ImageIcon(FightStartedPanelImpl.class.getResource("/puntitaekwondo/0_blue.png")));
	private final JLabel puntiBluImageDecine = new JLabel(new ImageIcon(FightStartedPanelImpl.class.getResource("/puntitaekwondo/0_blue.png")));
	private final JLabel puntiRossoImageDecine = new JLabel(new ImageIcon(FightStartedPanelImpl.class.getResource("/puntitaekwondo/0_red.png")));
	private final JLabel puntiRossoImage = new JLabel(new ImageIcon(FightStartedPanelImpl.class.getResource("/puntitaekwondo/0_red.png")));
	private final JLabel c1 = new JLabel();
	private final JLabel c2 = new JLabel();
	private Integer bluePoints=0;
	private Integer redPoints=0;
	
	private Integer redWarnings=0;
	private Integer blueWarnings=0;

	private Integer secondsFight;
	private Integer nRound;
	
	private String cognome1;
	private String cognome2;
	private String risultato;
	int xBlue = 1125;
	int xRed = 10;


	private File beep1 = new File(FightStartedPanelImpl.class.getResource("/audio/beep1.wav").getFile());
	private File beep2 = new File(FightStartedPanelImpl.class.getResource("/audio/beep2.wav").getFile());
	
	private List<Integer> opDone = new ArrayList<>();
	
	TimerTask timerTaskFight;
	
	
	public FightStartedPanelImpl(final String cognome1, final String cognome2){	
		
		this.cognome1 = cognome1;
		this.cognome2 = cognome2;
		this.c1.setText("                        " + this.cognome1);
		this.c2.setText("                     " + this.cognome2);
		this.setVisible(true);
		this.setResizable(false);
		this.setBounds(100, 100, 1200, 700);
		pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(pane);
		pane.setLayout(null);
		pane.setBackground(new Color(0, 0, 0));
		
		start.setForeground(new Color(0, 0, 0));
		start.setFont(new Font("Arial", Font.BOLD, 20));
		start.setBounds(10, 10 , 150, 50);
		pane.add(start);
		
		pause.setForeground(new Color(0, 0, 0));
		pause.setFont(new Font("Arial", Font.BOLD, 20));
		pause.setBounds(163, 10 , 150, 50);
		pane.add(pause);
		
		instructions.setForeground(new Color(255,255,255));
		instructions.setFont(new Font("Arial", Font.BOLD, 20));
		instructions.setBounds(330, 10 , 20, 50);
		instructions.setOpaque(true);
		instructions.setBackground(new Color(0, 0, 0));
		pane.add(instructions);
		
		round.setFont(new Font("Arial", Font.BOLD, 20));
		round.setBackground(new Color(255, 165, 0));
		round.setForeground(new Color(0, 0, 0));
		round.setOpaque(true);
		round.setBounds(545, 520, 100, 100);
		pane.add(round);
		
		undo.setForeground(new Color(0, 0, 0));
		undo.setFont(new Font("Arial", Font.BOLD, 20));
		undo.setBounds(1035, 10 , 150, 50);
		pane.add(undo);	
		
		c1.setForeground(new Color(255, 255, 255));
		c1.setFont(new Font("Arial", Font.BOLD, 30));
		c1.setBounds(10, 65, 500, 40);
		c1.setOpaque(true);
		c1.setBackground(new Color(255, 0, 0));
		pane.add(c1);
		
		c2.setForeground(new Color(255, 255, 255));
		c2.setFont(new Font("Arial", Font.BOLD, 30));
		c2.setBounds(700, 65, 485, 40);
		c2.setOpaque(true);
		c2.setBackground(new Color(0, 0, 255));
		pane.add(c2);
		
		secondsFightText.setFont(new Font("Arial", Font.BOLD, 50));
		secondsFightText.setForeground(new Color(255, 255, 255));
		secondsFightText.setBounds(530, 10 , 150, 50);
		pane.add(secondsFightText);
		
		secondsPauseText.setFont(new Font("Arial", Font.BOLD, 30));
		secondsPauseText.setForeground(new Color(255, 255, 255));
		secondsPauseText.setBounds(515, 60 , 200, 50);
		pane.add(secondsPauseText);
		
		puntiRossoImage.setBounds(390, 100 , 200, 420);
		pane.add(puntiRossoImage);
		puntiRossoImageDecine.setBounds(190, 100 , 200, 420);
		pane.add(puntiRossoImageDecine);
		
		puntiBluImage.setBounds(800, 100 , 200, 420);
		pane.add(puntiBluImage);
		puntiBluImageDecine.setBounds(600, 100 , 200, 420);
		pane.add(puntiBluImageDecine);
		
		koRed.setForeground(new Color(0, 0, 0));
		koRed.setFont(new Font("Arial", Font.BOLD, 20));
		koRed.setBounds(90, 520 , 100, 60);
		pane.add(koRed);
		
		onePointRed.setForeground(new Color(0, 0, 0));
		onePointRed.setFont(new Font("Arial", Font.BOLD, 20));
		onePointRed.setBounds(190, 520 , 100, 60);
		pane.add(onePointRed);
		
		threePointRed.setForeground(new Color(0, 0, 0));
		threePointRed.setFont(new Font("Arial", Font.BOLD, 20));
		threePointRed.setBounds(290, 520 , 100, 60);
		pane.add(threePointRed);
		
		warningOneBlue.setForeground(new Color(0, 0, 0));
		warningOneBlue.setFont(new Font("Arial", Font.BOLD, 20));
		warningOneBlue.setBounds(700, 520 , 100, 60);
		pane.add(warningOneBlue);
		
		threePointBlue.setForeground(new Color(0, 0, 0));
		threePointBlue.setFont(new Font("Arial", Font.BOLD, 20));
		threePointBlue.setBounds(800, 520 , 100, 60);
		pane.add(threePointBlue);
		
		onePointBlue.setForeground(new Color(0, 0, 0));
		onePointBlue.setFont(new Font("Arial", Font.BOLD, 20));
		onePointBlue.setBounds(900, 520 , 100, 60);
		pane.add(onePointBlue);
		
		koBlue.setForeground(new Color(0, 0, 0));
		koBlue.setFont(new Font("Arial", Font.BOLD, 20));
		koBlue.setBounds(1000, 520 , 100, 60);
		pane.add(koBlue);
		
		warningOneRed.setForeground(new Color(0, 0, 0));
		warningOneRed.setFont(new Font("Arial", Font.BOLD, 20));
		warningOneRed.setBounds(390, 520 , 100, 60);
		pane.add(warningOneRed);
						
		this.pause.setEnabled(false);
		this.onePointBlue.setEnabled(false);
		this.onePointRed.setEnabled(false);
		this.threePointBlue.setEnabled(false);
		this.threePointRed.setEnabled(false);
		this.koBlue.setEnabled(false);
		this.koRed.setEnabled(false);
		this.warningOneBlue.setEnabled(false);
		this.warningOneRed.setEnabled(false);
		this.instructions.addMouseListener(new LabelAdapter());
		this.secondsFight = 61;
		this.nRound = 0;
		

	    
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fightController.playSound(beep1);	
				secondsPauseText.setText("Break: 00:15");
				timerFight = new Timer();
				timerTaskFight = new TimerTask(){
					Integer secondi = secondsFight;
					@Override
					public void run() {
						if(secondi>60){
							secondi=secondi-60;
							if(secondi<11)
								secondsFightText.setText("01:0"+--secondi);
							else
								secondsFightText.setText("01:"+--secondi);			
							secondi=secondi+60;
						}else if(secondi<11){
							secondsFightText.setText("00:0"+--secondi);
						}else{
							secondsFightText.setText("00:"+--secondi);
						}
						if(secondi<=5 && secondi>=1){
							fightController.playSound(beep2);
						}
						if(nRound==3){
							
						
							if(bluePoints > redPoints+11){
								risultato = cognome2+" vince per differenza punti. "+ bluePoints+"-"+redPoints;
								endFightMessage(cognome2+" vince per differenza punti.");
							    
							}else if(bluePoints+11  < redPoints){
								
								risultato = cognome1+" vince per differenza punti. "+ bluePoints+"-"+redPoints;
							    endFightMessage(cognome1+" vince per differenza punti.");
							}
						}
						if(nRound==3 && secondi==0){
							if(bluePoints > redPoints){
								risultato= cognome2+" vince con un punteggio di "+ bluePoints+"-"+redPoints;
								endFightMessage(cognome2+" vince con un punteggio di "+ bluePoints+"-"+redPoints);
							}else if(bluePoints < redPoints){
								risultato=cognome1+" vince con un punteggio di "+ redPoints+"-"+bluePoints;
								endFightMessage(cognome1+" vince con un punteggio di "+ redPoints+"-"+bluePoints);
							}else if(bluePoints == redPoints){
								risultato="Pareggio";
								endFightMessage("Pareggio");
							}
						}
						if(secondi==0){
							
							if(bluePoints > redPoints+11 && nRound==2){
								risultato = cognome2+" vince per differenza punti. "+ bluePoints+"-"+redPoints;
								endFightMessage(cognome2+" vince per differenza punti.");
							    
							}else if(bluePoints+11  < redPoints && nRound==2){
								
								risultato = cognome1+" vince per differenza punti. "+ bluePoints+"-"+redPoints;
							    endFightMessage(cognome1+" vince per differenza punti.");

							}
							fightController.playSound(beep1);
							timerPausa = new Timer();
							TimerTask timerTaskPausa = new TimerTask(){
								
								Integer secondiPausa=15;
								
								@Override
								public void run() {
									
										if(secondiPausa > 0){
											if(secondiPausa<10)
												secondsPauseText.setText("Break: 00:0" + secondiPausa);	
											else
												secondsPauseText.setText("Break: 00:" + secondiPausa);		
											secondiPausa--;	
											start.setEnabled(false);
											pause.setEnabled(false);
											onePointBlue.setEnabled(false);
											onePointRed.setEnabled(false);
											warningOneBlue.setEnabled(false);
											warningOneRed.setEnabled(false);
											threePointBlue.setEnabled(false);
											threePointRed.setEnabled(false);
											koBlue.setEnabled(false);
											koRed.setEnabled(false);
										}else {
											secondsPauseText.setText("Break: 00:0" + secondiPausa);		
											start.setEnabled(true);
											start.setText("Start");
											pause.setEnabled(false);												
											this.cancel();
										}
									}
							};
							timerPausa.scheduleAtFixedRate(timerTaskPausa, 0, 1000);
							secondi = 6;	
							round.setText("    R - "+ ++nRound);
							this.cancel();
							
						}
						
						secondsFight = secondi;					
					}
				};		
				
				timerFight.scheduleAtFixedRate(timerTaskFight, 0, 1000);
				
				start.setEnabled(false);
				pause.setEnabled(true);	
				start.setText("Resume");
				warningOneBlue.setEnabled(true);
				warningOneRed.setEnabled(true);
				onePointBlue.setEnabled(true);
				onePointRed.setEnabled(true);
				threePointBlue.setEnabled(true);
				threePointRed.setEnabled(true);
				koBlue.setEnabled(true);
				koRed.setEnabled(true);
			}
		});
		
		pause.addActionListener(e->{
			
			pause.setEnabled(false);			
			start.setEnabled(true);
			timerFight.cancel();
		});	
		
		onePointBlue.addActionListener(e->{
			
			bluePoints++;
			if(bluePoints<99){
				if(bluePoints<10){
					puntiBluImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreBlue(bluePoints)[0])));
				}else{
					puntiBluImageDecine.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreBlue(bluePoints)[0])));
					puntiBluImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreBlue(bluePoints)[1])));
	
				}
				opDone.add(0);
			}
		});
		
		onePointRed.addActionListener(e->{
			
			redPoints++;
			if(redPoints<99){
				if(redPoints<10){
					puntiRossoImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreRed(redPoints)[0])));
				}else{
					puntiRossoImageDecine.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreRed(redPoints)[0])));
					puntiRossoImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreRed(redPoints)[1])));
					
				}
				opDone.add(1);
			}
		});
		
		threePointBlue.addActionListener(e->{
			bluePoints+=3;
			if(bluePoints<99){
				if(bluePoints<10){
					puntiBluImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreBlue(bluePoints)[0])));
				}else{
					puntiBluImageDecine.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreBlue(bluePoints)[0])));
					puntiBluImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreBlue(bluePoints)[1])));
	
				}
				
				opDone.add(2);	
			}
		});	
		
		threePointRed.addActionListener(e->{
			
			redPoints+=3;
			if(redPoints<99){
				if(redPoints<10){
					puntiRossoImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreRed(redPoints)[0])));
				}else{
					puntiRossoImageDecine.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreRed(redPoints)[0])));
					puntiRossoImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreRed(redPoints)[1])));
					
				}
				opDone.add(3);
			}
		});
		
		warningOneRed.addActionListener(e->{
						
			if(redWarnings<10){
				redWarnings++;
				 if(redWarnings==10){
					 
					 	risultato = cognome1+" 10 warning. "+cognome2+" vince.";
						endFightMessage(cognome1+" 10 warning."+cognome2+" vince");
						

				 }
				if(redWarnings%2==0){
					pane.remove(warningRed[redWarnings-1]);
					warningRed[redWarnings-1] = new JLabel(new ImageIcon(FightStartedPanelImpl.class.getResource(fightController.getWarningRed(2))));
					warningRed[redWarnings-1].setBounds(xRed, 570, 60, 110);
					pane.add(warningRed[redWarnings-1]);
					xRed = xRed + 63;
				}else {	
					warningRed[redWarnings] = new JLabel(new ImageIcon(FightStartedPanelImpl.class.getResource(fightController.getWarningRed(1))));
					warningRed[redWarnings].setBounds(xRed, 570, 60, 110);
					pane.add(warningRed[redWarnings]);	
					
				}
				SwingUtilities.updateComponentTreeUI(this);	
				opDone.add(4);
			}

		});
		
		warningOneBlue.addActionListener(e->{
			
			if(blueWarnings<10){
				blueWarnings++;
				if(blueWarnings==10){
					risultato = cognome2+" 10 warning."+cognome1+" vince. ";

					endFightMessage(cognome2+" 10 warning."+cognome2+" vince");
					
				}
				if(blueWarnings%2==0){	
					pane.remove(warningBlue[blueWarnings-1]);
					warningBlue[blueWarnings-1] = new JLabel(new ImageIcon(FightStartedPanelImpl.class.getResource(fightController.getWarningBlue(2))));
					warningBlue[blueWarnings-1].setBounds(xBlue, 570, 60, 110);
					pane.add(warningBlue[blueWarnings-1]);	
					xBlue = xBlue - 63;
				}else{
					warningBlue[blueWarnings] = new JLabel(new ImageIcon(FightStartedPanelImpl.class.getResource(fightController.getWarningBlue(1))));
					pane.add(warningBlue[blueWarnings]);	
					warningBlue[blueWarnings].setBounds(xBlue, 570, 60, 110);
					
				}
				SwingUtilities.updateComponentTreeUI(this);	
				opDone.add(5);
			}

			
		});
		koBlue.addActionListener(e->{
			
			risultato =cognome2+" vince per K.O";
			endFightMessage(cognome2+" vince per K.O");
			
		});
		
		koRed.addActionListener(e->{
			risultato = cognome1+" vince per K.O";
			endFightMessage(cognome1+" vince per K.O");
			
		});
		undo.addActionListener(e->{
			if(opDone.isEmpty() == false){
				if(opDone.get(opDone.size()-1) == 0){				
					bluePoints--;
					if(bluePoints<10){
						puntiBluImageDecine.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource("/puntitaekwondo/0_blue.png")));
						puntiBluImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreBlue(bluePoints)[0])));
					}else{
						puntiBluImageDecine.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreBlue(bluePoints)[0])));
						puntiBluImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreBlue(bluePoints)[1])));
					}
				}
				if(opDone.get(opDone.size()-1)==1){				
					redPoints--;
					if(redPoints<10){
						puntiRossoImageDecine.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource("/puntitaekwondo/0_red.png")));
						puntiRossoImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreRed(redPoints)[0])));
					}else{
						puntiRossoImageDecine.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreRed(redPoints)[0])));
						puntiRossoImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreRed(redPoints)[1])));
						
					}
				}
				if(opDone.get(opDone.size()-1)==2){
					bluePoints=bluePoints-3;
					if(bluePoints<10){
						puntiBluImageDecine.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource("/puntitaekwondo/0_blue.png")));
						puntiBluImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreBlue(bluePoints)[0])));
					}else{
						puntiBluImageDecine.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreBlue(bluePoints)[0])));
						puntiBluImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreBlue(bluePoints)[1])));

					}
				}
				if(opDone.get(opDone.size()-1)==3){
					redPoints=redPoints-3;
					if(redPoints<10){
						puntiRossoImageDecine.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource("/puntitaekwondo/0_red.png")));
						puntiRossoImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreRed(redPoints)[0])));
					}else{
						puntiRossoImageDecine.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreRed(redPoints)[0])));
						puntiRossoImage.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.fightController.getScoreRed(redPoints)[1])));
					}
				}
				if(opDone.get(opDone.size()-1)==4){
				
						 if(redWarnings%2==0){
							pane.remove(warningRed[redWarnings-1]);
							warningRed[redWarnings-1] = new JLabel(new ImageIcon(FightStartedPanelImpl.class.getResource(fightController.getWarningRed(1))));
							pane.add(warningRed[redWarnings-1]);
							xRed = xRed - 63;
						}else {			
							pane.remove(warningRed[(redWarnings)]);
							
						}	
						redWarnings--;
				}
				if(opDone.get(opDone.size()-1)==5){
					
						if(blueWarnings%2==0){	
							pane.remove(warningBlue[blueWarnings-1]);
							warningBlue[blueWarnings-1] = new JLabel(new ImageIcon(FightStartedPanelImpl.class.getResource(fightController.getWarningBlue(1))));
							pane.add(warningBlue[blueWarnings-1]);	
							xBlue = xBlue - 63;
			
						}else{
							pane.remove(warningBlue[blueWarnings]);	
							
						}
						blueWarnings--;
				}
				opDone.remove(opDone.size()-1);
			
				SwingUtilities.updateComponentTreeUI(this);	
			}
		});
		
		round.setText("    R - "+ ++nRound);
		KeyEventDispatcher keyEventDispatcher =   new KeyEventDispatcher(){
            public boolean dispatchKeyEvent(KeyEvent e) 
            {
                 if (e.getID()== KeyEvent.KEY_TYPED)
                 {
                     String key = "" + e.getKeyChar();
                     
                     if (key.equalsIgnoreCase("T"))
                     {
                         start.doClick();
                     } else if (key.equalsIgnoreCase("Y"))
                     {
                         pause.doClick();
                     }else if (key.equalsIgnoreCase("Q"))
                     {
                         warningOneRed.doClick();
                     }else if (key.equalsIgnoreCase("A"))
                     {
                         onePointRed.doClick();
                     }else if (key.equalsIgnoreCase("Z"))
                     {
                         threePointRed.doClick();
                     }else if (key.equalsIgnoreCase("O"))
                     {
                         warningOneBlue.doClick();
                     }else if (key.equalsIgnoreCase("K"))
                     {
                         onePointBlue.doClick();
                     }else if (key.equalsIgnoreCase("M"))
                     {
                         threePointBlue.doClick();
                     }
                     
                  }
				return rootPaneCheckingEnabled;
              }
         };
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			  @Override
			  public void windowClosing(WindowEvent we)
			  { 
			    String ObjButtons[] = {"Si","No"};
			    int PromptResult = JOptionPane.showOptionDialog(null, 
			        "Lo stato del combattimento verrà perso, sei sicuro di vole uscire?", "Attenzione", 
			        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
			        ObjButtons,ObjButtons[1]);
			    if(PromptResult==0)
			    {
			        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(keyEventDispatcher);	
			        dispose();        
			    }
			  }
		});

	}
	
	public void endFightMessage(String message){
		
	    	FightStartedPanelImpl.timerFight.cancel();
	    	fightController.insertListaMatch(cognome1, cognome2, risultato);
	    	fightController.insertListaMatchFile();
		    JFrame frame = new JFrame("JOptionPane showMessageDialog example");			   
		    JOptionPane.showMessageDialog(frame,
		        message);	   
		    this.dispose();
	}
	
	@Override
	public void addObserver(FightController controller){
		
		this.fightController = controller;
	}
	private class LabelAdapter extends MouseAdapter {
		JWindow wa = new JWindow();
	    @Override
	    public void mouseEntered(MouseEvent e) {
	    	  JLabel istruzioni = new JLabel();
	    	  istruzioni.setText("<html>Istruzioni/Funzioni da tastiera:"
					+ "<br> Start->T &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp Pause->Y"
			  		+ "<br>1 punto rosso->A	&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 	1 punto blu->K"
			  		+ "<br>3 punti rosso->z	&nbsp &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 3 punti blu->M"
			  		+ "<br>Ammonizione rosso->Q  &nbsp &nbsp &nbsp &nbsp  Ammonizione blu->O");
			  wa.add(istruzioni);
			  istruzioni.setFont(new Font("Arial", Font.BOLD, 30));
			  wa.setSize(100,100);
			  wa.setLocation(300,300);
			  wa.pack();
			  wa.setVisible(true);
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
	    	wa.setVisible(false);
	    }
	}
	
}