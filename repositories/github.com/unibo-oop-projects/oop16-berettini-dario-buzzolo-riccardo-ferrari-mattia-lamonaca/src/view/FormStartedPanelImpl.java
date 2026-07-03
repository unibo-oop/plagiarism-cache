package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.FormController;
import model.Athlete;
import model.AthleteImpl;
import model.Belt;


public class FormStartedPanelImpl  extends JFrame implements FormStartedPanel{

	private static final long serialVersionUID = 3573309690612785259L;
	FormController formController;
	Athlete atleta;
	private JPanel mainPanel = new JPanel();
	private final JLabel puntiDecimi = new JLabel(new ImageIcon(FightStartedPanelImpl.class.getResource("/puntitaekwondo/0_red.png")));
	private final JLabel puntiVirgola = new JLabel(new ImageIcon(FightStartedPanelImpl.class.getResource("/puntitaekwondo/float_red.png")));
	private final JLabel puntiUnita = new JLabel(new ImageIcon(FightStartedPanelImpl.class.getResource("/puntitaekwondo/0_red.png")));
	private final JLabel puntiDecine = new JLabel(new ImageIcon(FightStartedPanelImpl.class.getResource("/puntitaekwondo/1_red.png")));
	private final JLabel nome = new JLabel();
	private final JLabel cognome = new JLabel("Default");
	private final Belt cintura;
	private final JLabel belt = new JLabel();
	private final JLabel forma = new JLabel();
	private final JLabel timeText = new JLabel("00:00");
	private final JButton penalita1 = new JButton("-0,1");
	private final JButton penalita3 = new JButton("-0,3");
	private final JButton penalita5 = new JButton("-0,5");
	private final JButton start = new JButton("Start");
	public static Timer timer;
	TimerTask timerTask;
	private Integer secondi = 0;
	private int puntiTotali = 100;
	
	
	
	public FormStartedPanelImpl(String n, String co, Belt ci, String f){
		
		this.cintura = ci;
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setBounds(100, 100, 1200, 700);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(0, 0, 0));
		nome.setText(n);
		
		cognome.setText("           " + co);
		cognome.setForeground(new Color(255, 255, 255));
		cognome.setFont(new Font("Arial", Font.BOLD, 30));
		cognome.setBounds(20, 20, 300, 60);
		cognome.setOpaque(true);
		cognome.setBackground(new Color(255, 0, 0));
		mainPanel.add(cognome);
		
		forma.setText("   " + f);
		forma.setForeground(new Color(255, 255, 255));
		forma.setFont(new Font("Arial", Font.BOLD, 18));
		forma.setBounds(20, 90, 300, 40);
		forma.setOpaque(true);
		forma.setBackground(new Color(255, 0, 0));
		mainPanel.add(forma);
		
		belt.setText("                  " + cintura);
		belt.setForeground(new Color(255, 255, 255));
		belt.setFont(new Font("Arial", Font.BOLD, 20));
		belt.setBounds(20, 135, 300, 40);
		belt.setOpaque(true);
		belt.setBackground(new Color(255, 0, 0));
		mainPanel.add(belt);
		
		penalita1.setForeground(new Color(0, 0, 0));
		penalita1.setFont(new Font("Arial", Font.BOLD, 20));
		penalita1.setBounds(177, 550 , 150, 50);
		mainPanel.add(penalita1);
		penalita1.setEnabled(false);
		
		penalita3.setForeground(new Color(0, 0, 0));
		penalita3.setFont(new Font("Arial", Font.BOLD, 20));
		penalita3.setBounds(512, 550 , 150, 50);
		mainPanel.add(penalita3);
		penalita3.setEnabled(false);
		
		penalita5.setForeground(new Color(0, 0, 0));
		penalita5.setFont(new Font("Arial", Font.BOLD, 20));
		penalita5.setBounds(842, 550 , 150, 50);
		mainPanel.add(penalita5);
		penalita5.setEnabled(false);
		
		start.setForeground(new Color(0, 0, 0));
		start.setFont(new Font("Arial", Font.BOLD, 20));
		start.setBounds(970, 20 , 200, 50);
		mainPanel.add(start);
				
		timeText.setFont(new Font("Arial", Font.BOLD, 50));
		timeText.setForeground(new Color(255, 255, 255));
		timeText.setBounds(530, 20 , 150, 50);
		mainPanel.add(timeText);
		
		puntiDecine.setBounds(340, 100 , 200, 420);
		mainPanel.add(puntiDecine);
		
		puntiUnita.setBounds(540, 100 , 200, 420);
		mainPanel.add(puntiUnita);
		
		puntiVirgola.setBounds(740, 100 , 50, 420);
		mainPanel.add(puntiVirgola);
		
		puntiDecimi.setBounds(790, 100 , 200, 420);
		mainPanel.add(puntiDecimi);

		timer = new Timer();
		
