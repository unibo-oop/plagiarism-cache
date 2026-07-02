package controller;

import java.util.ArrayList;
import java.util.List;

import model.Card;
import model.CardImpl;
import model.Values;

public class PlayerDrawImpl implements PlayerDraw{

	private final static int ACE_VALUE = 11;
	private List<CardImpl> playerHand = new ArrayList<CardImpl>();
	private int pointplayer;
	
	private PickCard pickCard = new PickCard();
	
	public PlayerDrawImpl() {
		
	}

	/**
	 *method to draw a new card
	 */
	@Override
	public void DrawCard() {
		CardImpl card = pickCard.pickedCard();
		if(card.getValues() == Values.one && getPointPlayer()+ ACE_VALUE < 22) {
			card.setAceOrNot(Values.getValue(14));
		}

		this.playerHand.add(card);
		if(getPointPlayer() > 21) {
			this.setNoAce();
		}
	
	}
	
	@Override
	public List<CardImpl> getPlayerHand(){
		return this.playerHand;
	}
	
	@Override
	public int getPlayerNumberCard() {
		return this.playerHand.size();
	}
	
	@Override
	public void ResetPlayer() {
		this.playerHand.clear();
	}
	
	/**
	 *method to get point of dealer
	 */
	@Override
	public int getPointPlayer() {
		
		this.pointplayer = 0;
		for(Card carta:this.playerHand) {
			this.pointplayer += Values.getValues(carta.getValues());
		}
		return this.pointplayer;
	}
	

	/**
	 *method to set value of ace to one
	 */
	@Override
	public void setNoAce() {
		for(int i=0;i<this.playerHand.size();i++) {
			if(playerHand.get(i).getValues().getV() == ACE_VALUE) {
				playerHand.get(i).setAceOrNot(Values.one);
				if(this.getPointPlayer() < 21) {
					break;
				}
			}
		}

	}
	
	
}
