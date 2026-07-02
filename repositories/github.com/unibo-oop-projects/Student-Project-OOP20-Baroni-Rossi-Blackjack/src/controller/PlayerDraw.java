package controller;

import java.util.List;

import model.CardImpl;
/**
 * 
 * @author 
 *
 */
public interface PlayerDraw {

	void DrawCard();

	List<CardImpl> getPlayerHand();

	int getPlayerNumberCard();

	void ResetPlayer();

	int getPointPlayer();

	void setNoAce();

}