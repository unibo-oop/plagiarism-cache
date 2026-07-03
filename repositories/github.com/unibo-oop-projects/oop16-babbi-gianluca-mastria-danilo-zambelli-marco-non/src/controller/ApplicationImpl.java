package controller;

import view.AllListImpl;
import view.Home;
import view.RegistrationPlayersImpl;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.LinkedList;

import model.CellImpl;
import model.Dice;
import model.DiceImpl;
import model.MatchImpl;
import model.PlayerImpl;
import model.Standing;

public class ApplicationImpl implements Application{

	private RegistrationPlayersImpl regPlayers = new RegistrationPlayersImpl();
	private static MatchImpl m;
	private AllListImpl allList = new AllListImpl();
	private int nPlaying;		//Number of the player's tokens in playing use for some controls
	private static int turns = 1;		//Number that represent the turn
	private static int lastDice;		//Value of the random number
	private boolean playing;		//True if a player has three tokens in playing use for some controls
	private boolean changeTable;
	private boolean arrivingBlue;
	private boolean arrivingOrange;
	private boolean arrivingPurple;
	private boolean arrivingRed;
	private boolean arrivingYellow;
	private boolean arrivingGreen;

	public ApplicationImpl() {}

	public static void main (final String[] args) throws Exception {
		Home home = new Home();
	}
	
	public boolean getPlaying() {
		return playing;
	}

	public boolean isArrivingYellow() {
		return arrivingYellow;
	}
	
	public void setArrivingYellow(boolean arrivingYellow){
		this.arrivingYellow = arrivingYellow;
	}

	public boolean isArrivingGreen() {
		return arrivingGreen;
	}
	
	public void setArrivingGreen(boolean arrivingGreen){
		this.arrivingGreen = arrivingGreen;
	}
	
	public boolean isArrivingBlue() {
		return arrivingBlue;
	}
	
	public void setArrivingBlue(boolean arrivingBlue){
		this.arrivingBlue = arrivingBlue;
	}

	public boolean isArrivingOrange() {
		return arrivingOrange;
	}
	
	public void setArrivingOrange(boolean arrivingOrange){
		this.arrivingOrange = arrivingOrange;
	}

	public boolean isArrivingPurple() {
		return arrivingPurple;
	}
	
	public void setArrivingPurple(boolean arrivingPurple){
		this.arrivingPurple = arrivingPurple;
	}

	public boolean isArrivingRed() {
		return arrivingRed;
	}
	
	public void setArrivingRed(boolean arrivingRed){
		this.arrivingRed = arrivingRed;
	}

	public static MatchImpl getM() {
		return m;
	}

	public static void setM(MatchImpl m) {
		ApplicationImpl.m = m;
	}

	public boolean isChangeTable() {
		return changeTable;
	}

	public void setChangeTable(boolean changeTable) {
		this.changeTable = changeTable;
	}
	
	public int getlastDice(){
		return ApplicationImpl.lastDice;
	}

	public void setLastDice(int lastDice) {
		ApplicationImpl.lastDice = lastDice;
	}

	public int getTurns(){
		return ApplicationImpl.turns;
	}

	public void setTurns(int turns) {
		ApplicationImpl.turns = turns;
	}

	public List<PlayerImpl> getPlayers(){
		return getM().getPlayers();
	}

	public String[] getNames() {
		return regPlayers.getNames();	
	}
	
	public Boolean[] getThieves() {
		return regPlayers.getThieves();
	}

	public Boolean[] getHumans() {
		return regPlayers.getHumans();
	}

	public void startMatch(int nPlayers, boolean load){
		if(nPlayers == 4){
			setM(new MatchImpl(4, load));
		}
		else{
			setM(new MatchImpl(6, load));
		}	
	}

	public PlayerImpl getPlayer(){
		PlayerImpl p = getM().getIstance(turns-1); 
		return p; //Return the player who is gaming in this turn.
	}

	public int rollDice(){
		Dice dice = new DiceImpl();
		int result;
		if(getM().getIstance(turns-1).isAThief()){ 	//If is a thief:
			result = dice.getValueThief();
		}
		else{									//If isn't a thief
			result = dice.getValue();
		}
		return result;	
	}

	public void increaseTurns(int nPlayers){
		if((nPlayers == 4 && turns == 4) || (nPlayers == 6 && turns == 6)) //If turn reach the max possible value
		{
			turns = 1; 
		}
		else{
			turns ++; //Increments turn
		}
	}