		start.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(start.getText()=="Start"){
					start.setText("Stop");
					penalita1.setEnabled(true);
					penalita3.setEnabled(true);
					penalita5.setEnabled(true);
					timerTask = new TimerTask() {
						
						@Override
						public void run() {
							if(secondi>58){
								secondi=secondi-60;
								if(secondi<11)
									timeText.setText("01:0" + ++secondi+"");
								else
									timeText.setText("01:" + ++secondi+"");
								secondi=secondi+60;
							}
							else if(secondi < 9){
									timeText.setText("00:0" + ++secondi+"");
							}else {
									timeText.setText("00:" + ++secondi+"");
							}
						}
					};
					
					timer.scheduleAtFixedRate(timerTask, 0, 1000);
					
				} else if(start.getText()=="Stop"){
						start.setText("Salva e chiudi");
						penalita1.setEnabled(false);
						penalita3.setEnabled(false);
						penalita5.setEnabled(false);
						timer.cancel();
					
				}else {
					
					atleta = new AthleteImpl(nome.getText(), cognome.getText(), cintura, forma.getText(),(double)puntiTotali/10);
					formController.addAtleta(atleta);
					formController.addListaAtletiFile();	
					dispose();
					
				}
			}
		});
		
		penalita1.addActionListener(e->{
			
				if(puntiTotali-1<=0){
					puntiTotali=0;
					JFrame frame = new JFrame("Errore");			   
				    JOptionPane.showMessageDialog(frame,
				      "Prova terminata. Voto minimo.");	
				    timer.cancel();
					atleta = new AthleteImpl(nome.getText(), cognome.getText(), cintura, forma.getText(), (double)puntiTotali/10);
					formController.addAtleta(atleta);
					formController.addListaAtletiFile();	
					dispose();
					
				}else{
					puntiTotali = puntiTotali-1;
					puntiDecine.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.formController.getScoreRed(puntiTotali)[0])));
					puntiUnita.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.formController.getScoreRed(puntiTotali)[1])));
					puntiDecimi.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.formController.getScoreRed(puntiTotali)[2])));
				}
			}
		);
		
		penalita3.addActionListener(e->{
				if(puntiTotali-3<=0){
					puntiTotali=0;
					JFrame frame = new JFrame("Errore");			   
				    JOptionPane.showMessageDialog(frame,
				      "Prova terminata. Voto minimo.");	
				    timer.cancel();
					atleta = new AthleteImpl(nome.getText(), cognome.getText(), cintura, forma.getText(), (double)puntiTotali/10);
					formController.addAtleta(atleta);
					formController.addListaAtletiFile();	
					dispose();
					
				}else{
					puntiTotali = puntiTotali-3;
					puntiDecine.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.formController.getScoreRed(puntiTotali)[0])));
					puntiUnita.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.formController.getScoreRed(puntiTotali)[1])));
					puntiDecimi.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.formController.getScoreRed(puntiTotali)[2])));
				}
		});
		
		penalita5.addActionListener(e->{
				if(puntiTotali-5<=0){
					puntiTotali=0;
					JFrame frame = new JFrame("Errore");			   
				    JOptionPane.showMessageDialog(frame,
				      "Prova terminata. Voto minimo.");	
				    timer.cancel();
					atleta = new AthleteImpl(nome.getText(), cognome.getText(), cintura, forma.getText(),(double) puntiTotali/10);
					formController.addAtleta(atleta);
					formController.addListaAtletiFile();	
					dispose();
					
				}else{
					puntiTotali = puntiTotali-5;
					puntiDecine.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.formController.getScoreRed(puntiTotali)[0])));
					puntiUnita.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.formController.getScoreRed(puntiTotali)[1])));
					puntiDecimi.setIcon(new ImageIcon(FightStartedPanelImpl.class.getResource(this.formController.getScoreRed(puntiTotali)[2])));
				}
		});
		KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher(){
			public boolean dispatchKeyEvent(KeyEvent e) 
        {
            if (e.getID()== KeyEvent.KEY_TYPED)
            {
                String key = "" + e.getKeyChar();
                
                if (key.equalsIgnoreCase("T"))
                {
                    start.doClick();
                } else if (key.equalsIgnoreCase("1"))
                {
                    penalita1.doClick();
                }else if (key.equalsIgnoreCase("3"))
                {
                    penalita3.doClick();
                }else if (key.equalsIgnoreCase("5"))
                {
                    penalita5.doClick();
                }
                
             }
			return rootPaneCheckingEnabled;
         }
    };
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
		
		this.setFocusable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			  @Override
			  public void windowClosing(WindowEvent we)
			  { 
			    String ObjButtons[] = {"Si","No"};
			    int PromptResult = JOptionPane.showOptionDialog(null, 
			        "Lo stato dell'esecuzione della forma verrà perso, sei sicuro di vole uscire?", "Attenzione", 
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

	public void addObserverForm(FormController controller) {
		this.formController = controller;
	}
}
