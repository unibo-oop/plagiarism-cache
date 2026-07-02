package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Game;
import model.Card;
import model.CardImpl;
import model.State;
import model.Suit;
import utility.ImageLoader;
/**
 * JPanel for the game
 * @author Alberto_Rossi
 * 
 */
public class GameView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	//JPanel for the table game
    private JPanel table,dealerPanel,playerPanel,chips;
    //JLabel for player and dealer
    private JLabel playerCardLabel[] = new JLabel[6];
    private JLabel dealerCardLabel[] = new JLabel[6];
    //JLabel for Score
	private JLabel playerScore, dealerScore;
    private ImageLoader image;
    //JTextArea for game message
    private JTextArea messageText;
    //JPanel for the buttons
    private JPanel buttonPanel = new JPanel();
    private JButton button[] = new JButton[6];
    //Button for the chips
	private JButton buttonChip[] = new JButton[NCHIPS];
	//dimension of the card
	private static final int CARDWIDTH= 150;
	private static final int CARDHEIGHT= 213;
	private Game game;
	private JLabel balance, bet;
    private final static int NCARD = 6;
    private final static int NBUTTONS = 6;
    private final static int NCHIPS = 4; 
    /**
     * View of the game
     * @param view
     * @param game
     * @param image
     */
	public GameView(View view,Game game,ImageLoader image) {
		super();
		this.game = game;
		this.image = image;
		this.setOpaque(false);
		this.setLayout(null);
		this.table = new JPanel();
		this.table.setBackground(Color.DARK_GRAY);
		this.table.setBounds(25,25,800,525);
		this.table.setLayout(null);
		this.table.setVisible(true);
		
		
		this.dealerPanel = new JPanel();
		this.dealerPanel.setBounds(55,70,CARDWIDTH*5,CARDHEIGHT);
		this.dealerPanel.setBackground(null);
		this.dealerPanel.setOpaque(false);
		this.dealerPanel.setLayout(new GridLayout(1,5));
		this.dealerPanel.setVisible(true);
		this.add(dealerPanel);
				
		this.playerPanel = new JPanel();
		this.playerPanel.setBounds(55,300,CARDWIDTH*5,CARDHEIGHT);
		this.playerPanel.setOpaque(false);
		this.playerPanel.setLayout(new GridLayout(1,5));
		this.playerPanel.setVisible(true);
		this.add(playerPanel);

		for(int i = 1; i < NCARD; i++) {
			playerCardLabel[i] = new JLabel();
			playerCardLabel[i].setVisible(true);
			playerPanel.add(playerCardLabel[i]);
		}
		
		for(int i = 1; i < NCARD; i++) {
			this.dealerCardLabel[i] = new JLabel();
			this.dealerCardLabel[i].setVisible(true);
			this.dealerPanel.add(dealerCardLabel[i]);
		}	
		this.setTable();
		
		this.dealerScore = new JLabel();
		this.dealerScore.setBounds(50,5,200,30);
		this.dealerScore.setForeground(Color.white);
		this.dealerScore.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		this.dealerScore.setText("Dealer : 0");
		
		this.table.add(dealerScore);
		
		this.playerScore = new JLabel();
		this.playerScore.setBounds(50,490,200,30);
		this.playerScore.setForeground(Color.white);
		this.playerScore.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		this.playerScore.setText("Giocatore : 0");
		this.table.add(playerScore);
	
		this.add(table,BorderLayout.CENTER);
		
		this.messageText = new JTextArea();
		this.messageText.setBounds(100,560,670,80);
		this.messageText.setBackground(null);
		this.messageText.setForeground(Color.white);
		this.messageText.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		this.messageText.setEditable(false);
		this.messageText.setText("Fai la tua puntata");
		this.messageText.setOpaque(false);
		this.add(messageText);
		
		this.buttonPanel = new JPanel();
		this.buttonPanel.setBounds(920,150,200,300);
		this.buttonPanel.setBackground(null);
		this.buttonPanel.setLayout(new GridLayout(6,1));
		this.buttonPanel.setOpaque(false);
		this.add(buttonPanel);
		
		for(int i = 0; i < NBUTTONS; i++) {
			button[i] = new JButton();
			button[i].setBackground(null);
			button[i].setForeground(Color.white);
			button[i].setFocusPainted(false);
			button[i].setContentAreaFilled(false);
			button[i].setFont(new Font("Times New Roman", Font.PLAIN, 42));
			button[i].addActionListener(game.getActionHandler());
			button[i].setActionCommand(""+i);
			button[i].setVisible(false);
			buttonPanel.add(button[i]);
		}
		
		this.chips = new JPanel();
		this.chips.setLayout(new GridLayout(2,2));
		this.chips.setBounds(850,400,400,270);
		this.chips.setBackground(new Color(0,81,0));
		for(int i = 0; i < NCHIPS; i++) {
			buttonChip[i] = new JButton();
			buttonChip[i].setVisible(true);
			buttonChip[i].setBackground(new Color(0,81,0));
			buttonChip[i].setBorder(null);
			buttonChip[i].setFocusPainted(false);
			buttonChip[i].addActionListener(game.getActionHandler());
			buttonChip[i].setActionCommand("chips"+i);
			buttonChip[i].setIcon(this.image.getChipImage()[i]);
			this.chips.add(buttonChip[i]);
		}
		
		this.balance = new JLabel();
		this.balance.setForeground(Color.white);
		this.balance.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		this.balance.setText("Saldo:1000");
		this.chips.add(this.balance);
		
		this.bet = new JLabel();
		this.bet.setForeground(Color.white);
		this.bet.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		this.bet.setText("Puntata:0");
		this.chips.add(this.bet);
		
		this.add(this.chips);
	}
	
	/**
	 * set Card covers
	 */
	private void setTable() {
		for(int i = 1;i<3;i++) {
			playerCardLabel[i].setIcon(this.image.getBackCard());
			playerCardLabel[i].setVisible(true);
			dealerCardLabel[i].setIcon(this.image.getBackCard());
			dealerCardLabel[i].setVisible(true);
		}
	}
	/**
	 * Search image for draw the card
	 * @param playerHand
	 * @param dealerHand
	 */
	private void setImage(List<CardImpl> playerHand,List<CardImpl> dealerHand) {

		int cont =0;
		for(Card c : playerHand) {
			cont++;
			if(c.getSuit() == Suit.spades) {
				for(Card ca: this.image.getSpades().keySet()) {
					if(ca.getValues() == c.getValues()) {
						this.playerCardLabel[cont].setIcon(this.image.getSpades().get(ca));
						this.playerCardLabel[cont].setVisible(true);
					}
				}
			}else if(c.getSuit() == Suit.clubs) {
				for(Card ca: this.image.getClubs().keySet()) {
					if(ca.getValues() == c.getValues()) {
						this.playerCardLabel[cont].setIcon(this.image.getClubs().get(ca));
						this.playerCardLabel[cont].setVisible(true);
					}
				}
			}else if(c.getSuit() == Suit.diamods) {
				for(Card ca: this.image.getDiamonds().keySet()) {
					if(ca.getValues() == c.getValues()) {
						this.playerCardLabel[cont].setIcon(this.image.getDiamonds().get(ca));
						this.playerCardLabel[cont].setVisible(true);
					}
				}
			}else {
				for(Card ca: this.image.getHeart().keySet()) {
					if(ca.getValues() == c.getValues()) {
						this.playerCardLabel[cont].setIcon(this.image.getHeart().get(ca));
						this.playerCardLabel[cont].setVisible(true);
					}
				}
			}
			
		}
			
			
			
		int num = 0;
		for(Card c : dealerHand) {
			num++;
			if(c.getSuit() == Suit.spades) {
				for(Card ca: this.image.getSpades().keySet()) {
					if(ca.getValues() == c.getValues()) {
						this.dealerCardLabel[num].setIcon(this.image.getSpades().get(ca));
						this.dealerCardLabel[num].setVisible(true);
					}
				}
			}else if(c.getSuit() == Suit.clubs) {
				for(Card ca: this.image.getClubs().keySet()) {
					if(ca.getValues() == c.getValues()) {
						this.dealerCardLabel[num].setIcon(this.image.getClubs().get(ca));
						this.dealerCardLabel[num].setVisible(true);
					}
				}
			}else if(c.getSuit() == Suit.diamods) {
				for(Card ca: this.image.getDiamonds().keySet()) {
					if(ca.getValues() == c.getValues()) {
						this.dealerCardLabel[num].setIcon(this.image.getDiamonds().get(ca));
						this.dealerCardLabel[num].setVisible(true);
					}
				}
			}else {
				for(Card ca: this.image.getHeart().keySet()) {
					if(ca.getValues() == c.getValues()) {
						this.dealerCardLabel[num].setIcon(this.image.getHeart().get(ca));
						this.dealerCardLabel[num].setVisible(true);
					}
				}
			}
		}
	}
	