	public List<Integer> manageTurns(int nPlayers){
		playing = true;
		List<Integer> possibleMoves = new LinkedList<>();
		List<Integer> orderedPossibleMoves = new LinkedList<>();
		lastDice = rollDice();

		if(lastDice == 6) {  	//If dice is six
			if(getM().areAllOut(turns-1)){		//If all token are playing, player can move with a six
				getM().getMoves(lastDice, (turns-1), nPlayers).forEach(c->{
					possibleMoves.add(c.getPosition());
				});
			}
			if(!isStartOccupied() && getPlayer().getnTokenArrived() == 3){ //If the start cell is empty and all token are playing
					getM().getGameBoard().forEach((t,c)->{					   //but player can't move the token
						if(t.getTeam() == getPlayer().getColor()){
							if(t.getStanding() == Standing.PLAYING){
								playing = false;
							}
						}
				});
			}
				if(!isStartOccupied() && playing){		//If the start cell is empty put a token on it
					getM().startToken(turns-1, nPlayers); 
				}
		} 
		else { 
			getM().getMoves(lastDice, (turns-1), nPlayers).forEach(c->{		//if dice isn't six player can move a token
				possibleMoves.add(c.getPosition());
			});
		}
		orderedPossibleMoves = this.bubbleSort(possibleMoves);  	//Order the possible moves of the player's tokens
		return orderedPossibleMoves;
	}

	public List<Integer> manageTurnsCPU(int nPlayers){
		playing = true;
		List<Integer> possibleMoves = new LinkedList<>();
		List<Integer> possibleMovesOrdinata = new LinkedList<>();
		lastDice = rollDice();

		if(lastDice == 6){		//If dice is six
			if(getM().areAllOut(turns-1)){		//If all token are playing, player can move with a six
				possibleMoves = getM().getMovesCPU(lastDice, (turns-1), nPlayers);
			}
			else {
				if(!isStartOccupied() && getPlayer().getnTokenArrived() == 3){ //If the start cell is empty and all token are playing
					getM().getGameBoard().forEach((t,c)->{					   //but player can't move the token
						if(t.getTeam() == getPlayer().getColor()){
							if(t.getStanding() == Standing.PLAYING){
								playing = false;
							}
						}
					});
				}	
				if(!isStartOccupied() && playing){		//If the start cell is empty put a token on it
					getM().startToken(turns-1, nPlayers); 
				}		
			}
		} else{
			possibleMoves = getM().getMovesCPU(lastDice, (turns-1), nPlayers);		//if dice isn't six player can move a token
		}
		possibleMovesOrdinata = this.bubbleSort(possibleMoves);		//Order the possible moves of the player's tokens
		return possibleMovesOrdinata;
	}

	public void moveToken(int pos, int nPlayers){
		getM().changeGameBoard(pos, (turns-1), lastDice, nPlayers);
		refreshBaseButtons();	
	}

	public boolean isStartOccupied(){
		return getM().isStartOccupied(turns-1);
	}

	public List<CellImpl> getOriginPosition(){
		List<CellImpl> originPosition = new ArrayList<>();
		getM().getOriginValues().forEach((t,c)->{		//Return only the position of the tokens before the player do a moves
			originPosition.add(c);
		});
		return originPosition;
	}

	public void initializeBooleanVariables(int nPlayer){
		setChangeTable(false);
		arrivingBlue = false;
		arrivingOrange = false;
		arrivingPurple = false;
		arrivingRed = false;
		if (nPlayer == 6) {
			arrivingYellow = false;
			arrivingGreen = false;
		}
	}

	public void refreshBaseButtons(){
		getPlayers().forEach(p->{
			switch(p.getPlayerId()){
			case 1:
				nPlaying = 0;
				m.getGameBoard().forEach((t,c)->{
					if(t.getTeam() ==p.getColor() && t.getStanding() != Standing.AT_HOME){
						nPlaying++;		//Count how many token aren't in base
					}
				});
				for(int i=0; i < nPlaying; i++){		//set image of token in base
					allList.getListBasePlayerBlue().get(i).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_blue.png")));
				}
				for(int i=nPlaying; i < 4; i++){		//set image of token not in base
					allList.getListBasePlayerBlue().get(i).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_blue_cross.png")));
				}
				break;
			case 2:
				nPlaying = 0;
				m.getGameBoard().forEach((t,c)->{
					if(t.getTeam() ==p.getColor() && t.getStanding() != Standing.AT_HOME){
						nPlaying++;		//Count how many token aren't in base
					}
				});
				for(int i=0; i < nPlaying; i++){		//set image of token in base
					allList.getListBasePlayerOrange().get(i).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_orange.png")));
				}
				for(int i=nPlaying; i < 4; i++){		//set image of token not in base
					allList.getListBasePlayerOrange().get(i).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_orange_cross.png")));
				}
				break;
			case 3:
				nPlaying = 0;
				m.getGameBoard().forEach((t,c)->{
					if(t.getTeam() ==p.getColor() && t.getStanding() != Standing.AT_HOME){
						nPlaying++;		//Count how many token aren't in base
					}
				});
				for(int i=0; i < nPlaying; i++){		//set image of token in base
					allList.getListBasePlayerPurple().get(i).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_purple.png")));
				}
				for(int i=nPlaying; i < 4; i++){		//set image of token not in base
					allList.getListBasePlayerPurple().get(i).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_purple_cross.png")));
				}
			case 4:
				nPlaying = 0;
				m.getGameBoard().forEach((t,c)->{
					if(t.getTeam() ==p.getColor() && t.getStanding() != Standing.AT_HOME){
						nPlaying++;		//Count how many token aren't in base
					}
				});
				for(int i=0; i < nPlaying; i++){		//set image of token in base
					allList.getListBasePlayerRed().get(i).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_red.png")));
				}
				for(int i=nPlaying; i < 4; i++){		//set image of token not in base
					allList.getListBasePlayerRed().get(i).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_red_cross.png")));
				}
				break;
			case 5:
				nPlaying = 0;
				m.getGameBoard().forEach((t,c)->{
					if(t.getTeam() ==p.getColor() && t.getStanding() != Standing.AT_HOME){
						nPlaying++;		//Count how many token aren't in base
					}
				});
				for(int i=0; i < nPlaying; i++){		//set image of token in base
					allList.getListBasePlayerYellow().get(i).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_yellow.png")));
				}
				for(int i=nPlaying; i < 4; i++){		//set image of token not in base
					allList.getListBasePlayerYellow().get(i).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_yellow_cross.png")));
				}
				break;
			case 6:
				nPlaying = 0;
				m.getGameBoard().forEach((t,c)->{
					if(t.getTeam() ==p.getColor() && t.getStanding() != Standing.AT_HOME){
						nPlaying++;		//Count how many token aren't in base
					}
				});
				for(int i=0; i < nPlaying; i++){		//set image of token in base
					allList.getListBasePlayerGreen().get(i).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_green.png")));
				}
				for(int i=nPlaying; i < 4; i++){		//set image of token not in base
					allList.getListBasePlayerGreen().get(i).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_green_cross.png")));
				}
				break;
			}
		});
	}

