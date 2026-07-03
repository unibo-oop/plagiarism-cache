package controller;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import view.*;
import model.deck.Card;
import model.deck.Deck;
import model.deck.DeckImpl;
import model.dice.Dice;
import model.player.*;

public class Turn extends TestForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int nPlayer;
	private static boolean dmgKingX = false;
	private static int nTurni = 0;
	private static int startLife;
	private int startCash;
	private static String desc="";
	private static int reachPoint;
	private static ArrayList<Player> players = new ArrayList<>();
	private static ArrayList<Player> tmpPlayers = new ArrayList<>();
	private static boolean roll = false; //controllo se un giocatore ha lanciato almeno una volta i dadi
	private static JButton infoBtn;
	//il king
	//il giocatore di turno
	private static Player currentPlayer;
	//lista di giocatori che invece non sono di turno
	private static List<Player> otherPlayer = new ArrayList<>();
	//lista con il risultato dei dadi
	private static List<String> carteDesc = new ArrayList<>();
	private static List<JLabel> dices = new ArrayList<>(); //label dei dadi
	private static List<Dice> realDices = new ArrayList<>(); //dadi reali
	private static List<JButton> btnDices = new ArrayList<>(); //bottoni dei dadi
	private static List<JButton> btnCrown = new ArrayList<>(); //bottoni per le corone del king
	private static List<JButton> btnCard = new ArrayList<>(); //bottoni delle carte
	private static List<JComboBox<String>> comboEquip= new ArrayList<>();
	//contatore numero lanci dai dadi
	private static int lanci = 0;
	private static boolean endDice = false;
	//possibilità di finire il turno
	private static boolean canEndTurn = false;
	/*vettore con le label dei soldi*/
	private static List<JLabel> money = new ArrayList<>();
	/*vettore con le label della vita*/
	private static List<JLabel> playersLife = new ArrayList<>();
	/*vettore con le label dei punti*/
	private static List<JLabel> points = new ArrayList<>();
	private static int index;
	/*variabile per il mazzo*/
	private static Deck mazzo = new DeckImpl();
	private static int indiceCarte;
	private static List<Card> carteInCampo = new ArrayList<>();
	
	/**
	 * Constructor of the class Turn.
	 * @param n
	 * @param l
	 * @param c
	 * @param p
	 */
	public Turn(Integer n, Integer l, Integer c, Integer p) {
		nPlayer = n.intValue();
		startLife = l;
		this.startCash = c;
		reachPoint = p;
	}
	
	/**
	 * Set the points that a player has to reach to win the game.
	 * @param p
	 */
	public static void setReachPoint(int p) {
		reachPoint = p;
	}
	
	/**
	 * Set the number of player that play the game.
	 * @param np
	 */
	public static void setnPlayer(int np) {
		nPlayer = players.size();
	}
	
	/**
	 * Method that return if a player has rolled at least one time dices.
	 * @return
	 */
	public static boolean getRoll() {
		return roll;
	}
	
	
	
	/**
	 * Method to return the reference of the player.
	 * @return
	 */
	public static int getCurrentInt() {
		return tmpPlayers.indexOf(currentPlayer);
	}
	
	/**
	 * Settingd of the set the equipment combo box of the players.
	 * @param c1
	 * @param c2
	 * @param c3
	 * @param c4
	 */
	public static void setComboEquip(JComboBox<String> c1,JComboBox<String> c2,JComboBox<String> c3,JComboBox<String> c4){
		comboEquip.add(c1);
		comboEquip.add(c2);
		comboEquip.add(c3);
		comboEquip.add(c4);
	}

	/**
	 * Load the dice of the game table.
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @param d6
	 */
	public static void setDices(JLabel d1,JLabel d2,JLabel d3,JLabel d4,JLabel d5,JLabel d6) {
		dices.add(d1);
		dices.add(d2);
		dices.add(d3);
		dices.add(d4);
		dices.add(d5);
		dices.add(d6);
		for (int i = 0; i < 6; i++) {
			realDices.add(new Dice());
		}
		Game.logger("Dadi caricati");
	}
	
	/**
	 * Set the crown button of the players.
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 */
	public static void setCrown(JButton p1,JButton p2,JButton p3,JButton p4) {
			btnCrown.add(p1);
			btnCrown.add(p2);
			if(p3 != null) {
				btnCrown.add(p3);
			}
			if(p4 != null) {
				btnCrown.add(p4);
			}
	}
	
	/**
	 * Set the label of the player's point.
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 */
	public static void setPoints(JLabel p1,JLabel p2,JLabel p3,JLabel p4) {
		points.add(p1);
		points.add(p2);
		if(p3 != null) {
			points.add(p3);
		}
		if(p3 != null) {
			points.add(p4);
		}			
	}
	
	/**
	 * Set the label of the player's money.
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 */
	public static void setMoneyLbl(JLabel p1,JLabel p2,JLabel p3,JLabel p4) {
		money.add(p1);
		money.add(p2);
		if(p3 != null) {
			money.add(p3);
		}
		if(p4 != null) {
			money.add(p4);
		}
	}
	
	/**
	 * Set the label of the player's life.
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 */
	public static void setLifeLbl(JLabel p1,JLabel p2,JLabel p3,JLabel p4) {
		playersLife.add(p1);
		playersLife.add(p2);
		if(p3 != null) {
			playersLife.add(p3);
		}
		if(p4 != null) {
			playersLife.add(p4);
		}	
	}
	
	/**
	 * Set the button of the cards on the game table.
	 * @param c1
	 * @param c2
	 * @param c3
	 */
	public static void setFieldCards(JButton c1,JButton c2,JButton c3) {
		btnCard.add(c1);
		btnCard.add(c2);
		btnCard.add(c3);
		mazzo.createDeck();
		mazzo.showFieldCards().forEach(e-> {
			Game.logger("Inserita carta "+e.getName()+" al prezzo di "+e.getPrice());
		});
		for (int i = 0; i < 3; i++) {
			btnCard.get(i).setToolTipText(mazzo.showFieldCards().get(i).getName() + " - "+ mazzo.showFieldCards().get(i).getPrice() +" - " + mazzo.showFieldCards().get(i).getDescription());
		}
		actionBtn();
	}
	
	/**
	 * Method that set the btn for the info
	 */
	public static void setInfo(JButton info) {
		infoBtn = info;
		mazzo.getDeckCards().forEach(c -> {
			carteDesc.add(c.getName()+" - "+c.getPrice()+" - "+c.getDescription());
		});
		carteDesc.forEach(d -> desc += d + "\n");
		infoBtn.addActionListener(e->{
			gameInfo();
		});
	}
	
	/**
	 * Set the dice button, you can disable a dice and it will not be rerolled.
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param d4
	 * @param d5
	 * @param d6
	 */
	public static void setBtnDice(JButton d1,JButton d2,JButton d3,JButton d4,JButton d5,JButton d6) {
		btnDices.add(d1);
		btnDices.add(d2);
		btnDices.add(d3);
		btnDices.add(d4);
		btnDices.add(d5);
		btnDices.add(d6);
		
		for (int i = 0; i < 6; i++) {
			int h=i;
			btnDices.get(i).addActionListener(e->{
				if(dices.get(h).isEnabled()) {
					dices.get(h).setEnabled(false);
				} else {
					dices.get(h).setEnabled(true);
				}
			});
		}
	}
	
	/**
	 * Method to roll dice.
	 */
	public static void rollDice() {
		if(lanci<=2 && endDice==false) {
			lanci++;
			roll = true;
			// realDices.forEach(d->d.rollDice());
			for (int i = 0; i < 6; i++) {
				if (dices.get(i).isEnabled()) {
					realDices.get(i).rollDice();
					dices.get(i).setIcon(new ImageIcon(Game.class.getResource("/res/" + realDices.get(i).getNumber() + ".png")));
				}
				// dices.get(i).setIcon(new
				// ImageIcon(Game.class.getResource("/res/"+realDices.get(i).getNumber()+".png")));
				// Game.logger("Dado " + (i+1) +" - "+ realDices.get(i).getNumber());
			}

			if (lanci == 3) {
				solveDice();
				endDice = true;
			}
		}
	}
	
	/**
	 * Method to solve the results of dice.
	 */
	public static void solveDice() {
		if(endDice==false) {
			int indice = 0;
			int vita = 0;
			int soldi = 0;
			int schiaffi = 0;
			int cont1 = 0;
			int cont2 = 0;
			int cont3 = 0;
			
			for (int i = 0; i < 6; i++) {
				if(realDices.get(i).getNumber() == 1) {
					cont1++;
				}
				if(realDices.get(i).getNumber() == 2) {
					cont2++;
				}
				if(realDices.get(i).getNumber() == 3) {
					cont3++;
				}
				if (realDices.get(i).getNumber() == 4) {
					schiaffi++;
				}
				if (realDices.get(i).getNumber() == 5) {
					vita++;
				}
				if (realDices.get(i).getNumber() == 6) {
					soldi++;
				}
			}
			
			/*controllo livelli*/
			switch(cont1) {
			case 3:
				currentPlayer.increasePoints(1);
				//points.get(index).setText(String.valueOf(currentPlayer.getPoints()));
				Game.logger(currentPlayer.getName() + " guadagna 1 punto");
				break;
			case 4:
				currentPlayer.increasePoints(2);
				//points.get(index).setText(String.valueOf(currentPlayer.getPoints()));
				Game.logger(currentPlayer.getName() + " guadagna 2 punti");
				break;
			case 5:
				currentPlayer.increasePoints(3);
				//points.get(index).setText(String.valueOf(currentPlayer.getPoints()));
				Game.logger(currentPlayer.getName() + " guadagna 3 punti");
				break;
			case 6:
				currentPlayer.increasePoints(4);
				//points.get(index).setText(String.valueOf(currentPlayer.getPoints()));
				Game.logger(currentPlayer.getName() + " guadagna 4 punti");
				break;
			default:
				currentPlayer.increasePoints(0);
				break;
			}
			
			switch(cont2) {
			case 3:
				currentPlayer.increasePoints(2);
				//points.get(index).setText(String.valueOf(currentPlayer.getPoints()));
				Game.logger(currentPlayer.getName() + " guadagna 2 punti");
				break;
			case 4:
				currentPlayer.increasePoints(3);
				//points.get(index).setText(String.valueOf(currentPlayer.getPoints()));
				Game.logger(currentPlayer.getName() + " guadagna 3 punti");
				break;
			case 5:
				currentPlayer.increasePoints(4);
				//points.get(index).setText(String.valueOf(currentPlayer.getPoints()));
				Game.logger(currentPlayer.getName() + " guadagna 4 punti");
				break;
			case 6:
				currentPlayer.increasePoints(5);
				//points.get(index).setText(String.valueOf(currentPlayer.getPoints()));
				Game.logger(currentPlayer.getName() + " guadagna 5 punti");
				break;
			default:
				currentPlayer.increasePoints(0);
				break;
			}
			
			switch(cont3) {
			case 3:
				currentPlayer.increasePoints(3);
				//points.get(index).setText(String.valueOf(currentPlayer.getPoints()));
				Game.logger(currentPlayer.getName() + " guadagna 3 punti");
				break;
			case 4:
				currentPlayer.increasePoints(4);
				//points.get(index).setText(String.valueOf(currentPlayer.getPoints()));
				Game.logger(currentPlayer.getName() + " guadagna 4 punti");
				break;
			case 5:
				currentPlayer.increasePoints(5);
				//points.get(index).setText(String.valueOf(currentPlayer.getPoints()));
				Game.logger(currentPlayer.getName() + " guadagna 5 punti");
				break;
			case 6:
				currentPlayer.increasePoints(6);
				//points.get(index).setText(String.valueOf(currentPlayer.getPoints()));
				Game.logger(currentPlayer.getName() + " guadagna 6 punti");
				break;
			default:
				currentPlayer.increasePoints(0);
				break;
			}
			/*controllo livelli*/
			
			if (currentPlayer.isKing()) {
				int temp = schiaffi;
				otherPlayer.forEach(p -> {
					p.takeDamages(temp);
				});

				Game.logger(currentPlayer.getName() + " da " + schiaffi + " schiaffi agli altri giocatori");
			} else {
				for (int i = 0; i < otherPlayer.size(); i++) {
					if (otherPlayer.get(i).isKing()) {
						indice = i;
						otherPlayer.get(i).takeDamages(schiaffi);
						Game.logger(otherPlayer.get(i).getName() + " prende " + schiaffi + " schiaffi da "
								+ currentPlayer.getName());
					}
				}
			}
			
			if(!currentPlayer.isKing() && schiaffi>0) {
				dmgKingX = true;
			}
			
			if(soldi != 0) {
				currentPlayer.earnMoney(soldi);
			}
			//money.get(index).setText(String.valueOf(currentPlayer.getMoney()));
			Game.logger(currentPlayer.getName() + " ha guadagnato " + soldi + " soldi");
			
			if (currentPlayer.isKing()) {
				currentPlayer.rechargeLife(0);
				//playersLife.get(index).setText(String.valueOf(currentPlayer.getLife()));
			} else {
				currentPlayer.rechargeLife(vita);
				Game.logger(currentPlayer.getName() + " ha guadagnato " + vita + " di vita");
				//playersLife.get(index).setText(String.valueOf(currentPlayer.getLife()));
			}
			
			for (int i = 0; i < players.size(); i++) {
				playersLife.get(i).setText(String.valueOf(players.get(i).getLife()));
				money.get(i).setText(String.valueOf(players.get(i).getMoney()));
				points.get(i).setText(String.valueOf(players.get(i).getPoints()));
			}
			
			Player k = players.stream().filter(p->p.isKing()).findAny().get();
			if(dmgKingX == true && k.getLife() > 0) {
				dmgKing();
			}
			
			for (int i = 0; i < players.size(); i++) {
				money.get(i).setText(String.valueOf(players.get(i).getMoney()));
				points.get(i).setText(String.valueOf(players.get(i).getPoints()));
				if(players.get(i).getPoints() >= reachPoint) {
					vittoria(currentPlayer);
				}
				playersLife.get(i).setText(String.valueOf(players.get(i).getLife()));
				if(players.get(i).getLife() <= 0) {
					morte(i);
				}		
			}
			
			endDice = true;
			canEndTurn = true;
			dmgKingX = false;
		}
	}

	/**
	 * Method that end a turn and modifie the reference of currentPlayer and otherPlayer.
	 */
	public static void fineTurno() {
		if(canEndTurn == false) {
			Game.logger("CONFERMARE LANCIO DADI");
		} else {
			roll = false;
			Game.logger(currentPlayer.getName()+" FINISCE IL TURNO");
			dices.forEach(d->d.setEnabled(true));
			index++;
			if(index>=players.size()) {
				index = 0;
			}
			currentPlayer = players.get(index);
			if(currentPlayer.isKing()) {
				currentPlayer.increasePoints(2);
				points.get(index).setText(String.valueOf(players.get(index).getPoints()));
			}
			otherPlayer.clear();
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i) != currentPlayer) {
					otherPlayer.add(players.get(i));
				}
			}
			//otherPlayer = players.stream().filter(p->!p.equals(currentPlayer)).collect(Collectors.toList());
			Game.clearLog();
			Game.logger(currentPlayer.getName() + " INIZIO TURNO");
			endDice = false;
			canEndTurn = false;
			lanci = 0;
			btnCard.forEach(c -> c.setEnabled(false));
			players.forEach(p->{
				if(p.getPoints()>=reachPoint || players.size() == 1) {
					vittoria(currentPlayer);
				}
			});
		}
	}
	
	/**
	 * JOptionPane that came out if a player win the game.
	 * @param vincitore
	 */
	private static void vittoria(Player vincitore) {
		Object[] options = { "OK" };
		  int choice = JOptionPane.showOptionDialog(Game.getFrame(), 
		      "Il giocatore "+ vincitore.getName() + " è il vero King di Forlì!", 
		      "Complimenti", 
		      JOptionPane.YES_NO_OPTION, 
		      JOptionPane.QUESTION_MESSAGE, 
		      null, 
		      options, 
		      options[0]);

		  // interpret the user's choice
		  if (choice == JOptionPane.YES_OPTION)
		  {
			  System.exit(0);
		  }
	}
	
	/**
	 * Creation of the players list.
	 * @param names
	 * @param life
	 * @param cash
	 */
	public static void createPlayers(ArrayList<String> names, int life, int cash) {
		names.forEach(n ->players.add(new PlayerImpl(n,life,cash)));
		tmpPlayers.addAll(players);
	}
	
	/**
	 * Getter for the number of the players.
	 * @return
	 */
	public Integer getNPlayer() {
		return Turn.nPlayer;
	}
	
	/**
	 * Method that set the max life of the players. You con't go over this number.
	 * @param maxLife
	 */
	public static void setMaxLife(int maxLife) {
		startLife = maxLife;
	}
	
	/**
	 * Method that set the role of the king.
	 * @param k
	 */
	public static void setKing(int k) {
		index = k;
		players.get(k).changeRule();
		players.get(k).increasePoints(1);
		currentPlayer = players.get(k);
		//otherPlayer = players.stream().filter(p->!p.isKing()).collect(Collectors.toList());
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isKing() == false) {
				otherPlayer.add(players.get(i));
			}
		}
		for (int i = 0; i < players.size(); i++) {
			playersLife.get(i).setText(String.valueOf(players.get(i).getLife()));
			money.get(i).setText(String.valueOf(players.get(i).getMoney()));
			points.get(i).setText(String.valueOf(players.get(i).getPoints()));
		}
		Game.logger(players.get(k).getName() + " è il King di Forlì!");
		Game.logger(players.get(k).getName() + " INIZIO TURNO");
	}
	
	/**
	 * Getter of the maxLife of the player.
	 * @return
	 */
	public static int getMaxLife() {
		return startLife;
	}
	
	/**
	 * Main of the application. 
	 * @param args
	 */
	public static void main(String[] args) {
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

	/**
	 * Option pane that appear if the king get damage.
	 * @throws IllegalStateException
	 */
	public static void dmgKing() throws IllegalStateException
	{
	  // display the showOptionDialog
	  Object[] options = { "Si", "No" };
	  int choice = JOptionPane.showOptionDialog(Game.getFrame(), 
	      "Vuoi uscire da Forlì?", 
	      "?", 
	      JOptionPane.YES_NO_OPTION, 
	      JOptionPane.QUESTION_MESSAGE, 
	      null, 
	      options, 
	      options[0]);

	  // interpret the user's choice
	  if (choice == JOptionPane.YES_OPTION)
	  {
		  players.forEach(p->{
			  if(p.isKing()) {
				  p.changeRule();
			  }
		  });
		  currentPlayer.changeRule();
		  currentPlayer.increasePoints(1);
		  btnCrown.forEach(b->b.setIcon(null));
		  btnCrown.get(players.indexOf(currentPlayer)).setIcon(new ImageIcon(Game.class.getResource("/res/corona-png.png")));
		  for (int i = 0; i < players.size(); i++) {
				playersLife.get(i).setText(String.valueOf(players.get(i).getLife()));
				money.get(i).setText(String.valueOf(players.get(i).getMoney()));
				points.get(i).setText(String.valueOf(players.get(i).getPoints()));
		  }
	  }
	}

	/**
	 * Method that set the btnCard enabled if the current player can buy it.
	 */
	public static void canBuyCard() {
		indiceCarte = 0;
		mazzo.showFieldCards().forEach(c->{
			if(currentPlayer.getMoneyAndDiscount() >= c.getPrice()) {
				btnCard.get(indiceCarte).setEnabled(true);
			}
			indiceCarte++;
		});
		indiceCarte = 0;
	}
	
	/**
	 * Action listener for the button Info
	 * @throws IllegalStateException
	 */
	public static void gameInfo() throws IllegalStateException
	{
		
	  // display the showOptionDialog
	  Object[] options = { "Ok" };
	  int choice = JOptionPane.showOptionDialog(Game.getFrame(), 
	      desc, 
	      "Info", 
	      JOptionPane.YES_NO_OPTION, 
	      JOptionPane.QUESTION_MESSAGE, 
	      null, 
	      options, 
	      options[0]);
		  
	}
	
	/**
	 * Method that active the effect of the cards.
	 */
	public static void actionBtn() {
		btnCard.forEach(c -> {
			c.addActionListener(e-> {
				if (mazzo.showFieldCards().get(btnCard.indexOf(c)).isSingleUse()) {
					mazzo.showFieldCards().get(btnCard.indexOf(c)).activeCard(currentPlayer, otherPlayer);
					Game.logger(currentPlayer.getName() + " usa "+ mazzo.showFieldCards().get(btnCard.indexOf(c)).getName());
					Game.logger(mazzo.showFieldCards().get(btnCard.indexOf(c)).getDescription());
					currentPlayer.loseMoney(mazzo.showFieldCards().get(btnCard.indexOf(c)).getPrice());
					mazzo.showFieldCards().remove(btnCard.indexOf(c));
					mazzo.addCard(btnCard.indexOf(c));
					Game.logger("Aggiunta al campo carta " + mazzo.showFieldCards().get(btnCard.indexOf(c)).getName()
							+ " - costo " + mazzo.showFieldCards().get(btnCard.indexOf(c)).getPrice());
					c.setToolTipText(mazzo.showFieldCards().get(btnCard.indexOf(c)).getName() + " - "
							+ mazzo.showFieldCards().get(btnCard.indexOf(c)).getPrice() + " - "
							+ mazzo.showFieldCards().get(btnCard.indexOf(c)).getDescription());
					btnCard.forEach(b -> {
						if (mazzo.showFieldCards().get(btnCard.indexOf(b)).getPrice() > currentPlayer.getMoney()) {
							b.setEnabled(false);
						}
					});
					Random icon = new Random();
					int nomeIcona = icon.nextInt(18) + 31;
					c.setIcon(new ImageIcon(Game.class.getResource("/res/" + String.valueOf(nomeIcona) + ".png")));
					for (int i = 0; i < players.size(); i++) {
						playersLife.get(i).setText(String.valueOf(players.get(i).getLife()));
						if (players.get(i).getLife() <= 0) {
							morte(i);
						}
						money.get(i).setText(String.valueOf(players.get(i).getMoney()));
						points.get(i).setText(String.valueOf(players.get(i).getPoints()));
					}
				}else {
					currentPlayer.pickCard(mazzo.showFieldCards().get(btnCard.indexOf(c)), otherPlayer);
					Game.logger(currentPlayer.getName() + " usa "+ mazzo.showFieldCards().get(btnCard.indexOf(c)).getName());
					comboEquip.get(players.indexOf(currentPlayer)).addItem(mazzo.showFieldCards().get(btnCard.indexOf(c)).getName());
					mazzo.showFieldCards().remove(btnCard.indexOf(c));
					mazzo.addCard(btnCard.indexOf(c));
					Game.logger("Aggiunta al campo carta " + mazzo.showFieldCards().get(btnCard.indexOf(c)).getName()
							+ " - costo " + mazzo.showFieldCards().get(btnCard.indexOf(c)).getPrice());
					c.setToolTipText(mazzo.showFieldCards().get(btnCard.indexOf(c)).getName() + " - "
							+ mazzo.showFieldCards().get(btnCard.indexOf(c)).getPrice() + " - "
							+ mazzo.showFieldCards().get(btnCard.indexOf(c)).getDescription());
					btnCard.forEach(b -> {
						if (mazzo.showFieldCards().get(btnCard.indexOf(b)).getPrice() > currentPlayer.getMoney()) {
							b.setEnabled(false);
						}
					});
					Random icon = new Random();
					int nomeIcona = icon.nextInt(18) + 31;
					c.setIcon(new ImageIcon(Game.class.getResource("/res/" + String.valueOf(nomeIcona) + ".png")));
					for (int i = 0; i < players.size(); i++) {
						playersLife.get(i).setText(String.valueOf(players.get(i).getLife()));
						money.get(i).setText(String.valueOf(players.get(i).getMoney()));
						points.get(i).setText(String.valueOf(players.get(i).getPoints()));
				  }
				}
			});
		});
	}

	/**
	 * Method that remove a player that is dead.
	 * @param i
	 */
	private static void morte(int i) {
		if(i <= index) {
			index--;
		}
		Game.logger(players.get(i).getName() + " è morto!");
		int dead = tmpPlayers.indexOf(players.get(i));
		players.remove(i);
		btnCrown.remove(i);
		money.remove(i);
		playersLife.remove(i);
		points.remove(i);	
		comboEquip.remove(i);
		currentPlayer.changeRule();
		btnCrown.get(players.indexOf(currentPlayer)).setIcon(new ImageIcon(Game.class.getResource("/res/corona-png.png")));
		Game.logger("dead "+ dead);
		Game.dead(dead);
	}
	
	

}
