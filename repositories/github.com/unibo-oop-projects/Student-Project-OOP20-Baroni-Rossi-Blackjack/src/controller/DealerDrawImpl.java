package controller;

import java.util.ArrayList;
import java.util.List;

import model.Card;
import model.CardImpl;
import model.Values;
/**
 * 
 * @author 
 *
 */

public class DealerDrawImpl implements DealerDraw {
	
	private final static int ACE_VALUE = 11;
	private final static int MAX_DEALER_HAND_SIZE = 6;
	private List<CardImpl> dealerHand  = new ArrayList<CardImpl>();
	private int pointdealer;
	private PickCard pickCard = new PickCard();
	
	public DealerDrawImpl() {
		
	}
	
	/*
	 * method to draw a new card
	 */
	@Override
	public void DrawCard() {
		CardImpl card = pickCard.pickedCard();
		if(card.getValues() == Values.one && getPointDealer()+ ACE_VALUE < 22) {
			card.setAceOrNot(Values.getValue(14));
			this.dealerHand.add(card);
		}else if(card.getValues() == Values.one && getPointDealer()+ ACE_VALUE > 22 && this.dealerHand.size() < MAX_DEALER_HAND_SIZE) {
			card.setAceOrNot(Values.getValue(1));	
			this.dealerHand.add(card);
			this.setNoAce();
		}else{
			this.dealerHand.add(card);
			if(getPointDealer() > 21) {
				this.setNoAce();
			}
		}
	}
	
	@Override
	public List<CardImpl> getDealerHand(){
		return this.dealerHand;
	}
	
	@Override
	public void ResetDealer() {
		this.dealerHand.clear();
	}
	
	@Override
	public int getDealerNumberCard() {
		return this.dealerHand.size();
	}
	


	/**
	 *method to get point of dealer
	 */
	@Override
	public int getPointDealer() {
		this.pointdealer = 0;
		for(Card carta: this.dealerHand){
			this.pointdealer += Values.getValues(carta.getValues());
		}
		return this.pointdealer;
	}
	
	/**
	 *method to set value of ace to one  
	 */
	@Override
	public void setNoAce() {	
		for(int i=0;i<this.dealerHand.size();i++) {
			if(this.dealerHand.get(i).getValues().getV() == ACE_VALUE) {
				this.dealerHand.get(i).setAceOrNot(Values.one);
				if(this.getPointDealer() < 21) {
					break;
				}
			}
		}	
	}

	
}
