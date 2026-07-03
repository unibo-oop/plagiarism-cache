package controller;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.AllListImpl;
import view.MatchStatic;
import view.Pair;
import view.Start4Impl;
import view.Start6Impl;

public class GameActionImpl implements GameAction{

	ApplicationImpl appC;
	AllListImpl allList = new AllListImpl();
	private int i;						//Used to save the old position of a token
	private boolean isStartOcc;			//Used to verify if start position is occupied
	private boolean CPUYetChanged;		//Used to verify if the CPU player moved a token
	private boolean alreadyChange;		//Used to start token controls
	
	public GameActionImpl(ApplicationImpl appC) {
		this.appC = appC;
	}
	
	/**
	 * This method change the image of a start button of the game board
	 * 
	 * @param player
	 * 		player that inserts a token
	 * @param variable
	 * 		it's the value used to calculate where is the start position of a player
	 */
	private void setColorStartToken(int player, int variable){
		alreadyChange = false;
		switch(player){
		case 1:
			allList.getRouteButton().get((appC.getTurns()-1)*variable).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white-blueCross.png")));
			allList.getRouteButton().get((appC.getTurns()-1)*variable).setY(appC.getTurns());
			allList.getListBasePlayerBlue().forEach(x->{
				if(!alreadyChange && x.getY() == true){		//Delete image of token in base
					x.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_blue.png")));
					x.setY(false);
					alreadyChange = true;
				}
			});
			break;
		case 2:
			allList.getRouteButton().get((appC.getTurns()-1)*variable).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white-orangeCross.png")));
			allList.getRouteButton().get((appC.getTurns()-1)*variable).setY(appC.getTurns());
			allList.getListBasePlayerOrange().forEach(x->{
				if(!alreadyChange && x.getY() == true){		//Delete image of token in base
					x.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_orange.png")));
					x.setY(false);
					alreadyChange = true;
				}
			});
			break;
		case 3:
			allList.getRouteButton().get((appC.getTurns()-1)*variable).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white-purpleCross.png")));
			allList.getRouteButton().get((appC.getTurns()-1)*variable).setY(appC.getTurns());
			allList.getListBasePlayerPurple().forEach(x->{
				if(!alreadyChange && x.getY() == true){		//Delete image of token in base
					x.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_purple.png")));
					x.setY(false);
					alreadyChange = true;
				}
			});
			break;
		case 4:
			allList.getRouteButton().get((appC.getTurns()-1)*variable).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white-redCross.png")));
			allList.getRouteButton().get((appC.getTurns()-1)*variable).setY(appC.getTurns());
			allList.getListBasePlayerRed().forEach(x->{
				if(!alreadyChange && x.getY() == true){		//Delete image of token in base
					x.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_red.png")));
					x.setY(false);
					alreadyChange = true;
				}
			});
			break;
		case 5:
			allList.getRouteButton().get((appC.getTurns()-1)*variable).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white-yellowCross.png")));
			allList.getRouteButton().get((appC.getTurns()-1)*variable).setY(appC.getTurns());
			allList.getListBasePlayerYellow().forEach(x->{ 
				if(!alreadyChange && x.getY() == true){		//Delete image of token in base
					x.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_yellow.png")));
					x.setY(false);
					alreadyChange = true;
				}
			});
			break;
		case 6:
			allList.getRouteButton().get((appC.getTurns()-1)*variable).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white-greenCross.png")));
			allList.getRouteButton().get((appC.getTurns()-1)*variable).setY(appC.getTurns());
			allList.getListBasePlayerGreen().forEach(x->{
				if(!alreadyChange && x.getY() == true){		//Delete image of token in base
					x.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_green.png")));
					x.setY(false);
					alreadyChange = true;
				}
			});
			break;
		}
	}
	
