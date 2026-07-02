package model.dice;

import java.util.Map;

public interface ListDice {

	Dice classicDice();
	
	Dice twentyFaceDice();
	
	Dice multiFaceDice(int numberOfFace);
	
	Dice specialTwentyDice(Map<Integer,Integer> map);
	
	Dice specialClassicDice(Map<Integer,Integer> map);
	
	Dice totalPersonalized(Map<Integer,Integer> map ,int number); // Personalized
	
}
