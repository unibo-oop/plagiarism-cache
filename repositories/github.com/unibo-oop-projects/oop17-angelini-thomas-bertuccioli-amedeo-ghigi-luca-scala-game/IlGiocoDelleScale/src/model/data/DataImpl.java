package model.data;

import java.util.List;
import java.util.function.Supplier;
import model.dice.Dice;

public class DataImpl implements Data{

	List<Dice> diceList;
	int lastNumber;
	private final static int MINFINISH=64;
	private final static int MAXFINISH=400;
	private static final int MAXDICE=3;
	private static final Supplier<RuntimeException> diceError = () -> new IllegalStateException("Too many Dice");
	private static final Supplier<RuntimeException> cellError = () -> new IllegalStateException("Error with number of cell");

	public DataImpl(List<Dice> diceList,int lastNumber) {
		
		this.diceList=diceList;
		if (this.diceList.size()>MAXDICE){
			throw diceError.get();
		} 
		this.lastNumber=lastNumber;
		if ((this.lastNumber<MINFINISH) || (this.lastNumber>MAXFINISH)){
			throw cellError.get();
		}
	}
	
	@Override
	public int getFinishNumber() {
		
		return this.lastNumber;
	}

	@Override
	public List<Dice> getDice() {
		
		return this.diceList;
	}
	
	
}
