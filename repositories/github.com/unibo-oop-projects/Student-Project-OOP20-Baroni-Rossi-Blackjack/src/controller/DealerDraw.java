package controller;

import java.util.List;

import model.CardImpl;
/**
 * 
 * @author 
 *
 */
public interface DealerDraw {
	/**/
	void DrawCard();
	/**/
	List<CardImpl> getDealerHand();
	/**/
	void ResetDealer();
	/**/
	int getDealerNumberCard();
	/**/
	int getPointDealer();
	/**/
	void setNoAce();

}