/**
 * Set Table for each game Status
 * @param scoreDealer
 * @param scorePlayer
 * @param playerHand
 * @param dealerHand
 * @param state
 * @param balance
 * @param bet
 */
	
	public void render(int scoreDealer, int scorePlayer, List<CardImpl> playerHand, List<CardImpl> dealerHand, State state, int balance, int bet) {
		switch(state) {
		case win:
			this.resetGame();
			this.setScore(scorePlayer, scoreDealer);
			this.setImage(playerHand,dealerHand);
			this.button[0].setVisible(true);
			this.button[0].setText("Rigioca");
			this.messageText.setText("Hai vinto!");
			this.setBalance(balance);
			this.setInvisiblechips();
			break;
		case lose:
			this.resetGame();
			this.setScore(scorePlayer, scoreDealer);
			this.setImage(playerHand,dealerHand);
			this.button[0].setVisible(true);
			this.button[0].setText("Rigioca");
			this.messageText.setText("Hai Perso!");
			this.setBalance(balance);
			this.setInvisiblechips();
			break;
		case natural:
			this.resetGame();
			this.setScore(scorePlayer, scoreDealer);
			this.setImage(playerHand,dealerHand);
			this.button[0].setVisible(true);
			this.button[0].setText("Rigioca");
			this.messageText.setText("Hai fatto blackJack!");
			this.setBalance(balance);
			this.setInvisiblechips();
			break;
		case playerTurn:
			this.resetGame();
			this.playerScore.setText("Giocatore: "+String.valueOf(scorePlayer));
			this.dealerScore.setText("Dealer: ?");
			this.setImage(playerHand,dealerHand);
			this.dealerCardLabel[2].setIcon(this.image.getBackCard());
			this.messageText.setText("Vuoi pescare?");
			this.button[1].setVisible(true);
			this.button[1].setText("Pesca");
			this.button[2].setVisible(true);
			this.button[2].setText("Stai");
			this.setBalance(balance);
			this.setInvisiblechips();
			break;
		case dealerTurn:
			this.resetGame();
			this.setScore(scorePlayer, scoreDealer);;
			this.setImage(playerHand,dealerHand);
			this.setInvisiblechips();
			break;
		case drow:	
			this.resetGame();
			this.setScore(scorePlayer, scoreDealer);
			this.setImage(playerHand,dealerHand);
			this.messageText.setText("pareggio");
			this.button[0].setVisible(true);
			this.button[0].setText("Rigioca");
			this.setInvisiblechips();
			break;
		case broke:
			this.resetGame();
			this.setScore(scorePlayer, scoreDealer);
			this.setImage(playerHand,dealerHand);
			this.messageText.setText("Hai Perso!");		

			int n = JOptionPane.showConfirmDialog(null, "Saldo esaurito, vuoi rigiocare?", "ATTENZIONE",
			JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(n == JOptionPane.YES_OPTION) {
				this.game.playAgain();
			}else {
				System.exit(0);
			}
			
			break;
		case nobet:
			this.resetGame();
			this.setTable();
			this.setScore(scorePlayer, scoreDealer);
			this.messageText.setText("non puoi puntare piu di quello che hai");
			this.button[3].setVisible(true);
			this.button[3].setText("Gioca");
			this.setBalance(balance);
			this.bet.setText(String.valueOf("Puntata:"+bet));
			this.setInvisiblechips();
			
			break;
		default:
			this.resetGame();
			this.setTable();
			this.setScore(scorePlayer, scoreDealer);
			this.messageText.setText("Fai la tua puntata");
			this.button[3].setVisible(true);
			this.button[3].setText("Gioca");
			this.setBalance(balance);
			this.bet.setText(String.valueOf("Puntata:"+bet));
			this.setVisibleChip();
			break;
			
		}
	}
	/**
	 * set the JLabel of the balance
	 * @param balance
	 */
	private void setBalance(int balance) {
		this.balance.setText("Saldo:"+String.valueOf(balance));
	}
	/**
	 * set invisible the chips
	 */
	private void setInvisiblechips() {
		for(int i =0; i<NCHIPS; i++) {
			this.buttonChip[i].setEnabled(false);
		}
	}
	/**
	 * method for set visible the chips
	 */
	private void setVisibleChip() {
		for(int i =0; i<NCHIPS; i++) {
			this.buttonChip[i].setEnabled(true);
		}
	}
	/**
	 * Method for set the Score
	 * @param scorePlayer
	 * @param scoreDealer
	 */
	private void setScore(int scorePlayer,int scoreDealer) {
		this.playerScore.setText("Giocatore: "+String.valueOf(scorePlayer));
		this.dealerScore.setText("Dealer: "+ String.valueOf(scoreDealer));
	}
	/**
	 * to reset buttons and label of the card
	 */
	private void resetGame() {

		for(int i = 0; i < NBUTTONS; i++) {
			this.button[i].setVisible(false);
		}
		for(int j = 1; j < NCARD; j++) {
			this.playerCardLabel[j].setVisible(false);
			this.dealerCardLabel[j].setVisible(false);
		}
	}
}