	/**
	 * This method change the image of the arrive buttons when a player choose the move of a token
	 * 
	 * @param player
	 * 		number of players
	 */
	private void unsetEnableArrived(int player){
		switch(player){
		case 1:
			allList.getListArrivedPlayerBlue().forEach(jb->{
				if(jb.getY() == false){		//If there isn't a token on an arrive cell
					jb.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_blue_baby.png")));
				}
				jb.getX().setEnabled(true);
			});
			appC.setArrivingBlue(false);
			break;
		case 2:
			allList.getListArrivedPlayerOrange().forEach(jb->{
				if(jb.getY() == false){		//If there isn't a token on an arrive cell
					jb.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_orange_baby.png")));
				}
				jb.getX().setEnabled(true);
			});
			appC.setArrivingOrange(false);
			break;
		case 3:
			allList.getListArrivedPlayerPurple().forEach(jb->{
				if(jb.getY() == false){		//If there isn't a token on an arrive cell
					jb.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_purple_baby.png")));
				}
				jb.getX().setEnabled(true);
			});
			appC.setArrivingPurple(false);
			break;
		case 4:
			allList.getListArrivedPlayerRed().forEach(jb->{
				if(jb.getY() == false){		//If there isn't a token on an arrive cell
					jb.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_red_baby.png")));
				}
				jb.getX().setEnabled(true);
			});
			appC.setArrivingRed(false);
			break;
		case 5:
			allList.getListArrivedPlayerYellow().forEach(jb->{
				if(jb.getY() == false){		//If there isn't a token on an arrive cell
					jb.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_yellow_baby.png")));
				}
				jb.getX().setEnabled(true);
			});
			appC.setArrivingYellow(false);
			break;
		case 6:
			allList.getListArrivedPlayerGreen().forEach(jb->{
				if(jb.getY() == false){		//If there isn't a token on an arrive cell
					jb.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_green_baby.png")));
				}
				jb.getX().setEnabled(true);
			});
			appC.setArrivingGreen(false);
			break;
		}
	}

	/**
	 * This method change the image of the arrive buttons if a token can finish the trip
	 * 
	 * @param player
	 * 		number of players
	 */
	private void setEnableArrived(int player){
		switch(player){
		case 0:
			allList.getListArrivedPlayerBlue().forEach(jb->{
				if(jb.getY() == false)		//If there isn't a token on an arrive cell
					jb.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_blue_baby_highlighted.png")));
				else
					jb.getX().setEnabled(false);
			});
			appC.setArrivingBlue(true);
			break;
		case 1:
			allList.getListArrivedPlayerOrange().forEach(jb->{
				if(jb.getY() == false)		//If there isn't a token on an arrive cell
					jb.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_orange_baby_highlighted.png")));
				else
					jb.getX().setEnabled(false);
			});
			appC.setArrivingOrange(true);
			break;
		case 2:
			allList.getListArrivedPlayerPurple().forEach(jb->{
				if(jb.getY() == false)		//If there isn't a token on an arrive cell
					jb.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_purple_baby_highlighted.png")));
				else
					jb.getX().setEnabled(false);
			});
			appC.setArrivingPurple(true);
			break;
		case 3:
			allList.getListArrivedPlayerRed().forEach(jb->{
				if(jb.getY() == false)		//If there isn't a token on an arrive cell
					jb.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_red_baby_highlighted.png")));
				else
					jb.getX().setEnabled(false);
			});
			appC.setArrivingRed(true);
			break;
		case 4:
			allList.getListArrivedPlayerYellow().forEach(jb->{
				if(jb.getY() == false)		//If there isn't a token on an arrive cell
					jb.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_yellow_baby_highlighted.png")));
				else
					jb.getX().setEnabled(false);
			});
			appC.setArrivingYellow(true);
			break;
		case 5:
			allList.getListArrivedPlayerGreen().forEach(jb->{
				if(jb.getY() == false)		//If there isn't a token on an arrive cell
					jb.getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button_green_baby_highlighted.png")));
				else
					jb.getX().setEnabled(false);
			});
			appC.setArrivingGreen(true);
			break;
		}
	}
	
