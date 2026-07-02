package model.data;

import java.util.ArrayList;
import java.util.List;

import model.dice.Dice;
import model.dice.ListDiceImpl;

public class ListDataImpl implements ListData{

	private static final int CLASSIC=100;
	private List<Dice> diceList = new ArrayList<>();
	
	@Override
	public Data classicMode() {
		
		this.diceList.add(new ListDiceImpl().classicDice());
		return this.personalizedMode(CLASSIC,this.diceList);
	}

	@Override
	public Data personalizedMode(int cellNumber, List<Dice> diceList) {

		this.diceList=diceList;
		return new DataImpl(diceList, cellNumber);
	}


}
