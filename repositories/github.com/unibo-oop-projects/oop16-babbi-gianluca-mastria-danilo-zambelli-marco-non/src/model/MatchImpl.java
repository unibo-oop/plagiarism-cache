package model;

import controller.ApplicationImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MatchImpl implements Match, Serializable{

	private static final long serialVersionUID = 1L;
	
	private int nPlayers; //It contains the number of players who are playing
	private boolean check;	//It's a boolean variable used to check in to some methods.
	private boolean yetChanged;
	private int value;
	private Map<TokenImpl, CellImpl> originCells ; //List of the possibly slot where tokens could go.
	private List<PlayerImpl> players = new LinkedList<>();	//It's the list of the players who are playing
	private Map<TokenImpl, CellImpl> gameBoard = new HashMap<>();
	
	public int getNPlayers() {
		return nPlayers;
	}
	
	public void setNPlayers(int nPlayers) {
		this.nPlayers = nPlayers;
	}
	
	public Map<TokenImpl, CellImpl> getGameBoard() {
		return this.gameBoard;
	}

	public void setGameBoard(Map<TokenImpl, CellImpl> gameBoard) {
		this.gameBoard = gameBoard;
	}

	public List<PlayerImpl> getPlayers(){
		return this.players;
	}
	
	public void setPlayers(List<PlayerImpl> players){
		this.players = players;
	}
	
	//Constructor for PlayerImplTest:
	public MatchImpl(int nPlayers){
		this.nPlayers = nPlayers;
		this.gameBoard.clear();
		this.players.clear();
	}
	
	public MatchImpl(int nPlayers, boolean load){
		ApplicationImpl appC = new ApplicationImpl();
		
		this.gameBoard.put(new TokenImpl(1, Color.BLUE, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
		this.gameBoard.put(new TokenImpl(2, Color.BLUE, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
		this.gameBoard.put(new TokenImpl(3, Color.BLUE, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
		this.gameBoard.put(new TokenImpl(4, Color.BLUE, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
			
		this.gameBoard.put(new TokenImpl(5, Color.ORANGE, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
		this.gameBoard.put(new TokenImpl(6, Color.ORANGE, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
		this.gameBoard.put(new TokenImpl(7, Color.ORANGE, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
		this.gameBoard.put(new TokenImpl(8, Color.ORANGE, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
			
		this.gameBoard.put(new TokenImpl(9, Color.PURPLE, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
		this.gameBoard.put(new TokenImpl(10, Color.PURPLE, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
		this.gameBoard.put(new TokenImpl(11, Color.PURPLE, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
		this.gameBoard.put(new TokenImpl(12, Color.PURPLE, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
			
		this.gameBoard.put(new TokenImpl(13, Color.RED, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
		this.gameBoard.put(new TokenImpl(14, Color.RED, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
		this.gameBoard.put(new TokenImpl(15, Color.RED, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
		this.gameBoard.put(new TokenImpl(16, Color.RED, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
			
		if(nPlayers == 6){	//If there are 6 players, add another 8 pawn.
			this.gameBoard.put(new TokenImpl(17, Color.YELLOW, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
			this.gameBoard.put(new TokenImpl(18, Color.YELLOW, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
			this.gameBoard.put(new TokenImpl(19, Color.YELLOW, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
			this.gameBoard.put(new TokenImpl(20, Color.YELLOW, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
				
			this.gameBoard.put(new TokenImpl(21, Color.GREEN, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
			this.gameBoard.put(new TokenImpl(22, Color.GREEN, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
			this.gameBoard.put(new TokenImpl(23, Color.GREEN, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
			this.gameBoard.put(new TokenImpl(24, Color.GREEN, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
		}
			
		if(!load){
			if(nPlayers == 4){
				String [] names = new String[4];
				Boolean [] areThiefs = new Boolean[4];
				Color [] colors = new Color[]{Color.BLUE, Color.ORANGE, Color.PURPLE, Color.RED};
				Boolean [] areHumans = new Boolean[4];
				names = appC.getNames();
				areThiefs = appC.getThieves();
				areHumans = appC.getHumans();
				for(int i = 0; i < nPlayers; i++){
					players.add(new PlayerImpl(names[i], i+1, 0 , 0, colors[i], areThiefs[i], areHumans[i],4,0));
				}
			}
			else{
				String [] names = new String[6];
				Boolean [] areThiefs = new Boolean[6];
				Color [] colors = new Color[]{Color.BLUE, Color.ORANGE, Color.PURPLE, Color.RED, Color.YELLOW, Color.GREEN};
				Boolean [] areHumans = new Boolean[6];
				names = appC.getNames();
				areThiefs = appC.getThieves();
				areHumans = appC.getHumans();
				for(int i = 0; i < nPlayers; i++){
					players.add(new PlayerImpl(names[i], i+1, 0 , 0, colors[i], areThiefs[i], areHumans[i],4,0));
				}
			}
		}
	}
	
	public PlayerImpl getIstance(int player){
		return players.get(player); 	//Return the current player
	}
	
	public boolean areAllOut(int turns){
		check = false;
		Color color = this.getIstance(turns).getColor();
		players.forEach(p->{
			if(p.getColor() == color && p.getnTokenAtHome() == 0){
				check = true;
			}
		});
		return check;
	}
	
	public boolean isStartOccupied(int turns){
		check = false;
		Color color = this.getIstance(turns).getColor();
		gameBoard.forEach((t,c)->{
			if(t.getTeam() == color && t.getStanding() != Standing.AT_HOME && t.getnStep() == 0){
				check = true;	//If there is a pawn with nStep = 0 it means that the start position it's occupied.
			}
		});
		return check;
	}
	
	
	public List<CellImpl> getMoves(int jump, int turns, int nPlayer){
		originCells = new HashMap<>();
		List<CellImpl> possibleCells = new ArrayList<>(); //List of the possibly cells where tokens could go.
		Color color = this.getIstance(turns).getColor(); //Color of the current player
		gameBoard.forEach((t,c)->{
			gameBoard.forEach((x,y)->{
				if(t.getTeam() == color && t.getStanding() == Standing.PLAYING){
					value = t.getnStep() + jump;
					if(nPlayer == 4){
						if(value >= 40 && value <= 43){ ///Because when the token is put on it has nStep = 0.
							possibleCells.add(new CellImpl(value+1, Standing.ARRIVED));
							originCells.put(t,c);
						}
						else{
							if(c.getPosition() + jump > 40 && value < 40){
								originCells.put(t,c);
								possibleCells.add(new CellImpl(c.getPosition() + jump - 40, Standing.PLAYING));
							}
							else if(value < 40){
								originCells.put(t,c);
								possibleCells.add(new CellImpl(c.getPosition() + jump, Standing.PLAYING));
							}
						}
					}
					else{
						if(value > 48 && value <= 51){
							possibleCells.add(new CellImpl(value+1, Standing.ARRIVED));
							originCells.put(t,c);
						}	
						if(c.getPosition() + jump > 48 && value < 48){
							originCells.put(t,c);
							possibleCells.add(new CellImpl(c.getPosition() + jump - 48, Standing.PLAYING));
						}
						else if(value < 48){
							originCells.put(t,c);
							possibleCells.add(new CellImpl(c.getPosition() + jump, Standing.PLAYING));
						}
					}
				}
			});
		});
		return possibleCells;
	}
	
	public void startToken(int turns, int nPlayer){
		Color color = this.getIstance(turns).getColor(); //Is the color of the current player.
		check = true; 
		yetChanged = true;
		gameBoard.forEach((t,c)->{
			if(nPlayer == 4){
				if(t.getTeam() != color && c.getPosition() == 10*(turns)+1 && yetChanged){ 
					//Reset the token that has been eaten
					yetChanged = false;
					t.setnStep(0);
					t.setStanding(Standing.AT_HOME);
					c.setPosition(0, nPlayer);
					c.setType(Standing.AT_HOME);
					players.forEach(p->{
						if(p.getColor() == t.getTeam()){
							p.incrementNTimesHasBeenEating();			//Increments the counter for the player who has been eaten.
							p.setnTokenAtHome(p.getnTokenAtHome() +1);  //Increment the counter for the player who has been eaten
							p.getPlayerId();
						}
						if(p.getColor() == color){
							p.incrementNTimesHasEated();			//Increments the counter for the player who has eaten.
						}
					});
				}
			}
			
			if(nPlayer == 6){
				if(t.getTeam() != color && c.getPosition() == 8*(turns)+1 && yetChanged){ 
					//Reset the token that has been eaten
					yetChanged = false;
					t.setnStep(0);
					t.setStanding(Standing.AT_HOME);
					c.setPosition(0, nPlayer);
					c.setType(Standing.AT_HOME);
					players.forEach(p->{
						if(p.getColor() == t.getTeam()){
							p.incrementNTimesHasBeenEating();			//Increments the counter for the player who has been eaten.
							p.setnTokenAtHome(p.getnTokenAtHome() +1);  //Increment the counter for the player who has been eaten
							p.getPlayerId();
						}
						if(p.getPlayerId() + 1 == turns){
							p.incrementNTimesHasEated();			//Increments the counter for the player who has eaten.
						}
					});
				}
			}
			
			if(check && t.getTeam() == color && t.getStanding() == Standing.AT_HOME){ //If the player has got a pawn at home:
				t.setnStep(0); 					 //The number of step of the new token is 0.
				t.setStanding(Standing.PLAYING); //Changes the status of the token.
				if(nPlayer == 4){	
					c.setPosition(10*(turns)+1, nPlayer); //Each player has his start slot that changes for the number of player
				}
				else{
					c.setPosition(8*(turns)+1, nPlayer);
				}
				players.get(turns).setnTokenAtHome(players.get(turns).getnTokenAtHome() -1);
				c.setType(Standing.PLAYING);
				check = false; //Check to start only one pawn.
			}
		});
	}

	public void changeGameBoard(int pos, int turns, int dice, int nPlayers) {
		Color color = this.getIstance(turns).getColor(); //Color of the actual player.
		gameBoard.forEach((t,c)->{
			    if(c.getPosition() == pos){ //In the case of the death of a pawn.
					//Reset the token that has been eaten
					t.setnStep(0);
					t.setStanding(Standing.AT_HOME);
					c.setPosition(0, nPlayers);
					c.setType(Standing.AT_HOME);
					players.get(turns).incrementNTimesHasEated(); //Refresh the data of the player who has eaten
					players.forEach(p->{
						if(t.getTeam() == p.getColor()){
							p.incrementNTimesHasBeenEating();		//Refresh the data of the player who has been eaten
							p.setnTokenAtHome(p.getnTokenAtHome() +1); //Increment the counter for the player who has been eaten
							p.getPlayerId();
						}
					});
				}
			    
			    if(t.getTeam() == color && t.getStanding() == Standing.PLAYING){
				    if(c.getPosition() == pos - dice){
						t.setnStep(t.getnStep() + dice); 				//Increase the steps of the number extracted
						c.setPosition(pos,nPlayers);								//Update the new cell's position.
					}
				    
				    if(c.getPosition() == 40 + pos - dice){
				    	t.setnStep(t.getnStep() + dice); 				//Increase the steps of the number extracted
						c.setPosition(pos, nPlayers);
				    }
				    
				    if(c.getPosition() == 48 + pos - dice){
				    	t.setnStep(t.getnStep() + dice); 				//Increase the steps of the number extracted
						c.setPosition(pos, nPlayers);
				    }
			    }
		});
	}
	
	public boolean playerWin(int turns){
		if(players.get(turns).getnTokenArrived() == 4){
			return true;
		}
		return false;
	}

	public Map<TokenImpl, CellImpl> getOriginValues() {
		return originCells;
		
	}
	
	public int getPositionArrived(int turns, int dice, int nPlayers){
		int control;
		if(nPlayers == 4){
			control = 40;
		}
		else{
			control = 48;
		}
		value = 0;
		originCells.forEach((t,c)->{
			if(t.getTeam() == Color.BLUE && turns == 0){
				if((t.getnStep() + dice) >= control)
					value = c.getPosition();
			} else if(t.getTeam() == Color.ORANGE && turns == 1){
					if((t.getnStep() + dice) >= control)
						value = c.getPosition();
			} else if(t.getTeam() == Color.PURPLE && turns == 2){
					if((t.getnStep() + dice) >= control)
						value = c.getPosition();
			} else	if(t.getTeam() == Color.RED && turns == 3){
					if((t.getnStep() + dice) >= control) 
						value = c.getPosition();
			} else	if(t.getTeam() == Color.YELLOW && turns == 4){
				if((t.getnStep() + dice) >= control) 
					value = c.getPosition();
			}else	if(t.getTeam() == Color.GREEN && turns == 5){
				if((t.getnStep() + dice) >= control) 
					value = c.getPosition();
			}
		});
		return value;
	}

	public int getNStepArrived(int turns, int dice, int nPlayers){
		int control;
		if(nPlayers == 4){
			control = 40;
		}
		else{
			control = 48;
		}
		value = 0;
		originCells.forEach((t,c)->{
			int result = t.getnStep() + dice;
			if(t.getTeam() == Color.BLUE && turns == 0){
				if(result > control)
					value = result;
			} else if(t.getTeam() == Color.ORANGE && turns == 1){
					if(result > control)
						value = result;
			} else if(t.getTeam() == Color.PURPLE && turns == 2){
					if(result > control)
						value = result;
			} else	if(t.getTeam() == Color.RED && turns == 3){
					if(result > control)
						value = result;
			} else	if(t.getTeam() == Color.YELLOW && turns == 4){
				if(result > control)
					value = result;
			}else	if(t.getTeam() == Color.GREEN && turns == 5){
				if(result > control)
					value = result;
			}
		});
		return value;
	}
	
	public void changeInArrive(int pos, int nPlayers){
		gameBoard.forEach((t,c)->{
			if(c.getPosition() == pos){
				c.setType(Standing.ARRIVED);		//Change the Stand to arrived.
				t.setStanding(Standing.ARRIVED);	//Change the Stand to arrived.
				if(nPlayers == 4){  				//If there are 4 players:
					c.setPosition(41, nPlayers);	//Position = 41 means out of the game board.
				}
				else{								//Otherwise:
					c.setPosition(49, nPlayers);	//Position = 49 means out of the game board.
				}
				players.forEach(p->{
					if(p.getColor() == t.getTeam()){
						p.setnTokenArrived(p.getnTokenArrived() + 1);
					}
				});
			}
		});
	}
	
	public List<Integer> getMovesCPU(int jump, int turns, int nPlayer){
		originCells = new HashMap<>();
		List<Integer> possibleMoves = new ArrayList<>(); //List of the possibly cells where tokens could go.
		Color color = this.getIstance(turns).getColor(); //Color of the current player
		
		gameBoard.forEach((t,c)->{
				if(t.getTeam() == color && t.getStanding() == Standing.PLAYING){
					value = t.getnStep() + jump;
					if(nPlayer==4 && value <= 43){
						possibleMoves.add(value +1);
						originCells.put(t,c);
					}
					if(nPlayer==6 && value <= 51){
						possibleMoves.add(value +1);
						originCells.put(t,c);
					}
				}
		});
		return possibleMoves;
	}
	
	public int getPositionFromNStep(int nStep, int turns, int nPlayer){
		int position;
		if(nPlayer == 4){
			position = nStep + 10 * (turns);	//Because players haven't the same start button
			if(position>40){
				position -=40;
			}
		}
		else{
			position = nStep + 8 * (turns);		//Because players haven't the same start button
			if(position>48){
				position -=48;
			}
		}
		return position;
	}
	
	public void addPlayer(String name, int idP, Color color){
		players.add(new PlayerImpl(name, idP, color));
	}
	
	public void addToken(int idToken, Color color){
		this.gameBoard.put(new TokenImpl(idToken, color, Standing.AT_HOME), new CellImpl(0, Standing.AT_HOME));
	}
}