	public void routeAction(JButton b, int nPlayers,  JButton button, Start4Impl start4, Start6Impl start6, int n){
		if(appC.isChangeTable()){
			appC.setColorToken(appC.getTurns(), button);		//Change image of the button choose by the player
			allList.getRouteButton().get(Integer.valueOf(b.getName())-1).setY(appC.getTurns());		//Set the number of the button with the player id
			
			//Find the old position of the token and delete it from the map
			appC.getOriginPosition().forEach(y->{
				if(y.getPosition() == Integer.parseInt(b.getName()) - appC.getlastDice()){
					allList.getRouteButton().get(y.getPosition()-1).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white.png"))); //-1 perchè lo zero di la è l'uno di qua
					allList.getRouteButton().get(y.getPosition()-1).setY(0);		//Set 0 because now there isn't a token.
				}
				else if(y.getPosition() == Integer.parseInt(b.getName()) - appC.getlastDice() + n){
					allList.getRouteButton().get(y.getPosition()-1).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white.png"))); //-1 perchè lo zero di la è l'uno di qua
					allList.getRouteButton().get(y.getPosition()-1).setY(0);		//Set 0 because now there isn't a token.
				}
			});
			
			appC.setAllEnabled();
			appC.moveToken(Integer.parseInt(b.getName()), nPlayers);		//Change the game information on the model
			unsetEnableArrived(appC.getTurns());	
			
			if(appC.getlastDice() != 6){
				appC.increaseTurns(nPlayers);		//Increase the turn only if the dice isn't six
			}
			appC.initializeBooleanVariables(nPlayers);
			
			if (start4 != null) {
				start4.showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn.");
				start4.setDiceEnabled(true); 
			} else {
				start6.showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn.");
				start6.setDiceEnabled(true);
			}
		}
		if (start4 != null) {
			start4.setTextResult("");
		} else {
			start6.setTextResult("");
		}
	}

	public void arriveAction(ImageIcon image, JButton b, List<Pair<JButton, Boolean>> list, int nPlayers, Start4Impl start4, Start6Impl start6, int n) {
		int t = ApplicationImpl.getM().getPositionArrived(appC.getTurns()-1, appC.getlastDice(), nPlayers) -1;	//get the old position of the token arrived	
		allList.getRouteButton().get(t).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white.png")));
		allList.getRouteButton().get(t).setY(0);		//Set 0 because now there isn't a token.
		ApplicationImpl.getM().changeInArrive(t+1, nPlayers);		//Change the game information on the model
		
		//Change image and boolean value of the arrive button selected
		b.setIcon(image);
		list.forEach(x->{
			if(x.getX().getName() == b.getName())
				x.setY(true);
		});

		unsetEnableArrived(appC.getTurns());
		appC.refreshBaseButtons();
		
		if(ApplicationImpl.getM().playerWin(appC.getTurns()-1)){		//If a player wins close the game board and show match static
			if (nPlayers != 6) {
				start4.showMessage(appC.getPlayer().getName().toUpperCase()+ ": WIN.");
				start4.getFrame().dispose();	
				new MatchStatic(4, appC, true, null);
			} else {
				start6.showMessage(appC.getPlayer().getName().toUpperCase()+ ": WIN.");
				start6.getFrame().dispose();
				new MatchStatic(6, appC, true, null);
			}
		}
		else if(appC.getlastDice() != 6 && appC.getPlayer().getIsHuman()){
			appC.increaseTurns(nPlayers);
			if (nPlayers != 6) {
				start4.showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn." );
			} else {
				start6.showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn." );
			}
		}
		appC.setChangeTable(false);
		appC.setAllEnabled();
		
		if (start4 != null) {
			start4.setDiceEnabled(true);
			start4.setTextResult("");
		} else {
			start6.setDiceEnabled(true);
			start6.setTextResult("");
		}	
	}
	
