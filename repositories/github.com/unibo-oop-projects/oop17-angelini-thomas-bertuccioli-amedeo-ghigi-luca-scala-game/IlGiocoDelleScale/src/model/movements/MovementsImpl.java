package model.movements;

import model.data.Data;
import model.pawns.Pawns;

public class MovementsImpl implements Movements {
	
	private int finalPosition;
	private Data Data;
	private final static int Start=0;
	
	public MovementsImpl(Data data) {
		
		this.Data=data;
	}
	
	@Override
	public void changePosition(Pawns p) {
		
	p.setPosition(this.dicePosition(p.getPosition(),
				  this.Data.getDice().stream().mapToInt(d->d.roll()).sum())); 
	}
	
	@Override
	public int dicePosition(int initialPosition, int diceNumber) {

		this.finalPosition=initialPosition+diceNumber;

		if (diceNumber>(this.Data.getFinishNumber()+(this.Data.getFinishNumber()-initialPosition))) {  // You can't do 2 maps round
			return Start;
		}else if (this.finalPosition > this.Data.getFinishNumber()){
			return (this.Data.getFinishNumber()-(this.finalPosition-this.Data.getFinishNumber()));
		}else{
			return this.finalPosition;
		}
		
	}


	@Override
	public boolean checkWin(int pos) {
		
		if (pos==this.Data.getFinishNumber()){
			return true;
		}
		return false;
	}


}