	public void refreshArriveButtons(){
		getPlayers().forEach(p->{
			switch(p.getPlayerId()){
			case 1:
				allList.getListArrivedPlayerBlue().forEach((x->{
					if(x.getY() == true){		//if there is a token in arrive cell set the image of a token on it
						x.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_blue_baby_cross.png")));
					}
				}));
				break;
			case 2:
				allList.getListArrivedPlayerOrange().forEach((x->{
					if(x.getY() == true){		//if there is a token in arrive cell set the image of a token on it
						x.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_orange_baby_cross.png")));
					}
				}));
			case 3:
				allList.getListArrivedPlayerPurple().forEach((x->{
					if(x.getY() == true){		//if there is a token in arrive cell set the image of a token on it
						x.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_purple_baby_cross.png")));
					}
				}));
			case 4:
				allList.getListArrivedPlayerRed().forEach((x->{
					if(x.getY() == true){		//if there is a token in arrive cell set the image of a token on it
						x.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_Red_baby_cross.png")));
					}
				}));
			case 5:
				allList.getListArrivedPlayerYellow().forEach((x->{
					if(x.getY() == true){		//if there is a token in arrive cell set the image of a token on it
						x.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_yellow_baby_cross.png")));
					}
				}));
				break;
			case 6:
				allList.getListArrivedPlayerGreen().forEach((x->{
					if(x.getY() == true){		//if there is a token in arrive cell set the image of a token on it
						x.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_green_baby_cross.png")));
					}
				}));
				break;
			}
		});
	}

	public void setColorToken(int player, JButton b){
		switch(player){
		case 1:
			b.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white-blueCross.png")));
			break;
		case 2:
			b.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white-orangeCross.png")));
			break;
		case 3:
			b.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white-purpleCross.png")));
			break;
		case 4:
			b.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white-redCross.png")));
			break;
		case 5:
			b.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white-yellowCross.png")));
			break;
		case 6:
			b.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white-greenCross.png")));
			break;
		}
	}

	public void setAllNotEnabled(){
		allList.getRouteButton().forEach(jb->{
			jb.getX().setEnabled(false);
		});
	}

	public void setAllEnabled(){
		allList.getRouteButton().forEach(jb->{
			jb.getX().setEnabled(true);
		});
	}

	public void refreshGameboard(){	
		for (int i=0; i< allList.getRouteButton().size(); i++){
			if(allList.getRouteButton().get(i).getY() != 0){
				setColorToken(allList.getRouteButton().get(i).getY(), allList.getRouteButton().get(i).getX());
			}
		}
	}

	public List<Integer> bubbleSort(List<Integer> list) {
		int [] array = new int[list.size()];
		for(int i = 0; i < array.length; i++){
			array[i] = list.get(i);		//Copy the values in a vector
		}
		for(int i = 0; i < array.length; i++) {
			boolean flag = false;
			for(int j = 0; j < array.length-1; j++) {
				if(array[j]<array[j+1]) {		//If element j is bigger then next exchange the value
					int k = array[j];
					array[j] = array[j+1];
					array[j+1] = k;
					flag=true;		//True because there is an exchange
				}          
			}
			if(!flag) break; //If flag is false there aren't exchange so the vector is order
		}        
		list.clear();
		for(int i = 0; i < array.length; i++){
			list.add(array[i]);
		}
		return list;
	} 
}