	public void game4(int nPlayers, Start4Impl start4){
		if(appC.getPlayer().getIsHuman()){
			isStartOcc = appC.isStartOccupied();
			List<Integer> newPosition = appC.manageTurns(nPlayers);		//Roll dice and return the list of the possible moves
			start4.setTextResult(String.valueOf(appC.getlastDice()));
			start4.setDiceEnabled(false);
			
			if(newPosition.size() == 0 && appC.getlastDice() == 6){
				if(isStartOcc)
				{
					start4.showMessage(appC.getPlayer().getName().toUpperCase() + ": start position is occupied.");
				}
				else{
					if(appC.getPlaying()){									//If the player has another token in base 
						setColorStartToken(appC.getTurns(), 10);		//put the token in the start position
						appC.refreshBaseButtons();
						start4.showMessage(appC.getPlayer().getName().toUpperCase() + ": token inserted in the starting position.");
					}
					else{
						start4.showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn.");
					}
				}
				start4.setTextResult("");
				start4.setDiceEnabled(true);			
			}
			else{ 
				if(newPosition != null && newPosition.size() != 0){ 
					appC.setAllNotEnabled();
					newPosition.forEach(x->{
						if(x>40){
							setEnableArrived(appC.getTurns()-1);		//Arrive buttons set enabled because token could arrive
						}
						else{
							if(allList.getRouteButton().get(x-1).getY() !=  appC.getTurns()){
								allList.getRouteButton().get(x-1).getX().setEnabled(true);		//Set enabled the possible moves of tokens
							}
						}
					});
					appC.setChangeTable(true);
				}
				else{ 		//If last dice isn't six and player doesn't have possible moves	
					appC.increaseTurns(nPlayers);
					start4.showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn.");
					start4.setDiceEnabled(true);
					start4.setTextResult("");				
				}
			}
		}
		else{
			//If is a CPU player
			start4.setDiceEnabled(false);
			isStartOcc = appC.isStartOccupied();
			List<Integer> newPosition = appC.manageTurnsCPU(nPlayers);		//Roll dice and return the list of the possible moves
			start4.setTextResult(String.valueOf(appC.getlastDice()));							
			if(newPosition.size() == 0 && appC.getlastDice() == 6){
				if(isStartOcc)
				{
					start4.showMessage(appC.getPlayer().getName().toUpperCase() + ": start position is occupied.");
				}
				else{
					if(appC.getPlaying()){									//If the player has another token in base 
						setColorStartToken(appC.getTurns(), 10); 		//put the token in the start position
						appC.refreshBaseButtons();
						start4.showMessage(appC.getPlayer().getName().toUpperCase() + ": token inserted in the starting position.");
					}
					else{
						start4.showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn.");
					}
				}
				start4.setTextResult("");
				start4.setDiceEnabled(true);
			}				
			else{  	//If last dice isn't six
				CPUYetChanged = false; 
				if(newPosition != null && newPosition.size() != 0){ 
					newPosition.forEach(x->{
						if(x>40 && CPUYetChanged == false){		//Token can finish the trip
							switch(appC.getTurns()){
							case 1:
								allList.getListArrivedPlayerBlue().forEach(arrivedBlue->{
									if(arrivedBlue.getY() == false && CPUYetChanged == false){
										arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_blue_baby_cross.png")), arrivedBlue.getX(), allList.getListArrivedPlayerBlue(), nPlayers, start4, null, 43);
										arrivedBlue.setY(true);		//Set the token on the arrive list
										CPUYetChanged = true;		//Because CPU player moves
									}
								});
								break;
							case 2:
								allList.getListArrivedPlayerOrange().forEach(arrivedOrange->{
									if(arrivedOrange.getY() == false && CPUYetChanged == false){
										arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_orange_baby_cross.png")), arrivedOrange.getX(), allList.getListArrivedPlayerOrange(), nPlayers, start4, null, 43);
										arrivedOrange.setY(true);	//Set the token on the arrive list
										CPUYetChanged = true;		//Because CPU player moves
									}
								});
								break;
							case 3:
								allList.getListArrivedPlayerPurple().forEach(arrivedPurple->{
									if(arrivedPurple.getY() == false && CPUYetChanged == false){
										arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_purple_baby_cross.png")), arrivedPurple.getX(), allList.getListArrivedPlayerPurple(), nPlayers, start4, null, 43);
										arrivedPurple.setY(true);	//Set the token on the arrive list
										CPUYetChanged = true;		//Because CPU player moves
									}
								});
								break;
							case 4:
								allList.getListArrivedPlayerRed().forEach(arrivedRed->{
									if(arrivedRed.getY() == false && CPUYetChanged == false){
										arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_red_baby_cross.png")), arrivedRed.getX(), allList.getListArrivedPlayerRed(), nPlayers, start4, null, 43);
										arrivedRed.setY(true);		//Set the token on the arrive list
										CPUYetChanged = true;		//Because CPU player moves
									}
								});
								break;
							}
						}
					});

					newPosition.forEach(x->{	
						if(!CPUYetChanged){
							i = ApplicationImpl.getM().getPositionFromNStep(x, appC.getTurns()-1, nPlayers);	//Get the old position of the token
							if(allList.getRouteButton().get(i-1).getY() != 0 && allList.getRouteButton().get(i-1).getY() != appC.getTurns()){
								//If a token can eat another token of a different player
								appC.setColorToken(appC.getTurns(), allList.getRouteButton().get(i-1).getX());
								allList.getRouteButton().get(i-1).setY(appC.getTurns());		//Set the number of the button with the player id
								appC.getOriginPosition().forEach(y->{
									if(y.getPosition() == i - appC.getlastDice()){
										allList.getRouteButton().get(y.getPosition()-1).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white.png"))); //-1 perchè lo zero di la è l'uno di qua
										allList.getRouteButton().get(y.getPosition()-1).setY(0);	//Set 0 because now there isn't a token.
										appC.moveToken(i, nPlayers);
									}
									else if(y.getPosition() == i - appC.getlastDice() + 40){
										allList.getRouteButton().get(y.getPosition()-1).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white.png"))); //-1 perchè lo zero di la è l'uno di qua
										allList.getRouteButton().get(y.getPosition()-1).setY(0);	//Set 0 because now there isn't a token.
										appC.moveToken(i, nPlayers);
									}
								});
								CPUYetChanged = true;		//Because CPU player moves
							}
						}
					});

					if(!CPUYetChanged){
						//Move a token in starting position
						appC.getOriginPosition().forEach(x->{
							if(x.getPosition()==(appC.getTurns()-1)*10+1 && !CPUYetChanged){
								if(allList.getRouteButton().get(x.getPosition()+appC.getlastDice()-1).getY() != appC.getTurns()){
									appC.setColorToken(appC.getTurns(), allList.getRouteButton().get(x.getPosition()+appC.getlastDice()-1).getX());
									allList.getRouteButton().get(x.getPosition()+appC.getlastDice()-1).setY(appC.getTurns());	//Set the number of the button with the player id
									allList.getRouteButton().get(x.getPosition()-1).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white.png")));
									allList.getRouteButton().get(x.getPosition()-1).setY(0);		//Set 0 because now there isn't a token.
									appC.moveToken(x.getPosition()+appC.getlastDice(), nPlayers);
									CPUYetChanged = true;		//Because CPU player moves
								}
							}
						});
					}

					if(!CPUYetChanged){
						//Move the token nearest arrive cells
						i = ApplicationImpl.getM().getPositionFromNStep(newPosition.get(0), appC.getTurns()-1, nPlayers);
						allList.getRouteButton().forEach(jb->{
							if(Integer.parseInt(jb.getX().getName()) == i && jb.getY() != appC.getTurns()){
								appC.setColorToken(appC.getTurns(), jb.getX());
								jb.setY(appC.getTurns());	//Set the number of the button with the player id
							}
						});

						appC.getOriginPosition().forEach(y->{
							if(y.getPosition() == i - appC.getlastDice()){
								allList.getRouteButton().get(y.getPosition()-1).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white.png"))); //-1 perchè lo zero di la è l'uno di qua
								allList.getRouteButton().get(y.getPosition()-1).setY(0);	//Set 0 because now there isn't a token
							}
							else if(y.getPosition() == i - appC.getlastDice() + 40){
								allList.getRouteButton().get(y.getPosition()-1).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white.png"))); //-1 perchè lo zero di la è l'uno di qua
								allList.getRouteButton().get(y.getPosition()-1).setY(0);	//Set 0 because now there isn't a token
							}
						});
						appC.setAllEnabled();
						appC.moveToken(i, nPlayers);						
					}
					CPUYetChanged = true;		//Because CPU player moves							
				}
				appC.setAllEnabled();
				if(appC.getlastDice() != 6){
					appC.increaseTurns(nPlayers);
					start4.showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn." );
				}
				if(appC.getlastDice() == 6){
					start4.showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn.");
				}
				start4.setTextResult("");
				start4.setDiceEnabled(true);
				appC.setChangeTable(false); 
			}
		}
	}


	public void game6(int nPlayers, Start6Impl start6){
		if(appC.getPlayer().getIsHuman()){
			start6.setDiceEnabled(false);
			isStartOcc = appC.isStartOccupied();
			List<Integer> newPosition = appC.manageTurns(nPlayers);		//Roll dice and return the list of the possible moves
			start6.setTextResult(String.valueOf(appC.getlastDice()));
			if(newPosition.size() == 0 && appC.getlastDice() == 6){
				if(isStartOcc)
				{
					start6.showMessage(appC.getPlayer().getName().toUpperCase() + ": start position is occupied.");
				}
				else{
					if(appC.getPlaying()){									//If the player has another token in base 
						setColorStartToken(appC.getTurns(), 8); 		//put the token in the start position
						appC.refreshBaseButtons();
						start6.showMessage(appC.getPlayer().getName().toUpperCase() + ": token inserted in the starting position.");
					}
					else{
						start6.showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn.");
					}
				}
				start6.setTextResult("");
				start6.setDiceEnabled(true);
			}
			else{ 
				if(newPosition != null && newPosition.size() != 0){ 
					appC.setAllNotEnabled();
					newPosition.forEach(x->{
						if(x>48){
							setEnableArrived(appC.getTurns()-1);		//Arrive buttons set enabled because token could arrive
						}
						else{
							if(allList.getRouteButton().get(x-1).getY() !=  appC.getTurns()){
								allList.getRouteButton().get(x-1).getX().setEnabled(true);	//Set enabled the possible moves of tokens
							}
						}
					});
					appC.setChangeTable(true);
				}
				else{ 		//If last dice isn't six and player doesn't have possible moves	
					appC.increaseTurns(nPlayers);
					start6.showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn." );
					start6.setTextResult("");
					start6.setDiceEnabled(true);
				}
			}
		}
		else{
			//If is a CPU player
			isStartOcc = appC.isStartOccupied();
			List<Integer> newPosition = appC.manageTurnsCPU(nPlayers);		//Roll dice and return the list of the possible moves
			start6.setTextResult(String.valueOf(appC.getlastDice()));
			start6.setDiceEnabled(false);

			if(newPosition.size() == 0 && appC.getlastDice() == 6){
				if(isStartOcc)
				{
					start6.showMessage(appC.getPlayer().getName().toUpperCase() + ": start position is occupied.");
				}
				else{
					if(appC.getPlaying()){								//If the player has another token in base 
						setColorStartToken(appC.getTurns(), 8); 	//put the token in the start position
						appC.refreshBaseButtons();
						start6.showMessage(appC.getPlayer().getName().toUpperCase() + ": token inserted in the starting position.");
					}
					else{
						start6.showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn.");
					}
				}
				start6.setTextResult("");
				start6.setDiceEnabled(true);
			}
			else{		//If last dice isn't six
				CPUYetChanged = false;
				if(newPosition != null && newPosition.size() != 0){ 
					newPosition.forEach(x->{
						if(x>48 && CPUYetChanged == false){		//Token can finish the trip
							switch(appC.getTurns()){
							case 1:
								allList.getListArrivedPlayerBlue().forEach(arrivedBlue->{
									if(arrivedBlue.getY() == false && CPUYetChanged == false){
										arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_blue_baby_cross.png")), arrivedBlue.getX(), allList.getListArrivedPlayerBlue(), nPlayers, null, start6, 51);
										arrivedBlue.setY(true);		//Set the token on the arrive list
										CPUYetChanged = true;		//Because CPU player moves
									}
								});
								break;
							case 2:
								allList.getListArrivedPlayerOrange().forEach(arrivedOrange->{
									if(arrivedOrange.getY() == false && CPUYetChanged == false){
										arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_orange_baby_cross.png")), arrivedOrange.getX(), allList.getListArrivedPlayerOrange(), nPlayers, null, start6, 51);
										arrivedOrange.setY(true);	//Set the token on the arrive list
										CPUYetChanged = true;		//Because CPU player moves
									}
								});
								break;
							case 3:
								allList.getListArrivedPlayerPurple().forEach(arrivedPurple->{
									if(arrivedPurple.getY() == false && CPUYetChanged == false){
										arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_purple_baby_cross.png")), arrivedPurple.getX(), allList.getListArrivedPlayerPurple(), nPlayers, null, start6, 51);
										arrivedPurple.setY(true);	//Set the token on the arrive list
										CPUYetChanged = true;		//Because CPU player moves
									}
								});
								break;
							case 4:
								allList.getListArrivedPlayerRed().forEach(arrivedRed->{
									if(arrivedRed.getY() == false && CPUYetChanged == false){
										arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_red_baby_cross.png")), arrivedRed.getX(), allList.getListArrivedPlayerRed(), nPlayers, null, start6, 51);
										arrivedRed.setY(true);		//Set the token on the arrive list
										CPUYetChanged = true;		//Because CPU player moves
									}
								});
								break;
							case 5:
								allList.getListArrivedPlayerYellow().forEach(arrivedYellow -> {
									if(arrivedYellow.getY() == false && CPUYetChanged == false){
										arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_yellow_baby_cross.png")), arrivedYellow.getX(), allList.getListArrivedPlayerYellow(), nPlayers, null, start6, 51);
										arrivedYellow.setY(true);	//Set the token on the arrive list
										CPUYetChanged = true;		//Because CPU player moves
									}
								});
								break;
							case 6:
								allList.getListArrivedPlayerGreen().forEach(arrivedGreen -> {
									if(arrivedGreen.getY() == false && CPUYetChanged == false){
										arriveAction(new ImageIcon(getClass().getClassLoader().getResource("image/button_green_baby_cross.png")), arrivedGreen.getX(), allList.getListArrivedPlayerGreen(), nPlayers, null, start6, 51);
										arrivedGreen.setY(true);	//Set the token on the arrive list
										CPUYetChanged = true;		//Because CPU player moves
									}
								});
								break;
							}
						}
					});

					newPosition.forEach(x->{	
						if(!CPUYetChanged){
							i = ApplicationImpl.getM().getPositionFromNStep(x, appC.getTurns()-1, nPlayers);	//Get the old position of the token
							if(allList.getRouteButton().get(i-1).getY() != 0 && allList.getRouteButton().get(i-1).getY() != appC.getTurns()){
								//If a token can eat another token of a different player
								appC.setColorToken(appC.getTurns(), allList.getRouteButton().get(i-1).getX());
								allList.getRouteButton().get(i-1).setY(appC.getTurns());		//Set the number of the button with the player id
								appC.getOriginPosition().forEach(y->{
									if(y.getPosition() == i - appC.getlastDice()){
										allList.getRouteButton().get(y.getPosition()-1).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white.png"))); //-1 perchè lo zero di la è l'uno di qua
										allList.getRouteButton().get(y.getPosition()-1).setY(0);	//Set 0 because now there isn't a token.
										appC.moveToken(i, nPlayers);
									}
									else if(y.getPosition() == i - appC.getlastDice() + 48){
										allList.getRouteButton().get(y.getPosition()-1).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white.png"))); //-1 perchè lo zero di la è l'uno di qua
										allList.getRouteButton().get(y.getPosition()-1).setY(0);	//Set 0 because now there isn't a token.
										appC.moveToken(i, nPlayers);
									}
								});
								CPUYetChanged = true;		//Because CPU player moves
							}
						}
					});

					if(!CPUYetChanged){
						//Move a token in starting position
						appC.getOriginPosition().forEach(x->{	
							if(x.getPosition() == (appC.getTurns()-1)*8+1 && !CPUYetChanged){
								if(allList.getRouteButton().get(x.getPosition() + appC.getlastDice() -1).getY() != appC.getTurns()){
									appC.setColorToken(appC.getTurns(), allList.getRouteButton().get(x.getPosition() + appC.getlastDice() -1).getX());
									allList.getRouteButton().get(x.getPosition() + appC.getlastDice() -1).setY(appC.getTurns());	//Set the number of the button with the player id
									allList.getRouteButton().get(x.getPosition()-1).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white.png"))); //-1 perchè lo zero di la è l'uno di qua
									allList.getRouteButton().get(x.getPosition()-1).setY(0);	//Set 0 because now there isn't a token
									appC.moveToken(x.getPosition() + appC.getlastDice(), nPlayers);
									CPUYetChanged = true;		//Because CPU player moves
								}
							}
						});
					}

					if(!CPUYetChanged){
						//Move the token nearest arrive cells
						i = ApplicationImpl.getM().getPositionFromNStep(newPosition.get(0), appC.getTurns()-1, nPlayers);
						allList.getRouteButton().forEach(jb->{
							if(Integer.parseInt(jb.getX().getName()) == i && jb.getY() != appC.getTurns()){
								appC.setColorToken(appC.getTurns(), jb.getX());
								jb.setY(appC.getTurns());	//Set the number of the button with the player id
							}
						});

						appC.getOriginPosition().forEach(y->{
							if(y.getPosition() == i - appC.getlastDice()){
								allList.getRouteButton().get(y.getPosition()-1).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white.png"))); //-1 perchè lo zero di la è l'uno di qua
								allList.getRouteButton().get(y.getPosition()-1).setY(0);	//Set 0 because now there isn't a token
							}
							else if(y.getPosition() == i - appC.getlastDice() + 48){
								allList.getRouteButton().get(y.getPosition()-1).getX().setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/button-white.png"))); //-1 perchè lo zero di la è l'uno di qua
								allList.getRouteButton().get(y.getPosition()-1).setY(0);	//Set 0 because now there isn't a token
							}
						});
						appC.setAllEnabled();
						appC.moveToken(i, nPlayers);
					}
					CPUYetChanged = true;		//Because CPU player moves			
				}
				appC.setAllEnabled(); 
				if(appC.getlastDice() != 6){
					appC.increaseTurns(nPlayers);
					start6.showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn.");
				}
				if(appC.getlastDice() == 6){
					start6.showMessage(appC.getPlayer().getName().toUpperCase() + " is your turn.");
				}
				start6.setTextResult("");
				start6.setDiceEnabled(true);
				appC.setChangeTable(false); 
			}
		}
	}
}
